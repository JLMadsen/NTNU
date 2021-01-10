import sys
import pygame
import math
import random
import numpy as np
import time
import gym

from sklearn.preprocessing import KBinsDiscretizer
from collections import defaultdict

env = gym.make('CartPole-v1')

n_bins = ( 6 , 12 )
lower_bounds = [ env.observation_space.low[2], -math.radians(50) ]
upper_bounds = [ env.observation_space.high[2], math.radians(50) ]

def discretizer( cart_position , cart_velocity , angle, pole_velocity ):
    est = KBinsDiscretizer(n_bins=n_bins, encode='ordinal', strategy='uniform')
    est.fit([lower_bounds, upper_bounds ])
    return tuple(map(int,est.transform([[angle, pole_velocity]])[0]))

def main():

    
    Q_table = defaultdict(lambda: [0]*2 )

    # variables
    alpha = 0.1
    gamma = 0.95
    epsilon = 1
    
    # stats
    scores = []
    
    # vizualisation
    show_last_n = 20
    #show_heatmap = True
    
    episodes = 4_000
    
    e_decay = (epsilon - .1) / episodes
    for e in range(episodes):
        epsilon -= e_decay
        
        if e % 100 == 0:
            print('Episode', e)
                        
        score = 0
        done = False
        state = discretizer(*env.reset())
        action = None
        
        while not done:
            score += 1
            
            if random.uniform(0, 1) < epsilon:
                action = env.action_space.sample()
            else:
                action = np.argmax(Q_table[state])
            
            if e > (episodes - show_last_n):
                env.render()
            
            next_state, reward, done, info = env.step(action)
            next_state = discretizer(*next_state)
            
            old_value = Q_table[state][action]
            next_max = np.max(Q_table[next_state])
            
            new_value = (1 - alpha) * old_value + alpha * (reward + gamma * next_max)
            Q_table[state][action] = new_value
            
            state = next_state
            
        scores.append(score)
                        
    print('avg score', sum(scores)/episodes, 'for', episodes, 'episodes')                   
        
    import matplotlib.pyplot as plt

    plt.plot(list(range(len(scores))), scores)
    plt.show()
        
if __name__ == '__main__':
    main()
