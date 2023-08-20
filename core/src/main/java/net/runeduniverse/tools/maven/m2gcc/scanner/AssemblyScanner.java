package net.runeduniverse.tools.maven.m2gcc.scanner;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.EnumSet;

import org.apache.maven.plugin.logging.Log;
import org.codehaus.plexus.component.annotations.Component;

import net.runeduniverse.tools.maven.compiler.api.ResourceScanner;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Node;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Phase;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Resource;

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
		Path cSources = this.runtime.getSourceDirectory()
				.toPath()
				.resolve("assembly");
		ResourceCollector collector = new ResourceCollector(this.pipeline.getResourceIndex(this.mvnSession));

		try {
			Files.walkFileTree(cSources, EnumSet.of(FileVisitOption.FOLLOW_LINKS), Integer.MAX_VALUE, collector);
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (Resource resource : collector.getResources()) {
			addResourceToInitialHandler(resource);
		}
		return true;
	}

	@Override
	public boolean logAnalisis(Log log) {
		// TODO Auto-generated method stub
		return false;
	}

}
