package com.fastcampus.ch2;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// 날짜 연월일을 입력하면, 날짜를 말해주는 프로그램
@Controller // Controller 를 통해서, 로컬프로그램을 원격프로그램으로 변경
public class YoilTellerMVC { 
		@RequestMapping("/getYoilMVC") // RequestMapping은 중복되는 게 있으면 안된다.
		// public String main(int year, int month, int day, Model model) throws IOException{
		public ModelAndView main(int year, int month, int day) throws IOException{
			// 반환타입을 ModelAndView로 변경
			
			// 1. 유효성 검사 필요
			// if(isValid(year, month, day))
				// return "yoilError"; // WEB_INF/views/yoilError.jsp
			
			// 1. model and view의 경우
			ModelAndView mv = new ModelAndView();
			
			// 2. 요일 계산
			char yoil = getYoil(year, month, day);
			
			// 3. 계산한 결과를 Model에 저장
			// model.addAttribute("year", year);
			// model.addAttribute("month", month);
			// model.addAttribute("day", day);
			// model.addAttribute("yoil", yoil);
			
			// 3. 계산한 결과를 model에 저장
			mv.addObject("year", year);
			mv.addObject("month", month);
			mv.addObject("day", day);
			mv.addObject("yoil", yoil);
			
			// 4. 결과를 보여줄 view를 지정
			mv.setViewName("yoil");
			
			return mv;
			//return "yoil"; // WEB_INF/views/yoil.jsp
	}

		private boolean isValid(int year, int month, int day) {
			// 클래스 내에서만 쓰기에 private
			return true;
		}

		private char getYoil(int year, int month, int day) {
			Calendar cal = Calendar.getInstance();
			cal.set(year, month - 1, day);
			
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1 = 일, 2 = 월 ...
			return "일월화수목금토".charAt(dayOfWeek);
		}
}

