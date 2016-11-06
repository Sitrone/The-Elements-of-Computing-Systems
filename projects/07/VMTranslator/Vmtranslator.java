package com.elements.vm;

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
		String type = null;
		try
		{
			while ((line = br.readLine()) != null)
			{
				// 跳过空行和注释行
				if (line.equals("") || line.trim().startsWith("//"))
				{
					continue;
				}
				
				type = Parser.commandType(line).toString();
				if ("C_ARITHMETIC".equals(type))
				{
					String attribute1 = Parser.arg1(line);
					codeWriter.writeArithmetic(attribute1);
				} else if (type.equals("C_PUSH") || type.equals("C_POP"))
				{
					String attribute1 = Parser.arg1(line);
					int attribute2 = Parser.arg2(line);
					codeWriter.writePushPop(type, attribute1, attribute2);
				}
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
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

}
