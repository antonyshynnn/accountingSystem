package com.antonyshyn.accountingSystem.resource;

import com.antonyshyn.accountingSystem.entity.Computer;
import com.antonyshyn.accountingSystem.entity.StudyRoom;
import com.antonyshyn.accountingSystem.service.ComputerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/computers")
public class ComputerController {
    private final ComputerService computerService;

    public ComputerController(ComputerService computerService) {
        this.computerService = computerService;
    }

    @GetMapping("/room/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<List<Computer>>  findToyByUserId (@PathVariable("id") Long id) {
        System.out.println(id);
            List<Computer> toys = computerService.findAllComputersByRoom(id);
            return new ResponseEntity<>(toys, HttpStatus.OK);
        }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<List<Computer>>  findAllComputers () {
        List<Computer> toys = computerService.findAllComputers();
        return new ResponseEntity<>(toys, HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<Computer> addToy(@RequestBody Computer computer) {
        Computer newComputer = computerService.addComputer(computer);
        return new ResponseEntity<>(newComputer, HttpStatus.CREATED);

    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<Computer> updateComputer(@RequestBody Computer computer) {
        Computer updatedComputer = computerService.updateComputer(computer);
        return new ResponseEntity<>(updatedComputer, HttpStatus.OK);
    }

    @PutMapping("/report")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<Computer> updateReport(@RequestBody Computer computer) {
        Computer updatedComputer = computerService.updateComputer(computer);
        return new ResponseEntity<>(updatedComputer, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<?> deleteToy(@PathVariable("id") Long id) {
        computerService.deleteComputer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
