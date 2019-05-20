import numpy as np

class Cell:
    is_alive = False
    id = 0
    colours = [255, 255, 255]
    def __init__(self, input_alive = False, colours = [255,255,255]):
        self.is_alive = input_alive
        self.colours = colours

    def __repr__(self):
        #return repr(self.__id) + " " + repr(self.is_alive) + " "
        return repr(self.is_alive) + " "

    def return_state(self):
        return self.is_alive

    def set_id(self,input_id):
        self.id = input_id

    def set_state(self, alive_state):
        self.is_alive = alive_state

    def set_colours_array(self, colours_array):
        self.colours = colours_array

    def return_colours_array(self):
        return self.colours

    def set_colour(self,index,value):
        self.colours[index]=value

    def set_red(self,value):
        self.colours[0] = value

    def set_green(self,value):
        self.colours[1] = value

    def set_blue(self,value):
        self.colours[2] = value

    def return_id(self):
        return self.id
