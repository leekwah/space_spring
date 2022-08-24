package com.fastcampus.ch2;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.StringJoiner;

public class MethodInfo {
	public static void main(String[] args) throws Exception{
		
		// 1. YoilTeller Ŭ������ ��ü�� ����
		// Class clazz = Class.forName("com.fastcampus.ch2.YoilTeller");
		Class clazz = Class.forName("com.fastcampus.ch2.YoilTellerMVC");
		Object obj = clazz.newInstance();
		
		// 2. ��� �޼��� ������ �����ͼ� �迭�� ����
		Method[] methodArr = clazz.getDeclaredMethods();
		
		for(Method m : methodArr) {
			String name = m.getName(); // �޼��� �̸�
			Parameter[] paramArr = m.getParameters(); // �Ű����� ���
//			Class[] paramTypeArr = m.getParameterTypes();
			Class returnType = m.getReturnType(); // ��ȯ Ÿ��
			
			StringJoiner paramList = new StringJoiner(", ", "(", ")"); // ������, ���λ�, ���̻�
			
			for(Parameter param : paramArr) {
				String paramName = param.getName();
				Class  paramType = param.getType();
				
				paramList.add(paramType.getName() + " " + paramName);
			}
			
			System.out.printf("%s %s%s%n", returnType.getName(), name, paramList);
		}
	} // main
}

/* [������]
���� �ٲٱ� ��(1.6)
void main(javax.servlet.http.HttpServletRequest arg0, javax.servlet.http.HttpServletResponse arg1)

���� �ٲ� �� (11)
void main(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)

YoilTellerMVC�� ���� ����
org.springframework.web.servlet.ModelAndView main(int year, int month, int day)
char getYoil(int year, int month, int day)
boolean isValid(int year, int month, int day)

*/