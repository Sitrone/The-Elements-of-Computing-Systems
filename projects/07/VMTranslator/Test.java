package com.elements.vm;

import java.io.File;
import java.util.regex.Pattern;

public class Test
{
	Vmtranslator vm = new Vmtranslator();
	public static void main(String[] args){
		Vmtranslator vm = new Vmtranslator();
		String file = "C:\\Program Files\\GreenSoftware\\nand2tetris\\nand2tetris\\projects\\07";
		
		
		
	}
	
	private static void test(String str){
		File root = new File(str);
		File[] list = root.listFiles();
		
		if(null == list){
			return ;
		}
		for(File f : list){
			if(f.isDirectory()){
				System.out.println("Dir: " + f.getAbsolutePath());
				test(f.getAbsolutePath());
			}else if(f.isFile()){
				System.out.println("inFile: " + f.getAbsolutePath());
				System.out.println("outFile: " + getFileName(f.getName()));
				Pattern p = Pattern.compile(".asm");
				if (p.matcher(f.getAbsolutePath()) != null){
//					vm.process(f.getAbsolutePath(), getFileName(f.getName()));
				}
				
			}
		}
	}
	
	private static String getFileName(String in){
		String root = "C:\\Program Files\\GreenSoftware\\nand2tetris\\nand2tetris\\projects\\06\\";
		int indext = in.lastIndexOf(".");
		return new String (root + in.substring(0, indext) + ".hack");
	}
}
