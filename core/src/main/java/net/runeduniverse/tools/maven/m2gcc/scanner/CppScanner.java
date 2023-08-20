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

@Component(role = ResourceScanner.class, hint = "m2gcc:cpp")
public class CppScanner extends Scanner implements ResourceScanner {

	private Node preprocessorCpp;
	private Node compilerCpp;

	@Override
	public void identifyNodes() {
		this.preprocessorCpp = this.pipeline.acquireNode(Phase.PREPROCESSOR, "cpp");
		this.compilerCpp = this.pipeline.acquireNode(Phase.COMPILER, "cpp");
		this.pipeline.acquireNode(Phase.LINKER, "cpp");

		// C++ source code that must be preprocessed. Note that in .cxx, the last two
		// letters must both be literally x. Likewise, .C refers to a literal capital C.
		this.preprocessorCpp.registerResourceTypes(addTypes("cc", "cp", "cxx", "cpp", "CPP", "c++", "C"));
		// C++ source code that should not be preprocessed.
		this.compilerCpp.registerResourceType(addType("ii"));
	}

	@Override
	protected boolean _scan() {
		Path cSources = this.runtime.getSourceDirectory()
				.toPath()
				.resolve("cpp");
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
		return false;
	}

}
