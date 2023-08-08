package net.runeduniverse.tools.maven.m2gcc.mojos.preprocessor;

import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import net.runeduniverse.tools.maven.compiler.mojos.api.APreprocessorMojo;
import net.runeduniverse.tools.maven.compiler.pipeline.api.NodeContext;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Phase;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Pipeline;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Resource;

/**
 * C Preprocessor from GCC
 * 
 * @author VenaNocta
 *
 * @phase preprocessor
 * @goal preprocess-c
 */
public class PreprocessCMojo extends APreprocessorMojo {

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
		getLog().info("m2gcc-preprocessor:preprocess-c");

		NodeContext conPreprocC = this.pipeline.getNodeContext(this.mvnSession, Phase.PREPROCESSOR, "c");
		getLog().info("Resources:");
		for (Resource resource : conPreprocC.getResources()) {
			getLog().info("  " + resource.getFile()
					.getName());
		}
	}

}
