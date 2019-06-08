import random,math,numpy,copy

class MonteCarlo:

    def __init__(self, iterations=1, kt=0.5, periodical=True, neighbour_pattern="Moore", neighbour_radius = 4): #Todo create gui functions & logic, fix montecarlo logic so it can be compatible with gui
        self.iterations = iterations
        self.kt = kt
        self.periodical = periodical
        self.neighbour_pattern = neighbour_pattern
        self.neighbour_radius = neighbour_radius
        self.pattern_offsets = {
            'Neumann': [[-1, 0], [0, -1], [1, 0], [0, 1]],  # row, column
            'PentagonalUp': [[-1, 1], [-1, 0], [-1, -1], [0, -1], [0, 1]],
            'PentagonalDown': [[1, -1], [1, 0], [1, 1], [0, -1], [0, 1]],
            'PentagonalRight': [[-1, 1], [0, 1], [1, 1], [-1, 0], [1, 0]],
            'PentagonalLeft': [[-1, -1], [0, -1], [1, -1], [-1, 0], [1, 0]],
            'HexagonalL': [[-1, 0], [-1, 1], [0, -1], [0, 1], [1, -1], [1, 0]],
            'HexagonalR': [[-1, 0], [-1, -1], [0, -1], [0, 1], [1, 1], [1, 0]],
            'Moore': [[-1, 0], [-1, 1], [0, -1], [0, 1], [1, -1], [1, 0], [-1, -1], [1, 1]],
        }
        self.pentagonal_array_states = ["PentagonalLeft", "PentagonalRight", "PentagonalDown", "PentagonalUp"]
        self.hexagonal_array_states = ["HexagonalR", "HexagonalL"]
        self.border_seed_energy = 1
        self.shuffled_coordinates_array = None

        self.neighbours_states = 'Neumann,Pentagonal,Hexagonal,PentagonalLeft,PentagonalRight,PentagonalDown,PentagonalUp,Moore,Radius,HexagonalR,HexagonalL'
        self.neighbours_array = self.neighbours_states.split(",")
        self.energy_array = None

    def return_neighbour_array(self):
        return self.neighbours_array

    def set_generated_microstructure(self,generated_microstructure,height ,width):
        self.height = height
        self.width = width
        self.generated_microstructure = generated_microstructure
        self.shuffled_coordinates_array = numpy.array(
            [[row, column] for row in range(self.height) for column in range(self.width)])
        #print(self.shuffled_coordinates_array)



    # def prepare_energy_array(self,generated_microstructure):
    #     if self.energy_array == None:
    #         self.energy_array = copy.deepcopy(generated_microstructure)
    #
    #     for row in range(self.height):
    #         for column in range(self.width):
    #             if self.energy_array[row][column].return_energy() == 0:
    #                 self.energy_array[row][column].set_colours_array([0,0,0])
    #             elif self.energy_array[row][column].return_energy() > 0:
    #                 self.energy_array[row][column].set_colours_array([254,226,62])
    #             else:
    #                 print("ERROR VALUE")
    #                 self.energy_array[row][column].set_colours_array([0,0,0])



    def set_parameters(self,neighbour,kt,iterations,periodical,radius):
        if self.neighbour_pattern != neighbour or self.kt != kt or self.iterations != iterations or self.periodical != periodical or self.neighbour_radius != radius:
            self.neighbour_pattern = neighbour
            self.kt = kt
            self.iterations = iterations
            self.periodical = periodical
            self.neighbour_radius = radius

    def iteration(self, input_array): #Todo, GUI, connect gui in main file
        numpy.random.shuffle(self.shuffled_coordinates_array)

        if self.neighbour_pattern in self.pentagonal_array_states:
            self.neighbour_pattern = random.choice(self.pentagonal_array_states)
            # print(self.nucleation_neighbour)

        if self.neighbour_pattern in self.hexagonal_array_states:
            self.neighbour_pattern = random.choice(self.hexagonal_array_states)

        for random_coordinates in self.shuffled_coordinates_array:
            if self.neighbour_pattern == "Radius":
                index_row = input_array[random_coordinates[0]][random_coordinates[1]].return_weight_center()[0]
                index_column = input_array[random_coordinates[0]][random_coordinates[1]].return_weight_center()[1]
            else:
                index_row = random_coordinates[0]
                index_column = random_coordinates[1]

            dictionary = {}
            energy_before = 0

            if self.neighbour_pattern == "Radius":  # range od punktu wspolrzednych do length of radius, lookup table
                radius_counter = 0
                weight_row_current = input_array[random_coordinates[0]][random_coordinates[1]].return_weight_center()[0]
                weight_column_current = input_array[random_coordinates[0]][random_coordinates[1]].return_weight_center()[1]

                if self.periodical:
                    for current_row in range(random_coordinates[0] - self.neighbour_radius,
                                             random_coordinates[0] + self.neighbour_radius % self.height):
                        for current_column in range(random_coordinates[1] - self.neighbour_radius,
                                                    random_coordinates[1] + self.neighbour_radius % self.width):
                            weight_row_neighbour = \
                                input_array[current_row % self.height][
                                    current_column % self.width].return_weight_center()[0]
                            weight_column_neighbour = \
                                input_array[current_row % self.height][
                                    current_column % self.width].return_weight_center()[1]

                            if current_row == random_coordinates[0] and current_column == random_coordinates[1]:
                                current_column += 1
                                continue

                            if self.in_circle(weight_row_current, weight_column_current, self.neighbour_radius,
                                              weight_row_neighbour, weight_column_neighbour):

                                if input_array[current_row % self.height][
                                    current_column % self.width].id in dictionary:
                                    # print(" HERE HERE I AM HERE")
                                    dictionary[
                                        input_array[current_row % self.height][
                                            current_column % self.width].id][0] += 1
                                else:
                                    dictionary[
                                        input_array[current_row % self.height][
                                            current_column % self.width].id] = [1,input_array[current_row % self.height][
                                            current_column % self.width].return_colours_array()]

                                if input_array[current_row % self.height][
                                    current_column % self.width].return_id() != \
                                        input_array[random_coordinates[0] % self.height][
                                            random_coordinates[1] % self.width].return_id():
                                    energy_before += 1
                            radius_counter+=1

                else:
                    for current_row in range(
                            max(0, random_coordinates[0] - self.neighbour_radius),
                            min(self.height, random_coordinates[0] + self.neighbour_radius)
                    ):
                        for current_column in range(
                                max(0, random_coordinates[1] - self.neighbour_radius),
                                min(self.width, random_coordinates[1] + self.neighbour_radius)
                        ):

                            weight_row_neighbour = \
                                input_array[current_row][current_column].return_weight_center()[0]
                            weight_column_neighbour = \
                                input_array[current_row][current_column].return_weight_center()[1]

                            if current_row == random_coordinates[0] and current_column == random_coordinates[1]:
                                current_column += 1
                                continue

                            if self.in_circle(weight_row_current, weight_column_current, self.neighbour_radius, weight_row_neighbour, weight_column_neighbour):

                                if input_array[current_row][current_column].id in dictionary:
                                    # print(" HERE HERE I AM HERE")
                                    dictionary[input_array[current_row][current_column].id][0] += 1
                                else:
                                    dictionary[input_array[current_row][current_column].id] = [1,input_array[current_row][current_column].return_colours_array()]

                                if input_array[random_coordinates[0]][random_coordinates[1]].return_id() != input_array[current_row][current_column].return_id():
                                    energy_before += 1
                            radius_counter += 1

                random_index, amount_and_colors = random.choice(list(dictionary.items()))

                energy_after = radius_counter - amount_and_colors[0]

                if self.find_minimal_energy(energy_after - energy_before):
                    input_array[random_coordinates[0]][random_coordinates[1]].set_id(random_index)
                    input_array[random_coordinates[0]][random_coordinates[1]].set_colours_array(amount_and_colors[1])

                input_array[random_coordinates[0]][random_coordinates[1]].set_energy(energy_before)
            else:

                array_of_stuff = self.pattern_offsets[self.neighbour_pattern]

                for item in array_of_stuff:
                    current_row = index_row + item[0]
                    current_column = index_column + item[1]
                    if self.periodical:
                        if input_array[current_row % self.height][current_column % self.width].return_id() in dictionary:
                            dictionary[input_array[current_row % self.height][current_column % self.width].return_id()][0] += 1
                        else:
                            dictionary[input_array[current_row % self.height][current_column % self.width].return_id()] = [1, [input_array[current_row % self.height][
                                    current_column % self.width].return_colours_array()]]

                        if input_array[current_row % self.height][current_column % self.width].return_id() != input_array[index_row % self.height][
                            index_column % self.width].return_id():
                                energy_before += 1

                    else:
                        if not self.height > current_row >= 0 or not self.width > current_column >= 0:
                            continue

                        if input_array[current_row][current_column].return_id() in dictionary:
                            dictionary[input_array[current_row][current_column].return_id()][0] += 1
                        else:
                            dictionary[input_array[current_row][current_column].return_id()] = [1, [input_array[current_row][current_column].return_colours_array()]]
                        if input_array[current_row][current_column].return_id() != input_array[index_row][index_column].return_id():
                            energy_before += 1


                random_index, amount_and_colors = random.choice(list(dictionary.items()))
                energy_after = 8 - amount_and_colors[0]

                if self.find_minimal_energy(energy_after - energy_before):
                    input_array[index_row][index_column].set_id(random_index)
                    input_array[index_row][index_column].set_colours_array(amount_and_colors[1])

                input_array[index_row][index_column].set_energy(energy_before)
        #self.prepare_energy_array(self.generated_microstructure) # remembr to return energy array
        return input_array

    def return_energy_array(self):
        return self.energy_array

    def in_circle(self, center_x, center_y, radius, x, y):
        if self.periodical:
            dist = math.sqrt((((3 * (center_x - x))) ** 2)%self.height + (((3 * (center_y - y))) ** 2)%self.width)
        else:
            dist = math.sqrt((3 * (center_x - x)) ** 2 + (3 * (center_y - y)) ** 2)
        return dist <= radius ** 2

    def find_minimal_energy(self,delta_energy):
        if delta_energy <=0:
            return True
        else:
            if random.randint(0,100) < math.exp(-delta_energy/self.kt):
                return True
        return False

    def set_iterations(self, iterations):
        self.iterations = iterations

    def set_kt(self, kt):
        self.kt = kt

    def set_periodical(self, periodical):
        self.periodical = periodical

    def set_neighbour_pattern(self, neighbour_pattern):
        self.neighbour_pattern = neighbour_pattern

    def return_iterations(self):
        return self.iterations

    def return_kt(self):
        return self.kt

    def return_periodical(self):
        return self.periodical

    def return_neighbour_pattern(self):
        return self.neighbour_pattern

    def return_neighbour_radius(self):
        return self.neighbour_radius

    def set_neighbour_radius(self, radius):
        self.neighbour_radius = radius

    def set_periodical(self, periodical):
        self.periodical = periodical

    def set_initial_height(self,height):
        self.height = height

    def set_height(self,heigh):
        self.height = heigh
        self.shuffled_coordinates_array = numpy.array(
            [[row, column] for row in range(self.height) for column in range(self.width)])

    def set_initial_width(self, width):
        self.width = width

    def set_width(self, width):
        self.width = width
        self.shuffled_coordinates_array = numpy.array(
            [[row, column] for row in range(self.height) for column in range(self.width)])

    def return_height(self):
        return self.height

    def return_width(self):
        return self.width
