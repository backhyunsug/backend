package com.kosa.myapp;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

//localhost 나 127.0.0.1  브라우저에 따라서 다르게 동작
//* - 접속요청오는걸 모두 받겠다.
//실제 운영시에는 도메인명을 기술해야 한다
@CrossOrigin(origins="*", allowedHeaders = "*")  //접근허가를 받는 사이트의 도메인이 저장되어야 한다
@RestController
@RequiredArgsConstructor
@RequestMapping(value="/board")
public class BoardController {

	private final BoardService boardService;

	@GetMapping("/hello")
	public HashMap<String, Object>  hello()
	{
		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("hello", "Hello");
		return map;
	}


	@GetMapping("/list/{pg}")
	public HashMap<String, Object> 
	    getList(@PathVariable("pg")String pg, BoardDto dto)
	{
		HashMap<String, Object> map = new HashMap<String, Object>();
		Page<Board> list = boardService.getList(pg); //new ArrayList<BoardDto>();
//		for(int i=1; i<=20; i++)
//			list.add(new BoardDto(
//			""+i, "제목"+i,  "작성자"+i, "내용"+i, "file"+i, "이미지"+i
//			));
		map.put("totalCnt", boardService.getTotalCount());
		map.put("list", list);
		
		return map;
	}

	@GetMapping("/insert")
	public HashMap<String, Object> insert(BoardDto dto)
	{
		HashMap<String, Object> map = new HashMap<String, Object>();
		Board board = Board.builder().
						title(dto.getTitle())
						.contents(dto.getContents())
						.build();
		boardService.insert(board);
		map.put("result", "SUCCESS");
		return map;
	}
}



