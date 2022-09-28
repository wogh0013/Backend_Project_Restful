package com.example.demo.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.board.domain.BoardDto;
import com.example.demo.board.repository.BoardDao;

import lombok.RequiredArgsConstructor;

@Service("boardService")
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	private final BoardDao dao;

	@Override
	public List<BoardDto> getList(BoardDto dto) {
		return dao.getList(dto);
	}

	@Override
	public BoardDto getView(long id) {
		return dao.getView(id);
	}

	@Override
	public void insert(BoardDto dto) {
		dao.insert(dto);
	}

	@Override
	public int getTotalCnt(BoardDto dto) {
		return dao.getTotalCnt(dto);
	}

	@Override
	public void update(BoardDto dto) {
		dao.update(dto);
	}

	@Override
	public void delete(BoardDto dto) {
		dao.delete(dto);
	}
}






