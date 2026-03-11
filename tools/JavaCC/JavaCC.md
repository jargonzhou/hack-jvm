# JavaCC
* https://github.com/javacc/javacc
* https://javacc.github.io/javacc/

> **Java Compiler Compiler (JavaCC)** is the most popular parser generator for use with Java applications.
> 
> A parser generator is a tool that reads a grammar specification and converts it to a Java program that can recognize matches to the grammar.
> 
> In addition to the parser generator itself, JavaCC provides other standard capabilities related to parser generation such as tree building (via a tool called JJTree included with JavaCC), actions and debugging.
> 
> All you need to run a JavaCC parser, once generated, is a Java Runtime Environment (JRE).

books
* Copeland, Tom. **Generating Parsers with JavaCC**. 2009. Centennial Books.
* Reis, Anthony J. Dos. **Compiler Construction Using Java JavaCC and Yacc**. 2012. Wiley.

actions
* [JavaCC.ipynb](./JavaCC.ipynb)
* [javacc-examples](./javacc-examples/README.md)

# Terminology

* `<TOKEN>`: JavaCC文法中token定义
* `<Parser>`: JavaCC生成的解析器类名称
* BNF
* `*.jj`: JavaCC文法文件
* `*.jjt`: JJTree文件.
* AST: Abstract Syntax Tree, 抽象语法树
* error handling: 错误处理
* expansion choices: 展开选择项
* expansion choice conflict: 展开选择项冲突, 即下一个token无法确定唯一一个非终结符
* expansion choice point: 展开选择项点, 即下一个token可以是多个非终结符的开始token
* grammar: 文法
* JJTree: JavaCC使用的预处理器, 在JavaCC源码中多个位置插入解析树构建动作, 其输出用于创建解析器.
* JJDoc: 接收JavaCC解析器描述, 生成BNF文法的文档.
* JTB: Java Tree Builder, Java树构建器. 在标准JavaCC文法文件上运行JTB, 获得标注的文法.
* left factoring: 左因子化
* left recursion: 左递归
* lexical action: 词法动作
* lexical specification: 词法描述
* lexical state: 词法状态
* lookahead: 预看
* node descriptor: JJTree节点描述符
  * conditional node descriptor: 条件的节点描述符
  * definite node descriptor: 限定的节点描述符
  * embedded node descriptor: 内嵌的节点描述符
* nonterminal: 非终结符
* parse tree: 解析树
* parsing: 解析
* production rules: 产生式规则
* recursive descent parser: 递归下降解析器
* rigth recursion: 右递归
* semantic lookahead: 语义预看
* syntactic lookahead: 句法预看
* syntactic specification: 句法描述
* syntactic action: 句法动作
* terminal: 终结符
* token: 记号
* tokenizer: 记号器
* tokenizing: token化, 记号化

# Components

* JavaCC Command Line
* JavaCC Grammar
* JavaCC BNF
* JavaCC API
* JJTree
* JJDoc

# 文法

`JavaCC.bnf

## 产生式

* javacode_production Java代码产生式
* regular_expr_production 正则表达式产生式
    - `TOKEN`
    - `SPECIAL_TOKEN`
    - `SKIP`
    - `MORE`
    - lexical_state_list 词法状态
* token_manager_decls `TOKEN_MGR_DECLS` 插入到`*TokenManager`的代码
* bnf_production BNF产生式

# 词法

词法描述的构成:

* 正则表达式生成(regular expression productions): 正则表达式定义构成特定`Token`的字符序列.
* 词法状态(lexical state): 正则表达式生成的容器, 可以执行状态间切换.

四个特殊的正则表达式生成:

* `TOKEN`: 识别正则表达式生成`Token`. 使用`TOKEN_MGR_DECLS`编写处理识别出的token的代码.
* `SKIP`: 用于丢弃不需要的输入.
* `MORE`: 识别当前输入但不消费, 加到下一个创建的`Token`中.
* `SPECIAL_TOKEN`: 识别当前输入但不消费, 加到下一个创建的`Token`的`specialToken`字段中.

JavaCC中正则表达式:

| 名称                   | 示例                         |
| :--------------------- | :--------------------------- |
| 字面量                 | `"a"`                        |
| 字符类                 | `["a", "b", "c"]`            |
| 范围字符类             | `["a"-"z"]`                  |
| 混用字符类和范围字符类 | `["a", "c", "x"-"z"]`        |
| 取反                   | `~["a"]`                     |
| 范围取反               | `~["a"-"c"]`                 |
| 重复                   | `("a"){4}`                   |
| 范围重复               | `("a"){2,4}`                 |
| 一个或多个项           | `("a")+`, `("a" ["a"-"c"])+` |
| 零个或一个项           | `("a")?`, `(["+", "-"])?`    |
| 零个或多个项           | `("a")*`, `(["0"-"9"])*`     |
| 或                     | `["a"-"c"] \| ["x"-"z"]`     |

`TOKEN`消歧规则:

* 1. 最长匹配规则(maximal munch rule/longest match rule).
* 2. 首次出现优先规则: 处理几个记号匹配同样长度的数据的情况.

例:

```
TOKEN : {
  <HELLO : "hello">
  | <LETTERS : (["a"-"z"])+ >
}
```

私有记号定义(private token definition):

* `<#...>`.
* 不能有词法动作.
* 例: IDEA `java_comment.jj`

