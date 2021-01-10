# Game stuffs
from actions import Actions
from objects import Objects
from colors import Colors
from maze import maze

# Other stuffs
import sys
import pygame
import math
import random
import numpy as np
import copy
from pathlib import Path
mod_path = Path(__file__).parent
from zlib import crc32

def bytes_to_float(b):
    return float(crc32(b) & 0xffffffff) / 2**32

def str_to_float(s, encoding="utf-8"):
    return bytes_to_float(s.encode(encoding))

class Game:
    def __init__(self, mode=1):
        # size = cell size and grid length, both x and y
        self.size = 30
        
        self.WINDOW_WIDTH = self.WINDOW_HEIGHT = self.size*self.size + self.size

        pygame.init()
        pygame.display.set_caption('My Favourite Fish')
        
        self.screen = pygame.display.set_mode((self.WINDOW_HEIGHT, self.WINDOW_WIDTH))
        self.CLOCK = pygame.time.Clock()
        self.screen.fill(Colors.WHITE)
               
        # 0 = empty map, 1 = radom maze
        self.maps = [
            [[0 for i in range(self.size)] for j in range(self.size)],
             maze(self.size, self.size)]
        
        self.pos = None
        self.state = self.maps[mode]
                
        self.loadTextures()
        self.reset()
        
    def loadTextures(self):
        self.img_fish = pygame.image.load(str(mod_path) + '/sprites/fish2.png')
        self.img_fish = pygame.transform.scale(self.img_fish, (self.size, self.size))
        
        self.img_player = pygame.image.load(str(mod_path) + '/sprites/player.png')
        self.img_player = pygame.transform.scale(self.img_player, (self.size, self.size))

        self.img_wall = pygame.image.load(str(mod_path) + '/sprites/wall3.png')
        self.img_wall = pygame.transform.scale(self.img_wall, (self.size, self.size))

        self.img_wall2 = pygame.image.load(str(mod_path) + '/sprites/wall2.png')
        self.img_wall2 = pygame.transform.scale(self.img_wall2, (self.size, self.size))
        
        self.img_floor = pygame.image.load(str(mod_path) + '/sprites/floor.png')
        self.img_floor = pygame.transform.scale(self.img_floor, (self.size, self.size))

    def get(self, y, x):
        return self.state[y][x]

    # return as tuple, list is not hashable
    def index(self):
        return tuple(self.pos)

    def reset(self):
        
        if self.pos is not None:
            y, x = self.pos
            self.state[y][x] = Objects.AIR
        
        self.state[1][1] = Objects.GOAL
        self.goal = [1, 1] # keep track of goal pos
        
        placed = False
        while not placed:
            y, x = random.randint(0, self.size-1), random.randint(0, self.size-1)
            if self.get(y, x) == Objects.AIR:
                self.pos = [y, x]
                self.state[y][x] = Objects.PLAYER
                placed = True
        
        return self.index()
    
    def move(self, nxt, draw=0):
        y2, x2 = nxt
        
        try:
            nextObject = self.get(y2, x2)
        except:
            return False, -1

        if nextObject == Objects.AIR:

            self.state[y2][x2] = Objects.PLAYER
            y1, x1 = self.pos
            self.state[y1][x1] = Objects.AIR
            self.pos = nxt
            
            return False, 0

        elif nextObject == Objects.GOAL:

            return True, 1
        
        return False, -1

    def step(self, a, draw=False, q=None):

        y, x = self.pos
        done = False
        next_pos = None

        if a == Actions.UP:
            next_pos = [y, x-1]
            
        elif a == Actions.DOWN:
            next_pos = [y, x+1]
            
        elif a == Actions.LEFT:
            next_pos = [y-1, x]
            
        elif a == Actions.RIGHT:
            next_pos = [y+1, x]
        
        done, reward = self.move(next_pos, (draw and q is None))

        if done:
            return self.index(), reward, done, {}

        if draw:
    
            # draw heatmap if q is passed as param
            if q is not None:
                lower = min( map(lambda x: sum(x), q.values()) )
                upper = max( map(lambda x: sum(x), q.values()) )
                                
                for key, value in q.items():
                    value = sum(value)
                                        
                    color = Colors.gradient(Colors, lower, upper, value)
                    
                    rect = pygame.Rect(key[0]*self.size, 
                                       key[1]*self.size, 
                                       self.size, 
                                       self.size)
                    
                    pygame.draw.rect(self.screen, color, rect, 0)
                    
            for i, row in enumerate(self.state):
                for j, obj in enumerate(row):
                                                       
                    conf = (i*self.size, j*self.size, self.size, self.size)
                    
                    if obj == Objects.AIR and q is None:
                        self.screen.blit(self.img_floor, conf )
                    
                    if obj == Objects.GOAL:
                        self.screen.blit(self.img_fish, conf )
                    
                    if obj == Objects.PLAYER:
                        self.screen.blit(self.img_player, conf )
            
                    if obj == Objects.WALL:
                        #if str_to_float(str(i+j)) < .5:
                        #    self.screen.blit(self.img_wall2, conf )
                        #else:
                        #    self.screen.blit(self.img_wall, conf )

                        self.screen.blit(self.img_wall, conf )
            
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    pygame.quit()
                    sys.exit()

            pygame.display.update()
         
        return self.index(), reward, done, {}
    
if __name__ == '__main__':
    from main import main
    main()