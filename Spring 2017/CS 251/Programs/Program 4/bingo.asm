#======================================================================================
## Authors:  Justin Espiritu and Calvin Meier
## Description:  Program that plays BINGO only checks for horizontal and vertical
## Assumptions:  User must put in capital letters
## 5/8/17
#======================================================================================

# ===================== Start of data
.data
   SIZE: .word 5 							# max size of column and row
   MARKED: .word 1 							# = marked position
   
   board: .word 0:25 							# 2d array of 0's
   
   col: .byte 								# input from user
   	.align 2							# align by word
   
   column: .word 0 							# current column
   row: .word 0 							# current row
   status: .word 0 							# keeps track of status (BINGO or not)
    
   title: .asciiz "  B I N G O" 					# board title
   	  .align 2							# align by word
   welcome: .asciiz "Welcome to BINGO!\n" 				# welcome prompt
   	    .align 2							# align by word
   bingo: .asciiz "\n \n !!! BINGO !!! \n"				# winner prompt
   	  .align 2 							# align by word
   inputPrompt: .asciiz "Enter your choice (Q to quit): "		# input prompt
           	.align 2						# align by word
   invalidPrompt: .asciiz "\nInvalid position.\n"			# invalid prompt
   		  .align 2						# align by word
   positionMarked: .asciiz "\nPosition marked.\n"			# position marked prompt
   		   .align 2						# align by word
   alreadyMarked: .asciiz "\nPosition already marked.\n"		# already marked prompt
   		  .align 2						# align by word
   
# ===================== End of data
   
.text

# ===================== Start of Main
MAIN:
   la $a0, welcome 							# print welcome prompt
   li $v0, 4
   syscall
   
   jal DISPLAYBOARD 							# function call to display board
   
   sub $sp, $sp, 8							# prologue function call
   sw $a0, 4($sp)
   sw $a1, ($sp)
   
   la $a0, col 								# load address for arguments of function call
   la $a1, row 								# load address for arguments of function call
   
   jal READINPUT 							# function call to read input
   
   lw $a0, 4($sp)							# clean up function call
   lw $a1, ($sp)
   add $sp, $sp, 8				
   
   # ================== Start of while loop 				# repeat while loop until user quits or BINGO 
   WHILE_LOOP_MAIN:
      beq $a0, 81, EXIT							# check if user input = Q
      
      sub $sp, $sp, 8							# prologue function call
      sw $a0, 4($sp)
      sw $a1, ($sp)
      
      lw $a0, col 							# load words for arguments of function call
      lw $a1, row 							# load words for argument sof function call
      
      jal MARKPOSITION 							# function call to mark position
      
      lw $a0, 4($sp)							# clean up function call
      lw $a1, ($sp)
      add $sp, $sp, 8				
      
      jal DISPLAYBOARD 							# function call to display board
      
      sub $sp, $sp, 8							# prologue function call
      sw $a0, 4($sp)
      sw $a1, ($sp)
      
      la $a0, col 							# load address for arguments of function call
      la $a1, row 							# load address for arguments of function call
      
      jal READINPUT 							# function call to read input
      
      lw $a0, 4($sp)							# clean up function call
      lw $a1, ($sp)
      add $sp, $sp, 8				
      
      b WHILE_LOOP_MAIN							# branch back to while loop main
   # ================== End of While loop
   EXIT:
      li $v0, 10 							# exit program call
      syscall
   
# ===================== End of Main

