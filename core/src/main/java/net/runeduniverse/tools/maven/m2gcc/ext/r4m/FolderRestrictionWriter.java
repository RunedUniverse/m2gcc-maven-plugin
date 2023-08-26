package net.runeduniverse.tools.maven.m2gcc.ext.r4m;

import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.configuration.PlexusConfiguration;

import net.runeduniverse.tools.maven.r4m.pem.api.ExecutionRestrictionWriter;
import net.runeduniverse.tools.maven.r4m.pem.model.ExecutionRestriction;

@Component(role = ExecutionRestrictionWriter.class, hint = FolderRestriction.HINT)
public class FolderRestrictionWriter implements ExecutionRestrictionWriter {

	@Override
	public boolean append(PlexusConfiguration restrictionsNode, ExecutionRestriction<?> restriction) {
		if (restriction == null || !(restriction instanceof FolderRestriction))
			return false;

		final FolderRestriction folderRestriction = (FolderRestriction) restriction;
		final PlexusConfiguration node = restrictionsNode.addChild(FolderRestriction.HINT,
				folderRestriction.getValue());

		final Boolean exists = folderRestriction.getExists();
		if (exists != null)
			node.setAttribute("exists", exists.toString());
		final Boolean readable = folderRestriction.getReadable();
		if (readable != null)
			node.setAttribute("readable", readable.toString());

		return true;
	}

}
