package net.runeduniverse.tools.runes4tools.maven.c2gcc;

import org.apache.maven.plugin.logging.Log;
import org.codehaus.plexus.component.annotations.Component;

import net.runeduniverse.tools.maven.compiler.api.IReferenceScanner;

@Component(role = IReferenceScanner.class, hint = "c2gcc")
public class CScanner implements IReferenceScanner{

	@Override
	public boolean logInfo(Log log) {
		log.info("C Sacnner  - Hello World!");
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean logAnalisis(Log log) {
		// TODO Auto-generated method stub
		return false;
	}

}
