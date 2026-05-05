# Troubleshooting

## The Result Is Not Quoted

`escapeJavaString` returns literal content only.

```kotlin
val content = escapeJavaString("a\nb", doubleQuoted = true)
val literal = "\"$content\""
```

Add surrounding quotes in the caller when you need a complete Java literal.

## The Result Is Longer Than Limit

`limit` controls the escaped-content budget, not the final string length. If truncation happens,
`limitEnding` is appended after that budget and can make the returned string longer than `limit`.

## Some Non-ASCII Text Is Not Escaped

Letters and digits are preserved even when they are non-ASCII. Other non-ASCII string characters are
escaped as `\uXXXX`.

For `escapeJavaChar`, non-control code units below `0x100` are returned unchanged. Code units at
`0x100` and above use `\uXXXX`. Backspace is not a short escape in `escapeJavaChar`; it is rendered
as `\x08`.

## This Does Not Parse Escapes

The library only renders escaped output. It does not parse Java escape sequences back into raw text.
