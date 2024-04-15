package pageUIs.jQuery;

public class FileUploadPageUI {

	public static final String LOADED_FILE_NAME = "//p[text()='%s']";
	public static final String START_BUTTON_OF_FILE = LOADED_FILE_NAME + "/ancestor::tr//button[contains(@class,'start')]";
	public static final String UPLOADED_FILE_LINK = "//a[text()='%s']";
	public static final String UPLOADED_FILE_IMG = "//a[@title='%s']/img";
	public static final String DELETE_BUTTON_OF_FILE = UPLOADED_FILE_IMG + "/ancestor::tr//button[contains(@class,'delete')]";

}
