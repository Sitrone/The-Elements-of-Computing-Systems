package com.elements.assembler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Assembler {
	
	private Parser parser;
	private SymboleTable sTable;
	private BufferedReader br = null;
	private BufferedWriter bw = null;
	
	public Assembler(){
		parser = new Parser();
		sTable = new SymboleTable();
	}
	
	public void exec(String inFile, String outFile){
		process1(inFile);
		
		process2(inFile, outFile);
	}
	
	private void process1(String inFile){
		int i = 0;
		try {
			br = new BufferedReader(new FileReader(inFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String line = null;
		try {
			while((line = br.readLine()) != null){
				// 跳过空行和注释行
				if(line.equals("") || line.trim().startsWith("//")){
					continue;
				}
				
				if(parser.commandType(line) == CmdConst.L_COMMAND){
					String symbol = parser.symbol(line);
					if(!(sTable.contains(symbol))){
						sTable.addEntry(symbol, i);
					}
				}else{
					i++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try{
				if(null != br){
					br.close();
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	private void process2(String inFile, String outFile){
		int add = sTable.getNextAddress();
		
		try {
			br = new BufferedReader(new FileReader(inFile));
			bw = new BufferedWriter(new FileWriter(outFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String line = null;
		try {
			while((line = br.readLine()) != null){
				// 跳过空行和注释行
				if(line.equals("") || line.trim().startsWith("//")){
					continue;
				}
				
				String mCode = "";
				String cType = parser.commandType(line);
				switch(cType){
				case CmdConst.A_COMMAND:
					String aCmd = parser.symbol(line);
					if(!isNum(aCmd)){
						if(!(sTable.contains(aCmd))){
							sTable.addEntry(aCmd, add++);
							mCode += zfill(mCode);
						}else{
							mCode += zfill(Integer.toBinaryString(sTable.getAddress(aCmd)));
						}
					}else{
						mCode += zfill(Integer.toBinaryString(Integer.parseInt(aCmd)));
					}
					bw.write("0" + mCode + "\n");
					break;
					
				case CmdConst.C_COMMAND:
					String dest = Code.dest(parser.dest(line));
					String comp = Code.comp(parser.comp(line));
					String jump = Code.jump(parser.jump(line));
					bw.write("111" + comp + dest + jump + "\n");
					break;
				}
			
				bw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try{
				if(null != br){
					br.close();
				}
			}catch(IOException e){
				e.printStackTrace();
			}
			
			try{
				if(null != bw){
					bw.close();
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	
	
	private boolean isNum(String target){
		try {
			Integer.parseInt(target);
		}
		catch (NumberFormatException ex) {
			return false;
		}
		return true;
	}
	
	
	private String zfill(String in){
		if(in.length() == 15){
			return in;
		}else{
			return zfill("0" + in);
		}
	}
}
