package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 1. ���� ȣ�Ⱑ���� ���α׷����� ���
@Controller
public class Hello {
	int iv = 10; // instance ���� (iv)
	static int cv = 20; // static ���� (cv)
	
	
	// 2. URL�� �޼��带 ����
	@RequestMapping("/hello")
	private void main() { // �ν��Ͻ� �޼��� - iv, cv �� �� ��� ����
		// ���� public void main(String[] args)�� private void main(String[] args)�� ����
		// public�� �ƴ� private�� ȣ�� ����? -> �����ϴ�.
		// Main.java���� ��������
		// ---
		// �ٸ�����) static�� �޼��尡 �ƴѵ���, ȣ���� �����ϴ�. �� ������?
		// �ν��Ͻ� �޼����̴�. -> ��ü ���� �� ȣ�� ����
		// �߰����� ������ ��ü ������ ���ش�. (��Ĺ�� ���ش�.)
		// ���Ŀ� �޼��� ȣ���� �ȴ�.
		// �޼��� ���ο��� ��ü ������ ���ִ� ���� �ִ�.
		System.out.println("Hello - private");
		System.out.println(cv); // OK
		System.out.println(iv); // OK		
	}
	
	public static void main2(String[] args) { // static �޼��� - cv�� ��밡��
		System.out.println(cv); // OK
		// System.out.println(iv); // ERORR
		
		// �׷��⿡, static�� ���� ���� �ƴ�, �ν��Ͻ� �޼��带 ���� ���� �����ϴ�.
		// ������ iv�� �� �� �ֱ� �����̴�.
	}
}
