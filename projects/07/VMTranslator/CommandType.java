package com.elements.vm;

public enum CommandType
{

	C_ARITHMETIC
	{
		public String toString()
		{
			return "ARITHMETIC";
		}
	},

	C_PUSH
	{
		public String toString()
		{
			return "PUSH";
		}
	},

	C_POP
	{
		public String toString()
		{
			return "POP";
		}
	},

	C_IGNORE
	{
		public String toString()
		{
			return "IGNORE";
		}
	},
}
