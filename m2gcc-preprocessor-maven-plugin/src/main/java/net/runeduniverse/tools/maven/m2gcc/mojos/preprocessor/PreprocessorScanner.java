package net.runeduniverse.tools.maven.m2gcc.mojos.preprocessor;

import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.logging.Log;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;

import net.runeduniverse.tools.maven.compiler.api.ICompilerRuntime;
import net.runeduniverse.tools.maven.compiler.api.IReferenceScanner;
import net.runeduniverse.tools.maven.compiler.api.mojos.CurrentContextUtils;

@Component(role = IReferenceScanner.class, hint = "m2gcc-preprocessor")
public class PreprocessorScanner implements IReferenceScanner {

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
