from objects import Objects
import math

"""
getObjectColor currently replaced by textures.
Gradient is for heatmap for q table.
"""

class Colors:
    def __init__(self):
        pass

    WHITE  = (255, 255, 255)
    BLACK  = (0,   0,   0  )
    GRAY   = (100, 100, 100)
    RED    = (220, 20,  60 )
    GREEN  = (50,  205, 50 )
    YELLOW = (255, 255, 0  )
    PURPLE = (218, 112, 214)

    ALL = [WHITE, BLACK, GRAY, RED, GREEN]

    def getObjectColor(self, obj):

        if obj == Objects.AIR:
            return self.WHITE

        elif obj == Objects.WALL:
            return self.GRAY

        elif obj == Objects.GOAL:
            return self.YELLOW

        elif obj == Objects.PLAYER:
            return self.RED
            
        else:
            return self.WHITE

    def gradient(self, minimum, maximum, value):
        
        percentage = (value+minimum) / (minimum+maximum) * 100
        
        r = 255 if percentage < 50 else math.floor(255-(percentage*2-100)*255/100)
        
        g = 255 if percentage > 50 else math.floor((percentage*2)*255/200)
        
        r = r if r > 0 else 0
        g = g if g > 0 else 0
        
        return (r, g, 0)

if __name__ == '__main__':
    from main import main
    main()