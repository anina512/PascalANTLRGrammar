package com.compiler.delphi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.antlr.v4.runtime.tree.ParseTree;

/**
 * Visitor class to interpret the Delphi program.
 * Extends the ANTLR-generated base visitor.
 */
public class DelphiInterpreter extends DelphiBaseVisitor<Object> {

    // Symbol Table Management
    private final Stack<Map<String, Object>> scopes = new Stack<>(); // Stack for managing scopes
    private final Map<String, ParseTree> functions = new HashMap<>(); // Store function/procedure definitions
    private final Map<String, ParseTree> classes = new HashMap<>(); // Store class definitions
    private final Map<String, Object> globalVariables = new HashMap<>(); // Global variable storage

    // Control Flow Flags
    private boolean breakFlag = false;
    private boolean continueFlag = false;
    private Object returnValue = null; // For function returns

    /**
     * Constructor: Initializes the global scope.
     */
    public DelphiInterpreter() {
        // Initialize the global scope
        scopes.push(globalVariables);
    }

    /**
     * Get the current scope (innermost).
     */
    private Map<String, Object> getCurrentScope() {
        if (scopes.isEmpty()) {
            throw new RuntimeException("Scope stack is empty");
        }
        return scopes.peek();
    }

    /**
     * Look up a variable's value, searching from current scope outwards.
     */
    private Object lookupVariable(String name) {
        // Search from inner scope outwards
        for (int i = scopes.size() - 1; i >= 0; i--) {
            if (scopes.get(i).containsKey(name)) {
                return scopes.get(i).get(name);
            }
        }
        
        throw new RuntimeException("Variable '" + name + "' not defined in any accessible scope");
    }

    /**
     * Assign a value to a variable. Tries current scope first, then outer scopes.
     * If not found, declares it in the current scope.
     */
    private void assignVariable(String name, Object value) {
        // Try assigning in current scope first
        Map<String, Object> currentScope = getCurrentScope();
        if (currentScope.containsKey(name)) {
            currentScope.put(name, value);
            return;
        }

        // Check outer scopes
        for (int i = scopes.size() - 2; i >= 0; i--) {
            if (scopes.get(i).containsKey(name)) {
                scopes.get(i).put(name, value);
                return;
            }
        }

        // If not found anywhere, declare/assign in the current scope
        currentScope.put(name, value);
    }

    /**
     * Entry point for visiting the program.
     */
    @Override
    public Object visitProgram(DelphiParser.ProgramContext ctx) {
        System.out.println("Interpreting program: " + ctx.programHeading().identifier().getText());
        
        // Visit the main program block
        Object result = visit(ctx.block());
        
        System.out.println("Interpretation finished.");
        return result;
    }

    /**
     * Visit program block. Handles declarations and compound statement.
     */
    @Override
    public Object visitBlock(DelphiParser.BlockContext ctx) {
        // Visit all declarations first
        if (ctx.constantDefinitionPart() != null) {
            for (DelphiParser.ConstantDefinitionPartContext declCtx : ctx.constantDefinitionPart()) {
                visit(declCtx);
            }
        }
        
        if (ctx.typeDefinitionPart() != null) {
            for (DelphiParser.TypeDefinitionPartContext declCtx : ctx.typeDefinitionPart()) {
                visit(declCtx);
            }
        }
        
        if (ctx.variableDeclarationPart() != null) {
            for (DelphiParser.VariableDeclarationPartContext declCtx : ctx.variableDeclarationPart()) {
                visit(declCtx);
            }
        }
        
        if (ctx.procedureAndFunctionDeclarationPart() != null) {
            for (DelphiParser.ProcedureAndFunctionDeclarationPartContext declCtx : ctx.procedureAndFunctionDeclarationPart()) {
                visit(declCtx);
            }
        }
        
        if (ctx.classDeclarationPart() != null) {
            for (DelphiParser.ClassDeclarationPartContext declCtx : ctx.classDeclarationPart()) {
                visit(declCtx);
            }
        }
        
        // Then visit the compound statement
        return visit(ctx.compoundStatement());
    }

