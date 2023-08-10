package net.runeduniverse.tools.maven.m2gcc.scanner;

import org.apache.maven.plugin.logging.Log;
import org.codehaus.plexus.component.annotations.Component;

import net.runeduniverse.tools.maven.compiler.api.ResourceScanner;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Node;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Phase;

@Component(role = ResourceScanner.class, hint = "m2gcc:fortran")
public class FortranScanner extends Scanner implements ResourceScanner {

	private Node preprocessorFortran;
	private Node compilerFortran;

	@Override
	public void identifyNodes() {
		this.preprocessorFortran = this.pipeline.acquireNode(Phase.PREPROCESSOR, "fortran");
		this.compilerFortran = this.pipeline.acquireNode(Phase.COMPILER, "fortran");

		// Fixed form Fortran source code that should not be preprocessed.
		this.compilerFortran.registerResourceTypes(addTypes("f", "for", "ftn"));
		// Fixed form Fortran source code that must be preprocessed (with the
		// traditional preprocessor).
		this.preprocessorFortran.registerResourceTypes(addTypes("F", "FOR", "fpp", "FPP", "FTN"));
		// Free form Fortran source code that should not be preprocessed.
		this.compilerFortran.registerResourceTypes(addTypes("f90", "f95", "f03", "f08"));
		// Free form Fortran source code that must be preprocessed (with the traditional
		// preprocessor).
		this.preprocessorFortran.registerResourceTypes(addTypes("F90", "F95", "F03", "F08"));
	}

	@Override
	protected boolean _scan() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean logAnalisis(Log log) {
		// TODO Auto-generated method stub
		return false;
	}

}
