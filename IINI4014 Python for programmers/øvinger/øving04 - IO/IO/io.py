import os, re
from collections import Counter

def getfilelist(pathname):
    results = []
    for f in os.listdir(pathname):
        for j in os.listdir(pathname +"/"+ str(f)):
            if j.endswith('.txt'): results.append(pathname +"/"+ str(f) +"/"+ j)
    return sorted(results)

def getwordfreqs(filename):
    idontknow = open(filename, "r", encoding="utf8")
    text = idontknow.read().lower()
    idontknow.close()
    return Counter(re.findall(r'[0-9a-z]+' , text))

def getcommonwords(dicts):
    words = []
    for on in dicts:
        words.append([on.most_common(10)[i][0] for i in range(0,10)])
    wordSet = set(words[0])
    for word in words:
        wordSet = wordSet.intersection(word)
    return list(his)
# ______________________________________ "Main"

dicts = []
for f in getfilelist('books'):
    dicts.append(getwordfreqs(f))

print("* Most common words:")
for w in getcommonwords(dicts):
   print(w)

