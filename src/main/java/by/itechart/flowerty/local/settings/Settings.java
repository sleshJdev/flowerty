package by.itechart.flowerty.local.settings;

import java.io.File;

/**
 * @author Eugene Putsykovich(slesh) Apr 21, 2015
 * 
 *         contains paths to storage files
 */

public final class Settings {
    private String picturesPath;
    private String attachmentsPath;
    private String birthdayTemplatePath;
    private String usFullName;
    
    public Settings() {
    }

    public Settings(String picturesPath, String attachmentsPath, String birthdayTemplatePath, String usFullName) {
	super();
	this.picturesPath = picturesPath;
	this.attachmentsPath = attachmentsPath;
	this.birthdayTemplatePath = birthdayTemplatePath;
	this.usFullName = usFullName;
    }

    public String getPicturesPath() {
	return picturesPath;
    }

    public void setPicturesPath(String picturesPath) {
	this.picturesPath = picturesPath;
	makeDirectory(picturesPath);
        System.out.println("pic path is : " + picturesPath);
    }

    public String getAttachmentsPath() {
	return attachmentsPath;
    }

    public void setAttachmentsPath(String attachmentsPath) {
	this.attachmentsPath = attachmentsPath;
	makeDirectory(attachmentsPath);
    }
    
    public String getBirthdayTemplatePath() {
        return birthdayTemplatePath;
    }

    public void setBirthdayTemplatePath(String birthdayTemplatePath) {
        this.birthdayTemplatePath = birthdayTemplatePath;
    }
    
    public String getUsFullName() {
        return usFullName;
    }

    public void setUsFullName(String usFullName) {
        this.usFullName = usFullName;
    }

    private void makeDirectory(String path) {
	File file = new File(path);
	if (!file.exists()) {
	    file.mkdirs();
	}
    }
}
