package com.elements.vm2;

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
	
	public static boolean checkFuncName(String name)
	{
		if (Character.isDigit(name.charAt(0)))
		{
			return false;
		}
		for (Character c : name.toCharArray())
		{
			if (!isIdentifer(c))
			{
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 获得文件的全路径
	 * 
	 * @param path
	 * @return
	 */
	public static String getFilePath(String path)
	{
		int indexOfLastSlash = path.lastIndexOf("\\");
		return path.substring(0, indexOfLastSlash + 1);
	}
	
	/**
	 * 获得文件名
	 * 
	 * @param path
	 * @return
	 */
	public static String getFileName(String path)
	{
		int indexOfLastSlash = path.lastIndexOf("\\");
		int indexOfLastDot = path.lastIndexOf(".");
		return path.substring(indexOfLastSlash + 1, indexOfLastDot);
	}

	private static boolean isIdentifer(Character c)
	{
		return Character.isLetterOrDigit(c) || c.equals('_') || c.equals('.') || c.equals(':');
	}
}
