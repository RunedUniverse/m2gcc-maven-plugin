package net.runeduniverse.tools.runes4tools.maven.c2gcc;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import net.runeduniverse.tools.runes4tools.maven.runes4maven.lifecycles.builder.mojos.APreprocessorMojo;

/**
 * Assembler for GCC
 * @author Pl4yingNight
 *
 * @phase builder-preprocessor
 * @goal preprocess
 */
public class PreprocessorMojo extends APreprocessorMojo{

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("c2gcc:preprocess");
	}

}
