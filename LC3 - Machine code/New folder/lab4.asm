	.orig x3000;

	lea r6, xff;
	ld r1, num1;
	str r1, r6, x0; 

	add r5, r1, #-2;
	BRnz loop;

	and r2, r2, #0;
	and r3, r3, #0;
	ld r2, num2;
	ld r3, num2;
	add r1,r1,#-2;

loop2	add r4,r3,r2;
	and r2, r2, #0;
	add r2, r3, #0;
	and r3, r3, #0;
	add r3, r4, #0;
	add r1, r1, #-1;
	BRp loop2;
	str r3, r6, x1;
	BR loop3;
	halt

loop 	ld r2, num2;
	str r2, r6, x1;
	BR loop3;
	halt

loop3 	and r5, r5, #0;
	and r2, r2, #0;
	and r3, r3, #0;
	and r4, r4, #0;
	ld r5, num3;
	ld r2, num2;
	ld r3, num2;
loop4	add r4, r3,r2;
	BRn loop5;
	and r2, r2, #0;
	add r2, r3, #0;
	and r3, r3, #0;
	add r3, r4, #0;
	add r5, r5, #1;
	BR loop4;
	
	halt
loop5 	str r5, r6, x2;
	str r3, r6, x3;
	
	


num1	.fill 19;
num2	.fill 1;
num3	.fill 2;



	.end;