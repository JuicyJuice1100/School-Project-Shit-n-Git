# ====================== # start of .data
.data
   x: .word 1 # int x = 1
# ====================== # end of .data
.text
# ====================== start of MAIN
MAIN:
   lw $t0, x # load x into address
   bgt $t0, 0, IF # if(x > 0)
   b ELSE # else
   
   IF:
      li $t0, 5 # int x = 5 
      b EXIT 
   ELSE:
      li $a0, 6 # load immediate 6
      
      jal F # call F function
   
EXIT:   
   li $v0, 10
   syscall
      
F:
   move $s0, $a0 # int y = 6
   add $s0, $s0, 1 # add 1 to y
   
   lw $s1, ($s0) # int x = y + 1
   
   sub $sp, $sp, 12 # move stack
   sw $s0, ($sp) # store local 
   sw $s1, 4($sp)
   sw $ra, 8($sp) # store $ra
   
   jal G
   
   lw $ra, 8($sp) # load back in
   lw $s1, 4($sp) 
   lw $s0, ($sp) 
   add $sp, $sp, 12 # move stack back
   
   li $s0, 4 # int z = 4
   
   jr $ra # return to function call
   
G:
   jr $ra # return to function call
   
   
   
      
# ====================== end of MAIN
