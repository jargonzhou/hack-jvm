# The Definitive ANTLR 4 Reference

# 1 Meet ANTLR
## 1.1 Installing ANTLR

- UNIX

```shell
$ export CLASSPATH=".:/usr/local/lib/antlr-4.0-complete.jar:$CLASSPATH"
$ alias antlr4='java -jar /usr/local/lib/antlr-4.0-complete.jar'
$ alias grun='java org.antlr.v4.runtime.misc.TestRig'
```

```antlr title="Hello.g4"
grammar Hello; // Define a grammar called Hello
r : 'hello' ID ; // match keyword hello followed by an identifier
ID : [a-z]+ ; // match lower-case identifiers
WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines, \r (Windows)
```

```shell
# Generate parser and lexer
$ antlr4 Hello.g4

# Compile ANTLR-generated code
$ javac *.java 

# # start the TestRig on grammar Hello at rule r
$ grun Hello r -tokens 
➾ hello parrt     # input for the recognizer that you type
➾EOF              # type ctrl-D on Unix or Ctrl+Z on Windows

$ grun Hello r -tree
➾ hello parrt
➾EOF
```

- Windows

```shell
# install antlr4.exe antlr4-parse.exe
$ pip install antlr4-tools
```

```shell
# Add D:\bin\grun.bat
SET CLASSPATH=.;D:\bin\antlr-4.11.1-complete.jar;%CLASSPATH%

@ECHO OFF
SET TEST_CURRENT_DIR=%CLASSPATH:.;=%
if "%TEST_CURRENT_DIR%" == "%CLASSPATH%" ( SET CLASSPATH=.;%CLASSPATH% )
@ECHO ON
java org.antlr.v4.gui.TestRig %*
```

```shell
# compile
$ javac -cp "/d/bin/antlr-4.11.1-complete.jar:$CLASSPATH" DynScope*.java
# run with test inputs
$ grun.bat DynScope prog DynScope.txt
symbols=[i, x]
undefined variable: x
symbols=[i, j]
```

- antrl4, grun, antlr-parse

```shell
$ antlr4
ANTLR Parser Generator  Version 4.11.1
 -o ___              specify output directory where all output is generated
 -lib ___            specify location of grammars, tokens files
 -atn                generate rule augmented transition network diagrams
 -encoding ___       specify grammar file encoding; e.g., euc-jp
 -message-format ___ specify output style for messages in antlr, gnu, vs2005
 -long-messages      show exception details when available for errors and warnings
 -listener           generate parse tree listener (default)
 -no-listener        don't generate parse tree listener
 -visitor            generate parse tree visitor
 -no-visitor         don't generate parse tree visitor (default)
 -package ___        specify a package/namespace for the generated code
 -depend             generate file dependencies
 -D<option>=value    set/override a grammar-level option
 -Werror             treat warnings as errors
 -XdbgST             launch StringTemplate visualizer on generated code
 -XdbgSTWait         wait for STViz to close before continuing
 -Xforce-atn         use the ATN simulator for all predictions
 -Xlog               dump lots of logging info to antlr-timestamp.log
 -Xexact-output-dir  all output goes into -o dir regardless of paths/package
```

```shell
$ grun
java org.antlr.v4.gui.TestRig
java org.antlr.v4.gui.TestRig GrammarName startRuleName
  [-tokens] [-tree] [-gui] [-ps file.ps] [-encoding encodingname]
  [-trace] [-diagnostics] [-SLL]
  [input-filename(s)]
Use startRuleName='tokens' if GrammarName is a lexer grammar.
Omitting input-filename makes rig read from stdin.
```

```shell
$ antlr4-parse
java org.antlr.v4.guIntrepreter [X.g4|XParser.g4 XLexer.g4] startRuleName
  [-tokens] [-tree] [-gui] [-encoding encodingname]
  [-trace] [-profile filename.csv] [input-filename(s)]
Omitting input-filename makes rig read from stdin.
```

## 1.2 Executing ANTLR and Testing Recognizers

# 2 The Big Picture
## 2.1 Let’s Get Meta!
## 2.2 Implementing Parsers
## 2.3 You Can’t Put Too Much Water into a Nuclear Reactor
## 2.4 Building Language Applications Using Parse Trees
## 2.5 Parse-Tree Listeners and Visitors

# 3 A Starter ANTLR Project
## 3.1 The ANTLR Tool, Runtime, and Generated Code

> [!example] "ArrayInit.g4"

```antlr
/** Grammars always start with a grammar header. This grammar is called
* ArrayInit and must match the filename: ArrayInit.g4
*/
grammar ArrayInit;

/** A rule called init that matches comma-separated values between {...}. */
init : '{' value (',' value)* '}' ; // must match at least one value

/** A value can be either a nested array/struct or a simple integer (INT) */
value : init
  | INT
  ;

// parser rules start with lowercase letters, lexer rules with uppercase
INT : [0-9]+ ;            // Define token INT as one or more digits
WS : [ \t\r\n]+ -> skip ; // Define whitespace rule, toss it out
```

> [!example] "Generate lexer and parser for ArrayInit.g4"

```shell
$ antlr4 -visitor ArrayInit.g4
$ ls
ArrayInit.g4
ArrayInit.interp
ArrayInit.tokens                // contain token type numbers
ArrayInitBaseListener.java      // listener class
ArrayInitBaseVisitor.java       // visitor class
ArrayInitLexer.interp
ArrayInitLexer.java             // the lexer class
ArrayInitLexer.tokens
ArrayInitListener.java
ArrayInitParser.java            // the parser class
ArrayInitVisitor.java
```

## 3.2 Testing the Generated Parser

> [!example] "Test generated parser for ArrayInit.g4"

