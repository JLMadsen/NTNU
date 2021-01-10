from sklearn.preprocessing import KBinsDiscretizer
import numpy as np 
import time, math, random
import gym

from qLearn import Agent

env = gym.make('CartPole-v1')

n_bins = ( 6 , 12 )
lower_bounds = [ env.observation_space.low[2], -math.radians(50) ]
upper_bounds = [ env.observation_space.high[2], math.radians(50) ]

def discretizer( cart_position , cart_velocity , angle, pole_velocity ):
    est = KBinsDiscretizer(n_bins=n_bins, encode='ordinal', strategy='uniform')
    est.fit([lower_bounds, upper_bounds ])
    return tuple(map(int,est.transform([[angle, pole_velocity]])[0]))

q = Agent(2, 2)

scores = []
episodes = 10000
for e in range(episodes):
        
    current_state, done = discretizer(*env.reset()), False
    score = 0
    q.new_episode(e)

    while not done:

        score += 1
        
        action = q.policy(current_state, e)
         
        obs, reward, done, _ = env.step(action)
        new_state = discretizer(*obs)
        
        q.update_Q(current_state, new_state, action, score)
                
        current_state = new_state
        
        if e > episodes-10: 
            env.render()
        #env.render()
    
    if e % 100 == 0: 
        print(e)

    scores.append(score)

print('avg score for', episodes, 'episodes', sum(scores)/episodes)

import matplotlib.pyplot as plt

plt.plot(list(range(len(scores))), scores)
plt.show()

