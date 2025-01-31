package com.sp.app.service;

import java.util.List;
import java.util.Map;

import com.sp.app.entity.Board;
import com.sp.app.model.BoardDTO;

public interface BoardService {
	public void insertBoard(Board entity) throws Exception;
	public void updateBoard(Board entity) throws Exception;
	public void deleteBoard(long num) throws Exception;
	public Board findById(long num);
	
	public void updateHitCount(long num) throws Exception;
	
	public int dataCount(Map<String, Object> map);
	public List<BoardDTO> listBoard(Map<String, Object> map);
	public BoardDTO findByPrev(Map<String, Object> map);
	public BoardDTO findByNext(Map<String, Object> map);
}
