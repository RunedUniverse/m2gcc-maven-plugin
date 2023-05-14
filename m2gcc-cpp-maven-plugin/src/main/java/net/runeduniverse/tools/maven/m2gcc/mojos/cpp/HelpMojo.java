package net.runeduniverse.tools.maven.m2gcc.mojos.cpp;

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
		getLog().info("M2GCC-CPP Help");
		getLog().info("");
		getLog().info(" m2gcc-cpp:help");
		getLog().info("     prints this help-page");
		getLog().info("");
		getLog().info(" m2gcc-cpp:preprocess");
		getLog().info("");
		getLog().info(" m2gcc-cpp:compile");
		getLog().info("");
		getLog().info(" m2gcc-cpp:assemble");
		getLog().info("");
		getLog().info(" m2gcc-cpp:link");
		getLog().info("");
		getLog().info("");
	}

}
