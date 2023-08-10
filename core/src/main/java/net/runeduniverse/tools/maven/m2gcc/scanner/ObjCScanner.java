package net.runeduniverse.tools.maven.m2gcc.scanner;

import org.apache.maven.plugin.logging.Log;
import org.codehaus.plexus.component.annotations.Component;

import net.runeduniverse.tools.maven.compiler.api.ResourceScanner;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Node;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Phase;

@Component(role = ResourceScanner.class, hint = "m2gcc:objc")
public class ObjCScanner extends Scanner implements ResourceScanner {

	private Node preprocessorObjC;
	private Node compilerObjC;

	@Override
	public void identifyNodes() {
		this.preprocessorObjC = this.pipeline.acquireNode(Phase.PREPROCESSOR, "objc");
		this.compilerObjC = this.pipeline.acquireNode(Phase.COMPILER, "objc");

		// Objective-C source code. Note that you must link with the libobjc library to
		// make an Objective-C program work.
		this.preprocessorObjC.registerResourceType(addType("m"));
		// Objective-C source code that should not be preprocessed.
		this.compilerObjC.registerResourceType(addType("mi"));
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
