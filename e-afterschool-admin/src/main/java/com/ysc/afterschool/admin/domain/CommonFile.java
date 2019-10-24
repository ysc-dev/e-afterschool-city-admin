package com.ysc.afterschool.admin.domain;

import lombok.Data;
import lombok.Getter;

@Data
public class CommonFile {
	
	public static final String CLASS_PATH = "class";
	public static final String INVITATION_PATH = "invitation";
	public static final String COMMUNITY_PATH = "community";


	private String fileName;

	private String contentType;
	
	private Long size;
	
	private FileType fileType;
	
	@Getter
	public enum FileType {
		IMAGE("image"),
		VIDEO("video");
		
		private String name;
		
		private FileType(String name) {
			this.name = name;
		}
		
		public static FileType stringToType(String value) {
			for (FileType type : FileType.values()) {
				if (value.contains(type.name)) {
					return type;
				}
			}
			
			return null;
		}
	}
}
