package com.antonyshyn.accountingSystem.resource;

import com.antonyshyn.accountingSystem.dto.ComputerDTO;
import com.antonyshyn.accountingSystem.entity.Computer;
import com.antonyshyn.accountingSystem.repo.StudyRoomRepository;
import com.antonyshyn.accountingSystem.service.ComputerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<Computer>> findComputerByUserId(@PathVariable("id") Long id) {
        List<Computer> computers = computerService.findAllComputersByRoom(id);
        return new ResponseEntity<>(computers, HttpStatus.OK);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<List<Computer>> findAllComputers() {
        List<Computer> computers = computerService.findAllComputers();
        return new ResponseEntity<>(computers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<Computer> findComputerById(@PathVariable("id") Long id) {
        Computer computer = computerService.findComputerById(id);
        return new ResponseEntity<>(computer, HttpStatus.OK);
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<Computer> addComputer(@RequestBody ComputerDTO computer) {
        Computer newComputer = computerService.addComputer(computer);
        return new ResponseEntity<>(newComputer, HttpStatus.CREATED);

    }

    @PutMapping()
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<Computer> updateComputer(@RequestBody Computer computer) {
        Computer updatedComputer = computerService.updateComputer(computer);
        return new ResponseEntity<>(updatedComputer, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<?> deleteComputer(@PathVariable("id") Long id) {
        computerService.deleteComputer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
