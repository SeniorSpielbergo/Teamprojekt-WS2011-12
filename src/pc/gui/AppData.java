package gui;

import java.io.File;

public class AppData {
	/**
	 * The name of the application.
	 */
	public static final String APP_NAME = "TuringBrain IDE";
	/**
	 * The version of the application.
	 */
	public static final String APP_VERSION = "0.8.5";
	/**
	 * The authors of the application.
	 */
	public static final String APP_AUTHORS = "Im Rahmen des Teamprojekts 2011 entstanden.\n\nInstitut für Programmierung\nund Reaktive Systeme\n\nBetreuer: Matthias Hagner\n\nVanessa Baier,\n Nils Breyer,\n Phillipp Neumann,\n Sven Schuster,\n David Wille";


	static File AppDataDirectory;

	public static void init() {
		if (System.getProperties().getProperty("os.name").equals("Linux")) {
			AppData.AppDataDirectory = new File(System.getProperty("user.home") + File.separator + "." + AppData.APP_NAME);
		}
		else if (System.getProperties().getProperty("os.name").equals("Mac OS X")) {
			AppData.AppDataDirectory = new File(System.getProperty("user.home") + File.separator + "Library" + File.separator + "Application Support" + AppData.APP_NAME);

		}
		else {
			AppData.AppDataDirectory = new File(System.getProperty("user.home") + File.separator + AppData.APP_NAME);
		}
	} 
}


