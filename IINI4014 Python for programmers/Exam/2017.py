import re
from collections import Counter

def read_file(filename):
    input_file = open(filename, 'r')
    input_string = input_file.read()
    input_file.close()
    return input_string

def sentence_length(string):
    sentence_word_length = []
    sentences = string.split(".")
    for sentance in sentences:
        sentence_word_length.append(len(sentance.split(" ")))
    
    total = 0
    for length in sentence_word_length:
        total += length
    
    avg_sentance_length_in_words = total / len(sentence_word_length)

def different_words(string):
    counted = Counter(re.findall(r'\w', string))
    total_words = 0
    for values in counted.values():
        total_words += values
    different = len( counted.keys() )
    percentage_different_words = total_words/different
    return percentage_different_words