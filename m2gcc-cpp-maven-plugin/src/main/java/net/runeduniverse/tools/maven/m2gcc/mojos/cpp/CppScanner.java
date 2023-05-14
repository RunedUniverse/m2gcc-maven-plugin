package net.runeduniverse.tools.maven.m2gcc.mojos.cpp;

import org.apache.maven.plugin.logging.Log;
import org.codehaus.plexus.component.annotations.Component;

import net.runeduniverse.tools.maven.compiler.api.IReferenceScanner;

@Component(role = IReferenceScanner.class, hint = "cpp2gcc")
public class CppScanner implements IReferenceScanner{

	@Override
	public boolean logInfo(Log log) {
		log.info("Cpp Sacnner  - Hello World!");
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean logAnalisis(Log log) {
		// TODO Auto-generated method stub
		return false;
	}

}