```shell
$ javac *.java

$ grun ArrayInit init -tokens
java org.antlr.v4.gui.TestRig ArrayInit init -tokens
{99, 3, 451}
^Z
[@0,0:0='{',<'{'>,1:0]
[@1,1:2='99',<INT>,1:1]
[@2,3:3=',',<','>,1:3]
[@3,5:5='3',<INT>,1:5]
[@4,6:6=',',<','>,1:6]
[@5,8:10='451',<INT>,1:8]
[@6,11:11='}',<'}'>,1:11]
[@7,14:13='<EOF>',<EOF>,2:0]

$ grun ArrayInit init -tree
java org.antlr.v4.gui.TestRig ArrayInit init -tree
{99, 3, 451}
^Z
(init { (value 99) , (value 3) , (value 451) })

$ grun ArrayInit init -gui
java org.antlr.v4.gui.TestRig ArrayInit init -gui
{1, {2,3}, 4}
^Z
```

## 3.3 Integrating a Generated Parser into a Java Program

> [!example] "Using generated code for ArrayInit.g4"

```java
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Test {
  public static void main(String[] args) throws Exception {
    // create a CharStream that reads from standard input
    ANTLRInputStream input = new ANTLRInputStream(System.in);
    // create a lexer that feeds off of input CharStream
    ArrayInitLexer lexer = new ArrayInitLexer(input);
    // create a buffer of tokens pulled from the lexer
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    // create a parser that feeds off the tokens buffer
    ArrayInitParser parser = new ArrayInitParser(tokens);
    ParseTree tree = parser.init(); // begin parsing at init rule
    System.out.println(tree.toStringTree(parser)); // print LISP-style tree
  }
}
```

```shell
$ javac *.java
$ java Test
{1, {2,3}, 4}
^Z
(init { (value 1) , (value (init { (value 2) , (value 3) })) , (value 4) })

$ java Test
{1,2
^Z
line 2:0 extraneous input '<EOF>' expecting {',', '}'}
(init { (value 1) , (value 2) <missing '}'>)
```

## 3.4 Building a Language Application

> [!example] "Translate"
"X goes to Y" rules:
- translate `{` to `"`,
- translate `}` to `"`,
- translate integers to four-digit hexadecimal strings prefixed with `\u`.

example: `{99, 3, 451}` to `"\u0063 \u0003 \u01c3"`.

```java title="ShortToUnicodeString.java"
public class ShortToUnicodeString extends ArrayInitBaseListener {
  /** Translate { to " */
  @Override
  public void enterInit(ArrayInitParser.InitContext ctx) {
    System.out.print('"');
  }

  /** Translate } to " */
  @Override
  public void exitInit(ArrayInitParser.InitContext ctx) {
    System.out.print('"');
  }
  
  /** Translate integers to 4-digit hexadecimal strings prefixed with \\u */
  @Override
  public void enterValue(ArrayInitParser.ValueContext ctx) {
    // Assumes no nested array initializers
    int value = Integer.valueOf(ctx.INT().getText());
    System.out.printf("\\u%04x", value);
  }
}
```

```java title="Translate.java"
// import ANTLR's runtime libraries
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Translate {
  public static void main(String[] args) throws Exception {
    // create a CharStream that reads from standard input
    ANTLRInputStream input = new ANTLRInputStream(System.in);
    // create a lexer that feeds off of input CharStream
    ArrayInitLexer lexer = new ArrayInitLexer(input);
    // create a buffer of tokens pulled from the lexer
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    // create a parser that feeds off the tokens buffer
    ArrayInitParser parser = new ArrayInitParser(tokens);
    ParseTree tree = parser.init(); // begin parsing at init rule
    // Create a generic parse tree walker that can trigger callbacks
    ParseTreeWalker walker = new ParseTreeWalker();
    // Walk the tree created during the parse, trigger callbacks
    walker.walk(new ShortToUnicodeString(), tree);
    System.out.println(); // print a \n after translation
  }
}
```

```shell
$ javac *.java
$ java Translate
{99, 3, 451}
^Z
"\u0063\u0003\u01c3"
```

# 4 A Quick Tour
## 4.1 Matching an Arithmetic Expression Language
## 4.2 Building a Calculator Using a Visitor
## 4.3 Building a Translator with a Listener
## 4.4 Making Things Happen During the Parse
## 4.5 Cool Lexical Features

# 5 Designing Grammars
## 5.1 Deriving Grammars from Language Samples
## 5.2 Using Existing Grammars as a Guide
## 5.3 Recognizing Common Language Patterns with ANTLR Grammars
## 5.4 Dealing with Precedence, Left Recursion, and Associativity
## 5.5 Recognizing Common Lexical Structures
## 5.6 Drawing the Line Between Lexer and Parser

# 6 Exploring Some Real Grammars
## 6.1 Parsing Comma-Separated Values
## 6.2 Parsing JSON
## 6.3 Parsing DOT
## 6.4 Parsing Cymbol
## 6.5 Parsing R

# 7 Decoupling Grammars from Application-Specific Code
## 7.1 Evolving from Embedded Actions to Listeners
## 7.2 Implementing Applications with Parse-Tree Listeners
## 7.3 Implementing Applications with Visitors
## 7.4 Labeling Rule Alternatives for Precise Event Methods
## 7.5 Sharing Information Among Event Methods

# 8 Building Some Real Language Applications
## 8.1 Loading CSV Data
## 8.2 Translating JSON to XML
## 8.3 Generating a Call Graph
## 8.4 Validating Program Symbol Usage

# 9 Error Reporting and Recovery
## 9.1 A Parade of Errors
## 9.2 Altering and Redirecting ANTLR Error Messages
## 9.3 Automatic Error Recovery Strategy
## 9.4 Error Alternatives
## 9.5 Altering ANTLR’s Error Handling Strategy

# 10 Attributes and Actions
## 10.1 Building a Calculator with Grammar Actions
## 10.2 Accessing Token and Rule Attributes
## 10.3 Recognizing Languages Whose Keywords Aren’t Fixed

