package net.runeduniverse.tools.maven.m2gcc.mojos.compiler;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import net.runeduniverse.tools.maven.compiler.api.mojos.ACompilerMojo;

/**
 * Cpp Compiler from GCC
 * @author VenaNocta
 *
 * @phase compiler
 * @goal compile-cpp
 */
public class CompileCppMojo extends ACompilerMojo {

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("m2gcc-compiler:compile-cpp");
	}

}
