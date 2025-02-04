package com.thymeleaf.repository;

import com.thymeleaf.entity.Locker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LockerRepo extends JpaRepository<Locker, Integer> {

    List<Locker> findByIdIn(List<Integer> ids);
}
