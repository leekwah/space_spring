package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 1. 원격 호출가능한 프로그램으로 등록
@Controller
public class Hello {
	int iv = 10; // instance 변수 (iv)
	static int cv = 20; // static 변수 (cv)
	
	
	// 2. URL과 메서드를 연결
	@RequestMapping("/hello")
	private void main() { // 인스턴스 메서드 - iv, cv 둘 다 사용 가능
		// 원래 public void main(String[] args)을 private void main(String[] args)로 수정
		// public이 아닌 private도 호출 가능? -> 가능하다.
		// Main.java에서 정리했음
		// ---
		// 다른내용) static이 메서드가 아닌데도, 호출이 가능하다. 그 이유는?
		// 인스턴스 메서드이다. -> 객체 생성 후 호출 가능
		// 중간에서 누군가 객체 생성을 해준다. (톰캣이 해준다.)
		// 이후에 메서드 호출이 된다.
		// 메서드 내부에서 객체 생성을 해주는 것이 있다.
		System.out.println("Hello - private");
		System.out.println(cv); // OK
		System.out.println(iv); // OK		
	}
	
	public static void main2(String[] args) { // static 메서드 - cv만 사용가능
		System.out.println(cv); // OK
		// System.out.println(iv); // ERORR
		
		// 그렇기에, static을 쓰는 것이 아닌, 인스턴스 메서드를 쓰는 것이 유리하다.
		// 이유는 iv를 쓸 수 있기 때문이다.
	}
}
