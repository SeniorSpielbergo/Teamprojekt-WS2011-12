init
,[>,]>,[>,] write both numbers to action tape

compute
<  go to rightmost position of right number
[-
	[ if found 1 in right number
		>[+>]	fill 0s left of 1 with 1s / right number minus 1

		<[<]<	go to left number
		[
			search for 0
			-
			[  if found 1
				[<-]  make 0 and go on searching
			]++  if found 0 / make 1 / left number plus 1
			[>] add complete / go back between numbers to have hash
		]

		back to right number
		>[>]
		<-  check rightmost position of right number / if 1 start over
	]
	else found 0 in right number / restore it and keep searching for 1
	+<
]		
no 1 can be found / right number is only 0s

output
<[<]
>[.>] write left number to output tape