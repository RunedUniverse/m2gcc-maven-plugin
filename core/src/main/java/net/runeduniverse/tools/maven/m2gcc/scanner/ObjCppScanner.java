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

@Component(role = ResourceScanner.class, hint = "m2gcc:objcpp")
public class ObjCppScanner extends Scanner implements ResourceScanner {

	private Node preprocessorObjCpp;
	private Node compilerObjCpp;

	@Override
	public void identifyNodes() {
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

	@Override
	protected boolean _scan() {
		Path cSources = this.runtime.getSourceDirectory()
				.toPath()
				.resolve("objcpp");
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
