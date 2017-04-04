package com.elements.vm;

public class Parser
{

	private Parser()
	{
		throw new AssertionError();
	}

	// 第一阶段只实现算术指令和push pop指令
	public static CommandType commandType(String line)
	{
		if (line.contains("add") || line.contains("sub") || line.contains("neg") || line.contains("eq")
		        || line.contains("gt") || line.contains("lt") || line.contains("and") || line.contains("or")
		        || line.contains("not"))
		{
			return CommandType.C_ARITHMETIC;
		}
		else if (line.contains("push"))
		{
			return CommandType.C_PUSH;
		}
		else if (line.contains("pop"))
		{
			return CommandType.C_POP;
		}
		else
		{
			return CommandType.C_IGNORE;
		}
	}

	public static String arg1(String line)
	{
		if (line.contains("add"))
		{
			return "add";
		}
		else if (line.contains("sub"))
		{
			return "sub";
		}
		else if (line.contains("neg"))
		{
			return "neg";
		}
		else if (line.contains("eq"))
		{
			return "eq";
		}
		else if (line.contains("gt"))
		{
			return "gt";
		}
		else if (line.contains("lt"))
		{
			return "lt";
		}
		else if (line.contains("and"))
		{
			return "and";
		}
		else if (line.contains("or"))
		{
			return "or";
		}
		else if (line.contains("not"))
		{
			return "not";
		}
		else
		{
			return line.trim().split("\\s")[1];
		}
	}

	public static int arg2(String line)
	{
		String result = null;
		String type = commandType(line).toString();
		if (type.equals("PUSH") || type.equals("POP") || type.equals("FUNCTION") || type.equals("CALL"))
		{
			result = line.trim().split("\\s")[2];
		}
		return Integer.parseInt(result);
	}
}
