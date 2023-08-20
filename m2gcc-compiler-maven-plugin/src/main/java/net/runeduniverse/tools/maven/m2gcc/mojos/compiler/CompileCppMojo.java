package net.runeduniverse.tools.maven.m2gcc.mojos.compiler;

import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import net.runeduniverse.tools.maven.compiler.api.CompilerRuntime;
import net.runeduniverse.tools.maven.compiler.mojos.api.SessionContextUtils;
import net.runeduniverse.tools.maven.compiler.pipeline.api.NodeContext;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Phase;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Pipeline;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Resource;

/**
 * Cpp Compiler from GCC
 *
 * @author VenaNocta
 *
 * @phase compiler
 * @goal compile-cpp
 */
public class CompileCppMojo extends AbstractMojo {

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
		final CompilerRuntime runtime = SessionContextUtils.lookupSessionComponent(this.mvnSession,
				CompilerRuntime.class);
		getLog().info("m2gcc-compiler:compile-cpp");

		NodeContext context = this.pipeline.getNodeContext(this.mvnSession, Phase.COMPILER, "cpp");
		getLog().info("Resources:");
		for (Resource resource : context.getResources()) {
			getLog().info("  " + resource.getFile()
					.getName());
		}
	}

}
