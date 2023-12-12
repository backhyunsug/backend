package com.kosa.myapp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;
import lombok.Builder;

@Getter
@Setter
@NoArgsConstructor(access=AccessLevel.PUBLIC) //기본생성자
public class BoardDto {
	private String id="";
	private String title="";
	private String writer="";
	private String contents="";
	private String filename="";
	private String image_url="";
	
	@Builder
	public BoardDto(String id, String title, String writer, String contents, String filename, String image_url) {
		super();
		this.id = id;
		this.title = title;
		this.writer = writer;
		this.contents = contents;
		this.filename = filename;
		this.image_url = image_url;
	}
	
	
	
}
