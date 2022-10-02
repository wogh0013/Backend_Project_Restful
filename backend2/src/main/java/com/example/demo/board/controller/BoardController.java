package com.example.demo.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.board.domain.BoardDto;
import com.example.demo.board.service.BoardService;
import com.example.demo.common.FileUploadUtil;

@CrossOrigin("*")
@RestController
public class BoardController {


	@Value("${fileUploadPath}")
	String fileUploadPath;

	@Value("${domain}")
	String domain;

	@Resource(name = "boardService")
	BoardService boardService;

	@GetMapping("/board/list/{pg}")
	HashMap<String, Object> getList(@PathVariable("pg") int pg, BoardDto dto) {
		dto.setStart((pg - 1) * dto.getPageSize());
		HashMap<String, Object> map = new HashMap<>();
		map.put("totalCnt", boardService.getTotalCnt(dto));
		map.put("list", boardService.getList(dto));

		return map;
	}

	@GetMapping("/board/view/{id}")
	BoardDto getView(@PathVariable("id") long id) {
		return boardService.getView(id);
	}

	@PostMapping("/board/insert")
	@ResponseStatus(HttpStatus.CREATED)
	Map<String, String> insert(MultipartFile file, @RequestBody BoardDto dto) {
		String uploadDir = fileUploadPath + "/image";
		if (file != null) {
			try {
				String filename = FileUploadUtil.upload(uploadDir, file);
				dto.setFilename(filename);
				dto.setImage_url(domain + "/" + uploadDir + "/" + filename);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		boardService.insert(dto);
		Map<String, String> map = new HashMap<>();
		map.put("result", "success");

		return map;
	}

	@DeleteMapping("/board/delete/{id}")
	Map<String, String> delete(@PathVariable("id") long id, HttpServletRequest req) {
		BoardDto dto = new BoardDto();
		dto.setId(id);
		boardService.delete(dto);
		Map<String, String> map = new HashMap<>();
		map.put("result", "success");

		return map;
	}

	@PatchMapping("/board/update/{id}")
	Map<String, String> update(@PathVariable("id") long id, MultipartFile file, BoardDto dto) {
		String uploadDir = fileUploadPath + "/image";
		if (file != null) {
			try {
				String filename = FileUploadUtil.upload(uploadDir, file);
				dto.setFilename(filename);
				dto.setImage_url(domain + "/" + uploadDir + "/" + filename);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		boardService.update(dto);
		Map<String, String> map = new HashMap<>();
		map.put("result", "success");

		return map;
	}

}






