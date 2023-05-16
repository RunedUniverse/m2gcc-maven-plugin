package net.runeduniverse.tools.maven.m2gcc.mojos;

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
		getLog().info("M2GCC Help");
		getLog().info("");
		getLog().info(" m2gcc:help");
		getLog().info("     prints this help-page");
		getLog().info("");
		net.runeduniverse.tools.maven.m2gcc.mojos.preprocessor.HelpMojo.printInfo(getLog());
		getLog().info("");
		net.runeduniverse.tools.maven.m2gcc.mojos.compiler.HelpMojo.printInfo(getLog());
		getLog().info("");
		net.runeduniverse.tools.maven.m2gcc.mojos.assembler.HelpMojo.printInfo(getLog());
		getLog().info("");
		net.runeduniverse.tools.maven.m2gcc.mojos.linker.HelpMojo.printInfo(getLog());
		getLog().info("");
		getLog().info("");
	}

}
