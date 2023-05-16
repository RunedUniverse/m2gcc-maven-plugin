package net.runeduniverse.tools.maven.m2gcc.mojos.linker;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import net.runeduniverse.tools.maven.compiler.api.mojos.ALinkerMojo;

/**
 * C Linker form GCC
 * @author VenaNocta
 *
 * @phase linker
 * @goal link-c
 */
public class LinkCMojo extends ALinkerMojo {

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("m2gcc:link-c");
	}

}
