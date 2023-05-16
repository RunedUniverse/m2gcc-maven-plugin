package net.runeduniverse.tools.maven.m2gcc.mojos.assembler;

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
		getLog().info("M2GCC Assembler Help");
		getLog().info("");
		getLog().info(" net.runeduniverse.tools.maven.m2gcc:m2gcc-assembler-maven-plugin:assemble-c");
		getLog().info(" net.runeduniverse.tools.maven.m2gcc:m2gcc-assembler-maven-plugin:assemble-cpp");
		getLog().info("");
		getLog().info("");
	}

}
