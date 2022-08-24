package com.fastcampus.ch2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// ��¥ �������� �Է��ϸ�, ��¥�� �����ִ� ���α׷�
@Controller // Controller �� ���ؼ�, �������α׷��� �������α׷����� ����
public class YoilTeller { 
	// public static void main(String[] args) {
		@RequestMapping("/getYoil")
		public void main(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// 1. �Է�
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		
		// 2. �۾�
		int yyyy = Integer.parseInt(year);
		int mm = Integer.parseInt(month);
		int dd = Integer.parseInt(day);
		
		Calendar cal = Calendar.getInstance();
		cal.set(yyyy, mm - 1, dd);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1 = ��, 2 = �� ...
		char yoil = "�Ͽ�ȭ�������".charAt(dayOfWeek);
		
		// 3. ���
		response.setContentType("text/html"); // response ��ü ������ ������Ѵ�. ��, ����� ������ Ÿ���� ������Ѵ�.
		response.setCharacterEncoding("UTF-8"); // �ؽ�Ʈ�� ���ڵ��� �ؾ��� �ѱ��� ������ �ʴ´�.
		PrintWriter out = response.getWriter(); // response��ü���� ���������� ��� ��Ʈ���� ��´�.
		out.println(year + "��" + month + "��" + day + "���� "); 
		// ���� ����� �Ϸ��� HTML ������ ���缭 �ؾ��Ѵ�.
		out.println(yoil + "�����Դϴ�.");
	}
}
