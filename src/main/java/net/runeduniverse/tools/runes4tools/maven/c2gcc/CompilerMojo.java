package net.runeduniverse.tools.runes4tools.maven.c2gcc;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import net.runeduniverse.tools.maven.compiler.api.mojo.ACompilerMojo;

/**
 * Assembler for GCC
 * @author VenaNocta
 *
 * @phase compiler
 * @goal compile
 */
public class CompilerMojo extends ACompilerMojo {

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("c2gcc:compile");
	}

}
