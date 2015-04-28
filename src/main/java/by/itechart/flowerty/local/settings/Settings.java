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

    public Settings() {
    }

    public Settings(String picturesPath, String attachmentsPath) {
	super();
	this.picturesPath = picturesPath;
	this.attachmentsPath = attachmentsPath;
    }

    public String getPicturesPath() {
	return picturesPath;
    }

    public void setPicturesPath(String picturesPath) {
	this.picturesPath = picturesPath;
	makeDirectory(picturesPath);
    }

    public String getAttachmentsPath() {
	return attachmentsPath;
    }

    public void setAttachmentsPath(String attachmentsPath) {
	this.attachmentsPath = attachmentsPath;
	makeDirectory(attachmentsPath);
    }

    private void makeDirectory(String path) {
	File file = new File(path);
	if (!file.exists()) {
	    file.mkdirs();
	}
    }
}
