# kotlin-java-escape

A Kotlin-based library designed to provide utilities for escaping Java strings and characters, ensuring they are safely represented in Java source code or outputs.

## Installation

Add the following dependency to your project:

```kotlin
repositories {
    maven("https://jitpack.io")
}

dependencies {
    implementation("com.github.wabbit-corp:kotlin-java-escape:1.0.0")
}
```

## Usage

```kotlin
fun main() {
    val originalString = "Hello, World!\nThis is a test string with \"quotes\" and a backslash \\."
    val escapedString = escapeJavaString(originalString, doubleQuoted = true)
    println(escapedString)
    // Output: Hello, World!\nThis is a test string with \"quotes\" and a backslash \\.
}
```

```kotlin
fun main() {
    val char = '\n'
    val escapedChar = escapeJavaChar(char)
    println(escapedChar)
    // Output: \n
}
```

## Licensing

This project is licensed under the GNU Affero General Public License v3.0 (AGPL-3.0) for open source use.

For commercial use, please contact Wabbit Consulting Corporation (at wabbit@wabbit.one) for licensing terms.

## Contributing

Before we can accept your contributions, we kindly ask you to agree to our Contributor License Agreement (CLA).
