package net.runeduniverse.tools.maven.m2gcc.mojos.linker;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * prints the help-page
 * 
 * @author VenaNocta
 * @goal help
 * @requiresProject false
 */
public class HelpMojo extends AbstractMojo {

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("");
		getLog().info("M2GCC Linker Help");
		getLog().info("");
		getLog().info(" m2gcc-linker:link-c");
		getLog().info(" m2gcc-linker:link-cpp");
	}

}
