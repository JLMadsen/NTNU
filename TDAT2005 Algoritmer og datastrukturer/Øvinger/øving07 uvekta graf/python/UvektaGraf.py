import os
import re
import random

from BFS import BFS
from Topological import topological
from read import init

def show(graph, distances, predecessors):
        print("\nGraph:")
        print(graph)
        
        print("Node, pred, dist")
        spacer = "   "
        for node in graph:
            print(node, spacer, predecessors[node], spacer, distances[node])

def main() -> None:
    #files = ["L7g1.txt", "L7g2.txt", "L7g3.txt", "L7g5.txt", "L7Skandinavia.txt", "L7Skandinavia-navn.txt"]
    files = ["L7g1.txt", "L7g2.txt", "L7g3.txt", "L7g5.txt"]
    #files = ["L7g1.txt", "L7g5.txt"]

    drawImage = False
    graphs = init(files, drawImage)
    
    print("\nTask1:") #___________________________________________________________________ Task 1
    
    graph = graphs["L7g1.txt"]
    startingNode = 5
    distances, predecessors = BFS(startingNode, graph)
    
    show(graph, distances, predecessors)
    
    print("\nTask2:") #___________________________________________________________________ Task 2
    
    for name, graph in graphs.items():
        print("\nSorting:", name)
        result = topological(graph)

        if result:
            print(result)
        
    print("\nProgram done.") #____________________________________________________________ Finished
    exit()

if __name__ == '__main__':
    abspath = os.path.abspath(__file__)
    dname = os.path.dirname(abspath)
    os.chdir(dname)
    main()