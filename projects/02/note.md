1、 half-adder: 不带低位来的进位的加法器，分成两部分，sum由xor运算模拟，carry部分由and运算模拟

2、 full-adder：带低位进位来的加法器，看做三位相加，由两个half-adder和一个or组成

3、Inc： 累加器（+1），通过加法器 + 1完成

4、IsNeg: 由最高位来判断输入数据的正负， 通过And 0x01实现

5、ALU：算术逻辑单元，通过组各种简单的门电路合来实现复杂运算