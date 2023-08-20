package net.runeduniverse.tools.maven.m2gcc.scanner;

import org.codehaus.plexus.component.annotations.Component;

import net.runeduniverse.tools.maven.compiler.api.PipelineInitializer;
import net.runeduniverse.tools.maven.compiler.mojos.api.AbstractInitializer;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Node;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Phase;

@Component(role = PipelineInitializer.class, hint = "m2gcc:c")
public class CScanner extends AbstractInitializer implements PipelineInitializer {

	private Node preprocessorC;
	private Node compilerC;

	@Override
	public void _initialize() {
		this.preprocessorC = this.pipeline.acquireNode(Phase.PREPROCESSOR, "c");
		this.compilerC = this.pipeline.acquireNode(Phase.COMPILER, "c");
		this.pipeline.acquireNode(Phase.LINKER, "c");

		// C source code that must be preprocessed.
		this.preprocessorC.registerResourceType(addType("c"));
		// C source code that should not be preprocessed.
		this.compilerC.registerResourceType(addType("i"));
	}

}