# ===================== Start of mark position
MARKPOSITION:
   blt $a1, 1, INVALID							# check if row is less than 1
   bgt $a1, 5, INVALID							# check if row is greater than 5
   b VALID								# if it is valid input branch to valid
   
   INVALID:
      li $s0, -1							# load -1 
      sw $s0, status							# store -1 into status
      	
      sub $sp, $sp, 8							# move stack 8 bytes
      sw $a0, 4($sp)							# store $a0 into stack
      sw $a1, ($sp)							# store $a1 into stack
      	
      la $a0, ($zero)							# clear $a0
      la $a1, ($zero)							# clear $a1
      	
      la $a0, invalidPrompt						# print invalid prompt
      li $v0, 4
      syscall
      
      lw $a1, ($sp)							# load argument back into $a1
      lw $a0, 4($sp)							# loadargument back into $a0
      add $sp, $sp, 8							# move stack back to current location
      b END_MARKPOSITION						# branch to end mark position
      	
   VALID:
      beq $a0, 66, COL_B						# check if col = 'B'       
      beq $a0, 73, COL_I						# check if col = 'I'
      beq $a0, 78, COL_N						# check if col = 'N'
      beq $a0, 71, COL_G						# check if col = 'G'
      beq $a0, 79, COL_O						# check if col = 'O'
      b INVALID
      
      COL_B:
         li $s0, 0							# store 0
         sw $s0, column							# store 0 into column
         b CHECK_MARK							# branch to check mark
      COL_I:
         li $s0, 1							# store 1 
         sw $s0, column							# store 1 into column
         b CHECK_MARK							# branch to check mark
      COL_N:
         li $s0, 2							# store 2
         sw $s0, column							# store 2 into column
         b CHECK_MARK							# branch to check mark
      COL_G:
         li $s0, 3							# store 3
         sw $s0, column							# store 3 into column
         b CHECK_MARK							# branch to check mark
      COL_O:
         li $s0, 4							# store 4
         sw $s0, column							# store 4 into column
         b CHECK_MARK							# branch to check mark

      CHECK_MARK:
         sw $s1, SIZE							# store SIZE
         la $s2, board							# load board
         move $s3, $s2							# move offset
         sub $a1, $a1, 1						# adjust row for array
         # ========= Move through array
         mul $s4, $a1, $s1						# multiply current row w/ amount of columns 
         add $s4, $s4, $s0						# add current column
         mul $s4, $s4, 4 						# multiply the whole thing by 4 (size of a word)
         add $s3, $s3, $s4 						# then add to address of offset to move through array
         # ========= End of move through array
         
         lw $s5, ($s3)							# load word of array position
         lw $s6, MARKED							# load MARKED
         beq $s5, $s6, ALREADY_MARKED					# check if position is marked
         b MARK								# otherwise branch to mark
         
         ALREADY_MARKED:
            sub $sp, $sp, 8						# move stack 8 bytes
      	    sw $a0, 4($sp)						# store $a0 into stack
            sw $a1, ($sp)						# store $a1 into stack
      
      	    la $a0, ($zero)						# clear $a0
      	    la $a1, ($zero)						# clear $a1
      	
      	    la $a0, alreadyMarked					# print already marked prompt
      	    li $v0, 4
      	    syscall
      
      	    lw $a1, ($sp)						# load argument back into $a1
      	    lw $a0, 4($sp)						# loadargument back into $a0
      	    add $sp, $sp, 8						# move stack back to current location
      	    b END_MARKPOSITION						# branch to end mark position
         MARK:
            sw $s6, ($s3)						# mark current position in array
            sub $sp, $sp, 8						# move stack 8 bytes
            
      	    sw $a0, 4($sp)						# store $a0 into stack
            sw $a1, ($sp)						# store $a1 into stack
      	
      	    la $a0, ($zero)						# clear $a0
      	    la $a1, ($zero)						# clear $a1
      	
      	    la $a0, positionMarked					# print position marked prompt
      	    li $v0, 4
      	    syscall
      
      	    lw $a1, ($sp)						# load argument back into $a1
      	    lw $a0, 4($sp)						# loadargument back into $a0
      	    add $sp, $sp, 8						# move stack back to current location
      	    b END_MARKPOSITION						# branch to end mark position
            
      END_MARKPOSITION:
         sub $sp, $sp, 4						# go to empty stack register
         sw $ra, ($sp)							# store current address
         jal DISPLAYSTATUS						# function call DisplayStatus
         lw $ra, ($sp)							# load previous return address back
         add $sp, $sp, 4						# move stack back to current position
         jr $ra								# jump back to function call
         
   
