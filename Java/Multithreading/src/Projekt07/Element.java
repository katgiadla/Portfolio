package Projekt07;

public class Element {
    private String nameTask;
    private Task task;

    public Element(String inputNameTask, Task inputTask){
        nameTask = inputNameTask;
        task = inputTask;
    }

    public String getNameTask() {
        return nameTask;
    }


    public Task getTask() {
        return task;
    }

}


