package com.aig.gradle.build.temp.plugin

import org.gradle.api.Project
import org.gradle.testing.jacoco.tasks.JacocoReport

import com.aig.gradle.build.temp.AbstractUnitTester
import com.aig.gradle.build.temp.plugin.IntegrationTestPlugin

/**
 * Unit tests for the IntegrationTestPlugin
 *
 * @author Eric Deandrea December 2012
 */
class IntegrationTestPluginUnitTester extends AbstractUnitTester {
	private Project project = getProject("IntegrationTestPlugin")

	def setup() {
		configureProject this.project
		this.project.apply plugin: 'java'
		this.project.apply plugin: 'eclipse'
		this.project.apply plugin: IntegrationTestPlugin
	}

	def getIntegTestSourceSet = { this.project.sourceSets.findByName('integTest') }

	def "integration test plugin is applied"() {
		expect:
		this.project.plugins.findPlugin(IntegrationTestPlugin) != null
	}

	def "integTest source set added"() {
		expect:
		this.project.sourceSets != null
		getIntegTestSourceSet() != null
	}

	def "integTest configurations added"() {
		expect:
		this.project.configurations.findByName('integTestCompile') != null
		this.project.configurations.findByName('integTestRuntime') != null
	}

	def "integTest source set source dirs are set up properly for java plugin"() {
		setup:
		def sourceSet = getIntegTestSourceSet()

		expect:
		sourceSet != null
		sourceSet.java != null
		sourceSet.java.srcDirs != null
		sourceSet.java.srcDirs.contains(this.project.file('src/integTest/java'))
		sourceSet.resources != null
		sourceSet.resources.srcDirs != null
		sourceSet.resources.srcDirs.contains(this.project.file('src/integTest/resources'))
	}

	def "integTest source set source dirs are set up properly for groovy plugin"() {
		setup:
		this.project.apply plugin: 'groovy'
		def sourceSet = getIntegTestSourceSet()

		expect:
		sourceSet != null
		sourceSet.groovy != null
		sourceSet.groovy.srcDirs != null
		sourceSet.groovy.srcDirs.contains(this.project.file('src/integTest/groovy'))
	}

	def "integTest soruce set classpaths are set up properly"() {
		setup:
		def sourceSet = getIntegTestSourceSet()

		expect:
		sourceSet != null

		def compileClasspath = sourceSet.compileClasspath
		def runtimeClasspath = sourceSet.runtimeClasspath

		compileClasspath != null
		compileClasspath.files.containsAll(this.project.sourceSets.main.output.files)
		compileClasspath.files.containsAll(this.project.sourceSets.test.output.files)
		runtimeClasspath != null
		runtimeClasspath.files.containsAll(compileClasspath.files)
	}

	def "integTest task exists and is configured properly"() {
		setup:
		def task = this.project.tasks.findByName('integTest')

		expect:
		task != null
		task.systemProperties['INTEGRATION_TEST_BASE_DIR'] == "$project.buildDir/integTests"
		task.testClassesDir == this.project.sourceSets.integTest.output.classesDir
		task.classpath == this.project.sourceSets.integTest.runtimeClasspath
		task.jacoco.destinationFile == this.project.file("$project.buildDir/coverage-results/integration/jacoco.exec")
	}

	def "generateIntegrationCoverageReport task exists and is configured properly"() {
		setup:
		def task = this.project.tasks.findByName('generateIntegrationCoverageReport')

		expect:
		task != null
		task instanceof JacocoReport
		!task.reports.xml.enabled
		!task.reports.csv.enabled
		task.reports.html.destination == this.project.file("$project.buildDir/reports/test-integration-coverage")
		task.getDependsOn().contains('integTest')
	}

	def "generateIntegrationCoverageReport task should be enabled"() {
		when:
		runProjectAfterEvaluate(this.project)
		def genCovRptTask = this.project.tasks.findByName('generateIntegrationCoverageReport')

		then:
		noExceptionThrown()
		genCovRptTask != null
		genCovRptTask.enabled
	}

	def "check task has correct dependencies"() {
		setup:
		def checkTask = this.project.tasks.findByName('check')

		expect:
		checkTask != null
		checkTask.getDependsOn().containsAll([this.project.tasks.findByName('integTest'), this.project.tasks.findByName('generateIntegrationCoverageReport')])
	}

	def "Eclipse plugin configuration contains integration test stuff"() {
		expect:
		this.project.eclipse != null
		this.project.eclipse.classpath != null
		this.project.eclipse.classpath.plusConfigurations.containsAll([this.project.configurations.integTestCompile, this.project.configurations.integTestRuntime])
	}
}