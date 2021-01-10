import os

'''
Some notes:

Made a change_dir because my computer got the wrong working directory.
I Struggle with naming variables, but i try to name them in a way that makes sense.
I tried making 'try' things so my program wouldnt crash as often.

My method for solving this assignment:

1. The input method gets a list with filenames, this list goes into filtetype_finder.
2. Filetype_finder returns a list with the same indexes, this method also prints the results for each file.
3. This list goes into a folder_maker which creates the necessary folders for the image files.
4. The list also goes with the filenames into a file_sorter which moves them in to their respective folder based on the filetype.
'''

# Creating a reference dictionary
MAGIC_BYTES = {'JPEG': "FF D8 FF E0", 
               'PNG': "89 50 4E 47 0D 0A 1A 0A",
               'TIFF': "49 49 2A 00",
               'GIF89a': "47 49 46 38 39 61"}

# Changing working dir to same dir as file.
def change_dir():
    abspath = os.path.abspath(__file__)
    dname = os.path.dirname(abspath)
    os.chdir(dname)

def filetype_finder(filenames):
    try:
        results = []
        for filename in filenames:

            # if filename does not contain '.', STOP, hammer time
            if('.' not in filename):
                break
            input_file = open(filename, 'rb') # Open image file with read + byte format
            input_bytes = input_file.read(16) # read the 16 first bytes, dont need more
            input_hex = " ".join(['{:02x}'.format(byte) for byte in input_bytes]) # byte to hex
            hex_array = input_hex.split(" ") # split into array for easy comparing

            for key, value in MAGIC_BYTES.items():
                matches = 0 # need to match all the numbers from reference
                for image_hex, magic_hex in zip(hex_array, value.split(" ")):
                    if(image_hex.lower() == magic_hex.lower()):
                        matches += 1 # found a match
                    else:
                        break
                # if all the hex in read match all the hexes in reference its a match
                if(matches == len(value.split(" "))):
                    print("File ", filename ," is of type ", key)
                    results.append(key)
                    break
        return results
    except:
        print("Error in filetype_finder.")
        return None

# Moves files into respective folders based on filetypr
def file_sorter(filenames, filetypes):
    try:
        for filename, filetype in zip(filenames, filetypes):
            os.rename(filename, filetype +"/"+ filename)
    except:
        print("Error in file_sorter")

# Creates a folder for each filetype that is found
def folder_maker(filetypes):
    try:
        listdir = os.listdir()
        for filetype in filetypes:
            if(filetype not in listdir):
                os.mkdir(filetype)
    except:
        print("Error in folder_maker")

def main():
    change_dir()

    # input methods
    filenames = input().split(" ")
    #filenames = ["image.png", "image.jpg", "image.gif", "image.tif"]
    
    filetypes = filetype_finder(filenames)

    folder_maker(filetypes)
    file_sorter(filenames, filetypes)

if (__name__ == '__main__'):
    main()