    /**
     * Visit declaration parts (this method should be removed since we handle all declarations directly in visitBlock)
     */
    public Object visitDeclarationPart(Object ctx) {
        throw new UnsupportedOperationException("Declaration parts should be handled directly in visitBlock");
    }

    /**
     * Visit variable declaration part
     */
    @Override
    public Object visitVariableDeclarationPart(DelphiParser.VariableDeclarationPartContext ctx) {
        for (DelphiParser.VariableDeclarationContext varDecl : ctx.variableDeclaration()) {
            visit(varDecl);
        }
        return null;
    }

    /**
     * Visit a variable declaration
     */
    @Override
    public Object visitVariableDeclaration(DelphiParser.VariableDeclarationContext ctx) {
        String typeName = ctx.typeSpec().getText();
        Map<String, Object> currentScope = getCurrentScope();
        
        // Process each identifier in the declaration
        for (DelphiParser.IdentifierContext idCtx : ctx.identifierList().identifier()) {
            String varName = idCtx.getText();
            
            // Check if already defined in current scope
            if (currentScope.containsKey(varName)) {
                throw new RuntimeException("Variable '" + varName + "' already declared in this scope.");
            }
            
            // Initialize to null (or default value based on type)
            currentScope.put(varName, getDefaultValueForType(typeName));
        }
        
        return null;
    }

    /**
     * Get default value for a given type
     */
    private Object getDefaultValueForType(String typeName) {
        // Convert to lowercase for case-insensitive comparison
        String type = typeName.toLowerCase();
        
        switch (type) {
            case "integer": return 0;
            case "real": case "single": case "double": return 0.0;
            case "boolean": return false;
            case "char": return '\0';
            case "string": return "";
            default: return null; // For complex types, arrays, etc.
        }
    }

    /**
     * Visit compound statement (BEGIN...END)
     */
    @Override
    public Object visitCompoundStatement(DelphiParser.CompoundStatementContext ctx) {
        Object result = null;
        
        // Visit all statements in the statement list
        if (ctx.statementList() != null) {
            for (DelphiParser.StatementContext stmtCtx : ctx.statementList().statement()) {
                result = visit(stmtCtx);
                
                // Handle control flow
                if (breakFlag || continueFlag || returnValue != null) {
                    break;
                }
            }
        }
        
        return result;
    }

    /**
     * Visit a statement (dispatches to specific statement type)
     */
    @Override
    public Object visitStatement(DelphiParser.StatementContext ctx) {
        return visit(ctx.unlabelledStatement());
    }

    /**
     * Visit unlabelled statement (dispatches to simple or structured statement)
     */
    @Override
    public Object visitUnlabelledStatement(DelphiParser.UnlabelledStatementContext ctx) {
        if (ctx.simpleStatement() != null) {
            return visit(ctx.simpleStatement());
        } else if (ctx.structuredStatement() != null) {
            return visit(ctx.structuredStatement());
        }
        return null;
    }

    /**
     * Visit simple statement (dispatches to specific simple statement type)
     */
    @Override
    public Object visitSimpleStatement(DelphiParser.SimpleStatementContext ctx) {
        if (ctx.assignmentStatement() != null) {
            return visit(ctx.assignmentStatement());
        } else if (ctx.procedureStatement() != null) {
            return visit(ctx.procedureStatement());
        } else if (ctx.gotoStatement() != null) {
            return visit(ctx.gotoStatement());
        } else if (ctx.breakStatement() != null) {
            return visit(ctx.breakStatement());
        } else if (ctx.continueStatement() != null) {
            return visit(ctx.continueStatement());
        }
        // Empty statement
        return null;
    }

    /**
     * Visit assignment statement (variable := expression)
     */
    @Override
    public Object visitAssignmentStatement(DelphiParser.AssignmentStatementContext ctx) {
        String varName = ctx.variable().getText();
        Object value = visit(ctx.expression());
        
        assignVariable(varName, value);
        return null;
    }

