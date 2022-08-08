package org.aibles.worker2.controller;

import lombok.extern.slf4j.Slf4j;
import org.aibles.worker2.dto.Dto;
import org.aibles.worker2.entity.Worker;
import org.aibles.worker2.exeption.BadRequestException;
import org.aibles.worker2.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("api/v1/workers")
@Slf4j
public class Controller {
    private  final Service service;

    @Autowired
    public Controller(Service service) {
        this.service = service;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public ResponseEntity<Void> deleteWorker(@PathVariable("id") long workerId) {
        service.delete(workerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Transactional
//    public ResponseEntity<Worker> createdWorker(@RequestBody Worker worker) {
//        Worker createdWorker = workerService.createdWorker(worker);
//        return new ResponseEntity<>(createdWorker, HttpStatus.CREATED);
//    }
    public ResponseEntity createdWorker(
            @RequestBody @Validated() Dto workerDto ){
        log.info("add student have info: {}", workerDto);
        if (Objects.isNull(workerDto)) {
            throw new BadRequestException("Syntax Error!!!");
        }
        Dto worker = service.created(workerDto);
        return ResponseEntity.ok(worker);
    }

    @GetMapping
    public ResponseEntity<List<Worker>> workerList() {
        List<Worker> workerList = service.list();
        return new ResponseEntity<>(workerList, HttpStatus.OK);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Worker> updateWorker(@PathVariable("id") long workerId,
//                                               @RequestBody Dto dto) {
//        final Dto updatedWorker = service.update(workerId, dto);
//        return new ResponseEntity<>(updatedWorker, HttpStatus.OK);
//    }




}
