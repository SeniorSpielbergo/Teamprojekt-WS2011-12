adds two binary numbers
input has to be two binary numbers devided by one hash
first character of input has to be msb of first number
last character of input has to be lsb of second number

init
,[>,]>,[>,] write both numbers to action tape

compute
<  go to rightmost position of right number
[-  check if its 1 or 0
	[ if found 1
		>[+>]	fill 0s right of found 1 with 1s / gets right number minus 1

		<[<]<	go to rightmost position of left number
		[
			- check if its 1 or 0
			[ if found 1
				[<-]  write 0 and go to next bit
			]++ else found 0 / write 1 / gets left number plus 1
			[>] go back between numbers to have hash
		]

		back to right number
		>[>]
		<-  check rightmost position of right number / if 1 start over
	]
	else found 0 / restore it and keep searching for 1
	+<
]		
no 1 can be found / right number is only 0s / finished

output
<[<]
>[.>] write left number to output tape