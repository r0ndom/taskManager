package com.pb.task.manager.dao;

import com.pb.task.manager.mapper.TaskMapper;
import com.pb.task.manager.model.Task;
import com.pb.task.manager.model.filter.TaskSearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Mike on 12/31/2015.
 */
@Repository
public class TaskDao {

    @Autowired
    private TaskMapper taskMapper;

    public List<Task> findAll() {
        return taskMapper.findAll();
    }

    public List<Task> search(TaskSearchFilter filter) {
        return taskMapper.search(filter);
    }
}
