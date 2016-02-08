	.orig x3100;

	
loop2	lea r0, prompt; this loads the string prompt in r0
	puts;		outputs it the screen from r0		

	getc;

	Add r3,r0,x0;	this is leading the ascii in r3 and doing the work on it
	add r3,r3,#-16;
	add r3,r3,#-16;
	add r3,r3,#-16;

	
	add r4, r3, #-6;checks to see if the number is between 0-6
	BRp loop3	;will halt if it is 

	lea r0, days;	loads the days in x3100
	add r3, r3, x0;

loop	BRz display	;outputs the memory locations one at a time.
	add r0, r0, #10;
	add r3, r3, #-1;
	BR loop		;calls the loop

display PUTS
	BR loop2

loop3   Halt

days	.stringz "Sunday   "
	.stringz "Monday   "
	.stringz "Tuesday  "
	.stringz "Wednesday"
	.stringz "Thursday "
	.stringz "Friday   "
	.stringz "Saturday "

prompt:	.stringz "Please enter a #: "

	.end