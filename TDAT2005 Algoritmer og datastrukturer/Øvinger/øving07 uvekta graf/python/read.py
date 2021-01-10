import re

from draw import draw

def read(filename) -> {}:
    file = open(filename)
    if not file:
        exit("File not found")
    text = file.readlines()
    file.close()
    
    graph = {}    
    nodeCount, edgeCount  = 0, 0
    
    header = True
    for line in text:
        if header:
            nodeCount, edgeCount = [int(s) for s in re.findall(r'\d+', line)]
            header = False
            continue
        
        n1, n2 = 0, 0
        try:
            n1, n2 = [int(s) for s in re.findall(r'\d+', line)]
        except:
            print("Casting error in file: " + filename + ", on line: \"", line, "\"")
            exit()
        
        # add node1 to graph with edge
        if n1 in graph.keys():
            graph[n1].append(n2)
        else:
            graph[n1] = [n2]
        
        # add node2 to graph without edge
        if n2 not in graph.keys():
            graph[n2] = []
            
    if len(graph.keys()) != nodeCount:
        #print("Not all nodes in list ", filename)
        #print("In graph = ", len(graph.keys()), "In file = ", nodeCount)
        
        # quick fix
        for i in range(nodeCount):
            if i not in graph.keys():
                graph[i] = []
    
    return graph  

def init(files, drawImage):
    # Read all graphs from file
    graphs = {}
    for f in files:
        print("Reading "+ f )
        graphs[f] = read(f)
        
    if drawImage:
        for name, graph in graphs.items():
            draw(graph, name)
        
    return graphs

import os
if __name__ == '__main__':
    abspath = os.path.abspath(__file__)
    dname = os.path.dirname(abspath)
    os.chdir(dname)
    os.system("Python UvektaGraf.py")