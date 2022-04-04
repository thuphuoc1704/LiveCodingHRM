package com.hrm.pagebaseUI;

import java.io.File;

public class PageBaseUI {
	public static final String URL_HRM="https://opensource-demo.orangehrmlive.com/index.php/auth/login";
	public static final String PROJECT_PATH=System.getProperty("user.dir");
	public static final String REPORTNG_SCREEENSHOT=PROJECT_PATH + File.separator + "ReportNGImage"+ File.separator;
	public static final String UPLOAD_FOLDER_PATH=PROJECT_PATH + File.separator + "uploadfile"+ File.separator;
	public static final String TEXTBOX_BY_ID="xpath=//input[@id='%s']";
	public static final String USERNAME_TEXTBOX="xpath=//input[@id='txtUsername']";
	public static final String PASSWORD_TEXTBOX="xpath=//input[@id='txtPassword']";
	public static final String BUTTON_BY_VALUE = "xpath=//input[@value='%s']";
	public static final String BUTTON_BY_ID_FORM_AND_BY_ID_VALUEBUTTON = "xpath=//form[@id='%s']//input[@value='%s']";
	public static final String LINK_BY_ID = "xpath=//a[@id='%s']";
	public static final String LINK_HEADER_BY_TEXT = "xpath=//a[string()='%s']"; 
	public static final String CHECKBOX_BY_NAME = "xpath=//input[@name='%s']/preceding::label[text()='%s']";
	public static final String DROPDOWNLIST_BY_ID = "xpath=//select[@id='%s']";
	public static final String UPLOAD_FILE = "xpath=//input[@type='file']";
	public static final String TABLE_HEADER_BY_ID_AND_ROW ="xpath=//table[@id='%s']//th[string()='%s']/preceding-sibling::th";
	public static final String TABLE_ROW_BY_COLUMNINDEX_AND_ROWINDEX = "xpath=//table[@id='%s']/tbody/tr[%s]/td[%s]";
	public static final String MESSAGE_UPLOAD_SUCCESS="xpath=//div[@class='message success fadable']";
	public static final String OPTION_OFF_SELECT="xpath=//select[@id='%s']//option[text()='%s']";
	public static final String COLUMN_FOR_SORT ="xpath=//table[@id='resultTable']//tbody//tr//td[%s]/a";
	public static final String COLUMN_FOR_SORT1 ="xpath=//table[@id='resultTable']//tbody//tr[%s]//td[4]/a";
	public static final String ADMIN_MENU ="xpath=//a[@class='firstLevelMenu']/b[text()='Admin']";
	public enum BROWSERLIST {
		CHROME, COCCOC, FIREFOX, SAFARI,EDGE;
	}
	
	
}