# 11 Altering the Parse with Semantic Predicates
## 11.1 Recognizing Multiple Language Dialects
## 11.2 Deactivating Tokens
## 11.3 Recognizing Ambiguous Phrases

# 12 Wielding Lexical Black Magic
## 12.1 Broadcasting Tokens on Different Channels
## 12.2 Context-Sensitive Lexical Problems
## 12.3 Islands in the Stream
## 12.4 Parsing and Lexing XML

# 13 Exploring the Runtime API
## 13.1 Library Package Overview

- [ANTLR 4 Runtime API](https://www.antlr.org/api/Java/index.html)

> [!note] "`org.antlr.v4.runtime`"
```java
// Interface
ANTLRErrorListener	
    // How to emit recognition errors.
ANTLRErrorStrategy	
    // The interface for defining strategies to deal with syntax errors encountered during a parse by ANTLR-generated parsers.
CharStream	
    // A source of characters for an ANTLR lexer.
IntStream	
    // A simple stream of symbols whose values are represented as integers.
Token	
    // A token has properties: text, type, line, character position in the line (so we can ignore tabs), token channel, index, and source from which we obtained this token.
TokenFactory<Symbol extends Token>	
    // The default mechanism for creating tokens.
TokenSource	
    // A source of tokens must provide a sequence of tokens via TokenSource.nextToken() and also must reveal it's source of characters; CommonToken's text is computed from a CharStream; it only store indices into the char stream.
TokenStream	
    // An IntStream whose symbols are Token instances.
Vocabulary	
    // This interface provides information about the vocabulary used by a recognizer.
WritableToken

// Class
ANTLRFileStream
    // Deprecated. as of 4.7 Please use CharStreams interface.
ANTLRInputStream
    // Deprecated. as of 4.7 Please use CharStreams interface.
BailErrorStrategy	
    // This implementation of ANTLRErrorStrategy responds to syntax errors by immediately canceling the parse operation with a ParseCancellationException.
BaseErrorListener	
    // Provides an empty default implementation of ANTLRErrorListener.
BufferedTokenStream	
    // This implementation of TokenStream loads tokens from a TokenSource on-demand, and places the tokens in a buffer to provide access to any previous token by index.
CharStreams	
    // This class represents the primary interface for creating CharStreams from a variety of sources as of 4.7.
CodePointBuffer	
    // Wrapper for ByteBuffer / CharBuffer / IntBuffer.
CodePointBuffer.Builder	 
CodePointCharStream	
    // Alternative to ANTLRInputStream which treats the input as a series of Unicode code points, instead of a series of UTF-16 code units.
CommonToken	 
CommonTokenFactory	
    // This default implementation of TokenFactory creates CommonToken objects.
CommonTokenStream	
    // This class extends BufferedTokenStream with functionality to filter token streams to tokens on a particular channel (tokens where Token.getChannel() returns a particular value).
ConsoleErrorListener	 
DefaultErrorStrategy	
    // This is the default implementation of ANTLRErrorStrategy used for error reporting and recovery in ANTLR parsers.
DiagnosticErrorListener	
    // This implementation of ANTLRErrorListener can be used to identify certain potential correctness and performance problems in grammars.
InterpreterRuleContext	
    // This class extends ParserRuleContext by allowing the value of InterpreterRuleContext.getRuleIndex() to be explicitly set for the context.
Lexer	
    // A lexer is recognizer that draws input symbols from a character stream.
LexerInterpreter	 
ListTokenSource	
    // Provides an implementation of TokenSource as a wrapper around a list of Token objects.
Parser	
    // This is all the parsing support code essentially; most of it is error recovery stuff.
Parser.TrimToSizeListener	 
ParserInterpreter	
    // A parser simulator that mimics what ANTLR's generated parser code does.
ParserRuleContext	
    // A rule invocation record for parsing.
ProxyErrorListener	
    // This implementation of ANTLRErrorListener dispatches all calls to a collection of delegate listeners.
Recognizer<Symbol,​ATNInterpreter extends ATNSimulator>	 
RuleContext	
    // A rule context is a record of a single rule invocation.
RuleContextWithAltNum	
    // A handy class for use with options {contextSuperClass=org.antlr.v4.runtime.RuleContextWithAltNum;} that provides a backing field / impl for the outer alternative number matched for an internal parse tree node.
RuntimeMetaData	
    // This class provides access to the current version of the ANTLR 4 runtime library as compile-time and runtime constants, along with methods for checking for matching version numbers and notifying listeners in the case where a version mismatch is detected.
TokenStreamRewriter	
    // Useful for rewriting out a buffered input token stream after doing some augmentation or other manipulations on it.
UnbufferedCharStream	
    // Do not buffer up the entire char stream.
UnbufferedTokenStream<T extends Token>	 
VocabularyImpl	
    // This class provides a default implementation of the Vocabulary interface.

// Exception
FailedPredicateException	
    // A semantic predicate failed during validation.
InputMismatchException	
    // This signifies any kind of mismatched input exceptions such as when the current input does not match the expected token.
LexerNoViableAltException	 
NoViableAltException	
    // Indicates that the parser could not decide which of two or more paths to take based upon the remaining input.
RecognitionException	
    // The root of the ANTLR exception hierarchy.

// Enum
CodePointBuffer.Type
```

> [!note] "`org.antlr.v4.runtime.atn`"

It is used internally for ANTLR's Adaptive LL(\*) lexing and parsing strategy.

**atn**: augumented transition network, is a state machine taht can represent a grammar where edges represent grammar elements.
ANTLR walks the ATN during lexing and parsing to make predictions based upon lookahead symbols.

```java
// Interface
LexerAction	
    // Represents a single action which can be executed following the successful match of a lexer rule.

// Class
AbstractPredicateTransition	 
ActionTransition	 
AmbiguityInfo	
    // This class represents profiling event information for an ambiguity.
ArrayPredictionContext	 
ATN	 
ATNConfig	
    // A tuple: (ATN state, predicted alt, syntactic, semantic context).
ATNConfigSet	
    // Specialized Set<ATNConfig> that can track info about the set, with support for combining similar configurations using a graph-structured stack.
ATNConfigSet.AbstractConfigHashSet	 
ATNConfigSet.ConfigEqualityComparator	 
ATNConfigSet.ConfigHashSet	
    // The reason that we need this is because we don't want the hash map to use the standard hash code and equals.
ATNDeserializationOptions	 
ATNDeserializer	
    // Deserialize ATNs for JavaTarget; it's complicated by the fact that java requires that we serialize the list of integers as 16 bit characters in a string.
ATNSerializer	
    // This class represents a target neutral serializer for ATNs.
ATNSimulator	 
ATNState	
    // The following images show the relation of states and ATNState.transitions for various grammar constructs.
AtomTransition	
    // TODO: make all transitions sets? no, should remove set edges
BasicBlockStartState	 
BasicState	 
BlockEndState	
    // Terminal node of a simple (a|b|c) block.
BlockStartState	
    // The start of a regular (...) block.
CodePointTransitions	
    // Utility class to create AtomTransition, RangeTransition, and SetTransition appropriately based on the range of the input.
ContextSensitivityInfo	
    // This class represents profiling event information for a context sensitivity.
DecisionEventInfo	
    // This is the base class for gathering detailed information about prediction events which occur during parsing.
DecisionInfo	
    // This class contains profiling gathered for a particular decision.
DecisionState	 
EmptyPredictionContext	 
EpsilonTransition	 
ErrorInfo	
    // This class represents profiling event information for a syntax error identified during prediction.
LexerActionExecutor	
    // Represents an executor for a sequence of lexer actions which traversed during the matching operation of a lexer rule (token).
LexerATNConfig	 
LexerATNSimulator	
    // "dup" of ParserInterpreter
LexerATNSimulator.SimState	
    // When we hit an accept state in either the DFA or the ATN, we have to notify the character stream to start buffering characters via IntStream.mark() and record the current state.
LexerChannelAction	
    // Implements the channel lexer action by calling Lexer.setChannel(int) with the assigned channel.
LexerCustomAction	
    // Executes a custom lexer action by calling Recognizer.action(org.antlr.v4.runtime.RuleContext, int, int) with the rule and action indexes assigned to the custom action.
LexerIndexedCustomAction	
    // This implementation of LexerAction is used for tracking input offsets for position-dependent actions within a LexerActionExecutor.
LexerModeAction	
    // Implements the mode lexer action by calling Lexer.mode(int) with the assigned mode.
LexerMoreAction	
    // Implements the more lexer action by calling Lexer.more().
LexerPopModeAction	
    // Implements the popMode lexer action by calling Lexer.popMode().
LexerPushModeAction	
    // Implements the pushMode lexer action by calling Lexer.pushMode(int) with the assigned mode.
LexerSkipAction	
    // Implements the skip lexer action by calling Lexer.skip().
LexerTypeAction	
    // Implements the type lexer action by calling Lexer.setType(int) with the assigned type.
LL1Analyzer	 
LookaheadEventInfo	
    // This class represents profiling event information for tracking the lookahead depth required in order to make a prediction.
LoopEndState	
    // Mark the end of a * or + loop.
NotSetTransition	 
OrderedATNConfigSet	 
OrderedATNConfigSet.LexerConfigHashSet	 
ParseInfo	
    // This class provides access to specific and aggregate statistics gathered during profiling of a parser.
ParserATNSimulator	
    // The embodiment of the adaptive LL(*), ALL(*), parsing strategy.
PlusBlockStartState	
    // Start of (A|B|...)+ loop.
PlusLoopbackState	
    // Decision state for A+ and (A|B)+.
PrecedencePredicateTransition	 
PredicateEvalInfo	
    // This class represents profiling event information for semantic predicate evaluations which occur during prediction.
PredicateTransition	
    // TODO: this is old comment: A tree of semantic predicates from the grammar AST if label==SEMPRED.
PredictionContext	 
PredictionContextCache	
    // Used to cache PredictionContext objects.
ProfilingATNSimulator	 
RangeTransition	 
RuleStartState	 
RuleStopState	
    // The last node in the ATN for a rule, unless that rule is the start symbol.
RuleTransition	 
SemanticContext	
    // A tree structure used to record the semantic context in which an ATN configuration is valid.
SemanticContext.AND	
    // A semantic context which is true whenever none of the contained contexts is false.
SemanticContext.Empty	 
SemanticContext.Operator	
    // This is the base class for semantic context "operators", which operate on a collection of semantic context "operands".
SemanticContext.OR	
    // A semantic context which is true whenever at least one of the contained contexts is true.
SemanticContext.PrecedencePredicate	 
SemanticContext.Predicate	 
SetTransition	
    // A transition containing a set of values.
SingletonPredictionContext	 
StarBlockStartState	
    // The block that begins a closure loop.
StarLoopbackState	 
StarLoopEntryState	 
TokensStartState	
    // The Tokens rule start state linking to each lexer rule start state
Transition	
    // An ATN transition between any two ATN states.
WildcardTransition	 

// Enum
ATNType	
    // Represents the type of recognizer an ATN applies to.
LexerActionType	
    // Represents the serialization type of a LexerAction.
PredictionMode	
    // This enumeration defines the prediction modes available in ANTLR 4 along with utility methods for analyzing configuration sets for conflicts and/or ambiguities.
```

> [!note] "`org.antlr.v4.runtime.dfa`"
Using the ATN to make predictions is expensive, so the runtime caches prediction results in DFA(deterministic finite automate).
```java
DFA	 
DFASerializer	
    // A DFA walker that knows how to dump them to serialized strings.
DFAState	
    // A DFA state represents a set of possible ATN configurations.
DFAState.PredPrediction	
    // Map a predicate to a predicted alternative.
LexerDFASerializer	 
```

> [!note] "`org.antlr.v4.runtime.misc`"
```java
// Interface
EqualityComparator<T>	
    // This interface provides an abstract concept of object equality independent of Object.equals(java.lang.Object) (object equality) and the == operator (reference equality).
IntSet	
    // A generic set of integers.
Predicate<T>

// Class
AbstractEqualityComparator<T>	
    // This abstract base class is provided so performance-critical applications can use virtual- instead of interface-dispatch when calling comparator methods.
Array2DHashSet<T>	
    // Set implementation with closed hashing (open addressing).
DoubleKeyMap<Key1,​Key2,​Value>	
    // Sometimes we need to map a key to a value but key is two pieces of data.
FlexibleHashMap<K,​V>	
    // A limited map (many unsupported operations) that lets me use varying hashCode/equals.
FlexibleHashMap.Entry<K,​V>	 
IntegerList	 
IntegerStack	 
InterpreterDataReader	 
InterpreterDataReader.InterpreterData	 
Interval	
    // An immutable inclusive interval a..b
IntervalSet	
    // This class implements the IntSet backed by a sorted array of non-overlapping intervals.
LogManager	 
LogManager.Record	 
MultiMap<K,​V>	 
MurmurHash	 
ObjectEqualityComparator	
    // This default implementation of EqualityComparator uses object equality for comparisons by calling Object.hashCode() and Object.equals(java.lang.Object).
OrderedHashSet<T>	
    // A HashMap that remembers the order that the elements were added.
Pair<A,​B>	 
TestRig
    // Deprecated. 
Triple<A,​B,​C>	 
Utils	 

// Exception
ParseCancellationException	
    // This exception is thrown to cancel a parsing operation.
```

> [!note] "`org.antlr.v4.runtime.tree`"
```java
// Interface
ErrorNode	 
ParseTreeListener
    // An interface to access the tree of RuleContext objects created during a parse that makes the data structure look like a simple parse tree.
ParseTreeListener
    // This interface describes the minimal core of methods triggered by ParseTreeWalker.
ParseTreeVisitor<T>
    // This interface defines the basic notion of a parse tree visitor.
RuleNode	 
SyntaxTree
    // A tree that knows about an interval in a token stream is some kind of syntax tree.
TerminalNode	 
Tree
    // The basic notion of a tree has a parent, a payload, and a list of children.

// Class
AbstractParseTreeVisitor<T>	 
ErrorNodeImpl
    // Represents a token that was consumed during resynchronization rather than during a valid match operation.
IterativeParseTreeWalker
    // An iterative (read: non-recursive) pre-order and post-order tree walker that doesn't use the thread stack but heap-based stacks.
ParseTreeProperty<V>
    // Associate a property with a parse tree node.
ParseTreeWalker	 
TerminalNodeImpl	 
Trees
    // A set of utility routines useful for all kinds of ANTLR trees.
```
 
> [!note] "`org.antlr.v4.runtime.tree.pattern`"
```java
// Class
ParseTreeMatch
    // Represents the result of matching a ParseTree against a tree pattern.
ParseTreePattern
    // A pattern like <ID> = <expr>; converted to a ParseTree by ParseTreePatternMatcher.compile(String, int).
ParseTreePatternMatcher
    // A tree pattern matching mechanism for ANTLR ParseTrees.
RuleTagToken
    // A Token object representing an entire subtree matched by a parser rule; e.g., <expr>.
TokenTagToken
    // A Token object representing a token of a particular type; e.g., <ID>.

// Exception
ParseTreePatternMatcher.CannotInvokeStartRule
ParseTreePatternMatcher.StartRuleDoesNotConsumeFullPattern
```

> [!note] "`org.antlr.v4.runtime.tree.xpath`"
```java
// Class
XPath
    // Represent a subset of XPath XML path syntax for use in identifying nodes in parse trees.
XPathElement
XPathLexer
    // Mimic the old XPathLexer from .g4 file
XPathLexerErrorListener	 
XPathRuleAnywhereElement
    // Either ID at start of path or ...//ID in middle of path.
XPathRuleElement
XPathTokenAnywhereElement
XPathTokenElement
XPathWildcardAnywhereElement
XPathWildcardElement
```

## 13.2 Recognizers
## 13.3 Input Streams of Characters and Tokens
## 13.4 Tokens and Token Factories
## 13.5 Parse Trees
## 13.6 Error Listeners and Strategies
## 13.7 Maximizing Parser Speed
## 13.8 Unbuffered Character and Token Streams
## 13.9 Altering ANTLR’s Code Generation

# 14 Removing Direct Left Recursion
## 14.1 Direct Left-Recursive Alternative Patterns
## 14.2 Left-Recursive Rule Transformations

# 15 Grammar Reference
## 15.1 Grammar Lexicon

> [!note] "Comments"
```antlr
/** This grammar is an example illustrating the three kinds
* of comments.
*/
grammar T;

/* a multi-line
comment
*/

/** This rule matches a declarator for my language */
decl : ID ; // match a variable name
```

> [!note] "Identifiers"
Token names start with a capital letter, and so do lexer rules.
>
Parser rule names start with a lowercase letter.
>
ANTLR accepts Unicode characters in ANTLR names.
```antlr
ID : a=NameStartChar NameChar*
    {
        if ( Character.isUpperCase(getText().charAt(0)) ) setType(TOKEN_REF);
        else setType(RULE_REF);
    }
    ;

fragment
NameChar
    : NameStartChar
    | '0'..'9'
    | '_'
    | '\u00B7'
    | '\u0300'..'\u036F'
    | '\u203F'..'\u2040'
    ;

fragment
NameStartChar
    : 'A'..'Z' | 'a'..'z'
    | '\u00C0'..'\u00D6'
    | '\u00D8'..'\u00F6'
    | '\u00F8'..'\u02FF'
    | '\u0370'..'\u037D'
    | '\u037F'..'\u1FFF'
    | '\u200C'..'\u200D'
    | '\u2070'..'\u218F'
    | '\u2C00'..'\u2FEF'
    | '\u3001'..'\uD7FF'
    | '\uF900'..'\uFDCF'
    | '\uFDF0'..'\uFFFD'
    ;
```

> [!note] "Literals"
>All literal strings that are one or more characters in length are enclosed in single quotes, such as `';'`, `'if'`, `'\''`.
Literals never contain regular expressions.
>
Literals can contain Unicode escape sequences of the form `\uXXXX`. We can use Unicode characters directly within literals or use the Unicode escape sequences.
ANTLR understand the usual special escape sequences: `'\n'`, `'\r'`, `'\b'`, `'\f'`.
```antlr
grammar Foreign;
a : '外' ;
```

> [!note] "Actions"
>Actions are code block written in the target language: arbitrary text surrounded by curly braces `{}`. The action text should conform to the target language as specified with the `language` option.

Embedded code can appear in:

- `@header` and `@members` named actions,
- parser and lexer rules,
- exception catching specifications,
- attribute sections for parser rules: return values, arguments, locals,
- some rules element options: currently predicates.

The only interpretation ANTLR does inside actions relates to grammar attributes.
Actions embedded within lexer rules are emitted without any interpretation or translation into generated lexers.

> [!note] "KeyWords"
a list of the reserved words in ANTLR grammars:
- `import`
- `fragment`
- `lexer`
- `parser`
- `grammar`
- `returns`
- `locals`
- `throws`
- `catch`
- `finally`
- `mode`
- `options`
- `tokens`.

Do not use the word `rule` as a rule or alternative label name.
Do not use any keyword of the target language as a token, label, or rule name.

## 15.2 Grammar Structure

> [!note] "Grammar structure"
ANTLR grammar has the general form:
```antlr
/** Optional Javadoc-style comment */
grammar Name;
options {...}   // optional
import ... ;    // optional
tokens {...}    // optional
@actionName {...}

«rule1» // parser and lexer rules, possibly intermingled
// ...
«ruleN»
```

Rules take the basic form:

```antlr
ruleName : «alternative1» | ... | «alternativeN» ;
```

Grammars defined without a prefix on the `grammar` header are combined grammars that can contain both lexical and parser rules.

A pure parser grammer:    

```antlr
parser grammar Name;
// ...
```

A pure lexer grammar:

```antlr
lexer grammar Name;
// ...
```

Only lexer grammars can contain `mode` specifications.

> [!note] "Grammar imports"
>
A grammar inherits all of the rules, `tokens` specifications, and named actions from the imported grammar.
Rules in the *main grammar* override rules from imported grammars to implement inheritance.

```antlr
grammar ELang;

stat : (expr ';')+ ;
expr : INT ;
WS : [ \r\t\n]+ -> skip ;
ID : [a-z]+ ;
```

```antlr
grammar MyELang;

import ELang;

expr : INT | ID ;
INT : [0-9]+ ;
```

- `import ELang;`: grammar `MyELang` imports `ELang`.
- `MyElang` inherits rules `stat`, `WS`, `ID`, but it overrides rule `expr` and add `INT`.

The *main grammar* merges the `tokens` specifications, any named actions (such as `@member`).
ANTLR ignores any options in imported grammars.

ANTLR pursues all imported grammars in a depth-first fashion, if two or more imported grammars define rule `r`, ANTLR chooses the first version of `r` it finds.

```antlr
grammar G3;
r : B ;

grammar G1;
import G3;
t : A ;

grammar G2;
r : C;

grammar Nested;
import G1, G2;
s : r ;
```

the final `Nested` grammar:

```antlr
grammar Nested;
s : r ; // from Nested
r : B ; // from G3
t : A ; // from G1
```

Lexer grammars can import lexer grammars.
Parser grammars can import parser grammars.
Combined grammars can import lexer of parser grammars.

ANTLR adds imported rules to the end of the rule list in a main lexter grammar.

> [!note] "`tokens` Section"
>
`tokens` section defines token types needed by a grammar for which there is no associated lexcial rule.

```antlr
tokens { «Token1», ..., «TokenN» }
```

```antlr
// explicitly define keyword token types to avoid implicit definition warnings
tokens { BEGIN, END, IF, THEN, WHILE }
@lexer::members { // keywords map used in lexer to assign token types
Map<String,Integer> keywords = new HashMap<String,Integer>() {{
    put("begin", KeywordsParser.BEGIN);
    put("end", KeywordsParser.END);
    ...
}};
}
```

> [!note] "Actions at the Grammar Level"
>
Currently there are 2 defined actions for Java target:
>
- `header`: inject code into the generated recognizer class file, before the recognizer class definition.
- `members`: inject codes into the recognizer class definition, as fields and methods.

For combined grammars, to restrict an action to the generated parser or lexer, user `@parser::name` or `@lexter::name`.

```antlr
grammar Count;

@header {
package foo;
}

@members {
int count = 0;
}

list
@after {System.out.println(count+" ints");}
    : INT {count++;} (',' INT {count++;} )*
    ;

INT : [0-9]+ ;
WS : [ \r\t\n]+ -> skip ;
```

## 15.3 Parser Rules

> [!example] "Parser Rules"
```antlr
/** Javadoc comment can precede rule */
retstat : 'return' expr ';' ;

stat: retstat
    | 'break' ';'
    | 'continue' ';'
    ;

superClass
    : 'extends' ID
    |                 // empty means other alternative(s) are optional
    ;
```

TODO(zhoujiagen) restarted here.

> [!note] "Alternative Labels"
>
We can get more precise parse-tree context by labeling the outermost alternatives of a rule using the `# operator.
All alternatives within a rule must be labeled, or none of them should be.

```antlr title="AltLabels.g4"
grammar AltLabels;
stat: 'return' e ';'  # Return
    | 'break' ';'     # Break
    ;
e : e '*' e           # Mult
    | e '+' e         # Add
    | INT             # Int
    ;
```
    
```java
public interface AltLabelsVisitor<T> extends ParseTreeVisitor<T> {
	T visitReturn(AltLabelsParser.ReturnContext ctx);
	T visitBreak(AltLabelsParser.BreakContext ctx);
	T visitAdd(AltLabelsParser.AddContext ctx);
	T visitMult(AltLabelsParser.MultContext ctx);
	T visitInt(AltLabelsParser.IntContext ctx);
}
```

```antlr
e : e '*' e           # BinaryOp
    | e '+' e         # BinaryOp
    | INT # Int
    ;
```

```java
public interface AltLabelsVisitor<T> extends ParseTreeVisitor<T> {
	T visitReturn(AltLabelsParser.ReturnContext ctx);
	T visitBreak(AltLabelsParser.BreakContext ctx);
	T visitInt(AltLabelsParser.IntContext ctx);
	T visitBinaryOp(AltLabelsParser.BinaryOpContext ctx);
}
```

```antlr title="Conflict.g4"
grammar Conflict;
stat: 'return' e ';'  # Return
    | 'break' ';'     # Break
    ;
e : e '*' e           # e
    | e '+' e         # Stat
    | INT             # Int
    ;
```

> [!note] "Rule Context Objects"

```antlr
inc : e '++' ;

field : e '.' e ;

s : field
    {
    List<EContext> x = $field.ctx.e();
    ...
    }
    ;
```

```java
public static class IncContext extends ParserRuleContext {
    public EContext e() { ... } // return context object associated with e
}

public static class FieldContext extends ParserRuleContext {
    public EContext e(int i) { ... } // get ith e context
    public List<EContext> e() { ... } // return ALL e contexts
}
```

> [!note] "Rule Element Labels"

```antlr
stat: 'return' value=e ';' # Return
    | 'break' ';' # Break
    ;

array : '{' el+=INT (',' el+=INT)* '}' ;

elist : exprs+=e (',' exprs+=e)* ;
```

```java
public static class ReturnContext extends StatContext {
    public EContext value;
}

public static class ArrayContext extends ParserRuleContext {
    public List<Token> el = new ArrayList<Token>();
}

public static class ElistContext extends ParserRuleContext {
    public List<EContext> exprs = new ArrayList<EContext>();
}
```

> [!note] "Rule Elements"

- `T`
- `'literal'`
- `r`
- `r[«args»]`
- `{«action»}`
- `{«p»}?`
- `.`

> [!note] "Subrules"

- `(x|y|z)`

```antlr
returnType : (type | 'void') ;
```

- `(x|y|z)?`

```antlr
classDeclaration
    : 'class' ID (typeParameters)? ('extends' type)?
        ('implements' typeList)?
        classBody
;
```

- `(x|y|z)*`

```antlr
annotationName : ID ('.' ID)* ;
```

- `(x|y|z)+`

```antlr
annotations : (annotation)+ ;
```

> [!note] "Catching Exceptions"

```java
void r() throws RecognitionException {
    try {
        «rule-body»
    }
    catch (RecognitionException re) {
        _errHandler.reportError(this, re);
        _errHandler.recover(this, re);
    }
    finally {
        exitRule();
    }
}
```

```antlr
r : ...
    ;
    catch[RecognitionException e] { throw e; }
```

```antlr
r : ...
    ;
    catch[FailedPredicateException fpe] { ... }
    catch[RecognitionException e] { ... }
```

```antlr
r : ...
    ;
    // catch blocks go first
    finally { System.out.println("exit rule r"); }
```

list of exceptions:

- `RecognitionException`
- `NoViableAltException`
- `LexerNoViableAltException`
- `InputMismatchException`
- `FailedPredicateException`

> [!note] "Rule Attribute Definitions"
>
Rules can have arguments, return values and local variables just like functions in a programming language. 
ANTLR collects all of the variables you define and stores them in the rule context object.
These varaibles are usually called **attributes**.    

```antlr
rulename[«args»] returns [«retvals»] locals [«localvars»] : ... ;
```

```antlr
// Return the argument plus the integer value of the INT token
add[int x] returns [int result] : '+=' INT {$result = $x + $INT.int;} ;
```

As with the grammar level, we can specify rule-level named actions: 

- `init`: execute `init` actions immediately before trying to match the associated rule.
- `after`: execute `after` actions immediately after matching the rule.

> [!note] "Start Rules and EOF"

```antlr
config : element*; // can "match" even with invalid input.
file : element* EOF; // don't stop early. must match all input
```

## 15.4 Actions and Attributes

> [!note] "Token Attributes"

`$T.attr`:

- `text`
- `type`
- `line`
- `pos`
- `index`
- `channel`
- `int`

> [!note] "Parser Rule Attributes"

`$r.attr`:

- `text`
- `start`
- `stop`
- `ctx`

> [!note] "Dynamically Scoped Attributes"

## 15.5 Lexer Rules

> [!note] "Lexer Rules"

```antlr
/** Optional document comment */
TokenName : «alternative1» | ... | «alternativeN» ;

fragment HelperTokenRule : «alternative1» | ... | «alternativeN» ;
```

```antlr
INT : DIGIT+ ; // references the DIGIT helper rule
fragment DIGIT : [0-9] ; // not a token by itself
```

> [!note] "Lexical Modes"

```antlr
«rules in default mode»
// ...
mode MODE1;
«rules in MODE1»
// ...
mode MODEN;
«rules in MODEN»
// ...
```

> [!note] "Lexer Rule Elements"

- `'literal'`
- `[char set]`
- `'x'..'y'`
- `T`
- `.`
- `{«action»}`
- `{«p»}?`
- `~x`

> [!note] "Recursive Lexer Rules"

```antlr
lexer grammar Recur;

ACTION : '{' ( ACTION | ~[{}] )* '}' ;
WS : [ \r\t\n]+ -> skip ;
```

> [!note] "Redundant String Literals"

```antlr
lexer grammar L;
AND : '&' ;
mode STR;
MASK : '&' ;
```

```antlr
parser grammar P;
options { tokenVocab=L; }
a : '&' // results in a tool error: no such token
    AND // no problem
    MASK // no problem
    ;
```

> [!note] "Lexer Rule Actions"

```antlr
ENUM : 'enum' {if (!enumIsKeyword) setType(Identifier);} ;
```

> [!note] "Lexer Commands"

```antlr
TokenName : «alternative» -> command-name
TokenName : «alternative» -> command-name(«identifier or integer»)
```

valid command names:

- `skip`

```antlr
WS : [ \r\t\n]+ -> skip ;
```
 
- `more`

```antlr
lexer grammar Strings;
LQUOTE : '"' -> more, mode(STR) ;
WS : [ \r\t\n]+ -> skip ;
mode STR;
STRING : '"' -> mode(DEFAULT_MODE) ; // token we want parser to see
TEXT : . -> more ; // collect more text for string
```

- `type(T)`

```antlr
lexer grammar SetType;
tokens { STRING }
DOUBLE : '"' .*? '"' -> type(STRING) ;
SINGLE : '\'' .*? '\'' -> type(STRING) ;
WS : [ \r\t\n]+ -> skip ;
```

- `channel(C)`

```antlr
@lexer::members { public static final int WHITESPACE = 1; }

WS : [ \t\n\r]+ -> channel(WHITESPACE) ;
```

- `mode(M)`
- `pushMode(M)`
- `popMode`


## 15.6 Wildcard Operator and Nongreedy Subrules

> [!note] "Nongreedy Lexer Subrules"

```antlr
COMMENT : '/*' .*? '*/' -> skip ; // .*? matches anything until the first */
```

```antlr
grammar Nongreedy;
s : STRING+ ;
STRING : '"' ( '\\"' | . )*? '"' ; // match "foo", "\"", "x\"\"y", ...
WS : [ \r\t\n]+ -> skip ;
```


> [!note] "Nongreedy Parser Subrules"

```antlr
grammar FuzzyJava;

/** Match anything in between constant rule matches */
file : .*? (constant .*?)+ ;

/** Faster alternate version (Gets an ANTLR tool warning about
* a subrule like .* in parser that you can ignore.)
*/
altfile : (constant | .)* ; // match a constant or any token, 0-or-more times

/** Match things like "public static final SIZE" followed by anything */
constant
    : 'public' 'static' 'final' 'int' Identifier
    {System.out.println("constant: "+$Identifier.text);}
    ;

Identifier : [a-zA-Z_$] [a-zA-Z_$0-9]* ; // simplified
OTHER : . -> skip ;
```

## 15.7 Semantic Predicates

> [!note] "Making Predicated Parsing Decisions"

> [!note] "Finding Visible Predicates"

> [!note] "Using Context-Dependent Predicates"

> [!note] "Predicates in Lexer Rules"

```antlr
ENUM: [a-z]+ {getText().equals("enum")}?
    {System.out.println("enum!");}
    ;
ID : [a-z]+ {System.out.println("ID "+getText());} ;
```

## 15.8 Options

> [!note] "general syntax of options"

```antlr
options { name1=value1; ... nameN=valueN; } // ANTLR not target language syntax
```

> [!note] "Grammar Options"

`-D` overrides options within the grammar:

- `superClass`
- `language`
- `tokenVocab`
- `TokenLabelType`

> [!note] "Rule Options"

```antlr
rulename
options {...}
    : ...
    ;
```

> [!note] "Rule Element Options"

Token options have the form `T<name=value`>, the only token option is `assoc`, it accepts values `left` and `right`.

Semantic predicates also accept and option, the only valid option is `tail`, which takes either a string literal in `"` or an action that evaluates to a string.

```antlr
grammar ExprLR;

expr : expr '^'<assoc=right> expr
    | expr '*' expr // match subexpressions joined with '*' operator
    | expr '+' expr // match subexpressions joined with '+' operator
    | INT // matches simple integer atom
    ;

INT : '0'..'9'+ ;
WS : [ \n]+ -> skip ;
```

```antlr
ints[int max]
locals [int i=1]
    : INT ( ',' {$i++;} {$i<=$max}?<fail={"exceeded max "+$max}> INT )*
    ;
```

```antlr
{...}?<fail={doSomethingAndReturnAString()}>
```

## 15.9 ANTLR Tool Command-Line Options

> [!note] "antlr4"

```shell
 $ antlr4
ANTLR Parser Generator  Version 4.11.1
 -o ___              specify output directory where all output is generated
 -lib ___            specify location of grammars, tokens files
 -atn                generate rule augmented transition network diagrams
 -encoding ___       specify grammar file encoding; e.g., euc-jp
 -message-format ___ specify output style for messages in antlr, gnu, vs2005
 -long-messages      show exception details when available for errors and warnings
 -listener           generate parse tree listener (default)
 -no-listener        don't generate parse tree listener
 -visitor            generate parse tree visitor
 -no-visitor         don't generate parse tree visitor (default)
 -package ___        specify a package/namespace for the generated code
 -depend             generate file dependencies
 -D<option>=value    set/override a grammar-level option
 -Werror             treat warnings as errors
 -XdbgST             launch StringTemplate visualizer on generated code
 -XdbgSTWait         wait for STViz to close before continuing
 -Xforce-atn         use the ATN simulator for all predictions
 -Xlog               dump lots of logging info to antlr-timestamp.log
 -Xexact-output-dir  all output goes into -o dir regardless of paths/package
```
