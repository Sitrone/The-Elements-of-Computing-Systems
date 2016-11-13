package com.elements.vm2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.elements.vm2.Cmd.*;

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
	 * 初始化VM的汇编代码，初始化引导程序
	 */
	public void writeInit()
	{
		
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
				conbinationCmd.append(arithCmdAdd());
				break;
			case SUB:
				conbinationCmd.append(arithCmdSub());
				break;
			case NEG:
				conbinationCmd.append(arithCmdNeg());
				break;
			case AND:
				conbinationCmd.append(arithCmdAnd());
				break;
			case OR:
				conbinationCmd.append(arithCmdOr());
				break;
			case NOT:
				conbinationCmd.append(arithCmdNot());
				break;
			case GT:
				conbinationCmd.append(arithCmdGt(labelIdNumber));
				labelIdNumber++;
				break;
			case LT:
				conbinationCmd.append(arithCmdLt(labelIdNumber));
				labelIdNumber++;
				break;
			case EQ:
				conbinationCmd.append(arithCmdEq(labelIdNumber));
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
						conbinationCmd.append(pushArg(index));
						break;

					case LOCAL:
						conbinationCmd.append(pushLocal(index));
						break;

					case STATIC:
						conbinationCmd.append(pushStatic(index, this.fileName));
						break;

					case CONSTANT:
						conbinationCmd.append(pushConst(index));
						break;

					case THIS:
						conbinationCmd.append(pushThis(index));
						break;

					case THAT:
						conbinationCmd.append(pushThat(index));
						break;

					case POINTER:
						conbinationCmd.append(pushPointer(index));
						break;

					case TEMP:
						conbinationCmd.append(pushTemp(index));
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
						conbinationCmd.append(popArg(index));
						break;
					case LOCAL:
						conbinationCmd.append(popLocal(index));
						break;
					case STATIC:
						conbinationCmd.append(popStatic(this.fileName, index));
						break;
					case THIS:
						conbinationCmd.append(popThis(index));
						break;
					case THAT:
						conbinationCmd.append(popThat(index));
						break;
					case POINTER:
						conbinationCmd.append(popPointer(index));
						break;
					case TEMP:
						conbinationCmd.append(popTemp(index));
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

	/**
	 * 执行label汇编命令的代码
	 * @param label
	 */
	public void writeLabel(String label)
	{
		
	}
	
	/**
	 * 执行goto汇编命令的代码
	 * @param label
	 */
	public void writeGoto(String label)
	{
		
	}
	
	/**
	 * 执行if-goto命令的汇编代码
	 * @param label
	 */
	public void writeIf(String label)
	{
		
	}
	
	/**
	 * 执行call命令的汇编代码
	 * @param functionName
	 * @param numArgs
	 */
	public void writeCall(String functionName, int numArgs)
	{
		
	}
	
	/**
	 * 必行return命令的汇编代码
	 */
	public void writeReturn()
	{
		
	}
	
	/**
	 * 执行function命令的代码
	 * @param functionName
	 * @param numLocals
	 */
	public void writeFunction(String functionName, int numLocals)
	{
		
	}
	
	/**
	 * 将命令写入文件
	 * @param conbinationCmd
	 */
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
	 * @param w
	 */
	public void close()
	{
		try
		{
			if (bw != null)
			{
				bw.close();
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 获得文件的全路径
	 * @param path
	 * @return
	 */
	private String getFilePath(String path)
	{
		int indexOfLastSlash = path.lastIndexOf("\\");
		return new String(path.substring(0, indexOfLastSlash + 1));
	}

	/**
	 * 获得文件名
	 * @param path
	 * @return
	 */
	private String getFileName(String path)
	{
		int indexOfLastSlash = path.lastIndexOf("\\");
		int indexOfLastDot = path.lastIndexOf(".");
		return new String(path.substring(indexOfLastSlash + 1, indexOfLastDot));
	}
}
