package net.runeduniverse.tools.maven.m2gcc;

import java.io.File;

import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.logging.Log;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;

import net.runeduniverse.tools.maven.compiler.api.ICompilerRuntime;
import net.runeduniverse.tools.maven.compiler.api.IReferenceScanner;
import net.runeduniverse.tools.maven.compiler.mojos.api.SessionContextUtils;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Node;
import net.runeduniverse.tools.maven.compiler.pipeline.api.NodeContext;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Phase;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Pipeline;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Resource;
import net.runeduniverse.tools.maven.compiler.pipeline.api.ResourceType;

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

	private Node unsupported;
	private Node preprocessorHeader;
	private Node preprocessorC;
	private Node preprocessorCpp;
	private Node preprocessorObjC;
	private Node preprocessorObjCpp;
	private Node preprocessorFortran;
	private Node preprocessorAssembler;
	private Node compilerC;
	private Node compilerCpp;
	private Node compilerObjC;
	private Node compilerObjCpp;
	private Node compilerFortran;
	private Node assemblerAssembly;

	protected Log log;
	protected ICompilerRuntime runtime;

	@Override
	public void identifyNodes() {
		this.unsupported = this.pipeline.acquireNode(null, "unsupported");

		this.preprocessorHeader = this.pipeline.acquireNode(Phase.PREPROCESSOR, "header");
		this.preprocessorC = this.pipeline.acquireNode(Phase.PREPROCESSOR, "c");
		this.preprocessorCpp = this.pipeline.acquireNode(Phase.PREPROCESSOR, "cpp");
		this.preprocessorObjC = this.pipeline.acquireNode(Phase.PREPROCESSOR, "objc");
		this.preprocessorObjCpp = this.pipeline.acquireNode(Phase.PREPROCESSOR, "objcpp");
		this.preprocessorFortran = this.pipeline.acquireNode(Phase.PREPROCESSOR, "fortran");
		this.preprocessorAssembler = this.pipeline.acquireNode(Phase.PREPROCESSOR, "assembly");

		this.compilerC = this.pipeline.acquireNode(Phase.COMPILER, "c");
		this.compilerCpp = this.pipeline.acquireNode(Phase.COMPILER, "cpp");
		this.compilerObjC = this.pipeline.acquireNode(Phase.COMPILER, "objc");
		this.compilerObjCpp = this.pipeline.acquireNode(Phase.COMPILER, "objcpp");
		this.compilerFortran = this.pipeline.acquireNode(Phase.COMPILER, "fortran");

		this.assemblerAssembly = this.pipeline.acquireNode(Phase.ASSEMBLER, "assembly");

		// C source code that must be preprocessed.
		this.preprocessorC.registerResourceType(this.pipeline.acquireType("c"));
		// C source code that should not be preprocessed.
		this.compilerC.registerResourceType(this.pipeline.acquireType("i"));
		// C++ source code that should not be preprocessed.
		this.compilerCpp.registerResourceType(this.pipeline.acquireType("ii"));
		// Objective-C source code. Note that you must link with the libobjc library to
		// make an Objective-C program work.
		this.preprocessorObjC.registerResourceType(this.pipeline.acquireType("m"));
		// Objective-C source code that should not be preprocessed.
		this.compilerObjC.registerResourceType(this.pipeline.acquireType("mi"));
		// Objective-C++ source code. Note that you must link with the libobjc library
		// to make an Objective-C++ program work. Note that .M refers to a literal
		// capital M.
		this.preprocessorObjCpp.registerResourceTypes(this.pipeline.acquireTypes("mm", "M"));
		// Objective-C++ source code that should not be preprocessed.
		this.compilerObjCpp.registerResourceType(this.pipeline.acquireType("mii"));
		// C, C++, Objective-C or Objective-C++ header file to be turned into a
		// precompiled header (default), or C, C++ header file to be turned into an Ada
		// spec (via the -fdump-ada-spec switch).
		this.preprocessorHeader.registerResourceType(this.pipeline.acquireType("h"));
		// C++ source code that must be preprocessed. Note that in .cxx, the last two
		// letters must both be literally x. Likewise, .C refers to a literal capital C.
		this.preprocessorCpp
				.registerResourceTypes(this.pipeline.acquireTypes("cc", "cp", "cxx", "cpp", "CPP", "c++", "C"));
		// Objective-C++ source code that must be preprocessed.
		this.preprocessorObjCpp.registerResourceTypes(this.pipeline.acquireTypes("mm", "M"));
		// Objective-C++ source code that should not be preprocessed.
		this.compilerObjCpp.registerResourceType(this.pipeline.acquireType("mii"));
		// C++ header file to be turned into a precompiled header or Ada spec.
		this.preprocessorHeader
				.registerResourceTypes(this.pipeline.acquireTypes("hh", "H", "hp", "hxx", "hpp", "HPP", "h++", "tcc"));

		// Fixed form Fortran source code that should not be preprocessed.
		this.compilerFortran.registerResourceTypes(this.pipeline.acquireTypes("f", "for", "ftn"));
		// Fixed form Fortran source code that must be preprocessed (with the
		// traditional preprocessor).
		this.preprocessorFortran.registerResourceTypes(this.pipeline.acquireTypes("F", "FOR", "fpp", "FPP", "FTN"));
		// Free form Fortran source code that should not be preprocessed.
		this.compilerFortran.registerResourceTypes(this.pipeline.acquireTypes("f90", "f95", "f03", "f08"));
		// Free form Fortran source code that must be preprocessed (with the traditional
		// preprocessor).
		this.preprocessorFortran.registerResourceTypes(this.pipeline.acquireTypes("F90", "F95", "F03", "F08"));

		// Go source code.
		this.unsupported.registerResourceType(this.pipeline.acquireType("go"));

		// D source code.
		this.unsupported.registerResourceType(this.pipeline.acquireType("d"));
		// D interface file.
		this.unsupported.registerResourceType(this.pipeline.acquireType("di"));
		// D documentation code (Ddoc).
		this.unsupported.registerResourceType(this.pipeline.acquireType("dd"));

		// Ada source code file that contains a library unit declaration (a declaration
		// of a package, subprogram, or generic, or a generic instantiation), or a
		// library unit renaming declaration (a package, generic, or subprogram renaming
		// declaration). Such files are also called specs.
		this.unsupported.registerResourceType(this.pipeline.acquireType("ads"));
		// Ada source code file containing a library unit body (a subprogram or package
		// body). Such files are also called bodies.
		this.unsupported.registerResourceType(this.pipeline.acquireType("adb"));

		// Assembler code.
		this.assemblerAssembly.registerResourceType(this.pipeline.acquireType("s"));
		// Assembler code that must be preprocessed.
		this.preprocessorAssembler.registerResourceTypes(this.pipeline.acquireTypes("S", "sx"));

		/* RESULTS */
		// precompiled header -> gch
	}

	@Override
	public boolean scan() {
		this.log = SessionContextUtils.lookupSessionComponent(this.mvnSession, Log.class);
		this.runtime = SessionContextUtils.lookupSessionComponent(this.mvnSession, ICompilerRuntime.class);

		NodeContext conPreprocC = this.pipeline.getNodeContext(this.mvnSession, Phase.PREPROCESSOR, "c");
		ResourceType resourceTypeC = this.pipeline.acquireType("c");

		File cDir = new File(this.runtime.getSourceDirectory(), "c");
		if (cDir.exists() && cDir.isDirectory() && cDir.canRead()) {
			for (File file : cDir.listFiles()) {
				Resource resource = this.pipeline.createResource(resourceTypeC);
				conPreprocC.addResource(resource.setFile(file));
			}
		}

		log.info("Scanner: Preprocessor  - Hello World!");
		log.info("scanning src files: " + this.runtime.getSourceDirectory());

		log.info("");
		log.info("Pipeline: " + this.pipeline.toRecord());
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean logAnalisis(Log log) {
		// TODO Auto-generated method stub
		return false;
	}

}
