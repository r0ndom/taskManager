package com.pb.task.manager.mapper;

import com.pb.task.manager.model.Archive;
import com.pb.task.manager.model.TaskData;

import java.util.List;

/**
 * Created by Mednikov on 25.01.2016.
 */
public interface ArchiveMapper {
    void put(TaskData taskData);
    List<Archive> findAll();
}
