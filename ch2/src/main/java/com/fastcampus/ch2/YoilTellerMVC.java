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

// ��¥ �������� �Է��ϸ�, ��¥�� �����ִ� ���α׷�
@Controller // Controller �� ���ؼ�, �������α׷��� �������α׷����� ����
public class YoilTellerMVC { 
		@RequestMapping("/getYoilMVC") // RequestMapping�� �ߺ��Ǵ� �� ������ �ȵȴ�.
		// public String main(int year, int month, int day, Model model) throws IOException{
		public ModelAndView main(int year, int month, int day) throws IOException{
			// ��ȯŸ���� ModelAndView�� ����
			
			// 1. ��ȿ�� �˻� �ʿ�
			// if(isValid(year, month, day))
				// return "yoilError"; // WEB_INF/views/yoilError.jsp
			
			// 1. model and view�� ���
			ModelAndView mv = new ModelAndView();
			
			// 2. ���� ���
			char yoil = getYoil(year, month, day);
			
			// 3. ����� ����� Model�� ����
			// model.addAttribute("year", year);
			// model.addAttribute("month", month);
			// model.addAttribute("day", day);
			// model.addAttribute("yoil", yoil);
			
			// 3. ����� ����� model�� ����
			mv.addObject("year", year);
			mv.addObject("month", month);
			mv.addObject("day", day);
			mv.addObject("yoil", yoil);
			
			// 4. ����� ������ view�� ����
			mv.setViewName("yoil");
			
			return mv;
			//return "yoil"; // WEB_INF/views/yoil.jsp
	}

		private boolean isValid(int year, int month, int day) {
			// Ŭ���� �������� ���⿡ private
			return true;
		}

		private char getYoil(int year, int month, int day) {
			Calendar cal = Calendar.getInstance();
			cal.set(year, month - 1, day);
			
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1 = ��, 2 = �� ...
			return "�Ͽ�ȭ�������".charAt(dayOfWeek);
		}
}

