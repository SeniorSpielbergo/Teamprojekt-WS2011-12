package gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import machine.Machine;

import tape.Tape;

/**
 * This class contains references to the application data directories and information about the program. It contains methods to update and install the application data.
 * @author nils
 *
 */
public class AppData {

	/**
	 * The name of the application.
	 */
	public static final String APP_NAME = "TuringBrain IDE";
	/**
	 * The version of the application.
	 */
	public static final String APP_VERSION = "1.0.1";
	/**
	 * The authors of the application.
	 */
	public static final String APP_AUTHORS = "Im Rahmen eines Teamprojekts 2011/2012\nentstanden am\n\nInstitut für Programmierung\nund Reaktive Systeme\nan der TU Braunschweig\n\nAdvisor: Matthias Hagner\n\nDevelopers:\nVanessa Baier, Nils Breyer, Phillipp Neumann,\n Sven Schuster, David Wille \n\nTuringBrain IDE is licensed under the\nterms of the BEERWARE license.\njgraph is licensed under the terms of the BSD license.";


	/**
	 * The root directory for the application data
	 */
	public static File appDataDirectory;
	/**
	 * The directory where the example files are placed in
	 */
	public static File examplesDirectory;
	/**
	 * The directory where the tape styles are placed in
	 */
	public static File tapeStylesDirectory;
	/**
	 * 
	 */
	public static File versionFile;


	/**
	 * Initializes the program and checks if the correct version of the app data is installed, otherwise it is getting installed
	 */
	public static void init() {
		//init system specific paths
		if (System.getProperties().getProperty("os.name").equals("Linux")) {
			AppData.appDataDirectory = new File(System.getProperty("user.home") + File.separator + "." + AppData.APP_NAME);
		}
		else if (System.getProperties().getProperty("os.name").equals("Mac OS X")) {
			AppData.appDataDirectory = new File(System.getProperty("user.home") + File.separator + "Library" + File.separator + "Application Support" + File.separator + AppData.APP_NAME);

		}
		else {
			AppData.appDataDirectory = new File(System.getProperty("user.home") + File.separator + AppData.APP_NAME);
		}
		AppData.examplesDirectory = new File(appDataDirectory.getAbsolutePath() + File.separator + "examples");
		AppData.tapeStylesDirectory = new File(appDataDirectory.getAbsolutePath() + File.separator + "tape" + File.separator + "styles");
		AppData.versionFile = new File(appDataDirectory.getAbsolutePath() + File.separator + "Version");

		try {
			String installedVersion = AppData.getInstalledVersion();
			if (!AppData.APP_VERSION.equals(installedVersion)) {
				System.out.println("App data is outdated.");
				try {
					System.out.println("Updating app data  (" + installedVersion + "->" + AppData.APP_VERSION + ")...");
					AppData.update();
					System.out.println("Updating app data  (" + installedVersion + "->" + AppData.APP_VERSION + ") finished.");
				} catch (Exception e1) {
					ErrorDialog.showError("Failed to update application data (" + installedVersion + "->" + AppData.APP_VERSION + "). The application might not run correctly.", e1);
				}			
			}
		}
		catch (Exception e) {
			System.out.println("App data not installed.");
			try {
				System.out.println("Installing app data  (n/a->" + AppData.APP_VERSION + ")...");
				AppData.install();
				System.out.println("Installing app data  (n/a->" + AppData.APP_VERSION + ") finished.");
			} catch (Exception e1) {
				ErrorDialog.showError("Failed to install application data. The application might not run correctly.", e1);
			}
		}
	}

	private static String getInstalledVersion() throws FileNotFoundException {
		Scanner scanner = new Scanner(new FileInputStream(versionFile), "UTF-8");
		String version = scanner.nextLine();
		scanner.close();

		return version;
	}

	private static void install() throws IOException {
		if (AppData.appDataDirectory.exists()) {
			AppData.deleteDirectoryRecursively(AppData.appDataDirectory);
		}
		AppData.appDataDirectory.mkdirs();
		AppData.examplesDirectory.mkdirs();
		AppData.tapeStylesDirectory.mkdirs();

		copyResourcesRecursively(Tape.class.getResource("images/styles"), AppData.tapeStylesDirectory);
		copyResourcesRecursively(Machine.class.getResource("examples"), AppData.examplesDirectory);

		FileWriter outFile = new FileWriter(versionFile);
		PrintWriter out = new PrintWriter(outFile);
		out.println(AppData.APP_VERSION);
		out.close();
	}

