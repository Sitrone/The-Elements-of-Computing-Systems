package com.elements.compiler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class JackAnalyzer
{
	private String inputFile;

	private JackTokenizer tokenizer;

	private CompilationEngine engine;

	public JackAnalyzer(String inputFile) throws IOException
	{
		this.inputFile = inputFile;
		File outFile = createXmlFile(inputFile);

		tokenizer = new JackTokenizer(inputFile);
		engine = new CompilationEngine(new File(inputFile), outFile);
	}

	/**
	 * 消除注释, 包括//, /**
	 * @param inputFile
	 * @throws IOException
	 */
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

	private File createXmlFile(String inputFile) throws IOException
	{
		File in = new File(inputFile);
		if (!inputFile.endsWith(".jack") || !in.exists())
		{
			throw new IllegalArgumentException("Illegal input file name" + inputFile);
		}
		String xmlFile = inputFile.replace(".jack", ".xml");
		File out = new File(xmlFile);

		if (out.exists())
		{
			System.out.println("Target xml file " + out.getCanonicalPath() + " is existing.");
			out.delete();
		}

		Files.createFile(out.toPath());
		System.out.println("Succeed to create xml file " + out);
		return out;
	}

	public String getInputFile()
	{
		return inputFile;
	}

	public void setInputFile(String inputFile)
	{
		this.inputFile = inputFile;
	}
}
