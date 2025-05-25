package com.natche.ToDoComplex.utils;

import java.time.LocalDateTime;



import com.natche.ToDoComplex.entity.Task;
import com.natche.ToDoComplex.enums.TaskStatus;


public class TaskTimestampUtil {

    public static void setTimestampsForCreation(Task task) {
        LocalDateTime now = LocalDateTime.now();
        task.setCreated(now);

        switch (task.getStatus()) {
            case IN_PROGRESS:
                task.setStarted(now);
                break;
            case COMPLETED:
                task.setStarted(now);
                task.setEnded(now);
                break;
            default:
                // TO_DO or others: do nothing
                break;
        }
    }

    public static void updateTimestampsOnStatusChange(Task task, TaskStatus newStatus) {
        LocalDateTime now = LocalDateTime.now();

        if (newStatus == TaskStatus.IN_PROGRESS && task.getStarted() == null) {
            task.setStarted(now);
        } else if (newStatus == TaskStatus.COMPLETED) {
            if (task.getStarted() == null){
                task.setStarted(now);
            }
            task.setEnded(now);
        }else if(newStatus == TaskStatus.TO_DO){
            task.setEnded(null);
            task.setStarted(null);
        }
    }
}
