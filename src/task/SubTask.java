package task;

import utils.TaskManager;

import java.util.List;

public class SubTask extends Task {
    private final int parentId;

    public SubTask(String name, String description, int parentId) {
        super(name, description);
        this.parentId = parentId;
    }

    public int getParentId() {
        return parentId;
    }

    @Override
    public void nextStatus() {
        Task parent = TaskManager.getTaskById(parentId);
        if (parent.getStatus() == Status.NEW) {
            parent.setStatus(Status.IN_PROGRESS);
        }
        super.nextStatus();
        List<Task> children = TaskManager.getSubTasks(parent);
        if (children.stream().allMatch(task -> task.getStatus() == Status.DONE)) {
            parent.setStatus(Status.DONE);
        }
    }

    @Override
    public void prevStatus() {
        super.prevStatus();
    }
}
