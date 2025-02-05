package com.thymeleaf.controller;

import com.thymeleaf.model.LockerDto;
import com.thymeleaf.service.LockerService;
import jakarta.persistence.NoResultException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/locker")
public class LockerController {

    private LockerService lockerService;

    public LockerController(LockerService lockerService) {
        this.lockerService = lockerService;
    }

    @GetMapping({"", "/"})
    public String locker(Model model) {
        List<LockerDto> lockers = lockerService.findAll();
        model.addAttribute("lockers", lockers);
        model.addAttribute("onListPage", true);
        return "locker/list";
    }

    @GetMapping("/{id}")
    public String locker(@PathVariable("id") String idString, Model model) {
        model.addAttribute("locker", null);
        if (!StringUtils.isNumeric(idString)) {
            return "locker/error";
        }

        int id = Integer.parseInt(idString);
        try {
            LockerDto locker = lockerService.findDtoById(id);
            model.addAttribute("locker", locker);
            model.addAttribute("onListPage", false);
            return "locker/details";
        } catch (NoResultException e) {
            return "locker/noResult";
        } catch (Exception e) {
            return "locker/error";
        }
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable("id") String idString, Model model) {
        model.addAttribute("locker", null);
        if (!StringUtils.isNumeric(idString)) {
            return "locker/error";
        }

        int id = Integer.parseInt(idString);
        try {
            LockerDto locker = lockerService.findDtoById(id);
            model.addAttribute("locker", locker);
            return "locker/edit";
        } catch (NoResultException e) {
            return "locker/noResult";
        } catch (Exception e) {
            return "locker/error";
        }
    }

    @PostMapping("/{id}/edit")
    public String edit(@PathVariable("id") String idString, @ModelAttribute LockerDto locker, Model model) {
        model.addAttribute("locker", null);
        if (!StringUtils.isNumeric(idString)) {
            return "locker/error";
        }

        int id = Integer.parseInt(idString);
        LockerDto updatedLocker = lockerService.update(id, locker);
        model.addAttribute("locker", updatedLocker);
        return "locker/details";
    }

    @GetMapping("/add")
    public String showAddLockerForm(Model model) {
        model.addAttribute("locker", new LockerDto());
        return "locker/add";
    }

    @PostMapping("/add")
    public String addLocker(@ModelAttribute LockerDto locker, Model model) {
        LockerDto savedLocker = lockerService.save(locker);
        model.addAttribute("locker", savedLocker);
        return "locker/details";
    }

    @DeleteMapping("/{id}/delete")
    @ResponseBody
    public String deleteLocker(@PathVariable("id") String idString) {
        if (!StringUtils.isNumeric(idString)) {
            return "Invalid ID";
        }

        int id = Integer.parseInt(idString);
        try {
            lockerService.delete(id);
            return "Locker deleted successfully";
        } catch (NoResultException e) {
            return "Locker not found";
        } catch (Exception e) {
            return "Error deleting locker";
        }
    }
}
