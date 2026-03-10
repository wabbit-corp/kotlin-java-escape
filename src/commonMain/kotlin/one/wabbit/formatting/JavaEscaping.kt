package one.wabbit.formatting

private val SANE_ASCII_CHARS = "~!@#\$%^&*()_+{}|:\"<>?`-=[]\\;',./ ".toSet()

private fun Int.toHex(width: Int): String =
    toString(16).lowercase().padStart(width, '0')

/**
 * Escapes a string so it can be rendered safely as Java-style literal content.
 *
 * Example:
 * ```kotlin
 * val escaped = escapeJavaString("Hello,\n\"world\"", doubleQuoted = true)
 * check(escaped == "Hello,\\n\\\"world\\\"")
 * ```
 *
 * @param str Input text to escape.
 * @param doubleQuoted Whether the output is intended for a double-quoted string literal.
 *   When `true`, `"` is escaped and `'` is preserved. When `false`, the inverse happens.
 * @param limit Maximum length of the escaped output.
 * @param limitEnding Suffix appended when the escaped output would exceed [limit].
 */
fun escapeJavaString(
    str: CharSequence,
    doubleQuoted: Boolean,
    limit: Int = Int.MAX_VALUE,
    limitEnding: String = "...",
): String {
    // Preserve readable ASCII, use Java-style escapes for control characters, and
    // fall back to Unicode escapes when a character would render ambiguously.
    var count = 0
    val sb = StringBuilder()
    for (c in str) {
        if (count >= limit) {
            sb.append(limitEnding)
            break
        }

        when {
            c == '\\' ->
                if (count + 2 <= limit) {
                    sb.append("\\\\")
                    count += 2
                } else {
                    sb.append(limitEnding)
                    break
                }
            c == '\t' ->
                if (count + 2 <= limit) {
                    sb.append("\\t")
                    count += 2
                } else {
                    sb.append(limitEnding)
                    break
                }
            c == '\b' ->
                if (count + 2 <= limit) {
                    sb.append("\\b")
                    count += 2
                } else {
                    sb.append(limitEnding)
                    break
                }
            c == '\n' ->
                if (count + 2 <= limit) {
                    sb.append("\\n")
                    count += 2
                } else {
                    sb.append(limitEnding)
                    break
                }
            c == '\r' ->
                if (count + 2 <= limit) {
                    sb.append("\\r")
                    count += 2
                } else {
                    sb.append(limitEnding)
                    break
                }
            c == '\'' ->
                if (!doubleQuoted) {
                    if (count + 2 <= limit) {
                        sb.append("\\'")
                        count += 2
                    } else {
                        sb.append(limitEnding)
                        break
                    }
                } else {
                    sb.append(c)
                    count += 1
                }
            c == '"' ->
                if (doubleQuoted) {
                    if (count + 2 <= limit) {
                        sb.append("\\\"")
                        count += 2
                    } else {
                        sb.append(limitEnding)
                        break
                    }
                } else {
                    sb.append(c)
                    count += 1
                }

            c.isLetterOrDigit() -> {
                sb.append(c)
                count += 1
            }
            c in SANE_ASCII_CHARS -> {
                sb.append(c)
                count += 1
            }
            else -> {
                if (count + 6 <= limit) {
                    sb.append("\\u")
                    sb.append(c.code.toString(16).padStart(4, '0'))
                    count += 6
                } else {
                    sb.append(limitEnding)
                    break
                }
            }
        }
    }
    return sb.toString()
}

/**
 * Escapes a single character using Java-style escape sequences.
 *
 * Common control characters are rendered as short escapes such as `\n` and `\t`.
 * ISO control characters fall back to `\xNN`, and non-ASCII printable characters use `\uXXXX`.
 */
fun escapeJavaChar(ch: Char): String =
    when (ch) {
        '\t' -> "\\t"
        '\r' -> "\\r"
        '\n' -> "\\n"
        '\\' -> "\\\\"
        '[' -> "\\["
        ']' -> "\\]"
        '-' -> "\\-"

        else ->
            if (ch.isISOControl()) {
                "\\x${ch.code.toHex(2)}"
            } else if (ch.code < 0x100) {
                ch.toString()
            } else {
                "\\u${ch.code.toHex(4)}"
            }
    }
