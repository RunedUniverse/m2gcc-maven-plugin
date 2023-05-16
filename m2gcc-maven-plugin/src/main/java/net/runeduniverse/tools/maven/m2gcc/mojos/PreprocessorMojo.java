package net.runeduniverse.tools.maven.m2gcc.mojos;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import net.runeduniverse.tools.maven.compiler.api.mojos.APreprocessorMojo;

/**
 * Assembler for GCC
 * @author VenaNocta
 *
 * @phase preprocessor
 * @goal preprocess
 */
public class PreprocessorMojo extends APreprocessorMojo{

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("m2gcc:preprocess");
	}

}