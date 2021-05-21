package com.company.couplingtest;

public class AppleSpeaker implements Speaker {

	public AppleSpeaker() {
		System.out.println("==> AppleSpeaker 객체 생성");
	}
	
	@Override
	public void volumeUp() {
		System.out.println("AppleSpeaker -- 소리올린다");
	}

	@Override
	public void volumeDown() {
		System.out.println("AppleSpeaker -- 소리내린다");		
	}
	
}
