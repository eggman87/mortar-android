# Testing guide

The Mortar design system currently has several different types of tests you can run: 

## Unit tests 
```
./gradlew test 
```
This will run all jvm based unit tests, which includes junit tests and paparazzi tests. After running,
you can see test reports in all modules with junit tests at: 
```
build/reports/tests/index.html 
```

## Snapshot tests 
This project uses [paparazzi](https://github.com/cashapp/paparazzi) for snapshot testing. Tests will 
run when executing `./gradlew test` as these tests are jvm based. 

After running, you can see reports at:
```
compose-components/build/reports/paparazzi/debug/index.html 
compose-components/build/reports/paparazzi/release/index.html 
```

To update paparazzi screenshots used to detect breaking changes, run: 
```
./gradlew recordPaparazziDebug
```
The screenshots will be located in `compose-components/src/test/snapshots`

To verify your changes do not have breaking UI changes with paparazzi, run: 
```
./gradlew verifyPaparazziDebug
```
Sometimes, you might have changes that are intentional and breaking (visually )

## Android instrumentation tests

To run android instrumentation tests, ensure you have a device or emulator running and connected to 
your machine, and run: 
```
./gradlew connectedAndroidTest
```
View reports in android modules at:
```
build/reports/androidTests/connected/debug/index.html
```