    /**
     * Visit procedure statement (procedure call)
     */
    @Override
    public Object visitProcedureStatement(DelphiParser.ProcedureStatementContext ctx) {
        String procName = ctx.identifier().getText().toLowerCase();
        
        // Handle built-in procedures
        if (procName.equals("writeln") || procName.equals("write")) {
            // Evaluate arguments
            List<Object> args = new ArrayList<>();
            if (ctx.parameterList() != null) {
                for (DelphiParser.ActualParameterContext exprCtx : ctx.parameterList().actualParameter()) {
                    args.add(visit(exprCtx.expression()));
                }
            }
            
            // Print each argument
            for (Object arg : args) {
                System.out.print(arg);
                if (args.size() > 1 && arg != args.get(args.size() - 1)) {
                    System.out.print(" ");
                }
            }
            
            // Add newline for writeln
            if (procName.equals("writeln")) {
                System.out.println();
            }
            
            return null;
        }
        
        // Handle user-defined procedures
        if (!functions.containsKey(procName)) {
            throw new RuntimeException("Procedure '" + procName + "' not defined.");
        }
        
        ParseTree procNode = functions.get(procName);
        if (!(procNode instanceof DelphiParser.ProcedureDeclarationContext)) {
            throw new RuntimeException("Identifier '" + procName + "' is not a procedure.");
        }
        
        DelphiParser.ProcedureDeclarationContext procDecl = (DelphiParser.ProcedureDeclarationContext) procNode;
        
        // Create a new scope for procedure execution
        scopes.push(new HashMap<>());
        
        // Evaluate arguments and bind to parameters (omitted for brevity)
        // TODO: Implement parameter binding
        
        // Execute procedure body
        Object result = visit(procDecl.block());
        
        // Restore scope
        scopes.pop();
        
        return result;
    }

    /**
     * Visit break statement
     */
    @Override
    public Object visitBreakStatement(DelphiParser.BreakStatementContext ctx) {
        breakFlag = true;
        return null;
    }

    /**
     * Visit continue statement
     */
    @Override
    public Object visitContinueStatement(DelphiParser.ContinueStatementContext ctx) {
        continueFlag = true;
        return null;
    }

    /**
     * Visit structured statement (dispatches to compound, conditional, repetitive, etc.)
     */
    @Override
    public Object visitStructuredStatement(DelphiParser.StructuredStatementContext ctx) {
        if (ctx.compoundStatement() != null) {
            return visit(ctx.compoundStatement());
        } else if (ctx.conditionalStatement() != null) {
            return visit(ctx.conditionalStatement());
        } else if (ctx.repetitiveStatement() != null) {
            return visit(ctx.repetitiveStatement());
        } else if (ctx.withStatement() != null) {
            return visit(ctx.withStatement());
        }
        return null;
    }

    /**
     * Visit conditional statement (if, case)
     */
    @Override
    public Object visitConditionalStatement(DelphiParser.ConditionalStatementContext ctx) {
        if (ctx.ifStatement() != null) {
            return visit(ctx.ifStatement());
        } else if (ctx.caseStatement() != null) {
            return visit(ctx.caseStatement());
        }
        return null;
    }

    /**
     * Visit if statement
     */
    @Override
    public Object visitIfStatement(DelphiParser.IfStatementContext ctx) {
        Object condition = visit(ctx.expression());
        
        if (!(condition instanceof Boolean)) {
            throw new RuntimeException("IF condition must evaluate to a Boolean, got: " + condition);
        }
        
        if ((Boolean) condition) {
            return visit(ctx.statement(0)); // Execute THEN part
        } else if (ctx.ELSE() != null && ctx.statement().size() > 1) {
            return visit(ctx.statement(1)); // Execute ELSE part
        }
        
        return null;
    }

