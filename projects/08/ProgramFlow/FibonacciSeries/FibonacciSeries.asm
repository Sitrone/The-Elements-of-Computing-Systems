//Push Argument 1
@1
D=A
@ARG
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
//Pop Pointer 1
@SP
A=M-1
D=M
@SP
M=M-1
@4
M=D
//Push Constant 0
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
//Pop That 0
@0
D=A
@THAT
D=D+M
@R13
M=D
@SP
A=M-1
D=M
@SP
M=M-1
@R13
A=M
M=D
//Push Constant 1
@1
D=A
@SP
A=M
M=D
@SP
M=M+1
//Pop That 1
@1
D=A
@THAT
D=D+M
@R13
M=D
@SP
A=M-1
D=M
@SP
M=M-1
@R13
A=M
M=D
//Push Argument 0
@0
D=A
@ARG
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
//Push Constant 2
@2
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
//Pop Argument 0
@0
D=A
@ARG
D=D+M
@R13
M=D
@SP
A=M-1
D=M
@SP
M=M-1
@R13
A=M
M=D
// label
(Main$MAIN_LOOP_START)
//Push Argument 0
@0
D=A
@ARG
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
// if-goto COMPUTE_ELEMENT
@SP
M=M-1
@SP
A=M
D=M
@Main$COMPUTE_ELEMENT
D;JNE
// goto END_PROGRAM
@Main$END_PROGRAM
0;JMP
// label
(Main$COMPUTE_ELEMENT)
//Push That 0
@0
D=A
@THAT
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
//Push That 1
@1
D=A
@THAT
A=D+M
D=M
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
//Pop That 2
@2
D=A
@THAT
D=D+M
@R13
M=D
@SP
A=M-1
D=M
@SP
M=M-1
@R13
A=M
M=D
//Push Pointer 4
@4
D=M
@SP
A=M
M=D
@SP
M=M+1
//Push Constant 1
@1
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
//Pop Pointer 1
@SP
A=M-1
D=M
@SP
M=M-1
@4
M=D
//Push Argument 0
@0
D=A
@ARG
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
//Push Constant 1
@1
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
//Pop Argument 0
@0
D=A
@ARG
D=D+M
@R13
M=D
@SP
A=M-1
D=M
@SP
M=M-1
@R13
A=M
M=D
// goto MAIN_LOOP_START
@Main$MAIN_LOOP_START
0;JMP
// label
(Main$END_PROGRAM)
