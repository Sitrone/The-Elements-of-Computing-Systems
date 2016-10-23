// 配合测试脚本去理解
// SimpleAdd.tst测试脚本里面第一句话：
// set RAM[0] 256,

//Push constant 7
@7       
D=A  	// D = 7
@SP		// M=RAM[0]=256  sp为预定义符号，对应的RAM地址位0,M为取SP所对应的RAM地址的值
A=M     // A=RAM[0]
M=D		// 地址256处的值设为7
@SP		// M=RAM[0] 
M=M+1	// M=RAM[0] + 1 地址0处的值为257
//Push constant 8
@8
D=A  	// D = 8
@SP
A=M
M=D		// 地址257处的值设为8
@SP
M=M+1	// M=RAM[0] + 1=258
// add
@SP
AM=M-1  // M=257=RAM[0]
D=M     // D=8
@SP		
A=M-1	// A = 256
M=D+M	// RAM[256] =8 + RAM[256] = 8 + 7
