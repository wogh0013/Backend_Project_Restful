package com.example.demo.board.domain;

import com.example.demo.common.BaseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto extends BaseDto {

	private long id = -1;
	private String title;
	private String writer;
	private String contents;
	private String wdate;
	private int hit;
	private String filename;
	private String image_url;
}
