grammar Delphi;

// Package declaration for generated Java files

// --- PARSER RULES ---

program
    : programHeading SEMI block DOT
    ;

programHeading
    : PROGRAM identifier (LPAREN identifierList RPAREN)?
    ;

identifierList
    : identifier (COMMA identifier)*
    ;

block
    : (labelDeclarationPart
      | constantDefinitionPart
      | typeDefinitionPart
      | variableDeclarationPart
      | procedureAndFunctionDeclarationPart
      | classDeclarationPart
      | constructorImplementation)* compoundStatement
    ;

// LABEL Declaration Part
labelDeclarationPart
    : LABEL label (COMMA label)* SEMI
    ;

// CONST Definition Part
constantDefinitionPart
    : CONST constantDefinition (SEMI constantDefinition)* SEMI
    ;

constantDefinition
    : identifier EQ constant
    ;

constant
    : unsignedNumber
    | sign unsignedNumber
    | identifier
    | sign identifier
    | stringLiteral
    | constantChr
    ;

constantChr
    : CHR LPAREN unsignedInteger RPAREN
    ;

// TYPE Definition Part
typeDefinitionPart
    : TYPE typeDefinition (SEMI typeDefinition)* SEMI
    ;

typeDefinition
    : identifier EQ typeSpec
    ;

typeSpec
    : simpleType
    | structuredType
    | pointerType
    | typeIdentifier
    ;

simpleType
    : scalarType
    | subrangeType
    | typeIdentifier
    ;

scalarType
    : LPAREN identifierList RPAREN
    ;

subrangeType
    : constant DOTDOT constant
    ;

typeIdentifier
    : identifier
    ;

structuredType
    : arrayType
    | recordType
    | setType
    | fileType
    ;

arrayType
    : ARRAY LBRACK typeList RBRACK OF typeSpec
    ;

typeList
    : indexType (COMMA indexType)*
    ;

indexType
    : simpleType
    ;

recordType
    : RECORD fieldList END
    ;

fieldList
    : fixedPart (SEMI variantPart)? 
    | variantPart
    ;

fixedPart
    : recordSection (SEMI recordSection)*
    ;

recordSection
    : identifierList COLON typeSpec
    ;

variantPart
    : CASE tag OF variant (SEMI variant)*
    ;

tag
    : identifier COLON typeIdentifier
    | typeIdentifier
    ;

variant
    : constList COLON LPAREN fieldList RPAREN
    ;

constList
    : constant (COMMA constant)*
    ;

setType
    : SET OF simpleType
    ;

fileType
    : FILE OF typeSpec
    ;

pointerType
    : POINTER typeIdentifier
    ;

// Variable Declaration Part
variableDeclarationPart
    : VAR variableDeclaration (SEMI variableDeclaration)* SEMI
    ;

variableDeclaration
    : identifierList COLON typeSpec
    ;

// Procedure and Function Declaration Part
procedureAndFunctionDeclarationPart
    : procedureOrFunctionDeclaration SEMI
    ;

procedureOrFunctionDeclaration
    : procedureDeclaration
    | functionDeclaration
    ;

procedureDeclaration
    : procedureHeading SEMI (block | directive)
    ;

procedureHeading
    : PROCEDURE identifier (formalParameterList)?
    ;

functionDeclaration
    : functionHeading SEMI (block | directive)
    ;

functionHeading
    : FUNCTION identifier (formalParameterList)? COLON typeIdentifier
    ;

directive
    : FORWARD
    ;

formalParameterList
    : LPAREN formalParameterSection (SEMI formalParameterSection)* RPAREN
    ;

formalParameterSection
    : parameterGroup
    | VAR parameterGroup
    | CONST parameterGroup
    | OUT parameterGroup
    ;

parameterGroup
    : identifierList COLON typeIdentifier
    ;

// Class Declarations
classDeclarationPart
    : CLASS identifier SEMI (memberDeclaration SEMI)* END
    ;

