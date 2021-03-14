package com.ysc.afterschool.admin.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ysc.afterschool.admin.domain.CommonFile;
import com.ysc.afterschool.admin.domain.CommonFile.FileType;

/**
 * 파일 업로드 관리 서비스
 * 
 * @author hgko
 *
 */
@Service
public class FileUploadService {
	
	@Value("${resource.uploads.root}")
	private String uploadsRoot;

	/**
	 * 파일 업로드
	 * @param multipartFile
	 * @param path
	 * @return
	 */
	public CommonFile restore(MultipartFile multipartFile, String path) {
		CommonFile uploadedFile = new CommonFile();
		
		try {
			// 파일 정보
			String originFilename = multipartFile.getOriginalFilename();
			String extName = originFilename.substring(originFilename.lastIndexOf("."), originFilename.length());
			Long size = multipartFile.getSize();

			// 서버에서 저장 할 파일 이름
			FileType fileType = FileType.stringToType(multipartFile.getContentType());
			String saveFileName = getSaveFileName(extName, fileType);
			
			uploadedFile.setFileName(saveFileName);
			uploadedFile.setSize(size);
			uploadedFile.setContentType(multipartFile.getContentType());
			uploadedFile.setFileType(fileType);

//			System.out.println("originFilename : " + originFilename);
//			System.out.println("extensionName : " + extName);
//			System.out.println("size : " + size);
//			System.out.println("saveFileName : " + saveFileName);
			
			File folder = new File(path);
			if (!folder.exists())
				folder.mkdir();

			writeFile(multipartFile, path + "/" + saveFileName);
		} catch (IOException e) {
			// 원래라면 RuntimeException 을 상속받은 예외가 처리되어야 하지만
			// 편의상 RuntimeException을 던진다.
			// throw new FileUploadException();
			throw new RuntimeException(e);
		}
		
		return uploadedFile;
	}
	
	/**
	 * 파일 삭제
	 * @param path
	 * @param fileName
	 * @return
	 */
	public boolean fileDelete(String path, String fileName) {
		File file = new File(uploadsRoot + "/" + path + "/" + fileName);
		if (file.exists()) {
			return file.delete();
		}
		return false;
	}

	/**
	 * 현재 시간을 기준으로 파일 이름 생성
	 * @param extName
	 * @param contentType
	 * @return
	 */
	private String getSaveFileName(String extName, FileType fileType) {
		String fileName = "";

		Calendar calendar = Calendar.getInstance();
		fileName += fileType.getName();
		fileName += "_";
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += extName;

		return fileName;
	}

	/**
	 * 파일을 실제로 write 하는 메서드
	 * @param multipartFile
	 * @param saveFileName
	 * @throws IOException
	 */
	private void writeFile(MultipartFile multipartFile, String saveFileName) throws IOException {
		FileOutputStream fos = new FileOutputStream(uploadsRoot + "/" + saveFileName);
		fos.write(multipartFile.getBytes());
		fos.close();
	}
}
