import cv2, time, pydot, sys

import networkx as nx
from networkx.drawing.nx_pydot import write_dot

import matplotlib.pyplot as plt
import gc

G = nx.DiGraph()

def saveImage(name):
    edges = G.edges()

    nx.draw(G, edges=edges, with_labels=True)

    # save to root dir as png
    plt.savefig("Graph" + name + ".png")
    
    # clear image to prevent overlap
    plt.clf()
    
def drawImage(name):
    # read image and show
    img = cv2.imread("Graph" + name + ".png", 1)
    cv2.imshow('image', img)
    cv2.waitKey(1)
    
def draw(graph, name = ""):
    G.clear()
    G.add_nodes_from(graph.keys())
        
    # add edges, 180 total
    for node1, edges in graph.items():
        for node2 in edges:
            G.add_edge(node1, node2)
        
    saveImage(name)
    drawImage(name)
                
    # pause to see image
    time.sleep(4) 
    gc.collect()
    cv2.destroyAllWindows()
    
import os
if __name__ == '__main__':
    abspath = os.path.abspath(__file__)
    dname = os.path.dirname(abspath)
    os.chdir(dname)
    os.system("Python UvektaGraf.py")