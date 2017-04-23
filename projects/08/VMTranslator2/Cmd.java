package com.elements.vm2;

public class Cmd
{
	private static final char N = '\n'; // 换行符
	private static final String INC_SP = "@SP" + N + "M=M+1" + N; // 指针的值指向下一个位置
	private static final String POP_FROM_STACK = "@SP" + N + "A=M-1" + N + "D=M" + N;
	private static final String PUSH_TO_STACK = "D=M" + N + "@SP" + N + "A=M" + N + "M=D" + N + INC_SP;
	
	public static String arithCmdAdd()
	{
		StringBuilder build = new StringBuilder(64);
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
		StringBuilder build = new StringBuilder(64);
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
		StringBuilder build = new StringBuilder(64);
		build.append("//neg").append(N);
		build.append("@SP").append(N)
					  .append("A=M-1").append(N)
					  .append("M=-M").append(N);
		return build.toString();
	}
	
	public static String arithCmdAnd()
	{
		StringBuilder build = new StringBuilder(64);
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
		StringBuilder build = new StringBuilder(64);
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
		StringBuilder build = new StringBuilder(32);
		build.append("//not").append(N);
		build.append("@SP").append(N)
		  			  .append("A=M-1").append(N)
				      .append("M=!M").append(N);
		return build.toString();
	}
	
	public static String arithCmdGt(int labelIdNumber)
	{
		StringBuilder build = new StringBuilder(64);
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
		StringBuilder build = new StringBuilder(64);
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
		StringBuilder build = new StringBuilder(64);
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
		StringBuilder build = new StringBuilder(32);
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
		StringBuilder build = new StringBuilder(32);
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
		StringBuilder build = new StringBuilder(32);
		build.append(" Static " + index +N);
		build.append("@" + (fileName + "." + index)).append(N)
			 .append(PUSH_TO_STACK);
		return build.toString();
	}
	
	public static String pushConst(int index)
	{
		StringBuilder build = new StringBuilder(32);
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
		StringBuilder build = new StringBuilder(32);
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
		StringBuilder build = new StringBuilder(32);
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
		StringBuilder build = new StringBuilder(32);
		build.append(" Pointer " + (index + 3) +N);
		build.append("@" + (index + 3)).append(N)
			 .append(PUSH_TO_STACK);
		return build.toString();
	}
	
	public static String pushTemp(int index)
	{
		StringBuilder build = new StringBuilder(32);
		build.append(" Temp " + index +N);
		build.append("@" + (index + 5)).append(N)
			 .append(PUSH_TO_STACK);
		return build.toString();
	}
	
	public static String popArg(int index)
	{
		StringBuilder build = new StringBuilder(64);
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
		StringBuilder build = new StringBuilder(64);
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
		StringBuilder build = new StringBuilder(32);
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
		StringBuilder build = new StringBuilder(64);
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
		StringBuilder build = new StringBuilder(64);
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
		StringBuilder build = new StringBuilder(32);
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
		StringBuilder build = new StringBuilder(32);
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
	    StringBuilder build = new StringBuilder(128);
	    
	    // SP=256, 将堆栈指针初始化为0x0100
	    build.append("// bootstrap begin")
	    	 .append("@256").append(N)
	         .append("D=A").append(N)
	         .append("@SP").append(N)
	         .append("M=D").append(N)
	         .append("// call Sys.init").append(N);
	    
	    // call Sys.init, 开始执行(翻译后的)Sys.init
	    build.append(writeCallCmd("Sys.init", 0, -1));
	    
//	    // init return
//	    build.append("@return-Sys.init").append(N)
//	          .append("D=A").append(N)
//	          .append("@SP").append(N)
//	          .append("A=M").append(N)
//	          .append("M=D").append(N)
//	          .append("@SP").append(N)
//	          .append("M=M+1").append(N);
//	    
//	    // init LCL
//	    build.append(pushCmd("@LCL"));
//	    
//	    // init ARG
//	    build.append(pushCmd("@ARG"));
//	    
//	    // init THIS
//        build.append(pushCmd("@THIS"));
//        
//        // init THAT
//        build.append(pushCmd("@THAT"));
//        
//        build.append("@SP").append(N)
//             .append("D=M").append(N)
//             .append("@0").append(N)
//             .append("D=D-A").append(N)
//             .append("@5").append(N)
//             .append("D=D-A").append(N)
//             .append("@ARG").append(N)
//             .append("M=D").append(N)
//        
//             .append("@SP").append(N)
//             .append("D=M").append(N)
//             .append("@LCL").append(N)
//             .append("M=D").append(N);
//        
//        //TransferControl
//        build.append("@Sys.init").append(N)
//             .append("0; JMP").append(N)
//        
//             .append("(return-Sys.init)").append(N);
	    return build.toString();
	}
	
	public static String writeLabelCmd(String label, String functionName)
	{
	    StringBuilder build = new StringBuilder(16);
	    build.append("// label").append(N)
	         .append("(" + functionName + "$" + label + ")").append(N);
	   return build.toString(); 
	}
	
	public static String writeIfCmd(String label, String functionName)
	{
	    StringBuilder build = new StringBuilder(32);
	    build.append("// if-goto " + label).append(N)
	         .append("@SP").append(N)
	         .append("M=M-1").append(N)
	         .append("@SP").append(N)
	         .append("A=M").append(N)
	         .append("D=M").append(N)
	         .append("@" + functionName + "$" + label).append(N)
	         .append("D;JNE").append(N);
	    return build.toString();
	}
	
