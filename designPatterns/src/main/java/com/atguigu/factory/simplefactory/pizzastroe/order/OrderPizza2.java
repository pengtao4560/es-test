package com.atguigu.factory.simplefactory.pizzastroe.order;

import com.atguigu.factory.simplefactory.pizzastroe.SimpleFactory;
import com.atguigu.factory.simplefactory.pizzastroe.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class OrderPizza2 {

	Pizza pizza = null;
	String orderType = "";
	public OrderPizza2() {

		do {
			orderType = getType();
			pizza = SimpleFactory.createPizza2(orderType);

			// ���pizza
			if (pizza != null) { // �����ɹ�
				pizza.prepare();
				pizza.bake();
				pizza.cut();
				pizza.box();
			} else {
				System.out.println(" ��������ʧ�� ");
				break;
			}
		} while (true);
	}

	// дһ�����������Ի�ȡ�ͻ�ϣ����������������
	private String getType() {
		try {
			BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("input pizza ����:");
			String str = strin.readLine();
			return str;
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
}
