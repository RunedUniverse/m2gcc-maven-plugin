package net.runeduniverse.tools.maven.m2gcc.mojos.compiler;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import net.runeduniverse.tools.maven.compiler.api.mojos.ACompilerMojo;

/**
 * C Compiler form GCC
 * @author VenaNocta
 *
 * @phase compiler
 * @goal compile-c
 */
public class CompileCMojo extends ACompilerMojo {

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("m2gcc:compile-c");
	}

}
