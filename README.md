# acc
A short term test repo for addressing an Android aggregate code coverage (acc) issue whereby separately generated JVM (unit) test results cannot be combined with instrumentation (connected test) data.

Build instructions:

    ./gradlew clean jacocoTestReport

With the Android Gradle plugin version 2.3.3 and Gradle 3.3, no errors occur and the generated report does contain the expected result! Nice.
