package com.elements.vm2;

public class Cmd
{
	private static final String N = "\n"; // 换行符
	private static final String INC_SP = "@SP" + N + "M=M+1" + N; // 指针的值指向下一个位置
	private static final String POP_FROM_STACK = "@SP" + N + "A=M-1" + N + "D=M" + N;
	private static final String PUSH_TO_STACK = "D=M" + N + "@SP" + N + "A=M" + N + "M=D" + N + INC_SP;
	
	public static String arithCmdAdd()
	{
		StringBuilder build = new StringBuilder("");
		build.append("//add").append(N);
		build.append("@SP").append(N)
					  .append("AM=M-1").append(N)
					  .append("D=M").append(N)
					  .append("@SP").append(N)
					  .append("A=M-1").append(N)
					  .append("M=D+M").append(N);
		return build.toString();
	}
	
	public static String arithCmdSub()
	{
		StringBuilder build = new StringBuilder("");
		build.append("//sub").append(N);
		build.append("@SP").append(N)
		  			   .append("AM=M-1").append(N)
		  			   .append("D=M").append(N)
		  			   .append("@SP").append(N)
		  			   .append("A=M-1").append(N)
					   .append("M=M-D").append(N);
		return build.toString();
	}
	
	public static String arithCmdNeg()
	{
		StringBuilder build = new StringBuilder("");
		build.append("//neg").append(N);
		build.append("@SP").append(N)
					  .append("A=M-1").append(N)
					  .append("M=-M").append(N);
		return build.toString();
	}
	
	public static String arithCmdAnd()
	{
		StringBuilder build = new StringBuilder("");
		build.append("//and").append(N);
		build.append("@SP").append(N)
					  .append("AM=M-1").append(N)
					  .append("D=M").append(N)
					  .append("@SP").append(N)
					  .append("A=M-1").append(N)
					  .append("M=D&M").append(N);
		return build.toString();
	}
	
	public static String arithCmdOr()
	{
		StringBuilder build = new StringBuilder("");
		build.append("//or").append(N);
		build.append("@SP").append(N)
					  .append("AM=M-1").append(N)
					  .append("D=M").append(N)
					  .append("@SP").append(N)
					  .append("A=M-1").append(N)
					  .append("M=D|M").append(N);
		return build.toString();
	}
	
	public static String arithCmdNot()
	{
		StringBuilder build = new StringBuilder("");
		build.append("//not").append(N);
		build.append("@SP").append(N)
		  			  .append("A=M-1").append(N)
				      .append("M=!M").append(N);
		return build.toString();
	}
	
	public static String arithCmdGt(int labelIdNumber)
	{
		StringBuilder build = new StringBuilder("");
		build.append("//gt").append(N);
		build.append("@SP").append(N)
		  				.append("M=M-1").append(N)
		  				.append("A=M").append(N)
		  				.append("D=M").append(N)
		  				.append("@SP").append(N)
		  				.append("A=M-1").append(N)
						.append("D=M-D").append(N) // D = x - y
						.append("@RET_GT").append(labelIdNumber).append(N)
						.append("D;JGT").append(N) // Jump if x is greater than y
						.append("@SP").append(N) // If x is NOT greater than y
						.append("A=M-1").append(N)
						.append("M=0").append(N)
						.append("@END" + labelIdNumber).append(N)
						.append("0;JMP").append(N)
						.append("(RET_GT" + labelIdNumber + ")").append(N)
						.append("@SP").append(N) // If x is greater than y
						.append("A=M-1").append(N)
						.append("M=-1").append(N)
						.append("(END" + labelIdNumber + ")").append(N);
		return build.toString();
	}
	
	public static String arithCmdLt(int labelIdNumber)
	{
		StringBuilder build = new StringBuilder("");
		build.append("//lt").append(N);
		build.append("@SP").append(N)
						  .append("M=M-1").append(N)
						  .append("A=M").append(N)
						  .append("D=M").append(N)
						  .append("@SP").append(N)
						  .append("A=M-1").append(N)
				      .append("D=M-D").append(N) // D = y - x
				      .append("@RET_LT" + labelIdNumber).append(N)
				      .append("D;JLT").append(N) // Jump if x is less than y
				      .append("@SP").append(N) // If x is NOT less than y
				      .append("A=M-1").append(N)
				      .append("M=0").append(N) // FALSE
				      .append("@END" + labelIdNumber).append(N)
				      .append("0;JMP").append(N)
				      .append("(RET_LT" + labelIdNumber + ")").append(N)
				      .append("@SP").append(N) // If x is less than y
				      .append("A=M-1").append(N)
				      .append("M=-1").append(N) // TRUE
				      .append("(END" + labelIdNumber + ")").append(N);
		return build.toString();
	}
	
