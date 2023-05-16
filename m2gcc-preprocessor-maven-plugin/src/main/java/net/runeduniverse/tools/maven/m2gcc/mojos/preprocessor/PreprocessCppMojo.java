package net.runeduniverse.tools.maven.m2gcc.mojos.preprocessor;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import net.runeduniverse.tools.maven.compiler.api.mojos.APreprocessorMojo;

/**
 * Cpp Preprocessor from GCC
 * @author VenaNocta
 *
 * @phase preprocessor
 * @goal preprocess-cpp
 */
public class PreprocessCppMojo extends APreprocessorMojo{

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("m2gcc:preprocess-cpp");
	}

}
