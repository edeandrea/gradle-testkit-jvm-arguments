package com.company.gradle.build.plugin

import spock.lang.Specification

import org.gradle.testkit.runner.BuildResult
import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import org.gradle.testkit.runner.UnexpectedBuildFailure

import org.junit.Rule
import org.junit.rules.TemporaryFolder

import org.apache.commons.io.FileUtils

abstract class BasePluginBaseSpec extends Specification {
	@Rule
	TemporaryFolder tempFolder

	protected File testDir

	protected GradleRunner configureGradleRunner(GradleRunner runner) {
		runner
	}

	def setup() {
		def resourceURL = getClass().getResource(getClass().simpleName)
		def sourceDir = new File(resourceURL.toURI())
		def integrationTestBaseDir = System.properties['INTEGRATION_TEST_BASE_DIR']
		this.testDir = this.tempFolder.folder

		if (integrationTestBaseDir != null) {
			testDir = new File(integrationTestBaseDir, getClass().simpleName)
		}

		println "Copying from $sourceDir to $testDir"
		FileUtils.copyDirectory sourceDir, this.testDir
	}

	def "Verify JVM Arguments"() {
		when:
			def buildResult = build 'verifyJvmParams'

		then:
			notThrown UnexpectedBuildFailure
			buildResult.task(':verifyJvmParams').outcome == TaskOutcome.SUCCESS
			buildResult.output.contains "Inside ${BasePlugin.class.name} for project ${getClass().simpleName}"
			buildResult.output.contains 'company.gradle.artifactory.dontResolvePluginsFromArtifactory = true'
			buildResult.output.contains 'company.gradle.skipUpdateCheck = true'
			buildResult.output.contains 'Got ClassNotFoundException trying to load class com.company.gradle.build.SystemBuildListener'
	}

	protected BuildResult build(String... commands) {
		def buildResult = createAndConfigureGradleRunner(commands).build()
		println buildResult.output

		buildResult
	}

	protected BuildResult buildAndFail(String... commands) {
		def buildResult = createAndConfigureGradleRunner(commands).buildAndFail()
		println buildResult.output

		buildResult
	}

	protected GradleRunner createAndConfigureGradleRunner(String... commands) {
		def distLocation = new File(System.properties['distLocation'])

		def runner = GradleRunner.create()
			.withProjectDir(this.testDir)
			.withPluginClasspath()

		configureGradleRunner distLocation.exists() ?
			runner
				.withGradleDistribution(distLocation.toURI())
				.withArguments(['-s'] + (commands as List)) :
			runner
				.withGradleVersion(System.properties['gradleVersion'])
				.withArguments((['-I', System.properties['initScriptLocation']] + (commands as List)) as String[])
	}
}