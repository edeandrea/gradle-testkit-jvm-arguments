package com.company.gradle.build

import org.gradle.BuildAdapter
import org.gradle.api.invocation.Gradle
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging

final class SystemBuildListener extends BuildAdapter {
	private static final Logger LOGGER = Logging.getLogger(SystemBuildListener)

	@Override
	final void projectsLoaded(Gradle gradle) {
		printVersionMessage gradle
	}

	private void printVersionMessage(Gradle gradle) {
		LOGGER.lifecycle "\n-------------------------------------------------------------------------------"
		LOGGER.lifecycle "Packaged with core Gradle Distribution Version ${gradle.gradleVersion}"
		LOGGER.lifecycle "-------------------------------------------------------------------------------\n"
	}
}