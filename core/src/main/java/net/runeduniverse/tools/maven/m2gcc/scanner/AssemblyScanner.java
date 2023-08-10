package net.runeduniverse.tools.maven.m2gcc.scanner;

import org.apache.maven.plugin.logging.Log;
import org.codehaus.plexus.component.annotations.Component;

import net.runeduniverse.tools.maven.compiler.api.ResourceScanner;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Node;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Phase;

@Component(role = ResourceScanner.class, hint = "m2gcc:assembly")
public class AssemblyScanner extends Scanner implements ResourceScanner {

	private Node preprocessorAssembler;
	private Node assemblerAssembly;

	@Override
	public void identifyNodes() {
		this.preprocessorAssembler = this.pipeline.acquireNode(Phase.PREPROCESSOR, "assembly");
		this.assemblerAssembly = this.pipeline.acquireNode(Phase.ASSEMBLER, "assembly");

		// Assembler code.
		this.assemblerAssembly.registerResourceType(addType("s"));
		// Assembler code that must be preprocessed.
		this.preprocessorAssembler.registerResourceTypes(addTypes("S", "sx"));
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
