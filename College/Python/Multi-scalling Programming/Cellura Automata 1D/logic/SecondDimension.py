from models.Cell import Cell
import numpy as np

class SecondDimension:
    def __init__(self, width=100, height=100, iterations=100, rule=90):
        self.width = width
        self.iterations = iterations
        self.rule = rule
        self.game_array = self.initialize_array(width,height)
        self.game_array_previous_state = self.initialize_array(width,height)

    def initialize_array(self,width,height):
        tmp_array = []
        for row in range(height):
            obj_array =[]
            for column in range(width):
                obj_array.append(Cell(column+row*100+1,False))
            tmp_array.append(obj_array)
        return tmp_array


    def print_array(self,width=100,height=100):
        print("2D array")
        for row in range(height):
            for column in range(width):
                print(self.game_array[row][column], end = '')
            print("\n")