	public static String arithCmdEq(int labelIdNumber)
	{
		StringBuilder build = new StringBuilder("");
		build.append("//eq").append(N);
		build.append("@SP").append(N)
		  			  .append("M=M-1").append(N)
		  			  .append("A=M").append(N)
		  			  .append("D=M").append(N)
		  			  .append("@SP").append(N)
		  			  .append("A=M-1").append(N)
					  .append("D=D-M").append(N) // D = x - y
					  .append("@RET_EQ" + labelIdNumber).append(N)
					  .append("D;JEQ").append(N) // Jump x is equal y
					  .append("@SP").append(N) // if x is NOT equal y
					  .append("A=M-1").append(N)
					  .append("M=0").append(N) // FALSE
					  .append("@END" + labelIdNumber).append(N)
					  .append("0;JMP").append(N)
					  .append("(RET_EQ" + labelIdNumber + ")").append(N)
					  .append("@SP").append(N) // if
					  .append("A=M-1").append(N)
					  .append("M=-1").append(N) // TRUE
					  .append("(END" + labelIdNumber + ")").append(N);
		return build.toString();
	}
	
	public static String pushArg(int index)
	{
		StringBuilder build = new StringBuilder("");
		build.append(" Argument " + index +N);
		build.append("@" + index).append(N)
					  .append("D=A").append(N)
					  .append("@ARG").append(N)
					  .append("A=D+M").append(N)
					  .append(PUSH_TO_STACK);
		return build.toString();
	}
	
	public static String pushLocal(int index)
	{
		StringBuilder build = new StringBuilder("");
		build.append(" Local " + index +N);
		build.append("@" + index).append(N)
					  .append("D=A").append(N)
					  .append("@LCL").append(N)
					  .append("A=D+M").append(N)
					  .append(PUSH_TO_STACK);
		return build.toString();
	}
	
	public static String pushStatic(int index, String fileName)
	{
		StringBuilder build = new StringBuilder("");
		build.append(" Static " + index +N);
		build.append("@" + (fileName + "." + index)).append(N)
			 .append(PUSH_TO_STACK);
		return build.toString();
	}
	
	public static String pushConst(int index)
	{
		StringBuilder build = new StringBuilder("");
		build.append(" Constant " + index +N);
		build.append("@" + index + N)
					  .append("D=A").append(N)
					  .append("@SP").append(N)
					  .append("A=M").append(N)
					  .append("M=D").append(N)
					  .append("@SP").append(N)
					  .append("M=M+1").append(N);
		return build.toString();
	}
	
	public static String pushThis(int index)
	{
		StringBuilder build = new StringBuilder("");
		build.append(" This " + index +N);
		build.append("@" + index).append(N)
		  			  .append("D=A").append(N)
		  			  .append("@THIS").append(N)
				  	  .append("A=D+M").append(N)
				  	  .append(PUSH_TO_STACK);
		return build.toString();
	}
	
	public static String pushThat(int index)
	{
		StringBuilder build = new StringBuilder("");
		build.append(" That " + index +N);
		build.append("@" + index).append(N)
			 .append("D=A").append(N)
			 .append("@THAT").append(N)
			 .append("A=D+M").append(N)
			 .append(PUSH_TO_STACK);
		return build.toString();
	}
	
	public static String pushPointer(int index)
	{
		StringBuilder build = new StringBuilder("");
		build.append(" Pointer " + (index + 3) +N);
		build.append("@" + (index + 3)).append(N)
			 .append(PUSH_TO_STACK);
		return build.toString();
	}
	
	public static String pushTemp(int index)
	{
		StringBuilder build = new StringBuilder("");
		build.append(" Temp " + index +N);
		build.append("@" + (index + 5)).append(N)
			 .append(PUSH_TO_STACK);
		return build.toString();
	}
	
	public static String popArg(int index)
	{
		StringBuilder build = new StringBuilder("");
		build.append(" Argument " + index +N);
		build.append("@" + index).append(N)
					  .append("D=A").append(N)
					  .append("@ARG").append(N)
					  .append("D=D+M").append(N)
					  .append("@R13").append(N)  // FIXME R10 or R13?
					  .append("M=D").append(N)
					  .append("@SP").append(N)
					  .append("A=M-1").append(N)
					  .append("D=M").append(N)
					  .append("@SP").append(N)
					  .append("M=M-1").append(N)
					  .append("@R13").append(N)
					  .append("A=M").append(N)
					  .append("M=D").append(N);
		return build.toString();
	}
	
	
	public static String popLocal(int index)
	{
		StringBuilder build = new StringBuilder("");
		build.append(" Local " + index +N);
		build.append("@" + index).append(N)
					  .append("D=A").append(N)
					  .append("@LCL").append(N)
					  .append("D=D+M").append(N)
					  .append("@R13").append(N)
					  .append("M=D").append(N)
					  .append("@SP").append(N)
					  .append("A=M-1").append(N)
					  .append("D=M").append(N)
					  .append("@SP").append(N)
					  .append("M=M-1").append(N)
					  .append("@R13").append(N)
					  .append("A=M").append(N)
					  .append("M=D").append(N);
		return build.toString();
	}
	
