package utils;

import task.EpicTask;
import task.SubTask;
import task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private static final List<Task> tasks = new ArrayList<>();
    private static int newId = 0;
    private static TaskManager taskManager;

    private TaskManager() {
    }

    public static void addTask(Task task) {
        tasks.add(task);
    }

    public static List<Task> getTasks() {
        return tasks;
    }

    public static Task getTaskById(int id) {
        return tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public static void removeAll() {
        tasks.clear();
    }

    public static void removeById(int id) {
        tasks.remove(getTaskById(id));
    }

    public static List<EpicTask> getEpics() {
        return tasks.stream()
                .filter(task -> task.getClass().getName().equals(EpicTask.class.getName()))
                .map(task -> (EpicTask) task)
                .toList();
    }

    public static TaskManager getInstance() {
        if (taskManager == null) {
            taskManager = new TaskManager();
        }
        return taskManager;
    }

    public static int getNewId() {
        return ++newId;
    }

    public static List<Task> getSubTasks(Task epicTask) {
        return tasks.stream().filter(task -> {
            try {
                SubTask subtask = (SubTask) task;
                return subtask.getParentId() == epicTask.getId();
            } catch (Exception e) {
                return false;
            }
        }).toList();
    }

    public static void updateTask(int id, Task task) {
        if (tasks.size() == 0) {
            return;
        }
        tasks.set(tasks.indexOf(getTaskById(id)), task);
        task.setId(id);
    }
}
