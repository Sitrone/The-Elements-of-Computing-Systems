package com.elements.vm2;

public enum CommandType
{

	C_ARITHMETIC
	{
		@Override
		public String toString()
		{
			return "ARITHMETIC";
		}
	},

	C_PUSH
	{
		@Override
		public String toString()
		{
			return "PUSH";
		}
	},

	C_POP
	{
		@Override
		public String toString()
		{
			return "POP";
		}
	},
	
//  新增
	C_LABEL
	{
		@Override
		public String toString()
		{
			return "LABEL";
		}
	},
	
	C_GOTO
	{
		@Override
		public String toString()
		{
			return "GOTO";
		}
	},
	
	C_IF
	{
		@Override
		public String toString()
		{
			return "IF";
		}
	},
	
	C_FUNCTION
	{
		@Override
		public String toString()
		{
			return "FUNCTION";
		}
	},
	
	C_RETURN
	{
		@Override
		public String toString()
		{
			return "RETURN";
		}
	},
	
	C_CALL
	{
		@Override
		public String toString()
		{
			return "CALL";
		}
	},
	C_IGNORE
	{
		@Override
		public String toString()
		{
			return "IGNORE";
		}
	},
}