    public static String writeGotoCmd(String label, String functionName)
    {
        StringBuilder build = new StringBuilder(32);
        build.append("// goto " + label).append(N)
             .append("@" + functionName + "$" + label).append(N)
             .append("0;JMP").append(N);
        return build.toString();
    }
    
    // call f n
    public static String writeCallCmd(String functionName, int args, int labelIdNumber)
    {
    	StringBuilder build = new StringBuilder(128);
    	
    	// push return address
    	String returnAddr = "@return-" + functionName + labelIdNumber;
	    build.append(returnAddr).append(N)
        	 .append("D=M").append(N)
        	 .append("@SP").append(N)
        	 .append("A=M").append(N)
        	 .append("M=D").append(N)
        	 .append("@SP").append(N)
        	 .append("M=M+1").append(N);
    	
    	// push LCL
    	build.append(pushCmd("@LCL"));
    	
    	// push ARG
    	build.append(pushCmd("@ARG"));
    	
    	// push THIS
    	build.append(pushCmd("@THIS"));
    	
    	// push THAT
    	build.append(pushCmd("@THAT"));
    	
    	// reset ARG(n = args.length), ARG = SP -n -5
    	build.append("@SP").append(N)
    	     .append("D=M").append(N);
		for (int i = 0; i < (5 + args); i++)
		{
			build.append("D=D-1").append(N);
		}
    	build.append("@ARG").append(N)
    	     .append("M=D").append(N);
    	
    	// reset LCL, LCL=SP
    	build.append("@SP").append(N)
    		 .append("D=M").append(N)
    		 .append("@LCL").append(N)
    		 .append("M=D").append(N);
    	
    	// goto f
    	build.append("@" + functionName).append(N)
    		 .append("0;JMP").append(N);
    	
    	// (return-address)
    	String returnAddrLabel = "(" + returnAddr + ")";
    	build.append(returnAddrLabel).append(N);
    	
    	return build.toString();
    }
    
    /**
     * repeat k times push 0, k个全局变量全部初始化为0
     */
    public static String writeFunctionCmd(String functionName, int args)
    {
    	StringBuilder build = new StringBuilder(16 * args);
    	build.append("// function " + functionName).append(N)
    		 .append('(').append(functionName).append(')').append(N);
    	for(int i = 0; i < args; i++)
    	{
    		build.append(pushCmd("@0"));
    	}
    	return build.toString();
    }
    
    // 
    public static String writeReturnCmd(int labelIdNumber)
    {
    	StringBuilder build = new StringBuilder(128);
    	
    	// FRAME = LCL, FRAME 是临时变量
    	build.append("@LCL").append(N)
    	     .append("D=M").append(N)
    	     .append("@FRAME").append(N)
    	     .append("M=D").append(N);
    	
    	// RET = *(FRAME-5), 将返回地址放入临时变量
    	build.append("@FRAME").append(N)
    	     .append("D=M").append(N);
    	for (int i = 0; i < 5; i++)
    	{
    		build.append("D=D-1").append(N);
    	}
    	build.append("A=D").append(N)
    		 .append("D=M").append(N)
    		 .append("@RET").append(N)
    		 .append("M=D").append(N);
    	
    	// *ARG = POP(), 重置调用者的返回值
    	build.append("@SP").append(N)
    		 .append("M=M-1").append(N)
    	     .append("A=M").append(N)
    	     .append("D=M").append(N)
    	     .append("@ARG").append(N)
    	     .append("M=D").append(N);
    	
    	// SP = ARG + 1, 恢复调用者的SP
    	build.append("@ARG").append(N)
    	     .append("D=M").append(N)
    	     .append("D=D+1").append(N)
    	     .append("@SP").append(N)
    	     .append("M=D").append(N);
    	
    	// THAT = *(FRAME-1), 恢复调用者THAT段指针
    	build.append(restoreFrame2Sth("@THAT", 1));
    	
    	// THIS = *(FRAME-2), 恢复调用者THIS段指针
    	build.append(restoreFrame2Sth("@THIS", 2));
    	
    	// ARG = *(FRAME-3), 恢复调用者ARG段指针
    	build.append(restoreFrame2Sth("@ARG", 3));
    	
    	// LCL = *(FRAME-4), 恢复调用者LCL段指针
    	build.append(restoreFrame2Sth("@LCL", 4));
    	
    	// goto RET, 返回跳转地址，在调用者的代码中
    	build.append("@RET").append(N)
    	     .append("A=M").append(N)
    	     .append("0;JMP").append(N);
    	
    	return build.toString();
    }
    
    private static String restoreFrame2Sth(String sth, int num)
    {
    	StringBuilder build = new StringBuilder(16);
    	build.append("@FRAME").append(N)
	     	 .append("D=M").append(N);
    	for (int i = 0; i < num; i++)
    	{
    		build.append("D=D-1").append(N);
    	}
    	build.append("A=D").append(N)
	     	 .append("D=M").append(N)
	     	 .append(sth).append(N)
	     	 .append("M=D").append(N);
    	
    	return build.toString();
    }
    
    /**
     *  push cmd
     */
	private static String pushCmd(String cmd)
	{
	    StringBuilder build = new StringBuilder(32);
	    build.append(cmd).append(N)
             .append("D=M").append(N)
             .append("@SP").append(N)
             .append("A=M").append(N)
             .append("M=D").append(N)
             .append("@SP").append(N)
             .append("M=M+1").append(N);
	    return build.toString();
	}
    
}
