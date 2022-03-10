package task;

import utils.TaskManager;

public abstract class Task implements Comparable<Task> {
    private final String name;
    private final String description;
    private int id;
    private Status status;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.id = TaskManager.getNewId();
        this.status = Status.NEW;
    }

    @Override
    public int compareTo(Task o) {
        return this.id - o.getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void nextStatus() {
        if (status.ordinal() == 2) {
            return;
        }
        status = Status.values()[status.ordinal() + 1];
    }

    public void prevStatus() {
        if (status.ordinal() == 0) {
            return;
        }
        status = Status.values()[status.ordinal() - 1];
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("ID: %d\tNAME: %s\tSTATUS: %s", this.id, this.name, this.status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        return id == task.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
