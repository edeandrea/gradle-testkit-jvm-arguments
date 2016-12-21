package com.aig.gradle.build.temp

import spock.lang.Specification

import org.gradle.api.Project
import org.gradle.api.invocation.Gradle
import org.gradle.testfixtures.ProjectBuilder

/**
 * Base class for unit tests
 *
 * @author Eric Deandrea November 2016
 */
abstract class AbstractUnitTester extends Specification {
	def getProject(String rootProjectName, String... childProjects) {
		def projBuilder = ProjectBuilder.builder()
		def root = projBuilder.withName(rootProjectName).build()

		if (childProjects) {
			childProjects.each {
				def childProject = projBuilder.withName(it).withParent(root).build()
				childProject.projectDir.mkdirs()
			}
		}

		root
	}

	protected void configureProject(Project proj) {
		proj.ext.setProperty 'projectName', proj.name
		proj.ext.setProperty 'artifactId', "test-$proj.name"
		proj.group 'com.aig'
	}

	def runProjectAfterEvaluate(Project project) {
		project.projectEvaluationBroadcaster.afterEvaluate(project, null)
	}

	def runGradleProjectsEvaluated(Project project) {
		runGradleProjectsEvaluated(project.gradle)
	}

	def runGradleProjectsEvaluated(Gradle gradle) {
		gradle.buildListenerBroadcaster.projectsEvaluated(gradle)
	}

	protected Throwable findException(Throwable root, Class<Throwable> clazz) {
		if (root == null) {
			assert false : "Couldn't find exception of type $clazz.name"
		}

		clazz.isInstance(root) ? root : findException(root.cause, clazz)
	}

	protected List<Throwable> getExceptionChain(Throwable root) {
		(root != null) ? [root] + getExceptionChain(root.cause) : []
	}

	protected Throwable getRootCause(Throwable t) {
		getExceptionChain(t)[-1]
	}
}