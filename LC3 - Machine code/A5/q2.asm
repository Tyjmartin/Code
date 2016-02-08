	.orig x3100;

	lea r0, prompt1;
	puts;
	trap xfb;
	add r2, r0, #0;		taking in the day
	
	lea r0, prompt2;
	puts;
	trap xfb;
	add r3, r0, #0;		taking in the month	
	
	lea r0, prompt3;
	puts;
	trap xfb;
	add r4, r0, #0;		taking in the year

	add r5, r2, #0;  adding k to f

	add r1, r3, #-2;
	BRp start;
	add r1, r1, #12;

start	and r0, r0, #0;  will jump to here if the number is not febuary or january
	add r0, r0, #13;
	trap xfe;
	add r0, r0, #-1;
	and r1, r1, #0;
	add r1, r1, #5;	
	trap xff;
	add r5, r5, r0; this is the second part of the equation

	
	and r0, r0, #0;   
	and r1, r1, #0;
	add r0, r4, #0;
	ld r1, num;
	trap xff;
	add r6, r3, #-2;
	BRp loop;
	add r1, r1, #-1;
	BRp loop;
	and r1, r1, #0;
	ld r1, num1;     	this will get the third part of the equation

loop 	add r5, r5, r1;


	and r0, r0, #0;
	add r0, r1, #0;
	and r1, r1, #0;
	add r1, r1, #4;
	trap xff;
	add r5, r5, r0;  	this will get the division by 4

	and r0, r0, #0;
	and r1, r1, #0;
	add r0, r4, #0;
	ld r1, num;
	trap xff;		
	and r6, r6, #0;
	add r6, r0, #0;
	and r1, r1, #0;
	add r1, r1, #4;
	trap xff;
	add r5, r5, r0;		more division for the C

	and r0, r0, #0;
	add r0, r6, #0;
	and r1, r1, #0;
	add r1, r1, #2;
	trap xfe;
	not r0, r0;
	add r0, r0, #1;
	add r5, r5, r0;		multiplying by 2C
	

	and r6, r6, #0;
	and r0, r0, #0;
	and r1, r1, #0;
	add r0, r5, #0;
	add r1, r1, #7;		modulus by 7
	trap xff;

	and r0, r0, #0;
	
	add r6, r1, #0;
	lea r0, months;		I have to use months here i have no idea what is wrong but it will return a day with months here

loop3	BRz display;
	add r0, r0, #10;	this is the display for day
	add r6, r6, #-1;
	BR loop3;
display PUTS;

halt;

prompt1	.stringz "Enter a day: ";
prompt2	.stringz "Enter a month#: ";
prompt3	.stringz "Enter a year: ";

num	.fill #100;
num1	.fill #99;

days	.stringz "Sunday   "
	.stringz "Monday   "
	.stringz "Tuesday  "
	.stringz "Wednesday"
	.stringz "Thursday "
	.stringz "Friday   "
	.stringz "Saturday "

months	.stringz "January  "
	.stringz "February "
	.stringz "March    "
	.stringz "April    "
	.stringz "May      "
	.stringz "June     "
	.stringz "July     "
	.stringz "August   "
	.stringz "September"
	.stringz "October  "
	.stringz "November "
	.stringz "December "

space	.stringz " ";

prompt4 .stringz " is a ";

	.end