from models.Cell import Cell
import random
import numpy
import math
import copy


class Nucleation:

    def __init__(self, width=50, height=50, iterations=10, pattern='random', periodical=False,
                 neighbours="PentagonalUp",
                 seeds_amount=15, width_amount=7, height_amount=8, radius=3, neighbour_radius=4):
        self.width = width
        self.height = height
        self.iterations = iterations
        self.pattern = pattern
        self.game_array_initial_state_2d = self.initialize_2d_array()
        self.game_array_current_state_2d = copy.deepcopy(self.game_array_initial_state_2d)
        self.game_array_previous_state_2d = copy.deepcopy(self.game_array_initial_state_2d)
        self.initial_states_2d = 'homogeneous,radius,random,manual'
        self.patterns_array = self.initial_states_2d.split(",")
        self.neighbours_states = 'Neumann,Pentagonal,Hexagonal,PentagonalLeft,PentagonalRight,PentagonalDown,PentagonalUp,Moore,Radius,HexagonalR,HexagonalL'
        self.pattern_offsets = {
            'Neumann': [[-1, 0], [0, -1], [1, 0], [0, 1]], #row, column
            'PentagonalUp': [[-1, 1], [-1, 0], [-1, -1],[0, -1], [0, 1]],
            'PentagonalDown': [[1, -1], [1, 0], [1, 1], [0, -1], [0, 1]],
            'PentagonalRight': [[-1, 1], [0, 1], [1, 1], [-1, 0], [1, 0]],
            'PentagonalLeft': [[-1, -1], [0, -1], [1, -1], [-1, 0], [1, 0]],
            'HexagonalL': [[-1, 0], [-1, 1], [0, -1], [0, 1],[1, -1], [1, 0]],
            'HexagonalR': [[-1, 0], [-1, -1], [0, -1], [0, 1],[1, 1], [1, 0]],
            'Moore': [[-1, 0], [-1, 1], [0, -1], [0, 1],[1, -1], [1, 0],[-1,-1],[1,1]],
        }
        self.neighbours_array = self.neighbours_states.split(",")
        self.pentagonal_array_states = ["PentagonalLeft", "PentagonalRight", "PentagonalDown", "PentagonalUp"]
        self.hexagonal_array_states = ["HexagonalR", "HexagonalL"]
        self.nucleation_neighbour = neighbours
        self.colors_dictionary = {0: [255, 255, 255]}
        self.radius_dictionary = {}
        self.local_index_dictionary_radius_neighbour = {}
        self.local_colours_dictionary_radius = {}
        self.seeds_amount = seeds_amount  # for random mode
        self.width_amount = width_amount  # for homogeneous mode
        self.height_amount = height_amount  # for homogeneous mode
        self.radius = radius
        self.neighbour_radius = neighbour_radius
        self.last_iteration = False
        self.periodical = periodical
        if periodical == "periodical" or periodical == True:
            self.periodical = True
        else:
            self.periodical = False

        self.height_disbalance = False
        self.width_disbalance = False

        self.zeros = self.height * self.width
        self.set_pattern_in_array(self.pattern)
        self.set_neighbour(neighbours)

    def set_periodical(self,periodical):
        self.periodical = periodical

    def return_periodical(self):
        return self.periodical

    def decrease_zeros(self):
        self.zeros -= 1
        #print(self.zeros)

    def compare_coordinates_with_pattern(self, current_row, current_column, index_row, index_column):
        if self.nucleation_neighbour == "Neumann":  # mozna zredukowac offsety
            if self.periodical:
                if current_row == index_row and current_column == index_column or \
                        current_row == index_row - 1 and current_column == index_column - 1 \
                        or current_row == index_row - 1 and current_column == index_column + 1 \
                        or current_row == index_row + 1 and current_column == index_column - 1 \
                        or current_row == index_row + 1 and current_column == index_column + 1:
                    return True
            else:

                if current_column >= len(self.game_array_previous_state_2d[0]) or current_row >= len(
                        self.game_array_previous_state_2d) or current_column < 0 or current_row < 0:
                    return True
                if current_row == index_row and current_column == index_column or \
                        current_row == index_row - 1 and current_column == index_column - 1 or \
                        current_row == index_row - 1 and current_column == index_column + 1 or \
                        current_row == index_row + 1 and current_column == index_column - 1 or \
                        current_row == index_row + 1 and current_column == index_column + 1:
                    return True
        if self.nucleation_neighbour == "Moore":
            if self.periodical:
                if current_row == index_row and current_column == index_column:
                    return True
            else:
                if current_column >= len(self.game_array_previous_state_2d[0]) or current_row >= len(
                        self.game_array_previous_state_2d) or current_column < 0 or current_row < 0:
                    return True
                if current_row == index_row and current_column == index_column:
                    return True
        if self.nucleation_neighbour == "PentagonalRight":
            if self.periodical:
                if current_row == index_row and current_column == index_column or \
                        current_row == index_row - 1 and current_column == index_column + 1 or \
                        current_row == index_row and current_column == index_column + 1 or \
                        current_row == index_row + 1 and current_column == index_column + 1:
                    return True
            else:
                if current_column >= len(self.game_array_previous_state_2d[0]) or current_row >= len(
                        self.game_array_previous_state_2d) or current_column < 0 or current_row < 0:
                    return True
                if current_row == index_row and current_column == index_column or \
                        current_row == index_row - 1 and current_column == index_column + 1 or \
                        current_row == index_row and current_column == index_column + 1 or \
                        current_row == index_row + 1 and current_column == index_column + 1:
                    return True
        if self.nucleation_neighbour == "PentagonalLeft":
            if self.periodical:
                if current_row == index_row and current_column == index_column or \
                        current_row == index_row - 1 and current_column == index_column - 1 or \
                        current_row == index_row and current_column == index_column - 1 or \
                        current_row == index_row + 1 and current_column == index_column - 1:
                    return True
            else:
                if current_column >= len(self.game_array_previous_state_2d[0]) or current_row >= len(
                        self.game_array_previous_state_2d) or current_column < 0 or current_row < 0:
                    return True
                if current_row == index_row and current_column == index_column or \
                        current_row == index_row - 1 and current_column == index_column - 1 or \
                        current_row == index_row and current_column == index_column - 1 or \
                        current_row == index_row + 1 and current_column == index_column - 1:
                    return True
        if self.nucleation_neighbour == "PentagonalUp":
            if self.periodical:
                if current_row == index_row and current_column == index_column or \
                        current_row == index_row - 1 and current_column == index_column - 1 or \
                        current_row == index_row - 1 and current_column == index_column or \
                        current_row == index_row - 1 and current_column == index_column + 1:
                    return True
            else:
                if current_column >= len(self.game_array_previous_state_2d[0]) or current_row >= len(
                        self.game_array_previous_state_2d) or current_column < 0 or current_row < 0:
                    return True
                if current_row == index_row and current_column == index_column or \
                        current_row == index_row - 1 and current_column == index_column - 1 or \
                        current_row == index_row - 1 and current_column == index_column or \
                        current_row == index_row - 1 and current_column == index_column + 1:
                    return True
        if self.nucleation_neighbour == "PentagonalDown":
            if self.periodical:
                if current_row == index_row and current_column == index_column or \
                        current_row == index_row + 1 and current_column == index_column - 1 or \
                        current_row == index_row + 1 and current_column == index_column or \
                        current_row == index_row + 1 and current_column == index_column + 1:
                    return True
            else:
                if current_column >= len(self.game_array_previous_state_2d[0]) or current_row >= len(
                        self.game_array_previous_state_2d) or current_column < 0 or current_row < 0:
                    return True
                if current_row == index_row and current_column == index_column or \
                        current_row == index_row + 1 and current_column == index_column - 1 or \
                        current_row == index_row + 1 and current_column == index_column or \
                        current_row == index_row + 1 and current_column == index_column + 1:
                    return True
        if self.nucleation_neighbour == "HexagonalL":
            if self.periodical:
                if current_row == index_row and current_column == index_column or \
                        current_row == index_row - 1 and current_column == index_column - 1 or \
                        current_row == index_row + 1 and current_column == index_column + 1:
                    return True
            else:
                if current_column >= len(self.game_array_previous_state_2d[0]) or current_row >= len(
                        self.game_array_previous_state_2d) or current_column < 0 or current_row < 0:
                    return True
                if current_row == index_row and current_column == index_column or \
                        current_row == index_row - 1 and current_column == index_column - 1 or \
                        current_row == index_row + 1 and current_column == index_column + 1:
                    return True
        if self.nucleation_neighbour == "HexagonalR":
            if self.periodical:
                if current_row == index_row and current_column == index_column or \
                        current_row == index_row - 1 and current_column == index_column + 1 or \
                        current_row == index_row + 1 and current_column == index_column - 1:
                    return True
            else:
                if current_column >= len(self.game_array_previous_state_2d[0]) or current_row >= len(
                        self.game_array_previous_state_2d) or current_column < 0 or current_row < 0:
                    return True
                if current_row == index_row and current_column == index_column or \
                        current_row == index_row - 1 and current_column == index_column + 1 or \
                        current_row == index_row + 1 and current_column == index_column - 1:
                    return True
                # Radius'
        return False

    def return_colors_dictionary(self):
        return self.colors_dictionary

    def set_colors_dictionary_element(self, index, array):
        if array in self.colors_dictionary.values():
            while True:
                new_array = [random.randint(0, 255),random.randint(0, 255),random.randint(0, 255)]
                if new_array != array:
                    self.colors_dictionary[index] = new_array
                    break
        else:
            self.colors_dictionary[index] = array

    def return_neighbour_array(self):
        return self.neighbours_array

    def search_for_zeros(self):  # globalna zmienna z iloscia zer
        if self.zeros <= 0:
            self.last_iteration = True
            for row in range(self.height):
                for column in range(self.width):
                    if self.game_array_current_state_2d[row][column].id == 0:
                        self.last_iteration = False

    def check_if_last_iteration(self):
        return self.last_iteration

    def randomize_cell_weight_centre(self, index_row, index_column, next_index_row, next_index_column):
        random_weight_row = random.uniform(index_row, next_index_row)
        random_weight_column = random.uniform(index_column, next_index_column)
        return [random_weight_row, random_weight_column]

    def initialize_2d_array(self):
        tmp_array = []
        for row in range(self.height):
            row_array = []
            for column in range(self.width):
                cell_obj = Cell(False)
                cell_obj.set_weight_center(self.randomize_cell_weight_centre(row, column, row + 1, column + 1))
                row_array.append(cell_obj)
            tmp_array.append(row_array)

        return tmp_array

    def calculate_value_2d(self, index_row,
                           index_column):  # for row in range(index_row-self.radius,index_row+self.radius)
        neighbour_index_array = [0, 0, 0, 0]
        neighbour_amount_array = [0, 0, 0, 0]
        dictionary = {}

        if self.nucleation_neighbour == "Radius":  # range od punktu wspolrzednych do length of radius, lookup table
            if self.game_array_previous_state_2d[index_row][index_column].return_id() != 0:
                not_zero_index = self.game_array_previous_state_2d[index_row][index_column].return_id()
                return [not_zero_index, self.colors_dictionary.get(not_zero_index)]
            # iterowanie po tablicy punktow wagowych
            self.local_index_dictionary_radius_neighbour.clear()
            weight_row_current = self.game_array_previous_state_2d[index_row][index_column].return_weight_center()[0]
            weight_column_current = self.game_array_previous_state_2d[index_row][index_column].return_weight_center()[1]

            if self.periodical:
                for current_row in range(index_row - self.neighbour_radius,index_row + self.neighbour_radius % self.height):
                    for current_column in range(index_column - self.neighbour_radius,index_column + self.neighbour_radius % self.width):
                        weight_row_neighbour = \
                            self.game_array_previous_state_2d[current_row % self.height][current_column % self.width].return_weight_center()[0]
                        weight_column_neighbour = \
                            self.game_array_previous_state_2d[current_row % self.height][current_column % self.width].return_weight_center()[1]

                        if current_row == index_row and current_column == index_column:
                            current_column += 1
                            continue

                        if self.in_circle(weight_row_current, weight_column_current, self.neighbour_radius,
                                          weight_row_neighbour, weight_column_neighbour) and \
                                self.game_array_previous_state_2d[current_row % self.height][current_column % self.width].id != 0:

                            if self.game_array_previous_state_2d[current_row % self.height][
                                current_column % self.width].id in self.local_index_dictionary_radius_neighbour:
                                # print(" HERE HERE I AM HERE")
                                self.local_index_dictionary_radius_neighbour[
                                    self.game_array_previous_state_2d[current_row % self.height][current_column % self.width].id] += 1
                            else:
                                self.local_index_dictionary_radius_neighbour[
                                    self.game_array_previous_state_2d[current_row % self.height][current_column % self.width].id] = 1

                                # print(str(self.game_array_previous_state_2d[current_row][current_column].return_id())+" "+str(self.game_array_previous_state_2d[current_row][current_column].return_colours_array()))
                                self.local_colours_dictionary_radius[
                                    self.game_array_previous_state_2d[current_row % self.height][current_column % self.width].id] = \
                                    self.game_array_previous_state_2d[current_row % self.height][
                                        current_column % self.width].return_colours_array()

            else:
                for current_row in range(
                        max(0, index_row - self.neighbour_radius),
                        min(self.height, index_row + self.neighbour_radius)
                ):
                    for current_column in range(
                            max(0, index_column - self.neighbour_radius),
                            min(self.width, index_column + self.neighbour_radius)
                    ):

                        weight_row_neighbour = \
                        self.game_array_previous_state_2d[current_row][current_column].return_weight_center()[0]
                        weight_column_neighbour = \
                        self.game_array_previous_state_2d[current_row][current_column].return_weight_center()[1]

                        if current_row == index_row and current_column == index_column:
                            current_column += 1
                            continue

                        if self.in_circle(weight_row_current, weight_column_current, self.neighbour_radius, weight_row_neighbour, weight_column_neighbour) and self.game_array_previous_state_2d[current_row][current_column].id != 0:

                            if self.game_array_previous_state_2d[current_row][
                                current_column].id in self.local_index_dictionary_radius_neighbour:
                                # print(" HERE HERE I AM HERE")
                                self.local_index_dictionary_radius_neighbour[
                                    self.game_array_previous_state_2d[current_row][current_column].id] += 1
                            else:
                                self.local_index_dictionary_radius_neighbour[
                                    self.game_array_previous_state_2d[current_row][current_column].id] = 1

                                # print(str(self.game_array_previous_state_2d[current_row][current_column].return_id())+" "+str(self.game_array_previous_state_2d[current_row][current_column].return_colours_array()))
                                self.local_colours_dictionary_radius[
                                    self.game_array_previous_state_2d[current_row][current_column].id] = \
                                self.game_array_previous_state_2d[current_row][current_column].return_colours_array()

            if len(self.local_index_dictionary_radius_neighbour) == 0:
                return [0, [255, 255, 255]]
            max_value = max(self.local_index_dictionary_radius_neighbour.values())
            # print("Return index")
            max_indexes = [k for k, v in self.local_index_dictionary_radius_neighbour.items() if v == max_value]

            return_index = random.choice(max_indexes)

            if return_index == 0:
                return [return_index, [255, 255, 255]]
            # print(str(return_index)+" "+str(dictionary.get(return_index))+" "+str(index_row)+" "+str(index_column))
            # print(str(return_index)+" "+str(self.local_colours_dictionary_radius.get(return_index)))
            self.decrease_zeros()
            return [return_index, self.local_colours_dictionary_radius.get(return_index)]

        else:
            if self.game_array_previous_state_2d[index_row][index_column].return_id() != 0:
                not_zero_index = self.game_array_previous_state_2d[index_row][index_column].return_id()
                return [not_zero_index, self.colors_dictionary.get(not_zero_index)]

            array_of_stuff = self.pattern_offsets[self.nucleation_neighbour]
            # array_of_stuff.append([0, 0])
            for item in array_of_stuff:

                current_row = index_row + item[0]
                current_column = index_column + item[1]

                if self.periodical:
                    if self.game_array_previous_state_2d[current_row % self.height][
                        current_column % self.width].return_id()== 0:
                        continue
                    neighbour_number = \
                    self.game_array_previous_state_2d[current_row % self.height][
                        current_column % self.width].return_id()
                    if neighbour_number != 0:
                        if neighbour_number in neighbour_index_array:
                            neighbour_amount_array[neighbour_index_array.index(neighbour_number)] += 1
                            if neighbour_number in self.colors_dictionary:
                                dictionary[neighbour_number] = self.colors_dictionary.get(neighbour_number)
                                pass
                            else:
                                array_of_colours = \
                                    self.game_array_previous_state_2d[
                                        current_row % len(self.game_array_previous_state_2d)][
                                        current_column % len(
                                            self.game_array_previous_state_2d[0])].return_colours_array()
                                self.colors_dictionary[neighbour_number] = array_of_colours
                                dictionary[neighbour_number] = array_of_colours
                        else:
                            new_neighbour_index = neighbour_index_array.index(0)
                            neighbour_index_array[new_neighbour_index] = self.game_array_previous_state_2d[
                                current_row % len(self.game_array_previous_state_2d)][
                                current_column % len(self.game_array_previous_state_2d[0])].return_id()
                            neighbour_amount_array[new_neighbour_index] += 1
                            array_of_colours = self.game_array_previous_state_2d[
                                current_row % len(self.game_array_previous_state_2d)][current_column % len(
                                self.game_array_previous_state_2d[0])].return_colours_array()
                            dictionary[neighbour_number] = array_of_colours
                            if new_neighbour_index in self.colors_dictionary:
                                dictionary[neighbour_number] = self.colors_dictionary.get(new_neighbour_index)
                            else:
                                self.colors_dictionary[neighbour_number] = array_of_colours
                else:
                    if not self.height > current_row >= 0 or not self.width > current_column >= 0 or self.game_array_previous_state_2d[current_row][
                        current_column].return_id() == 0:
                        continue
                    neighbour_number = self.game_array_previous_state_2d[current_row][
                        current_column].return_id()
                    if neighbour_number != 0:
                        if neighbour_number in neighbour_index_array:
                            neighbour_amount_array[neighbour_index_array.index(neighbour_number)] += 1
                            if neighbour_number in self.colors_dictionary:
                                dictionary[neighbour_number] = self.colors_dictionary.get(neighbour_number)
                            else:
                                array_of_colours = self.game_array_previous_state_2d[current_row][
                                    current_column].return_colours_array()
                                self.colors_dictionary[neighbour_number] = array_of_colours
                                dictionary[neighbour_number] = array_of_colours
                        else:
                            new_neighbour_index = neighbour_index_array.index(0)
                            neighbour_index_array[new_neighbour_index] = \
                                self.game_array_previous_state_2d[current_row][current_column].return_id()
                            neighbour_amount_array[new_neighbour_index] += 1
                            array_of_colours = self.game_array_previous_state_2d[current_row][
                                current_column].return_colours_array()
                            dictionary[neighbour_number] = array_of_colours
                            if new_neighbour_index in self.colors_dictionary:
                                dictionary[neighbour_number] = self.colors_dictionary.get(new_neighbour_index)
                            else:
                                self.colors_dictionary[neighbour_number] = array_of_colours

            # print(str(index_row)+" "+str(index_column)+" "+str(neighbour_index_array)+" "+str(neighbour_amount_array))

            dominant_neighbour_indexes = numpy.where(neighbour_amount_array == numpy.amax(neighbour_amount_array))[0]
            random_amount_index = random.choice(dominant_neighbour_indexes)
            return_index = neighbour_index_array[random_amount_index]
            if return_index == 0:
                return [return_index, [255, 255, 255]]
            # print(str(return_index)+" "+str(dictionary.get(return_index))+" "+str(index_row)+" "+str(index_column))
            self.decrease_zeros()
            return [return_index, dictionary.get(return_index)]

    def begin_the_game(self):
        for iteration in range(self.iterations):
            # print("iteration number ", iteration+1)
            self.next_iteration()

    def next_iteration(self):
        del self.game_array_previous_state_2d
        self.game_array_previous_state_2d = self.game_array_current_state_2d
        self.game_array_current_state_2d = self.initialize_2d_array()
        for row in range(self.height):
            for column in range(self.width):
                if self.nucleation_neighbour in self.pentagonal_array_states:
                    self.nucleation_neighbour = random.choice(self.pentagonal_array_states)
                    # print(self.nucleation_neighbour)

                if self.nucleation_neighbour in self.hexagonal_array_states:
                    self.nucleation_neighbour = random.choice(self.hexagonal_array_states)
                    # print(self.nucleation_neighbour)

                index_and_colors = self.calculate_value_2d(row, column)
                #print(str(row) + " " + str(column) + " " + str(index_and_colors[0]) + "    " + str(
                    #index_and_colors[1]) + " new iteration ")
                self.game_array_current_state_2d[row][column].set_id(index_and_colors[0])
                self.game_array_current_state_2d[row][column].set_colours_array(index_and_colors[1])
                self.game_array_current_state_2d[row][column].set_weight_center(
                    self.game_array_previous_state_2d[row][column].return_weight_center())

        self.search_for_zeros()
        # print("return")
        # print(self.game_array_current_state_2d)
        return self.game_array_current_state_2d

    def return_current_array(self):
        return self.game_array_current_state_2d

    def return_previous_array(self):
        return self.game_array_previous_state_2d

    def set_neighbour_radius(self, radius):
        self.radius = radius

    def return_neighbour_radius(self):
        return self.neighbour_radius

    def return_radius(self):
        return self.radius

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

    def set_current_array(self, array):
        self.game_array_current_state_2d = array
        if self.pattern == "manual":
            self.game_array_current_state_2d = self.initialize_weights(self.game_array_current_state_2d)

    def initialize_weights(self, array):
        for row in range(self.height):
            for column in range(self.width):
                array[row][column].set_weight_center(
                    self.randomize_cell_weight_centre(row, column, row + 1, column + 1))
        return array

    def set_pattern_in_array(self, pattern):  # CHECK FOR WRONG ARRAY WRITE PROCESS
        # print("\n\n")
        # print("SET PATTERN")
        if pattern in self.patterns_array:
            if pattern == 'homogeneous':
                #     row_space = self.height/self.height_amount DOROBIC HOMOGENEOUS + RADIUS W GLOWNYM I PRZETESTOWAC
                #     column_space = self.width / self.height_amount

                if self.height_amount > self.height // 2:
                    height_disbalance = True
                    space_between_rows = 2
                    disbalanced_amount_in_rows = self.height_amount - self.height // 2
                    self.tmp_height_amount = self.height // 2
                else:
                    height_disbalance = False
                    space_between_rows = self.height // self.height_amount
                    disbalanced_amount_in_rows = 0
                    self.tmp_height_amount = self.height_amount

                if self.width_amount > self.width // 2:
                    width_disbalance = True
                    space_between_columns = 2
                    disbalanced_amount_in_columns = self.width_amount - self.width // 2
                    self.tmp_width_amount = self.width // 2
                else:
                    width_disbalance = False
                    space_between_columns = self.width // self.width_amount
                    disbalanced_amount_in_columns = 0
                    self.tmp_width_amount = self.width_amount

                iteration = 0
                row_counter = space_between_columns // 2
                for index_row in range(self.tmp_height_amount):
                    column_counter = space_between_columns // 2
                    for index_column in range(self.tmp_width_amount):
                        random_red = random.randint(0, 255)
                        random_green = random.randint(0, 255)
                        random_blue = random.randint(0, 255)

                        if [random_red, random_green, random_blue] in self.colors_dictionary.values():
                            while True:
                                new_array = [random.randint(0, 255), random.randint(0, 255), random.randint(0, 255)]
                                if new_array != [random_red, random_green, random_blue]:
                                    self.colors_dictionary[iteration + 1] = new_array
                                    self.game_array_current_state_2d[row_counter][column_counter].set_colours_array(
                                        new_array)
                                    break
                        else:
                            self.colors_dictionary[iteration + 1] = [random_red, random_green, random_blue]
                            self.game_array_current_state_2d[row_counter][column_counter].set_colours_array(
                                [random_red, random_green, random_blue])
                        self.game_array_current_state_2d[row_counter][column_counter].id = iteration + 1

                        column_counter += space_between_columns
                        iteration += 1
                        self.decrease_zeros()
                    row_counter += space_between_rows

                if height_disbalance != False and width_disbalance != False:
                    disbalanced_row_counter = 0
                    for disbalanced_index_row in range(disbalanced_amount_in_rows):
                        disbalanced_column_counter = 0
                        for disbalanced_index_column in range(disbalanced_amount_in_columns):
                            random_red = random.randint(0, 255)
                            random_green = random.randint(0, 255)
                            random_blue = random.randint(0, 255)
                            self.game_array_current_state_2d[disbalanced_row_counter][
                                disbalanced_column_counter].id = iteration + 1

                            if [random_red, random_green, random_blue] in self.colors_dictionary.values():
                                while True:
                                    new_array = [random.randint(0, 255), random.randint(0, 255), random.randint(0, 255)]
                                    if new_array != [random_red, random_green, random_blue]:
                                        self.colors_dictionary[iteration + 1] = new_array
                                        self.game_array_current_state_2d[disbalanced_row_counter][
                                            disbalanced_column_counter].set_colours_array(
                                            new_array)
                                        break
                            else:
                                self.colors_dictionary[iteration + 1] = [random_red, random_green, random_blue]
                                self.game_array_current_state_2d[disbalanced_row_counter][
                                    disbalanced_column_counter].set_colours_array(
                                    [random_red, random_green, random_blue])

                            disbalanced_column_counter += space_between_columns
                            iteration += 1
                            self.decrease_zeros()
                        disbalanced_row_counter += space_between_rows

                if height_disbalance == False and width_disbalance != False:
                    row_counter = 1
                    for index_row in range(self.tmp_height_amount):
                        disbalanced_column_counter = 0
                        for disbalanced_index_column in range(disbalanced_amount_in_columns):
                            random_red = random.randint(0, 255)
                            random_green = random.randint(0, 255)
                            random_blue = random.randint(0, 255)

                            if [random_red, random_green, random_blue] in self.colors_dictionary.values():
                                while True:
                                    new_array = [random.randint(0, 255), random.randint(0, 255), random.randint(0, 255)]
                                    if new_array != [random_red, random_green, random_blue]:
                                        self.colors_dictionary[iteration + 1] = new_array
                                        self.game_array_current_state_2d[row_counter][
                                            disbalanced_column_counter].set_colours_array(
                                            new_array)
                                        break
                            else:
                                self.colors_dictionary[iteration + 1] = [random_red, random_green, random_blue]
                                self.game_array_current_state_2d[row_counter][
                                    disbalanced_column_counter].set_colours_array(
                                    [random_red, random_green, random_blue])

                            self.game_array_current_state_2d[row_counter][disbalanced_column_counter].id = iteration + 1
                            disbalanced_column_counter += space_between_columns
                            iteration += 1
                            self.decrease_zeros()
                        row_counter += space_between_rows

                if height_disbalance != False and width_disbalance == False:
                    disbalanced_row_counter = 0
                    for disbalanced_index_row in range(disbalanced_amount_in_rows):
                        column_counter = 1
                        for index_column in range(self.tmp_width_amount):
                            random_red = random.randint(0, 255)
                            random_green = random.randint(0, 255)
                            random_blue = random.randint(0, 255)
                            if [random_red, random_green, random_blue] in self.colors_dictionary.values():
                                while True:
                                    new_array = [random.randint(0, 255), random.randint(0, 255), random.randint(0, 255)]
                                    if new_array != [random_red, random_green, random_blue]:
                                        self.colors_dictionary[iteration + 1] = new_array
                                        self.game_array_current_state_2d[disbalanced_row_counter][column_counter].set_colours_array(
                                            new_array)
                                        break
                            else:
                                self.colors_dictionary[iteration + 1] = [random_red, random_green, random_blue]
                                self.game_array_current_state_2d[disbalanced_row_counter][column_counter].set_colours_array(
                                    [random_red, random_green, random_blue])
                            self.game_array_current_state_2d[disbalanced_row_counter][column_counter].id = iteration + 1
                            column_counter += space_between_columns
                            iteration += 1
                            self.decrease_zeros()
                        disbalanced_row_counter += space_between_rows

            if pattern == 'radius':  # radius w menu glownym nie tu albo jebac tutaj

                self.radius_dictionary.clear()
                local_side = 3
                radius_seeds_counter = 0
                can_place_more_seeds = True
                while radius_seeds_counter < self.seeds_amount:
                    # print(radius_seeds_counter)
                    if not can_place_more_seeds:
                        # print("no more space on map, leaving, set this amont of seeds: "+str(radius_seeds_counter))
                        break

                    seed_can_be_placed_in_array = False
                    fail_counter = 0

                    while not seed_can_be_placed_in_array:
                        seed_can_be_placed_in_array = True
                        if fail_counter >= 20000:
                            # print("no free space for iteration: "+str(radius_seeds_counter+1))
                            # can_place_more_seeds = False
                            break

                        random_row = random.randint(0, len(self.game_array_current_state_2d) - 1)
                        random_column = random.randint(0, len(self.game_array_current_state_2d[random_row]) - 1)

                        if len(self.radius_dictionary) == 0:
                            self.radius_dictionary[radius_seeds_counter + 1] = [random_row, random_column]
                            random_red = random.randint(0, 255)
                            random_green = random.randint(0, 255)
                            random_blue = random.randint(0, 255)
                            if [random_red, random_green, random_blue] in self.colors_dictionary.values():
                                while True:
                                    new_array = [random.randint(0, 255), random.randint(0, 255), random.randint(0, 255)]
                                    if new_array != [random_red, random_green, random_blue]:
                                        self.colors_dictionary[radius_seeds_counter + 1] = new_array
                                        self.game_array_current_state_2d[random_row][random_column].set_colours_array(
                                            new_array)
                                        break
                            else:
                                self.colors_dictionary[radius_seeds_counter + 1] = [random_red, random_green, random_blue]
                                self.game_array_current_state_2d[random_row][random_column].set_colours_array(
                                    [random_red, random_green, random_blue])

                            self.game_array_current_state_2d[random_row][random_column].id = radius_seeds_counter + 1
                            self.decrease_zeros()
                            break

                        for index in self.radius_dictionary:
                            already_set_row = self.radius_dictionary.get(index)[0]
                            already_set_column = self.radius_dictionary.get(index)[1]

                            if self.game_array_current_state_2d[random_row][random_column].id != 0 or self.in_circle(
                                    already_set_row, already_set_column, self.radius, random_row, random_column):
                                seed_can_be_placed_in_array = False

                        if seed_can_be_placed_in_array:
                            self.radius_dictionary[radius_seeds_counter + 1] = [random_row, random_column]
                            random_red = random.randint(0, 255)
                            random_green = random.randint(0, 255)
                            random_blue = random.randint(0, 255)
                            if [random_red, random_green, random_blue] in self.colors_dictionary.values():
                                while True:
                                    new_array = [random.randint(0, 255), random.randint(0, 255), random.randint(0, 255)]
                                    if new_array != [random_red, random_green, random_blue]:
                                        self.colors_dictionary[radius_seeds_counter + 1] = new_array
                                        self.game_array_current_state_2d[random_row][random_column].set_colours_array(
                                            new_array)
                                        break
                            else:
                                self.colors_dictionary[radius_seeds_counter + 1] = [random_red, random_green, random_blue]
                                self.game_array_current_state_2d[random_row][random_column].set_colours_array(
                                    [random_red, random_green, random_blue])
                            self.game_array_current_state_2d[random_row][random_column].id = radius_seeds_counter + 1
                            self.decrease_zeros()
                            break
                        fail_counter += 1

                    radius_seeds_counter += 1

            if pattern == 'random':

                for iteration in range(self.seeds_amount):
                    random_row = random.randint(0, len(self.game_array_current_state_2d) - 1)
                    random_column = random.randint(0, len(self.game_array_current_state_2d[random_row]) - 1)
                    if (self.game_array_current_state_2d[random_row][random_column].id == 0):
                        random_red = random.randint(0, 255)
                        random_green = random.randint(0, 255)
                        random_blue = random.randint(0, 255)
                        if [random_red, random_green, random_blue] in self.colors_dictionary.values():
                            while True:
                                new_array = [random.randint(0, 255), random.randint(0, 255), random.randint(0, 255)]
                                if new_array != [random_red, random_green, random_blue]:
                                    self.colors_dictionary[iteration + 1] = new_array
                                    self.game_array_current_state_2d[random_row][random_column].set_colours_array(
                                        new_array)
                                    break
                        else:
                            self.colors_dictionary[iteration + 1] = [random_red, random_green, random_blue]
                            self.game_array_current_state_2d[random_row][random_column].set_colours_array(
                                [random_red, random_green, random_blue])
                        self.game_array_current_state_2d[random_row][random_column].id = iteration + 1
                        self.decrease_zeros()
                    else:
                        print(" iteration number: " + str(iteration) + " was not applied")
            if pattern == 'manual':
                pass
        else:
            self.game_array_current_state_2d = self.initialize_2d_array()

    def set_parameters(self, width, height, iterations, pattern, periodical, neighbour, seeds_amount, width_amount,
                       height_amount, radius, neighbour_radius):
        self.iterations = iterations
        if periodical == "periodical":
            self.periodical = True
        else:
            self.periodical = False

        if pattern != self.pattern or self.width != width or self.height != height or \
                self.nucleation_neighbour != neighbour or self.seeds_amount != seeds_amount or \
                self.width_amount != width_amount or self.height_amount != height_amount or self.radius != radius or self.neighbour_radius != neighbour_radius:
            self.height_amount = height_amount
            self.width_amount = width_amount
            self.radius = radius
            self.neighbour_radius = neighbour_radius

            self.height = height
            self.width = width
            self.seeds_amount = seeds_amount
            self.colors_dictionary.clear()

            self.game_array_current_state_2d = self.initialize_2d_array()
            self.nucleation_neighbour = neighbour
            self.set_neighbour(self.nucleation_neighbour)
            self.pattern = pattern
            self.zeros = self.height * self.width
            self.set_pattern_in_array(pattern)
            self.last_iteration = False

    def set_seeds_amount(self, seeds):
        self.seeds_amount = seeds

    def return_seeds_amount(self):
        return self.seeds_amount

    def set_radius_amount(self, radius):
        self.radius = radius

    def return_radius_amount(self):
        return self.radius

    def set_width_amount(self, width):
        self.width_amount = width

    def return_width_amount(self):
        return self.width_amount

    def set_heigh_amount(self, height):
        self.height_amount = height

    def return_heigh_amount(self):
        return self.height_amount

    def set_neighbour(self, neighbour):
        if neighbour in self.neighbours_array:
            self.nucleation_neighbour = neighbour
        else:
            self.nucleation_neighbour = "Neumann"

    def in_circle(self, center_x, center_y, radius, x, y):
        if self.periodical:
            dist = math.sqrt((((3 * (center_x - x))) ** 2)%self.height + (((3 * (center_y - y))) ** 2)%self.width)
        else:
            dist = math.sqrt((3 * (center_x - x)) ** 2 + (3 * (center_y - y)) ** 2)
        return dist <= radius ** 2

    def restart_grid(self):
        self.last_iteration = False
        self.local_index_dictionary_radius_neighbour.clear()
        self.local_colours_dictionary_radius.clear()
        self.colors_dictionary.clear()
        self.colors_dictionary[0] = [255, 255, 255]
        self.game_array_previous_state_2d = self.initialize_2d_array()
        self.game_array_current_state_2d = copy.deepcopy(self.game_array_previous_state_2d)
        self.set_neighbour(self.nucleation_neighbour)
        self.zeros = self.width * self.height
        self.set_pattern_in_array(self.pattern)

    def return_parameters(self):
        return repr(self.height) + " " + repr(self.width) + " " + repr(self.iterations) + " " + repr(
            self.periodical) + " " + repr(
            self.pattern) + " " + repr(self.game_array_previous_state_2d)

    def print_current_array(self):
        for row in range(len(self.game_array_current_state_2d)):
            for column in range(len(self.game_array_current_state_2d[row])):
                print(str(self.game_array_current_state_2d[row][column].return_id()), end=' ')
            print("\n")

    def return_initial_array(self):
        return self.initialize_2d_array()

    def return_pattern_array(self):
        return self.patterns_array

    def return_nucleation_neighbour(self):
        return self.nucleation_neighbour
