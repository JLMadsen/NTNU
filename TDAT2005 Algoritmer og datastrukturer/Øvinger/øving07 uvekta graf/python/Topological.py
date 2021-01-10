def topological(graph) -> []:
    # only works for Directed Aclyclic Graphs (DAG)
    # follows algorithm described at:
    # https://no.wikipedia.org/wiki/Topologisk_sortering

    graph = graph.copy()
    degree = {}
    result = []
    queue = []

    # degree of incoming edges
    for node, edges in graph.items():
        for edge in edges:
            if edge not in degree.keys():
                degree[edge] = 0
            degree[edge] += 1
    
    # Adding nodes that are "independent"
    for node in graph.keys():
        if node not in degree.keys() or degree[node] == 0:
            if not queue:
                queue = [node]
            else:
                queue.append(node)
    
    # if queue is not initialized the graph has no edges with degree 0
    # this means there are no independant ndoes
    if not queue:
        print("Graph has no starting nodes which qualify and cannot be sorted.")
        print("Graph may be undirected and/or cyclic.")
        return
    
    #print("queue: ", queue) # epic debugging
                
    while queue:
        # Chosing a random node from queue
        node = queue[0]
        queue.pop(0)
        result.append(node)
        
        # decrease degree of neighbor nodes        
        for neighbor in graph[node]:
            degree[neighbor] -= 1
            
            # if degree less than zero means graph is cyclic
            if degree[neighbor] < 0:
                print(neighbor, "has degree", degree[neighbor])
                print("Graph is cyclic and cannot be sorted.")
                return
            
            # if new degree is 0 this node is "independent" and can be added to queue
            if degree[neighbor] == 0:
                queue.append(neighbor)
        
        #print(result)

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