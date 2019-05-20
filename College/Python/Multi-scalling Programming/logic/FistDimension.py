from models.Cell import Cell


class FirstDimension:
    def __init__(self, width=100, iterations=100, rule=90):
        self.width = width
        self.iterations = iterations
        self.rule = rule
        self.rule_array = self.create_variables_for_speficic_rule(rule)
        self.game_array_current_state = self.initialize_array(width)
        self.game_array_previous_state = self.initialize_array(width)
        self.alive_cells = [51]
        self.set_first_cell_alive(self.alive_cells)

    def create_variables_for_speficic_rule(self, rule):
        tmp_array = []
        while rule > 0:
            if rule >= 128:
                tmp_array.append(7)
                rule -= 128
            elif rule >= 64:
                tmp_array.append(6)
                rule -= 64
            elif rule >= 32:
                tmp_array.append(5)
                rule -= 32
            elif rule >= 16:
                tmp_array.append(4)
                rule -= 16
            elif rule >= 8:
                tmp_array.append(3)
                rule -= 8
            elif rule >= 4:
                tmp_array.append(2)
                rule -= 4
            elif rule >= 2:
                tmp_array.append(1)
                rule -= 2
            elif rule >= 1:
                tmp_array.append(0)
                rule -= 1

        #print(tmp_array)
        return tmp_array

    def initialize_array(self, width=100):
        tmp_array = []
        for column in range(width):
            tmp_array.append(Cell(False))

        return tmp_array

    def calculate_value(self, index):
        sum = 2 ** 2 * (self.game_array_previous_state[index - 1].is_alive * 1) + 2 ** 1 * (
                self.game_array_previous_state[index].is_alive * 1) + 2 ** 0 * (self.game_array_previous_state[
                                                                                    (index + 1) % len(
                                                                                        self.game_array_previous_state)].is_alive * 1)  # previous, current, following
        if sum in self.rule_array:  # any(first == c for c in letter) , if "a" in ["a", "b", "c"]:
            return True
        else:
            return False

    def begin_the_game(self):
        for iteration in range(self.iterations):
            # print("iteration number ", iteration+1)
            self.next_iteration()

    def single_iteration(self):
        self.game_array_previous_state = self.game_array_current_state
        self.game_array_current_state = self.initialize_array(len(self.game_array_current_state))
        for index in range(len(self.game_array_current_state)):
            self.game_array_current_state[index].is_alive = self.calculate_value(index)
        return self.game_array_previous_state

    def single_iteration_first_time(self):
        self.game_array_previous_state = self.game_array_current_state
        self.game_array_current_state = self.initialize_array(len(self.game_array_current_state))
        for index in range(len(self.game_array_current_state)):
            self.game_array_current_state[index].is_alive = self.calculate_value(index)
        return self.game_array_current_state

    def next_iteration(self):
        self.game_array_previous_state = self.game_array_current_state
        self.game_array_current_state = self.initialize_array(len(self.game_array_current_state))
        for index in range(len(self.game_array_current_state)):
            self.game_array_current_state[index].is_alive = self.calculate_value(index)
        #self.print_array()
        #print("\n")


    def return_current_array(self):
        return self.game_array_current_state

    def return_previous_array(self):
        return self.game_array_previous_state

    def set_iteration(self, iteration):
        self.iterations = iteration

    def set_width(self, width):
        self.width = width

    def set_rule(self, rule):
        self.rule = rule

    def set_first_cell_alive(self, array):
        if -1 in array:
            self.initialize_array(self.width)
            return
        for index in array:
            self.game_array_current_state[index].is_alive = True

    def set_parameters(self, width, iterations, rule, array):
        self.width = width
        self.iterations = iterations
        self.rule = rule
        self.alive_cells = array
        self.rule_array = self.create_variables_for_speficic_rule(rule)
        self.set_first_cell_alive(self.alive_cells)

    def restart_grid(self):
        self.game_array_current_state = self.initialize_array(self.width)
        self.set_first_cell_alive(self.alive_cells)

    def return_parameters(self):
        return repr(self.rule) + " " + repr(self.width) + " " + repr(self.iterations) + " " + repr(self.game_array_current_state)
