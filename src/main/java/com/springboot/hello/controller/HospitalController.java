package com.springboot.hello.controller;

import com.springboot.hello.dao.HospitalDao;
import com.springboot.hello.domain.dto.Hospital;
import com.springboot.hello.service.HospitalService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/hospital")
public class HospitalController {

    private final HospitalDao hospitalDao;
    private final HospitalService hospitalService;

    @GetMapping("/{id}")
    public ResponseEntity<Hospital> select(@PathVariable int id) {
        try {
            Hospital hospital = this.hospitalDao.findById(id);
            return ResponseEntity
                    .ok()
                    .body(hospital);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/details/{id}")
    public String selectDetails(@PathVariable int id) {
        return hospitalService.getData(hospitalDao.findById(id));
    }

    @PostMapping("/hospital")
    public Hospital postHospital(@RequestBody Hospital hospital) {
        hospitalDao.add(hospital);
        return hospitalDao.findById(hospital.getId());
    }

    @DeleteMapping("/hospital/{id}")
    public ResponseEntity<Integer> deleteById(@PathVariable int id) {
        return ResponseEntity
                .ok()
                .body(hospitalDao.deleteById(id));
    }
}
