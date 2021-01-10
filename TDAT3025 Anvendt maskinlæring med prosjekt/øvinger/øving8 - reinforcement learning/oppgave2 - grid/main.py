# Game stuffs
from actions import Actions
from objects import Objects
from colors import Colors
from game import Game

# Other stuffs
import sys
import pygame
import math
import random
import numpy as np
import time
import matplotlib.pyplot as plt

from console_progressbar import ProgressBar

from collections import defaultdict

def main():

    env = Game()
    
    Q_table = defaultdict(lambda: [0]*4 )

    # variables
    alpha   = .1
    gamma   = .95
    epsilon = 1
    
    # stats
    finished = 0
    
    # vizualisation
    show_last_n  = 20
    show_heatmap = 1
    
    episodes = 5_000
    
    pb = ProgressBar(total=episodes)
    e_decay = (epsilon - .1) / episodes
    
    print('TRAINING:')
    for e in range(episodes):
        epsilon -= e_decay
        
        if e % 500 == 0:
            pb.print_progress_bar(e)
                        
        steps  = 100
        done   = False
        state  = env.reset()
        action = None
              
        draw = 1 if e > (episodes - show_last_n) else 0
        epsilon = 0 if draw else epsilon
        heatmap = Q_table if draw and show_heatmap else None
                
        while not done:
            
            steps -= 1
            if steps == 0:
                break
            
            if draw:
                time.sleep(.05)
            
            if random.uniform(0, 1) < epsilon:
                action = Actions.ALL[random.randint(0, 3)]
            else:
                action = np.argmax(Q_table[state])
            
            next_state, reward, done, info = env.step(action, draw, q=heatmap)
            
            old_value = Q_table[state][action]
            next_max = np.max(Q_table[next_state])
            
            new_value = (1 - alpha) * old_value + alpha * (reward + gamma * next_max)
            Q_table[state][action] = new_value
            
            state = next_state

        if done:
            finished += 1

    pb.print_progress_bar(episodes)

    #for key, value in Q_table.items():
    #    print(key, value)
                        
    print('\nCleared', finished, '/', episodes)                   

if __name__ == '__main__':
    main()
