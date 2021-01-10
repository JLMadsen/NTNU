def BFS(source, graph, distances = {}, predecessors = {}, visited = []):
    
    # nodes to visit, or start from in this case
    queue = [source]
    
    # keep track of where we have been
    visited.append(source)
    distances[source] = 0
    predecessors[source] = " "
    
    while queue:
        source = queue.pop(0)
        
        for neighbor in graph[source]:
            if neighbor not in visited:
                
                predecessors[neighbor] = source
                distances[neighbor] = distances[source] + 1
                
                queue.append(neighbor)
                visited.append(neighbor)
        
    return distances, predecessors
        
import os
if __name__ == '__main__':
    abspath = os.path.abspath(__file__)
    dname = os.path.dirname(abspath)
    os.chdir(dname)
    os.system("Python UvektaGraf.py")