    /**
     * Visit repetitive statement (while, repeat, for)
     */
    @Override
    public Object visitRepeatStatement(DelphiParser.RepeatStatementContext ctx) {
        do {
            // Create a new scope for each iteration
            scopes.push(new HashMap<>());
            
            // Execute the statement list
            visit(ctx.statementList());
            
            // Remove the scope
            scopes.pop();
            
            // Handle break/continue
            if (breakFlag) {
                breakFlag = false;
                break;
            }
            
            if (continueFlag) {
                continueFlag = false;
            }
            
            // Evaluate the condition (continue until condition is true)
            Object conditionObj = visit(ctx.expression());
            
            if (!(conditionObj instanceof Boolean)) {
                throw new RuntimeException("REPEAT-UNTIL condition must evaluate to a Boolean, got: " + conditionObj);
            }
            
            // If condition is true, we exit the loop
            if ((Boolean) conditionObj) {
                break;
            }
            
        } while (true); // We handle the exit condition inside the loop
        
        return null;
    }

    /**
     * Visit while statement
     */
    @Override
    public Object visitWhileStatement(DelphiParser.WhileStatementContext ctx) {
        Object conditionObj = visit(ctx.expression());
        
        if (!(conditionObj instanceof Boolean)) {
            throw new RuntimeException("WHILE condition must evaluate to a Boolean, got: " + conditionObj);
        }
        
        Boolean condition = (Boolean) conditionObj;
        
        while (condition) {
            // Create a new scope for the loop body
            scopes.push(new HashMap<>());
            
            // Execute loop body
            visit(ctx.statement());
            
            // Remove loop body scope
            scopes.pop();
            
            // Handle break/continue
            if (breakFlag) {
                breakFlag = false;
                break;
            }
            
            if (continueFlag) {
                continueFlag = false;
            }
            
            // Re-evaluate condition
            conditionObj = visit(ctx.expression());
            
            if (!(conditionObj instanceof Boolean)) {
                throw new RuntimeException("WHILE condition must re-evaluate to a Boolean, got: " + conditionObj);
            }
            
            condition = (Boolean) conditionObj;
        }
        
        return null;
    }

    /**
     * Visit for statement
     */
    @Override
    public Object visitForStatement(DelphiParser.ForStatementContext ctx) {
        String loopVarName = ctx.identifier().getText();
        Object startValueObj = visit(ctx.expression(0));
        Object endValueObj = visit(ctx.expression(1));
        
        if (!(startValueObj instanceof Integer) || !(endValueObj instanceof Integer)) {
            throw new RuntimeException("FOR loop bounds must be integers, got: " + 
                                      startValueObj.getClass().getSimpleName() + " and " + 
                                      endValueObj.getClass().getSimpleName());
        }
        
        int startValue = (Integer) startValueObj;
        int endValue = (Integer) endValueObj;
        boolean ascending = ctx.TO() != null; // TO vs DOWNTO
        
        // Create new scope for the loop
        scopes.push(new HashMap<>());
        Map<String, Object> loopScope = getCurrentScope();
        
        if (ascending) {
            for (int i = startValue; i <= endValue; i++) {
                // Set loop variable in the loop scope
                loopScope.put(loopVarName, i);
                
                // Execute loop body
                visit(ctx.statement());
                
                // Handle break/continue
                if (breakFlag) {
                    breakFlag = false;
                    break;
                }
                
                if (continueFlag) {
                    continueFlag = false;
                }
            }
        } else { // Descending (DOWNTO)
            for (int i = startValue; i >= endValue; i--) {
                // Set loop variable in the loop scope
                loopScope.put(loopVarName, i);
                
                // Execute loop body
                visit(ctx.statement());
                
                // Handle break/continue
                if (breakFlag) {
                    breakFlag = false;
                    break;
                }
                
                if (continueFlag) {
                    continueFlag = false;
                }
            }
        }
        
        // Remove loop scope
        scopes.pop();
        
        return null;
    }

    /**
     * Visit expression
     */
    @Override
    public Object visitExpression(DelphiParser.ExpressionContext ctx) {
        Object left = visit(ctx.simpleExpression(0));
        
        // If there's a relational operator
        if (ctx.relationalOperator() != null && ctx.simpleExpression().size() > 1) {
            Object right = visit(ctx.simpleExpression(1));
            String op = ctx.relationalOperator().getText().toLowerCase();
            
            // Apply the appropriate comparison
            return applyRelationalOperator(left, right, op);
        }
        
        return left;
    }
    
