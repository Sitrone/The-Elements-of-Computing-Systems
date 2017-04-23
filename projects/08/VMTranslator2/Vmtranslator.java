package com.elements.vm2;

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
		// codeWriter.writeInit();

		try (BufferedReader br = new BufferedReader(new FileReader(file)))
		{
			String line = null;
			CommandType type = null;

			while ((line = br.readLine()) != null)
			{
				String attribute1 = null;
				int attribute2 = 0;
				// 跳过空行和注释行
				if (line.equals("") || line.trim().startsWith("//"))
				{
					continue;
				}

				type = Parser.commandType(line.trim());
				switch (type)
				{
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
					codeWriter.writeIf(attribute1);
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

				/*
				  if ("ARITHMETIC".equals(type)) { attribute1 =
				  Parser.arg1(line); codeWriter.writeArithmetic(attribute1); }
				  else if (type.equals("PUSH") || type.equals("POP")) {
				  attribute1 = Parser.arg1(line); attribute2 =
				  Parser.arg2(line); codeWriter.writePushPop(type.toString(),
				 attribute1, attribute2); }else if (type.equals("LABEL")) {
				 attribute1 = Parser.arg1(line);
				  codeWriter.writeLabel(attribute1); }else if
				  (type.equals("GOTO")) { attribute1 = Parser.arg1(line);
				  codeWriter.writeGoto(attribute1); }else if
				  (type.equals("IF")) { attribute1 = Parser.arg1(line);
				  codeWriter.writeGoto(attribute1); }else if
				  (type.equals("FUNCTION")) { attribute1 = Parser.arg1(line);
				  attribute2 = Parser.arg2(line);
				  codeWriter.writeFunction(attribute1, attribute2); }else if
				  (type.equals("RETURN")) { codeWriter.writeReturn(); }else if
				 (type.equals("CALL")) { attribute1 = Parser.arg1(line);
				  attribute2 = Parser.arg2(line);
				  codeWriter.writeCall(attribute1, attribute2); }
				 */
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
