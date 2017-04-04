package com.elements.assembler;

import java.io.Closeable;
import java.io.IOException;

public class Utils
{
	/**
	 * 在final中安静的关闭, 不再往外抛出异常避免影响原有异常，最常用函数. 同时兼容Closeable为空未实际创建的情况.
	 */
	public static void closeQuietly(Closeable closeable)
	{
		if (closeable != null)
		{
			try
			{
				closeable.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public static boolean isNumber(String target)
	{
		for (char c : target.toCharArray())
		{
			if (!Character.isLetter(c))
			{
				return false;
			}
		}
		return true;
	}
}
