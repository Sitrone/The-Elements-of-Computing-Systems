// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel. When no key is pressed, the
// program clears the screen, i.e. writes "white" in every pixel.

// Put your code here.

// define max screen(24576)
@24576
D=A
@MAXSCREEN
M=D

// define screen pointer (16384)
@SCREEN
D=A
@POINTER
M=D

(LOOP)
  // 如果键盘有输入，则调到fill进行处理
  @KBD
  D=M
  @FILL
  D; JGT

  // 否则执行unfill
  @UNFILL
  0; JMP


(UNFILL)
  // if POINTER = SCREEN, do nothing
  @SCREEN
  D=A
  @POINTER
  D=D-M
  @LOOP
  D; JEQ

  // unfill the screen
  D=0
  @POINTER
  A=M
  M=D

  // Decrement POINTER
  D=1
  @POINTER
  D=M-D
  @POINTER
  M=D

  // Jump back to main loop
  @LOOP
  0; JMP

(FILL)
  // if screen is full, do nothing
  @MAXSCREEN
  D=M
  @POINTER
  D=D-M
  @LOOP
  D; JEQ

  // 填充pointer指向的位置
  D=-1
  @POINTER
  A=M
  M=D

  // 迭代pointer 加1
  D=1
  @POINTER
  D=D+M
  @POINTER
  M=D

  // 跳回main 循环
  @LOOP
  0; JMP

// REF:https://github.com/cmoylan/Elements-of-Computing-Systems/blob/master/project04/fill/Fill.asm
