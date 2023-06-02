package net.runeduniverse.tools.maven.m2gcc;

import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.logging.Log;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;

import net.runeduniverse.tools.maven.compiler.api.ICompilerRuntime;
import net.runeduniverse.tools.maven.compiler.api.IReferenceScanner;
import net.runeduniverse.tools.maven.compiler.api.mojos.CurrentContextUtils;

@Component(role = IReferenceScanner.class, hint = "m2gcc")
public class ReferenceScanner implements IReferenceScanner {

	// TODO - create pipeline in compiler-maven-extension
	// TODO - add api to add nodes (filetypes: c,i,ii,...)
	// TODO - create links between nodes (either in the ref scanner or when the  actual mojo executes)
	// TODO - in summuary/validation/ScanReferencesMojo check if it's fully linked
	// TODO - in mojos: 1. validate if compiler-lifecycle is present by optional plexus linking
	// TODO - in mojos: 2. if compiler-lifecycle is not loaded do the defined step
	// to all files found in main/target

	@Requirement
	private MavenSession mvnSession;

	protected Log log;
	protected ICompilerRuntime runtime;

	@Override
	public boolean scan() {
		this.log = CurrentContextUtils.lookupComponent(this.mvnSession, Log.class);
		this.runtime = CurrentContextUtils.lookupComponent(this.mvnSession, ICompilerRuntime.class);

		log.info("Scanner: Preprocessor  - Hello World!");
		log.info("scanning src files: " + this.runtime.getSourceDirectory());
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean logAnalisis(Log log) {
		// TODO Auto-generated method stub
		return false;
	}

}
