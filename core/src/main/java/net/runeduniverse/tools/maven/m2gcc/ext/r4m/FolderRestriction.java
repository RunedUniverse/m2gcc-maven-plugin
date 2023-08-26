package net.runeduniverse.tools.maven.m2gcc.ext.r4m;

import net.runeduniverse.lib.utils.logging.logs.CompoundTree;
import net.runeduniverse.tools.maven.r4m.pem.api.ExecutionArchiveSelectorConfig;
import net.runeduniverse.tools.maven.r4m.pem.model.ExecutionRestriction;

public class FolderRestriction implements ExecutionRestriction<ExecutionArchiveSelectorConfig> {

	public static final String HINT = "folder";

	private String value = "";
	private Boolean exists = null;
	private Boolean readable = null;

	public FolderRestriction(final String value, final Boolean exists, final Boolean readable) {
		this.value = value;
		this.exists = exists;
		this.readable = readable;
	}

	@Override
	public CompoundTree toRecord() {
		CompoundTree tree = new CompoundTree(HINT).append("value", this.value);
		if (this.exists != null)
			tree.append("exists", this.exists.toString());
		if (this.readable != null)
			tree.append("readable", this.readable.toString());
		return tree;
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Boolean getExists() {
		return exists;
	}

	public void setExists(Boolean exists) {
		this.exists = exists;
	}

	public Boolean getReadable() {
		return readable;
	}

	public void setReadable(Boolean readable) {
		this.readable = readable;
	}

}
