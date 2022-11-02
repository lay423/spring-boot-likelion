package com.springboot.hello.service;

import com.springboot.hello.dao.HospitalDao;
import com.springboot.hello.domain.dto.Hospital;
import com.springboot.hello.parser.ReadLineContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
public class HospitalService {


    private final ReadLineContext<Hospital> hospitalReadLineContext;

    private final HospitalDao hospitalDao;
    public HospitalService(ReadLineContext<Hospital> hospitalReadLineContext, HospitalDao hospitalDao) {
        this.hospitalReadLineContext = hospitalReadLineContext;
        this.hospitalDao = hospitalDao;
    }

    public String getData(Hospital hospital) {
        String s = "병원이름:" + hospital.getHospitalName();
        s += "주소: " + hospital.getFullAddress();
        s += "도로명주소: " + hospital.getRoadNameAddress();
        s += "의료진 수: " + hospital.getHealthcareProviderCount();
        s += "병상 수: " + hospital.getTotalNumberOfBeds();
        s += "면적: " + hospital.getTotalAreaSize();
        if (hospital.getBusinessStatusCode() == 13) {
            s += "폐업여부: 영업중";
        } else if (hospital.getBusinessStatusCode() == 3) {
            s += "폐업여부: 폐업";
        } else {
            s += "폐업여부: 오류";
        }
        return s;
    }

    @Transactional
    public int insertLargeVolumeHospitalData(String filename) {
        int cnt=0;
        try {
            List<Hospital> hospitalList = hospitalReadLineContext.readByLine(filename);
            for (Hospital hospital : hospitalList) {
                try {
                    this.hospitalDao.add(hospital);
                    cnt++;
                } catch (Exception e) {
                    System.out.printf("id:%d 레코드에 문제가 있습니다.",hospital.getId());
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return cnt;
    }
}
