# ======================================================================
# Description: Program that reverses an array of integeres
# Justin Espiritu
# 4/16/2017
# ======================================================================

# ============== # Data segment
.data
   promptA: .asciiz "How many integers would you like to enter (up to 50)?\n" # ask user how many integersthey would like to enter
            .align 2 # align by .word
   promptB: .asciiz "Enter the Values:\n" # ask user to enter values
   	    .align 2 # align by .word
   promptC: .asciiz "Here are the values in order:\n" # prompt for values in order
   	    .align 2 # align by .word
   promptD: .asciiz "Here are the values in reverse:\n" # prompt for values in reverse
   	    .align 2 # align by .word
   nextLine: .asciiz "\n" # call for new line
   	     .align 2 # align by .word
   array: .space 200 # allocate space for max amount of numbers
   reverseArray: .space 200 # allocate space for reverse array
   max: .word # max user wants inputed
   #count: .word # count user wants inputed

   
.text
.globl MAIN
# ========================================== # MAIN start
MAIN:
# ========================================== # prompt user 
   la $a0, promptA # load address with prompt
   li $v0, 4 # load immediate to print
   syscall # system call print
   
# ========================================== # read in amount from user
   li $v0, 5 # load address to save max value
   syscall # load immediate to save
   move $t0, $v0 # move to temporary address
   la $t1, max # load count into address
   sw $t0, 0($t1) # save user input into address
   
   blez $t0, EXIT # if number is 0 go to Exit
   
# ========================================== # read in values from user
   la $a0, promptB # load address with prompt to enter values
   li $v0, 4 # load immediate to print
   syscall # system call print
   
   li $s0, 0 # count = 0
   la $s1, array # load the array to address
   move $s2, $s1 # offset into array
   
   
# ========================================== # start of array loop
   ARRAY_LOOP_START:
      li $v0, 5 # load immediate to read
      syscall # system call read
      # move $t2, $v0 # move read input to array address
      sw $v0, ($s1) # save word into array
      
      add $s1, $s1, 4 # add to move in array
      add $s0, $s0, 1 # increase count by 1
      blt  $s0, $t0, ARRAY_LOOP_START # repeat until count reaches 0

# ========================================== # print prompt   
   la $a0, promptC # load promptC to address
   li $v0, 4 # load immediate for print
   syscall # syscall print
   
   la $s1, array # load array to address
   move $s2, $s1 # offset into array
   
# ========================================== # print array loop
   PRINT_ARRAY_LOOP:
      lw $a0, ($s2) # load word into address
      li $v0, 1 # load immediate to print
      syscall # system call print
      
      la $a0, nextLine # print a new line
      li $v0, 4 # load immediate for print
      syscall # syscall print
      
      add $s2, $s2, 4 # add 4 to go through array
      sub $s0, $s0, 1 # decrement by 1 to count
      bgtz $s0, PRINT_ARRAY_LOOP # repeat until count is the same as max
      
# ========================================== # load array to another address
   sub $s2, $s2, 4 # to go back into array
   # add $s0, $s0, 1 # set count to 1
   
   la $s3, array # load array to address
   move $s4, $s3 # offset into array  
   la $t2, ($t0) # copy max to another address
   
# ========================================== # print prompt
   la $a0, promptD # load promptD to address
   li $v0, 4 # load immediate for print
   syscall # syscall print       
     
# ========================================== # reverse array loop
   REVERSE_ARRAY_LOOP: # Start of reverse array loop
      lw $t3, ($s4) # load word from array to temp address
      lw $t4, ($s2) # load word from array to reverse address in array
      sw $t4, ($s4) # store word from temp into array
      sw $t3, ($s2) # store word from temp address to reverse address in array
      
      add $s4, $s4, 4 # increment to move in array
      sub $s2, $s2, 4 # decrement to move in array
      
      add $s0, $s0, 1 # increment count
      sub $t0, $t0, 1 # decrement max
      
      bgt $t0, $s0, REVERSE_ARRAY_LOOP # if max is still larger than count go back through loop
      
# ========================================== # reload array and set count to 0
   la $s1, array # load array to address
   move $s2, $s1 # offset into array
   li $s0, 0 # reset count to 0
   
# ========================================== # print array
      PRINT_REVERSE_ARRAY_LOOP:
         lw $a0, ($s2) # load word into address
         li $v0, 1 # load immediate to print
         syscall # system call print
      
         la $a0, nextLine # print a new line
         li $v0, 4 # load immediate for print
         syscall # syscall print
      
         add $s2, $s2, 4 # add 4 to go through array
         add $s0, $s0, 1 # increase count by 1
         blt $s0, $t2, PRINT_REVERSE_ARRAY_LOOP # repeat until count is the same as max
      
# ========================================== # start of exit   
EXIT:
   li $v0, 10 # load immediate to ext
   syscall # system call exit
   
# end program
