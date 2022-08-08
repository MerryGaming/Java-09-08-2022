package org.aibles.worker2.repository;

import org.aibles.worker2.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Worker, Integer> {
}
