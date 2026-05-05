# Development

`kotlin-java-escape` is a Kotlin Multiplatform library. In the monorepo workspace, build and test it
through the `dev` tooling:

```bash
./dev build kotlin-java-escape
```

From the project directory, or when working from a standalone checkout, run Gradle directly:

```bash
./gradlew build
```

## Documentation Standards

Public KDoc should state:

- whether a function returns literal content or a complete quoted literal
- which characters are preserved, escaped, or treated specially
- how truncation budgets and suffixes are applied

Generated Dokka output remains the source of truth for exact signatures and platform availability.
