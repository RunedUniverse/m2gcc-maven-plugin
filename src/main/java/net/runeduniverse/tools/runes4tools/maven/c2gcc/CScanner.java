package net.runeduniverse.tools.runes4tools.maven.c2gcc;

import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.logging.Log;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;

import net.runeduniverse.tools.maven.compiler.api.ICompilerRuntime;
import net.runeduniverse.tools.maven.compiler.api.IReferenceScanner;
import net.runeduniverse.tools.maven.compiler.api.mojos.CurrentContextUtils;

@Component(role = IReferenceScanner.class, hint = "c2gcc")
public class CScanner implements IReferenceScanner {

	@Requirement
	private MavenSession mvnSession;

	protected ICompilerRuntime runtime;

	@Override
	public boolean logInfo(Log log) {
		this.runtime = CurrentContextUtils.lookupComponent(this.mvnSession, ICompilerRuntime.class);

		log.info("C Scanner  - Hello World!");
		log.info("scanning src for c files: " + this.runtime.getSourceDirectory());
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean logAnalisis(Log log) {
		// TODO Auto-generated method stub
		return false;
	}

}
