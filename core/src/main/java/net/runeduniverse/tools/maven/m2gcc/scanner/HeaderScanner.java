package net.runeduniverse.tools.maven.m2gcc.scanner;

import org.apache.maven.plugin.logging.Log;
import org.codehaus.plexus.component.annotations.Component;
import net.runeduniverse.tools.maven.compiler.api.ResourceScanner;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Node;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Phase;

@Component(role = ResourceScanner.class, hint = "m2gcc:header")
public class HeaderScanner extends Scanner implements ResourceScanner {

	private Node preprocessorHeader;

	@Override
	public void identifyNodes() {
		this.preprocessorHeader = this.pipeline.acquireNode(Phase.PREPROCESSOR, "header");

		// C, C++, Objective-C or Objective-C++ header file to be turned into a
		// precompiled header (default), or C, C++ header file to be turned into an Ada
		// spec (via the -fdump-ada-spec switch).
		this.preprocessorHeader.registerResourceType(addType("h"));
		// C++ header file to be turned into a precompiled header or Ada spec.
		this.preprocessorHeader
				.registerResourceTypes(addTypes("hh", "H", "hp", "hxx", "hpp", "HPP", "h++", "tcc"));

	}

	@Override
	public boolean _scan() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean logAnalisis(Log log) {
		// TODO Auto-generated method stub
		return false;
	}

}
