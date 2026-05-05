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

`escapeJavaString` can cap the escaped-content budget before appending a truncation suffix:

```kotlin
import one.wabbit.formatting.escapeJavaString

val escaped = escapeJavaString("line1\nline2", doubleQuoted = true, limit = 6, limitEnding = "...")
check(escaped == "line1...")
```

The limit is applied to escaped content, not to the original input length. The truncation suffix is
not counted against that limit.

## Output Rules

- `escapeJavaString` preserves alphanumeric characters and common ASCII punctuation
- `escapeJavaString` uses short escapes for `\n`, `\r`, `\t`, `\b`, selected quotes, and `\\`
- non-ASCII letters and digits are preserved by `escapeJavaString`
- other non-ASCII characters in `escapeJavaString` are emitted as `\uXXXX`
- `escapeJavaChar` uses short escapes for `\t`, `\r`, `\n`, and `\\`
- `escapeJavaChar` uses `\xNN` for other ISO control characters, including backspace
