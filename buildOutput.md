```
Working Directory: /Users/edeandre/workspaces/Stuff/gradle-testkit-jvm-arguments
Gradle User Home: /Users/edeandre/.gradle
Gradle Distribution: Specific Gradle version 3.2.1
Gradle Version: 3.2.1
Java Home: /Library/Java/JavaVirtualMachines/jdk1.8.0_102.jdk/Contents/Home
JVM Arguments: None
Program Arguments: None
Gradle Tasks: clean build

:buildSrc:compileJava UP-TO-DATE
:buildSrc:compileGroovy UP-TO-DATE
:buildSrc:pluginDescriptors UP-TO-DATE
:buildSrc:processResources UP-TO-DATE
:buildSrc:classes UP-TO-DATE
:buildSrc:jar UP-TO-DATE
:buildSrc:assemble UP-TO-DATE
:buildSrc:pluginUnderTestMetadata UP-TO-DATE
:buildSrc:compileTestJava UP-TO-DATE
:buildSrc:compileTestGroovy UP-TO-DATE
:buildSrc:processTestResources UP-TO-DATE
:buildSrc:testClasses UP-TO-DATE
:buildSrc:test UP-TO-DATE
:buildSrc:validateTaskProperties UP-TO-DATE
:buildSrc:check UP-TO-DATE
:buildSrc:build UP-TO-DATE
:clean
:compileJava UP-TO-DATE
:compileGroovy
:pluginDescriptors
:processResources
:captureBuildSystemVersion
:classes
:jar
:assemble
:pluginUnderTestMetadata
:compileTestJava UP-TO-DATE
:compileTestGroovy UP-TO-DATE
:processTestResources UP-TO-DATE
:testClasses UP-TO-DATE
:test UP-TO-DATE
:createCustomGradleDist
:compileIntegTestJava UP-TO-DATE
:compileIntegTestGroovy
:processIntegTestResources
:integTestClasses
:integTestJava HotSpot(TM) 64-Bit Server VM warning: ignoring option MaxPermSize=128m; support was removed in 8.0
objc[14209]: Class JavaLaunchHelper is implemented in both /Library/Java/JavaVirtualMachines/jdk1.8.0_102.jdk/Contents/Home/bin/java and /Library/Java/JavaVirtualMachines/jdk1.8.0_102.jdk/Contents/Home/jre/lib/libinstrument.dylib. One of the two will be used. Which one is undefined.


com.company.gradle.build.plugin.BasePluginFailSpec > Verify JVM Arguments STANDARD_OUT
    Copying from /Users/edeandre/workspaces/Stuff/gradle-testkit-jvm-arguments/build/resources/integTest/com/company/gradle/build/plugin/BasePluginFailSpec to /Users/edeandre/workspaces/Stuff/gradle-testkit-jvm-arguments/build/integTests/BasePluginFailSpec
    Downloading file:/Users/edeandre/workspaces/Stuff/gradle-testkit-jvm-arguments/build/distributions/my-gradle-1.0-bin.zip
    .................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................
    Unzipping /Users/edeandre/workspaces/Stuff/gradle-testkit-jvm-arguments/build/tmp/TestKit/wrapper/dists/my-gradle-1.0-bin/cso21vuim8lp40pi16izs1ih4/my-gradle-1.0-bin.zip to /Users/edeandre/workspaces/Stuff/gradle-testkit-jvm-arguments/build/tmp/TestKit/wrapper/dists/my-gradle-1.0-bin/cso21vuim8lp40pi16izs1ih4
    Set executable permissions for: /Users/edeandre/workspaces/Stuff/gradle-testkit-jvm-arguments/build/tmp/TestKit/wrapper/dists/my-gradle-1.0-bin/cso21vuim8lp40pi16izs1ih4/my-gradle-bin/bin/gradle
    company.gradle.artifactory.dontResolvePluginsFromArtifactory = null
    company.gradle.skipUpdateCheck = null
    Got ClassNotFoundException trying to load class com.company.gradle.build.SystemBuildListener
    Inside com.company.gradle.build.plugin.BasePlugin for project BasePluginFailSpec
    :verifyJvmParams

    BUILD SUCCESSFUL

    Total time: 2.202 secs


com.company.gradle.build.plugin.BasePluginFailSpec > Verify JVM Arguments FAILED
    Condition not satisfied:

    buildResult.output.contains 'company.gradle.artifactory.dontResolvePluginsFromArtifactory = true'
    |           |      |
    |           |      false
    |           company.gradle.artifactory.dontResolvePluginsFromArtifactory = null
    |           company.gradle.skipUpdateCheck = null
    |           Got ClassNotFoundException trying to load class com.company.gradle.build.SystemBuildListener
    |           Inside com.company.gradle.build.plugin.BasePlugin for project BasePluginFailSpec
    |           :verifyJvmParams
    |            
    |           BUILD SUCCESSFUL
    |            
    |           Total time: 2.202 secs
    org.gradle.testkit.runner.internal.FeatureCheckBuildResult@63824432

com.company.gradle.build.plugin.BasePluginSuccessSpec > Verify JVM Arguments STANDARD_OUT
    Copying from /Users/edeandre/workspaces/Stuff/gradle-testkit-jvm-arguments/build/resources/integTest/com/company/gradle/build/plugin/BasePluginSuccessSpec to /Users/edeandre/workspaces/Stuff/gradle-testkit-jvm-arguments/build/integTests/BasePluginSuccessSpec
    company.gradle.artifactory.dontResolvePluginsFromArtifactory = true
    company.gradle.skipUpdateCheck = true
    Got ClassNotFoundException trying to load class com.company.gradle.build.SystemBuildListener
    Inside com.company.gradle.build.plugin.BasePlugin for project BasePluginSuccessSpec
    :verifyJvmParams

    BUILD SUCCESSFUL

    Total time: 1.72 secs


com.company.gradle.build.plugin.BasePluginSuccessSpec > Verify JVM Arguments PASSED

2 tests completed, 1 failed
:integTest FAILED

FAILURE: Build failed with an exception.

* What went wrong:
Execution failed for task ':integTest'.
> There were failing tests. See the report at: file:///Users/edeandre/workspaces/Stuff/gradle-testkit-jvm-arguments/build/reports/tests/integTest/index.html

* Try:
Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output.

BUILD FAILED

Total time: 12.177 secs
```