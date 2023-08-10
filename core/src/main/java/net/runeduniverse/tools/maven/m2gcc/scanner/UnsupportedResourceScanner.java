package net.runeduniverse.tools.maven.m2gcc.scanner;

import org.apache.maven.plugin.logging.Log;
import org.codehaus.plexus.component.annotations.Component;
import net.runeduniverse.tools.maven.compiler.api.ResourceScanner;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Node;

@Component(role = ResourceScanner.class, hint = "m2gcc:unsupported")
public class UnsupportedResourceScanner extends Scanner implements ResourceScanner {

	// TODO - create pipeline in compiler-maven-extension
	// TODO - add api to add nodes (filetypes: c,i,ii,...)
	// TODO - create links between nodes (either in the ref scanner or when the
	// actual mojo executes)
	// TODO - in summuary/validation/ScanReferencesMojo check if it's fully linked
	// TODO - in mojos: 1. validate if compiler-lifecycle is present by optional
	// plexus linking
	// TODO - in mojos: 2. if compiler-lifecycle is not loaded do the defined step
	// to all files found in main/target

	private Node unsupported;

	@Override
	public void identifyNodes() {
		this.unsupported = this.pipeline.acquireNode(null, "unsupported");

		// Go source code.
		this.unsupported.registerResourceType(addType("go"));

		// D source code.
		this.unsupported.registerResourceType(addType("d"));
		// D interface file.
		this.unsupported.registerResourceType(addType("di"));
		// D documentation code (Ddoc).
		this.unsupported.registerResourceType(addType("dd"));

		// Ada source code file that contains a library unit declaration (a declaration
		// of a package, subprogram, or generic, or a generic instantiation), or a
		// library unit renaming declaration (a package, generic, or subprogram renaming
		// declaration). Such files are also called specs.
		this.unsupported.registerResourceType(addType("ads"));
		// Ada source code file containing a library unit body (a subprogram or package
		// body). Such files are also called bodies.
		this.unsupported.registerResourceType(addType("adb"));

		/* RESULTS */
		// precompiled header -> gch
	}

	@Override
	public boolean _scan() {
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
