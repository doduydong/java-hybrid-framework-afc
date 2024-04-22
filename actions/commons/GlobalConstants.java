package commons;

import java.io.File;

public class GlobalConstants {

	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String SEPARATOR_CHAR = File.separator;
	public static final String FILE_UPLOAD_FOLDER_PATH = PROJECT_PATH + SEPARATOR_CHAR + "fileUpload" + SEPARATOR_CHAR;
	public static final String EXTENT_REPORTS_OUTPUT = PROJECT_PATH + SEPARATOR_CHAR + "extentReports-output" + SEPARATOR_CHAR + "extentReports.html";

	public static final String JDK_VERSION = System.getProperty("java.version");

	public static final String CRUD_DATA_GRID_URL = "https://www.jqueryscript.net/demo/CRUD-Data-Grid-Plugin-jQuery-Quickgrid/";
	public static final String DYNAMIC_DATA_GRID_URL = "https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/";
	public static final String FILE_UPLOAD_URL = "https://blueimp.github.io/jQuery-File-Upload/";

	public static final long LONG_TIMEOUT = 30;
	public static final long ONE_SEC = 1;

}
