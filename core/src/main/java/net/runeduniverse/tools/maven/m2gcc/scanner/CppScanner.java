package net.runeduniverse.tools.maven.m2gcc.scanner;

import org.apache.maven.plugin.logging.Log;
import org.codehaus.plexus.component.annotations.Component;

import net.runeduniverse.tools.maven.compiler.api.ResourceScanner;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Node;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Phase;

@Component(role = ResourceScanner.class, hint = "m2gcc:cpp")
public class CppScanner extends Scanner implements ResourceScanner {

	private Node preprocessorCpp;
	private Node compilerCpp;

	@Override
	public void identifyNodes() {
		this.preprocessorCpp = this.pipeline.acquireNode(Phase.PREPROCESSOR, "cpp");
		this.compilerCpp = this.pipeline.acquireNode(Phase.COMPILER, "cpp");

		// C++ source code that must be preprocessed. Note that in .cxx, the last two
		// letters must both be literally x. Likewise, .C refers to a literal capital C.
		this.preprocessorCpp
				.registerResourceTypes(addTypes("cc", "cp", "cxx", "cpp", "CPP", "c++", "C"));
		// C++ source code that should not be preprocessed.
		this.compilerCpp.registerResourceType(addType("ii"));
	}

	@Override
	protected boolean _scan() {
		return false;
	}

	@Override
	public boolean logAnalisis(Log log) {
		return false;
	}

}
