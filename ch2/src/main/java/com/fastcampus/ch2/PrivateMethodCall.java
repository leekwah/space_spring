package com.fastcampus.ch2;

import java.lang.reflect.Method;

public class PrivateMethodCall {
	public static void main(String[] args) throws Exception {
		// Hello hello = new Hello(); // Hello ��ü ����
		// hello.main(); // public�� ���� �ܺ�ȣ�� ����, ������ private�� ���� �ܺ�ȣ�� �Ұ���
		
		// Reflection API�� ��� - Ŭ���� ������ ��� �ٷ� �� �ִ� ������ ����� �����ϴ� API
		// java.lnag.reflect ��Ű���� ����

		// Hello Ŭ������ Class ��ü(Ŭ������ ������ ��� �ִ� ��ü)�� ���´�.
		Class helloClass = Class.forName("com.fastcampus.app.Hello");
		// ��ȯŸ���� Object�� ����ȯ �ʿ� (�޼��尡 deprecated�Ǿ�����, ������� �ʾƵ� �ȴ�.)
		Hello hello = (Hello) helloClass.newInstance(); // Class��ü�� ���� ������ ��ü ����
		Method main = helloClass.getDeclaredMethod("main");
		main.setAccessible(true); // private�� main()�� ȣ�Ⱑ���ϰ� �Ѵ�.
		
		main.invoke(hello); // hello.main(); �� ���� ���̴�.
		
		// �̰� �� ������ SpringFrameWork�� Reflection API�� ���� ����ϱ� �����̴�.
		// private�ε��� ��밡���� ����, Java�� ReflectionAPI�� �̿��ϱ� �����̴�. 
	}
}
