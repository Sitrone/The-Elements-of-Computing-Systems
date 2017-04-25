package com.elements.vm2;

import java.io.File;
import java.io.FilenameFilter;

public class Test
{
	private static final FilenameFilter VM_FILTER = new VMFileFilter();

	public static void main(String[] args)
	{
		String root = "C:\\Program Files\\GreenSoftware\\nand2tetris\\nand2tetris\\projects\\08\\FunctionCalls\\SimpleFunction";
		test(root);
	}

	private static void test(String str)
	{
		File file = new File(str);
		if(!file.exists())
		{
			System.out.println("Failed to read file:" + file);
			System.exit(0);
		}
		if (file.isDirectory())
		{
			System.out.println("Dir: " + file.getAbsolutePath());
			File[] vmFiles = file.listFiles(VM_FILTER);
			for (File f : vmFiles)
			{
				System.out.println("matches : " + f.getAbsolutePath());
				Vmtranslator.newInstance().process(f.getAbsolutePath());
			}
		}
		else if (file.isFile())
		{
			System.out.println("inFile: " + file.getAbsolutePath());
			System.out.println("outFile: " + getFileName(file.getAbsolutePath()));
			if (file.getAbsolutePath().endsWith(".vm"))
			{
				System.out.println("matches : " + file.getAbsolutePath());
				Vmtranslator.newInstance().process(file.getAbsolutePath());
			}

		}
	}

	private static String getFileName(String in)
	{
		int indext = in.lastIndexOf(".");
		return in.substring(0, indext) + ".asm";
	}

	private static class VMFileFilter implements FilenameFilter
	{
		@Override
		public boolean accept(File dir, String name)
		{
			return name.endsWith(".vm");
		}
	}
}
