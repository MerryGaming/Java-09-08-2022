package org.aibles.worker2.mapper;

import org.aibles.worker2.dto.Dto;
import org.aibles.worker2.entity.Worker;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    private ModelMapper modelMapper;

    public Mapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Dto mapToDto(Worker worker) {
        return modelMapper.map(worker, Dto.class);
    }
    public Worker mapToEntity(Worker worker, Dto dto) {
        modelMapper.map(dto,worker);
        return worker;
    }
}
