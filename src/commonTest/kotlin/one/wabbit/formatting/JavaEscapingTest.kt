// SPDX-License-Identifier: LicenseRef-Wabbit-Public-Test-License-1.1

package one.wabbit.formatting

import kotlin.test.Test
import kotlin.test.assertEquals

class JavaEscapingTest {
    @Test
    fun escapesDoubleQuotedStrings() {
        assertEquals(
            "Hello,\\n\\\"world\\\" \\\\ snowman: \\u2603",
            escapeJavaString("Hello,\n\"world\" \\ snowman: \u2603", doubleQuoted = true),
        )
    }

    @Test
    fun truncatesWhenUnicodeEscapeWouldOverflowLimit() {
        assertEquals(
            "ab...",
            escapeJavaString("ab\u2603", doubleQuoted = true, limit = 4, limitEnding = "..."),
        )
    }

    @Test
    fun keepsFullUnicodeEscapeWhenItFitsWithinLimit() {
        assertEquals(
            "ab\\u2603",
            escapeJavaString("ab\u2603", doubleQuoted = true, limit = 8, limitEnding = "..."),
        )
    }

    @Test
    fun escapesIndividualCharacters() {
        assertEquals("\\n", escapeJavaChar('\n'))
        assertEquals("\\\\", escapeJavaChar('\\'))
        assertEquals("\\u2603", escapeJavaChar('\u2603'))
        assertEquals("\\[", escapeJavaChar('['))
    }
}
