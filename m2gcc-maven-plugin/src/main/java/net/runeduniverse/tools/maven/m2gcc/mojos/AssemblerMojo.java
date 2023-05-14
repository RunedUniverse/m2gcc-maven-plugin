package net.runeduniverse.tools.maven.m2gcc.mojos;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import net.runeduniverse.tools.maven.compiler.api.mojos.AAssemblerMojo;

/**
 * Assembler for GCC
 * @author VenaNocta
 *
 * @phase assembler
 * @goal assemble
 */
public class AssemblerMojo extends AAssemblerMojo {

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("m2gcc:assemble");
	}

}
