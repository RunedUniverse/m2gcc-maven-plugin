package net.runeduniverse.tools.maven.m2gcc.scanner;

import org.codehaus.plexus.component.annotations.Component;

import net.runeduniverse.tools.maven.compiler.api.PipelineInitializer;
import net.runeduniverse.tools.maven.compiler.mojos.api.AbstractInitializer;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Node;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Phase;

@Component(role = PipelineInitializer.class, hint = "m2gcc:objcpp")
public class ObjCppScanner extends AbstractInitializer implements PipelineInitializer {

	private Node preprocessorObjCpp;
	private Node compilerObjCpp;

	@Override
	public void _initialize() {
		this.preprocessorObjCpp = this.pipeline.acquireNode(Phase.PREPROCESSOR, "objcpp");
		this.compilerObjCpp = this.pipeline.acquireNode(Phase.COMPILER, "objcpp");
		this.pipeline.acquireNode(Phase.LINKER, "objcpp");

		// Objective-C++ source code. Note that you must link with the libobjc library
		// to make an Objective-C++ program work. Note that .M refers to a literal
		// capital M.
		// Objective-C++ source code that must be preprocessed.
		this.preprocessorObjCpp.registerResourceTypes(addTypes("mm", "M"));
		// Objective-C++ source code that should not be preprocessed.
		this.compilerObjCpp.registerResourceType(addType("mii"));
	}

}
