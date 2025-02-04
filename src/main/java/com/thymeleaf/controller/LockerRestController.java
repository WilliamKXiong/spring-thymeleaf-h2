package com.thymeleaf.controller;

import com.thymeleaf.entity.Locker;
import com.thymeleaf.mapper.LockerMapper;
import com.thymeleaf.repository.LockerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/locker")
public class LockerRestController {

    @Autowired
    public LockerRepo lockerRepo;

    @Autowired
    public LockerMapper lockerMapper;

    @GetMapping({"", "/"})
    public ResponseEntity<?> root() {
        return ResponseEntity.ok("Root successful!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> locker(@PathVariable int id) {
        Locker locker = lockerRepo.findById(id).orElse(null);

        if (locker != null) {
            return ResponseEntity.ok(lockerMapper.toDto(locker));
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/all")
    public ResponseEntity<?> allLockers() {
        return ResponseEntity.ok(lockerRepo.findAll());
    }

    @GetMapping("/test")
    public ResponseEntity<?> testLockerRepo() {
        System.out.println("LockerRepo Bean: " + lockerRepo);
        System.out.println("LockerRepo Records: " + lockerRepo.findAll());
        return ResponseEntity.ok(lockerRepo.findAll());
    }


    @GetMapping("/save")
    public ResponseEntity<?> testSaveLocker() {
        Locker locker = new Locker();
        locker.setName("testSave");
        lockerRepo.save(locker);
        return ResponseEntity.ok(lockerRepo.findAll());
    }

}
