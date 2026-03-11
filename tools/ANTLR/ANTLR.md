# ANTLR
* https://www.antlr.org/
* https://github.com/antlr/antlr4
  * https://github.com/antlr/antlr4/blob/master/doc/index.md


> What is ANTLR?
>
> ANTLR (ANother Tool for Language Recognition) is a powerful parser generator for reading, processing, executing, or translating structured text or binary files. It's widely used to build languages, tools, and frameworks. From a grammar, ANTLR generates a parser that can build and walk parse trees.

books
* Parr, Terence. **The Definitive ANTLR 4 Reference**. 2012. The Pragmatic Programmers, LLC.
* Aho, Alfred V. / Lam, Monica S. / Sethi, Ravi / Ullman, Jeffrey D. **Compilers: Principles, Techniques, and Tools**. 2007, 2. edition . Pearson.

actions
* [ANTLR.ipynb](./ANTLR.ipynb)
* [antlr-examples](./antlr-examples/README.md)

# Terminology

* action: 动作
* `ALL(*)`: adaptive `LL(*)`
* attribute: 属性
* backtrack: 回溯
* error recovery: 错误恢复
* error report: 错误报告
* exception catching specification: 异常捕获描述
* fragment: 片段
* grammar: 文法
* label: 标签
* lexer: 词法器
    - character stream: 字符流
* LL(1): 从左向右扫描输入, 产生最左推导, 每步预看(lookahead)一个输入符号来做解析动作决策.
* LR(k): 从左向右扫描输入, 构造最右推导, 解析决策时预看k个输入符号.
* option: 选项
* parser: 解析器
* predicate: 谓词
* `SLL(*)`: strong `LL(*)`
* token: 记号
    - token stream: 记号流

# The Meta Grammar

## 文法词典(lexicon)

* 注释(comments): `/** ... */`, `/* ... */`, `// ...`
* 标识符(identifiers):
    - 记号和词法规则名称: 以大写字符开始
    - 解析规则名称: 以小写字符开始
    - 后接大小写字符, 数字和下划线`_`
* 字面量(literals): `if`, Unicode转义字符`'\uXXXX'`, 特殊转义字符`'\n'`, ...
* 动作(actions): `{ ... }`
    - 出现位置:
        - 命名的动作: `@header`, `@member`
        - 词法规则, 解析规则
        - 异常捕获描述
        - 解析规则的属性段: 返回值, 参数, 本地变量(locals)
        - 规则元素选项: 谓词
    - 解释(interpretation)
        - 与文法属性相关: 记号属性
        - 词法规则中的动作不做解释或翻译.
* 关键字(keywords)
    - `import`, `fragment`, `lexer`, `parser`, `grammar`, `returns`, `locals`, `throws`, `catch`, `finally`, `mode`, `options`, `tokens`
    - 不要使用`rule`作为规则名称, 不要使用目标语言中的关键字作为记号, 标签或规则的名称.

## 文法结构