# ===================== End of mark position

# ===================== Start of read input
READINPUT:
   sub $sp, $sp, 4							# move stack to empty stack
   sw $a0, ($sp)							# store argument to stack
   
   la $a0, inputPrompt 							# print input prompt
   li $v0, 4
   syscall
   
   lw $a0, ($sp)							# load previous argument back to stack
   add $sp, $sp, 4							# move stack to current stack
   
   li $v0, 12								# read char input
   syscall
   
   sw $v0, ($a0)							# store char input into col
   beq $v0, 81, EXIT							# check user input for Q
 
   li $v0, 5								# read integer input
   syscall
   	
   sw $v0, ($a1)							# store integer input into row
   jr $ra								# jump back to function call
# ===================== End of read input

# ===================== Start of Display Status
DISPLAYSTATUS:
   li $s0, 0 								# load outer loop variable = 0
   lw $s1, SIZE 							# load SIZE
   la $s4, board 							# load board as array
   move $s5, $s4 							# load size as variable
   
   # ================== Start of outer loop
   OUTER_LOOP_START_HORIZONTAL_DISPLAYSTATUS:
      li $s2, 0 							# load count as variable = 0
      blt $s0, $s1, OUTER_LOOP_BODY_HORIZONTAL_DISPLAYSTATUS 		# check if outer loop varaible is less than SIZE
      b OUTER_LOOP_END_HORIZONTAL_DISPLAYSTATUS 			# otherwise branch to outer loop end 
   OUTER_LOOP_BODY_HORIZONTAL_DISPLAYSTATUS:
      li $s3, 0 							# load inner loop variable = 0 
      
      # =============== Start of inner loop
      INNER_LOOP_START_HORIZONTAL_DISPLAYSTATUS:
         blt $s3, $s1, INNER_LOOP_BODY_HORIZONTAL_DISPLAYSTATUS 	# check if inner loop variable is less than SIZE
         b INNER_LOOP_END_HORIZONTAL_DISPLAYSTATUS 			# otherwise branch to inner loop end
         
      INNER_LOOP_BODY_HORIZONTAL_DISPLAYSTATUS:
         move $s5, $s4 							# reset starting point of array
         # ========= Move through array
         mul $s6, $s0, $s1						# multiply current row w/ amount of columns 
         add $s6, $s6, $s3 						# add current column
         mul $s6, $s6, 4 						# multiply the whole thing by 4 (size of a word)
         add $s5, $s5, $s6 						# then add to address of offset to move through array
         # ========= End of move through array
         
         lw $s7, ($s5) 							# load content of location into address
         bne $s7, 1, IF_HORIZONTAL_NOT_MARKED				# check if location is marked
         
         add $s2, $s2, 1 						# increment count
         
         IF_HORIZONTAL_NOT_MARKED:
            bne $s2, 5, NO_HORIZONTAL_BINGO 
            
            sub $sp, $sp, 4 						# move stack to empty register
            sw $s0, ($sp) 						# store $s0 into empty register
            
            li $s0, 1 							# load 1 into register
            sw $s0, status						# store 1 into status
            
            lw $s0, ($sp)						# load previous $so back into register
            add $sp, $sp, 4						# current stack into register
            
            b OUTER_LOOP_END_HORIZONTAL_DISPLAYSTATUS			# break
            
         NO_HORIZONTAL_BINGO:
            add $s3, $s3, 1						# increment inner loop variable
            b INNER_LOOP_START_HORIZONTAL_DISPLAYSTATUS			# branch back to inner loop start
        						      
      INNER_LOOP_END_HORIZONTAL_DISPLAYSTATUS:
         add $s0, $s0, 1						# increment outer loop variable
         b OUTER_LOOP_START_HORIZONTAL_DISPLAYSTATUS			# branch back to outer loop start
      # =============== End of inner loop
      
   OUTER_LOOP_END_HORIZONTAL_DISPLAYSTATUS:
      sub $sp, $sp, 4 							# move stack to empty register
      sw $s0, ($sp) 							# store $s0 into empty register
            
      lw $s0, status							# load status
      beq $s0, 1, BINGO							# check status if winner
            
      lw $s0, ($sp)							# load previous $so back into register
      add $sp, $sp, 4							# current stack into register  
      b VERTICAL_CHECK							# branch to vertical check               
   # ================== End of outer loop

