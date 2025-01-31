package com.sp.app.service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sp.app.entity.Board;
import com.sp.app.mapper.BoardMapper;
import com.sp.app.model.BoardDTO;
import com.sp.app.repository.BoardRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
  - 클래스 레벨에서 @Transactional(readOnly = true) 를
    설정하면 모든 메소드는 변경이 불가능하다.
  - 이럴 경우 insert, update, delete 를 하는 메소드의  
    메소드 레벨에서 @Transactional 애노테이션을 사용하여 
    변경이 가능하도록 설정해야 한다.
 */
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {
	private final BoardRepository boardRepository;
	private final BoardMapper mapper;
	
	@Transactional
	@Override
	public void insertBoard(Board entity) throws Exception {
		try {
			boardRepository.save(entity);
		} catch (Exception e) {
			log.info("insertBoard : ", e);
			throw e;
		}
	}

	@Transactional
	@Override
	public void updateBoard(Board entity) throws Exception {
		try {
			// save : 존재하면 수정, 없으면 추가
			boardRepository.save(entity);
		} catch (Exception e) {
			log.info("updateBoard : ", e);
			throw e;
		}
	}
	
	@Transactional
	@Override
	public void deleteBoard(long num) throws Exception{
		try {
			boardRepository.deleteById(num);
			
/*
			Board dto = boardRepository.findById(num)
                    .orElseThrow(() -> new IllegalAccessError("[num=" + num + "] 해당 게시글이 존재하지 않습니다."));
            boardRepository.delete(dto);      
*/                    			
		} catch (Exception e) {
			log.info("deleteBoard : ", e);
			throw e;
		}
	}

	@Override
	public Board findById(long num) {
		Board dto = null;
		
		try {
			// Optional<T> 클래스는 NullPointException 를 방지할 수 있도록 도와준다.
			// Optional<T>는 null이 올 수 있는 값을 감싸는 Wrapper 클래스로, 참조하더라도 NPE가 발생하지 않도록 도와준다.
			
			Optional<Board> board = boardRepository.findById(num);
			dto = board.get();
			
/*
			dto = boardRepository.findById(num)
                    .orElseThrow(() -> new IllegalAccessError("[num=" + num + "] 해당 게시글이 존재하지 않습니다."));
*/                    			
		}catch (NoSuchElementException e) {
			// board.get()에서 데이터가 존재하지 않는 경우
		} catch (Exception e) {
			log.info("findById : ", e);
		}
		
		return dto;
	}
	
	@Override
	public void updateHitCount(long num) throws Exception {
		try {
			mapper.updateHitCount(num);
		} catch (Exception e) {
			log.info("updateHitCount : ", e);
			
			throw e;
		}
	}

	@Override
	public int dataCount(Map<String, Object> map) {
		int result = 0;

		try {
			result = mapper.dataCount(map);
		} catch (Exception e) {
			log.info("dataCount : ", e);
		}

		return result;
	}

	@Override
	public List<BoardDTO> listBoard(Map<String, Object> map) {
		List<BoardDTO> list = null;

		try {
			list = mapper.listBoard(map);
		} catch (Exception e) {
			log.info("listBoard : ", e);
		}

		return list;
	}

	@Override
	public BoardDTO findByPrev(Map<String, Object> map) {
		BoardDTO dto = null;
		
		try {
			dto = mapper.findByPrev(map);
		} catch (Exception e) {
			log.info("findByPrev : ", e);
		}
		
		return dto;
	}

	@Override
	public BoardDTO findByNext(Map<String, Object> map) {
		BoardDTO dto = null;
		
		try {
			dto = mapper.findByNext(map);
		} catch (Exception e) {
			log.info("findByNext : ", e);
		}
		
		return dto;
	}

}
