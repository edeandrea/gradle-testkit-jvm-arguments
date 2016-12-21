Sample project for https://github.com/gradle/gradle/issues/1043.

* [BasePluginSuccessSpec](src/integTest/groovy/com/company/gradle/build/plugin/BasePluginSuccessSpec.groovy) with [build file](src/integTest/resources/com/company/gradle/build/plugin/BasePluginSuccessSpec/build.gradle) succeeds when using `DefaultGradleRunner.withJvmArguments`
* [BasePluginFailSpec](src/integTest/groovy/com/company/gradle/build/plugin/BasePluginFailSpec.groovy) with [build file](src/integTest/resources/com/company/gradle/build/plugin/BasePluginFailSpec/build.gradle) fails when adding systemProps to [gradle.properties](src/integTest/resources/com/company/gradle/build/plugin/BasePluginFailSpec/gradle.properties)

See the [build output](buildOutput.md)