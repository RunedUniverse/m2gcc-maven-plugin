package net.runeduniverse.tools.maven.m2gcc;

import org.apache.maven.execution.MavenSession;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;

import net.runeduniverse.tools.maven.m2gcc.api.IScanner;

@Component(role = IScanner.class)
public class Scanner implements IScanner {

	@Requirement
	private MavenSession mvnSession;

}
