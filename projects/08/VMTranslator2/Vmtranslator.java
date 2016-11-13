package com.elements.vm2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Vmtranslator
{
	CodeWriter codeWriter;

	BufferedReader br = null;

	public void process(String file)
	{
		codeWriter = new CodeWriter(file);
		try
		{
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		String line = null;
		CommandType type = null;
		try
		{
			while ((line = br.readLine()) != null)
			{
				String attribute1 = null;
				int attribute2 = 0;
				// 跳过空行和注释行
				if (line.equals("") || line.trim().startsWith("//"))
				{
					continue;
				}
				
				type = Parser.commandType(line);
				switch(type){
				case C_ARITHMETIC:
					attribute1 = Parser.arg1(line);
					codeWriter.writeArithmetic(attribute1);
					break;
				case C_PUSH:
				case C_POP:
					attribute1 = Parser.arg1(line);
					attribute2 = Parser.arg2(line);
					codeWriter.writePushPop(type.toString(), attribute1, attribute2);
					break;
				case C_LABEL:
					attribute1 = Parser.arg1(line);
					codeWriter.writeLabel(attribute1);
					break;
				case C_GOTO:
					attribute1 = Parser.arg1(line);
					codeWriter.writeGoto(attribute1);
					break;
				case C_IF:
					attribute1 = Parser.arg1(line);
					codeWriter.writeGoto(attribute1);
					break;
				case C_FUNCTION:
					attribute1 = Parser.arg1(line);
					attribute2 = Parser.arg2(line);
					codeWriter.writeFunction(attribute1, attribute2);
					break;
				case C_RETURN:
					codeWriter.writeReturn();
					break;
				case C_CALL:
					attribute1 = Parser.arg1(line);
					attribute2 = Parser.arg2(line);
					codeWriter.writeCall(attribute1, attribute2);
					break;
				default:
				}
				
/*				if ("ARITHMETIC".equals(type))
				{
					attribute1 = Parser.arg1(line);
					codeWriter.writeArithmetic(attribute1);
				} else if (type.equals("PUSH") || type.equals("POP"))
				{
					attribute1 = Parser.arg1(line);
					attribute2 = Parser.arg2(line);
					codeWriter.writePushPop(type.toString(), attribute1, attribute2);
				}else if (type.equals("LABEL"))
				{
					attribute1 = Parser.arg1(line);
					codeWriter.writeLabel(attribute1);
				}else if (type.equals("GOTO"))
				{
					attribute1 = Parser.arg1(line);
					codeWriter.writeGoto(attribute1);
				}else if (type.equals("IF"))
				{
					attribute1 = Parser.arg1(line);
					codeWriter.writeGoto(attribute1);
				}else if (type.equals("FUNCTION"))
				{
					attribute1 = Parser.arg1(line);
					attribute2 = Parser.arg2(line);
					codeWriter.writeFunction(attribute1, attribute2);
				}else if (type.equals("RETURN"))
				{
					codeWriter.writeReturn();
				}else if (type.equals("CALL"))
				{
					attribute1 = Parser.arg1(line);
					attribute2 = Parser.arg2(line);
					codeWriter.writeCall(attribute1, attribute2);
				}*/
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if (null != br)
				{
					br.close();
				}
				codeWriter.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

}
