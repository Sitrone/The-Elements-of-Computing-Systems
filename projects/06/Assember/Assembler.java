package com.elements.assembler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Assembler
{
	private Parser parser;
	private SymboleTable sTable;

	public Assembler(Parser parser, SymboleTable sTable)
	{
		this.parser = parser;
		this.sTable = sTable;
	}
	
	public Parser getParser()
	{
		return parser;
	}

	public SymboleTable getsTable()
	{
		return sTable;
	}

	/**
	 * 遍历过程分为两遍<br>
	 * 1. 建立每条命令和其对应的地址，不生成任何代码，程序处理每一行，利用数字来记录ROM地址，这个数字从0开始，
	 * 不管碰到C指令还是A指令，自加1；碰到伪指令，将伪指令加入符号表中<br>
	 * 2.语法分析，对每一行进行语法分析，对A，C指令分别进行处理<br>
	 * 注意： 特别注意单行中存在注释的情况
	 * 
	 * @param inFile
	 *            String
	 * @param outFile
	 *            String
	 */
	public void exec(String inFile, String outFile)
	{
		try
		{
			process1(inFile);
			process2(inFile, outFile);
		}
		catch (IOException e)
		{
			System.out.println("Failed to process" + e.getMessage());
			e.printStackTrace();
		}

	}

	/**
	 * 第一遍遍历
	 * 
	 * @param inFile
	 * @throws IOException 
	 */
	private void process1(String inFile) throws IOException
	{
		try (BufferedReader br = new BufferedReader(new FileReader(inFile));)
		{
			int i = 0;
			String line = null;

			while ((line = br.readLine()) != null)
			{
				// 跳过空行和注释行
				if (line.equals("") || line.trim().startsWith("//"))
				{
					continue;
				}

				if (parser.commandType(line) == CmdConst.L_COMMAND)
				{
					String symbol = parser.symbol(line);
					if (!(sTable.contains(symbol)))
					{
						sTable.addEntry(symbol, i);
					}
				}
				else
				{
					i++;
				}
			}
		}
	}

	private void process2(String inFile, String outFile) throws IOException
	{
		int add = sTable.getNextAddress();
		String line = null;

		try (BufferedReader br = new BufferedReader(new FileReader(inFile));
		        BufferedWriter bw = new BufferedWriter(new FileWriter(outFile)))
		{

			while ((line = br.readLine()) != null)
			{
				// 跳过空行和注释行
				if (line.equals("") || line.trim().startsWith("//"))
				{
					continue;
				}

				String mCode = "";
				String cType = parser.commandType(line);
				switch (cType)
				{
				case CmdConst.A_COMMAND:
					String aCmd = parser.symbol(line);
					if (!Utils.isNumber(aCmd))
					{
						if (!(sTable.contains(aCmd)))
						{
							sTable.addEntry(aCmd, add++);
							mCode += zfill(mCode);
						}
						else
						{
							mCode += zfill(Integer.toBinaryString(sTable.getAddress(aCmd)));
						}
					}
					else
					{
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
		}
	}

	private String zfill(String in)
	{
		return in.length() == 15 ? in : zfill("0" + in);
	}
}