词法动作:

* 在词法描述中插入`*TokenManager#TokenLexicalActions(Token matchedToken)`, 当遇到特定token定义时执行.
* 例: IDEA `lexical_action.jj` `run_length_encoding.jj`

词法状态:

* `<XXX>`, 默认为`<DEFAULT>`.
* `<*>`: 适用于所有词法状态.
* 在`*Constants`中生成词法状态名称常量.
* 词法记号名称不能与词法状态名称相同.
* 例: IDEA `lexical_state.jj` `java_comment.jj`

# 解析

产生式:

* 终结符, 非终结符
* 独立项: 展开/展开点

句法描述:

* 类似于Java方法声明, 生成在`<Parser>`中.

```
// BNF产生式
ReturnType Nonterminal(ArgType args) : 
{
  // code in front of <Parser>#Nonterminal(...)
}
{
  // 展开选择项:
  // <TOKEN>     {句法动作}
  // Nonterminal {句法动作}
  // EBNF标记: (expansion)? 或 [expansion], (expansion)+, (expansion)*
}
```

例: IDEA `phone_bnf.jj`

句法动作:

* 引用`<Parser>`中字段和方法: `token`, `getNextToken()`, `getToken(int)`, ...

产生式之间的关联:

* 通过参数, 返回值, `<Parser>`中字段.
* 抛出异常.

```
JAVACODE
ReturnType Nonterminal(ArgType args)
{
  // Java代码, 生成在<Parser>#Nonterminal(...)中
}
```

例: IDEA `phone_javacode.jj`

展开选择项冲突解决方法:

* 左因子化
* 引入新的非终结符处理左递归
* 使用迭代替换左递归

例: IDEA `phone_choice.jj`

预看:

* JavaCC不回溯, 使用预看处理展开选择项冲突.
* 内嵌预看:
    - 忽略内嵌的句法预看.
    - 不忽略内嵌的语义预看.
* trick: 只用于预看的产生式.

```
LOOKAHEAD(int)                   // 多个token预看
LOOKAHEAD(展开选择项)            // 句法预看
LOOKAHEAD(int, 展开选择项) 
LOOKAHEAD({code-return-boolean}) // 语义预看
```

例: IDEA `phone_lookahead.jj` `phone_syntactic_lookahead.jj` `phone_semantic_lookahead.jj`

# 选项

## javacc

