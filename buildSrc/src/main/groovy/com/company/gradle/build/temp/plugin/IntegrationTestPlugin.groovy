package com.company.gradle.build.temp.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.GroovyBasePlugin
import org.gradle.api.tasks.testing.Test
import org.gradle.plugins.ide.eclipse.EclipsePlugin
import org.gradle.testing.jacoco.tasks.JacocoReport

final class IntegrationTestPlugin implements Plugin<Project> {
	private static final VERIFICATION_TASKS_GROUP = 'Verification'
	private static final GENERATE_COVERAGE_REPORT_TASK_NAME = 'generateIntegrationCoverageReport'
	private static final INTEGRATION_TEST_TASK_NAME = 'integTest'

	private Project project

	@Override
	final void apply(Project project) {
		this.project = project
		
		project.apply plugin: 'jacoco'
		
		configureDependencies()

		project.sourceSets.create('integTest') {
			java.srcDir project.file('src/integTest/java')
			resources.srcDir project.file('src/integTest/resources')
			compileClasspath += project.sourceSets.main.output + project.sourceSets.test.output
			runtimeClasspath += compileClasspath

			project.plugins.withType(GroovyBasePlugin) {
				groovy.srcDir project.file('src/integTest/groovy')
			}
		}

		project.dependencies {
			integTestCompile project.configurations.testCompile
			integTestRuntime project.configurations.testRuntime
		}

		// Now add these configurations to the eclipse plugin
		if (project.plugins.withType(EclipsePlugin)) {
			project.eclipse.classpath {
				plusConfigurations += [project.configurations.integTestCompile]
				plusConfigurations += [project.configurations.integTestRuntime]
			}
		}

		def integTestTask = project.task(INTEGRATION_TEST_TASK_NAME, type: Test, dependsOn: 'jar', group: VERIFICATION_TASKS_GROUP, description: 'Run the integration tests') {
			shouldRunAfter 'test'
			testClassesDir = project.sourceSets.integTest.output.classesDir
			classpath = project.sourceSets.integTest.runtimeClasspath
			systemProperty 'INTEGRATION_TEST_BASE_DIR', "$project.buildDir/integTests"
			maxHeapSize '512m'
			minHeapSize '128m'
			binResultsDir project.file("$project.buildDir/test-results/$project.sourceSets.integTest.name/binary")
			jvmArgs '-XX:MaxPermSize=128m'

			reports {
				html.destination project.file("$project.reporting.baseDir/tests/$project.sourceSets.integTest.name")
				junitXml.destination project.file("$project.buildDir/test-results/$project.sourceSets.integTest.name")
			}

			jacoco {
				destinationFile = project.file("$project.buildDir/coverage-results/integration/jacoco.exec")
			}
		}

		def genCovRptTask = project.task(GENERATE_COVERAGE_REPORT_TASK_NAME, type: JacocoReport, description: 'Generates integration test coverage report', dependsOn: INTEGRATION_TEST_TASK_NAME) {
			onlyIf { integTestTask.enabled }
			sourceSets project.sourceSets.integTest
			executionData integTestTask

			reports {
				xml.enabled false
				csv.enabled false
				html.destination project.file("$project.buildDir/reports/test-integration-coverage")
			}
		}

		project.tasks['check'].dependsOn project.tasks[INTEGRATION_TEST_TASK_NAME], genCovRptTask
	}
	
	private void configureDependencies() {
		def jacocoVersion = '0.7.7.201606060606'

		project.dependencies {
			jacocoAgent "org.jacoco:org.jacoco.agent:$jacocoVersion"
			jacocoAnt "org.jacoco:org.jacoco.ant:$jacocoVersion"
		}
	}
}