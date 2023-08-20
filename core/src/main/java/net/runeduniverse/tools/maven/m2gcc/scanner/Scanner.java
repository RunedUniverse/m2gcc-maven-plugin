package net.runeduniverse.tools.maven.m2gcc.scanner;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.logging.Log;
import org.codehaus.plexus.component.annotations.Requirement;

import net.runeduniverse.tools.maven.compiler.api.CompilerRuntime;
import net.runeduniverse.tools.maven.compiler.api.ResourceScanner;
import net.runeduniverse.tools.maven.compiler.mojos.api.SessionContextUtils;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Pipeline;
import net.runeduniverse.tools.maven.compiler.pipeline.api.Resource;
import net.runeduniverse.tools.maven.compiler.pipeline.api.ResourceType;

public abstract class Scanner implements ResourceScanner {

	protected final Set<ResourceType> registeredTypes = new LinkedHashSet<>(0);

	@Requirement
	protected MavenSession mvnSession;

	@Requirement
	protected Pipeline pipeline;

	protected Log log;
	protected CompilerRuntime runtime;

	protected ResourceType addType(String suffix) {
		final ResourceType type = this.pipeline.acquireType(suffix);
		this.registeredTypes.add(type);
		return type;
	}

	protected Collection<ResourceType> addTypes(String... suffixes) {
		final Collection<ResourceType> types = this.pipeline.acquireTypes(suffixes);
		this.registeredTypes.addAll(types);
		return types;
	}

	protected void addResourceToInitialHandler(final Resource resource) {
		this.pipeline.addResourceToNextNodeContext(this.mvnSession, null, resource);
	}

	protected abstract boolean _scan();

	@Override
	public boolean scan() {
		this.log = SessionContextUtils.lookupSessionComponent(this.mvnSession, Log.class);
		this.runtime = SessionContextUtils.lookupSessionComponent(this.mvnSession, CompilerRuntime.class);

		return _scan();
	}

}