	public static String popStatic(String fileName, int index)
	{
		StringBuilder build = new StringBuilder("");
		build.append(" Static " + index +N);
		build.append(POP_FROM_STACK)
					  .append("@SP").append(N)
					  .append("M=M-1").append(N)
					  .append("@" + fileName + "." + index + N)
					  .append("M=D").append(N);
		return build.toString();
	}
	
	public static String popThis(int index)
	{
		StringBuilder build = new StringBuilder("");
		build.append(" This " + index +N);
		build.append("@" + index).append(N)
		  			  .append("D=A").append(N)
					  .append("@THIS").append(N)
					  .append("D=D+M").append(N)
					  .append("@R13").append(N)
					  .append("M=D").append(N)
					  .append(POP_FROM_STACK)
					  .append("@SP").append(N)
					  .append("M=M-1").append(N)
					  .append("@R13").append(N)
					  .append("A=M").append(N)
					  .append("M=D").append(N);
		return build.toString();
	}
	
	public static String popThat(int index)
	{
		StringBuilder build = new StringBuilder("");
		build.append(" That " + index +N);
		build.append("@" + index).append(N)
					  .append("D=A").append(N)
					  .append("@THAT").append(N)
					  .append("D=D+M").append(N)
					  .append("@R13").append(N)
					  .append("M=D").append(N)
					  .append(POP_FROM_STACK)
					  .append("@SP").append(N)
					  .append("M=M-1").append(N)
					  .append("@R13").append(N)
					  .append("A=M").append(N)
					  .append("M=D").append(N);
		return build.toString();
	}
	
	public static String popPointer(int index)
	{
		StringBuilder build = new StringBuilder("");
		build.append(" Pointer " + index +N);
		build.append(POP_FROM_STACK)
					  .append("@SP").append(N)
					  .append("M=M-1").append(N)
		 		      .append("@" + (index + 3) + N)
					  .append("M=D").append(N);
		return build.toString();
	}
	
	public static String popTemp(int index)
	{
		StringBuilder build = new StringBuilder("");
		build.append(" Temp " + index +N);
		build.append(POP_FROM_STACK)
					  .append("@SP").append(N)
					  .append("M=M-1").append(N)
					  .append("@" + (index + 5) + N)
					  .append("M=D").append(N);
		return build.toString();
	}
	
	public static String vmInit()
	{
	    StringBuilder build = new StringBuilder(32);
	    build.append("@256").append(N)
	         .append("D=A").append(N)
	         .append("@SP").append(N)
	         .append("M=D").append(N)
	         .append("// call Sys.init").append(N);
	    
	    // init return
	    build.append("@return-Sys.init").append(N)
	          .append("D=A").append(N)
	          .append("@SP").append(N)
	          .append("A=M").append(N)
	          .append("M=D").append(N)
	          .append("@SP").append(N)
	          .append("M=M+1").append(N);
	    
	    // init LCL
	    build.append(pushInitCmd("LCL"));
	    
	    // init ARG
	    build.append(pushInitCmd("ARG"));
	    
	    // init THIS
        build.append(pushInitCmd("THIS"));
        
        // init THAT
        build.append(pushInitCmd("THAT"));
        
        
        build.append("@SP").append(N)
             .append("D=M").append(N)
             .append("@0").append(N)
             .append("D=D-A").append(N)
             .append("@5").append(N)
             .append("D=D-A").append(N)
             .append("@ARG").append(N)
             .append("M=D").append(N)
        
             .append("@SP").append(N)
             .append("D=M").append(N)
             .append("@LCL").append(N)
             .append("M=D").append(N);
        
        //TransferControl
        build.append("@Sys.init").append(N)
             .append("0; JMP").append(N)
        
             .append("(return-Sys.init)").append(N);
	    return build.toString();
	}
	
	private static String pushInitCmd(String cmd)
	{
	    StringBuilder build = new StringBuilder();
	    build.append(cmd).append(N)
             .append("D=M").append(N)
             .append("@SP").append(N)
             .append("A=M").append(N)
             .append("M=D").append(N)
             .append("@SP").append(N)
             .append("M=M+1").append(N);
	    return build.toString();
	}
	
	public static String writeLabelCmd(String label, String functionName)
	{
	    StringBuilder build = new StringBuilder();
	    build.append("// label").append(N)
	         .append("(" + functionName + "$" + label + ")").append(N);
	   return build.toString(); 
	}
	
	public static String writeIfCmd(String label, String functionName)
	{
	    StringBuilder build = new StringBuilder();
	    build.append("// if-goto " + label).append(N)
	         .append("@SP").append(N)
	         .append("A=M-1").append(N)
	         .append("D=M").append(N)
	         .append("@SP").append(N)
	         .append("M=M-1").append(N)
	         .append("@" + functionName + "$" + label).append(N)
	         .append("D;JNE").append(N);
	    return build.toString();
	}
	
    public static String writeGotoCmd(String label, String functionName)
    {
        StringBuilder build = new StringBuilder();
        build.append("// goto " + label).append(N)
             .append("@" + functionName + "$" + label).append(N)
             .append("0;JMP").append(N);
        return build.toString();
    }
}
