package org.aibles.worker2.service;

import org.aibles.worker2.dto.Dto;
import org.aibles.worker2.entity.Worker;

import java.util.List;

public interface Service {
    Dto created (Dto dto);
    void delete(long id);
    void deleteAll() throws Exception;
    List<Worker> list();
    Dto get(String id);
    void update (long id, Dto dto);

}
