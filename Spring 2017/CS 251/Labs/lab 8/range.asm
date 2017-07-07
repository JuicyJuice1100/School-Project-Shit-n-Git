.data
    integerA: .word # first integer inputed by user
    integerB: .word # second integer inputed by user
    promptA: .asciiz "Enter first integer: "  # prompt to enter first integer
    	     .align 2	# align promptA by .word
    promptB: .asciiz "Enter second integer: " #prompt to enter second integer
    	     .align 2 # align promptB by .word
   	     
.text

MAIN:
# ================================================ # ask the user to enter first integer
    la $a0, promptA # load promptA into address
    li $v0, 4 # prompt user to enter first integer
    syscall # system call line 18

# ================================================ # read input from user and save into integerA
    li $v0, 5 # read input from user
    syscall # system call line 21
    move $t0, $v0 # move stored integer into temporary address
    la $t1, integerA # loading integerA into address
    sw $t0, 0($t1) # save user input into address
    
# ================================================ # ask the user to enter second integer
    la $a0, promptB # load prompB into address
    li $v0, 4 # prompt user to enter first integer
    syscall # system call line 30

# ================================================ # read input from user and save into integerB
    li $v0, 5 # read input from user
    syscall # system call line 34
    move $t1, $v0 # move stored integer into temporary address
    la $t2, integerB # loading integerB into address
    sw $t1, 0($t2) # save user sinput into address
    
# ================================================ # check if integerA is positive
    bLez $t0, IF_INTEGER_A_LESS_ZERO # check to see if integerA is negative
    b CHECK_INTEGER_B # if positive branch to CHECK_INTEGER_B to check integerB
    
        IF_INTEGER_A_LESS_ZERO:
            la $t0, 0 # load 0 into temporary address

# ================================================ # check if integerB is positive
    CHECK_INTEGER_B:
    	
    blez $t1, IF_INTEGER_B_LESS_ZERO # check to see if integerB is negative
    b NEXT # if positive branch to next
    
    	IF_INTEGER_B_LESS_ZERO:
    	    la $t1, 0 # load 0 into temporary address
        
# ================================================ # find out which integer is greater        
    NEXT:
    
    bgt $t0, $t1, INT_A_GREATER # check to see if integerA is greater
    bgt $t1, $t0, INT_B_GREATER # check to see if integerB is greater
    
# ================================================ # calculate how many positve integers between

    IF_EQUAL: # if integerA and integerB are equal
    
    	la $a0, 0 # load 0 into address
    	b PRINT # branch to PRINT
    	
    INT_A_GREATER: # if integerA is greater than integerB
    
    	sub $t0, $t0, 1 # subtract 1 from integerA
    	sub $t0, $t0, $t1 # subtract integerB from integerA
    	move $a0, $t0 # move new number to address
    	b PRINT # branch to PRINT
    	
    INT_B_GREATER: # if integerB is greater than integerA
    
    	sub $t1, $t1, 1 # subtract 1 from integerB
    	sub $t1, $t1, $t0 # subtract integerA from integerB
    	move $a0, $t1 # move new number to address
    	b PRINT # branch to PRINT
     	 
# ================================================ # print out results

    PRINT: # print
    	li $v0, 1 # load immediate for print
    	syscall # system call print
    
    
    
# ================================================ # Exit    
.EXIT 
    li $v0, 10 # load immediate for exit
    syscall # system call exit

# program end
