'''
Justin Espiritu
version 1.1
program grabs the top 5 used words in given articles and print them into a cvs file
'''

import nltk, string, itertools, csv
from nltk.corpus import stopwords, reuters, wordnet
from nltk import word_tokenize, FreqDist, re

#start of loop ##repeat 5 times
#find text via id and set to variable
    ## we can change the number of the id by adding index to the loop 
    ##or we can grab all texts and put into list and grab [:5]
#remove any punctuations, numbers, or any stopWords
#set FreqDist of text to variable
#add new synonyms to list
#format lists to be strings
#remove all numbers or stopWords
#setFreqDist of synonyms to variable
#create a list of synonyms *again in synset format
#print number of samples into csv file
#print top 5 synonyms
#repeat loop
