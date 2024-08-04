
# Mortar Android

Mortar is a fully functional design system for building consistent, flexible, and fast UI components. This project is the Android implementation of Mortar for Jetpack Compose, leaning heavily on Android platform standards and Material 3 (M3) components.

## Getting Started

### Prerequisites

- [Android Studio](https://developer.android.com/studio)
- [Java Development Kit (JDK) 11](https://adoptopenjdk.net/)
- [Kotlin](https://kotlinlang.org/)

### Running the Project

1. Clone the repository:
    ```sh
    git clone <repository-url>
    cd Mortar
    ```

2. Open the project in Android Studio.

3. Build and run the project using Android Studio.

### Running Tests

To run the tests, use the following command:
```sh
./gradlew test
```
Tests include regular junit, paparazzi, as well as compose instrumentation tests (androidTest).

### API Binary Checks

To run API binary checks, use the following command:
```sh
./gradlew apiCheck
```

### Publishing

To publish the project, use the following command:
```sh
./gradlew publish
```

The project is published to a local Maven repository located at `project/build/repos`.

## Documentation

Documentation is published to [GH pages](https://eggman87.github.io/mortar-android/dev_guide/). 

This project uses [MkDocs](https://www.mkdocs.org/) for documentation. Most documentation for this project will be located
there.

### Starting the MkDocs Server

1. Ensure you have MkDocs installed. If not, install it using:
    ```sh
    pip install mkdocs
    ```

2. Start the MkDocs server:
    ```sh
    mkdocs serve
    ```

3. Open your browser and navigate to `http://127.0.0.1:8000` to view the documentation.

4. To deploy latest documentation to GH pages, run:
    ```sh
    mkdocs gh-deploy
    ```

## Project Modules

- `docs`: Documentation files.
- `compose-components`: Standard UI components like buttons, text fields, etc.
- `compose-core`: Base component code and logic common to all Compose implementations.
- `demo-mortar`: A demo application showcasing Mortar components.
- `themes`: Generated product-specific themes.
- `token-compiler`: Kotlin annotation processor for generating theme code from JSON.
- `token-model`: Generated token definitions.

## Versioning

Mortar follows [semantic versioning](https://semver.org/).

## Contributing

Contributions are welcome! Please read the [CONTRIBUTING.md](CONTRIBUTING.md) for details.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
