# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'Gui.ui'
#
# Created by: PyQt5 UI code generator 5.11.3
#
# WARNING! All changes made in this file will be lost!

import random,sys
from PyQt5 import QtCore, QtGui, QtWidgets
from PyQt5.QtCore import pyqtSlot
from PyQt5.QtWidgets import QWidget, QMainWindow, QDialog
from PyQt5 import QtTest
from PyQt5.QtGui import QColor, QPainter

from logic.FistDimension import FirstDimension
from logic.SecondDimension import SecondDimension
from logic.Nucleation import Nucleation
from logic.MonteCarlo import MonteCarlo

from models.Cell import  Cell
import numpy


class Ui_Dialog(QWidget):

    def __init__(self):
        super().__init__()
        self.FirstDimensionObj = FirstDimension()
        self.SecondDimensionObj = SecondDimension()
        self.NucleationObj = Nucleation()
        self.MonteCarloObj = MonteCarlo()
        self.width = 100
        self.iterations = 100
        self.rule = 90
        self.alive_cels_numbers = [51]
        self.side = 3
        self.side_2d = 3
        self.nucleation_side = 3
        self.width_draw_2d = 3
        self.height_draw_2d = 3
        self.row = 0
        self.nucleation_row = 0
        self.nucleation_column = 0
        self.first_time = True
        self.previous_iteration_array_2d = [] #self.current_iteration_array self.current_iteration_array_2d should be her!

        self.column_2d_counter = 0
        self.row_2d_counter = 0
        self.pattern_width_counter = 0
        self.pattern_height_counter = 0
        self.first_time_2d = True
        self.counter_2d = 0

        self.height_2d = self.SecondDimensionObj.return_height()
        self.width_2d = self.SecondDimensionObj.return_width()
        self.iterations_2d = self.SecondDimensionObj.return_iteration()
        self.pattern_height = self.SecondDimensionObj.return_pattern_height()
        self.pattern_width = self.SecondDimensionObj.return_pattern_width()
        self.pattern_2d = self.SecondDimensionObj.return_pattern()

        self.initial_manual_array_2d = self.SecondDimensionObj.return_initial_array()
        self.manual_array_text_backup = ''
        self.settings_has_changed = True

        self.previous_iteration_array_2d=self.SecondDimensionObj.return_previous_array()
        self.current_iteration_array_2d = self.SecondDimensionObj.return_current_array()
        self.nucleation_neighbour_radius = self.NucleationObj.return_neighbour_radius()

        self.previous_counter = self.iterations
        self.previous_row = self.row

        self.nucleation_pattern_2d = self.NucleationObj.return_pattern()

        if self.nucleation_pattern_2d != "manual":
            self.nucleation_settings_has_changed = False
        else:
            self.nucleation_settings_has_changed = True

        self.nucleation_height_2d = self.NucleationObj.return_height()
        self.nucleation_width_2d = self.NucleationObj.return_width()
        self.nucleation_iterations_2d = self.NucleationObj.return_iteration()
        if self.NucleationObj.return_periodical():
            self.nucleation_boundary_conditions = "periodical"
        else:
            self.nucleation_boundary_conditions = "absorbing"
        self.nucleation_neighbours_type = self.NucleationObj.return_nucleation_neighbour()
        self.nucleation_seeds_amount = self.NucleationObj.return_seeds_amount()
        self.nucleation_user_width = self.NucleationObj.return_width_amount()
        self.nucleation_user_height = self.NucleationObj.return_heigh_amount()
        self.nucleation_radius = self.NucleationObj.return_radius_amount()
        self.nucleation_initial_manual_array_2d = self.NucleationObj.return_initial_array()
        self.nucleation_manual_array_text_backup = ''
        self.nucleation_previous_iteration_array_2d = self.NucleationObj.return_previous_array()
        self.nucleation_current_iteration_array_2d = self.NucleationObj.return_current_array()
        self.array_if_is_drawn = numpy.zeros([self.nucleation_height_2d,self.nucleation_width_2d])

    def setupUi(self, Dialog):
        Dialog.setObjectName("Dialog")
        Dialog.resize(1250, 600)
        Dialog.setMinimumSize(QtCore.QSize(1250, 600))
        Dialog.setMaximumSize(QtCore.QSize(1250, 600))
        Dialog.setSizeIncrement(QtCore.QSize(10, 10))
        Dialog.setBaseSize(QtCore.QSize(100, 100))
        Dialog.setAutoFillBackground(True)
        self.horizontalWidget = QtWidgets.QWidget(Dialog)
        self.horizontalWidget.setGeometry(QtCore.QRect(0, 0, 1250, 600))
        self.horizontalWidget.setMinimumSize(QtCore.QSize(1250, 600))
        self.horizontalWidget.setMaximumSize(QtCore.QSize(1250, 600))
        self.horizontalWidget.setObjectName("horizontalWidget")
        self.mode_menu = QtWidgets.QHBoxLayout(self.horizontalWidget)
        self.mode_menu.setSizeConstraint(QtWidgets.QLayout.SetMaximumSize)
        self.mode_menu.setContentsMargins(0, 0, 0, 0)
        self.mode_menu.setObjectName("mode_menu")
        self.tabWidget = QtWidgets.QTabWidget(self.horizontalWidget)
        self.tabWidget.setMinimumSize(QtCore.QSize(1350, 600))
        self.tabWidget.setMaximumSize(QtCore.QSize(1350, 601))
        self.tabWidget.setObjectName("tabWidget")
        self.OneDimensionalTab = QtWidgets.QWidget()
        self.OneDimensionalTab.setObjectName("OneDimensionalTab")
        self.horizontalLayoutWidget_7 = QtWidgets.QWidget(self.OneDimensionalTab)
        self.horizontalLayoutWidget_7.setGeometry(QtCore.QRect(0, 0, 1361, 606))
        self.horizontalLayoutWidget_7.setObjectName("horizontalLayoutWidget_7")
        self.horizontalLayout_4 = QtWidgets.QHBoxLayout(self.horizontalLayoutWidget_7)
        self.horizontalLayout_4.setContentsMargins(0, 0, 0, 0)
        self.horizontalLayout_4.setObjectName("horizontalLayout_4")
        self.formLayout = QtWidgets.QFormLayout()
        self.formLayout.setObjectName("formLayout")
        self.horizontalLayout_3 = QtWidgets.QHBoxLayout()
        self.horizontalLayout_3.setObjectName("horizontalLayout_3")
        self.formLayout.setLayout(1, QtWidgets.QFormLayout.FieldRole, self.horizontalLayout_3)
        self.scene_1d = QtWidgets.QGraphicsScene()
        self.green_pen = QtGui.QPen(QtCore.Qt.green) #QColor(200,200,0)
        self.red_pen = QtGui.QPen(QtCore.Qt.red)
        self.blue_pen = QtGui.QPen(QtCore.Qt.blue)
        self.width_layout_horizontal = QtWidgets.QHBoxLayout()
        self.width_layout_horizontal.setObjectName("width_layout_horizontal")
        self.widthLabel1D = QtWidgets.QLabel(self.horizontalLayoutWidget_7)
        self.widthLabel1D.setMinimumSize(QtCore.QSize(60, 0))
        self.widthLabel1D.setMaximumSize(QtCore.QSize(60, 16777215))
        self.widthLabel1D.setObjectName("widthLabel1D")
        self.width_layout_horizontal.addWidget(self.widthLabel1D)
        self.widthText1D = QtWidgets.QTextEdit(self.horizontalLayoutWidget_7)
        self.widthText1D.setMinimumSize(QtCore.QSize(60, 30))
        self.widthText1D.setMaximumSize(QtCore.QSize(60, 30))
        self.widthText1D.setDocumentTitle("")
        self.widthText1D.setObjectName("widthText1D")
        self.width_layout_horizontal.addWidget(self.widthText1D)
        self.iterationsLayout = QtWidgets.QHBoxLayout()
        self.iterationsLayout.setObjectName("iterationsLayout")
        self.iterationsLabel1D = QtWidgets.QLabel(self.horizontalLayoutWidget_7)
        self.iterationsLabel1D.setMinimumSize(QtCore.QSize(60, 0))
        self.iterationsLabel1D.setMaximumSize(QtCore.QSize(60, 16777215))
        self.iterationsLabel1D.setObjectName("iterationsLabel1D")
        self.iterationsLayout.addWidget(self.iterationsLabel1D)
        self.iterationsText1D = QtWidgets.QTextEdit(self.horizontalLayoutWidget_7)
        self.iterationsText1D.setMinimumSize(QtCore.QSize(60, 30))
        self.iterationsText1D.setMaximumSize(QtCore.QSize(60, 30))
        self.iterationsText1D.setObjectName("iterationsText1D")
        self.iterationsLayout.addWidget(self.iterationsText1D)
        self.aliveLayout = QtWidgets.QHBoxLayout()
        self.aliveLayout.setObjectName("aliveLayout")
        self.aliveCellsArrayLabel1D = QtWidgets.QLabel(self.horizontalLayoutWidget_7)
        self.aliveCellsArrayLabel1D.setMinimumSize(QtCore.QSize(50, 0))
        self.aliveCellsArrayLabel1D.setMaximumSize(QtCore.QSize(50, 16777215))
        self.aliveCellsArrayLabel1D.setObjectName("aliveCellsArrayLabel1D")
        self.aliveLayout.addWidget(self.aliveCellsArrayLabel1D)
        self.aliveCellsText1D = QtWidgets.QTextEdit(self.horizontalLayoutWidget_7)
        self.aliveCellsText1D.setMinimumSize(QtCore.QSize(60, 30))
        self.aliveCellsText1D.setMaximumSize(QtCore.QSize(60, 30))
        self.aliveCellsText1D.setObjectName("aliveCellsText1D")
        self.aliveLayout.addWidget(self.aliveCellsText1D)
        self.ruleLayout = QtWidgets.QHBoxLayout()
        self.ruleLayout.setObjectName("ruleLayout")
        self.ruleLabel1D = QtWidgets.QLabel(self.horizontalLayoutWidget_7)
        self.ruleLabel1D.setMinimumSize(QtCore.QSize(30, 0))
        self.ruleLabel1D.setMaximumSize(QtCore.QSize(30, 16777215))
        self.ruleLabel1D.setSizeIncrement(QtCore.QSize(100, 0))
        self.ruleLabel1D.setBaseSize(QtCore.QSize(100, 0))
        self.ruleLabel1D.setObjectName("ruleLabel1D")
        self.ruleLayout.addWidget(self.ruleLabel1D)
        self.ruleText1D = QtWidgets.QTextEdit(self.horizontalLayoutWidget_7)
        self.ruleText1D.setMinimumSize(QtCore.QSize(60, 30))
        self.ruleText1D.setMaximumSize(QtCore.QSize(60, 30))
        self.ruleText1D.setObjectName("ruleText1D")
        self.ruleLayout.addWidget(self.ruleText1D)
        self.aliveLayout.addLayout(self.ruleLayout)
        self.iterationsLayout.addLayout(self.aliveLayout)
        self.width_layout_horizontal.addLayout(self.iterationsLayout)
        self.initializeGameButton1D = QtWidgets.QPushButton(self.horizontalLayoutWidget_7)
        self.initializeGameButton1D.setMinimumSize(QtCore.QSize(60, 0))
        self.initializeGameButton1D.setMaximumSize(QtCore.QSize(60, 16777215))
        self.initializeGameButton1D.setObjectName("initializeGameButton1D")
        self.initializeGameButton1D.clicked.connect(self.initialize_click)
        self.width_layout_horizontal.addWidget(self.initializeGameButton1D)
        self.beginGameButton1D = QtWidgets.QPushButton(self.horizontalLayoutWidget_7)
        self.beginGameButton1D.setMinimumSize(QtCore.QSize(60, 0))
        self.beginGameButton1D.setMaximumSize(QtCore.QSize(60, 16777215))
        self.beginGameButton1D.setObjectName("beginGameButton1D")
        self.beginGameButton1D.clicked.connect(self.begin_game)
        self.width_layout_horizontal.addWidget(self.beginGameButton1D)
        self.restart_button_1d = QtWidgets.QPushButton(self.horizontalLayoutWidget_7)
        self.restart_button_1d.setObjectName("restart_button_1d")
        self.restart_button_1d.clicked.connect(self.restart_plot)
        self.width_layout_horizontal.addWidget(self.restart_button_1d)
        self.formLayout.setLayout(1, QtWidgets.QFormLayout.LabelRole, self.width_layout_horizontal)
        self.graphic_ca_1d = QtWidgets.QGraphicsView(self.horizontalLayoutWidget_7)
        self.graphic_ca_1d.setMinimumSize(QtCore.QSize(1350, 550))
        self.graphic_ca_1d.setMaximumSize(QtCore.QSize(1050, 550))
        self.graphic_ca_1d.setObjectName("graphic_ca_1d")
        self.graphic_ca_1d.setScene(self.scene_1d)
        self.formLayout.setWidget(2, QtWidgets.QFormLayout.LabelRole, self.graphic_ca_1d)
        self.horizontalLayout_4.addLayout(self.formLayout)
        self.tabWidget.addTab(self.OneDimensionalTab, "")
        self.TwoDimensionalTab = QtWidgets.QWidget()
        self.TwoDimensionalTab.setMinimumSize(QtCore.QSize(1350, 0))
        self.TwoDimensionalTab.setMaximumSize(QtCore.QSize(1350, 16777215))
        self.TwoDimensionalTab.setObjectName("TwoDimensionalTab")
        self.formLayoutWidget_2 = QtWidgets.QWidget(self.TwoDimensionalTab)
        self.formLayoutWidget_2.setGeometry(QtCore.QRect(0, -6, 1231, 1171))
        self.formLayoutWidget_2.setObjectName("formLayoutWidget_2")
        self.formLayout_2 = QtWidgets.QFormLayout(self.formLayoutWidget_2)
        self.formLayout_2.setContentsMargins(0, 0, 0, 0)
        self.formLayout_2.setObjectName("formLayout_2")
        self.scene_2d = QtWidgets.QGraphicsScene()
        self.horizontalLayout_7 = QtWidgets.QHBoxLayout()
        self.horizontalLayout_7.setObjectName("horizontalLayout_7")
        self.horizontalLayout_8 = QtWidgets.QHBoxLayout()
        self.horizontalLayout_8.setObjectName("horizontalLayout_8")
        self.width_layout_horizontal_3 = QtWidgets.QHBoxLayout()
        self.width_layout_horizontal_3.setObjectName("width_layout_horizontal_3")
        self.widthLabel_2D = QtWidgets.QLabel(self.formLayoutWidget_2)
        self.widthLabel_2D.setMinimumSize(QtCore.QSize(40, 0))
        self.widthLabel_2D.setMaximumSize(QtCore.QSize(40, 16777215))
        self.widthLabel_2D.setObjectName("widthLabel_2D")
        self.width_layout_horizontal_3.addWidget(self.widthLabel_2D)
        self.widthText_2D = QtWidgets.QTextEdit(self.formLayoutWidget_2)
        self.widthText_2D.setMinimumSize(QtCore.QSize(40, 30))
        self.widthText_2D.setMaximumSize(QtCore.QSize(40, 30))
        self.widthText_2D.setDocumentTitle("")
        self.widthText_2D.setObjectName("widthText_2D")
        self.width_layout_horizontal_3.addWidget(self.widthText_2D)
        self.iterationsLayout_3 = QtWidgets.QHBoxLayout()
        self.iterationsLayout_3.setObjectName("iterationsLayout_3")
        self.horizontalLayout_6 = QtWidgets.QHBoxLayout()
        self.horizontalLayout_6.setObjectName("horizontalLayout_6")
        self.heightLabel2D = QtWidgets.QLabel(self.formLayoutWidget_2)
        self.heightLabel2D.setObjectName("heightLabel2D")
        self.horizontalLayout_6.addWidget(self.heightLabel2D)
        self.heightText2D = QtWidgets.QTextEdit(self.formLayoutWidget_2)
        self.heightText2D.setMinimumSize(QtCore.QSize(40, 30))
        self.heightText2D.setMaximumSize(QtCore.QSize(40, 30))
        self.heightText2D.setObjectName("heightText2D")
        self.horizontalLayout_6.addWidget(self.heightText2D)
        self.iterationsLayout_3.addLayout(self.horizontalLayout_6)
        self.iterationsLabel_2D = QtWidgets.QLabel(self.formLayoutWidget_2)
        self.iterationsLabel_2D.setMinimumSize(QtCore.QSize(60, 0))
        self.iterationsLabel_2D.setMaximumSize(QtCore.QSize(60, 16777215))
        self.iterationsLabel_2D.setObjectName("iterationsLabel_2D")
        self.iterationsLayout_3.addWidget(self.iterationsLabel_2D)
        self.iterationsText_2D = QtWidgets.QTextEdit(self.formLayoutWidget_2)
        self.iterationsText_2D.setMinimumSize(QtCore.QSize(40, 30))
        self.iterationsText_2D.setMaximumSize(QtCore.QSize(40, 30))
        self.iterationsText_2D.setObjectName("iterationsText_2D")
        self.iterationsLayout_3.addWidget(self.iterationsText_2D)
        self.aliveLayout_3 = QtWidgets.QHBoxLayout()
        self.aliveLayout_3.setObjectName("aliveLayout_3")
        self.pattern_Label_2D = QtWidgets.QLabel(self.formLayoutWidget_2)
        self.pattern_Label_2D.setMinimumSize(QtCore.QSize(60, 0))
        self.pattern_Label_2D.setMaximumSize(QtCore.QSize(60, 16777215))
        self.pattern_Label_2D.setObjectName("pattern_Label_2D")
        self.aliveLayout_3.addWidget(self.pattern_Label_2D)
        self.pattern_Text_2D = QtWidgets.QTextEdit(self.formLayoutWidget_2)
        self.pattern_Text_2D.setMinimumSize(QtCore.QSize(100, 30))
        self.pattern_Text_2D.setMaximumSize(QtCore.QSize(100, 30))
        self.pattern_Text_2D.setObjectName("pattern_Text_2D")
        self.aliveLayout_3.addWidget(self.pattern_Text_2D)
        self.iterationsLayout_3.addLayout(self.aliveLayout_3)
        self.ruleLayout_3 = QtWidgets.QHBoxLayout()
        self.ruleLayout_3.setObjectName("ruleLayout_3")
        self.iterationsLayout_3.addLayout(self.ruleLayout_3)
        self.width_layout_horizontal_3.addLayout(self.iterationsLayout_3)
        self.horizontalLayout_8.addLayout(self.width_layout_horizontal_3)
        self.horizontalLayout_7.addLayout(self.horizontalLayout_8)
        self.initializeGameButton_2D = QtWidgets.QPushButton(self.formLayoutWidget_2)
        self.initializeGameButton_2D.setMinimumSize(QtCore.QSize(60, 0))
        self.initializeGameButton_2D.setMaximumSize(QtCore.QSize(60, 16777215))
        self.initializeGameButton_2D.setObjectName("initializeGameButton_2D")
        self.initializeGameButton_2D.clicked.connect(self.initialize_click_2d)

        self.horizontalLayout_7.addWidget(self.initializeGameButton_2D)
        self.beginGameButton_2D = QtWidgets.QPushButton(self.formLayoutWidget_2)
        self.beginGameButton_2D.setMinimumSize(QtCore.QSize(60, 0))
        self.beginGameButton_2D.setMaximumSize(QtCore.QSize(60, 16777215))
        self.beginGameButton_2D.setObjectName("beginGameButton_2D")
        self.beginGameButton_2D.clicked.connect(self.begin_game_2d)

        self.horizontalLayout_7.addWidget(self.beginGameButton_2D)
        self.restart_button_2d = QtWidgets.QPushButton(self.formLayoutWidget_2)
        self.restart_button_2d.setMinimumSize(QtCore.QSize(80, 0))
        self.restart_button_2d.setMaximumSize(QtCore.QSize(80, 16777215))
        self.restart_button_2d.setObjectName("restart_button_2d")
        self.restart_button_2d.clicked.connect(self.restart_plot_2d)
        self.horizontalLayout_7.addWidget(self.restart_button_2d)
        self.formLayout_2.setLayout(1, QtWidgets.QFormLayout.LabelRole, self.horizontalLayout_7)
        self.verticalLayout_6 = QtWidgets.QVBoxLayout()
        self.verticalLayout_6.setObjectName("verticalLayout_6")
        self.graphic_ca_2d = QtWidgets.QGraphicsView(self.formLayoutWidget_2)
        self.graphic_ca_2d.setMinimumSize(QtCore.QSize(700, 550))
        self.graphic_ca_2d.setMaximumSize(QtCore.QSize(700, 550))
        self.graphic_ca_2d.setObjectName("graphic_ca_2d")
        self.verticalLayout_6.addWidget(self.graphic_ca_2d)
        self.graphic_ca_2d.setScene(self.scene_2d)
        self.formLayout_2.setLayout(2, QtWidgets.QFormLayout.LabelRole, self.verticalLayout_6)
        self.manualInputTextArea_2D = QtWidgets.QPlainTextEdit(self.formLayoutWidget_2)
        self.manualInputTextArea_2D.setMinimumSize(QtCore.QSize(0, 550))
        self.manualInputTextArea_2D.setMaximumSize(QtCore.QSize(16777215, 450))
        self.manualInputTextArea_2D.setObjectName("manualInputTextArea_2D")
        self.formLayout_2.setWidget(2, QtWidgets.QFormLayout.FieldRole, self.manualInputTextArea_2D)
        self.tabWidget.addTab(self.TwoDimensionalTab, "")
        self.nucleation_tab = QtWidgets.QWidget()
        self.nucleation_tab.setObjectName("nucleation_tab")
        self.formLayoutWidget_3 = QtWidgets.QWidget(self.nucleation_tab)
        self.formLayoutWidget_3.setGeometry(QtCore.QRect(0, 0, 1231, 593))
        self.formLayoutWidget_3.setObjectName("formLayoutWidget_3")
        self.formLayout_3 = QtWidgets.QFormLayout(self.formLayoutWidget_3)
        self.formLayout_3.setContentsMargins(0, 0, 0, 0)
        self.formLayout_3.setObjectName("formLayout_3")
        self.horizontalLayout_16 = QtWidgets.QHBoxLayout()
        self.horizontalLayout_16.setObjectName("horizontalLayout_16")
        self.nucleation_scene = QtWidgets.QGraphicsScene()
        self.nucleation_graphic_ca_2d = QtWidgets.QGraphicsView(self.formLayoutWidget_3)
        self.nucleation_graphic_ca_2d.setMinimumSize(QtCore.QSize(700, 500))
        self.nucleation_graphic_ca_2d.setMaximumSize(QtCore.QSize(700, 450))
        self.nucleation_graphic_ca_2d.setObjectName("nucleation_graphic_ca_2d")
        self.horizontalLayout_16.addWidget(self.nucleation_graphic_ca_2d)
        self.nucleation_graphic_ca_2d.setScene(self.nucleation_scene)
        self.nucleation_manualInputTextArea_2D = QtWidgets.QPlainTextEdit(self.formLayoutWidget_3)
        self.nucleation_manualInputTextArea_2D.setMinimumSize(QtCore.QSize(0, 500))
        self.nucleation_manualInputTextArea_2D.setMaximumSize(QtCore.QSize(16777215, 450))
        self.nucleation_manualInputTextArea_2D.setObjectName("nucleation_manualInputTextArea_2D")
        self.horizontalLayout_16.addWidget(self.nucleation_manualInputTextArea_2D)
        self.formLayout_3.setLayout(3, QtWidgets.QFormLayout.SpanningRole, self.horizontalLayout_16)
        self.horizontalLayout_9 = QtWidgets.QHBoxLayout()
        self.horizontalLayout_9.setObjectName("horizontalLayout_9")
        self.horizontalLayout_10 = QtWidgets.QHBoxLayout()
        self.horizontalLayout_10.setObjectName("horizontalLayout_10")
        self.width_layout_horizontal_4 = QtWidgets.QHBoxLayout()
        self.width_layout_horizontal_4.setObjectName("width_layout_horizontal_4")
        self.nucleation_width_Label_2D = QtWidgets.QLabel(self.formLayoutWidget_3)
        self.nucleation_width_Label_2D.setMinimumSize(QtCore.QSize(30, 0))
        self.nucleation_width_Label_2D.setMaximumSize(QtCore.QSize(30, 16777215))
        self.nucleation_width_Label_2D.setObjectName("nucleation_width_Label_2D")
        self.width_layout_horizontal_4.addWidget(self.nucleation_width_Label_2D)
        self.nucleation_widthText_2D = QtWidgets.QTextEdit(self.formLayoutWidget_3)
        self.nucleation_widthText_2D.setMinimumSize(QtCore.QSize(35, 30))
        self.nucleation_widthText_2D.setMaximumSize(QtCore.QSize(35, 30))
        self.nucleation_widthText_2D.setDocumentTitle("")
        self.nucleation_widthText_2D.setObjectName("nucleation_widthText_2D")
        self.width_layout_horizontal_4.addWidget(self.nucleation_widthText_2D)
        self.nucleation_height_Label_2D = QtWidgets.QLabel(self.formLayoutWidget_3)
        self.nucleation_height_Label_2D.setMinimumSize(QtCore.QSize(35, 0))
        self.nucleation_height_Label_2D.setMaximumSize(QtCore.QSize(35, 16777215))
        self.nucleation_height_Label_2D.setObjectName("nucleation_height_Label_2D")
        self.width_layout_horizontal_4.addWidget(self.nucleation_height_Label_2D)
        self.nucleation_heightText2D = QtWidgets.QTextEdit(self.formLayoutWidget_3)
        self.nucleation_heightText2D.setMinimumSize(QtCore.QSize(35, 30))
        self.nucleation_heightText2D.setMaximumSize(QtCore.QSize(35, 30))
        self.nucleation_heightText2D.setObjectName("nucleation_heightText2D")
        self.width_layout_horizontal_4.addWidget(self.nucleation_heightText2D)
        self.nucleation_width_amount_label = QtWidgets.QLabel(self.formLayoutWidget_3)
        self.nucleation_width_amount_label.setMinimumSize(QtCore.QSize(30, 0))
        self.nucleation_width_amount_label.setMaximumSize(QtCore.QSize(31, 16777215))
        self.nucleation_width_amount_label.setObjectName("nucleation_width_amount_label")
        self.width_layout_horizontal_4.addWidget(self.nucleation_width_amount_label)
        self.nucleation_amount_width_text = QtWidgets.QTextEdit(self.formLayoutWidget_3)
        self.nucleation_amount_width_text.setMinimumSize(QtCore.QSize(30, 30))
        self.nucleation_amount_width_text.setMaximumSize(QtCore.QSize(30, 30))
        self.nucleation_amount_width_text.setObjectName("nucleation_amount_width_text")
        self.width_layout_horizontal_4.addWidget(self.nucleation_amount_width_text)
        self.nucleation_height_amount_label = QtWidgets.QLabel(self.formLayoutWidget_3)
        self.nucleation_height_amount_label.setMinimumSize(QtCore.QSize(30, 0))
        self.nucleation_height_amount_label.setMaximumSize(QtCore.QSize(30, 16777215))
        self.nucleation_height_amount_label.setObjectName("nucleation_height_amount_label")
        self.width_layout_horizontal_4.addWidget(self.nucleation_height_amount_label)
        self.nucleation_amount_height_text = QtWidgets.QTextEdit(self.formLayoutWidget_3)
        self.nucleation_amount_height_text.setMinimumSize(QtCore.QSize(30, 30))
        self.nucleation_amount_height_text.setMaximumSize(QtCore.QSize(30, 30))
        self.nucleation_amount_height_text.setObjectName("nucleation_amount_height_text")
        self.width_layout_horizontal_4.addWidget(self.nucleation_amount_height_text)
        self.nucleation_seeds_amount_label = QtWidgets.QLabel(self.formLayoutWidget_3)
        self.nucleation_seeds_amount_label.setMinimumSize(QtCore.QSize(39, 0))
        self.nucleation_seeds_amount_label.setMaximumSize(QtCore.QSize(40, 16777215))
        self.nucleation_seeds_amount_label.setSizeIncrement(QtCore.QSize(0, 0))
        self.nucleation_seeds_amount_label.setObjectName("nucleation_seeds_amount_label")
        self.width_layout_horizontal_4.addWidget(self.nucleation_seeds_amount_label)
        self.nucleation_amount_seeds_text_1 = QtWidgets.QTextEdit(self.formLayoutWidget_3)
        self.nucleation_amount_seeds_text_1.setMinimumSize(QtCore.QSize(30, 30))
        self.nucleation_amount_seeds_text_1.setMaximumSize(QtCore.QSize(30, 30))
        self.nucleation_amount_seeds_text_1.setObjectName("nucleation_amount_seeds_text_1")
        self.width_layout_horizontal_4.addWidget(self.nucleation_amount_seeds_text_1)
        self.nucleation_radius_label = QtWidgets.QLabel(self.formLayoutWidget_3)
        self.nucleation_radius_label.setMinimumSize(QtCore.QSize(40, 0))
        self.nucleation_radius_label.setMaximumSize(QtCore.QSize(40, 16777215))
        self.nucleation_radius_label.setObjectName("nucleation_radius_label")
        self.width_layout_horizontal_4.addWidget(self.nucleation_radius_label)
        self.nucleation_radius_text = QtWidgets.QTextEdit(self.formLayoutWidget_3)
        self.nucleation_radius_text.setMinimumSize(QtCore.QSize(30, 30))
        self.nucleation_radius_text.setMaximumSize(QtCore.QSize(30, 30))
        self.nucleation_radius_text.setObjectName("nucleation_radius_text")
        self.width_layout_horizontal_4.addWidget(self.nucleation_radius_text)
        self.nucleation_iterationsLabel_2D = QtWidgets.QLabel(self.formLayoutWidget_3)
        self.nucleation_iterationsLabel_2D.setMinimumSize(QtCore.QSize(60, 0))
        self.nucleation_iterationsLabel_2D.setMaximumSize(QtCore.QSize(60, 16777215))
        self.nucleation_iterationsLabel_2D.setObjectName("nucleation_iterationsLabel_2D")
        self.width_layout_horizontal_4.addWidget(self.nucleation_iterationsLabel_2D)
        self.nucleation_iterationsText_2D = QtWidgets.QTextEdit(self.formLayoutWidget_3)
        self.nucleation_iterationsText_2D.setMinimumSize(QtCore.QSize(35, 30))
        self.nucleation_iterationsText_2D.setMaximumSize(QtCore.QSize(35, 30))
        self.nucleation_iterationsText_2D.setObjectName("nucleation_iterationsText_2D")
        self.width_layout_horizontal_4.addWidget(self.nucleation_iterationsText_2D)
        self.nucleation_boundary_Label_2D_7 = QtWidgets.QLabel(self.formLayoutWidget_3)
        self.nucleation_boundary_Label_2D_7.setMinimumSize(QtCore.QSize(40, 0))
        self.nucleation_boundary_Label_2D_7.setMaximumSize(QtCore.QSize(40, 16777215))
        self.nucleation_boundary_Label_2D_7.setObjectName("nucleation_boundary_Label_2D_7")
        self.width_layout_horizontal_4.addWidget(self.nucleation_boundary_Label_2D_7)
        self.nucleation_boundary_Text_2D_7 = QtWidgets.QTextEdit(self.formLayoutWidget_3)
        self.nucleation_boundary_Text_2D_7.setMinimumSize(QtCore.QSize(65, 30))
        self.nucleation_boundary_Text_2D_7.setMaximumSize(QtCore.QSize(65, 30))
        self.nucleation_boundary_Text_2D_7.setObjectName("nucleation_boundary_Text_2D_7")
        self.width_layout_horizontal_4.addWidget(self.nucleation_boundary_Text_2D_7)
        self.nucleation_pattern_Label_2D = QtWidgets.QLabel(self.formLayoutWidget_3)
        self.nucleation_pattern_Label_2D.setMinimumSize(QtCore.QSize(60, 0))
        self.nucleation_pattern_Label_2D.setMaximumSize(QtCore.QSize(60, 16777215))
        self.nucleation_pattern_Label_2D.setObjectName("nucleation_pattern_Label_2D")
        self.width_layout_horizontal_4.addWidget(self.nucleation_pattern_Label_2D)
        self.nucleation_pattern_Text_2D = QtWidgets.QTextEdit(self.formLayoutWidget_3)
        self.nucleation_pattern_Text_2D.setMinimumSize(QtCore.QSize(90, 30))
        self.nucleation_pattern_Text_2D.setMaximumSize(QtCore.QSize(90, 30))
        self.nucleation_pattern_Text_2D.setObjectName("nucleation_pattern_Text_2D")
        self.width_layout_horizontal_4.addWidget(self.nucleation_pattern_Text_2D)
        self.horizontalLayout_10.addLayout(self.width_layout_horizontal_4)
        self.horizontalLayout_9.addLayout(self.horizontalLayout_10)
        self.nucleation_initializeGameButton_2D = QtWidgets.QPushButton(self.formLayoutWidget_3)
        self.nucleation_initializeGameButton_2D.setMinimumSize(QtCore.QSize(55, 0))
        self.nucleation_initializeGameButton_2D.setMaximumSize(QtCore.QSize(55, 16777215))
        self.nucleation_initializeGameButton_2D.setObjectName("nucleation_initializeGameButton_2D")
        self.horizontalLayout_9.addWidget(self.nucleation_initializeGameButton_2D)
        self.nucleation_beginGameButton_2D_2 = QtWidgets.QPushButton(self.formLayoutWidget_3)
        self.nucleation_beginGameButton_2D_2.setMinimumSize(QtCore.QSize(45, 0))
        self.nucleation_beginGameButton_2D_2.setMaximumSize(QtCore.QSize(45, 16777215))
        self.nucleation_beginGameButton_2D_2.setObjectName("nucleation_beginGameButton_2D_2")
        self.horizontalLayout_9.addWidget(self.nucleation_beginGameButton_2D_2)
        self.nucleation_restart_button_2d = QtWidgets.QPushButton(self.formLayoutWidget_3)
        self.nucleation_restart_button_2d.setMinimumSize(QtCore.QSize(55, 0))
        self.nucleation_restart_button_2d.setMaximumSize(QtCore.QSize(55, 16777215))
        self.nucleation_restart_button_2d.setObjectName("nucleation_restart_button_2d")
        self.horizontalLayout_9.addWidget(self.nucleation_restart_button_2d)
        self.formLayout_3.setLayout(1, QtWidgets.QFormLayout.SpanningRole, self.horizontalLayout_9)
        self.horizontalLayout = QtWidgets.QHBoxLayout()
        self.horizontalLayout.setObjectName("horizontalLayout")
        self.nucleation_neighbours_label = QtWidgets.QLabel(self.formLayoutWidget_3)
        self.nucleation_neighbours_label.setObjectName("nucleation_neighbours_label")
        self.horizontalLayout.addWidget(self.nucleation_neighbours_label)
        self.nucleation_neighbours_text = QtWidgets.QTextEdit(self.formLayoutWidget_3)
        self.nucleation_neighbours_text.setMinimumSize(QtCore.QSize(100, 30))
        self.nucleation_neighbours_text.setMaximumSize(QtCore.QSize(80, 30))
        self.nucleation_neighbours_text.setObjectName("nucleation_neighbours_text")
        self.horizontalLayout.addWidget(self.nucleation_neighbours_text)
        self.formLayout_3.setLayout(2, QtWidgets.QFormLayout.LabelRole, self.horizontalLayout)
        self.tabWidget.addTab(self.nucleation_tab, "")
        self.mc = QtWidgets.QWidget()
        self.mc.setMinimumSize(QtCore.QSize(0, 600))
        self.mc.setMaximumSize(QtCore.QSize(16777215, 600))
        self.mc.setObjectName("mc")
        self.formLayoutWidget_4 = QtWidgets.QWidget(self.mc)
        self.formLayoutWidget_4.setGeometry(QtCore.QRect(0, 0, 1231, 661))
        self.formLayoutWidget_4.setObjectName("formLayoutWidget_4")
        self.formLayout_4 = QtWidgets.QFormLayout(self.formLayoutWidget_4)
        self.formLayout_4.setContentsMargins(0, 0, 0, 0)
        self.formLayout_4.setObjectName("formLayout_4")
        self.horizontalLayout_11 = QtWidgets.QHBoxLayout()
        self.horizontalLayout_11.setObjectName("horizontalLayout_11")
        self.horizontalLayout_12 = QtWidgets.QHBoxLayout()
        self.horizontalLayout_12.setObjectName("horizontalLayout_12")
        self.width_layout_horizontal_5 = QtWidgets.QHBoxLayout()
        self.width_layout_horizontal_5.setObjectName("width_layout_horizontal_5")
        self.mc_kt_label = QtWidgets.QLabel(self.formLayoutWidget_4)
        self.mc_kt_label.setMinimumSize(QtCore.QSize(40, 0))
        self.mc_kt_label.setMaximumSize(QtCore.QSize(40, 16777215))
        self.mc_kt_label.setObjectName("mc_kt_label")
        self.width_layout_horizontal_5.addWidget(self.mc_kt_label)
        self.mc_kt_text = QtWidgets.QTextEdit(self.formLayoutWidget_4)
        self.mc_kt_text.setMinimumSize(QtCore.QSize(30, 30))
        self.mc_kt_text.setMaximumSize(QtCore.QSize(30, 30))
        self.mc_kt_text.setObjectName("mc_kt_text")
        self.width_layout_horizontal_5.addWidget(self.mc_kt_text)
        self.mc_iterations_label = QtWidgets.QLabel(self.formLayoutWidget_4)
        self.mc_iterations_label.setMinimumSize(QtCore.QSize(60, 0))
        self.mc_iterations_label.setMaximumSize(QtCore.QSize(60, 16777215))
        self.mc_iterations_label.setObjectName("mc_iterations_label")
        self.width_layout_horizontal_5.addWidget(self.mc_iterations_label)
        self.mc_iteration_text = QtWidgets.QTextEdit(self.formLayoutWidget_4)
        self.mc_iteration_text.setMinimumSize(QtCore.QSize(35, 30))
        self.mc_iteration_text.setMaximumSize(QtCore.QSize(35, 30))
        self.mc_iteration_text.setObjectName("mc_iteration_text")
        self.width_layout_horizontal_5.addWidget(self.mc_iteration_text)
        self.mc_bound_label = QtWidgets.QLabel(self.formLayoutWidget_4)
        self.mc_bound_label.setMinimumSize(QtCore.QSize(40, 0))
        self.mc_bound_label.setMaximumSize(QtCore.QSize(40, 16777215))
        self.mc_bound_label.setObjectName("mc_bound_label")
        self.width_layout_horizontal_5.addWidget(self.mc_bound_label)
        self.mc_bound_periodical_text = QtWidgets.QTextEdit(self.formLayoutWidget_4)
        self.mc_bound_periodical_text.setMinimumSize(QtCore.QSize(65, 30))
        self.mc_bound_periodical_text.setMaximumSize(QtCore.QSize(65, 30))
        self.mc_bound_periodical_text.setObjectName("mc_bound_periodical_text")
        self.width_layout_horizontal_5.addWidget(self.mc_bound_periodical_text)
        self.mc_neighbours_label = QtWidgets.QLabel(self.formLayoutWidget_4)
        self.mc_neighbours_label.setMinimumSize(QtCore.QSize(80, 30))
        self.mc_neighbours_label.setMaximumSize(QtCore.QSize(80, 30))
        self.mc_neighbours_label.setObjectName("mc_neighbours_label")
        self.width_layout_horizontal_5.addWidget(self.mc_neighbours_label)
        self.mc_neighbours_text = QtWidgets.QTextEdit(self.formLayoutWidget_4)
        self.mc_neighbours_text.setMinimumSize(QtCore.QSize(80, 22))
        self.mc_neighbours_text.setMaximumSize(QtCore.QSize(80, 30))
        self.mc_neighbours_text.setObjectName("mc_neighbours_text")
        self.width_layout_horizontal_5.addWidget(self.mc_neighbours_text)
        self.Mc_radius_label = QtWidgets.QLabel(self.formLayoutWidget_4)
        self.Mc_radius_label.setMinimumSize(QtCore.QSize(100, 30))
        self.Mc_radius_label.setMaximumSize(QtCore.QSize(100, 30))
        self.Mc_radius_label.setObjectName("Mc_radius_label")
        self.width_layout_horizontal_5.addWidget(self.Mc_radius_label)
        self.Mc_Radius_text = QtWidgets.QPlainTextEdit(self.formLayoutWidget_4)
        self.Mc_Radius_text.setMinimumSize(QtCore.QSize(100, 30))
        self.Mc_Radius_text.setMaximumSize(QtCore.QSize(100, 30))
        self.Mc_Radius_text.setObjectName("Mc_Radius_text")
        self.width_layout_horizontal_5.addWidget(self.Mc_Radius_text)
        self.horizontalLayout_12.addLayout(self.width_layout_horizontal_5)
        self.horizontalLayout_11.addLayout(self.horizontalLayout_12)
        self.mc_initializeGameButton_2D = QtWidgets.QPushButton(self.formLayoutWidget_4)
        self.mc_initializeGameButton_2D.setMinimumSize(QtCore.QSize(55, 0))
        self.mc_initializeGameButton_2D.setMaximumSize(QtCore.QSize(55, 16777215))
        self.mc_initializeGameButton_2D.setObjectName("mc_initializeGameButton_2D")
