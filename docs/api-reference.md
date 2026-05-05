# API Reference

`kotlin-java-escape` exposes two top-level functions in `one.wabbit.formatting`.

Generate exact signatures locally with:

```bash
./gradlew dokkaGeneratePublicationHtml
```

## Public Surface

- `escapeJavaString(str, doubleQuoted, limit, limitEnding)`: escapes a character sequence as
  Java-style literal content. `limit` defaults to `Int.MAX_VALUE`; `limitEnding` defaults to
  `"..."`.
- `escapeJavaChar(ch)`: escapes one character for Java-style diagnostic output.

`escapeJavaString` does not add surrounding quotes. `limit` applies to escaped content before
`limitEnding` is appended.
