	.ORIG X3000;
	
	LD R1, NUM1;
	STI R1, X;

	ld r2, num2;
	sti r2, Y;

	ldi r1,X;
	ldi r2,Y;
	not r4, r2;
	add r4, r4, #1;
	add r3, r1, r4;
	sti r3, T;

	ldi r1, X;
	add r3, r1, #0;
	BRzp ZP;
	not r3, r3;
	add r3, r3, #1;
ZP
	sti r3, U;

	
	ldi r2, Y;
	add r3, r2, #0;
	BRzp PZ;
	not r3, r3;
	add r3, r3, #1;
PZ
	sti r3, V;


	ldi r6, U;
	ldi r7, V;
	not r4, r7;
	add r4, r4, #1;
	add r3, r6, r4;
	BRp positive;
	BRn negative;
	ld r3, zer;
	sti r3, W;
	halt
positive
	ld r3, neg;
	sti r3, W;
	halt
negative
	ld r3, pos;
	sti r3, W;

X: 	.FILL X3120;
Y:      .FILL X3121;
T: 	.FILL x3122;
U:	.FILL x3123;
V:	.FILL x3124;
W:	.FILL x3125;
NUM1:	.FILL 12;
num2:	.fill 12;
pos: 	.fill 2;
neg:	.fill 1;
zer:	.fill 0;




	.end