	private static void update() throws IOException{
		AppData.deleteDirectoryRecursively(AppData.examplesDirectory);
		AppData.deleteDirectoryRecursively(AppData.tapeStylesDirectory);

		AppData.examplesDirectory.mkdirs();
		AppData.tapeStylesDirectory.mkdirs();

		copyResourcesRecursively(Tape.class.getResource("images/styles"), AppData.tapeStylesDirectory);
		copyResourcesRecursively(Machine.class.getResource("examples"), AppData.examplesDirectory);

		FileWriter outFile = new FileWriter(versionFile);
		PrintWriter out = new PrintWriter(outFile);
		out.println(AppData.APP_VERSION);
		out.close();
	}


	private static void deleteDirectoryRecursively(File f) throws IOException {
		if (f.isDirectory()) {
			for (File c : f.listFiles()) {
				deleteDirectoryRecursively(c);
			}
		}
		System.out.println("Deleting " + f.getAbsolutePath() + "...");
		boolean successful = f.delete();
		if (!successful) {
			System.out.println("Warning: Deleting " + f.getAbsolutePath() + " failed!");
		}
	}

	private static boolean copyFilesRecusively(final File toCopy,
			final File destDir) {
		assert destDir.isDirectory();

		if (!toCopy.isDirectory()) {
			File destFile = new File(destDir, toCopy.getName());
			System.out.println("Installing '" + destFile.getAbsolutePath() + "'...");
			try {
				return copyStream(new FileInputStream(toCopy), new FileOutputStream(destFile));
			} catch (final FileNotFoundException e) {
				e.printStackTrace();
			}
			return false;
		} else {
			final File newDestDir = new File(destDir, toCopy.getName());

			if (!newDestDir.exists() && !newDestDir.mkdir()) {
				return false;
			}
			for (final File child : toCopy.listFiles()) {
				if (!copyFilesRecusively(child, newDestDir)) {
					return false;
				}
			}
		}
		return true;
	}

	private static boolean copyJarResourcesRecursively(final File destDir, final JarURLConnection jarConnection) throws IOException {
		final JarFile jarFile = jarConnection.getJarFile();

		for (final Enumeration<JarEntry> e = jarFile.entries(); e.hasMoreElements();) {
			final JarEntry entry = e.nextElement();
			if (entry.getName().startsWith(jarConnection.getEntryName())) {
				String filename = entry.getName();
				if (entry.getName().startsWith(jarConnection.getEntryName())) {
					filename = filename.substring(jarConnection.getEntryName().length());
				}

				final File f = new File(destDir, filename);
				if (!entry.isDirectory()) {
					System.out.println("Installing '" + f.getAbsolutePath() + "'...");
					final InputStream entryInputStream = jarFile.getInputStream(entry);
					if(!copyStream(entryInputStream, f)){
						return false;
					}
					entryInputStream.close();
				} else {
					if (!ensureDirectoryExists(f)) {
						throw new IOException("Could not create directory: "
								+ f.getAbsolutePath());
					}
				}
			}
		}
		return true;
	}

	private static boolean copyResourcesRecursively(final URL originUrl, final File destination) {
		try {
			final URLConnection urlConnection = originUrl.openConnection();
			if (urlConnection instanceof JarURLConnection) {
				return copyJarResourcesRecursively(destination,
						(JarURLConnection) urlConnection);
			} else {
				boolean success = true;
				for (File f: (new File(originUrl.getPath())).listFiles()) {
					success = copyFilesRecusively(f,
							destination);
					if (success = false) {
						return false;
					}
				}
				return success;
			}
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	private static boolean copyStream(final InputStream is, final File f) {
		try {
			return copyStream(is, new FileOutputStream(f));
		} catch (final FileNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	private static boolean copyStream(final InputStream is, final OutputStream os) {
		try {
			final byte[] buf = new byte[1024];

			int len = 0;
			while ((len = is.read(buf)) > 0) {
				os.write(buf, 0, len);
			}
			is.close();
			os.close();
			return true;
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	private static boolean ensureDirectoryExists(final File f) {
		return f.exists() || f.mkdir();
	}
}


