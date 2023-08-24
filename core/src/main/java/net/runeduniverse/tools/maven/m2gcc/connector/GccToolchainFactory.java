package net.runeduniverse.tools.maven.m2gcc.connector;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.maven.toolchain.MisconfiguredToolchainException;
import org.apache.maven.toolchain.RequirementMatcher;
import org.apache.maven.toolchain.RequirementMatcherFactory;
import org.apache.maven.toolchain.ToolchainFactory;
import org.apache.maven.toolchain.ToolchainPrivate;
import org.apache.maven.toolchain.model.ToolchainModel;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;
import org.codehaus.plexus.logging.Logger;
import org.codehaus.plexus.util.xml.Xpp3Dom;

/**
 * GCC toolchain factory. This is a <code>ToolchainFactory</code> Plexus
 * component registered with <code>jdk</code> hint.
 *
 * @author VenaNocta
 * @since 1.0.0
 */
@Component(role = ToolchainFactory.class, hint = "gcc")
public class GccToolchainFactory implements ToolchainFactory {

	@Requirement
	private Logger logger;

	@Override
	public ToolchainPrivate createToolchain(ToolchainModel model) throws MisconfiguredToolchainException {
		if (model == null)
			return null;

		GccToolchainImpl toolchain = new GccToolchainImpl(model, this.logger);

		// populate the provides section
		Properties provides = model.getProvides();
		for (Entry<Object, Object> provide : provides.entrySet()) {
			String key = (String) provide.getKey();
			String value = (String) provide.getValue();

			if (value == null) {
				throw new MisconfiguredToolchainException(
						"Provides token '" + key + "' doesn't have any value configured.");
			}

			RequirementMatcher matcher;
			if ("version".equals(key)) {
				matcher = RequirementMatcherFactory.createVersionMatcher(value);
			} else {
				matcher = RequirementMatcherFactory.createExactMatcher(value);
			}

			toolchain.addProvideToken(key, matcher);
		}

		// populate the configuration section
		Xpp3Dom dom = (Xpp3Dom) model.getConfiguration();
		Xpp3Dom binaryPathDom = dom.getChild(GccToolchainImpl.KEY_BINARY_PATH);
		if (binaryPathDom != null) {
			Path binaryPath = Paths.get(binaryPathDom.getValue());
			if (!Files.exists(binaryPath))
				throw new MisconfiguredToolchainException(
						"Non-existing GCC Binary configuration at " + binaryPath.toAbsolutePath()
								.toString());
			if (!Files.isReadable(binaryPath) && !Files.isExecutable(binaryPath))
				throw new MisconfiguredToolchainException(
						"Inaccessible GCC Binary configuration at " + binaryPath.toAbsolutePath()
								.toString());
			toolchain.setBinaryPath(binaryPathDom.getValue());
		}
		Xpp3Dom libraryPathDom = dom.getChild(GccToolchainImpl.KEY_LIBRARY_PATH);
		if (libraryPathDom != null) {
			Path libraryPath = Paths.get(libraryPathDom.getValue());
			if (!Files.exists(libraryPath))
				throw new MisconfiguredToolchainException(
						"Non-existing GCC Library configuration at " + libraryPath.toAbsolutePath()
								.toString());
			if (!Files.isReadable(libraryPath))
				throw new MisconfiguredToolchainException(
						"Inaccessible GCC Library configuration at " + libraryPath.toAbsolutePath()
								.toString());
			toolchain.setLibraryPath(libraryPathDom.getValue());
		}
		Xpp3Dom libraryExecutablePathDom = dom.getChild(GccToolchainImpl.KEY_LIBRARY_EXECUTABLE_PATH);
		if (libraryPathDom != null) {
			Path libraryExecutablePath = Paths.get(libraryExecutablePathDom.getValue());
			if (!Files.exists(libraryExecutablePath))
				throw new MisconfiguredToolchainException(
						"Non-existing GCC Library Executable configuration at " + libraryExecutablePath.toAbsolutePath()
								.toString());
			if (!Files.isReadable(libraryExecutablePath))
				throw new MisconfiguredToolchainException(
						"Inaccessible GCC Library Executable configuration at " + libraryExecutablePath.toAbsolutePath()
								.toString());
			toolchain.setLibraryExecutablePath(libraryExecutablePathDom.getValue());
		}

		return toolchain;
	}

	@Override
	public ToolchainPrivate createDefaultToolchain() {
		// not sure it's necessary to provide a default toolchain here.
		return null;
	}

}
