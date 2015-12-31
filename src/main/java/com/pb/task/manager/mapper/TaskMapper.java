package com.pb.task.manager.mapper;

import com.pb.task.manager.model.Task;
import com.pb.task.manager.model.filter.TaskSearchFilter;

import java.util.List;

/**
 * Created by Mike on 12/31/2015.
 */
public interface TaskMapper {
    public List<Task> findAll();
    public List<Task> search(TaskSearchFilter filter);
}
