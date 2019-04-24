import numpy as np

class Cell:
    is_alive = False

    def __init__(self, input_id, input_alive = False):
        self.__id = input_id
        self.__isAlive = input_alive

    def __repr__(self):
        #return repr(self.__id) + " " + repr(self.is_alive) + " "
        return repr(self.is_alive) + " "

    def return_state(self):
        return self.is_alive

    def set_state(self, alive_state):
        self.is_alive = alive_state

    def return_id(self):
        return self.id
