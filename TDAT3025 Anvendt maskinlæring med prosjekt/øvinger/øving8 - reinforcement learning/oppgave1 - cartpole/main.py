from sklearn.preprocessing import KBinsDiscretizer
import numpy as np 
import time, math, random
import gym

env = gym.make('CartPole-v1')

n_bins = ( 6 , 12 )
lower_bounds = [ env.observation_space.low[2], -math.radians(50) ]
upper_bounds = [ env.observation_space.high[2], math.radians(50) ]

def discretizer( cart_position , cart_velocity , angle, pole_velocity ):

    est = KBinsDiscretizer(n_bins=n_bins, encode='ordinal', strategy='uniform')

    est.fit([lower_bounds, upper_bounds ])

    return tuple(map(int,est.transform([[angle, pole_velocity]])[0]))

Q_table = np.zeros(n_bins + (env.action_space.n,))
Q_table.shape

def new_Q_value( reward,  new_state, discount_factor=1 ):

    future_optimal_value = np.max(Q_table[new_state])

    learned_value = reward + discount_factor * future_optimal_value

    return learned_value

def learning_rate(n, min_rate=0.01 ):
    return max(min_rate, min(1.0, 1.0 - math.log10((n + 1) / 25)))

def exploration_rate(n, min_rate= 0.1 ):
    return max(min_rate, min(1, 1.0 - math.log10((n  + 1) / 25)))

scores = []
n_episodes = 1_000
for e in range(n_episodes):

    current_state, done = discretizer(*env.reset()), False
    score = 0

    while done==False:

        score += 1

        action = np.argmax(Q_table[state])

        if np.random.random() < exploration_rate(e):
            action = env.action_space.sample()

        obs, reward, done, _ = env.step(action)

        new_state = discretizer(*obs)

        lr = learning_rate(e)

        learnt_value = new_Q_value(reward , new_state )

        old_value = Q_table[current_state][action]

        Q_table[current_state][action] = (1-lr)*old_value + lr*learnt_value

        current_state = new_state

        # Render
        if e % 100 == 0: 
            env.render()

    if e % 100 == 0: 
        print(e)

    #print(score)
    scores.append(score)

import matplotlib.pyplot as plt

plt.plot(list(range(len(scores))), scores)
plt.show()