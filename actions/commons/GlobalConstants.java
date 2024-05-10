package commons;

import java.io.File;

public class GlobalConstants {

	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String SEPARATOR_CHAR = File.separator;
	public static final String FILE_UPLOAD_FOLDER_PATH = PROJECT_PATH + SEPARATOR_CHAR + "fileUpload" + SEPARATOR_CHAR;
	public static final String EXTENT_REPORTS_OUTPUT = PROJECT_PATH + SEPARATOR_CHAR + "extentReports-output" + SEPARATOR_CHAR + "extentReports.html";
	public static final String NOPCOMMERCE_DATA_JSON = PROJECT_PATH + File.separator + "resources" + File.separator + "nopCommerceData.json";

	public static final String OS_NAME = System.getProperty("os.name");;
	public static final String JDK_VERSION = System.getProperty("java.version");

	public static final String DEV_USER_NOPCOMMERCE_URL = "https://dev.nopcommerce.com/";
	public static final String TEST_USER_NOPCOMMERCE_URL = "https://test.nopcommerce.com/";
	public static final String DEMO_USER_NOPCOMMERCE_URL = "https://demo.nopcommerce.com/";
	public static final String STAGING_USER_NOPCOMMERCE_URL = "https://staging.nopcommerce.com/";
	public static final String PROD_USER_NOPCOMMERCE_URL = "https://prod.nopcommerce.com/";

	public static final String DEV_ADMIN_NOPCOMMERCE_URL = "https://admin-dev.nopcommerce.com/login?ReturnUrl=/admin/";
	public static final String TEST_ADMIN_NOPCOMMERCE_URL = "https://admin-test.nopcommerce.com/login?ReturnUrl=/admin/";
	public static final String DEMO_ADMIN_NOPCOMMERCE_URL = "https://admin-demo.nopcommerce.com/login?ReturnUrl=/admin/";
	public static final String STAGING_ADMIN_NOPCOMMERCE_URL = "https://admin-staging.nopcommerce.com/login?ReturnUrl=/admin/";
	public static final String PROD_ADMIN_NOPCOMMERCE_URL = "https://admin-prod.nopcommerce.com/login?ReturnUrl=/admin/";

	public static final String CRUD_DATA_GRID_URL = "https://www.jqueryscript.net/demo/CRUD-Data-Grid-Plugin-jQuery-Quickgrid/";
	public static final String DYNAMIC_DATA_GRID_URL = "https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/";
	public static final String FILE_UPLOAD_URL = "https://blueimp.github.io/jQuery-File-Upload/";

	public static final long LONG_TIMEOUT = 30;
	public static final long SHORT_TIMEOUT = 5;
	public static final long ONE_SEC = 1;

	enum BrowsersList {
		FIREFOX, CHROME, EDGE;
	}

	enum ServersList {
		DEV, TEST, STAGING, DEMO, PROD;
	}

	enum RolesList {
		ADMIN, USER;
	}

}
