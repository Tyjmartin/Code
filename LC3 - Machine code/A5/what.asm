	.orig x3000;

loop	lea r0, prompt;
	puts;
	trap xfb;
	add r1, r0, #0;
	BRzp zp;
	not r1, r1;
	add r1, r1, #1;
	add r6, r6, #-1;	This is my check for the negativem I just check the first one because that is the one that matters

zp
	lea r0, prompt;
	puts;
	trap xfb;
	add r2, r0, #0;

JSR 	subr			calls the subroutine
	
	trap xfc;
	lea r0, nl;
	puts;

	BR loop
	
	Halt

subr	and r0, r0, #0;		setting up r0
	add r4, r0, #1;		the bit mask

loop 	and r5, r2, r4;		this is my check for zero
	BRz iszero;
	add r0, r0, r1;		adds if its not zero
	
iszero 	add r1, r1, r1;		shifts the bit
	
	BRn check		checks to see if it can continue doing it

	add r4, r4, r4;		increments the bitmask
	
	BR loop

check 	add r6, r6, #0		if check is negative it branchs here to deal with check if it is a negative number
	BRzp pos;
	not r0, r0;
	add r0,r0,#1;
	
pos  	ret


nl:	.stringz "\n";
prompt: .stringz "Please enter an integer ";




.end;