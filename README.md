# kotlin-java-escape

`kotlin-java-escape` provides small, dependency-light utilities for rendering strings and characters with Java-style escaping.

It is aimed at code generation, debugging, diagnostics, and any workflow where arbitrary text needs to be shown as a Java-friendly literal instead of raw terminal output.

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

`doubleQuoted` controls which quote character gets escaped:

- `true`: escape `"`
- `false`: escape `'`

## Escape A Character

```kotlin
import one.wabbit.formatting.escapeJavaChar

check(escapeJavaChar('\n') == "\\n")
check(escapeJavaChar('\\') == "\\\\")
check(escapeJavaChar('\u2603') == "\\u2603")
```

`escapeJavaChar` also escapes `[`, `]`, and `-`, which makes it convenient for character-class style output and parser/debug tooling.

## Truncation

`escapeJavaString` can enforce a maximum output length after escaping:

```kotlin
import one.wabbit.formatting.escapeJavaString

val escaped = escapeJavaString("line1\nline2", doubleQuoted = true, limit = 6, limitEnding = "...")

check(escaped == "line1...")
```

That matters because a single input character may expand to multiple output characters such as `\\n` or `\\u2603`.

## Output Rules

- letters, digits, and common ASCII punctuation are preserved
- `\n`, `\r`, `\t`, `\b`, quotes, and backslashes get Java-style escapes
- non-ASCII characters become `\uXXXX`
- ISO control characters in `escapeJavaChar` become `\xNN`

## API Reference

Published API docs are available at:

- [https://wabbit-corp.github.io/kotlin-java-escape/](https://wabbit-corp.github.io/kotlin-java-escape/)

## Licensing

This project is licensed under the GNU Affero General Public License v3.0 (AGPL-3.0) for open source use.

For commercial use, contact Wabbit Consulting Corporation at `wabbit@wabbit.one`.

## Contributing

Before contributions can be merged, contributors need to agree to the repository CLA.
