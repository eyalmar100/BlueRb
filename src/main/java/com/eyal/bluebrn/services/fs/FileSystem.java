package com.eyal.bluebrn.services.fs;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileSystem implements IFileSystem {

	private static final String EMPTY_JSON = "{}";

	@Override
	public String readFile(String relativePath) {
		try (FileInputStream fileInputStream = new FileInputStream(
				new File(this.getClass().getResource(relativePath).toURI()));
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();) {
			IOUtils.copy(fileInputStream, byteArrayOutputStream);
			return new String(byteArrayOutputStream.toByteArray());
		} catch (Exception exception) {
			log.error("Error reading file: {}", relativePath);
		}
		return EMPTY_JSON;
	}

}
