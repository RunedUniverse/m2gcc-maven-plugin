package net.runeduniverse.tools.maven.m2gcc.mojos.preprocessor;

import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import net.runeduniverse.lib.utils.maven.SessionContextUtils;
import net.runeduniverse.tools.maven.compiler.api.CompilerRuntime;
import net.runeduniverse.tools.maven.compiler.pipeline.api.NodeContext;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Phase;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Pipeline;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Resource;

/**
 * Cpp Preprocessor from GCC
 *
 * @author VenaNocta
 *
 * @phase preprocessor
 * @goal preprocess-cpp
 */
public class PreprocessCppMojo extends AbstractMojo {

	/**
	 * @component
	 */
	private Pipeline pipeline;

	/**
	 * @component
	 */
	private MavenSession mvnSession;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		final CompilerRuntime runtime = SessionContextUtils.loadSessionComponent(this.mvnSession,
				CompilerRuntime.class);
		getLog().info("m2gcc-preprocessor:preprocess-cpp");

		NodeContext context = this.pipeline.getNodeContext(this.mvnSession, Phase.PREPROCESSOR, "cpp");
		getLog().info("Resources:");
		for (Resource resource : context.getResources()) {
			getLog().info("  " + resource.getFile()
					.getName());
		}
	}

}
