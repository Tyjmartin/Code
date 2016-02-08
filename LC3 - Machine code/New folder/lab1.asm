	.orig x3000;


	LEA R2, xff;
	LDR R1, R2, x0;
	LDR R3, R2, x1;

	LD R1, X;
	STR R1, R2, x0;
	LD r3, Y;
	STR R3 , R2 , x1;
	
	LDR R1, R2, X0;
	LDR R3, R2, X1;
	AND R4 , R4 , x0;
	ADD r4, r1, r3;
	str r4, r2, x2;

	LDR R1, R2, X0;
	LDR R3, R2, X1;
	AND R4, R4, X0;
	AND R4, R3, R1;
	STR R4, R2, X3;


	LDR R1, R2, X0;
	LDR R3, R2, X1;
	NOT R1, R1;
	NOT R3, R3;
	AND R4, R1, R3;
	NOT R4, R4;
	STR R4, R2, X4;



	LDR R1, R2, X0;
	NOT R4, R1;
	STR R4, R2, X5;

	LDR R3, R2, X1;
	NOT R4, R3;
	STR R4, R2, X6;

	LDR R1, R2, X0;
	ADD R4, R1, #3;
	STR R4, R2, X7;

	LDR R3, R2, X1;
	ADD R4, R3, #-3;
	STR R4, R2, X8;
	
	LDR R1, R2, X0;
	AND R4, R1, X0001;
	STR R4, R2, X9;
	
X:	.fill 9;
Y:	.fill 12;
	.end