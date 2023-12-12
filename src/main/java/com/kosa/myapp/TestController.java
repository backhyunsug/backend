package com.kosa.myapp;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController 
public class TestController {
	
	//add?x=5&y=8  -->  add/5/8 
	@GetMapping("/add/{x}/{y}")
	public HashMap<String, String>add( 
			@PathVariable("x")int x, 
			@PathVariable("y")int y)
	{
		int result = x+y;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("x", String.valueOf(x));
		map.put("y", String.valueOf(y));
		map.put("result", String.valueOf(result));
		
		return map;
	}
	
	//add?x=5&y=8  -->  add/5/8
	
	@PostMapping("/add_post/{x}/{y}")
	public HashMap<String, String>add_post( 
			@PathVariable("x")int x, 
			@PathVariable("y")int y)
	{
		int result = x+y;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("x", String.valueOf(x));
		map.put("y", String.valueOf(y));
		map.put("result", String.valueOf(result));
		
		return map;
	}

	//postman body - x-www-form-urlencoded  <form 태그로 전송
	@PostMapping("/add_form")
	public HashMap<String, String>add_form(int x, int y)
	{
		int result = x+y;
		HashMap<String, String> map = new HashMap<String, String>();
		//form  태그에 enctype="multipart/form-data" 로 전송하면 
		//request  객체로 값을 못받고 MultipartResolver 에 의해 값을 처리
		//해야 한다. 스프링부트가 사고 안나게 내부적인 처리가 된다.  
		//map.put("req_x", req.getParameter("x")); //서블릿에서 작업함 
		//map.put("req_y", req.getParameter("y"));
		
		//String.valueOf(기보타입변수) -> 문자열로 전환해준다 
		//x(int) => int 는 객체가 아니다. toString()함수 없음 
		//java int => 객체로 전환해야 할때가 있는데 Integer, Float, Double
		//wrapper class 들로 기본값을 감싸서 개체로 전환할때는 toString()
		//함수가 있다. 그냥 기본값 자체로 개체로 전환되지 않는다 
		//c#은 int와 Integer 간에 기본타입과 객체타입간에 전환이 자유자재임 
		//  s = new Integer(x); s.toString();
		// x + "" : 앞에 스트링이 아닌 타입을 스트링으로 전환시켜서 더한다 
		map.put("x",  String.valueOf(x));
		map.put("y",  String.valueOf(x));
		
		map.put("result", String.valueOf(result));
		
		
		return map;
	}
	
	@PostMapping("/add_json")
	public HashMap<String, String>add_json( 
			@RequestBody HashMap<String, String> paramMap)
	{
		//json으로 받을때는 @RequestBody로 받아야 한다. 
		//기본타입변수로는 못 받는다 
		//HashMap 이나 또는 Dto로만 받을 수 있다
		int result = Integer.parseInt(paramMap.get("x"))
				+Integer.parseInt(paramMap.get("y"));
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("x", paramMap.get("x"));
		map.put("y", paramMap.get("x"));
		map.put("result", String.valueOf(result));
		
		return map;
	}
	
	/*
	 *   url : score_json
	 *   {
	 *   	"name":"홍길동",
	 *      "kor":90,
	 *      "eng":80,
	 *      "mat":70
	 *   }
	 *   결과
	 *   {
	 *      "name":"홍길동",
	 *      "kor":90,
	 *      "eng":80,
	 *      "mat":70,
	 *      "total":240,
	 *      "avg":80
	 *   }
	 */
	
	@PostMapping("/score_json")
	public HashMap<String, Object>score_json( 
			@RequestBody ScoreDto dto)
	{
		HashMap<String, Object>resultMap
		    = new HashMap<String, Object>();
		
		int total = dto.getKor()+dto.getEng()+dto.getMat();
		dto.setTotal(total);
		dto.setAvg(total/3);
		
		resultMap.put("name", dto.getName());
		resultMap.put("kor",  dto.getKor());
		resultMap.put("eng", dto.getEng());
		resultMap.put("mat", dto.getName());
		resultMap.put("total", dto.getTotal());
		resultMap.put("avg", dto.getAvg());
		
		return resultMap;
	}
	
	@PostMapping("/score_json2")
	public ScoreDto score_json2(@RequestBody ScoreDto dto)
	{
		int total = dto.getKor()+dto.getEng()+dto.getMat();
		dto.setTotal(total);
		dto.setAvg(total/3);
		
		return dto;
	}
	
}





