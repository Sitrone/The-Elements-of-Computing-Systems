package com.elements.assembler;

import java.util.HashMap;
import java.util.Map;

public class SymboleTable {
	
	private Map<String, Integer> symbolTable = new HashMap<String, Integer>();
	private int nextAddress = 16;
	
	public SymboleTable(){
		symbolTable.put("SP",     0);
		symbolTable.put("LCL",    1);
		symbolTable.put("ARG",    2);
		symbolTable.put("THIS",   3);
		symbolTable.put("THAT",   4);
		for(int i = 0; i < 16; i++){
			symbolTable.put("R" + String.valueOf(i), i);
		}
		symbolTable.put("SCREEN", 16384);
		symbolTable.put("KBD",    24576);
	}
	
	/**
	 * 
	 * @param symbol
	 * @param address
	 */
	public void addEntry(String symbol, int address){
		symbolTable.put(symbol, address);
	}
	
	/**
	 * 
	 * @param symbol
	 * @return
	 */
	public boolean contains(String symbol){
		boolean result = false;
		if(symbolTable.containsKey(symbol)){
			result = true;
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param symbol
	 * @return int
	 */
	public int getAddress(String symbol){
		if(symbolTable.containsKey(symbol)){
			return symbolTable.get(symbol);
		}
		addEntry(symbol, nextAddress);
		nextAddress += 1;
		return nextAddress;
	}
	
	public int getNextAddress(){
		return nextAddress;
	}
	
}
