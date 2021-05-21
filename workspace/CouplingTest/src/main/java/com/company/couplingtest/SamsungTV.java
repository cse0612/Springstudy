package com.company.couplingtest;

public class SamsungTV implements TV {

	private Speaker speaker;	//SonySpeaker 선언
	private int price;
	
	public SamsungTV() {
		System.out.println("==> SamsungTV 객체 생성");
	}
	
	public void setSpeaker(Speaker speaker) {
		System.out.println("==> setSpeaker() 호출");
		this.speaker = speaker;
	}
	
	public void setPrice(int price) {
		System.out.println("==> setPrice() 호출");
		this.price = price;
	}
//	//생성자 인젝션 이용  (매개변수로 의존관계 객체 전달)
//	public SamsungTV(Speaker speaker) {
//		System.out.println("==> SamsungTV(2) 객체 생성");
//		this.speaker = speaker;
//	}
//	
//	//생성자 인젝션 이용  (매개변수로 의존관계 객체 전달)
//	public SamsungTV(Speaker speaker, int price) {
//		System.out.println("==> SamsungTV(3) 객체 생성");
//		this.speaker = speaker;
//		this.price = price;
//	}
	
	@Override
	public void powerOn() {
		System.out.println("SamsungTV -- 전원 켠다  (가격: " + price + ")");
	}

	@Override
	public void powerOff() {
		System.out.println("SamsungTV -- 전원 끈다");
	}

	@Override
	public void volumnUp() {
		speaker.volumeUp();
	}

	@Override
	public void volumnDown() {
		speaker.volumeDown();
	}

}
