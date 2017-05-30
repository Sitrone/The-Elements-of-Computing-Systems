package com.elements.compiler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class JackTokenizer
{
	private Reader reader;

	private TokenType currentToken;

	public JackTokenizer(String inputFile) throws FileNotFoundException
	{
		this.reader = new BufferedReader(new FileReader(new File(inputFile)));
	}

	public TokenType advance() throws IOException
	{
		if (hasMoreTokens())
		{
			return getCurrentTokenType();
		}
		return null;
	}

	public boolean hasMoreTokens() throws IOException
	{
		return reader.read() != -1;
	}

	public TokenType getCurrentTokenType()
	{
		return TokenType.IDENTIFIER;
	}
}
