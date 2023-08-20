package net.runeduniverse.tools.maven.m2gcc.scanner;

import org.codehaus.plexus.component.annotations.Component;

import net.runeduniverse.tools.maven.compiler.api.PipelineInitializer;
import net.runeduniverse.tools.maven.compiler.mojos.api.AbstractInitializer;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Node;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Phase;

@Component(role = PipelineInitializer.class, hint = "m2gcc:assembly")
public class AssemblyScanner extends AbstractInitializer implements PipelineInitializer {

	private Node preprocessorAssembler;
	private Node assemblerAssembly;

	@Override
	public void _initialize() {
		this.preprocessorAssembler = this.pipeline.acquireNode(Phase.PREPROCESSOR, "assembly");
		this.assemblerAssembly = this.pipeline.acquireNode(Phase.ASSEMBLER, "assembly");

		// Assembler code.
		this.assemblerAssembly.registerResourceType(addType("s"));
		// Assembler code that must be preprocessed.
		this.preprocessorAssembler.registerResourceTypes(addTypes("S", "sx"));
	}

}