#        self.mc_initializeGameButton_2D.clicked.connect(self.mc_initialize_parameters)

        self.horizontalLayout_11.addWidget(self.mc_initializeGameButton_2D)
        self.mc_beginGameButton_2D_3 = QtWidgets.QPushButton(self.formLayoutWidget_4)
        self.mc_beginGameButton_2D_3.setMinimumSize(QtCore.QSize(45, 0))
        self.mc_beginGameButton_2D_3.setMaximumSize(QtCore.QSize(45, 16777215))
        self.mc_beginGameButton_2D_3.setObjectName("mc_beginGameButton_2D_3")
#        self.mc_beginGameButton_2D_3.clicked.connect(self.mc_begin_process)

        self.horizontalLayout_11.addWidget(self.mc_beginGameButton_2D_3)
        self.mc_restart_button_2d_2 = QtWidgets.QPushButton(self.formLayoutWidget_4)
        self.mc_restart_button_2d_2.setMinimumSize(QtCore.QSize(55, 0))
        self.mc_restart_button_2d_2.setMaximumSize(QtCore.QSize(55, 16777215))
        self.mc_restart_button_2d_2.setObjectName("mc_restart_button_2d_2")
 #       self.mc_restart_button_2d_2.clicked.connect(self.mc_restart_process)

        self.horizontalLayout_11.addWidget(self.mc_restart_button_2d_2)
        self.formLayout_4.setLayout(1, QtWidgets.QFormLayout.SpanningRole, self.horizontalLayout_11)
        self.horizontalLayout_17 = QtWidgets.QHBoxLayout()
        self.horizontalLayout_17.setObjectName("horizontalLayout_17")
        self.mc_graphic_nucleation = QtWidgets.QGraphicsView(self.formLayoutWidget_4)
        self.mc_graphic_nucleation.setMinimumSize(QtCore.QSize(600, 550))
        self.mc_graphic_nucleation.setMaximumSize(QtCore.QSize(600, 550))
        self.mc_graphic_nucleation.setObjectName("mc_graphic_nucleation")
        self.horizontalLayout_17.addWidget(self.mc_graphic_nucleation)
        self.mc_graphic_energy = QtWidgets.QGraphicsView(self.formLayoutWidget_4)
        self.mc_graphic_energy.setMinimumSize(QtCore.QSize(600, 550))
        self.mc_graphic_energy.setMaximumSize(QtCore.QSize(600, 550))
        self.mc_graphic_energy.setObjectName("mc_graphic_energy")
        self.horizontalLayout_17.addWidget(self.mc_graphic_energy)
        self.formLayout_4.setLayout(2, QtWidgets.QFormLayout.SpanningRole, self.horizontalLayout_17)
        self.tabWidget.addTab(self.mc, "")
        self.mode_menu.addWidget(self.tabWidget)

        self.retranslateUi(Dialog)
        self.tabWidget.setCurrentIndex(3)
        QtCore.QMetaObject.connectSlotsByName(Dialog)

    def retranslateUi(self, Dialog):
        _translate = QtCore.QCoreApplication.translate
        Dialog.setWindowTitle(_translate("Dialog", "Cellular-Automata:Mateusz Tchorek"))
        self.widthLabel1D.setText(_translate("Dialog", "Width"))
        self.widthText1D.setPlaceholderText(_translate("Dialog", str(self.width)))
        self.iterationsLabel1D.setText(_translate("Dialog", "Iterations"))
        self.iterationsText1D.setPlaceholderText(_translate("Dialog", str(self.iterations)))
        self.aliveCellsArrayLabel1D.setText(_translate("Dialog", "Set cell/s alive"))
        self.string_of_alive_cells = str(self.alive_cels_numbers)
        self.aliveCellsText1D.setPlaceholderText(
            _translate("Dialog", self.string_of_alive_cells[1:len(self.string_of_alive_cells) - 1]))
        self.ruleLabel1D.setText(_translate("Dialog", "Rule 1-255"))
        self.ruleText1D.setPlaceholderText(_translate("Dialog", str(self.rule)))
        self.initializeGameButton1D.setText(_translate("Dialog", "Initialize"))
        self.beginGameButton1D.setText(_translate("Dialog", "Start"))
        self.restart_button_1d.setText(_translate("Dialog", "restart"))
        self.tabWidget.setTabText(self.tabWidget.indexOf(self.OneDimensionalTab),
                                  _translate("Dialog", "OneDimensional"))
        self.widthLabel_2D.setText(_translate("Dialog", "Width"))
        self.manualInputTextArea_2D.appendPlainText(
            _translate("Dialog", str(self.draw_manual_array_on_textarea())[1:-1]))
        self.nucleation_manualInputTextArea_2D.appendPlainText(
            _translate("Dialog", str(self.nucleation_draw_manual_array_on_textarea())[1:-1]))
        self.widthText_2D.setPlaceholderText(_translate("Dialog", str(self.width_2d)))
        self.heightLabel2D.setText(_translate("Dialog", "Height"))
        self.heightText2D.setPlaceholderText(_translate("Dialog", str(self.height_2d)))
        self.iterationsLabel_2D.setText(_translate("Dialog", "Iterations"))
        self.iterationsText_2D.setPlaceholderText(_translate("Dialog", str(self.iterations_2d)))
        self.pattern_Label_2D.setText(_translate("Dialog", "Pattern"))
        self.pattern_Text_2D.setPlaceholderText(_translate("Dialog", str(self.pattern_2d)))
        self.initializeGameButton_2D.setText(_translate("Dialog", "Initialize"))
        self.beginGameButton_2D.setText(_translate("Dialog", "Start"))
        self.restart_button_2d.setText(_translate("Dialog", "restart"))
        self.tabWidget.setTabText(self.tabWidget.indexOf(self.TwoDimensionalTab),
                                  _translate("Dialog", "TwoDimensional"))
        self.nucleation_width_Label_2D.setText(_translate("Dialog", "Width"))
        self.nucleation_widthText_2D.setPlaceholderText(_translate("Dialog", str(self.nucleation_width_2d)))
        self.nucleation_height_Label_2D.setText(_translate("Dialog", "Height"))
        self.nucleation_heightText2D.setPlaceholderText(_translate("Dialog", str(self.nucleation_height_2d)))
        self.nucleation_width_amount_label.setText(_translate("Dialog", "A_W"))
        self.nucleation_amount_width_text.setPlaceholderText(_translate("Dialog", str(self.nucleation_user_width)))
        self.nucleation_height_amount_label.setText(_translate("Dialog", "A_H"))
        self.nucleation_amount_height_text.setPlaceholderText(_translate("Dialog", str(self.nucleation_user_height)))
        self.nucleation_seeds_amount_label.setText(_translate("Dialog", "Seeds"))
        self.nucleation_amount_seeds_text_1.setPlaceholderText(_translate("Dialog", str(self.nucleation_seeds_amount)))
        self.nucleation_radius_label.setText(_translate("Dialog", "Radius"))
        self.nucleation_radius_text.setPlaceholderText(_translate("Dialog", str(self.nucleation_radius)))
        self.nucleation_iterationsLabel_2D.setText(_translate("Dialog", "Iterations"))
        self.nucleation_iterationsText_2D.setPlaceholderText(_translate("Dialog", str(self.nucleation_iterations_2d)))
        self.nucleation_boundary_Label_2D_7.setText(_translate("Dialog", "Boundary"))
        self.nucleation_boundary_Text_2D_7.setPlaceholderText(_translate("Dialog", str(self.nucleation_boundary_conditions)))
        self.nucleation_pattern_Label_2D.setText(_translate("Dialog", "Nucleation"))
        self.nucleation_pattern_Text_2D.setPlaceholderText(_translate("Dialog", str(self.nucleation_pattern_2d)))
        self.nucleation_initializeGameButton_2D.setText(_translate("Dialog", "Initialize"))
        self.nucleation_initializeGameButton_2D.clicked.connect(self.initialize_nucleation_parameters)
        self.nucleation_beginGameButton_2D_2.setText(_translate("Dialog", "Start"))
        self.nucleation_beginGameButton_2D_2.clicked.connect(self.begin_nucleation)
        self.nucleation_restart_button_2d.setText(_translate("Dialog", "restart"))
        self.nucleation_restart_button_2d.clicked.connect(self.restart_nucleation)
        self.nucleation_neighbours_label.setText(_translate("Dialog", "Neighbours"))
        self.nucleation_neighbours_text.setPlaceholderText(_translate("Dialog", str(self.nucleation_neighbours_type)))
        self.tabWidget.setTabText(self.tabWidget.indexOf(self.nucleation_tab), _translate("Dialog", "Nucleation"))
        self.mc_kt_label.setText(_translate("Dialog", "kt"))
        self.mc_kt_text.setPlaceholderText(_translate("Dialog", str(self.MonteCarloObj.return_kt())))
        self.mc_iterations_label.setText(_translate("Dialog", "Iterations"))
        self.mc_iteration_text.setPlaceholderText(_translate("Dialog", str(self.MonteCarloObj.return_iterations())))
        self.mc_bound_label.setText(_translate("Dialog", "Bound"))
        if self.MonteCarloObj.return_periodical():
            self.mc_bound_periodical_text.setPlaceholderText(_translate("Diaglog","periodical"))
        else:
            self.mc_bound_periodical_text.setPlaceholderText(_translate("Dialog", "absorbing"))

        self.mc_neighbours_label.setText(_translate("Dialog", "Neighbours"))
        self.mc_neighbours_text.setPlaceholderText(_translate("Dialog", str(self.MonteCarloObj.return_neighbour_pattern())))
        self.Mc_radius_label.setText(_translate("Dialog", "Radius"))
        self.Mc_Radius_text.setPlaceholderText(_translate("Dialog", "5"))
        self.mc_initializeGameButton_2D.setText(_translate("Dialog", "Initialize"))
        self.mc_beginGameButton_2D_3.setText(_translate("Dialog", "Start"))
        self.mc_restart_button_2d_2.setText(_translate("Dialog", "restart"))
        self.tabWidget.setTabText(self.tabWidget.indexOf(self.mc), _translate("Dialog", "MC"))

    @pyqtSlot()
    def mc_initialize_parameters(self):
        _translate = QtCore.QCoreApplication.translate
        if str(self.mc_neighbours_text.toPlainText()) != "":
            if str(self.mc_neighbours_text.toPlainText()) in self.NucleationObj.return_neighbour_array():
                pattern_changed = True
                self.nucleation_neighbours_type = str(self.nucleation_neighbours_text.toPlainText())
                self.nucleation_neighbours_text.setPlaceholderText(
                    _translate("Dialog", str(self.nucleation_neighbours_type)))
                if self.nucleation_neighbours_type == "Pentagonal":
                    pentagonal_patterns = ["PentagonalLeft", "PentagonalRight", "PentagonalDown", "PentagonalUp"]
                    self.nucleation_neighbours_type = random.choice(pentagonal_patterns)
                    print(self.nucleation_neighbours_type)
                if self.nucleation_neighbours_type == "Hexagonal":
                    hexagonal_patterns = ["HexagonalR", "HexagonalL"]
                    self.nucleation_neighbours_type = random.choice(hexagonal_patterns)
                    print(self.nucleation_neighbours_type)
                if str(self.nucleation_neighbours_text.toPlainText()) == "Radius" and str(
                        self.nucleation_radius_text.toPlainText()) != "" and str(
                        self.nucleation_radius_text.toPlainText()).isdigit():
                    self.nucleation_neighbour_radius = int(self.nucleation_radius_text.toPlainText())
                    self.nucleation_radius_text.clear()

                if str(self.nucleation_pattern_Text_2D.toPlainText()) == "radius" and str(
                        self.nucleation_radius_text.toPlainText()) != "" and str(
                    self.nucleation_radius_text.toPlainText()).isdigit():
                    self.nucleation_radius = int(self.nucleation_radius_text.toPlainText())
                    self.nucleation_radius_text.clear()

                if str(self.nucleation_neighbours_text.toPlainText()) == "Radius" and str(self.nucleation_radius_text.toPlainText() == ""):
                    self.nucleation_neighbour_radius = self.NucleationObj.return_neighbour_radius()
                    self.nucleation_radius_text.clear()

                if str(self.nucleation_pattern_Text_2D.toPlainText()) == "radius" and str(
                        self.nucleation_radius_text.toPlainText() == ""):
                    self.nucleation_radius = self.NucleationObj.return_radius()
                    self.nucleation_radius_text.clear()

                self.nucleation_neighbours_text.clear()
            else:
                self.nucleation_neighbours_text.clear()
                self.nucleation_seeds_amount = int(self.nucleation_radius_text.toPlainText())
                self.nucleation_radius_text.setPlaceholderText(
                    _translate("Dialog", str(self.nucleation_seeds_amount)))
                self.nucleation_radius_text.clear()

        if str(self.nucleation_boundary_Text_2D_7.toPlainText()) != "": # boundary conditions = periodical
            self.nucleation_boundary_conditions = str(self.nucleation_boundary_Text_2D_7.toPlainText())
            if self.nucleation_boundary_conditions.lower() == "periodical":
                self.nucleation_boundary_conditions = "periodical"
            else:
                self.nucleation_boundary_conditions = "absorbing"
            self.nucleation_boundary_Text_2D_7.setPlaceholderText(_translate("Dialog", str(self.nucleation_boundary_conditions)))
            self.nucleation_boundary_Text_2D_7.clear()

    @pyqtSlot()
    def restart_plot(self):
        self.first_time = True
        self.scene_1d.clear()
        self.row = 0
        self.FirstDimensionObj.restart_grid()

    @pyqtSlot()
    def initialize_click(self):
        _translate = QtCore.QCoreApplication.translate
        changed = False
        if str(self.widthText1D.toPlainText()) != "" and str(self.widthText1D.toPlainText()).isdigit():
            if any(element > int(self.widthText1D.toPlainText()) for element in self.alive_cels_numbers):
                self.widthText1D.clear()
            else:
                self.width = int(self.widthText1D.toPlainText())
                self.widthText1D.setPlaceholderText(_translate("Dialog", str(self.width)))
                self.widthText1D.clear()
            changed = True
        if str(self.iterationsText1D.toPlainText()) != "" and str(self.iterationsText1D.toPlainText()).isdigit():
            self.iterations = int(self.iterationsText1D.toPlainText())
            self.iterationsText1D.setPlaceholderText(_translate("Dialog", str(self.iterations)))
            self.iterationsText1D.clear()
            changed = True
        if str(self.aliveCellsText1D.toPlainText()) != "":
            self.alive_cels_numbers = [int(element) for element in self.aliveCellsText1D.toPlainText().split(",") if
                                       int(element) <= (self.width - 1) and element.isdigit()]
            self.string_of_alive_cells = str(self.alive_cels_numbers)
            self.aliveCellsText1D.setPlaceholderText(
                _translate("Dialog", self.string_of_alive_cells[1:len(self.string_of_alive_cells) - 1]))
            self.aliveCellsText1D.clear()
            changed = True
        if str(self.ruleText1D.toPlainText()) != "" and str(self.ruleText1D.toPlainText()).isdigit():
            self.rule = int(self.ruleText1D.toPlainText())
            self.ruleText1D.setPlaceholderText(_translate("Dialog", str(self.rule)))
            self.ruleText1D.clear()
            changed = True

        self.FirstDimensionObj.set_parameters(self.width, self.iterations, self.rule, self.alive_cels_numbers)

    @pyqtSlot()
    def begin_game(self):
        if self.first_time == False:
            self.mark_previous_iteration(self.previous_iteration_array_2d, self.previous_counter, self.previous_row)
        counter = self.iterations

        self.previous_iteration_array_2d = []
        self.previous_counter = self.iterations
        self.previous_row = self.row

        self.side = 3
        while counter > 0:
            self.current_iteration_array = self.FirstDimensionObj.single_iteration()
            self.previous_iteration_array_2d.append(self.current_iteration_array)

            for index in range(len(self.current_iteration_array)):
                rectangle = QtCore.QRectF(QtCore.QPointF(index * self.side, self.row * self.side),
                                          QtCore.QSizeF(self.side, self.side))

                if self.current_iteration_array[index].is_alive:
                    self.scene_1d.addRect(rectangle, self.red_pen)
                else:
                    line_left = QtCore.QLineF(index * self.side, self.row * self.side, index * self.side,
                                              self.row * self.side + self.side)
                    line_down = QtCore.QLineF(index * self.side, self.row * self.side + self.side,
                                              index * self.side + self.side, self.row * self.side + self.side)
                    line_right = QtCore.QLineF(index * self.side + self.side, self.row * self.side,
                                               index * self.side + self.side, self.row * self.side + self.side)
                    if self.current_iteration_array[index - 1].is_alive:
                        self.scene_1d.addLine(line_left, self.red_pen)
                    else:
                        self.scene_1d.addLine(line_left, self.green_pen)
                    self.scene_1d.addLine(line_down, self.green_pen)
                    self.scene_1d.addLine(line_right, self.green_pen)

            counter -= 1
            self.row += 1
        self.first_time = False

    def mark_previous_iteration(self, previous_iteration_array, previous_counter, previous_row):
        for array in previous_iteration_array:
            for index in range(len(array)):
                rectangle = QtCore.QRectF(QtCore.QPointF(index * self.side, previous_row * self.side),
                                          QtCore.QSizeF(self.side, self.side))

                if array[index].is_alive:
                    self.scene_1d.addRect(rectangle, self.blue_pen)

                else:
                    line_left = QtCore.QLineF(index * self.side, previous_row * self.side, index * self.side,
                                              previous_row * self.side + self.side)
                    line_down = QtCore.QLineF(index * self.side, previous_row * self.side + self.side,
                                              index * self.side + self.side, previous_row * self.side + self.side)
                    line_right = QtCore.QLineF(index * self.side + self.side, previous_row * self.side,
                                               index * self.side + self.side, previous_row * self.side + self.side)
                    if array[index - 1].is_alive:
                        self.scene_1d.addLine(line_left, self.blue_pen)
                    else:
                        self.scene_1d.addLine(line_left, self.green_pen)
                    self.scene_1d.addLine(line_down, self.green_pen)
                    self.scene_1d.addLine(line_right, self.green_pen)

            previous_counter -= 1
            previous_row += 1

    @pyqtSlot()
    def restart_plot_2d(self):
        self.first_time_2d = True
        self.scene_2d.clear()
        self.row_2d_counter = 0
        self.SecondDimensionObj.restart_grid()

    @pyqtSlot()
    def initialize_click_2d(self):
        _translate = QtCore.QCoreApplication.translate
        width_or_height_changed = False
        pattern_changed = False

        if str(self.widthText_2D.toPlainText()) != "" and str(self.widthText_2D.toPlainText()).isdigit():
            if self.SecondDimensionObj.return_pattern_width() > int(self.widthText_2D.toPlainText()):
                self.widthText_2D.clear()
            else:
                self.width_2d = int(self.widthText_2D.toPlainText())
                self.widthText_2D.setPlaceholderText(_translate("Dialog", str(self.width_2d)))
                self.widthText_2D.clear()
                width_or_height_changed = True
        if str(self.heightText2D.toPlainText()) != "" and str(self.heightText2D.toPlainText()).isdigit():
            if self.SecondDimensionObj.return_pattern_height() > int(self.heightText2D.toPlainText()):
                self.heightText2D.clear()
            else:
                self.height_2d = int(self.heightText2D.toPlainText())
                self.heightText2D.setPlaceholderText(_translate("Dialog", str(self.height_2d)))
                self.heightText2D.clear()
                width_or_height_changed = True

        if str(self.iterationsText_2D.toPlainText()) != "" and str(self.iterationsText_2D.toPlainText()).isdigit():
            self.iterations_2d = int(self.iterationsText_2D.toPlainText())
            self.iterationsText_2D.setPlaceholderText(_translate("Dialog", str(self.iterations_2d)))
            self.iterationsText_2D.clear()

        if str(self.pattern_Text_2D.toPlainText()) != "":
            pattern_changed = True
            self.pattern_2d = str(self.pattern_Text_2D.toPlainText())
            self.pattern_Text_2D.setPlaceholderText(_translate("Dialog", str(self.pattern_2d)))
            self.pattern_Text_2D.clear()

        self.SecondDimensionObj.set_parameters(self.width_2d, self.iterations_2d, self.height_2d, self.pattern_2d)
        if self.pattern_2d == "manual":
            self.manualInputTextArea_2D.clear()
            self.initial_manual_array_2d = self.SecondDimensionObj.return_initial_array()
            self.manualInputTextArea_2D.appendPlainText(
                _translate("Dialog", str(self.draw_manual_array_on_textarea())[1:-1]))

        if width_or_height_changed:
            self.manualInputTextArea_2D.clear()
            self.initial_manual_array_2d = self.SecondDimensionObj.return_current_array()
        if width_or_height_changed or pattern_changed:
            self.settings_has_changed = True

    def draw_empty_board_2d(self):
        self.width_draw_2d = 3
        self.height_draw_2d = 3

        for row in range(self.height_2d):
            for column in range(self.width_2d):
                rectangle = QtCore.QRectF(QtCore.QPointF(column * self.width_draw_2d, row * self.height_draw_2d),
                                          QtCore.QSizeF(self.width_draw_2d, self.height_draw_2d))
                self.scene_2d.addRect(rectangle, self.green_pen)

    def draw_board_2d(self,input_array):
        self.width_draw_2d = 3
        self.height_draw_2d = 3
        for row in range(len(input_array)):
            for column in range(len(input_array[row])):
                rectangle = QtCore.QRectF(QtCore.QPointF(column * self.width_draw_2d, row * self.height_draw_2d),
                                          QtCore.QSizeF(self.width_draw_2d, self.height_draw_2d))
                if input_array[row][column].is_alive==True:
                    self.scene_2d.addRect(rectangle, self.red_pen)

    def compare_current_step_with_previous(self):
        are_they_equal = True
        for row in range(self.nucleation_height_2d):
            for column in range(self.nucleation_width_2d):
                if self.nucleation_previous_iteration_array_2d[row][column] != self.nucleation_current_iteration_array_2d[row][column]:
                    are_they_equal = False
        return are_they_equal

    @pyqtSlot()
    def begin_game_2d(self):
        _translate = QtCore.QCoreApplication.translate
        self.counter_2d = 0
        self.previous_iteration_array_2d = self.SecondDimensionObj.return_previous_array()
        self.current_iteration_array_2d = self.SecondDimensionObj.return_current_array()
        self.scene_2d.clear()
        if self.pattern_2d == "manual":
            self.manual_array_text_backup = str(self.draw_manual_array_on_textarea())[1:-1]
        self.draw_empty_board_2d()
        self.draw_board_2d(self.SecondDimensionObj.next_iteration())

        while self.counter_2d < self.iterations_2d:
            QtTest.QTest.qWait(150)
            self.draw_empty_board_2d()
            self.previous_iteration_array_2d = self.current_iteration_array_2d
            self.current_iteration_array_2d = self.SecondDimensionObj.next_iteration()
            self.draw_board_2d(self.current_iteration_array_2d)
            self.counter_2d+=1

        if self.pattern_2d == "manual":
            self.initial_manual_array_2d = self.current_iteration_array_2d
            self.read_manual_array_from_textarea()
            self.manualInputTextArea_2D.clear()
            self.manualInputTextArea_2D.appendPlainText(
                _translate("Dialog", str(self.draw_manual_array_on_textarea())[1:-1]))

    def draw_manual_array_on_textarea(self):
        draw_array = []
        for row in range(self.height_2d):
            row_array = ''

            for column in range(self.width_2d):

                if self.initial_manual_array_2d[row][column].is_alive:
                    row_array+='1'
                else:
                    row_array+='0'

                if column == self.width_2d-1:
                    row_array+=''
                else:
                    row_array+=','

            draw_array.append(row_array)
        return draw_array

    def read_manual_array_from_textarea(self):
        if self.settings_has_changed:
            self.settings_has_changed = False
            return
        else:
            current_text =  str(self.manualInputTextArea_2D.toPlainText())
            current_text_modified = current_text.replace(" ","").replace("',","S").replace("'","").split("S")
            if current_text != self.manual_array_text_backup:
                user_edited_array = []
                for row in range(len(current_text_modified)):
                    row_array = []
                    splitted_array = current_text_modified[row].split(",")
                    for column in range(len(current_text_modified[row].split(","))):
                        #print(splitted_array[column],end="")
                        if splitted_array[column] == "1":
                            #print("im adding alive cell")
                            new_cell = Cell()
                            new_cell.is_alive = True
                            row_array.append(new_cell)
                        if splitted_array[column] == "0":
                            row_array.append(Cell(False))
                    #print(row_array)
                    user_edited_array.append(row_array)
                self.SecondDimensionObj.set_current_array(user_edited_array)

    def initialize_nucleation_parameters(self):
        _translate = QtCore.QCoreApplication.translate
        width_or_height_changed = False

        if str(self.nucleation_widthText_2D.toPlainText()) != "" and str(self.nucleation_widthText_2D.toPlainText()).isdigit():
            self.nucleation_width_2d = int(self.nucleation_widthText_2D.toPlainText())
            self.nucleation_widthText_2D.setPlaceholderText(_translate("Dialog", str(self.nucleation_width_2d)))
            self.nucleation_widthText_2D.clear()
            width_or_height_changed = True

        if str(self.nucleation_radius_text.toPlainText()) != "" and str(self.nucleation_radius_text.toPlainText()).isdigit():
            self.nucleation_radius = int(self.nucleation_radius_text.toPlainText())
            self.nucleation_radius_text.setPlaceholderText(_translate("Dialog", str(self.nucleation_radius)))
            self.nucleation_radius_text.clear()
            width_or_height_changed = True

        if str(self.nucleation_amount_width_text.toPlainText()) != "" and str(self.nucleation_amount_width_text.toPlainText()).isdigit():
            if self.NucleationObj.return_width() < int(self.nucleation_amount_width_text.toPlainText()):
                self.nucleation_amount_width_text.clear()
            else:
                self.nucleation_user_width = int(self.nucleation_amount_width_text.toPlainText())
                self.nucleation_amount_width_text.setPlaceholderText(_translate("Dialog", str(self.nucleation_user_width)))
                self.nucleation_amount_width_text.clear()
                width_or_height_changed = True

        if str(self.nucleation_amount_height_text.toPlainText()) != "" and str(self.nucleation_amount_height_text.toPlainText()).isdigit():
            if self.NucleationObj.return_height() < int(self.nucleation_amount_height_text.toPlainText()):
                self.nucleation_amount_height_text.clear()
            else:
                self.nucleation_user_height = int(self.nucleation_amount_height_text.toPlainText())
                self.nucleation_amount_height_text.setPlaceholderText(_translate("Dialog", str(self.nucleation_user_height)))
                self.nucleation_amount_height_text.clear()
                width_or_height_changed = True

        if str(self.nucleation_amount_seeds_text_1.toPlainText()) != "" and str(self.nucleation_amount_seeds_text_1.toPlainText()).isdigit():
            if self.NucleationObj.return_height()*self.NucleationObj.return_width() < int(self.nucleation_amount_seeds_text_1.toPlainText()):
                self.nucleation_amount_seeds_text_1.clear()
            else:
                self.nucleation_seeds_amount = int(self.nucleation_amount_seeds_text_1.toPlainText())
                self.nucleation_amount_seeds_text_1.setPlaceholderText(_translate("Dialog", str(self.nucleation_seeds_amount)))
                self.nucleation_amount_seeds_text_1.clear()
                width_or_height_changed = True

        if str(self.nucleation_neighbours_text.toPlainText()) != "":
            if str(self.nucleation_neighbours_text.toPlainText()) in self.NucleationObj.return_neighbour_array():
                pattern_changed = True
                self.nucleation_neighbours_type = str(self.nucleation_neighbours_text.toPlainText())
                self.nucleation_neighbours_text.setPlaceholderText(_translate("Dialog", str(self.nucleation_neighbours_type)))
                if self.nucleation_neighbours_type == "Pentagonal":
                    pentagonal_patterns = ["PentagonalLeft","PentagonalRight","PentagonalDown","PentagonalUp"]
                    self.nucleation_neighbours_type = random.choice(pentagonal_patterns)
                    print(self.nucleation_neighbours_type)
                if self.nucleation_neighbours_type == "Hexagonal":
                    hexagonal_patterns = ["HexagonalR","HexagonalL"]
                    self.nucleation_neighbours_type = random.choice(hexagonal_patterns)
                    print(self.nucleation_neighbours_type)
                if str(self.nucleation_neighbours_text.toPlainText()) == "Radius" and str(
                        self.nucleation_radius_text.toPlainText()) != "" and str(
                        self.nucleation_radius_text.toPlainText()).isdigit():
                    self.nucleation_neighbour_radius = int(self.nucleation_radius_text.toPlainText())
                    self.nucleation_radius_text.clear()

                if str(self.nucleation_pattern_Text_2D.toPlainText()) == "radius" and str(
                        self.nucleation_radius_text.toPlainText()) != "" and str(
                    self.nucleation_radius_text.toPlainText()).isdigit():
                    self.nucleation_radius = int(self.nucleation_radius_text.toPlainText())
                    self.nucleation_radius_text.clear()

                if str(self.nucleation_neighbours_text.toPlainText()) == "Radius" and str(self.nucleation_radius_text.toPlainText() == ""):
                    self.nucleation_neighbour_radius = self.NucleationObj.return_neighbour_radius()
                    self.nucleation_radius_text.clear()

                if str(self.nucleation_pattern_Text_2D.toPlainText()) == "radius" and str(
                        self.nucleation_radius_text.toPlainText() == ""):
                    self.nucleation_radius = self.NucleationObj.return_radius()
                    self.nucleation_radius_text.clear()
                self.nucleation_neighbours_text.clear()
            else:
                self.nucleation_neighbours_text.clear()

        if str(self.nucleation_heightText2D.toPlainText()) != "" and str(self.nucleation_heightText2D.toPlainText()).isdigit():
            self.nucleation_height_2d = int(self.nucleation_heightText2D.toPlainText())
            self.nucleation_heightText2D.setPlaceholderText(_translate("Dialog", str(self.nucleation_height_2d)))
            self.nucleation_heightText2D.clear()
            width_or_height_changed = True

        if str(self.nucleation_iterationsText_2D.toPlainText()) != "" and str(self.nucleation_iterationsText_2D.toPlainText()).isdigit():
            self.nucleation_iterations_2d = int(self.nucleation_iterationsText_2D.toPlainText())
            self.nucleation_iterationsText_2D.setPlaceholderText(_translate("Dialog", str(self.nucleation_iterations_2d)))
            self.nucleation_iterationsText_2D.clear()

        if str(self.nucleation_pattern_Text_2D.toPlainText()) != "":
            if str(self.nucleation_pattern_Text_2D.toPlainText()) in self.NucleationObj.return_pattern_array():
                pattern_changed = True
                self.nucleation_pattern_2d = str(self.nucleation_pattern_Text_2D.toPlainText())
                self.nucleation_pattern_Text_2D.setPlaceholderText(_translate("Dialog", str(self.nucleation_pattern_2d)))
                self.nucleation_pattern_Text_2D.clear()
            else:
                self.nucleation_pattern_Text_2D.clear()

        if str(self.nucleation_boundary_Text_2D_7.toPlainText()) != "": # boundary conditions = periodical
            self.nucleation_boundary_conditions = str(self.nucleation_boundary_Text_2D_7.toPlainText())
            if self.nucleation_boundary_conditions.lower() == "periodical":
                self.nucleation_boundary_conditions = "periodical"
            else:
                self.nucleation_boundary_conditions = "absorbing"
            self.nucleation_boundary_Text_2D_7.setPlaceholderText(_translate("Dialog", str(self.nucleation_boundary_conditions)))
            self.nucleation_boundary_Text_2D_7.clear()

        self.NucleationObj.set_parameters(self.nucleation_width_2d, self.nucleation_height_2d,
                                          self.nucleation_iterations_2d, self.nucleation_pattern_2d,
                                          self.nucleation_boundary_conditions, self.nucleation_neighbours_type,
                                          self.nucleation_seeds_amount, self.nucleation_user_width,
                                          self.nucleation_user_height, self.nucleation_radius,self.nucleation_neighbour_radius)

        if self.nucleation_pattern_2d == "manual":
            self.nucleation_manualInputTextArea_2D.clear()
            self.nucleation_initial_manual_array_2d = self.NucleationObj.return_initial_array()
            self.nucleation_manualInputTextArea_2D.appendPlainText(
                _translate("Dialog", str(self.nucleation_draw_manual_array_on_textarea())[1:-1]))

        if width_or_height_changed:
            self.nucleation_manualInputTextArea_2D.clear()
            self.nucleation_initial_manual_array_2d = self.NucleationObj.return_current_array()
            self.nucleation_settings_has_changed = True
        self.array_if_is_drawn = numpy.zeros([self.nucleation_height_2d, self.nucleation_width_2d])


    def begin_nucleation(self):
        self.colours_nucleation_local_dictionary = {}
        _translate = QtCore.QCoreApplication.translate
        self.nucleation_scene.clear()

        if self.NucleationObj.check_if_last_iteration():
            self.restart_nucleation()
        self.nucleation_previous_iteration_array_2d = self.NucleationObj.return_previous_array()
        self.nucleation_current_iteration_array_2d = self.NucleationObj.return_current_array()

        if self.nucleation_pattern_2d == "manual":

            self.manual_array_text_backup = str(self.nucleation_draw_manual_array_on_textarea())[1:-1]
            self.nucleation_read_manual_array_from_textarea()
            self.nucleation_current_iteration_array_2d = self.NucleationObj.return_current_array()

        self.nucleation_draw_empty_board_2d()

        #self.nucleation_current_iteration_array_2d = self.NucleationObj.next_iteration()
        if self.nucleation_pattern_2d == "manual":
            #self.nucleation_draw_board_2d(self.nucleation_current_iteration_array_2d)
            #self.nucleation_draw_board_2d(self.NucleationObj.next_iteration())
            pass
        else:
            self.nucleation_draw_board_2d(self.NucleationObj.next_iteration())
            self.nucleation_draw_board_2d(self.nucleation_current_iteration_array_2d)

        QtTest.QTest.qWait(2000)
        while not self.NucleationObj.check_if_last_iteration():
            QtTest.QTest.qWait(150)
            #self.nucleation_draw_empty_board_2d()
            del self.nucleation_previous_iteration_array_2d
            self.nucleation_previous_iteration_array_2d = self.nucleation_current_iteration_array_2d
            self.nucleation_current_iteration_array_2d = self.NucleationObj.next_iteration()
            self.nucleation_draw_board_2d(self.nucleation_current_iteration_array_2d)
            if self.compare_current_step_with_previous():
                print("They are equal!")
                break

        QtTest.QTest.qWait(50)
        #self.nucleation_draw_empty_board_2d()
        self.nucleation_previous_iteration_array_2d = self.nucleation_current_iteration_array_2d
        #self.nucleation_draw_empty_board_2d()

        #print("last printing before drawing")
        #self.nucleation_last_draw_2d(self.nucleation_current_iteration_array_2d)
        self.nucleation_current_iteration_array_2d = self.NucleationObj.next_iteration()
        self.nucleation_draw_board_2d(self.nucleation_current_iteration_array_2d)
        #self.nucleation_last_draw_2d(self.nucleation_current_iteration_array_2d)

        if self.nucleation_pattern_2d == "manual":
            self.nucleation_initial_manual_array_2d = self.nucleation_current_iteration_array_2d
            self.nucleation_read_manual_array_from_textarea()
            self.nucleation_manualInputTextArea_2D.clear()
            self.nucleation_manualInputTextArea_2D.appendPlainText(_translate("Dialog", str(self.nucleation_draw_manual_array_on_textarea())[1:-1]))

    def nucleation_last_draw_2d(self,input_array):
        self.nucleation_side = 3
        for row in range(self.nucleation_height_2d):
            for column in range(self.nucleation_width_2d):
                rectangle = QtCore.QRectF(QtCore.QPointF(column * self.nucleation_side, row * self.nucleation_side),
                                          QtCore.QSizeF(self.nucleation_side, self.nucleation_side))

                colors_array = input_array[row][column].return_colours_array()
                # print(colors_array)
                self.nucleation_scene.addRect(rectangle,
                                              QtGui.QPen(QColor(colors_array[0], colors_array[1], colors_array[2])))

    def nucleation_read_manual_array_from_textarea(self):
        if self.nucleation_settings_has_changed and self.nucleation_pattern_2d != "manual":
            self.nucleation_settings_has_changed = False
            return
        else:
            current_text = str(self.nucleation_manualInputTextArea_2D.toPlainText())
            current_text_modified = current_text.replace(" ","").replace("',","S").replace("'","").split("S")

            if current_text != self.nucleation_manual_array_text_backup:
                user_edited_array = []
                for row in range(len(current_text_modified)):
                    row_array = []
                    splitted_array = current_text_modified[row].split(",")
                    for column in range(len(current_text_modified[row].split(","))):

                        splitted_array_index = int(splitted_array[column])
                        new_cell = Cell()
                        new_cell.set_id(splitted_array_index)
                        if splitted_array_index != 0:
                            if splitted_array_index in self.NucleationObj.return_colors_dictionary():
                                new_cell.set_colours_array(self.NucleationObj.return_colors_dictionary().get(splitted_array_index))
                            else:
                                random_red = random.randint(0, 255)
                                random_green = random.randint(0, 255)
                                random_blue = random.randint(0, 255)
                                new_cell.set_colours_array([random_red, random_green, random_blue])
                                self.NucleationObj.set_colors_dictionary_element(splitted_array_index,[random_red, random_green, random_blue])
                        else:
                            new_cell.set_colours_array([255,255,255])
                        row_array.append(new_cell)
                    user_edited_array.append(row_array)
                self.NucleationObj.set_current_array(user_edited_array)

    def nucleation_draw_board_2d(self,input_array):
        self.nucleation_side = 3
        for row in range(self.nucleation_height_2d):
            for column in range(self.nucleation_width_2d):
                if input_array[row][column].id == 0 or input_array[row][column].return_colours_array() == [255,255,255] or self.array_if_is_drawn[row][column] >= 2:#or self.array_if_is_drawn[row][column]:
                    continue
                #if self.nucleation_pattern_2d == "manual":
                if input_array[row][column].return_id() not in self.colours_nucleation_local_dictionary:
                    self.colours_nucleation_local_dictionary[input_array[row][column].return_id()] = input_array[row][column].return_colours_array()
                #print(str(self.colours_nucleation_local_dictionary[input_array[row][column].return_id()])+" id: "+str(input_array[row][column].return_id()))

                self.array_if_is_drawn[row][column] += 1
                rectangle = QtCore.QRectF(QtCore.QPointF(column * self.nucleation_side, row * self.nucleation_side),
                                          QtCore.QSizeF(self.nucleation_side, self.nucleation_side))
                #if self.nucleation_pattern_2d == "manual":
                colors_array = self.colours_nucleation_local_dictionary[input_array[row][column].return_id()]
                #else:
                #    colors_array = input_array[row][column].return_colours_array()
                self.nucleation_scene.addRect(rectangle, QtGui.QPen(QColor(colors_array[0],colors_array[1],colors_array[2])))

    def nucleation_draw_manual_array_on_textarea(self):
        draw_array = []
        for row in range(self.nucleation_height_2d):
            row_array = ''

            for column in range(self.nucleation_width_2d): # zmienic?
                row_array+=str(self.nucleation_initial_manual_array_2d[row][column].id)

                if column == self.nucleation_width_2d-1:
                    row_array+=''
                else:
                    row_array+=','

            draw_array.append(row_array)
        return draw_array

    def nucleation_draw_empty_board_2d(self):
        self.nucleation_side = 3

        for row in range(self.nucleation_height_2d):
            for column in range(self.nucleation_width_2d):
                rectangle = QtCore.QRectF(QtCore.QPointF(column * self.nucleation_side, row * self.nucleation_side),
                                          QtCore.QSizeF(self.nucleation_side, self.nucleation_side))
                self.nucleation_scene.addRect(rectangle, QtGui.QPen(QColor(255,255,255)))

    def restart_nucleation(self):
        _translate = QtCore.QCoreApplication.translate
        self.array_if_is_drawn = numpy.zeros([self.nucleation_height_2d,self.nucleation_width_2d])
        self.nucleation_scene.clear()
        self.nucleation_row = 0
        self.nucleation_column = 0
        self.NucleationObj.restart_grid()
        self.nucleation_manualInputTextArea_2D.clear()
        self.nucleation_initial_manual_array_2d = self.NucleationObj.return_initial_array()
        self.nucleation_manualInputTextArea_2D.appendPlainText(
                    _translate("Dialog", str(self.nucleation_draw_manual_array_on_textarea())[1:-1]))

    sys._excepthook = sys.excepthook

    def exception_hook(exctype, value, traceback):
        print(exctype, value, traceback)
        sys._excepthook(exctype, value, traceback)
        sys.exit(1)

    sys.excepthook = exception_hook