// Generated from com/compiler/delphi/Delphi.g4 by ANTLR 4.13.1
package com.compiler.delphi;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link DelphiParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface DelphiVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link DelphiParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(DelphiParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#programHeading}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgramHeading(DelphiParser.ProgramHeadingContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#identifierList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierList(DelphiParser.IdentifierListContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(DelphiParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#labelDeclarationPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabelDeclarationPart(DelphiParser.LabelDeclarationPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#constantDefinitionPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantDefinitionPart(DelphiParser.ConstantDefinitionPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#constantDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantDefinition(DelphiParser.ConstantDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(DelphiParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#constantChr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantChr(DelphiParser.ConstantChrContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#typeDefinitionPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDefinitionPart(DelphiParser.TypeDefinitionPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#typeDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDefinition(DelphiParser.TypeDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#typeSpec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeSpec(DelphiParser.TypeSpecContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#simpleType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleType(DelphiParser.SimpleTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#scalarType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScalarType(DelphiParser.ScalarTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#subrangeType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubrangeType(DelphiParser.SubrangeTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#typeIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeIdentifier(DelphiParser.TypeIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#structuredType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructuredType(DelphiParser.StructuredTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#arrayType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayType(DelphiParser.ArrayTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#typeList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeList(DelphiParser.TypeListContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#indexType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexType(DelphiParser.IndexTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#recordType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecordType(DelphiParser.RecordTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#fieldList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldList(DelphiParser.FieldListContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#fixedPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFixedPart(DelphiParser.FixedPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#recordSection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecordSection(DelphiParser.RecordSectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#variantPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariantPart(DelphiParser.VariantPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#tag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTag(DelphiParser.TagContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#variant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariant(DelphiParser.VariantContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#constList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstList(DelphiParser.ConstListContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#setType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetType(DelphiParser.SetTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#fileType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFileType(DelphiParser.FileTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#pointerType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPointerType(DelphiParser.PointerTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#variableDeclarationPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclarationPart(DelphiParser.VariableDeclarationPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#variableDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclaration(DelphiParser.VariableDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#procedureAndFunctionDeclarationPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedureAndFunctionDeclarationPart(DelphiParser.ProcedureAndFunctionDeclarationPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#procedureOrFunctionDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedureOrFunctionDeclaration(DelphiParser.ProcedureOrFunctionDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#procedureDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedureDeclaration(DelphiParser.ProcedureDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#procedureHeading}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedureHeading(DelphiParser.ProcedureHeadingContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#functionDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDeclaration(DelphiParser.FunctionDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#functionHeading}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionHeading(DelphiParser.FunctionHeadingContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#directive}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDirective(DelphiParser.DirectiveContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#formalParameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalParameterList(DelphiParser.FormalParameterListContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#formalParameterSection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalParameterSection(DelphiParser.FormalParameterSectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#parameterGroup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterGroup(DelphiParser.ParameterGroupContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#classDeclarationPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDeclarationPart(DelphiParser.ClassDeclarationPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#memberDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberDeclaration(DelphiParser.MemberDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#constructorDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructorDeclaration(DelphiParser.ConstructorDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#destructorDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDestructorDeclaration(DelphiParser.DestructorDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#constructorImplementation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructorImplementation(DelphiParser.ConstructorImplementationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(DelphiParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#unlabelledStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnlabelledStatement(DelphiParser.UnlabelledStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#simpleStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleStatement(DelphiParser.SimpleStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#assignmentStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentStatement(DelphiParser.AssignmentStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#procedureStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedureStatement(DelphiParser.ProcedureStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#parameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterList(DelphiParser.ParameterListContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#actualParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActualParameter(DelphiParser.ActualParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#gotoStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGotoStatement(DelphiParser.GotoStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#emptyStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyStatement(DelphiParser.EmptyStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#breakStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStatement(DelphiParser.BreakStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#continueStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueStatement(DelphiParser.ContinueStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#structuredStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructuredStatement(DelphiParser.StructuredStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#compoundStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompoundStatement(DelphiParser.CompoundStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#statementList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementList(DelphiParser.StatementListContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#conditionalStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionalStatement(DelphiParser.ConditionalStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(DelphiParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#caseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseStatement(DelphiParser.CaseStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#caseListElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseListElement(DelphiParser.CaseListElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#repetitiveStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepetitiveStatement(DelphiParser.RepetitiveStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#whileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(DelphiParser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#repeatStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepeatStatement(DelphiParser.RepeatStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#forStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStatement(DelphiParser.ForStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#withStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWithStatement(DelphiParser.WithStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(DelphiParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#relationalOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalOperator(DelphiParser.RelationalOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#simpleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleExpression(DelphiParser.SimpleExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#additiveOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveOperator(DelphiParser.AdditiveOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(DelphiParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#multiplicativeOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeOperator(DelphiParser.MultiplicativeOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#signedFactor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSignedFactor(DelphiParser.SignedFactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(DelphiParser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#unsignedConstant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnsignedConstant(DelphiParser.UnsignedConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#functionDesignator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDesignator(DelphiParser.FunctionDesignatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#parameterValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterValue(DelphiParser.ParameterValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(DelphiParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#stringLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLiteral(DelphiParser.StringLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#set_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSet_(DelphiParser.Set_Context ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#elementList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementList(DelphiParser.ElementListContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElement(DelphiParser.ElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#label}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabel(DelphiParser.LabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#unsignedNumber}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnsignedNumber(DelphiParser.UnsignedNumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#unsignedInteger}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnsignedInteger(DelphiParser.UnsignedIntegerContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#unsignedReal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnsignedReal(DelphiParser.UnsignedRealContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#sign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSign(DelphiParser.SignContext ctx);
	/**
	 * Visit a parse tree produced by {@link DelphiParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(DelphiParser.IdentifierContext ctx);
}