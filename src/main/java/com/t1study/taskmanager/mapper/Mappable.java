package com.t1study.taskmanager.mapper;

public interface Mappable <E,D> {

    D toDTO(E dto);

    E toEntity(D entity);
}
