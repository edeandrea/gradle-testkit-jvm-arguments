package com.company.gradle.build.plugin

import org.gradle.testkit.runner.GradleRunner

class BasePluginSuccessSpec extends BasePluginBaseSpec {
	@Override
	protected GradleRunner configureGradleRunner(GradleRunner runner) {
		runner.withJvmArguments('-Dcompany.gradle.artifactory.dontResolvePluginsFromArtifactory=true', '-Dcompany.gradle.skipUpdateCheck=true')
	}
}