package com.elements.vm;

public enum CommandType {
	
	C_ARITHMETIC {
		public String toString() {
			return "Arithmetic";
		}
	},
	
	C_PUSH {
		public String toString() {
			return "Push";
		}
	},
	
	C_POP {
		public String toString() {
			return "Pop";
		}
	},
	
	C_IGNORE {
		public String toString() {
			return "Ignore";
		}
	},
}
