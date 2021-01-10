import re

'''
My method for solving:
1. Init method takes string, and sets compressed boolean to false, i assume this because the exception raises if the string contains numbers.
2. Compress method checks if already compressed.
3. Compress goes thru each letter of string, saving the previous one, if next is same, add 1 to number, else add number and letter to output.
4. Decompress splits string into array of "couples" which are the number of letters, and the letter. this then adds that many letters to output.
5. Iscompressed() just checks if string contains number, if it does it sets the flag to true, meaning it has been compressed.
'''


class RLEString:
    def __init__(self, object_string):
        self.object_string =  object_string
        self.__iscompressed = False # i assume false because init raises exception if there are numbers.
        if ((re.search('[^a-zA-Z]',  object_string)) or object_string == ""):
            raise Exception("String contains illegal characters")

    def compress(self):
        if(self.__iscompressed):
            raise Exception("Already compressed:"+ self.object_string)
            return
        previous_letter = ""
        number = 0
        output_string = ""
        for letter in self.object_string:
            if(previous_letter == letter):
                number += 1
            else:
                if(number != 0):
                    output_string += str(number) + previous_letter
                previous_letter = letter
                number = 1

        output_string += str(number) + previous_letter # when i reach the end it stops, so it wont add the last letter, which is why i put this here :)
        self.object_string = output_string
        self.__iscompressed = True

    def recompress(self):
        if(self.__iscompressed):
            raise Exception("Already compressed:"+ self.object_string)
            return
        output = ""
        regex_array = re.findall(r'(\w)\1*' ,self.object_string)
            
        for group in regex_array:
            output += str(len(group)) + group[0]
        self.object_string = output
        self.__iscompressed = True
        
    def decompress(self):
        if(self.__iscompressed == False):
            raise Exception("Already decompressed:"+ self.object_string)
            return
        output_string = ""
        # couple refers to a set of numbers and a letter
        for couple in re.findall('\d+[a-zA-Z]', self.object_string):
            number = re.findall('\d+', couple)[0]
            letter = re.findall('[a-zA-Z]', couple)[0]
            for i in range(0, int(number)):
                output_string += letter
        self.object_string = output_string
        self.__iscompressed = False

    def iscompressed(self):
        return self.__iscompressed

    def __str__(self):
        return self.object_string   
    
if __name__ == '__main__':
    idk = RLEString('kkkkkfffffffjjjjjjjjjeeeeeeeeelllllllllleeeeeeeeeeeerrrrrrrrrrrr')

    print(idk.__str__())
    idk.compress()
    print(idk.iscompressed())
    print(idk.__str__())
    idk.decompress()
    print(idk.iscompressed())
    print(idk.__str__())
