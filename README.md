# acc
A short term test repo for addressing an Android aggregate code coverage (acc) issue whereby separately generated JVM (unit) test results cannot be combined with instrumentation (connected test) data.

Build instructions:

    ./gradlew clean jacocoTestReport

With the Android Gradle plugin version 2.3.3 and Gradle 3.3, no errors occur and the generated report does contain the expected result! Nice.

When using the Android Gradle plugin version 3.0.0 with Gradle 4.2, an error occurs as shown below.  The full build log file is build-3.0.0.txt in the top level directory.  Note that Gradle downloads Jacoco dependencies for the connected test that are version 0.7.4.201502262128 whereas the unit test and the report generator use version 0.7.8.  These two version use an incompatible data format, hence the bug.

    ./gradlew clean jacocoTestReport
    Downloading https://services.gradle.org/distributions/gradle-4.2-all.zip
    ...
    Download https://jcenter.bintray.com/org/jacoco/org.jacoco.report/0.7.4.201502262128/org.jacoco.report-0.7.4.201502262128.pom
    ...
    Download https://jcenter.bintray.com/org/jacoco/org.jacoco.core/0.7.4.201502262128/org.jacoco.core-0.7.4.201502262128.pom
    ...
    Download https://jcenter.bintray.com/org/jacoco/org.jacoco.build/0.7.4.201502262128/org.jacoco.build-0.7.4.201502262128.pom
    Download https://jcenter.bintray.com/org/jacoco/org.jacoco.report/0.7.4.201502262128/org.jacoco.report-0.7.4.201502262128.jar
    Download https://jcenter.bintray.com/org/jacoco/org.jacoco.core/0.7.4.201502262128/org.jacoco.core-0.7.4.201502262128.jar
    ...
    Download https://jcenter.bintray.com/org/jacoco/org.jacoco.agent/0.7.4.201502262128/org.jacoco.agent-0.7.4.201502262128.pom
    ...
    :app:transformClassesWithJacocoForDebug
    ...
    Download https://jcenter.bintray.com/org/jacoco/org.jacoco.agent/0.7.4.201502262128/org.jacoco.agent-0.7.4.201502262128-runtime.jar
    ...
    :app:connectedDebugAndroidTest
    Starting 2 tests on Pixel XL - 8.0.0
    :app:createDebugAndroidTestCoverageReport
    Download https://jcenter.bintray.com/org/jacoco/org.jacoco.ant/0.7.4.201502262128/org.jacoco.ant-0.7.4.201502262128.pom
    Download https://jcenter.bintray.com/org/ow2/asm/asm-debug-all/5.0.1/asm-debug-all-5.0.1.pom
    Download https://jcenter.bintray.com/org/jacoco/org.jacoco.agent/0.7.4.201502262128/org.jacoco.agent-0.7.4.201502262128.jar
    Download https://jcenter.bintray.com/org/jacoco/org.jacoco.ant/0.7.4.201502262128/org.jacoco.ant-0.7.4.201502262128.jar
    Download https://jcenter.bintray.com/org/ow2/asm/asm-debug-all/5.0.1/asm-debug-all-5.0.1.jar
    :app:createDebugCoverageReport
    :app:compileDebugUnitTestKotlin
    Using Kotlin incremental compilation
    :app:preDebugUnitTestBuild UP-TO-DATE
    :app:javaPreCompileDebugUnitTest
    :app:compileDebugUnitTestJavaWithJavac NO-SOURCE
    :app:mockableAndroidJar
    :app:processDebugUnitTestJavaRes NO-SOURCE
    :app:testDebugUnitTest
    Download https://jcenter.bintray.com/org/jacoco/org.jacoco.agent/0.7.8/org.jacoco.agent-0.7.8.pom
    Download https://jcenter.bintray.com/org/jacoco/org.jacoco.build/0.7.8/org.jacoco.build-0.7.8.pom
    Download https://jcenter.bintray.com/org/jacoco/org.jacoco.agent/0.7.8/org.jacoco.agent-0.7.8.jar
    :app:jacocoTestReport
    Download https://jcenter.bintray.com/org/jacoco/org.jacoco.ant/0.7.8/org.jacoco.ant-0.7.8.pom
    Download https://jcenter.bintray.com/org/jacoco/org.jacoco.core/0.7.8/org.jacoco.core-0.7.8.pom
    Download https://jcenter.bintray.com/org/jacoco/org.jacoco.report/0.7.8/org.jacoco.report-0.7.8.pom
    Download https://jcenter.bintray.com/org/ow2/asm/asm-debug-all/5.1/asm-debug-all-5.1.pom
    Download https://jcenter.bintray.com/org/jacoco/org.jacoco.report/0.7.8/org.jacoco.report-0.7.8.jar
    Download https://jcenter.bintray.com/org/jacoco/org.jacoco.core/0.7.8/org.jacoco.core-0.7.8.jar
    Download https://jcenter.bintray.com/org/jacoco/org.jacoco.ant/0.7.8/org.jacoco.ant-0.7.8.jar
    Download https://jcenter.bintray.com/org/ow2/asm/asm-debug-all/5.1/asm-debug-all-5.1.jar
    :app:jacocoTestReport FAILED

    FAILURE: Build failed with an exception.

    * What went wrong:
    Execution failed for task ':app:jacocoTestReport'.
    > Unable to read execution data file /Users/pmr/Projects/acc/app/build/outputs/code-coverage/connected/Pixel XL - 8.0.0-coverage.ec
    ...

    BUILD FAILED in 1m 7s
    61 actionable tasks: 60 executed, 1 up-to-date

    Compilation exited abnormally with code 1 at Sun Oct 29 03:52:43

And this is where things currently stand. The burning questions are:

1) Which module owns this bug: Gradle, Jacoco, Android Gradle Plugin?

I cast my vote for the latter.

2) Is there a workaround to be had?

Not that I can find ... yet.
