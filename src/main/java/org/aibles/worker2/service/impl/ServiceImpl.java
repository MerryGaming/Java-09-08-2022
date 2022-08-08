package org.aibles.worker2.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.aibles.worker2.dto.Dto;
import org.aibles.worker2.entity.Worker;
import org.aibles.worker2.exeption.ServerInternalException;
import org.aibles.worker2.mapper.Mapper;
import org.aibles.worker2.repository.Repository;
import org.aibles.worker2.service.Service;
import org.aspectj.bridge.IMessage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.crossstore.ChangeSetPersister;

import javax.transaction.Transactional;
import javax.xml.validation.Validator;
import java.util.List;
import java.util.Optional;


//@Service
@Slf4j
public class ServiceImpl implements Service {
    private final Repository repository;
    private final ModelMapper modelMapper;

    @Autowired
    private Validator validator;

    public ServiceImpl(Repository repository, Mapper mapper, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    /**
     * create  a worker
     *
     * @param dto
     * @return
     */
    @Override
    @Transactional
    public Dto created(Dto dto) {

//        Set<ConstraintViolation<Worker>> violations = validator.validate(Worker);
//        if(!violations.isEmpty()) {
//            StringBuilder stringBuilder = new StringBuilder();
//            for (ConstraintViolation<Worker> constraintViolation : violations) {
//                stringBuilder.append(constraintViolation.getMessage());
//            }
//            throw new ConstraintViolationException("ERROR!!!: ") + stringBuilder.toString(), violations;
//        }
//        repository.
//
//        return repository.save(worker);
        Worker worker =  modelMapper.map(dto, Worker.class);
        Worker create = repository.save(worker);
        Optional.ofNullable(create).orElseThrow(() -> {
            throw new ServerInternalException("Failse craeted!!! Try again");
        });
        Dto dtoUpdate =  modelMapper.map(create, Dto.class);
        log.info("(Create) Dto: {}", dtoUpdate);
        return dtoUpdate;


    }

    /**
     * delete worker by id
     *
     * @param id
     * return
     */
    @Override
    @Transactional
    public void delete(long id) {
        boolean checkIdWorker = repository.existsById(Math.toIntExact(id));
        if(!checkIdWorker) {
            try {
                throw new ChangeSetPersister.NotFoundException();
            } catch (ChangeSetPersister.NotFoundException messager) {
                throw new RuntimeException(messager);
            }
        }
        repository.deleteById(Math.toIntExact(id));
        boolean checkIdWorkerDelete = repository.existsById(Math.toIntExact(id));
        if(checkIdWorkerDelete) {
            throw new ServerInternalException("Delete found!");
        }
        log.info("Delete");

    }

   /**
    * delete all worker

    * return
    */

    @Override
    @CacheEvict
    @Transactional
    public void deleteAll()  {
        repository.deleteAll();
        List<Worker> listWorker = repository.findAll();
        if (!listWorker.isEmpty()) {
            throw new ServerInternalException("Delete all car failed!");
        }
        log.info("(DeleteAll)");
    }

    /**
     * list worker
     * return
     */
    @Override
    public List<Worker> list() {
        return repository.findAll();
    }

    @Override
    public Dto get(String id) {
        Worker worker = repository.findById(Integer.valueOf(id)).orElseThrow(() -> {
            try {
                throw new ChangeSetPersister.NotFoundException();
            } catch (ChangeSetPersister.NotFoundException messager) {
                throw new RuntimeException(messager);
            }
        });
        Dto dto = modelMapper.map(worker, Dto.class);
        log.info("(Get) carDTO: {} ", dto);
        return dto;
    }

    /**
     * update worker
     *
     * @param id
     * @param dto return
     */
    @Override
    @Transactional
    public void update(long id, Dto dto) {
        //Worker result = repository.findById((int) id)
//                .map(worker -> {
//                    worker.setName(workerUpdate.getName());
//                    worker.setAddress(workerUpdate.getAddress());
//                    log.info("successful update");
//                    return worker;
//                })
//                .map(repository::save)
//                .orElse(null);
        return ;

    }


}
