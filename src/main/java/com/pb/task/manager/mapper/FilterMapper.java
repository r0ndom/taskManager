package com.pb.task.manager.mapper;

import com.pb.task.manager.model.TaskData;
import com.pb.task.manager.model.TaskDto;
import com.pb.task.manager.model.filter.TaskSearchFilter;

import java.util.List;

/**
 * Created by Mike on 3/1/2016.
 */
public interface FilterMapper {
    List<TaskDto> search(TaskSearchFilter filter);
}
