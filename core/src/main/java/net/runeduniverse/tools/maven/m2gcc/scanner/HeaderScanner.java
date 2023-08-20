package net.runeduniverse.tools.maven.m2gcc.scanner;

import org.codehaus.plexus.component.annotations.Component;

import net.runeduniverse.tools.maven.compiler.api.PipelineInitializer;
import net.runeduniverse.tools.maven.compiler.mojos.api.AbstractInitializer;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Node;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Phase;

@Component(role = PipelineInitializer.class, hint = "m2gcc:header")
public class HeaderScanner extends AbstractInitializer implements PipelineInitializer {

	private Node preprocessorHeader;

	@Override
	public void _initialize() {
		this.preprocessorHeader = this.pipeline.acquireNode(Phase.PREPROCESSOR, "header");

		// C, C++, Objective-C or Objective-C++ header file to be turned into a
		// precompiled header (default), or C, C++ header file to be turned into an Ada
		// spec (via the -fdump-ada-spec switch).
		this.preprocessorHeader.registerResourceType(addType("h"));
		// C++ header file to be turned into a precompiled header or Ada spec.
		this.preprocessorHeader.registerResourceTypes(addTypes("hh", "H", "hp", "hxx", "hpp", "HPP", "h++", "tcc"));

	}
}
