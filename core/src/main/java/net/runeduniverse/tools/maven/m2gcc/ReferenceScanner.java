package net.runeduniverse.tools.maven.m2gcc;

import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.logging.Log;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;

import net.runeduniverse.tools.maven.compiler.api.ICompilerRuntime;
import net.runeduniverse.tools.maven.compiler.api.IReferenceScanner;
import net.runeduniverse.tools.maven.compiler.mojos.api.CurrentContextUtils;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Pipeline;

@Component(role = IReferenceScanner.class, hint = "m2gcc")
public class ReferenceScanner implements IReferenceScanner {

	// TODO - create pipeline in compiler-maven-extension
	// TODO - add api to add nodes (filetypes: c,i,ii,...)
	// TODO - create links between nodes (either in the ref scanner or when the
	// actual mojo executes)
	// TODO - in summuary/validation/ScanReferencesMojo check if it's fully linked
	// TODO - in mojos: 1. validate if compiler-lifecycle is present by optional
	// plexus linking
	// TODO - in mojos: 2. if compiler-lifecycle is not loaded do the defined step
	// to all files found in main/target

	@Requirement
	private MavenSession mvnSession;

	@Requirement
	private Pipeline pipeline;

	protected Log log;
	protected ICompilerRuntime runtime;

	@Override
	public void identifyNodes() {
		// C source code that must be preprocessed.
		this.pipeline.registerNode("c");
		// C source code that should not be preprocessed.
		this.pipeline.registerNode("i");
		// C++ source code that should not be preprocessed.
		this.pipeline.registerNode("ii");
		// Objective-C source code. Note that you must link with the libobjc library to
		// make an Objective-C program work.
		this.pipeline.registerNode("m");
		// Objective-C source code that should not be preprocessed.
		this.pipeline.registerNode("mi");
		// Objective-C++ source code. Note that you must link with the libobjc library
		// to make an Objective-C++ program work. Note that .M refers to a literal
		// capital M.
		this.pipeline.registerNodesAsAliases("mm", "M");
		// Objective-C++ source code that should not be preprocessed.
		this.pipeline.registerNode("mii");
		// C, C++, Objective-C or Objective-C++ header file to be turned into a
		// precompiled header (default), or C, C++ header file to be turned into an Ada
		// spec (via the -fdump-ada-spec switch).
		this.pipeline.registerNode("h");
		// C++ source code that must be preprocessed. Note that in .cxx, the last two
		// letters must both be literally x. Likewise, .C refers to a literal capital C.
		this.pipeline.registerNodesAsAliases("cc", "cp", "cxx", "cpp", "CPP", "c++", "C");
		// Objective-C++ source code that must be preprocessed.
		this.pipeline.registerNodesAsAliases("mm", "M");
		// Objective-C++ source code that should not be preprocessed.
		this.pipeline.registerNode("mii");
		// C++ header file to be turned into a precompiled header or Ada spec.
		this.pipeline.registerNodesAsAliases("hh", "H", "hp", "hxx", "hpp", "HPP", "h++", "tcc");

		// Fixed form Fortran source code that should not be preprocessed.
		this.pipeline.registerNodesAsAliases("f", "for", "ftn");
		// Fixed form Fortran source code that must be preprocessed (with the
		// traditional preprocessor).
		this.pipeline.registerNodesAsAliases("F", "FOR", "fpp", "FPP", "FTN");
		// Free form Fortran source code that should not be preprocessed.
		this.pipeline.registerNodesAsAliases("f90", "f95", "f03", "f08");
		// Free form Fortran source code that must be preprocessed (with the traditional
		// preprocessor).
		this.pipeline.registerNodesAsAliases("F90", "F95", "F03", "F08");

		// Go source code.
		this.pipeline.registerNode("go");

		// D source code.
		this.pipeline.registerNode("d");
		// D interface file.
		this.pipeline.registerNode("di");
		// D documentation code (Ddoc).
		this.pipeline.registerNode("dd");

		// Ada source code file that contains a library unit declaration (a declaration
		// of a package, subprogram, or generic, or a generic instantiation), or a
		// library unit renaming declaration (a package, generic, or subprogram renaming
		// declaration). Such files are also called specs.
		this.pipeline.registerNode("ads");
		// Ada source code file containing a library unit body (a subprogram or package
		// body). Such files are also called bodies.
		this.pipeline.registerNode("adb");

		// Assembler code.
		this.pipeline.registerNode("s");
		// Assembler code that must be preprocessed.
		this.pipeline.registerNodesAsAliases("S", "sx");
	}

	@Override
	public boolean scan() {
		this.log = CurrentContextUtils.lookupComponent(this.mvnSession, Log.class);
		this.runtime = CurrentContextUtils.lookupComponent(this.mvnSession, ICompilerRuntime.class);

		log.info("Scanner: Preprocessor  - Hello World!");
		log.info("scanning src files: " + this.runtime.getSourceDirectory());
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean logAnalisis(Log log) {
		// TODO Auto-generated method stub
		return false;
	}

}
