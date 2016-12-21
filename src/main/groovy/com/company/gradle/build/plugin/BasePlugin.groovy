package com.company.gradle.build.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

final class BasePlugin implements Plugin<Project> {
	@Override
	final void apply(Project project) {
		project.logger.lifecycle "Inside ${getClass().name} for project $project.name"
	}
}