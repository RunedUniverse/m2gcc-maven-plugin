package net.runeduniverse.tools.maven.m2gcc.ext.r4m;

import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.configuration.PlexusConfiguration;

import net.runeduniverse.tools.maven.r4m.pem.api.ExecutionArchiveSelectorConfig;
import net.runeduniverse.tools.maven.r4m.pem.api.ExecutionRestrictionParser;
import net.runeduniverse.tools.maven.r4m.pem.model.ExecutionRestriction;

@Component(role = ExecutionRestrictionParser.class, hint = FolderRestriction.HINT)
public class FolderRestrictionParser implements ExecutionRestrictionParser {

	@Override
	public ExecutionRestriction<ExecutionArchiveSelectorConfig> parse(PlexusConfiguration node) {
		String sExists = node.getAttribute("exists", null);
		Boolean exists = null;
		if (sExists != null) {
			if (sExists.equalsIgnoreCase("true"))
				exists = true;
			if (sExists.equalsIgnoreCase("false"))
				exists = false;
		}
		String sReadable = node.getAttribute("readable", null);
		Boolean readable = null;
		if (sReadable != null) {
			if (sReadable.equalsIgnoreCase("true"))
				readable = true;
			if (sReadable.equalsIgnoreCase("false"))
				readable = false;
		}
		return new FolderRestriction(node.getValue(), exists, readable);
	}

}
