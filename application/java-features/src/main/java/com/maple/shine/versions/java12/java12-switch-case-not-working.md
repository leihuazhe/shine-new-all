
# 为什么下面代码只能在 Java 12 中运行, Java 13 以后不可以?
```java
var shouldIGo = switch (mood) {
            // accepts multiple case labels
            case Good, Regular -> {
                break true;
            }
            case Bad -> {
                break false;
            }
            // there is no need to use default (we covered all possible cases)
        };
```

## 在 Java 21 中，你的代码将无法编译，因为 switch 表达式的语法有所变化。以下是一些需要注意的地方：

- break 替换为 yield: 在 switch 表达式中不再使用 break，而是使用 yield 来返回值。

- 封闭的代码块: case 子句不需要显式的 {} 来封闭代码块，除非有多条语句需要执行。

## 以下是修改后的代码：

```java

var shouldIGo = switch (mood) {
case Good, Regular -> true;
case Bad -> false;
// 如果你列举了所有可能的情况，并且没有遗漏，就不需要 `default` 子句
};
```

如果 mood 是一个 enum 类型，并且你确实涵盖了所有可能的枚举值，那么你可以省略 default 子句。如果存在未列举的可能情况，则需要添加 default 子句：

```java
var shouldIGo = switch (mood) {
case Good, Regular -> true;
case Bad -> false;
default -> throw new IllegalArgumentException("Unexpected value: " + mood);
};
```

请确保你使用的 mood 是一个枚举或常量，并且所有可能的值都已经在 switch 表达式中处理。这样，你的代码应该可以在 Java 21 中正确编译和运行。

## 为什么在 java 12 中可以，这个怎么界定，如何兼容？
在 Java 12 中引入了增强的 switch 表达式，这使得 switch 语句更加简洁和灵活。
然而，从 Java 13 开始，**yield 关键字取代了 break 作为 switch 表达式中的返回值指示符。** 因此，您的代码在 Java 12 中可以运行，但在 Java 13 及以后的版本中，需要进行修改。

为什么在 Java 12 中可以运行？
在 Java 12 中，switch 表达式首次引入，允许使用 break 来返回值：

```java

var shouldIGo = switch (mood) {
    case Good, Regular -> {
        break true;
    }
    case Bad -> {
        break false;
    }
};
```
Java 13 及以后的版本中的更改
从 Java 13 开始，yield 关键字被引入，并且必须使用它来返回值：

```java
var shouldIGo = switch (mood) {
    case Good, Regular -> {
        yield true;
    }
    case Bad -> {
        yield false;
    }
};
```
如何兼容 Java 12 和以后的版本？
如果您希望代码在 Java 12 和更高版本中都能正常运行，可以编写条件编译逻辑，或者简单地将代码更新为兼容 Java 13+ 的版本，并要求在 Java 13 或更高版本中运行。这是因为 yield 关键字在 Java 12 中还不可用。

代码更新示例
以下是更新后的代码，适用于 Java 13 及以后的版本：

```java
var shouldIGo = switch (mood) {
case Good, Regular -> true;
case Bad -> false;
};
```
对于需要支持 Java 12 的项目，您可以在构建工具（如 Maven 或 Gradle）中指定编译目标版本，并保持使用 Java 12 的语法。如果需要在不同版本的 Java 中编译，可以考虑使用条件编译，但这在 Java 中并不常见，因为 Java 并不直接支持条件编译（不像 C/C++ 中的预处理器指令）。

## 结论
为了简化开发并利用最新的语言特性，建议升级项目使用 Java 13 或更高版本，并更新 switch 表达式的语法。如果必须支持多个版本，需谨慎处理代码的语法和构建配置。