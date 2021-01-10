    '''
Task 1
'''
class KVStore:
    def __init__(self):
        self.key = []
        self.value = []
        self.iter_value = 0

    def insert(self, key, value):
        self.key.append(key)
        self.value.append(value)

    def get(self, nkey):
        for key, value in zip(self.key, self.value):
            if nkey == key:
                return key, value

    def set(self, nkey, value):
        for i in range(len(key)):
            if nkey == self.key[i]:
                self.value[i] = value

    def delete(self, nkey):
        for i in range(len(key)):
            if nkey == self.key[i]:
                self.key.pop(i)
                self.value.pop(i)

    def __iter__(self):
        return zip(self.key, self.value)

db = KVStore()
db.insert("name", "Rick Astley")
obj = object()
db.insert("object", obj)

for key, value in db:
    print(key, ":", value)

'''
Task 2
'''
def matrix_function(a, b):
    x = [[]]

'''
Task 3
'''

def string_function(string, vector):
    vector = str(vector)
    while len(vector) < len(string):
        vector += vector

    string.lower()
    output = ""
    for letter, value in zip(string, vector):
        if value == '1':
            output += letter.upper()
        else:
            output += letter
    return output

print(string_function("Python", "010"))