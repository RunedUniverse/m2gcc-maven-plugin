package net.runeduniverse.tools.maven.m2gcc.mojos.preprocessor;

import java.util.Collection;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import net.runeduniverse.tools.maven.compiler.mojos.api.APreprocessorMojo;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Node;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Phase;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Pipeline;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Resource;
import net.runeduniverse.tools.maven.compiler.pipeline.api.ResourceRegistry;

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
	private ResourceRegistry registry;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("m2gcc-preprocessor:preprocess-c");
		Node node = this.pipeline.acquireNode(Phase.PREPROCESSOR, "c");

		Collection<Resource> resources = this.registry.selectBatch(node);

		getLog().info("Resources:");
		for (Resource resource : resources) {
			getLog().info("  " + resource.getFile()
					.getName());
		}
	}

}
