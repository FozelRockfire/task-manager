package com.t1study.taskmanager.mapper;

import com.t1study.taskmanager.dto.request.TaskRequest;
import com.t1study.taskmanager.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TaskMapper extends Mappable<Task, TaskRequest> {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);
}
