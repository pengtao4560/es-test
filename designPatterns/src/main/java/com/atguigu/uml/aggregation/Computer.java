package com.atguigu.uml.aggregation;
/** 鼠标和显示器可以和电脑分离，所以是聚合关系 */
public class Computer {
	/** 鼠标 */
	private Mouse mouse;
	/** 显示器 */
	private Moniter moniter;
	public void setMouse(Mouse mouse) {
		this.mouse = mouse;
	}
	public void setMoniter(Moniter moniter) {
		this.moniter = moniter;
	}

}

class Moniter {

}

class Mouse {

}
