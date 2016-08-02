###小结
1、基本的门电路，And, Or, Not, Xor, Mux, Dmux

2、所有基本的门电路都可以又Nand（与非门）或者Xor（异或门）来构建，他们两个都具备函数完备性

3、高层电路都是对底层电路的不断抽象，隐藏内部实现细节，只给出输入和输出
例如And = Nand(Nand(a, b), Nand(a, b))

###布尔代数
Not(a) = Nand(a, a)
true = Not(False)
And(a, b) = Not(Nand(a, b))
Or(a, b) = Not(And(Not(a), Not(b)))
Xor(a, b) = Or(And(a, Not(b), And(Not(a), b)))

