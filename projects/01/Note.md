###С��
1���������ŵ�·��And, Or, Not, Xor, Mux, Dmux

2�����л������ŵ�·��������Nand������ţ�����Xor������ţ��������������������߱������걸��

3���߲��·���ǶԵײ��·�Ĳ��ϳ��������ڲ�ʵ��ϸ�ڣ�ֻ������������
����And = Nand(Nand(a, b), Nand(a, b))

###��������
Not(a) = Nand(a, a)
true = Not(False)
And(a, b) = Not(Nand(a, b))
Or(a, b) = Not(And(Not(a), Not(b)))
Xor(a, b) = Or(And(a, Not(b), And(Not(a), b)))

