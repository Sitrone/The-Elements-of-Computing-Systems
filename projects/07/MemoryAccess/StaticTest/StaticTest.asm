//Push Constant 111
@111
D=A
@SP
A=M
M=D
@SP
M=M+1
//Push Constant 333
@333
D=A
@SP
A=M
M=D
@SP
M=M+1
//Push Constant 888
@888
D=A
@SP
A=M
M=D
@SP
M=M+1
//Pop Static 8
@SP
A=M-1
D=M
@SP
M=M-1
@StaticTest.8
M=D
//Pop Static 3
@SP
A=M-1
D=M
@SP
M=M-1
@StaticTest.3
M=D
//Pop Static 1
@SP
A=M-1
D=M
@SP
M=M-1
@StaticTest.1
M=D
//Push Static 3
@StaticTest.3
D=M
@SP
A=M
M=D
@SP
M=M+1
//Push Static 1
@StaticTest.1
D=M
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
//Push Static 8
@StaticTest.8
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
