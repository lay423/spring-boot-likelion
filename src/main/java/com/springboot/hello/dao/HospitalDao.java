package com.springboot.hello.dao;

import com.springboot.hello.domain.dto.Hospital;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Component
public class HospitalDao {
    private final JdbcTemplate jdbcTemplate;

    public HospitalDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    RowMapper<Hospital> rowMapper = (rs, rowNum) -> {
        Hospital hospital = new Hospital();
        hospital.setId(rs.getInt("id"));
        hospital.setOpenServiceName(rs.getString("open_service_name"));
        hospital.setHospitalName(rs.getString("hospital_name"));
        hospital.setLicenseDate(rs.getTimestamp("license_date").toLocalDateTime());
        hospital.setTotalAreaSize(rs.getFloat("total_area_size"));

        hospital.setOpenLocalGovernmentCode(rs.getInt("open_local_government_code"));
        hospital.setManagementNumber(rs.getString("management_number"));

        hospital.setBusinessStatus(rs.getInt("business_status"));
        hospital.setBusinessStatusCode(rs.getInt("business_status_code"));
        hospital.setPhone(rs.getString("phone"));
        hospital.setFullAddress(rs.getString("full_address"));
        hospital.setRoadNameAddress(rs.getString("road_name_address"));

        hospital.setBusinessTypeName(rs.getString("business_type_name"));
        hospital.setHealthcareProviderCount(rs.getInt("healthcare_provider_count"));
        hospital.setPatientRoomCount(rs.getInt("patient_room_count"));
        hospital.setTotalNumberOfBeds(rs.getInt("total_number_of_beds"));

        return hospital;
    } ;

    public Hospital findById(int id) {
        return this.jdbcTemplate.queryForObject("select * from `likelion-db`.`hospital_db` where id=?", rowMapper,id);
    }

    public void deleteAll() {
        this.jdbcTemplate.update("delete from `likelion-db`.`hospital_db`;");
    }
    public int deleteById(int id) {
        return this.jdbcTemplate.update("delete from `likelion-db`.`hospital_db` where id=?", id);
    }

    public int getCount() {
        String sql = "select count(id) from `likelion-db`.`hospital_db`;";
        return this.jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public void add(Hospital hospital) {
        String sql = "INSERT INTO `likelion-db`.`hospital_db` VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
