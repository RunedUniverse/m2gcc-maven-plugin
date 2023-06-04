package net.runeduniverse.tools.maven.m2gcc.mojos.preprocessor;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import net.runeduniverse.tools.maven.compiler.mojos.api.APreprocessorMojo;

/**
 * C Preprocessor from GCC
 * @author VenaNocta
 *
 * @phase preprocessor
 * @goal preprocess-c
 */
public class PreprocessCMojo extends APreprocessorMojo{

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("m2gcc-preprocessor:preprocess-c");
	}

}
