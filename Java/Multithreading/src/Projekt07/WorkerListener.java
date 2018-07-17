package Projekt07;

public interface WorkerListener {
    public void onWorkerStarted();
    public void onWorkerStoped();
    public void onTaskStarted(int taskNumber, String taskName);
    public void onTaskCompleted(int taskNumber, String taskName);
}

