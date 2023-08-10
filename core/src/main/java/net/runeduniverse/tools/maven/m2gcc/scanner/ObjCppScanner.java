package net.runeduniverse.tools.maven.m2gcc.scanner;

import org.apache.maven.plugin.logging.Log;
import org.codehaus.plexus.component.annotations.Component;

import net.runeduniverse.tools.maven.compiler.api.ResourceScanner;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Node;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Phase;

@Component(role = ResourceScanner.class, hint = "m2gcc:objcpp")
public class ObjCppScanner extends Scanner implements ResourceScanner {

	private Node preprocessorObjCpp;
	private Node compilerObjCpp;

	@Override
	public void identifyNodes() {
		this.preprocessorObjCpp = this.pipeline.acquireNode(Phase.PREPROCESSOR, "objcpp");
		this.compilerObjCpp = this.pipeline.acquireNode(Phase.COMPILER, "objcpp");

		// Objective-C++ source code. Note that you must link with the libobjc library
		// to make an Objective-C++ program work. Note that .M refers to a literal
		// capital M.
		// Objective-C++ source code that must be preprocessed.
		this.preprocessorObjCpp.registerResourceTypes(addTypes("mm", "M"));
		// Objective-C++ source code that should not be preprocessed.
		this.compilerObjCpp.registerResourceType(addType("mii"));
	}

	@Override
	protected boolean _scan() {
		return false;
	}

	@Override
	public boolean logAnalisis(Log log) {
		// TODO Auto-generated method stub
		return false;
	}

}
