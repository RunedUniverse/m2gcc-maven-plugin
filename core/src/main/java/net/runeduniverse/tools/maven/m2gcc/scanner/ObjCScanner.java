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
		Path cSources = this.runtime.getSourceDirectory()
				.toPath()
				.resolve("objc");
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
