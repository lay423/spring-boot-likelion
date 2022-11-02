package com.springboot.hello.service;

import com.springboot.hello.domain.dto.Hospital;
import org.springframework.stereotype.Service;

@Service
public class HospitalService {
    public String getData(Hospital hospital) {
        String s = "병원이름:"+hospital.getHospitalName();
        s += "주소: "+hospital.getFullAddress();
        s += "도로명주소: "+hospital.getRoadNameAddress();
        s += "의료진 수: "+hospital.getHealthcareProviderCount();
        s += "병상 수: "+hospital.getTotalNumberOfBeds();
        s += "면적: "+hospital.getTotalAreaSize();
        if (hospital.getBusinessStatusCode() == 13) {
            s += "폐업여부: 영업중";
        } else if (hospital.getBusinessStatusCode() == 3) {
            s += "폐업여부: 폐업";
        }else {
            s += "폐업여부: 오류";
        }

        return s;
    }
}
