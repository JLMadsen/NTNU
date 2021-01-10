def topological(graph) -> []:
    # only works for Directed Aclyclic Graphs (DAG)
    # follows algorithm described at:
    # https://no.wikipedia.org/wiki/Topologisk_sortering

    graph = graph.copy()
    degree = {}
    result = []
    start = graph.keys()
    
    # degree of incoming edges
    for edges in graph.values():
        for edge in edges:
            if edge not in degree.keys():
                degree[edge] = 0
            degree[edge] += 1
            if edge in start:
                start.remove(edge)
    
    # if start is not initialized the graph has no edges with degree 0
    # this means there are no independant ndoes
    if not start:
        print("Graph has no starting nodes which qualify and cannot be sorted.")
        print("Graph may be undirected and/or cyclic.")
        return
                        
    while start:
        # Chosing a random node from start
        node = start.pop(0)
        result.append(node)
        
        # decrease degree of neighbor nodes        
        for neighbor in graph[node]:
            degree[neighbor] -= 1
            
            # if degree less than zero means graph is cyclic
            if degree[neighbor] < 0:
                print(neighbor, "has degree", degree[neighbor])
                print("Graph is cyclic and cannot be sorted.")
                return
            
            # if new degree is 0 this node is "independent" and can be added to start
            if degree[neighbor] == 0:
                start.append(neighbor)
        
    if len(result) != len(graph.keys()):
        print("Result is not equal to number of nodes.")
        print("Result length = ", len(result), "Graph length = ", len(graph))
        return

    return result

import os
if __name__ == '__main__':
    abspath = os.path.abspath(__file__)
    dname = os.path.dirname(abspath)
    os.chdir(dname)
    os.system("Python UvektaGraf.py")