package net.runeduniverse.tools.maven.m2gcc.ext.r4m;

import net.runeduniverse.lib.utils.logging.logs.CompoundTree;
import net.runeduniverse.tools.maven.r4m.pem.api.ExecutionArchiveSelectorConfig;
import net.runeduniverse.tools.maven.r4m.pem.model.ExecutionRestriction;

public class FolderRestriction implements ExecutionRestriction<ExecutionArchiveSelectorConfig> {

	public static final String HINT = "folder";

	@Override
	public CompoundTree toRecord() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHint() {
		return HINT;
	}

	@Override
	public Class<ExecutionArchiveSelectorConfig> getDataType() {
		return ExecutionArchiveSelectorConfig.class;
	}

	@Override
	public boolean isActive(ExecutionArchiveSelectorConfig data) {
		// TODO Auto-generated method stub
		return false;
	}

}
