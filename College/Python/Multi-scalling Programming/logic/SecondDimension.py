from models.Cell import Cell
import os
import random

class SecondDimension:
    def __init__(self, width=15, height=10, iterations=10, pattern='oscillator', periodical=True):
        self.width = width
        self.height = height
        self.iterations = iterations
        self.pattern = pattern
        self.neighbours_amount_to_survive = [2, 3]
        self.neighbours_amount_to_born = [3]
        self.game_array_current_state_2d = self.initialize_2d_array()
        self.game_array_previous_state_2d = self.initialize_2d_array()
        self.initial_states_2d = 'unchanged,glider,oscillator'
        self.patterns_array = self.initial_states_2d.split(",")
        self.periodical = periodical
        self.first_time = True
        self.set_pattern_in_array(pattern)  # default = empty
        self.pattern_width = 0
        self.pattern_height = 0

    def initialize_2d_array(self):
        tmp_array = []
        for row in range(self.height):
            row_array = []
            for column in range(self.width):
                row_array.append(Cell(False))
            tmp_array.append(row_array)

        return tmp_array

    def calculate_value_2d(self, index_row, index_column):
        neighbour_counter = 0;
        current_row = index_row - 1
        current_column = index_column - 1
        if self.periodical:
            for row_index in range(3):
                current_column = index_column - 1
                for column_index in range(3):
                    if current_row == index_row and current_column == index_column:
                        current_column += 1
                        continue
                    if self.game_array_previous_state_2d[current_row % len(self.game_array_previous_state_2d)][current_column % len(self.game_array_previous_state_2d[0])].is_alive == True:
                        neighbour_counter += 1
                    current_column += 1
                current_row += 1
        else:
            for row_index in range(3):
                current_column = index_column - 1
                for column_index in range(3):
                    if (current_column == index_column and current_row == index_row):
                        current_column +=1
                        continue
                    if current_column >= len(self.game_array_previous_state_2d[0]) or current_row >= len(self.game_array_previous_state_2d):
                        continue
                    if current_column <0:
                        current_column+=1
                        continue
                    if current_row <0:
                        continue
                    if self.game_array_previous_state_2d[current_row][current_column].is_alive == True:
                        neighbour_counter += 1
                    current_column += 1
                current_row += 1
        #print(str(neighbour_counter)+" row: "+str(index_row)+" column: "+str(index_column))
        if (self.game_array_previous_state_2d[index_row][index_column].is_alive and neighbour_counter in self.neighbours_amount_to_survive) or (neighbour_counter in self.neighbours_amount_to_born):
            return True
        else:
            return False

    def begin_the_game(self):
        for iteration in range(self.iterations):
            # print("iteration number ", iteration+1)
            self.next_iteration()

    def next_iteration(self):
        self.game_array_previous_state_2d = self.game_array_current_state_2d
        self.game_array_current_state_2d = self.initialize_2d_array()
        for row in range(len(self.game_array_previous_state_2d)):
            for column in range(len(self.game_array_previous_state_2d[row])):
                self.game_array_current_state_2d[row][column].is_alive = self.calculate_value_2d(row, column)
        return self.game_array_current_state_2d

    def return_current_array(self):
        return self.game_array_current_state_2d

    def return_previous_array(self):
        return self.game_array_previous_state_2d

    def return_pattern_width(self):
        return self.pattern_width

    def return_pattern_height(self):
        return self.pattern_height

    def set_iteration(self, iteration):
        self.iterations = iteration

    def set_width(self, width):
        self.width = width

    def set_height(self, height):
        self.height = height

    def return_height(self):
        return self.height

    def return_width(self):
        return self.width

    def return_iteration(self):
        return self.iterations

    def return_pattern(self):
        return self.pattern

    def set_current_array(self,array):
        self.game_array_current_state_2d = array

    def set_pattern_in_array(self, pattern):  # CHECK FOR WRONG ARRAY WRITE PROCESS
        self.game_array_current_state_2d = self.initialize_2d_array()
        suffix = ".txt"
        if self.first_time:
            os.chdir("patterns")
            self.first_time = False
        current_directory = os.getcwd()
        if pattern in self.patterns_array:
            file = open(current_directory + "\\" + pattern + suffix)
            lines = file.readlines()
            lines_array = str(lines).replace("\\n", "").replace(" ", "").replace("[", "").replace("'", "").replace("]]","").split("],")
            if len(lines_array) > self.height or len(lines_array[0].split(",")) > self.width:
                self.game_array_current_state_2d = self.initialize_2d_array()
            else:
                self.pattern_height = len(lines_array)
                self.pattern_width = len(lines_array[0].split(","))
                for row in range(len(lines_array)):
                    row_array = lines_array[row].split(",")
                    for column in range(len(row_array)):

                        if row_array[column] == "1":
                            self.game_array_current_state_2d[row][column].is_alive = True
                        else:
                            self.game_array_current_state_2d[row][column].is_alive = False
        elif pattern == 'default':
            self.game_array_current_state_2d = self.initialize_2d_array()
        elif pattern == 'random':
            self.pattern_height = self.height
            self.pattern_width = self.width
            for row in range(self.height):
                for column in range(self.width):
                    self.game_array_current_state_2d[row][column].is_alive = bool(random.getrandbits(1))
        elif pattern == 'manual':
            pass
        else:
            self.game_array_current_state_2d = self.initialize_2d_array()
        #self.print_current_array()

    def set_parameters(self, width, iterations, height, pattern):
        self.iterations = iterations

        if pattern != self.pattern or self.width != width or self.height != height:
            self.pattern = pattern
            self.height = height
            self.width = width

            self.game_array_current_state_2d = self.initialize_2d_array()
            self.set_pattern_in_array(pattern)

    def restart_grid(self):
        self.game_array_current_state_2d = self.initialize_2d_array()
        self.set_pattern_in_array(self.pattern)

    def return_parameters(self):
        return repr(self.height) + " " + repr(self.width) + " " + repr(self.iterations) + " " + repr(
            self.pattern) + " " + repr(self.game_array_previous_state_2d)

    def print_current_array(self):
        for row in range(len(self.game_array_current_state_2d)):
            for column in range(len(self.game_array_current_state_2d[row])):
                print(self.game_array_current_state_2d[row][column].is_alive, end=' ')

            print("\n")

    def set_current_array(self,array):
        self.game_array_current_state_2d = array

    def return_initial_array(self):
        return self.initialize_2d_array()
