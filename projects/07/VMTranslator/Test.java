package com.elements.vm;

import java.io.File;

public class Test
{
	static String root = "C:\\Program Files\\GreenSoftware\\nand2tetris\\nand2tetris\\projects\\07";

	public static void main(String[] args)
	{
		test(root);
	}

	private static void test(String str)
	{
		File root = new File(str);
		File[] list = root.listFiles();

		if (null == list)
		{
			return;
		}
		for (File f : list)
		{
			if (f.isDirectory())
			{
				System.out.println("Dir: " + f.getAbsolutePath());
				test(f.getAbsolutePath());
			}
			else if (f.isFile())
			{
				System.out.println("inFile: " + f.getAbsolutePath());
				System.out.println("outFile: " + getFileName(f.getName()));
				if (f.getAbsolutePath().endsWith(".vm"))
				{
					Vmtranslator.newInstance().process(f.getAbsolutePath());
					System.out.println("matches : " + f.getAbsolutePath());
				}

			}
		}
	}

	private static String getFileName(String in)
	{
		int indext = in.lastIndexOf(".");
		return new String(root + in.substring(0, indext) + ".asm");
	}
}
