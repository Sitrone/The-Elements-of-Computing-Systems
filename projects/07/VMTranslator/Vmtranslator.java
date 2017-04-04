package com.elements.vm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Vmtranslator
{
	private static final Vmtranslator INSTANCE = new Vmtranslator();

	public static Vmtranslator newInstance()
	{
		return INSTANCE;
	}
	
	public void process(String file)
	{
		CodeWriter codeWriter = new CodeWriter(file);
		try (BufferedReader br = new BufferedReader(new FileReader(file)))
		{
			String line = null;
			String type = null;
			while ((line = br.readLine()) != null)
			{
				// 跳过空行和注释行
				if (line.equals("") || line.trim().startsWith("//"))
				{
					continue;
				}

				type = Parser.commandType(line).toString();
				if ("ARITHMETIC".equals(type))
				{
					String attribute1 = Parser.arg1(line);
					codeWriter.writeArithmetic(attribute1);
				}
				else if (type.equals("PUSH") || type.equals("POP"))
				{
					String attribute1 = Parser.arg1(line);
					int attribute2 = Parser.arg2(line);
					codeWriter.writePushPop(type, attribute1, attribute2);
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			codeWriter.close();
		}
	}

}
