package com.example.demo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseDto {
	private String key;
	private String keyword;
	private int pg;
	private int pageSize=10;
	private int pgGroup=5;
	private int start=0;
	private int rnum=0;
}
