count the symbols on inputtape that are not hashes
action tape will be lsb left to msb right
output tape will be msb left to lsb right

count
, write first inputcharacter
[ if exists increment binary number
	[-]> delete inputchar and go to lsb of binary number
	- check if its 1 or 0
	[ if 1
		[>-] go to next bit and check that for 1 or 0
	]++ else 0 / write 1 / gets binary number plus 1
	[<] go back to position left of number
	, write next inputcharacter
]
else inputcharacter is hash

output
write number of action tape from right to left to output tape / gets msb to lsb on output
>[>]
<[.<]