    /**
     * Apply a relational operator to two values
     */
    private Object applyRelationalOperator(Object left, Object right, String op) {
        // Handle string comparisons
        if (left instanceof String && right instanceof String) {
            String leftStr = (String) left;
            String rightStr = (String) right;
            
            switch (op) {
                case "=": return leftStr.equals(rightStr);
                case "<>": return !leftStr.equals(rightStr);
                case "<": return leftStr.compareTo(rightStr) < 0;
                case "<=": return leftStr.compareTo(rightStr) <= 0;
                case ">": return leftStr.compareTo(rightStr) > 0;
                case ">=": return leftStr.compareTo(rightStr) >= 0;
                default: throw new RuntimeException("Unknown relational operator: " + op);
            }
        } 
        // Handle numeric comparisons
        else if (left instanceof Number && right instanceof Number) {
            double leftVal = ((Number) left).doubleValue();
            double rightVal = ((Number) right).doubleValue();
            
            switch (op) {
                case "=": return leftVal == rightVal;
                case "<>": return leftVal != rightVal;
                case "<": return leftVal < rightVal;
                case "<=": return leftVal <= rightVal;
                case ">": return leftVal > rightVal;
                case ">=": return leftVal >= rightVal;
                default: throw new RuntimeException("Unknown relational operator: " + op);
            }
        } 
        // Handle boolean comparisons
        else if (left instanceof Boolean && right instanceof Boolean) {
            boolean leftVal = (Boolean) left;
            boolean rightVal = (Boolean) right;
            
            switch (op) {
                case "=": return leftVal == rightVal;
                case "<>": return leftVal != rightVal;
                default: throw new RuntimeException("Invalid boolean comparison with operator: " + op);
            }
        }
        
        throw new RuntimeException("Cannot compare values of types: " + 
                                  left.getClass().getSimpleName() + " and " + 
                                  right.getClass().getSimpleName());
    }

    /**
     * Visit simple expression
     */
    @Override
    public Object visitSimpleExpression(DelphiParser.SimpleExpressionContext ctx) {
        Object result = visit(ctx.term(0));
        
        // Apply all additive operators in sequence
        for (int i = 0; i < ctx.additiveOperator().size(); i++) {
            Object right = visit(ctx.term(i + 1));
            String op = ctx.additiveOperator(i).getText().toLowerCase();
            
            result = applyAdditiveOperator(result, right, op);
        }
        
        return result;
    }
    
    /**
     * Apply an additive operator to two values
     */
    private Object applyAdditiveOperator(Object left, Object right, String op) {
        // Handle string concatenation
        if (op.equals("+") && (left instanceof String || right instanceof String)) {
            return String.valueOf(left) + String.valueOf(right);
        }
        
        // Handle numeric operations
        if (left instanceof Number && right instanceof Number) {
            double leftVal = ((Number) left).doubleValue();
            double rightVal = ((Number) right).doubleValue();
            
            switch (op) {
                case "+": return leftVal + rightVal;
                case "-": return leftVal - rightVal;
                default: throw new RuntimeException("Unknown additive operator: " + op);
            }
        }
        
        // Handle boolean operations
        if (left instanceof Boolean && right instanceof Boolean) {
            boolean leftVal = (Boolean) left;
            boolean rightVal = (Boolean) right;
            
            switch (op.toLowerCase()) {
                case "or": return leftVal || rightVal;
                case "xor": return leftVal ^ rightVal;
                default: throw new RuntimeException("Invalid boolean operation with operator: " + op);
            }
        }
        
        throw new RuntimeException("Cannot apply operator '" + op + "' to types: " + 
                                  left.getClass().getSimpleName() + " and " + 
                                  right.getClass().getSimpleName());
    }

