// RAM[0]=256

@17  
D=A // D=17
@SP // M=RAM[0]=256
A=M // A=256
M=D // M=RAM[256]=17
@SP // M=RAM[0]=256
M=M+1 // M=RAM[257]

@17
D=A	// D=17
@SP // M=RAM[0]=257
A=M // A=257
M=D // M=RAM[257]=17
@SP // M=RAM[0]=257
M=M+1 // M=RAM[258]

@SP  // M=RAM[0]=258
M=M-1  // M=258-1=257
A=M  // A=257
D=M	 //D=Memory[257]=17
@SP	 // M=RAM[0]=257
M=M-1  //M=257-1=256
A=M    //A=256
D=D-M  // D=17-RAM[256]=17-17

@RET_EQ0
D;JEQ
@SP
A=M
M=0
@SP
M=M+1
@END0
0;JMP
(RET_EQ0)
@SP
A=M // M=256
M=-1 // M=RAM[256]=-1
@SP
M=M+1 // M=256+1=257
(END0)

@17
D=A
@SP
A=M
M=D
@SP
M=M+1
@16
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
D=D-M
@RET_EQ1
D;JEQ
@SP
A=M
M=0
@SP
M=M+1
@END1
0;JMP
(RET_EQ1)
@SP
A=M
M=-1
@SP
M=M+1
(END1)
@16
D=A
@SP
A=M
M=D
@SP
M=M+1
@17
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
D=D-M
@RET_EQ2
D;JEQ
@SP
A=M
M=0
@SP
M=M+1
@END2
0;JMP
(RET_EQ2)
@SP
A=M
M=-1
@SP
M=M+1
(END2)
@892
D=A
@SP
A=M
M=D
@SP
M=M+1
@891
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
D=D-M
@RET_LT3
D;JGT
@SP
A=M
M=0
@SP
M=M+1
@END3
0;JMP
(RET_LT3)
@SP
A=M
M=-1
@SP
M=M+1
(END3)
@891
D=A
@SP
A=M
M=D
@SP
M=M+1
@892
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
D=D-M
@RET_LT4
D;JGT
@SP
A=M
M=0
@SP
M=M+1
@END4
0;JMP
(RET_LT4)
@SP
A=M
M=-1
@SP
M=M+1
(END4)
@891
D=A
@SP
A=M
M=D
@SP
M=M+1
@891
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
D=D-M
@RET_LT5
D;JGT
@SP
A=M
M=0
@SP
M=M+1
@END5
0;JMP
(RET_LT5)
@SP
A=M
M=-1
@SP
M=M+1
(END5)
@32767
D=A
@SP
A=M
M=D
@SP
M=M+1
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
D=M-D
@RET_GT6
D;JGT
@SP
A=M
M=0
@SP
M=M+1
@END6
0;JMP
(RET_GT6)
@SP
A=M
M=-1
@SP
M=M+1
(END6)
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1
@32767
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
D=M-D
@RET_GT7
D;JGT
@SP
A=M
M=0
@SP
M=M+1
@END7
0;JMP
(RET_GT7)
@SP
A=M
M=-1
@SP
M=M+1
(END7)
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
D=M-D
@RET_GT8
D;JGT
@SP
A=M
M=0
@SP
M=M+1
@END8
0;JMP
(RET_GT8)
@SP
A=M
M=-1
@SP
M=M+1
(END8)
@57
D=A
@SP
A=M
M=D
@SP
M=M+1
@31
D=A
@SP
A=M
M=D
@SP
M=M+1
@53
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
D=D+M
@SP
M=M+1
@112
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
D=M-D
@SP
M=M+1
@SP
M=M-1
A=M
D=-M
M=D
@SP
M=M+1
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
D=D&M
@SP
M=M+1
@82
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
D=D|M
@SP
M=M+1
@SP
M=M-1
A=M
M=!M
@SP
M=M+1
