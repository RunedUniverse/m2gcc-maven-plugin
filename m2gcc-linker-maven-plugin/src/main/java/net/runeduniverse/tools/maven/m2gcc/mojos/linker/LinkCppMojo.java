package net.runeduniverse.tools.maven.m2gcc.mojos.linker;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import net.runeduniverse.tools.maven.compiler.mojos.api.ALinkerMojo;

/**
 * Cpp Linker from GCC
 * 
 * @author VenaNocta
 *
 * @phase linker
 * @goal link-cpp
 */
public class LinkCppMojo extends ALinkerMojo {

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("m2gcc-linker:link-cpp");
	}

}
