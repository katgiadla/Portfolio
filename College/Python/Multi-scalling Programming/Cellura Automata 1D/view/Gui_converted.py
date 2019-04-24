# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'Gui.ui'
#
# Created by: PyQt5 UI code generator 5.11.3
#
# WARNING! All changes made in this file will be lost!

from PyQt5 import QtCore, QtGui, QtWidgets
from PyQt5.QtCore import pyqtSlot
from PyQt5.QtWidgets import QWidget

from logic.FistDimension import FirstDimension


class Ui_Dialog(QWidget):

    def __init__(self):
        super().__init__()
        self.FirstDimensionObj = FirstDimension()
        self.width = 100
        self.iterations = 100
        self.rule = 90
        self.alive_cels_numbers = [51]
        self.side = 7
        self.row = 0
        self.first_time = True
        self.previous_iteration_array = []
        self.previous_counter = self.iterations
        self.previous_row = self.row

    def setupUi(self, Dialog):
        Dialog.setObjectName("Dialog")
        Dialog.resize(850, 450)
        Dialog.setMinimumSize(QtCore.QSize(850, 450))
        Dialog.setMaximumSize(QtCore.QSize(850, 450))
        Dialog.setSizeIncrement(QtCore.QSize(10, 10))
        Dialog.setBaseSize(QtCore.QSize(100, 100))
        Dialog.setAutoFillBackground(True)
        self.horizontalWidget = QtWidgets.QWidget(Dialog)
        self.horizontalWidget.setGeometry(QtCore.QRect(0, -1, 850, 441))
        self.horizontalWidget.setMinimumSize(QtCore.QSize(850, 350))
        self.horizontalWidget.setMaximumSize(QtCore.QSize(850, 450))
        self.horizontalWidget.setObjectName("horizontalWidget")
        self.mode_menu = QtWidgets.QHBoxLayout(self.horizontalWidget)
        self.mode_menu.setSizeConstraint(QtWidgets.QLayout.SetMaximumSize)
        self.mode_menu.setContentsMargins(0, 0, 0, 0)
        self.mode_menu.setObjectName("mode_menu")
        self.tabWidget = QtWidgets.QTabWidget(self.horizontalWidget)
        self.tabWidget.setMinimumSize(QtCore.QSize(850, 450))
        self.tabWidget.setMaximumSize(QtCore.QSize(850, 450))
        self.tabWidget.setObjectName("tabWidget")
        self.OneDimensionalTab = QtWidgets.QWidget()
        self.OneDimensionalTab.setObjectName("OneDimensionalTab")
        self.horizontalLayoutWidget_7 = QtWidgets.QWidget(self.OneDimensionalTab)
        self.horizontalLayoutWidget_7.setGeometry(QtCore.QRect(0, 0, 980, 456))
        self.horizontalLayoutWidget_7.setObjectName("horizontalLayoutWidget_7")
        self.horizontalLayout_4 = QtWidgets.QHBoxLayout(self.horizontalLayoutWidget_7)
        self.horizontalLayout_4.setContentsMargins(0, 0, 0, 0)
        self.horizontalLayout_4.setObjectName("horizontalLayout_4")
        self.formLayout = QtWidgets.QFormLayout()
        self.formLayout.setObjectName("formLayout")
        self.horizontalLayout_3 = QtWidgets.QHBoxLayout()
        self.horizontalLayout_3.setObjectName("horizontalLayout_3")
        self.formLayout.setLayout(2, QtWidgets.QFormLayout.FieldRole, self.horizontalLayout_3)

        self.scene_1d = QtWidgets.QGraphicsScene()
        self.green_pen = QtGui.QPen(QtCore.Qt.green)
        self.red_pen = QtGui.QPen(QtCore.Qt.red)
        self.blue_pen = QtGui.QPen(QtCore.Qt.blue)

        self.graphic_ca_1d = QtWidgets.QGraphicsView(self.horizontalLayoutWidget_7)
        self.graphic_ca_1d.setMinimumSize(QtCore.QSize(849, 350))
        self.graphic_ca_1d.setMaximumSize(QtCore.QSize(849, 350))
        self.graphic_ca_1d.setObjectName("graphic_ca_1d")
        self.graphic_ca_1d.setScene(self.scene_1d)

        self.formLayout.setWidget(2, QtWidgets.QFormLayout.LabelRole, self.graphic_ca_1d)
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
        self.aliveCellsArrayLabel1D.setMinimumSize(QtCore.QSize(100, 0))
        self.aliveCellsArrayLabel1D.setMaximumSize(QtCore.QSize(100, 16777215))
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
        self.ruleLabel1D.setMinimumSize(QtCore.QSize(70, 0))
        self.ruleLabel1D.setMaximumSize(QtCore.QSize(70, 16777215))
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
        self.horizontalLayout_4.addLayout(self.formLayout)
        self.tabWidget.addTab(self.OneDimensionalTab, "")
        self.TwoDimensionalTab = QtWidgets.QWidget()
        self.TwoDimensionalTab.setObjectName("TwoDimensionalTab")
        self.formLayoutWidget_2 = QtWidgets.QWidget(self.TwoDimensionalTab)
        self.formLayoutWidget_2.setGeometry(QtCore.QRect(-1, 9, 851, 440))
        self.formLayoutWidget_2.setObjectName("formLayoutWidget_2")
        self.formLayout_2 = QtWidgets.QFormLayout(self.formLayoutWidget_2)
        self.formLayout_2.setContentsMargins(0, 0, 0, 0)
        self.formLayout_2.setObjectName("formLayout_2")
        self.scene_2d = QtWidgets.QGraphicsScene()
        self.graphic_ca_2d = QtWidgets.QGraphicsView(self.formLayoutWidget_2)
        self.graphic_ca_2d.setMinimumSize(QtCore.QSize(849, 350))
        self.graphic_ca_2d.setObjectName("graphic_ca_2d")
        self.graphic_ca_2d.setScene(self.scene_2d)
        self.formLayout_2.setWidget(2, QtWidgets.QFormLayout.SpanningRole, self.graphic_ca_2d)
        self.horizontalLayout_7 = QtWidgets.QHBoxLayout()
        self.horizontalLayout_7.setObjectName("horizontalLayout_7")
        self.horizontalLayout_8 = QtWidgets.QHBoxLayout()
        self.horizontalLayout_8.setObjectName("horizontalLayout_8")
        self.width_layout_horizontal_3 = QtWidgets.QHBoxLayout()
        self.width_layout_horizontal_3.setObjectName("width_layout_horizontal_3")
        self.widthLabel_2D = QtWidgets.QLabel(self.formLayoutWidget_2)
        self.widthLabel_2D.setMinimumSize(QtCore.QSize(50, 0))
        self.widthLabel_2D.setMaximumSize(QtCore.QSize(50, 16777215))
        self.widthLabel_2D.setObjectName("widthLabel_2D")
        self.width_layout_horizontal_3.addWidget(self.widthLabel_2D)
        self.widthText_2D = QtWidgets.QTextEdit(self.formLayoutWidget_2)
        self.widthText_2D.setMinimumSize(QtCore.QSize(60, 30))
        self.widthText_2D.setMaximumSize(QtCore.QSize(60, 30))
        self.widthText_2D.setDocumentTitle("")
        self.widthText_2D.setObjectName("widthText_2D")
        self.width_layout_horizontal_3.addWidget(self.widthText_2D)
        self.iterationsLayout_3 = QtWidgets.QHBoxLayout()
        self.iterationsLayout_3.setObjectName("iterationsLayout_3")
        self.iterationsLabel_2D = QtWidgets.QLabel(self.formLayoutWidget_2)
        self.iterationsLabel_2D.setMinimumSize(QtCore.QSize(60, 0))
        self.iterationsLabel_2D.setMaximumSize(QtCore.QSize(60, 16777215))
        self.iterationsLabel_2D.setObjectName("iterationsLabel_2D")
        self.iterationsLayout_3.addWidget(self.iterationsLabel_2D)
        self.iterationsText_2D = QtWidgets.QTextEdit(self.formLayoutWidget_2)
        self.iterationsText_2D.setMinimumSize(QtCore.QSize(60, 30))
        self.iterationsText_2D.setMaximumSize(QtCore.QSize(60, 30))
        self.iterationsText_2D.setObjectName("iterationsText_2D")
        self.iterationsLayout_3.addWidget(self.iterationsText_2D)
        self.aliveLayout_3 = QtWidgets.QHBoxLayout()
        self.aliveLayout_3.setObjectName("aliveLayout_3")
        self.aliveCellsArrayLabel_2D = QtWidgets.QLabel(self.formLayoutWidget_2)
        self.aliveCellsArrayLabel_2D.setMinimumSize(QtCore.QSize(80, 0))
        self.aliveCellsArrayLabel_2D.setMaximumSize(QtCore.QSize(80, 16777215))
        self.aliveCellsArrayLabel_2D.setObjectName("aliveCellsArrayLabel_2D")
        self.aliveLayout_3.addWidget(self.aliveCellsArrayLabel_2D)
        self.aliveCellsText_2D = QtWidgets.QTextEdit(self.formLayoutWidget_2)
        self.aliveCellsText_2D.setMinimumSize(QtCore.QSize(60, 30))
        self.aliveCellsText_2D.setMaximumSize(QtCore.QSize(60, 30))
        self.aliveCellsText_2D.setObjectName("aliveCellsText_2D")
        self.aliveLayout_3.addWidget(self.aliveCellsText_2D)
        self.iterationsLayout_3.addLayout(self.aliveLayout_3)
        self.ruleLayout_3 = QtWidgets.QHBoxLayout()
        self.ruleLayout_3.setObjectName("ruleLayout_3")
        self.ruleLabel_2D = QtWidgets.QLabel(self.formLayoutWidget_2)
        self.ruleLabel_2D.setMinimumSize(QtCore.QSize(60, 0))
        self.ruleLabel_2D.setMaximumSize(QtCore.QSize(60, 16777215))
        self.ruleLabel_2D.setObjectName("ruleLabel_2D")
        self.ruleLayout_3.addWidget(self.ruleLabel_2D)
        self.ruleText_2D = QtWidgets.QTextEdit(self.formLayoutWidget_2)
        self.ruleText_2D.setMinimumSize(QtCore.QSize(60, 30))
        self.ruleText_2D.setMaximumSize(QtCore.QSize(60, 30))
        self.ruleText_2D.setObjectName("ruleText_2D")
        self.ruleLayout_3.addWidget(self.ruleText_2D)
        self.iterationsLayout_3.addLayout(self.ruleLayout_3)
        self.width_layout_horizontal_3.addLayout(self.iterationsLayout_3)
        self.horizontalLayout_8.addLayout(self.width_layout_horizontal_3)
        self.horizontalLayout_7.addLayout(self.horizontalLayout_8)
        self.initializeGameButton_2D = QtWidgets.QPushButton(self.formLayoutWidget_2)
        self.initializeGameButton_2D.setMinimumSize(QtCore.QSize(60, 0))
        self.initializeGameButton_2D.setMaximumSize(QtCore.QSize(60, 16777215))
        self.initializeGameButton_2D.setObjectName("initializeGameButton_2D")
        self.horizontalLayout_7.addWidget(self.initializeGameButton_2D)
        self.beginGameButton_2D = QtWidgets.QPushButton(self.formLayoutWidget_2)
        self.beginGameButton_2D.setMinimumSize(QtCore.QSize(60, 0))
        self.beginGameButton_2D.setMaximumSize(QtCore.QSize(60, 16777215))
        self.beginGameButton_2D.setObjectName("beginGameButton_2D")
        self.horizontalLayout_7.addWidget(self.beginGameButton_2D)
        self.restart_button_2d = QtWidgets.QPushButton(self.formLayoutWidget_2)
        self.restart_button_2d.setObjectName("restart_button_2d")
        self.horizontalLayout_7.addWidget(self.restart_button_2d)
        self.formLayout_2.setLayout(1, QtWidgets.QFormLayout.LabelRole, self.horizontalLayout_7)
        self.tabWidget.addTab(self.TwoDimensionalTab, "")
        self.mode_menu.addWidget(self.tabWidget)

        self.retranslateUi(Dialog)
        self.tabWidget.setCurrentIndex(0)
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
        self.widthText_2D.setPlaceholderText(_translate("Dialog", "100"))
        self.iterationsLabel_2D.setText(_translate("Dialog", "Iterations"))
        self.iterationsText_2D.setPlaceholderText(_translate("Dialog", "100"))
        self.aliveCellsArrayLabel_2D.setText(_translate("Dialog", "Set cell/s alive"))
        self.aliveCellsText_2D.setPlaceholderText(_translate("Dialog", "51,1"))
        self.ruleLabel_2D.setText(_translate("Dialog", "Rule 1-255"))
        self.ruleText_2D.setPlaceholderText(_translate("Dialog", "90"))
        self.initializeGameButton_2D.setText(_translate("Dialog", "Initialize"))
        self.beginGameButton_2D.setText(_translate("Dialog", "Start"))
        self.restart_button_2d.setText(_translate("Dialog", "restart"))
        self.tabWidget.setTabText(self.tabWidget.indexOf(self.TwoDimensionalTab),
                                  _translate("Dialog", "TwoDimensional"))

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
                pass
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

        # if changed == True:
        # self.scene_1d.clear()
        # self.row = 0

        self.FirstDimensionObj.set_parameters(self.width, self.iterations, self.rule, self.alive_cels_numbers)
        # print('parameters set')
        # print(self.FirstDimensionObj.return_parameters())

    @pyqtSlot()
    def begin_game(self):
        # self.scene_1d.clear()
        # self.FirstDimensionObj.begin_the_game()
        # print(' ')
        if self.first_time == False:
            self.mark_previous_iteration(self.previous_iteration_array, self.previous_counter, self.previous_row)

        counter = self.iterations

        self.previous_iteration_array = []
        self.previous_counter = self.iterations
        self.previous_row = self.row

        self.side = 7
        while counter > 0:
            self.current_iteration_array = self.FirstDimensionObj.single_iteration()
            self.previous_iteration_array.append(self.current_iteration_array)

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

            # for element in self.current_iteration_array:
            #     print(element, end='')

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


# if __name__ == "__main__":
#     import sys
#
#     app = QtWidgets.QApplication(sys.argv)
#     Dialog = QtWidgets.QDialog()
#     ui = Ui_Dialog()
#     ui.setupUi(Dialog)
#     Dialog.show()
#     sys.exit(app.exec_())