| 选项                            | 默认值  | 说明                                                 |
| ------------------------------- | ------- | ---------------------------------------------------- |
| BUILD_PARSER                    | true    | 是否生成解析器                                       |
| BUILD_TOKEN_MANAGER             | true    | 是否生成TokenManager                                 |
| CACHE_TOKENS                    | false   | 解析器是否预读token                                  |
| CHOICE_AMBIGUITY_CHECK          | 2       | 选项歧义检查                                         |
| COMMON_TOKEN_ACTION             | false   | 是否开启通用记号动作 `void CommonTokenAction(Token)` |
| DEBUG_LOOKAHEAD                 | false   | 是否开启预看调试                                     |
| DEBUG_PARSER                    | false   | 是否开启解析器调试                                   |
| DEBUG_TOKEN_MANAGER             | false   | 是否开启TokenManager调试                             |
| DEPTH_LIMIT                     | 0       |                                                      |
| ERROR_REPORTING                 | true    | 是否开启错误报告                                     |
| FORCE_LA_CHECK                  | false   | 是否强制带预看的选项检查                             |
| GENERATE_ANNOTATIONS            | false   |                                                      |
| GENERATE_BOILERPLATE            | true    |                                                      |
| GENERATE_CHAINED_EXCEPTION      | false   |                                                      |
| GENERATE_GENERICS               | false   |                                                      |
| GENERATE_STRING_BUILDER         | false   |                                                      |
| GRAMMAR_ENCODING                |         | 文法编码                                             |
| IGNORE_ACTIONS                  | false   |                                                      |
| IGNORE_CASE                     | false   | 是否忽略大小写                                       |
| JAVA_TEMPLATE_TYPE              | classic |                                                      |
| JAVA_UNICODE_ESCAPE             | false   | 是否解码Java Unicode转义序列                         |
| JDK_VERSION                     | 1.5     | JavaCC使用的Java语言版本                             |
| KEEP_LINE_COLUMN                | true    | 是否收集字符的行列信息                               |
| LOOKAHEAD                       | 1       | 全局预看记号的数量                                   |
| NAMESPACE                       |         |                                                      |
| NO_DFA                          | false   |                                                      |
| OTHER_AMBIGUITY_CHECK           | 1       | 量词歧义检查                                         |
| OUTPUT_DIRECTORY                | .       | 生成代码的目录                                       |
| OUTPUT_LANGUAGE                 | java    |                                                      |
| PARSER_CODE_GENERATOR           |         |                                                      |
| PARSER_INCLUDE                  |         |                                                      |
| PARSER_SUPER_CLASS              |         |                                                      |
| SANITY_CHECK                    | true    | 是否开启健全检查                                     |
| STACK_LIMIT                     |         |                                                      |
| STATIC                          | true    | 生成的记号器中是否只包含静态的字段和方法             |
| STOP_ON_FIRST_ERROR             | false   |                                                      |
| SUPPORT_CLASS_VISIBILITY_PUBLIC | true    | 生成的类的访问限定符是否是`public`                   |
| TOKEN_EXTENDS                   |         | 生成的`Token`类可以继承的类                          |
| TOKEN_FACTORY                   |         | 创建Token的工厂方法                                  |
| TOKEN_INCLUDE                   |         |                                                      |
| TOKEN_MANAGER_CODE_GENERATOR    |         |                                                      |
| TOKEN_MANAGER_INCLUDE           |         |                                                      |
| TOKEN_MANAGER_SUPER_CLASS       |         |                                                      |
| TOKEN_MANAGER_USES_PARSER       | false   | 记号器中是否包含解析器字段`parser`                   |
| TOKEN_SUPER_CLASS               |         |                                                      |
| UNICODE_INPUT                   | false   |                                                      |
| USER_CHAR_STREAM                | false   | 是否不使用`SimpleCharStream`                         |
| USER_TOKEN_MANAGER              | false   | 是否不使用`*TokenManager`                            |

## jjtree

| 选项                    | 默认值                            | 说明 |
| ----------------------- | --------------------------------- | ---- |
| STATIC                  | true                              |      |
| MULTI                   | false                             |      |
| NODE_DEFAULT_VOID       | false                             |      |
| NODE_SCOPE_HOOK         | false                             |      |
| NODE_USES_PARSER        | false                             |      |
| BUILD_NODE_FILES        | true                              |      |
| TRACK_TOKENS            | false                             |      |
| VISITOR                 | false                             |      |
| JDK_VERSION             | 1.5                               |      |
| NODE_CLASS              |                                   |      |
| NODE_PREFIX             | AST                               |      |
| NODE_PACKAGE            |                                   |      |
| NODE_EXTENDS            |                                   |      |
| NODE_FACTORY            |                                   |      |
| OUTPUT_FILE             | remove input file suffix, add .jj |      |
| OUTPUT_DIRECTORY        |                                   |      |
| JJTREE_OUTPUT_DIRECTORY | value of OUTPUT_DIRECTORY option  |      |
| VISITOR_DATA_TYPE       |                                   |      |
| VISITOR_RETURN_TYPE     | Object                            |      |
| VISITOR_EXCEPTION       |                                   |      |
