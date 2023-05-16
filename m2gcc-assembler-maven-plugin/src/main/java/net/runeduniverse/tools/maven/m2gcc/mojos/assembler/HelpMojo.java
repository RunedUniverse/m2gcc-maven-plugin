package net.runeduniverse.tools.maven.m2gcc.mojos.assembler;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;

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
		printInfo(getLog());
		getLog().info("");
		getLog().info("");
	}

	public static void printInfo(Log log) {
		log.info(" m2gcc-assembler:assemble-c");
		log.info(" m2gcc-assembler:assemble-cpp");
	}

}
