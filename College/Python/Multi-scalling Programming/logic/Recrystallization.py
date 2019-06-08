from models.Cell import Cell
import math

class Recrystallization:

    def __init__(self,height,width):
        self.height = height
        self.width = width
        self.A = 86710969050178.5
        self.B = 9.41268203527779
        self.time_step = 0.0

        self.cosNadA = 2.57E-10
        self.cosNadB = 8.0E10
        self.cosPodF = 0.0
        self.cosPodG = 1.9

        self.ro = self.calculate_ro(self.A, self.B, self.time_step)
        self.sigma = self.calculate_sigma(self.cosNadA, self.cosNadB, self.cosPodF, self.cosPodG, self.ro)
        self.previous_ro = 0
        self.delta_ro = self.ro-self.previous_ro
        #print(str(self.ro) + " " + str(self.sigma))
        self.average_ro = self.delta_ro / (self.height * self.width)

        #print(" dimensions: "+str(self.height)+" "+str(self.width))



    def calculate_ro(self, a, b, time_step):
        return a/b + (1-(a/b))*math.exp(-b*time_step)

    def calculate_sigma(self, overA, overB, underF, underG, ro):
        return underF + underG*overA*overB*math.sqrt(ro)

    def iteration(self):
        self.time_step += 0.001
        self.ro = self.calculate_ro(self.A, self.B, self.time_step)
        self.sigma = self.calculate_sigma(self.cosNadA, self.cosNadB, self.cosPodF, self.cosPodG, self.ro)
        self.delta_ro = self.ro-self.previous_ro
        self.previous_ro = self.ro
        self.average_ro = self.delta_ro / (self.height * self.width)
        print(str(self.time_step)+" "+str(self.ro)+" "+str(self.delta_ro)+" "+str(self.average_ro))

    def return_average_ro(self):
        return self.average_ro

    def return_ro(self):
        return self.ro

    def return_delta_ro(self):
        return self.delta_ro

    def return_sigma(self):
        return self.sigma