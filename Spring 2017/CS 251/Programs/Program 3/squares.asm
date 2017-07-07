# ================================================================================
# Description: Print all positive, perfect squares, integers between two numbers
# Author: Justin Espiritu
# Date: 4/15/2017
# ================================================================================

.data
    integerA: .word # first integer inputed by user
    integerB: .word # second integer inputed by user
    promptA: .asciiz "Enter first integer: "  # prompt to enter first integer
    	     .align 2 # align promptA by .word
    promptB: .asciiz "Enter second integer: " # prompt to enter second integer
    	     .align 2 # align promptB by .word
    promptC: .asciiz "Distinct integers required" # prompt when two integers are entered
    	     .align 2 # align promptC by .word
    promptD: .asciiz "Done. Normal termination." # prompt for normal termination
    	     .align 2 # align promptD by .word 
    newLine: .asciiz "\n" # command for new line
    	     .align 2 # align newline by .word
   	     
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
    
# ================================================ # check if inputs are the same
    beq $t0, $t1, IF_EQUAL

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
        la $t4, 1 # load 1 into address for squares
        beq $t0, $t1, DONE # if equal we exit program, no square inbetween 
        bgt $t0, $t1, A_GREATER_LOOP_START # check to see if integerA is greater

    
# ================================================ # what square are inbetween integerA and integerB
    B_GREATER_LOOP_START: # start of loop
        mul $t3, $t4, $t4 # square squareInteger and save to address
        bge $t3, $t1, INCREMENT_A #  if statement, if square is greater go to INCREMENT_A
        ble $t3, $t0, INCREMENT_A # if statement, if square is less than or equal go to INCREMENT_A
       
        # ========================================= # start of if statement
        la $a0, 0($t3) # load square to address
        li $v0, 1 # load immediate for print
        syscall # system call print
        la $a0, newLine # load new line command to address
        li $v0, 4 # load immediate for command
        syscall # system call new line
       
        # ========================================= # increment integerA by 1
        INCREMENT_A:
            add $t4, $t4, 1 # add 1 to 1
            blt $t4, $t1, B_GREATER_LOOP_START # go through loop again
            b DONE # exit program
        # End loop
       
    A_GREATER_LOOP_START: # start of loop
        mul $t3, $t4, $t4 # square squareInteger and save to address
        bge $t3, $t0, INCREMENT_B # if statement, if square is greater go to INCREMENT_B
        ble $t3, $t1, INCREMENT_B # if statement, if square is less than or equal integerB got to INCREMENT_B
       
        # ========================================= # start of if statement
        la $a0, 0($t3) # load square to address
        li $v0, 1 # load immediate for print
        syscall # system call print
        la $a0, newLine # load new line command to address
        li $v0, 4 # load immediate for command
        syscall # system call new line
        
        # ========================================= # start of INCREMENT_B
        INCREMENT_B:
            add $t4, $t4, 1 # add 1 to integerA
            blt $t4, $t0, A_GREATER_LOOP_START # go through loop again
            b DONE # exit program
        # End loop
           	 
# ================================================ # Print PromptD
    DONE:
        la $a0, promptD # load promptD to address
        li $v0, 4 # load immediate for promptD
        syscall # system call print
        b EXIT # branch to exit program

# ================================================ # If inputs are equal
    IF_EQUAL:
        la $a0, promptC # load promptC to address
        li $v0, 4 # load immediate for promptC
        syscall # system call print
        
    
# ================================================ # Exit    
EXIT: 
    li $v0, 10 # load immediate for exit
    syscall # system call exit

# program end
