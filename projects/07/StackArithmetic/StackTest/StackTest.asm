//Push Constant 17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1
//Push Constant 17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1
//eq
@SP
M=M-1
A=M
D=M
@SP
A=M-1
D=D-M
@RET_EQ0
D;JEQ
@SP
A=M-1
M=0
@END0
0;JMP
(RET_EQ0)
@SP
A=M-1
M=-1
(END0)
//Push Constant 17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1
//Push Constant 16
@16
D=A
@SP
A=M
M=D
@SP
M=M+1
//eq
@SP
M=M-1
A=M
D=M
@SP
A=M-1
D=D-M
@RET_EQ1
D;JEQ
@SP
A=M-1
M=0
@END1
0;JMP
(RET_EQ1)
@SP
A=M-1
M=-1
(END1)
//Push Constant 16
@16
D=A
@SP
A=M
M=D
@SP
M=M+1
//Push Constant 17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1
//eq
@SP
M=M-1
A=M
D=M
@SP
A=M-1
D=D-M
@RET_EQ2
D;JEQ
@SP
A=M-1
M=0
@END2
0;JMP
(RET_EQ2)
@SP
A=M-1
M=-1
(END2)
//Push Constant 892
@892
D=A
@SP
A=M
M=D
@SP
M=M+1
//Push Constant 891
@891
D=A
@SP
A=M
M=D
@SP
M=M+1
//lt
@SP
M=M-1
A=M
D=M
@SP
A=M-1
D=M-D
@RET_LT3
D;JLT
@SP
A=M-1
M=0
@END3
0;JMP
(RET_LT3)
@SP
A=M-1
M=-1
(END3)
//Push Constant 891
@891
D=A
@SP
A=M
M=D
@SP
M=M+1
//Push Constant 892
@892
D=A
@SP
A=M
M=D
@SP
M=M+1
//lt
@SP
M=M-1
A=M
D=M
@SP
A=M-1
D=M-D
@RET_LT4
D;JLT
@SP
A=M-1
M=0
@END4
0;JMP
(RET_LT4)
@SP
A=M-1
M=-1
(END4)
//Push Constant 891
@891
D=A
@SP
A=M
M=D
@SP
M=M+1
//Push Constant 891
@891
D=A
@SP
A=M
M=D
@SP
M=M+1
//lt
@SP
M=M-1
A=M
D=M
@SP
A=M-1
D=M-D
@RET_LT5
D;JLT
@SP
A=M-1
M=0
@END5
0;JMP
(RET_LT5)
@SP
A=M-1
M=-1
(END5)
//Push Constant 32767
@32767
D=A
@SP
A=M
M=D
@SP
M=M+1
//Push Constant 32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1
//gt
@SP
M=M-1
A=M
D=M
@SP
A=M-1
D=M-D
@RET_GT6
D;JGT
@SP
A=M-1
M=0
@END6
0;JMP
(RET_GT6)
@SP
A=M-1
M=-1
(END6)
//Push Constant 32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1
//Push Constant 32767
@32767
D=A
@SP
A=M
M=D
@SP
M=M+1
//gt
@SP
M=M-1
A=M
D=M
@SP
A=M-1
D=M-D
@RET_GT7
D;JGT
@SP
A=M-1
M=0
@END7
0;JMP
(RET_GT7)
@SP
A=M-1
M=-1
(END7)
//Push Constant 32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1
//Push Constant 32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1
//gt
@SP
M=M-1
A=M
D=M
@SP
A=M-1
D=M-D
@RET_GT8
D;JGT
@SP
A=M-1
M=0
@END8
0;JMP
(RET_GT8)
@SP
A=M-1
M=-1
(END8)
//Push Constant 57
@57
D=A
@SP
A=M
M=D
@SP
M=M+1
//Push Constant 31
@31
D=A
@SP
A=M
M=D
@SP
M=M+1
//Push Constant 53
@53
D=A
@SP
A=M
M=D
@SP
M=M+1
//add
@SP
AM=M-1
D=M
@SP
A=M-1
M=D+M
//Push Constant 112
@112
D=A
@SP
A=M
M=D
@SP
M=M+1
//sub
@SP
AM=M-1
D=M
@SP
A=M-1
M=M-D
//neg
@SP
A=M-1
M=-M
//and
@SP
AM=M-1
D=M
@SP
A=M-1
M=D&M
//Push Constant 82
@82
D=A
@SP
A=M
M=D
@SP
M=M+1
//or
@SP
AM=M-1
D=M
@SP
A=M-1
M=D|M
//not
@SP
A=M-1
M=!M