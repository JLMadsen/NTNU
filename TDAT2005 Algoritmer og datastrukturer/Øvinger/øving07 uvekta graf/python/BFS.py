def BFS(source, graph, predecessors = {}, distances = {}, visited = []) -> [{},{}]:
    
    # check exit condition
    if(len(visited) == len(graph.keys())):
        print("BFS done.")
        return distances, predecessors
    
    # if not finished continue
    else:
        # initialize distances
        if not distances:
            distances[source] = 0
            predecessors[source] = " "
        
        # check all neighbors
        for neighbor in graph[source]:
            if neighbor not in visited:
                #print(distances)
                new_distance = 0
                try:
                    new_distance = distances[source] + 1
                except:
                    exit("KeyError, source = " + str(source))
                
                # check if new distance is shorter than previous, if no distance availible use infinite
                if new_distance < distances.get(neighbor, float('inf')):
                    distances[neighbor] = new_distance
                    predecessors[neighbor] = source
        
        visited.append(source)
        
        # map unvisited nodes
        unvisited = []
        for node in graph:
            if node not in visited:
                unvisited.append(node)
        
        # if visited all nodes, return result
        if not unvisited:
            return distances, predecessors
        
        # get next source
        queue = graph[source]
        for node in queue:
            if node in unvisited:
                return BFS(node, graph, predecessors, distances, visited)
        
        exit("No avalible path, source = " + str(source))    
        
import os
if __name__ == '__main__':
    abspath = os.path.abspath(__file__)
    dname = os.path.dirname(abspath)
    os.chdir(dname)
    os.system("Python UvektaGraf.py")