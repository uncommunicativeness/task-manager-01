package task;

import utils.TaskManager;

import java.util.List;

public class EpicTask extends Task {
    public EpicTask(String name, String description) {
        super(name, description);
    }

    public List<Task> getSubTasks() {
        return TaskManager.getSubTasks(this);
    }

    @Override
    public void nextStatus() {
    }

    @Override
    public void prevStatus() {
    }
}
