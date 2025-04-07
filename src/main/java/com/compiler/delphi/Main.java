package com.compiler.delphi;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Main class for the Delphi interpreter.
 * Handles file reading, parsing, and interpretation.
 */
public class Main {
    
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Error: Please provide a Delphi source file path");
            System.exit(1);
        }

        try {
            // Read the input file
            String input = Files.readString(Paths.get(args[0]));
            System.out.println("Interpreting file: " + args[0]);
            
            // Create a character stream from the input
            CharStream charStream = CharStreams.fromString(input);
            
            // Create the lexer
            DelphiLexer lexer = new DelphiLexer(charStream);
            
            // Create the token stream
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            
            // Create the parser
            DelphiParser parser = new DelphiParser(tokens);
            
            // Enable error recovery to continue parsing after syntax errors
            parser.setErrorHandler(new DefaultErrorStrategy());
            
            // Register an error listener to print syntax errors
            parser.removeErrorListeners();
            parser.addErrorListener(new BaseErrorListener() {
                @Override
                public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, 
                                        int line, int charPositionInLine, String msg, 
                                        RecognitionException e) {
                    System.err.println("Syntax error at line " + line + ":" + charPositionInLine + " - " + msg);
                }
            });
            
            // Parse the program
            System.out.println("Parsing...");
            ParseTree tree = parser.program();
            
            // Check for syntax errors
            if (parser.getNumberOfSyntaxErrors() > 0) {
                System.err.println("Parsing completed with " + parser.getNumberOfSyntaxErrors() + " syntax errors.");
                System.exit(1);
            }
            
            System.out.println("Parsing completed successfully.");
            
            // Create and run the interpreter
            System.out.println("Starting interpretation...");
            DelphiInterpreter interpreter = new DelphiInterpreter();
            interpreter.visit(tree);
            
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Error during interpretation: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}