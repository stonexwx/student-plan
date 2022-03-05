package com.student.dto;

import com.student.entity.Task;
import com.student.entity.Type;

public class TaskDTO {
    private Task task;
    private Type type;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
