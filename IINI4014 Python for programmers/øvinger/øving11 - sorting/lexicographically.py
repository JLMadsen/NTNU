import os, time

def readfile(filename):
    """
    For reading file containing strings.
    Returns unsorted array of strings.
    """
    file_input = open(filename, 'r')
    input_string = file_input.read()
    file_input.close()
    string_array = input_string.split(" ")
    
    return string_array

def sort(array):
    """
    Method for sorting an array of strings.
    line, comment:
    29 First i find the longest word, this length is longest_word_length.
    34 sub_arrays is an array containing longest_word_length value amount of arrays.
    38 Then put all the words in sub_array based on length, if length is 2, it is placed in array 2.
    39 I use sub_arrays because this makes sorting based on word length very easy!
    41 After this there may be empty sub_arrays, to optimize the program i will remove them.
    47 Then i go thru each array sorting them lexicographically, i do this using selection sort. They are sorted low to high.
    55 Finally i append all sub_arrays into one final sorted array.
    59 Returns sorted array.
    """
    longest_word_length = 0
    for word in array:
        if len(word) > longest_word_length:
            longest_word_length = len(word)
    
    sub_arrays = []
    for length in range(longest_word_length):
        sub_arrays.append([])

    for word in array:
        sub_arrays[len(word)-1].append(word)
    
    temp_array = []
    for array in sub_arrays:
        if array:
            temp_array.append(array)
    sub_arrays = temp_array
    
    print(sub_arrays)
    
    for array in sub_arrays:
        for i in range(len(array)):
            minimum = i
            for j in range(i+1, len(array)):
                if array[minimum] > array[j]:
                    minimum = j
            array[i], array[minimum] = array[minimum], array[i]

    sorted_array = []
    for sub in sub_arrays:
        for word in sub:
            sorted_array.append(word)
    return sorted_array

def change_dir():
    """
    For changing working directory.
    My computer starts in wrong directory so I get error on readfile(), this allows me to read file in same folder as program.
    """
    abspath = os.path.abspath(__file__)
    dname = os.path.dirname(abspath)
    os.chdir(dname)

def main():
    """
    "strings" is a file which contains the string to be sorted, only used for testing. Change this to your filename.
    """
    change_dir()
    start = time.time()
    filename = "strings" # Your filename here
    unsorted_array = readfile(filename)
    sorted_array = sort(unsorted_array)            
    end = time.time()
    print(sorted_array)
    print(end-start)

if __name__ == '__main__':
    main()