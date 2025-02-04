package com.thymeleaf.service;

import com.thymeleaf.entity.Locker;
import com.thymeleaf.repository.LockerRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LockerInitializationService {

    @Autowired
    private LockerRepo lockerRepo;

//    @PostConstruct
//    public void init() {
//        // Check if the table is already populated (optional)
//        if (lockerRepo.count() == 0) {
//            // Create some default lockers
//            Locker locker1 = new Locker();
//            locker1.setName("Locker A");
//            locker1.setDescription("First locker in the row");
//            locker1.setLocked(false);
//            locker1.setUsage(0);
//            locker1.setCapacity(10);
//
//            Locker locker2 = new Locker();
//            locker2.setName("Locker B");
//            locker2.setDescription("Second locker in the row");
//            locker2.setLocked(true);
//            locker2.setUsage(3);
//            locker2.setCapacity(5);
//
//            Locker locker3 = new Locker();
//            locker3.setName("Locker C");
//            locker3.setDescription("Third locker in the row");
//            locker3.setLocked(false);
//            locker3.setUsage(1);
//            locker3.setCapacity(7);
//
//            // Save them to the database
//            lockerRepo.save(locker1);
//            lockerRepo.save(locker2);
//            lockerRepo.save(locker3);
//
//            System.out.println("Lockers initialized in the database.");
//        }
//    }
}
