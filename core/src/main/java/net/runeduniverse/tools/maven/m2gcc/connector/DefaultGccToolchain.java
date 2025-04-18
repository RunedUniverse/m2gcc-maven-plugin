package net.runeduniverse.tools.maven.m2gcc.connector;

import static net.runeduniverse.lib.utils.common.StringUtils.isBlank;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.apache.maven.toolchain.DefaultToolchain;
import org.apache.maven.toolchain.model.ToolchainModel;
import org.codehaus.plexus.logging.Logger;

import net.runeduniverse.lib.utils.logging.log.DefaultCompoundTree;
import net.runeduniverse.lib.utils.logging.log.api.CompoundTree;
import net.runeduniverse.lib.utils.logging.log.api.Recordable;

/**
 * Gcc toolchain implementation.
 *
 * @author VenaNocta
 * @since 1.0.0
 */
public class DefaultGccToolchain extends DefaultToolchain implements Recordable {

	public static final String KEY_BINARY_PATH = "binPath";
	public static final String KEY_LIBRARY_PATH = "libPath";
	public static final String KEY_LIBRARY_EXECUTABLE_PATH = "libExecPath";

	private String binaryPath = null;
	private String libraryPath = null;
	private String libraryExecutablePath = null;

	protected DefaultGccToolchain(ToolchainModel model, Logger logger) {
		super(model, "gcc", logger);
	}

	@Override
	public String findTool(String toolName) {
		if (isBlank(toolName))
			return null;
		if (toolName.equals("gcc")) {
			if (!isBlank(this.binaryPath)) {
				Path path = Paths.get(this.binaryPath);
				if (Files.exists(path) && Files.isExecutable(path))
					return path.toAbsolutePath()
							.toString();
			}
			return null;
		}

		if (isBlank(this.libraryExecutablePath))
			return null;

		return findTool(new LinkedHashSet<>(), Paths.get(this.libraryExecutablePath), toolName);
	}

	private String findTool(final Set<Object> visitedDirKeys, final Path dir, String toolName) {
		final Map<String, Path> dirs = new LinkedHashMap<>(0);
		for (Path path : dir) {
			path = path.toAbsolutePath();
			if (!Files.isReadable(path))
				continue;
			String sPath = path.toString();
			if (Files.isDirectory(path) && !visitedDirKeys.contains(sPath)) {
				dirs.put(sPath, path);
				continue;
			}
			if (!Files.isExecutable(path))
				continue;
			String name = path.getFileName()
					.toString();
			int idx = name.indexOf('.');
			if (idx != -1)
				name = name.substring(0, idx);
			if (toolName.equals(name))
				return sPath;
		}
		String result = null;
		for (Map.Entry<String, Path> entry : dirs.entrySet()) {
			visitedDirKeys.add(entry.getKey());
			result = findTool(visitedDirKeys, entry.getValue(), toolName);
			if (result != null)
				return result;
		}
		return null;
	}

	public String getBinaryPath() {
		return this.binaryPath;
	}

	public String getLibraryPath() {
		return this.libraryPath;
	}

	public String getLibraryExecutablePath() {
		return this.libraryExecutablePath;
	}

	public void setBinaryPath(String binaryPath) {
		this.binaryPath = binaryPath;
	}

	public void setLibraryPath(String libraryPath) {
		this.libraryPath = libraryPath;
	}

	public void setLibraryExecutablePath(String libraryExecutablePath) {
		this.libraryExecutablePath = libraryExecutablePath;
	}

	@Override
	public String toString() {
		return String.format("[GCC] %s %s %s", this.binaryPath, this.libraryPath, this.libraryExecutablePath);
	}

	@Override
	public CompoundTree toRecord() {
		final CompoundTree tree = new DefaultCompoundTree("gcc toolchain");
		if (!isBlank(this.binaryPath))
			tree.append("BINARY_PATH", this.binaryPath);
		if (!isBlank(this.libraryPath))
			tree.append("LIBRARY_PATH", this.libraryPath);
		if (!isBlank(this.libraryExecutablePath))
			tree.append("LIBRARY_EXECUTABLE_PATH", this.libraryPath);
		return tree;
	}

}
