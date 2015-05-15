package by.itechart.flowerty.web.controller.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Eugene Putsykovich(slesh) Apr 21, 2015
 *
 *         helper class for execute helpful work
 */
public final class FlowertUtil {
    public static String processMultipart(final String targetDirectory, MultipartFile multipartFile) throws IOException {
	String name = String.format("%s_%s", UUID.randomUUID().toString(), multipartFile.getOriginalFilename());
	File file = Paths.get(targetDirectory, name).toFile();
	OutputStream outputStream = new FileOutputStream(file);
	BufferedOutputStream buffereOutputStream = new BufferedOutputStream(outputStream);
	byte[] bytes = multipartFile.getBytes();
	buffereOutputStream.write(bytes);
	buffereOutputStream.close();
	
	return name;
    }
    
    public static void saveMultiparts(final String targetDirectory, MultipartFile[] multipartFiles) throws IOException {
	for (MultipartFile multipartFile : multipartFiles) {
	    processMultipart(targetDirectory, multipartFile);
	}
    }
}
