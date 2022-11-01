package com.springboot.hello.dao;

import com.springboot.hello.domain.dto.Hospital;
import org.springframework.jdbc.core.JdbcTemplate;

public class HospitalDao {
    private final JdbcTemplate jdbcTemplate;

    public HospitalDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add(Hospital hospital) {
        String sql = "INSERT INTO `likelion-db`.`hospital_db` (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        this.jdbcTemplate.update(sql, hospital.getId()
                , hospital.getOpenServiceName()
                , hospital.getOpenLocalGovernmentCode()
                , hospital.getManagementNumber()
                , hospital.getLicenseDate()
                , hospital.getBusinessStatus()
                , hospital.getBusinessStatusCode()
                , hospital.getPhone()
                , hospital.getFullAddress()
                , hospital.getRoadNameAddress()
                , hospital.getHospitalName()
                , hospital.getBusinessTypeName()
                , hospital.getHealthcareProviderCount()
                , hospital.getPatientRoomCount()
                , hospital.getTotalNumberOfBeds()
                , hospital.getTotalAreaSize());

    }
}