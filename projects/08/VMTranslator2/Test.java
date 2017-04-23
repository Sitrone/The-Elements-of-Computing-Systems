package com.elements.vm2;

import java.io.File;

public class Test
{

	public static void main(String[] args)
	{
		String root = "C:\\Program Files\\GreenSoftware\\nand2tetris\\nand2tetris\\projects\\08\\ProgramFlow";
		test(root);
	}

	private static void test(String str)
	{
		File root = new File(str);
		File[] list = root.listFiles();

		if (list == null)
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
				System.out.println("outFile: " + getFileName(f.getAbsolutePath()));
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
		return in.substring(0, indext) + ".asm";
	}
}
