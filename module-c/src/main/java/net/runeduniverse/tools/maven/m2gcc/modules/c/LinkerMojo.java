package net.runeduniverse.tools.maven.m2gcc.modules.c;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import net.runeduniverse.tools.maven.compiler.api.mojos.ALinkerMojo;

/**
 * Assembler for GCC
 * @author VenaNocta
 *
 * @phase linker
 * @goal link
 */
public class LinkerMojo extends ALinkerMojo {

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("m2gcc-c:link");
	}

}