memberDeclaration
    : variableDeclaration
    | procedureDeclaration
    | functionDeclaration
    | constructorDeclaration
    | destructorDeclaration
    ;

constructorDeclaration
    : CONSTRUCTOR identifier (formalParameterList)? SEMI block
    ;

destructorDeclaration
    : DESTRUCTOR identifier SEMI block
    ;

constructorImplementation
    : CONSTRUCTOR identifier DOT identifier (formalParameterList)? SEMI block
    ;

// Statements
statement
    : label COLON unlabelledStatement
    | unlabelledStatement
    ;

unlabelledStatement
    : simpleStatement
    | structuredStatement
    ;

simpleStatement
    : assignmentStatement
    | procedureStatement
    | gotoStatement
    | emptyStatement
    | breakStatement
    | continueStatement
    ;

assignmentStatement
    : variable ASSIGN expression
    ;

procedureStatement
    : identifier (LPAREN parameterList RPAREN)?
    ;

parameterList
    : actualParameter (COMMA actualParameter)*
    ;

actualParameter
    : expression
    ;

gotoStatement
    : GOTO label
    ;

emptyStatement
    : // nothing
    ;

breakStatement
    : BREAK
    ;

continueStatement
    : CONTINUE
    ;

structuredStatement
    : compoundStatement
    | conditionalStatement
    | repetitiveStatement
    | withStatement
    ;

compoundStatement
    : BEGIN statementList END
    ;

statementList
    : statement (SEMI statement)*
    ;

conditionalStatement
    : ifStatement
    | caseStatement
    ;

ifStatement
    : IF expression THEN statement (ELSE statement)?
    ;

caseStatement
    : CASE expression OF caseListElement (SEMI caseListElement)* (SEMI ELSE statementList)? END
    ;

caseListElement
    : constList COLON statement
    ;

repetitiveStatement
    : whileStatement
    | repeatStatement
    | forStatement
    ;

whileStatement
    : WHILE expression DO statement
    ;

repeatStatement
    : REPEAT statementList UNTIL expression
    ;

forStatement
    : FOR identifier ASSIGN expression (TO | DOWNTO) expression DO statement
    ;

withStatement
    : WITH variable (COMMA variable)* DO statement
    ;

// Expressions
expression
    : simpleExpression (relationalOperator simpleExpression)?
    ;

relationalOperator
    : EQ
    | NE
    | LT
    | LE
    | GT
    | GE
    | IN
    ;

simpleExpression
    : term (additiveOperator term)*
    ;

additiveOperator
    : PLUS
    | MINUS
    | OR
    ;

term
    : signedFactor (multiplicativeOperator signedFactor)*
    ;

multiplicativeOperator
    : STAR
    | SLASH
    | DIV
    | MOD
    | AND
    ;

signedFactor
    : (PLUS | MINUS)? factor
    ;

factor
    : variable
    | LPAREN expression RPAREN
    | functionDesignator
    | unsignedConstant
    | NOT factor
    | set_
    | NIL
    ;

unsignedConstant
    : unsignedNumber
    | stringLiteral
    | NIL
    ;

functionDesignator
    : identifier LPAREN parameterList? RPAREN
    ;

parameterValue
    : expression
    ;

variable
    : identifier
    | variable LBRACK expression RBRACK
    | variable DOT identifier
    | variable POINTER
    ;

// Basic Elements
stringLiteral
    : STRING_LITERAL
    ;

// Sets
set_
    : LBRACK elementList? RBRACK
    ;

elementList
    : element (COMMA element)*
    ;

element
    : expression (DOTDOT expression)?
    ;

// Other basics
label
    : unsignedInteger
    ;

unsignedNumber
    : unsignedInteger
    | unsignedReal
    ;

unsignedInteger
    : INTEGER_CONST
    ;

unsignedReal
    : REAL_CONST
    ;

sign
    : PLUS
    | MINUS
    ;

identifier
    : IDENTIFIER
    ;

