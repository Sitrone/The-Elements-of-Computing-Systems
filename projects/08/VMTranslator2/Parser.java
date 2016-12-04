package com.elements.vm2;

public class Parser
{

    private Parser()
    {
        throw new AssertionError();
    }

    // 第二阶段实现算术指令和push pop指令和剩下的7个指令
    public static CommandType commandType(String line)
    {
        if (line.contains("add") || line.contains("sub") || line.contains("neg") || line.contains("eq")
                || line.contains("gt") || line.contains("lt") || line.contains("and") || line.contains("or")
                || line.contains("not"))
        {
            return CommandType.C_ARITHMETIC;
        }
        else if (line.startsWith("push"))
        {
            return CommandType.C_PUSH;
        }
        else if (line.startsWith("pop"))
        {
            return CommandType.C_POP;
        }
        else if (line.startsWith("label"))
        {
            return CommandType.C_LABEL;
        }
        else if (line.startsWith("goto"))
        {
            return CommandType.C_GOTO;
        }
        else if (line.startsWith("if-goto"))
        {
            return CommandType.C_IF;
        }
        else if (line.startsWith("function"))
        {
            return CommandType.C_FUNCTION;
        }
        else if (line.startsWith("return"))
        {
            return CommandType.C_RETURN;
        }
        else if (line.startsWith("call"))
        {
            return CommandType.C_CALL;
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
