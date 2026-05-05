# User Guide

`kotlin-java-escape` renders text as Java-style literal content. It is intended for diagnostics,
code generation, parser debugging, and any place where raw text should be shown with visible control
characters.

## Strings

`escapeJavaString` returns escaped content only. It does not add surrounding quotes.

```kotlin
import one.wabbit.formatting.escapeJavaString

val input = "Hello,\n\"world\" \\ snowman: \u2603"
val escaped = escapeJavaString(input, doubleQuoted = true)

check(escaped == "Hello,\\n\\\"world\\\" \\\\ snowman: \\u2603")
```

Use `doubleQuoted = true` when the content will be placed inside a double-quoted literal. Use
`doubleQuoted = false` when it will be placed inside a single-quoted literal.

## Characters

`escapeJavaChar` renders a single character:

```kotlin
import one.wabbit.formatting.escapeJavaChar

check(escapeJavaChar('\n') == "\\n")
check(escapeJavaChar('\b') == "\\x08")
check(escapeJavaChar('\\') == "\\\\")
check(escapeJavaChar('[') == "\\[")
```

It also escapes `]` and `-`, which is useful for character-class style debug output.

## Unicode

`escapeJavaString` preserves letters and digits, including non-ASCII letters and digits. Other
non-ASCII string characters are rendered as `\uXXXX` escape sequences.

For single characters, `escapeJavaChar` leaves non-control code units below `0x100` unchanged and
uses `\uXXXX` for code units at `0x100` and above.

## Truncation

The `limit` parameter is an escaped-content budget. The `limitEnding` suffix is appended after that
budget is exhausted and is not counted against the budget.

```kotlin
import one.wabbit.formatting.escapeJavaString

val escaped = escapeJavaString("line1\nline2", doubleQuoted = true, limit = 6, limitEnding = "...")

check(escaped == "line1...")
```
