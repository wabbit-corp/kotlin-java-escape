# Module kotlin-java-escape

`kotlin-java-escape` turns strings and characters into Java-style escaped literals.

It is useful when you need to:

- display arbitrary text as a Java string literal fragment
- render characters safely for logs, code generation, or diagnostics
- normalize control characters into readable escapes
- truncate escaped output without manually counting expansion length

## Installation

```kotlin
commonMain.dependencies {
    implementation("one.wabbit:kotlin-java-escape:1.0.1")
}
```

## Escape A String

```kotlin
import one.wabbit.formatting.escapeJavaString

val input = "Hello,\n\"world\" \\ snowman: \u2603"
val escaped = escapeJavaString(input, doubleQuoted = true)

check(escaped == "Hello,\\n\\\"world\\\" \\\\ snowman: \\u2603")
```

When `doubleQuoted = true`, double quotes are escaped and single quotes are left alone. When `doubleQuoted = false`, the opposite happens.

## Escape A Character

```kotlin
import one.wabbit.formatting.escapeJavaChar

check(escapeJavaChar('\n') == "\\n")
check(escapeJavaChar('\\') == "\\\\")
check(escapeJavaChar('\u2603') == "\\u2603")
```

`escapeJavaChar` also escapes `[`, `]`, and `-`, which is useful when building character-class style output.

## Truncation

`escapeJavaString` can cap the rendered output length after escaping:

```kotlin
import one.wabbit.formatting.escapeJavaString

val escaped = escapeJavaString("line1\nline2", doubleQuoted = true, limit = 6, limitEnding = "...")
check(escaped == "line1...")
```

The limit is applied to the escaped output, not to the original input length.

## Output Rules

- alphanumeric characters are kept as-is
- common control characters use short escapes such as `\n`, `\r`, `\t`, and `\\`
- non-ASCII characters are emitted as `\uXXXX`
- ISO control characters in `escapeJavaChar` use `\xNN`

## API Reference

Published API docs are available at:

- [https://wabbit-corp.github.io/kotlin-java-escape/](https://wabbit-corp.github.io/kotlin-java-escape/)