// --- LEXER RULES ---

// Keywords
PROGRAM: [Pp][Rr][Oo][Gg][Rr][Aa][Mm];
CONST: [Cc][Oo][Nn][Ss][Tt];
TYPE: [Tt][Yy][Pp][Ee];
VAR: [Vv][Aa][Rr];
PROCEDURE: [Pp][Rr][Oo][Cc][Ee][Dd][Uu][Rr][Ee];
FUNCTION: [Ff][Uu][Nn][Cc][Tt][Ii][Oo][Nn];
BEGIN: [Bb][Ee][Gg][Ii][Nn];
END: [Ee][Nn][Dd];
ARRAY: [Aa][Rr][Rr][Aa][Yy];
OF: [Oo][Ff];
RECORD: [Rr][Ee][Cc][Oo][Rr][Dd];
SET: [Ss][Ee][Tt];
CASE: [Cc][Aa][Ss][Ee];
FILE: [Ff][Ii][Ll][Ee];
FORWARD: [Ff][Oo][Rr][Ww][Aa][Rr][Dd];
CLASS: [Cc][Ll][Aa][Ss][Ss];
CONSTRUCTOR: [Cc][Oo][Nn][Ss][Tt][Rr][Uu][Cc][Tt][Oo][Rr];
DESTRUCTOR: [Dd][Ee][Ss][Tt][Rr][Uu][Cc][Tt][Oo][Rr];
IF: [Ii][Ff];
THEN: [Tt][Hh][Ee][Nn];
ELSE: [Ee][Ll][Ss][Ee];
WHILE: [Ww][Hh][Ii][Ll][Ee];
DO: [Dd][Oo];
REPEAT: [Rr][Ee][Pp][Ee][Aa][Tt];
UNTIL: [Uu][Nn][Tt][Ii][Ll];
FOR: [Ff][Oo][Rr];
TO: [Tt][Oo];
DOWNTO: [Dd][Oo][Ww][Nn][Tt][Oo];
WITH: [Ww][Ii][Tt][Hh];
GOTO: [Gg][Oo][Tt][Oo];
BREAK: [Bb][Rr][Ee][Aa][Kk];
CONTINUE: [Cc][Oo][Nn][Tt][Ii][Nn][Uu][Ee];
OUT: [Oo][Uu][Tt];
NIL: [Nn][Ii][Ll];
IN: [Ii][Nn];
LABEL: [Ll][Aa][Bb][Ee][Ll];
DIV: [Dd][Ii][Vv];
MOD: [Mm][Oo][Dd];
AND: [Aa][Nn][Dd];
OR: [Oo][Rr];
NOT: [Nn][Oo][Tt];
POINTER: '^';
CHR: [Cc][Hh][Rr];

// Operators
ASSIGN: ':=';
PLUS: '+';
MINUS: '-';
STAR: '*';
SLASH: '/';
EQ: '=';
GT: '>';
LT: '<';
GE: '>=';
LE: '<=';
NE: '<>';

// Separators
LPAREN: '(';
RPAREN: ')';
LBRACK: '[';
RBRACK: ']';
COLON: ':';
COMMA: ',';
SEMI: ';';
DOT: '.';
DOTDOT: '..';

// Literals
INTEGER_CONST: DIGIT+;
REAL_CONST: DIGIT+ '.' DIGIT* | '.' DIGIT+;
STRING_LITERAL: '\'' ( ~('\''|'\n'|'\r') | '\'\'' )* '\'';

// Identifier
IDENTIFIER: LETTER (LETTER | DIGIT | '_')*;

// Skipped
WHITESPACE: [ \t\r\n]+ -> skip;
COMMENT: '{' .*? '}' -> skip;
COMMENT_LINE: '//' ~[\r\n]* -> skip;
COMMENT_PARENTHESIS: '(*' .*? '*)' -> skip;

// Fragments
fragment LETTER: [a-zA-Z];
fragment DIGIT: [0-9];