package net.runeduniverse.tools.maven.m2gcc.scanner;

import org.codehaus.plexus.component.annotations.Component;

import net.runeduniverse.tools.maven.compiler.api.PipelineInitializer;
import net.runeduniverse.tools.maven.compiler.mojos.api.AbstractInitializer;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Node;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Phase;

@Component(role = PipelineInitializer.class, hint = "m2gcc:objc")
public class ObjCScanner extends AbstractInitializer implements PipelineInitializer {

	private Node preprocessorObjC;
	private Node compilerObjC;

	@Override
	public void _initialize() {
		this.preprocessorObjC = this.pipeline.acquireNode(Phase.PREPROCESSOR, "objc");
		this.compilerObjC = this.pipeline.acquireNode(Phase.COMPILER, "objc");
		this.pipeline.acquireNode(Phase.LINKER, "objc");

		// Objective-C source code. Note that you must link with the libobjc library to
		// make an Objective-C program work.
		this.preprocessorObjC.registerResourceType(addType("m"));
		// Objective-C source code that should not be preprocessed.
		this.compilerObjC.registerResourceType(addType("mi"));
	}

}
