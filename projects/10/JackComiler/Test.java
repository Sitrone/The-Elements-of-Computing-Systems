package com.elements.compiler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;

public class Test
{
	private static final FilenameFilter VM_FILTER = new VMFileFilter();

	public static void main(String[] args) throws IOException
	{
		String root = "C:\\Program Files\\GreenSoftware\\nand2tetris\\nand2tetris\\projects\\10\\ArrayTest";
		 test(root);
	}

	private static void test(String str) throws IOException
	{
		File file = new File(str);
		if (!file.exists())
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
				copyFile(f.getAbsolutePath());
			}
		}
	}

	private static void copyFile(String inputFile) throws IOException
	{
		String outputFile = inputFile + ".copy";
		try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
		        BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile)))
		{
			String line = null;
			String out = null;
			while ((line = br.readLine()) != null)
			{
				if (line.equals("") || line.trim().startsWith("//"))
				{
					continue;
				}
				else if (line.contains("//"))
				{
					int index = line.indexOf("//");
					out = line.substring(0, index);
					bw.write(out);
				}
				else if (line.trim().startsWith("/*") && line.trim().endsWith("*/"))
				{
					continue;
				}
				else if (line.trim().contains("/*"))
				{
					if (!line.trim().substring(0, line.indexOf("/*")).equals(""))
					{
						bw.write(line.trim().substring(0, line.indexOf("/*")));
					}
					while ((line = br.readLine()).indexOf("*/") < 0)
					{
					}
					if (!line.substring(line.indexOf("*/") + 2).trim().equals(""))
					{
						bw.write(line.substring(line.indexOf("*/") + 2).trim());
					}
				}
				else
				{
					bw.write(line.trim());
				}
			}
		}
	}

	/**
	 * 文件过滤器
	 * 
	 * @author Administrator
	 *
	 */
	private static class VMFileFilter implements FilenameFilter
	{
		@Override
		public boolean accept(File dir, String name)
		{
			return name.endsWith(".jack");
		}
	}
}
