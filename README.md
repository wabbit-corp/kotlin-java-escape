# kotlin-java-escape

![](./.meta/github-project-banner.png)

<p align=center>
    <img src="https://img.shields.io/maven-central/v/one.wabbit/kotlin-java-escape" alt="Maven Central">
    <img src="https://img.shields.io/badge/Kotlin-Multiplatform-7F52FF" alt="Kotlin Multiplatform">
</p>

`kotlin-java-escape` provides small, dependency-light utilities for rendering strings and characters with Java-style escaping.

It is aimed at code generation, debugging, diagnostics, and any workflow where arbitrary text needs to be shown as a Java-friendly literal instead of raw terminal output.

## 🚀 Installation

```kotlin
commonMain.dependencies {
    implementation("one.wabbit:kotlin-java-escape:1.0.1")
}
```

## 🚀 Usage

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

`escapeJavaString` can enforce an escaped-content budget before appending a truncation suffix:

```kotlin
import one.wabbit.formatting.escapeJavaString

val escaped = escapeJavaString("line1\nline2", doubleQuoted = true, limit = 6, limitEnding = "...")

check(escaped == "line1...")
```

That matters because a single input character may expand to multiple output characters such as `\\n` or `\\u2603`.
The `limitEnding` suffix is appended after the budget is exhausted and is not counted against
`limit`.

## Output Rules

- `escapeJavaString` preserves letters, digits, and common ASCII punctuation
- `escapeJavaString` renders `\n`, `\r`, `\t`, `\b`, selected quotes, and backslashes with
  Java-style escapes
- non-ASCII letters and digits are preserved by `escapeJavaString`
- other non-ASCII characters in `escapeJavaString` become `\uXXXX`
- `escapeJavaChar` renders `\t`, `\r`, `\n`, and backslash with short escapes
- `escapeJavaChar` renders other ISO control characters, including backspace, as `\xNN`
- `escapeJavaChar` leaves quote characters unchanged

## Status

This library is small and stable in scope. It renders Java-style escaped fragments; it does not add
surrounding quotes or parse escaped Java input back into text.

## Documentation

- [User guide](docs/user-guide.md)
- [API reference notes](docs/api-reference.md)
- [Troubleshooting](docs/troubleshooting.md)
- [Development](docs/development.md)

Generated API docs can be built locally with Dokka. See [API reference notes](docs/api-reference.md)
for the command.

## Release Notes

- [CHANGELOG.md](CHANGELOG.md)

## Licensing

This project is licensed under the GNU Affero General Public License v3.0 (AGPL-3.0) for open source use.

For commercial use, contact Wabbit Consulting Corporation at `wabbit@wabbit.one`.

## Contributing

Before contributions can be merged, contributors need to agree to the repository CLA.
