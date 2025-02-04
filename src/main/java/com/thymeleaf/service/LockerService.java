package com.thymeleaf.service;

import com.thymeleaf.entity.Locker;
import com.thymeleaf.mapper.LockerMapper;
import com.thymeleaf.model.LockerDto;
import com.thymeleaf.repository.LockerRepo;
import jakarta.persistence.NoResultException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Service
public class LockerService {

    private final LockerRepo lockerRepo;
    private final LockerMapper lockerMapper;

    public LockerService(LockerRepo lockerRepo,
                         LockerMapper lockerMapper) {
        this.lockerRepo = lockerRepo;
        this.lockerMapper = lockerMapper;
    }

    public LockerDto update(Integer lockerId, LockerDto lockerDto) {
        Locker existingLocker = lockerRepo.findById(lockerId).orElseThrow(NoResultException::new);
        return saveOrUpdate(existingLocker, lockerDto);
    }

    public LockerDto save(LockerDto lockerDto) {
        return saveOrUpdate(new Locker(), lockerDto);
    }

    private LockerDto saveOrUpdate(Locker locker, LockerDto lockerDto) {
        lockerMapper.update(locker, lockerDto);
        Locker savedOrUpdatedLocker = lockerRepo.save(locker);
        return lockerMapper.toDto(savedOrUpdatedLocker);
    }

    public LockerDto findDtoById(Integer lockerId) throws NoResultException {
        return toDto(findById(lockerId));
    }

    public Locker findById(Integer lockerId) throws NoResultException {
        return lockerRepo.findById(lockerId).orElseThrow(NoResultException::new);
    }

    public List<LockerDto> findDtosByIds(List<Integer> lockerIds) {
        return toDtos(findByIds(lockerIds));
    }

    public List<Locker> findByIds(List<Integer> lockerIds) {
        return lockerRepo.findByIdIn(lockerIds);
    }

    public LockerDto toDto(Locker locker) {
        if (null == locker) {
            return null;
        }

        return lockerMapper.toDto(locker);
    }

    public List<LockerDto> toDtos(List<Locker> lockers) {
        if (null == lockers) {
            return null;
        }

        if (CollectionUtils.isEmpty(lockers)) {
            return Collections.EMPTY_LIST;
        }

        return lockerMapper.toDtos(lockers);
    }

    public List<LockerDto> findAll() {
        List<Locker> lockers = lockerRepo.findAll();

        if (CollectionUtils.isEmpty(lockers)) {
            return Collections.EMPTY_LIST;
        }

        return lockerMapper.toDtos(lockers);
    }

    public void deleteById(Integer lockerId) {
        lockerRepo.deleteById(lockerId);
    }

    public void deleteByIds(List<Integer> lockerIds) {
        lockerRepo.deleteAllById(lockerIds);
    }
}
