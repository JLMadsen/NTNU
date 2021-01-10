
import numpy as np
import math
import random
import sys

from collections import defaultdict

class Agent:
    
    def __init__(self, actions, states):
        self.Q = defaultdict(lambda: [0 for _ in range(actions)])
        
        self.alpha = 0.1
        self.gamma = 0.9
        self.epsilon = 1
        
        self.actions = actions
        
    def policy(self, state, e):
        #print(self.Q)
        
        if np.random.random() < self.exploration_rate(e): 
            return random.randint(0, self.actions-1)
        else:
            return np.argmax(self.Q[state])
        

    def update_Q(self, state, next_state, action, reward):
                
        old_value = self.Q[state][action]
        next_max = np.max(self.Q[next_state])
        
        new_value = (1 - self.alpha) * old_value + self.alpha * (reward + self.gamma * next_max)
        self.Q[state][action] = new_value
        
    def new_episode(self, e):
        self.alpha = self.learning_rate(e)
        self.epsilon = self.exploration_rate(e)
    
    def learning_rate(self, n, min_rate=0.01 ):
        return max(min_rate, min(1.0, 1.0 - math.log10((n + 1) / 25)))

    def exploration_rate(self, n, min_rate= 0.1 ):
        return max(min_rate, min(1, 1.0 - math.log10((n  + 1) / 25)))
    
    
    
    
    