# ===================== Start of Bingo   
BINGO:
   lw $s0, ($sp)							# load previous $so back into register
   add $sp, $sp, 4							# current stack into register  
   
   la $a0, bingo							# print winner prompt
   li $v0, 4
   syscall
   
   li $v0, 10								# exit on BINGO
   syscall 
# ===================== End of Bingo
     
VERTICAL_CHECK: 
   li $s0, 0 								# load outer loop variable = 0
   lw $s1, SIZE 							# load SIZE
   la $s4, board 							# load board as array
   move $s5, $s4 							# load size as variable
   
   # ================== Start of outer loop
   OUTER_LOOP_START_VERTICAL_DISPLAYSTATUS:
      li $s2, 0 							# load count as variable = 0
      blt $s0, $s1, OUTER_LOOP_BODY_VERTICAL_DISPLAYSTATUS 		# check if outer loop varaible is less than SIZE
      b OUTER_LOOP_END_VERTICAL_DISPLAYSTATUS 				# otherwise branch to outer loop end 
   OUTER_LOOP_BODY_VERTICAL_DISPLAYSTATUS:
      li $s3, 0 							# load inner loop variable = 0 
      
      # =============== Start of inner loop
      INNER_LOOP_START_VERTICAL_DISPLAYSTATUS:
         blt $s3, $s1, INNER_LOOP_BODY_VERTICAL_DISPLAYSTATUS 		# check if inner loop variable is less than SIZE
         b INNER_LOOP_END_VERTICAL_DISPLAYSTATUS 			# otherwise branch to inner loop end
         
      INNER_LOOP_BODY_VERTICAL_DISPLAYSTATUS:
         move $s5, $s4 							# reset starting point of array
         # ========= Move through array
         mul $s6, $s3, $s1						# multiply current column w/ amount of rows
         add $s6, $s6, $s0 						# add current row
         mul $s6, $s6, 4 						# multiply the whole thing by 4 (size of a word)
         add $s5, $s5, $s6 						# then add to address of offset to move through array
         # ========= End of move through array
         
         lw $s7, ($s5) 							# load content of location into address
         bne $s7, 1, IF_VERTICAL_NOT_MARKED				# check if location is marked
         
         add $s2, $s2, 1 						# increment count
         
         IF_VERTICAL_NOT_MARKED:
            bne $s2, 5, NO_VERTICAL_BINGO 
            
            sub $sp, $sp, 4 						# move stack to empty register
            sw $s0, ($sp) 						# store $s0 into empty register
            
            li $s0, 1 							# load 1 into register
            sw $s0, status						# store 1 into status
            
            lw $s0, ($sp)						# load previous $so back into register
            add $sp, $sp, 4						# current stack into register
            
            b OUTER_LOOP_END_VERTICAL_DISPLAYSTATUS			# break
            
         NO_VERTICAL_BINGO:
            add $s3, $s3, 1						# increment inner loop variable
            b INNER_LOOP_START_VERTICAL_DISPLAYSTATUS			# branch back to inner loop start
        						      
      INNER_LOOP_END_VERTICAL_DISPLAYSTATUS:
         add $s0, $s0, 1						# increment outer loop variable
         b OUTER_LOOP_START_VERTICAL_DISPLAYSTATUS			# branch back to outer loop start
      # =============== End of inner loop
      
   OUTER_LOOP_END_VERTICAL_DISPLAYSTATUS:
      sub $sp, $sp, 4 							# move stack to empty register
      sw $s0, ($sp) 							# store $s0 into empty register
            
      lw $s0, status							# load status
      beq $s0, 1, BINGO							# check status if winner
            
      lw $s0, ($sp)							# load previous $so back into register
      add $sp, $sp, 4							# current stack into register 
      
      jr $ra								# jump back to function calll
