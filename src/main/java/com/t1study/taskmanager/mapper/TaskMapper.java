package com.t1study.taskmanager.mapper;

import com.t1study.taskmanager.dto.request.TaskRequest;
import com.t1study.taskmanager.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TaskMapper extends Mappable<Task, TaskRequest> {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dueDate", dateFormat = "yyyy-MM-dd")
    void updateTaskFromRequest(TaskRequest taskRequest, @MappingTarget Task task);
}
