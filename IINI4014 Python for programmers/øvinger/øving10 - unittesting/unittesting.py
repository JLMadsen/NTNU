import unittest
import sys
import re

# Uncomment below for testing only, comment out before upload
#from rle import RLEString

class Assignment8Tests(unittest.TestCase):
    
    def test_init(self):
        with self.assertRaises(Exception): RLEString("03214")
        with self.assertRaises(Exception): RLEString("")
        with self.assertRaises(Exception): RLEString(",.-_!*^?=/&%¤#")
        with self.assertRaises(Exception): RLEString(True)
        with self.assertRaises(Exception): RLEString(None)
        with self.assertRaises(Exception): RLEString()
        with self.assertRaises(Exception): RLEString(5)
        with self.assertRaises(Exception): RLEString(-420).__class__()

    def test_compress(self):
        obj1 = RLEString("aaaa")
        obj2 = RLEString("aaaabbbb")
        obj3 = RLEString("aAabBbcCc")

        obj1.compress()
        obj2.compress()
        obj3.compress()
        
        with self.assertRaises(Exception): obj1.compress()
        with self.assertRaises(Exception): obj2.compress()
        with self.assertRaises(Exception): obj3.compress()

        self.assertEqual(obj1.__str__(), "4a")
        self.assertEqual(obj2.__str__(), "4a4b")
        self.assertEqual(obj3.__str__(), "1a1A1a1b1B1b1c1C1c")

    def test_iscompressed(self):
        obj = RLEString("aaaa")
        
        self.assertFalse(obj.iscompressed())
        
        obj.compress()
        self.assertTrue(obj.iscompressed())
        
        obj.decompress()
        self.assertFalse(obj.iscompressed())
        
    def test_decompress(self):
        obj1 = RLEString("aaaa")
        obj2 = RLEString("aaaabbbb")
        obj3 = RLEString("aAabBbcCc")
        
        with self.assertRaises(Exception): obj1.decompress()
        with self.assertRaises(Exception): obj2.decompress()
        with self.assertRaises(Exception): obj3.decompress()

        obj1.compress()
        obj1.decompress()
        obj2.compress()
        obj2.decompress()
        obj3.compress()
        obj3.decompress()
        
        self.assertEqual(obj1.__str__(), "aaaa")
        self.assertEqual(obj2.__str__(), "aaaabbbb")
        self.assertEqual(obj3.__str__(), "aAabBbcCc")




#_________________________________________________ Class to be tested

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
    # Start the unit test
    unittest.main(verbosity=2)