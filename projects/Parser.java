package com.unit.test;

public class Parser {
	/**
	 * 
	 * @return
	 */
	public String commandType(String line){
		String result = null;
		// 单行存在注释的情况，如：
		// M=D              // M[2] = D (greatest number)
		line = line.split("//")[0].trim();
		if(line.contains("@")){
			result = CmdConst.A_COMMAND;
		}else if(line.contains("=") || line.contains(";")){
			result = CmdConst.C_COMMAND;
		}else if(line.contains("(")){
			result = CmdConst.L_COMMAND;
		}
		
		return result;
	}
	
	
	public String symbol(String line){
		String result = null;
		line = line.split("//")[0].trim();
		/* A command */
		if(line.contains("@")){
			int indexOfAt = line.indexOf("@");
			result = line.substring(indexOfAt + 1).trim();
			
			/* C command */
		}else if(line.contains("(") && line.contains(")")){
			int indexOfLeft = line.indexOf("(");
			int indexOfRight = line.indexOf(")");
			result = line.substring(indexOfLeft + 1, indexOfRight).trim();
		}
		return result;
	}
	
	/* Symbolic: dest=comp;jump*/
			// Either the dest or jump fields may be empty.
	 		// If dest is empty, the "=" is ommitted;
	 		// If jump is empty, the ";" is omitted.
	
	public String dest(String line){
		line = line.split("//")[0].trim();
		String result = "null";
		if(line.contains("=")){
			result = line.split("=")[0].trim(); 
		}else if(line.contains(";")){
		}
		
		return result;
	}
	
	public String comp(String line){
		line = line.split("//")[0].trim();
		String result = "null";
		if(line.contains("=") ){
			String[] lines = line.split("=");
			result = lines[1].trim();
		}else if(line.contains(";")){
			String[] lines = line.split(";");
			result = lines[0].trim();
		}
		
		return result;
	}
	
	public String jump(String line){
		line = line.split("//")[0].trim();
		String result = "null";
		if(line.contains("=")){
		}else if(line.contains(";")){
			int indexOfComma = line.indexOf(";");
			result = line.substring(indexOfComma + 1).trim();
		}
		
		return result;
	}
}