[more](https://github.com/antlr/antlr4/blob/master/doc/grammars.md)

```
/** Optional javadoc style comment */
grammar Name; // 必需, 文件名Name.g4.

// 均可选, 至多一个, 可以按任意顺序出现.
options {...}
import ... ; 	// 类似于面向对象继承, 对于同一规则主文法会覆盖导入的文档.
tokens {...} // 记号段: 定义没有关联词法规则的记号


channels {...} // lexer only

// 命名的动作
// header: 插入在识别器类定义之前的代码, 包括包声明
// members: 插入在识别器类定义中的代码, 包括字段和方法
// 限制: @lexer::name, @parser::name
@actionName {...}

// 至少一个规则.
// 任意规则可充当起始规则, 起始规则不需要匹配所有输入. 使用预定义记号EOF匹配所有输入.
rule1 // parser and lexer rules, possibly intermingled
...
ruleName : alternative1 | ... | alternativeN ;
config : element*; // can "match" even with invalid input.
file : element* EOF; // don't stop early. must match all input
...
ruleN
```

拆分词法规则和解析规则:

```
lexer grammar Name;

// 可以有mode描述.

// 可以有通道描述.
channels {
  WHITESPACE_CHANNEL,
  COMMENTS_CHANNEL
}

WS : [ \r\t\n]+ -> channel(WHITESPACE_CHANNEL) ;
```

```
parser grammar Name;
```

## 词法规则

[more](https://github.com/antlr/antlr4/blob/master/doc/lexer-rules.md)

## 解析规则

[more](https://github.com/antlr/antlr4/blob/master/doc/parser-rules.md)

规则元素:

* `T`: 记号
* `'literal'`: 字面量
* `r`: 规则
* `r [<<args>>]`: 规则, 并传入参数
* `{<<action>>}`: 在前面的备选元素之后, 后面的备选元素之前执行的动作. 符合目标语言的语法. 会替换属性和记号引用, 例`$x`, `$x.y`. [more](https://github.com/antlr/antlr4/blob/master/doc/actions.md)
* `{<<p>>}?`: 语义谓词`<<p>>`求值, 结果为false时不再解析.
* `.`: 除EOF外的记号
* `~`: 非操作符, 例 `~INT`, `~','`, `~(INT|ID)`

```
// 注释
/** Javadoc comment can precede rule */ 
retstat : 'return' expr ';' ;

// 备选(alternative)
stat: retstat
 	| 'break' ';'
 	| 'continue' ';'
 	;
superClass
 	: 'extends' ID
 	| // empty means other alternative(s) are optional
 	;

// 备选标签(label)
stat: 'return' e ';' # Return
 	| 'break' ';' # Break
 	;

// 规则上下文对象
field : e '.' e ;
s : field
 	{
 	List<EContext> x = $field.ctx.e(); // 引用
 	...
 	}
;

// 规则元素标签: =, +=
stat: 'return' value=e ';' # Return
 	| 'break' ';' # Break
 	;
array : '{' el+=INT (',' el+=INT)* '}' ;
elist : exprs+=e (',' exprs+=e)* ; // 适用于规则引用
```

子规则:

```
// (x|y|z)
returnType : (type | 'void') ;

// (x|y|z)?
classDeclaration
    : 'class' ID (typeParameters)? ('extends' type)?
      ('implements' typeList)?
 	   classBody
    ;

// (x|y|z)*
annotationName : ID ('.' ID)* ;

// (x|y|z)+
annotations : (annotation)+ ;
```

捕获异常:

* `RecognitionException`: 识别器抛出异常的基类.
* `NoViableAltException`: 多个备选路径无法决定时抛出.
* `LexerNoViableAltException`: 词法中多个备选路径无法决定时抛出.
* `InputMismatchException`: 当前输入记号与预期不匹配时抛出.
* `FailedPredicateException`: 语义谓词求值为false时抛出.

```
r : ...
  ;
  catch[FailedPredicateException fpe] { ... }
  catch[RecognitionException e] { ... }
  // catch blocks go first
  finally { System.out.println("exit rule r"); }
```

规则属性定义:

```
// 参数, 返回值, 本地变量
rulename[args] returns [retvals] locals [localvars] : ... ;

// 例
// Return the argument plus the integer value of the INT token
add[int x] returns [int result] : '+=' INT {$result = $x + $INT.int;} ;
// 多个属性
a[Map<String,String> x, int y] : ... ;


// 规则级的命名动作: @init, @after
/** Derived from rule "row : field (',' field)* '\r'? '\n' ;" */
row[String[] columns]
   returns [Map<String,String> values]
   locals [int col=0]
	@init {
	$values = new HashMap<String,String>();
	}
	@after {
	if ($values!=null && $values.size()>0) {
	System.out.println("values = "+$values);
	}
	}
	: ...
	;
```

## 选项

[more](https://github.com/antlr/antlr4/blob/master/doc/options.md)

# Usage

ZX

```shell
$ pip install antlr4-tools

$ antlr4
Downloading antlr4-4.13.2-complete.jar
ANTLR Parser Generator  Version 4.13.2
...

$ antlr4-parse --help
java org.antlr.v4.gui.Intrepreter [X.g4|XParser.g4 XLexer.g4] startRuleName
  [-tokens] [-tree] [-gui] [-encoding encodingname]
  [-trace] [-profile filename.csv] [input-filename(s)]
Omitting input-filename makes rig read from stdin.
```

## Components

* the ANTLR tool
* the ANTLR runtime (parse-time) API

## Example

Expr.g4:

```
grammar Expr;		
prog:	expr EOF ;
expr:	expr ('*'|'/') expr
    |	expr ('+'|'-') expr
    |	INT
    |	'(' expr ')'
    ;
NEWLINE : [\r\n]+ -> skip;
INT     : [0-9]+ ;
```

parsing:

ZX

```shell
$ antlr4-parse Expr.g4 prog -tree
10+20*30
^Z
(prog:1 (expr:2 (expr:3 10) + (expr:1 (expr:3 20) * (expr:3 30))) <EOF>)

$ antlr4-parse Expr.g4 prog -tokens -trace

$ antlr4-parse Expr.g4 prog -gui
```

gen parser code:

ZX

```shell
$ antlr4 Expr.g4
$ ls
Expr.g4      Expr.tokens            ExprLexer.interp  ExprLexer.tokens   ExprParser.java
Expr.interp  ExprBaseListener.java  ExprLexer.java    ExprListener.java
```

## VSCode

* [VS Code Extension for ANTLR4 Grammars](https://github.com/mike-lischke/vscode-antlr4)
    - [Debugging ANTLR4 grammars](https://github.com/mike-lischke/vscode-antlr4/blob/HEAD/doc/grammar-debugging.md)
    - [command-variable](https://github.com/rioj7/command-variable): Visual Studio Code extension for variable substitution via `${command:commandID}`

launch.json:

```json
{
  "version": "0.2.0",
  "configurations": [
    {
      "name": "Debug ANTLR4 grammar",
      "type": "antlr-debug",
      "request": "launch",
      // "input": "grammars/Expr.test.txt",
      "input": "${fileDirname}/${fileBasenameNoExtension}.test.txt",
      // "grammar": "grammars/Expr.g4",
      "grammar": "${relativeFileDirname}/${fileBasename}",
      // "startRule": "prog",
      "startRule": "${input:fileBasenameNoExtensionLowerCase}",
      "printParseTree": true,
      "visualParseTree": true
    }
  ],
  "inputs": [
    {
      "id": "fileBasenameNoExtensionLowerCase",
      "type": "command",
      "command": "extension.commandvariable.transform",
      "args": {
        "text": "${fileBasenameNoExtension|lowerCase}"
      }
    }
  ]
}
```

Expr.test.txt:

```plaintext
10+20*30
```

## Maven

* `org.antlr:antlr4-maven-plugin`
* `com.khubla.antlr:antlr4test-maven-plugin`: [example](https://github.com/antlr/grammars-v4/blob/master/sql/mysql/Positive-Technologies/pom.xml)

# StringTemplate 
* https://www.stringtemplate.org/

> What is StringTemplate?
>
> StringTemplate is a java template engine (with ports for C#, Objective-C, JavaScript, Scala) for generating source code, web pages, emails, or any other formatted text output. StringTemplate is particularly good at code generators, multiple site skins, and internationalization / localization. StringTemplate also powers ANTLR.

# See Also
* [Enkel JVM Language](https://github.com/JakubDziworski/Enkel-JVM-language): Enkel is a simple programming language running on the jvm - **Creating JVM language**
* [grammars-v4](https://github.com/antlr/grammars-v4): Grammars written for ANTLR v4; expectation that the grammars are free of actions.
