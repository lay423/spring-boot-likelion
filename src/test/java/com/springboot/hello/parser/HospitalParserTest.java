package com.springboot.hello.parser;

import com.springboot.hello.domain.dto.Hospital;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HospitalParserTest {
    String line1 = "\"1\",\"의원\",\"01_01_02_P\",\"3620000\",\"PHMA119993620020041100004\",\"19990612\",\"\",\"01\",\"영업/정상\",\"13\",\"영업중\",\"\",\"\",\"\",\"\",\"062-515-2875\",\"\",\"500881\",\"광주광역시 북구 풍향동 565번지 4호 3층\",\"광주광역시 북구 동문대로 24, 3층 (풍향동)\",\"61205\",\"효치과의원\",\"20211115113642\",\"U\",\"2021-11-17 02:40:00.0\",\"치과의원\",\"192630.735112\",\"185314.617632\",\"치과의원\",\"1\",\"0\",\"0\",\"52.29\",\"401\",\"치과\",\"\",\"\",\"\",\"0\",\"0\",\"\",\"\",\"0\",\"\"";
    String line2 = "\"770\",\"의원\",\"01_01_02_P\",\"5710000\",\"PHMA119974360079041100017\",\"19970706\",\"\",\"01\",\"영업/정상\",\"13\",\"영업중\",\"\",\"\",\"\",\"\",\"295-5001\",\"\",\"362856\",\"충청북도 청주시 서원구 분평동 1202번지 2호\",\"충청북도 청주시 서원구 월평로 69 (분평동)\",\"28792\",\"유림가정의학과의원\",\"20170905183709\",\"I\",\"2018-08-31 23:59:59.0\",\"의원\",\"244003.914471\",\"345700.272648\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"";

    @Autowired
    ReadLineContext<Hospital> hospitalReadLineContext;

    @Test
    void name() throws IOException {
        // 서버 환경에서 build할때 input 에러가 발생할 수 있다.
        // 어ㄷ에서든지 실행 할 수 있게 '짜는 것이 목표
        String filename = "C:\\Users\\A\\springedu\\hello\\전국 병의원 정보.csv";
        List<Hospital> hospitalList = hospitalReadLineContext.readByLine(filename);
        assertTrue(hospitalList.size() > 1000);
        assertTrue(hospitalList.size() > 10000);
    }

    @Test
    @DisplayName("csv 1줄을 Hospital로 잘 만드는지 Test")
    void convertToHospital(){
        HospitalParser hp = new HospitalParser();
        Hospital hospital = hp.parse(line1);

        assertEquals(1, hospital.getId());
        assertEquals("의원", hospital.getOpenServiceName());
        assertEquals(3620000,hospital.getOpenLocalGovernmentCode());
        assertEquals("PHMA119993620020041100004",hospital.getManagementNumber());
        assertEquals(LocalDateTime.of(1999,6,12,0,0,0), hospital.getLicenseDate());
        assertEquals(1, hospital.getBusinessStatus());
        assertEquals(13, hospital.getBusinessStatusCode());
        assertEquals("062-515-2875", hospital.getPhone());
        assertEquals("광주광역시 북구 풍향동 565번지 4호 3층", hospital.getFullAddress());
        assertEquals("광주광역시 북구 동문대로 24, 3층 (풍향동)", hospital.getRoadNameAddress());
        assertEquals("효치과의원", hospital.getHospitalName());
        assertEquals("치과의원", hospital.getBusinessTypeName());
        assertEquals(1, hospital.getHealthcareProviderCount());
        assertEquals(0, hospital.getPatientRoomCount());
        assertEquals(0, hospital.getTotalNumberOfBeds());
        assertEquals(52.29f, hospital.getTotalAreaSize());

    }
    @Test
    @DisplayName("파일 잘 쓰는지 Test")
    public void FileWrite() throws IOException {
        ReadLineContext<Hospital> hospitalLineReader
                = new ReadLineContext<>(new HospitalParser());
        String filename = "C:\\Users\\A\\springedu\\hello\\전국 병의원 정보.csv";
        FileWriterContext writer = new FileWriterContext();

        List<String> strings = new ArrayList<>();
        List<Hospital> hospitals = hospitalLineReader.readByLine(filename);
        strings.add("INSERT INTO `likelion-db`.`hospital_db` (`id`,`open_service_name`,`open_local_gorvernment_code`,`management_number`,`license_date`,`business_status`,`business_status_code`,`phone`,`full_address`,`road_name_address`,`hospital_name`,`business_type_name`,`healthcare_provider_count`,`patient_room_count`,`total_number_of_beds`,`total_area_size`)");

        for (Hospital hospital : hospitals) {
            strings.add("("+writer.fromTOString(hospital));
        }
        strings.add(";");
        writer.write(strings, "hospital_data.sql");

    }

}