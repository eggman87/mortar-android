# Developer guide

The Mortar design system currently supports jetpack compose. 

## Getting started 

First, bring in the required dependencies from artifactory:
```kotlin
    dependencies {
        implementation("com.eggman.mortar:compose-components:0.0.1")
        implementation("com.eggman.mortar:themes:0.0.1")
    }
```

The themes project will bring in all product themes from MDS, but don't worry - R8 will get rid of unused
classes when compiling a release build.

Next, to use Mortar components, it is required wrap all of your composable code with `MaterialMortarTheme`. Mortar currently
uses material 3 components internally. 

```kotlin
    MaterialMortarTheme(
        theme = HebMortar.theme,
        isInDarkTheme = isSystemInDarkTheme(),
    ) {
        //your compose code goes here....
    }
```

Current themes available: 
```kotlin
HebMortar.theme // heb 
CmMortar.theme // central market
```

## Components
*_view javadocs for more information on available parameters_

MDS currently supports the following components:



* `MortarButton`: A Mortar theme implementation of a button, this button uses mortar theming on top of material UI button theming.
```kotlin
            MortarButton(
                onClick = {},
                label = "I am a button"
            )
```
## Using theme properties 
It is recommended that you use components if they already exist, but you still might sometimes need 
to drive some of your UI with theme values. To do this, access colors/typography/components from 
any composable code that is within a `MaterialMortarTheme`:
```kotlin
LocalMortarTypography.current // access to theme typography 
LocalMortarColorScheme.current // access to theme colors
LocalMortarComponents.current // access to theme component properties.
```

## Adding/Updating themes 
To update a existing theme to the latest values, simply get the design data json from the design 
data repository, and drop it into `token-compiler/resources`, overwrite existing. 

To add a new theme, drop the new theme design data json file in `token-compiler/resources`. Then you 
need to create a new definition in the `themes` module, specifically inside of `DesignTokens.kt`. 

For example, if we were going to add a new theme for a product called "string cheese", it would look
like this: 

```kotlin
@GenerateTokens(
    themePrefix = "Sc",
    jsonFile = "sc_tokens.json",
)
internal object StringCheeseTokenSchema
```

drop json file called `sc_tokens.json` into `token-compiler/resources`.

## Javadocs
To see dokka javadocs in your browser, run:
```
./gradlew dokkaJavadoc
```
Then you can navigate to each modules index.html @ module/build/dokka/javadoc/index.html to view 
javadocs in your browser. 

## Breaking Changes
To support semantic versioning, this project uses the [binary-compatibility-validator](https://github.com/Kotlin/binary-compatibility-validator) 
plugin. Run the following command to dump the current api as the baseline version to compare against:
```
./gradlew apiDump
```
After running this command, you will see `.api` files located in each module that is public facing. These 
files should be committed into source control. Everytime a new version is released, the api baseline
should be updated accordingly. 

When making changes, you can verify if you have binary incompatible changes by running: 
```
./gradlew apiCheck
```

## Publishing
To publish all modules (which is desirable), simply run from terminal:
```
./gradlew publish
```
This will build and publish aars/jars of modules locally for now. You can see these generated artifacts
in `build/repos/com/eggman/mortar`. Each publication will include sources and docs jar so that developers
consuming these components can debug in the library and see all code documentation.

In a production environment, these would hopefully be managed via a mature maven repository system
like Artifactory. 

