package com.rabbitforever.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
//	private String DATASET_FOLDER;
//	private SysProperties sysProperties;
	private String getClassName() {
		return this.getClass().getName();
	}
	public FileUtils() {
//		sysProperties = new SysProperties();
//		DATASET_FOLDER =sysProperties.getTestFolderRoot();
	}
	
//	public Icon readImage(String fileName){
//		Icon icon = null;
//		try{
//			String buttonsIconsFolder = sysProperties.getButtonsIconsRoot();
//			if(buttonsIconsFolder != null){
//				String fullPath = buttonsIconsFolder + "/" + fileName;
//				icon = new ImageIcon(fullPath);
//			}
//		} catch (Exception e){
//			logger.error(className + ".readImage() - ", e);
//		} finally{
//			//
//		}
//		return icon;
//	}
	
//	public List<String> getScopeFileNameList() throws Exception{
//		List<String> scopeFileNameList = null;
//		File directory = null;
//		try{
//			boolean isDirExisted = isDirExisted(DATASET_FOLDER);
//			if (isDirExisted){
//				scopeFileNameList = new ArrayList<String>();
//				directory = new File(DATASET_FOLDER);
//				for (File fileEntry: directory.listFiles()){
//					if (fileEntry.isFile()){
//						String fileName = fileEntry.getName();
//						if (fileName.contains(sysProperties.getScopeFileNameExt())){
//							scopeFileNameList.add(fileEntry.getName());
//						}
//					}
//				}
//				Collections.sort(scopeFileNameList);
//			}
//		} catch (Exception e){
//			logger.error(className + ".getScopeFileNameList() - ", e);
//		} finally{
//			if (directory != null){
//				directory = null;
//			}
//		}
//		return scopeFileNameList;
//	}
	
//	public void writeText2File(String content, String fileName) throws Exception{
//		File file = null;
//		OutputStreamWriter outputStreamWriter = null;
//		FileOutputStream fileOutputStream = null;
//	
//		BufferedWriter bw = null;
//		try {
//			
//			file = new File(fileName);
//
//			// if file doesnt exists, then create it
//			if (!file.exists()) {
//				file.createNewFile();
//			}
//			fileOutputStream = new FileOutputStream(file);
//			outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
//			bw = new BufferedWriter(outputStreamWriter);
//			bw.write(content);
//			bw.close();
//
//
//		} catch (Exception e) {
//			logger.error(className + ".writeText2File() - content=" + content + ",fileName=" + fileName, e);
//			throw e;
//		} finally{
//			if (file != null){
//				file = null;
//			}
//			if (fileOutputStream != null){
//				fileOutputStream.close();
//				fileOutputStream = null;
//			}
//			if (outputStreamWriter != null){
//				outputStreamWriter.close();
//				outputStreamWriter = null;
//			}
//
//		}
//	}
	
//	public void writeText2XmlFile(String content, String fileName) throws Exception{
//		String fileNameString = null;
//		try {
//			createDirectoryIfNotExisted(DATASET_FOLDER);
//			fileNameString = DATASET_FOLDER + "/" + fileName;
//			writeText2File(content, fileNameString);
//		} catch (Exception e) {
//			logger.error(className + ".writeText2XmlFile() - content=" + content + ",fileName=" + fileName, e);
//		} finally{
//			if (fileNameString != null){
//				fileNameString = null;
//			}
//		}
//	}
//	
	public boolean isDirExisted(String directoryName) throws Exception {
		boolean isDirExisted = false;
		File theDir = null;
		try {
			  theDir = new File(directoryName);
			  
			  if (theDir.exists()){
				  isDirExisted = true;
			  }


		} catch (Exception e) {
			logger.error(getClassName() + ".isDirExisted() - directoryName=" + directoryName, e);
		} finally{
			if (theDir != null){
				theDir = null;
			}
		}
		return isDirExisted;
	}
	public boolean isFileExisted(String fileName) throws Exception {
		boolean isDirExisted = false;
		File theFile = null;
		try {
			theFile = new File(fileName);
			  
			  if (theFile.exists() && !theFile.isDirectory()){
				  isDirExisted = true;
			  }


		} catch (Exception e) {
			logger.error(getClassName() + ".isFileExisted() - fileName=" + fileName, e);
		} finally{
			if (theFile != null){
				theFile = null;
			}
		}
		return isDirExisted;
	}
	
	public void createDirectoryIfNotExisted(String directoryName) throws Exception {
		File theDir = null;
		try {
			  theDir = new File(directoryName);
			  
			  if (!theDir.exists()){
			    theDir.mkdir();
			  }


		} catch (Exception e) {
			logger.error(getClassName() + ".createDirectoryIfNotExisted() - directoryName=" + directoryName, e);
		} finally{
			if (theDir != null){
				theDir = null;
			}
		}
	}
	
	public List<String> readFromFile(String fileName) throws Exception {
		BufferedReader br = null;
		List<String> stringList = new ArrayList<String>();
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(fileName));
			while ((sCurrentLine = br.readLine()) != null) {
				stringList.add(sCurrentLine);
			}

		} catch (Exception e) {
			logger.error(getClassName() + ".readFromFile() - fileName=" + fileName, e);
			throw e;
		} finally {
			try {
				if (br != null){
					br.close();
				}
			} catch (IOException ex) {
				logger.error(getClassName() + ".readFromFile() - fileName=" + fileName, ex);
				throw ex;
			}
		}
		return stringList;
	}
//	public byte[] readFileToByteArray(String fileName) throws Exception {
//		InputStream inputStream = null;
//		byte[] byteArray = null;
//		try {
//			inputStream = new FileInputStream(fileName);
//			byteArray = IOUtils.toByteArray(inputStream);
//		} catch (Exception e) {
//			logger.error(getClassName() + ".readFileToByteArray() - fileName=" + fileName, e);
//			throw e;
//		} finally {
//			try {
//				if (inputStream != null){
//					inputStream.close();
//				}
//			} catch (IOException ex) {
//				logger.error(getClassName() + ".readFileToByteArray() - fileName=" + fileName, ex);
//				throw ex;
//			}
//		}
//		return byteArray;
//	}
//	public List<String> readFromFile(String fileName) throws Exception {
//		BufferedReader br = null;
//		List<String> stringList = new ArrayList<String>();
//		try {
//			String sCurrentLine;
//			br = new BufferedReader(new FileReader(DATASET_FOLDER + "/" + fileName));
//			while ((sCurrentLine = br.readLine()) != null) {
//				stringList.add(sCurrentLine);
//			}
//
//		} catch (IOException e) {
//			logger.error(className + ".readFromFile() - fileName=" + fileName, e);
//		} finally {
//			try {
//				if (br != null){
//					br.close();
//				}
//			} catch (IOException ex) {
//				ex.printStackTrace();
//			}
//		}
//		return stringList;
//	}
}
