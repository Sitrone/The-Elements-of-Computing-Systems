package com.elements.vm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CodeWriter
{
	private BufferedWriter bw = null;
	private String fileName = "";

	public String getFileName()
	{
		return fileName;
	}

	private static int labelIdNumber = 0;

	private enum Segments
	{
		ARGUMENT, LOCAL, STATIC, CONSTANT, THIS, THAT, POINTER, TEMP
	};

	private enum Arithmetic
	{
		ADD, SUB, NEG, EQ, GT, LT, AND, OR, NOT
	};

	private enum StackOperation
	{
		PUSH, POP
	};

	private static final String N = "\n"; // 换行符
	private static final String INC_SP = "@SP" + N + "M=M+1" + N; // 指针的值指向下一个位置
//	private static final String DEC_SP = "@SP" + N + "M=M-1" + N; // 指针的值指向上一个位置
	// private static final String GET_ARG = DEC_SP + "A=M" + N + "D=M";
	// private static final String GET_ARG1_AND_ARG2 = DEC_SP + "A=M" + N +
	// "D=M" + N + DEC_SP + "A=M" + N;
	// private static final String GET_D_INSTOR_A = "@SP" + N + "A=M" + N +
	// "M=D" + N;//将指针所指的地址的值设置为D

	private static final String POP_FROM_STACK = "@SP" + N + "A=M-1" + N + "D=M" + N;
	private static final String PUSH_TO_STACK = "D=M" + N + "@SP" + N + "A=M" + N + "M=D" + N + INC_SP;

	/**
	 * 打开输出文件/输出流，准备进行写入
	 * 
	 * @param file
	 */
	public CodeWriter(String file)
	{
		try
		{
			String outPutFile = setFileName(file);
			bw = new BufferedWriter(new FileWriter(outPutFile));
		} catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	/**
	 * 通知代码写入新程序，新的VM文件翻译过程已经开始
	 * 
	 * @param file
	 * @throws IOException
	 */
	public String setFileName(String file)
	{
		String outFile = getFilePath(file) + getFileName(file) + ".asm";
		File f = new File(outFile);
		if (f.exists())
		{
			f.delete();
			System.out.println("asm file is deleted, waiting for create...");
		}
		try
		{
			f.createNewFile();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		System.out.println("asm file is created successful.");

		this.fileName = getFileName(file);
		return outFile;
	}

	/**
	 * 将给定的算术操作所对应的汇编代码写至输出
	 * 
	 * @param command
	 */
	public void writeArithmetic(String command)
	{
		StringBuilder conbinationCmd = new StringBuilder("");
		Arithmetic arithmeticCmd = Arithmetic.valueOf(command.toUpperCase());
		switch (arithmeticCmd)
			{
			case ADD:
				conbinationCmd.append("//add").append(N);
				conbinationCmd.append("@SP").append(N)
							  .append("AM=M-1").append(N)
							  .append("D=M").append(N)
							  .append("@SP").append(N)
							  .append("A=M-1").append(N)
							  .append("M=D+M").append(N);
							  // .append(GET_D_INSTOR_A)
//							  .append(INC_SP);
				break;
			case SUB:
				conbinationCmd.append("//sub").append(N);
				conbinationCmd.append("@SP").append(N)
				  			   .append("AM=M-1").append(N)
				  			   .append("D=M").append(N)
				  			   .append("@SP").append(N)
				  			   .append("A=M-1").append(N)
							   .append("M=M-D").append(N);
							  // .append(GET_D_INSTOR_A)
//							  .append(INC_SP);
				break;
			case NEG:
				conbinationCmd.append("//neg").append(N);
				conbinationCmd.append("@SP").append(N)
							  .append("A=M-1").append(N)
							  .append("M=-M").append(N);
//							  .append("M=D").append(N)
//							  .append(INC_SP);
				break;
			case AND:
				conbinationCmd.append("//and").append(N);
				conbinationCmd.append("@SP").append(N)
							  .append("AM=M-1").append(N)
							  .append("D=M").append(N)
							  .append("@SP").append(N)
							  .append("A=M-1").append(N)
							  .append("M=D&M").append(N);
							  // .append(GET_D_INSTOR_A)
//							  .append(INC_SP);
				break;
			case OR:
				conbinationCmd.append("//or").append(N);
				conbinationCmd.append("@SP").append(N)
							  .append("AM=M-1").append(N)
							  .append("D=M").append(N)
							  .append("@SP").append(N)
							  .append("A=M-1").append(N)
							  .append("M=D|M").append(N);
							  // .append(GET_D_INSTOR_A)
//							  .append(INC_SP);
				break;
			case NOT:
				conbinationCmd.append("//not").append(N);
				conbinationCmd.append("@SP").append(N)
				  			  .append("A=M-1").append(N)
						      .append("M=!M").append(N);
//						      .append(INC_SP);
				break;
			case GT:
				conbinationCmd.append("//gt").append(N);
				conbinationCmd.append("@SP").append(N)
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
				labelIdNumber++;
				break;
			case LT:
				conbinationCmd.append("//lt").append(N);
				conbinationCmd.append("@SP").append(N)
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
				labelIdNumber++;
				break;
			case EQ:
				conbinationCmd.append("//eq").append(N);
				conbinationCmd.append("@SP").append(N)
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
				labelIdNumber++;
				break;
			default:
				break;
			}
		writeConbinationCmd(conbinationCmd.toString());
	}

	/**
	 * 将给定的command（命令类型为C_PUSH或C_POP）所对应的汇编代码写入至输出
	 * 
	 * @param command
	 * @param segment
	 * @param index
	 */
	public void writePushPop(String command, String segment, int index)
	{
		StringBuilder conbinationCmd = new StringBuilder("");
		StackOperation stackCmd = StackOperation.valueOf(command.toUpperCase());
		Segments stackSegment = Segments.valueOf(segment.toUpperCase());
		switch (stackCmd)
			{
			case PUSH:
				conbinationCmd.append("//Push");
				switch (stackSegment)
					{
					case ARGUMENT:
						conbinationCmd.append(" Argument " + index +N);
						conbinationCmd.append("@" + index).append(N)
									  .append("D=A").append(N)
									  .append("@ARG").append(N)
									  .append("A=D+M").append(N)
									  .append(PUSH_TO_STACK);
						break;

					case LOCAL:
						conbinationCmd.append(" Local " + index +N);
						conbinationCmd.append("@" + index).append(N)
									  .append("D=A").append(N)
									  .append("@LCL").append(N)
									  .append("A=D+M").append(N)
									  .append(PUSH_TO_STACK);
						break;

					case STATIC:
						conbinationCmd.append(" Static " + index +N);
						conbinationCmd.append("@").append(this.fileName + ".").append(index).append(N)
									  .append(PUSH_TO_STACK);
						break;

					case CONSTANT:
						conbinationCmd.append(" Constant " + index +N);
						conbinationCmd.append("@" + index + N)
									  .append("D=A").append(N)
									  .append("@SP").append(N)
									  .append("A=M").append(N)
									  .append("M=D").append(N)
									  .append("@SP").append(N)
									  .append("M=M+1").append(N);
						break;

					case THIS:
						conbinationCmd.append(" This " + index +N);
						conbinationCmd.append("@" + index).append(N)
						  			  .append("D=A").append(N)
						  			  .append("@THIS").append(N)
								  	  .append("A=D+M").append(N)
								  	  .append(PUSH_TO_STACK);
						break;

					case THAT:
						conbinationCmd.append(" That " + index +N);
						conbinationCmd.append("@" + index).append(N)
			  			  			  .append("D=A").append(N)
			  			  			  .append("@THAT").append(N)
									  .append("A=D+M").append(N)
									  .append(PUSH_TO_STACK);
						break;

					case POINTER:
						conbinationCmd.append(" Pointer " + (index + 3) +N);
						conbinationCmd.append("@" + (index + 3)).append(N)
									  .append(PUSH_TO_STACK);
						break;

					case TEMP:
						conbinationCmd.append(" Temp " + index +N);
						conbinationCmd.append("@" + (index + 5)).append(N)
									  .append(PUSH_TO_STACK);
						break;

					default:
						break;
					}
				break;
			case POP:
				conbinationCmd.append("//Pop");
				switch (stackSegment)
					{
					case ARGUMENT:
						conbinationCmd.append(" Argument " + index +N);
						conbinationCmd.append("@" + index).append(N)
									  .append("D=A").append(N)
									  .append("@ARG").append(N)
									  .append("D=D+M").append(N)
									  .append("@R10").append(N)
									  .append("M=D").append(N)
									  .append("@SP").append(N)
									  .append("A=M-1").append(N)
									  .append("D=M").append(N)
									  .append("@SP").append(N)
									  .append("M=M-1").append(N)
									  .append("@R10").append(N)
									  .append("A=M").append(N)
									  .append("M=D").append(N);
						break;
					case LOCAL:
						conbinationCmd.append(" Local " + index +N);
						conbinationCmd.append("@" + index).append(N)
									  .append("D=A").append(N)
									  .append("@LCL").append(N)
									  .append("D=D+M").append(N)
									  .append("@R10").append(N)
									  .append("M=D").append(N)
									  .append("@SP").append(N)
									  .append("A=M-1").append(N)
									  .append("D=M").append(N)
									  .append("@SP").append(N)
									  .append("M=M-1").append(N)
									  .append("@R10").append(N)
									  .append("A=M").append(N)
									  .append("M=D").append(N);
						break;
					case STATIC:
						conbinationCmd.append(" Static " + index +N);
						conbinationCmd.append(POP_FROM_STACK)
									  .append("@SP").append(N)
									  .append("M=M-1").append(N)
									  .append("@" + this.fileName + "." + index + N)
									  .append("M=D").append(N);
						break;
					case THIS:
						conbinationCmd.append(" This " + index +N);
						conbinationCmd.append("@" + index).append(N)
						  			  .append("D=A").append(N)
									  .append("@THIS").append(N)
									  .append("D=D+M").append(N)
									  .append("@R10").append(N)
									  .append("M=D").append(N)
									  .append(POP_FROM_STACK)
									  .append("@SP").append(N)
									  .append("M=M-1").append(N)
									  .append("@R10").append(N)
									  .append("A=M").append(N)
									  .append("M=D").append(N);
						break;
					case THAT:
						conbinationCmd.append(" That " + index +N);
						conbinationCmd.append("@" + index).append(N)
									  .append("D=A").append(N)
									  .append("@THAT").append(N)
									  .append("D=D+M").append(N)
									  .append("@R10").append(N)
									  .append("M=D").append(N)
									  .append(POP_FROM_STACK)
									  .append("@SP").append(N)
									  .append("M=M-1").append(N)
									  .append("@R10").append(N)
									  .append("A=M").append(N)
									  .append("M=D").append(N);
						break;
					case POINTER:
						conbinationCmd.append(" Pointer " + index +N);
						conbinationCmd.append(POP_FROM_STACK)
									  .append("@SP").append(N)
									  .append("M=M-1").append(N)
						 		      .append("@" + (index + 3) + N)
									  .append("M=D").append(N);
						break;
					case TEMP:
						conbinationCmd.append(" Temp " + index +N);
						conbinationCmd.append(POP_FROM_STACK)
									  .append("@SP").append(N)
									  .append("M=M-1").append(N)
									  .append("@" + (index + 5) + N)
									  .append("M=D").append(N);
						break;

					default:
						break;
					}
				break;
			default:
				break;
			}
		writeConbinationCmd(conbinationCmd.toString());
	}

	private void writeConbinationCmd(String conbinationCmd)
	{
		try
		{
			bw.write(conbinationCmd);
			bw.flush();
		} catch (IOException e)
		{
			System.out.println("write command failed : " + conbinationCmd);
		}

	}

	/**
	 * 关闭输出流
	 * 
	 * @param w
	 */
	public void close()
	{
		IOUtils.closeQuietly(bw);
	}

	private String getFilePath(String path)
	{
		int indexOfLastSlash = path.lastIndexOf("\\");
		return new String(path.substring(0, indexOfLastSlash + 1));
	}

	private String getFileName(String path)
	{
		int indexOfLastSlash = path.lastIndexOf("\\");
		int indexOfLastDot = path.lastIndexOf(".");
		return new String(path.substring(indexOfLastSlash + 1, indexOfLastDot));
	}
	
}
