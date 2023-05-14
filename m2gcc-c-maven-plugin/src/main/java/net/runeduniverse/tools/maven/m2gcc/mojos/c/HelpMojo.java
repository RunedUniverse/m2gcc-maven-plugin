package net.runeduniverse.tools.maven.m2gcc.mojos.c;

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
		getLog().info("M2GCC-C Help");
		getLog().info("");
		getLog().info(" m2gcc-c:help");
		getLog().info("     prints this help-page");
		getLog().info("");
		getLog().info(" m2gcc-c:preprocess");
		getLog().info("");
		getLog().info(" m2gcc-c:compile");
		getLog().info("");
		getLog().info(" m2gcc-c:assemble");
		getLog().info("");
		getLog().info(" m2gcc-c:link");
		getLog().info("");
		getLog().info("");
	}

}
