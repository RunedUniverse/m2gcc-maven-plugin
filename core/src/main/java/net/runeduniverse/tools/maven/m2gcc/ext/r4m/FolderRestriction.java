package net.runeduniverse.tools.maven.m2gcc.ext.r4m;

import net.runeduniverse.lib.utils.logging.log.DefaultCompoundTree;
import net.runeduniverse.lib.utils.logging.log.api.CompoundTree;
import net.runeduniverse.tools.maven.r4m.pem.model.ExecutionRestriction;

import static net.runeduniverse.lib.utils.common.ComparisonUtils.objectEquals;
import static net.runeduniverse.lib.utils.common.HashUtils.hash;

public class FolderRestriction implements ExecutionRestriction {

	public static final String HINT = "folder";
	public static final String CANONICAL_NAME = "net.runeduniverse.tools.maven.m2gcc.ext.r4m.FolderRestriction";

	private String value = "";
	private Boolean exists = null;
	private Boolean readable = null;

	public FolderRestriction(final String value, final Boolean exists, final Boolean readable) {
		this.value = value;
		this.exists = exists;
		this.readable = readable;
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

	@Override
	public int hashCode() {
		return hash(HINT);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof FolderRestriction))
			return false;
		final FolderRestriction restriction = (FolderRestriction) obj;
		return objectEquals(this.value, restriction.getValue()) //
				&& objectEquals(this.exists, restriction.getExists()) //
				&& objectEquals(this.readable, restriction.getReadable());
	}

	@Override
	public FolderRestriction copy() {
		final FolderRestriction restriction = new FolderRestriction(this.value, this.exists, this.readable);
		return restriction;
	}

	@Override
	public CompoundTree toRecord() {
		CompoundTree tree = new DefaultCompoundTree(HINT).append("value", this.value);
		if (this.exists != null)
			tree.append("exists", this.exists.toString());
		if (this.readable != null)
			tree.append("readable", this.readable.toString());
		return tree;
	}
}