# ===================== End of Display Status

# ===================== Start of DisplayBoard Function
DISPLAYBOARD:
   la $a0, title 							# print Bingo board
   li $v0, 4
   syscall
   
   li $a0, 10 								# print new line
   li $v0, 11
   syscall
   
   li $s0, 0 								# load outer loop variable as 0
   li $s2, 1 								# load count variable
   lw $s3, SIZE 							# load SIZE
   la $s4, board 							# load board as array
   move $s5, $s4 							# move offset
   
   # ================== Start of Outer Loop
   OUTER_LOOP_START_DISPLAYBOARD:
      blt $s0, $s3, OUTER_LOOP_BODY_DISPLAYBOARD 			# branch into loop if less than SIZE
      b OUTER_LOOP_END_DISPLAYBOARD 					# otherwise end loop
      
      OUTER_LOOP_BODY_DISPLAYBOARD:   
         move $a0, $s2 							# print what row number
         li $v0, 1
         syscall
         
         li $a0, 32 							# print space
         li $v0, 11
         syscall
         
         li $s1, 0 							# load inner loop variable as 0
         # ============ Start of Inner Loop
         INNER_LOOP_START_DISPLAYBOARD:            
            blt $s1, $s3, INNER_LOOP_BODY_DISPLAYBOARD 			# branch into loop if less than SIZE
            b INNER_LOOP_END_DISPLAYBOARD 				# otherwise end loop
               
         INNER_LOOP_BODY_DISPLAYBOARD:
            move $s5, $s4 						# reset starting point of array
            # ========= Move through array
            mul $s6, $s0, $s3 						# multiply current row w/ amount of columns 
            add $s6, $s6, $s1						# add current column
            mul $s6, $s6, 4 						# multiply the whole thing by 4 (size of a word)
            add $s5, $s5, $s6 						# then add to address of offset to move through array
            # ========= End of move through array
            
            lw $s7, ($s5) 						# load content of location into address
            beq $s7, $zero, IF_DISPLAYBOARD 				# If content is == 0 branch to if
            b ELSE_DISPLAYBOARD 					# otherwise branch to else
            IF_DISPLAYBOARD:
               li $a0, 95 						# print "_"
               li $v0, 11
               syscall
               
               li $a0, 32 						# print space
               li $v0, 11
               syscall
               
               add $s1, $s1, 1						#increment inner loop variable
               #move $s5, $s4 						# reset starting point of array
               b INNER_LOOP_START_DISPLAYBOARD 				# branch start of inner loop
               
            ELSE_DISPLAYBOARD:
               li $a0, 88 						# print "X"
               li $v0, 11
               syscall
               
               li $a0, 32 						# print space
               li $v0, 11
               syscall
               
               add $s1, $s1, 1 						# increment innerloop variable
               #move $s5, $s4						# reset starting point of array
               b INNER_LOOP_START_DISPLAYBOARD 				# branch start of inner loop
               
         INNER_LOOP_END_DISPLAYBOARD:
            li $a0, 10 							# print space
            li $v0, 11
            syscall
                 
            add $s2, $s2, 1 						# increment count
            add $s0, $s0, 1 						# increment outer loop variable
            b OUTER_LOOP_START_DISPLAYBOARD				# branch to start of outer loop
         # ============ End of Inner Loop
            
      OUTER_LOOP_END_DISPLAYBOARD:
         jr $ra 							# go back to function call
   
# ===================== End of DisplayBoard Function

# ===================== End program
