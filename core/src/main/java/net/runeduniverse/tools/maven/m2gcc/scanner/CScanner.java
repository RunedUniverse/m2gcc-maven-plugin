package net.runeduniverse.tools.maven.m2gcc.scanner;

import java.io.File;

import org.apache.maven.plugin.logging.Log;
import org.codehaus.plexus.component.annotations.Component;

import net.runeduniverse.tools.maven.compiler.api.ResourceScanner;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Node;
import net.runeduniverse.tools.maven.compiler.pipeline.api.NodeContext;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Phase;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Resource;
import net.runeduniverse.tools.maven.compiler.pipeline.api.ResourceType;

@Component(role = ResourceScanner.class, hint = "m2gcc:c")
public class CScanner extends Scanner implements ResourceScanner {

	private Node preprocessorC;
	private Node compilerC;

	@Override
	public void identifyNodes() {
		this.preprocessorC = this.pipeline.acquireNode(Phase.PREPROCESSOR, "c");
		this.compilerC = this.pipeline.acquireNode(Phase.COMPILER, "c");

		// C source code that must be preprocessed.
		this.preprocessorC.registerResourceType(addType("c"));
		// C source code that should not be preprocessed.
		this.compilerC.registerResourceType(addType("i"));
	}

	@Override
	protected boolean _scan() {
		NodeContext conPreprocC = this.pipeline.getNodeContext(this.mvnSession, Phase.PREPROCESSOR, "c");
		ResourceType resourceTypeC = this.pipeline.acquireType("c");

		File cDir = new File(this.runtime.getSourceDirectory(), "c");
		if (cDir.exists() && cDir.isDirectory() && cDir.canRead()) {
			for (File file : cDir.listFiles()) {
				Resource resource = this.pipeline.createResource(resourceTypeC);
				conPreprocC.addResource(resource.setFile(file));
			}
		}
		return true;
	}

	@Override
	public boolean logAnalisis(Log log) {
		// TODO Auto-generated method stub
		return false;
	}

}
