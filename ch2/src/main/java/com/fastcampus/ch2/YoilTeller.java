package com.fastcampus.ch2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 날짜 연월일을 입력하면, 날짜를 말해주는 프로그램
@Controller // Controller 를 통해서, 로컬프로그램을 원격프로그램으로 변경
public class YoilTeller { 
	// public static void main(String[] args) {
		@RequestMapping("/getYoil")
		public void main(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// 1. 입력
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		
		// 2. 작업
		int yyyy = Integer.parseInt(year);
		int mm = Integer.parseInt(month);
		int dd = Integer.parseInt(day);
		
		Calendar cal = Calendar.getInstance();
		cal.set(yyyy, mm - 1, dd);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1 = 일, 2 = 월 ...
		char yoil = "일월화수목금토".charAt(dayOfWeek);
		
		// 3. 출력
		response.setContentType("text/html"); // response 객체 형식을 적어야한다. 즉, 출력할 내용의 타입을 적어야한다.
		response.setCharacterEncoding("UTF-8"); // 텍스트도 인코딩을 해야지 한글이 깨지지 않는다.
		PrintWriter out = response.getWriter(); // response객체에서 브라우져로의 출력 스트림을 얻는다.
		out.println(year + "년" + month + "월" + day + "일은 "); 
		// 원래 제대로 하려면 HTML 형식을 갖춰서 해야한다.
		out.println(yoil + "요일입니다.");
	}
}
