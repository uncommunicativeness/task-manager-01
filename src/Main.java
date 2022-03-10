import task.EpicTask;
import task.SubTask;
import task.Task;
import utils.TaskManager;

public class Main {
    public static void main(String[] args) {
        Task task1 = new task.SimpleTask("Task1", "");
        TaskManager.addTask(task1);
        TaskManager.addTask(new task.SimpleTask("Task2", ""));
        TaskManager.addTask(new task.SimpleTask("Task3", ""));

        Task epicTask1 = new EpicTask("EPIC1", "");
        Task epicTask2 = new EpicTask("EPIC2", "");
        Task subTask1 = new SubTask("SUB1", "", epicTask1.getId());
        Task subTask2 = new SubTask("SUB2", "", epicTask1.getId());
        Task subTask3 = new SubTask("SUB3", "", epicTask2.getId());

        TaskManager.addTask(epicTask1);
        TaskManager.addTask(epicTask2);
        TaskManager.addTask(subTask1);
        TaskManager.addTask(subTask2);
        TaskManager.addTask(subTask3);

        // Получение всех задач
        System.out.println("Получение всех задач");
        System.out.println(TaskManager.getTasks());
        System.out.println();

        // Получение всех эпиков
        System.out.println("Получение всех эпиков");
        System.out.println(TaskManager.getEpics());
        System.out.println();

        // Получение всех подзадач эпика
        System.out.println("Получение всех подзадач эпика");
        System.out.println(TaskManager.getSubTasks(epicTask1));
        System.out.println();

        // Получение задачи по ID
        System.out.println("Получение задачи по ID");
        System.out.println(TaskManager.getTaskById(1));
        System.out.println();

        // Обновление задачи по ID
        System.out.println("Обновление задачи по ID");
        Task newTask = new task.SimpleTask("NewTask", "");
        TaskManager.updateTask(1, newTask);
        System.out.println(TaskManager.getTaskById(1));
        System.out.println();

        // Удаление задач по ID
        System.out.println("Удаление задач по ID");
        TaskManager.removeById(2);
        System.out.println(TaskManager.getTasks());
        System.out.println();

        // Меняем статус у простой задачи
        System.out.println("Меняем статус у простой задачи");
        System.out.println(task1);
        task1.nextStatus();
        System.out.println(task1);
        System.out.println();

        // Статус эпика меняется на В ПРОГРЕССЕ если хотя бы один из детей взят в работу
        System.out.println("Смена статуса у эпиков");
        // Эпик
        System.out.println(epicTask1);
        // Дети
        TaskManager.getSubTasks(epicTask1).forEach(System.out::println);
        // Берем ребенка в работу - в работу берется и эпик
        System.out.println("Берем ребенка в работу - в работу берется и эпик");
        subTask1.nextStatus();
        System.out.println(epicTask1);
        // Доводим до конечного финала всех детей - эпик завершается
        System.out.println("Доводим до конечного финала всех детей - эпик завершается");
        subTask1.nextStatus();
        subTask2.nextStatus();
        subTask2.nextStatus();
        System.out.println(epicTask1);
    }
}
