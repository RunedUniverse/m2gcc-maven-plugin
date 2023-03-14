package net.runeduniverse.tools.runes4tools.maven.c2gcc;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import net.runeduniverse.tools.maven.compiler.api.mojo.AAssemblerMojo;

/**
 * Assembler for GCC
 * @author Pl4yingNight
 *
 * @phase assembler
 * @goal assemble
 */
public class AssemblerMojo extends AAssemblerMojo {

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("c2gcc:assemble");
	}

}
