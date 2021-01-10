
def classifyLetters(letters):
    return [[0. if j!=i else 1. for j in range(len(letters))] for i, c in enumerate(letters)]


