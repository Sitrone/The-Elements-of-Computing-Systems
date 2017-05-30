package com.elements.compiler;

import java.io.File;

public class CompilationEngine
{
	private File inFile;
	private File outFile;
	
	public CompilationEngine(File inFile, File outFile)
	{
		this.inFile = inFile;
		this.outFile = outFile;
	}
}