    /**
     * Visit term
     */
    @Override
    public Object visitTerm(DelphiParser.TermContext ctx) {
        Object result = visit(ctx.signedFactor(0));
        
        // Apply all multiplicative operators in sequence
        for (int i = 0; i < ctx.multiplicativeOperator().size(); i++) {
            Object right = visit(ctx.signedFactor(i + 1));
            String op = ctx.multiplicativeOperator(i).getText().toLowerCase();
            
            result = applyMultiplicativeOperator(result, right, op);
        }
        
        return result;
    }
    
    /**
     * Apply a multiplicative operator to two values
     */
    private Object applyMultiplicativeOperator(Object left, Object right, String op) {
        // Handle numeric operations
        if (left instanceof Number && right instanceof Number) {
            double leftVal = ((Number) left).doubleValue();
            double rightVal = ((Number) right).doubleValue();
            
            switch (op) {
                case "*": return leftVal * rightVal;
                case "/": 
                    if (rightVal == 0) throw new RuntimeException("Division by zero");
                    return leftVal / rightVal;
                case "div":
                    if (rightVal == 0) throw new RuntimeException("Division by zero");
                    return (int)(leftVal / rightVal);
                case "mod":
                    if (rightVal == 0) throw new RuntimeException("Division by zero");
                    return (int)leftVal % (int)rightVal;
                default: throw new RuntimeException("Unknown multiplicative operator: " + op);
            }
        }
        
        // Handle boolean operations
        if (left instanceof Boolean && right instanceof Boolean) {
            boolean leftVal = (Boolean) left;
            boolean rightVal = (Boolean) right;
            
            switch (op.toLowerCase()) {
                case "and": return leftVal && rightVal;
                default: throw new RuntimeException("Invalid boolean operation with operator: " + op);
            }
        }
        
        throw new RuntimeException("Cannot apply operator '" + op + "' to types: " + 
                                  left.getClass().getSimpleName() + " and " + 
                                  right.getClass().getSimpleName());
    }

    /**
     * Visit signed factor
     */
    @Override
    public Object visitSignedFactor(DelphiParser.SignedFactorContext ctx) {
        Object value = visit(ctx.factor());
        
        // Apply sign if present
        if (ctx.MINUS() != null) {
            if (value instanceof Number) {
                if (value instanceof Integer) {
                    return -((Integer) value);
                } else if (value instanceof Double) {
                    return -((Double) value);
                }
            }
            throw new RuntimeException("Cannot apply unary minus to non-numeric type: " + value.getClass().getSimpleName());
        }
        
        return value;
    }

    /**
     * Visit factor
     */
    @Override
    public Object visitFactor(DelphiParser.FactorContext ctx) {
        if (ctx.variable() != null) {
            return visit(ctx.variable());
        } else if (ctx.LPAREN() != null && ctx.expression() != null) {
            return visit(ctx.expression());
        } else if (ctx.functionDesignator() != null) {
            return visit(ctx.functionDesignator());
        } else if (ctx.unsignedConstant() != null) {
            return visit(ctx.unsignedConstant());
        } else if (ctx.NOT() != null) {
            Object value = visit(ctx.factor());
            if (!(value instanceof Boolean)) {
                throw new RuntimeException("Cannot apply NOT to non-boolean type: " + value.getClass().getSimpleName());
            }
            return !(Boolean) value;
        } else if (ctx.set_() != null) {
            return visit(ctx.set_());
        } else if (ctx.NIL() != null) {
            return null;
        }
        
        throw new RuntimeException("Unhandled factor type");
    }

    /**
     * Visit variable (handles simple variables and various access methods)
     */
    @Override
    public Object visitVariable(DelphiParser.VariableContext ctx) {
        // Simple variable reference
        if (ctx.identifier() != null && ctx.getChildCount() == 1) {
            String varName = ctx.identifier().getText();
            return lookupVariable(varName);
        }
        
        // TODO: Handle array access, record fields, pointer dereference, etc.
        throw new UnsupportedOperationException("Complex variable access not yet implemented");
    }

