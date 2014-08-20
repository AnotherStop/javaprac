//package com.mobileStorage

//computer class
class Computer{
	void readFromStorage(FlashDisk f){
		f.loadDriver();
		f.read();
	}

	void writeToStorage(FlashDisk f){
		f.loadDriver();
		f.write();
	}
}

interface FlashDisk{
	void loadDriver();
	void read();
	void write();
}

class PortableHarddrive implements FlashDisk{
	public void loadDriver(){
		System.out.println("Load driver for portable harddrive");
	}

	public void read(){
		System.out.println("Read in portable harddrive");
	}

	public void write(){
		System.out.println("Write in portable harddrive");
	}
}

class USB implements FlashDisk{
	public void loadDriver(){
		System.out.println("Load driver for USB");
	}

	public void read(){
		System.out.println("Read in USB");
	}

	public void write(){
		System.out.println("Write in USB");
	}
}

class MP3 implements FlashDisk{
	public void loadDriver(){
		System.out.println("Load driver for MP3");
	}

	public void read(){
		System.out.println("Read in Mp3");
	}

	public void write(){
		System.out.println("Write in Mp3");
	}
}

public class TestInterface{

	public static void main(String[] args){
		FlashDisk pHD = new PortableHarddrive();
		FlashDisk usb = new USB();
		FlashDisk mp3 = new MP3();

		Computer computer = new Computer();
		computer.readFromStorage(pHD);
		computer.readFromStorage(usb);
		computer.writeToStorage(usb);
	}
}