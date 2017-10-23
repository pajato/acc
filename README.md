# acc
A short term test repo for addressing an Android aggregate code coverage (acc) issue whereby separately generated JVM (unit) test results cannot be combined with instrumentation (connected test) data.

Build instructions:

    ./gradlew clean jacocoTestReport

With the Android Gradle plugin version 2.3.3 and Gradle 3.3, no errors occur and the generated report does contain the expected result! Nice.

When using the Android Gradle plugin version 3.0.0-rc1 with Gradle 4.1, two errors occurred.  The first:

    ...
    :app:testDebugUnitTest
    Download https://jcenter.bintray.com/org/jacoco/org.jacoco.agent/0.7.8/org.jacoco.agent-0.7.8.pom
    Download https://jcenter.bintray.com/org/jacoco/org.jacoco.build/0.7.8/org.jacoco.build-0.7.8.pom
    Download https://jcenter.bintray.com/org/jacoco/org.jacoco.agent/0.7.8/org.jacoco.agent-0.7.8.jar
    objc[15619]: Class JavaLaunchHelper is implemented in both /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/bin/java (0x10442a4c0) and /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/libinstrument.dylib (0x1044b64e0). One of the two will be used. Which one is undefined.
    Error occurred during initialization of VM
    java.lang.InternalError: Could not create SecurityManager: worker.org.gradle.process.internal.worker.child.BootstrapSecurityManager
        at sun.misc.Launcher.<init>(Launcher.java:102)
        at sun.misc.Launcher.<clinit>(Launcher.java:53)
        at java.lang.ClassLoader.initSystemClassLoader(ClassLoader.java:1451)
        at java.lang.ClassLoader.getSystemClassLoader(ClassLoader.java:1436)

    :app:testDebugUnitTest FAILED

This failure seems like an anomaly and does not repeat.  Instead, a second error occurs which is recurrent:

    ...
    :app:testDebugUnitTestobjc[15694]: Class JavaLaunchHelper is implemented in both /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/bin/java (0x10fc714c0) and /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/libinstrument.dylib (0x10fcfd4e0). One of the two will be used. Which one is undefined.

    :app:jacocoTestReport
    Download https://jcenter.bintray.com/org/jacoco/org.jacoco.ant/0.7.8/org.jacoco.ant-0.7.8.pom
    Download https://jcenter.bintray.com/org/jacoco/org.jacoco.core/0.7.8/org.jacoco.core-0.7.8.pom
    Download https://jcenter.bintray.com/org/jacoco/org.jacoco.report/0.7.8/org.jacoco.report-0.7.8.pom
    Download https://jcenter.bintray.com/org/ow2/asm/asm-debug-all/5.1/asm-debug-all-5.1.pom
    Download https://jcenter.bintray.com/org/jacoco/org.jacoco.core/0.7.8/org.jacoco.core-0.7.8.jar
    Download https://jcenter.bintray.com/org/jacoco/org.jacoco.ant/0.7.8/org.jacoco.ant-0.7.8.jar
    Download https://jcenter.bintray.com/org/jacoco/org.jacoco.report/0.7.8/org.jacoco.report-0.7.8.jar
    Download https://jcenter.bintray.com/org/ow2/asm/asm-debug-all/5.1/asm-debug-all-5.1.jar
    :app:jacocoTestReport FAILED

    FAILURE: Build failed with an exception.

    * What went wrong:
    Execution failed for task ':app:jacocoTestReport'.
    > Unable to read execution data file /Users/pmr/Projects/acc/app/build/outputs/code-coverage/connected/Nexus 6 - 7.0-coverage.ec

And this is where things currently stand. The burning questions are:

1) Which module owns this bug: Gradle, Jacoco, Android Gradle Plugin?

I cast my vote for the latter.

2) Is there a workaround to be had?

Not that I can find ... yet.
