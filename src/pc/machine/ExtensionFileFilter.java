package machine;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * This class implements a simple file filter based on the file extension.
 * @author Nils Breyer
 *
 */
public class ExtensionFileFilter extends FileFilter {
	/**
	 * Constructs a new ExtensionFileFilter
	 * @param description the description of the filter
	 * @param extension the file extension the filter should match
	 */
	public ExtensionFileFilter(String description, String extension) {
		super();
		this.description = description;
		this.extension = extension;
	}

	private String description;
	private String extension;

	@Override
	public boolean accept(File f) {
		return f.isDirectory() || f.getName().toLowerCase().endsWith(this.extension);

	}

	@Override
	public String getDescription() {
		return this.description + " (" + this.extension + ")";
	}
	
	/**
	 * Returns the extension the filter accepts
	 * @return the file extension
	 */
	public String getExtension() {
		return this.extension;
	}
}
