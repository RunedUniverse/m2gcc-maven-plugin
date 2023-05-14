package net.runeduniverse.tools.maven.m2gcc.mojos.cpp;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import net.runeduniverse.tools.maven.compiler.api.mojos.ACompilerMojo;

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
		getLog().info("cpp2gcc:compile");
	}

}
