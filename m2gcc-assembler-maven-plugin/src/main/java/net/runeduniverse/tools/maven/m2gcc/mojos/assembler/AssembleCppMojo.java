package net.runeduniverse.tools.maven.m2gcc.mojos.assembler;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import net.runeduniverse.tools.maven.compiler.api.mojos.AAssemblerMojo;

/**
 * Cpp Assembler from GCC
 * @author VenaNocta
 *
 * @phase assembler
 * @goal assemble-cpp
 */
public class AssembleCppMojo extends AAssemblerMojo {

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("m2gcc-assembler:assemble-cpp");
	}

}
