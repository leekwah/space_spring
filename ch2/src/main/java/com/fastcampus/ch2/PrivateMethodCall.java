package com.fastcampus.ch2;

import java.lang.reflect.Method;

public class PrivateMethodCall {
	public static void main(String[] args) throws Exception {
		// Hello hello = new Hello(); // Hello 객체 생성
		// hello.main(); // public일 때는 외부호출 가능, 하지만 private일 때는 외부호출 불가능
		
		// Reflection API를 사용 - 클래스 정보를 얻고 다룰 수 있는 강력한 기능을 제공하는 API
		// java.lnag.reflect 패키지를 제공

		// Hello 클래스의 Class 객체(클래스의 정보를 담고 있는 객체)를 얻어온다.
		Class helloClass = Class.forName("com.fastcampus.app.Hello");
		// 반환타입이 Object라서 형변환 필요 (메서드가 deprecated되었지만, 상관하지 않아도 된다.)
		Hello hello = (Hello) helloClass.newInstance(); // Class객체가 가진 정보로 객체 생성
		Method main = helloClass.getDeclaredMethod("main");
		main.setAccessible(true); // private인 main()을 호출가능하게 한다.
		
		main.invoke(hello); // hello.main(); 과 같은 것이다.
		
		// 이걸 한 이유는 SpringFrameWork가 Reflection API를 많이 사용하기 때문이다.
		// private인데도 사용가능한 것은, Java의 ReflectionAPI를 이용하기 때문이다. 
	}
}
