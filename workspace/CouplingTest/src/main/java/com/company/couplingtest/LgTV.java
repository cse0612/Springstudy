package com.company.couplingtest;

public class LgTV implements TV {

	@Override
	public void powerOn() {
		System.out.println("LGTV -- 전원 켠다");
	}

	@Override
	public void powerOff() {
		System.out.println("LGTV -- 전원 끈다");
	}

	@Override
	public void volumnUp() {
		System.out.println("LGTV -- 소리 올린다");
	}

	@Override
	public void volumnDown() {
		System.out.println("LGTV -- 소리 내린다");
	}
}