    /**
     * Visit unsigned constant
     */
    @Override
    public Object visitUnsignedConstant(DelphiParser.UnsignedConstantContext ctx) {
        if (ctx.unsignedNumber() != null) {
            return visit(ctx.unsignedNumber());
        } else if (ctx.stringLiteral() != null) {
            String str = ctx.stringLiteral().getText();
            // Remove surrounding quotes and handle escaped quotes
            return str.substring(1, str.length() - 1).replace("''", "'");
        } else if (ctx.NIL() != null) {
            return null;
        }
        
        throw new RuntimeException("Unknown unsigned constant type");
    }

    /**
     * Visit unsigned number
     */
    @Override
    public Object visitUnsignedNumber(DelphiParser.UnsignedNumberContext ctx) {
        if (ctx.unsignedInteger() != null) {
            return Integer.parseInt(ctx.unsignedInteger().getText());
        } else if (ctx.unsignedReal() != null) {
            return Double.parseDouble(ctx.unsignedReal().getText());
        }
        
        throw new RuntimeException("Unknown number type");
    }
    
    /**
     * Visit function designator (function call)
     */
    @Override
    public Object visitFunctionDesignator(DelphiParser.FunctionDesignatorContext ctx) {
        String funcName = ctx.identifier().getText().toLowerCase();
        
        // Handle user-defined functions
        if (!functions.containsKey(funcName)) {
            throw new RuntimeException("Function '" + funcName + "' not defined.");
        }
        
        ParseTree funcNode = functions.get(funcName);
        if (!(funcNode instanceof DelphiParser.FunctionDeclarationContext)) {
            throw new RuntimeException("Identifier '" + funcName + "' is not a function.");
        }
        
        DelphiParser.FunctionDeclarationContext funcDecl = (DelphiParser.FunctionDeclarationContext) funcNode;
        
        // Create a new scope for function execution
        Map<String, Object> funcScope = new HashMap<>();
        scopes.push(funcScope);
        
        // Evaluate arguments and bind to parameters (omitted for brevity)
        // TODO: Implement parameter binding
        
        // Reset return value
        returnValue = null;
        
        // Execute function body
        visit(funcDecl.block());
        
        // Get return value (from function name variable or returnValue field)
        Object result = funcScope.containsKey(funcName) ? funcScope.get(funcName) : returnValue;
        
        // Restore scope
        scopes.pop();
        
        // Clear return value for next call
        returnValue = null;
        
        return result;
    }
    
    /**
     * Visit procedure and function declaration part
     */
    @Override
    public Object visitProcedureAndFunctionDeclarationPart(DelphiParser.ProcedureAndFunctionDeclarationPartContext ctx) {
        return visit(ctx.procedureOrFunctionDeclaration());
    }
    
    /**
     * Visit procedure or function declaration
     */
    @Override
    public Object visitProcedureOrFunctionDeclaration(DelphiParser.ProcedureOrFunctionDeclarationContext ctx) {
        if (ctx.procedureDeclaration() != null) {
            return visit(ctx.procedureDeclaration());
        } else if (ctx.functionDeclaration() != null) {
            return visit(ctx.functionDeclaration());
        }
        return null;
    }
    
    /**
     * Visit procedure declaration
     */
    @Override
    public Object visitProcedureDeclaration(DelphiParser.ProcedureDeclarationContext ctx) {
        String procName = ctx.procedureHeading().identifier().getText().toLowerCase();
        
        // Store procedure for later invocation
        functions.put(procName, ctx);
        
        return null;
    }
    
    /**
     * Visit function declaration
     */
    @Override
    public Object visitFunctionDeclaration(DelphiParser.FunctionDeclarationContext ctx) {
        String funcName = ctx.functionHeading().identifier().getText().toLowerCase();
        
        // Store function for later invocation
        functions.put(funcName, ctx);
        
        return null;
    }
    
    /**
     * Visit class declaration part
     */
    @Override
    public Object visitClassDeclarationPart(DelphiParser.ClassDeclarationPartContext ctx) {
        String className = ctx.identifier().getText();
        
        // Store class definition for later instantiation
        classes.put(className, ctx);
        
        return null;
    }
}