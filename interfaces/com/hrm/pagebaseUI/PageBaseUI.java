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
	////table[@id='resultTable']//a[text()='Employee']//preceding::th//following::tbody/tr[6]/td[2]
	////table[@id='resultTable']//th[string()='Job Title']/preceding-sibling::th
	
}