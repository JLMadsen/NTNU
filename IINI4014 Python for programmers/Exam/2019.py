import sys

'''
IINI 4014 Python exam 2019
This program is a bit more hardcoded than i would like but this works for all the given tests except for one.
Because of this i struggled with odd.bin.hex as the last line is 125, mine only gives 110 and thinks its done..
I tried increasing with hex functions but i struggled with formatting, so i went with strings.
The expanded hexdumps are returned as a list.
'''

# This list is used as an reference for address_translator()
hex_values = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f']

def hexdump_expander(dump):
    '''
    This method takes in a dump list.
    I then increments thru the list, if an element only is * it will fetch the previous and next address.
    Then it will calculate the distance between these addresses.
    After this it will create n amount of addresses with the data from the previous data entry.
    '''
    lines = dump
    previous_address = ""
    previous_data = ""
    output = []

    for j in range(len(lines)):
        if '*' in lines[j]:
            
            next_address = lines[j+1].split(" ")[0]
            length = address_translator(previous_address, next_address)
            prev1, prev2 = str(previous_address[4]), str(previous_address[5])

            # For loop that adds n amount of entries based on the length between addresses.
            for p in range(1, length):
                new_address = ""

                if prev2 == 'f':
                    if prev1 == 'f':
                        new_address += '0'
                    else:
                        # This increments the value by 1, it does this by fetching the index for the previous hex number and adding 1.
                        new_address += hex_values[(hex_values.index(prev1)+1)]
                    new_address += '0'
                else:
                    new_address += prev1 +""+ hex_values[(hex_values.index(prev2)+1)]

                output.append("0000"+ new_address +"0 "+ previous_data)
                prev1, prev2 = new_address[0], new_address[1]

        else:
            output.append(lines[j])
            segments = lines[j].split(" ")
            previous_address = segments[0]
            previous_data = ""
            for i in range(1, len(segments)):
                previous_data += segments[i] +" "

    return output

def address_translator(address1, address2):
    '''
    Based on the hex location in the reference list i can calculate the length bewteen the adresses.
    For this i use the reference list created above and make good use of the index function.
    '''
    values = len(hex_values)
    start = str(hex_values.index(address1[4])), str(hex_values.index(address1[5]))
    end = str(hex_values.index(address2[4])), str(hex_values.index(address2[5]))
    length = 0

    if start[0] == end[0]:
        length = int(end[1]) - int(start[1])
        return length
    else:
        # When the first number does not equal it needs to go a full loop.
        length = 16*(int(end[0])-int(start[0]))

        if int(start[1]) > int(end[1]):
            length += int(end[1])-int(start[1])
        else:
            length += int(start[1])-int(end[1])
        return length

def read_file():
    '''
    Reads the file and removes the newline and creates a list.
    '''
    filename = sys.argv[1]
    file_input = open(filename, 'r')
    hexdump = file_input.readlines()
    file_input.close()
    new_dump = []
    for dump in hexdump:
        new_dump.append(dump.replace("\n", ""))
    return new_dump

def size(dump):
    '''
    For determining the byte size.
    '''
    line = dump[0].replace(" ", "")
    length = len(line)
    length -= 7
    byte = length/2*4
    return byte

def main():
    dump = read_file()
    result = hexdump_expander(dump)
    
    for thing in result:
        print(thing)

if __name__ == '__main__':
    main()
    