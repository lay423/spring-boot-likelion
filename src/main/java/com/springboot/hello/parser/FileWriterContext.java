package com.springboot.hello.parser;

import com.springboot.hello.domain.dto.Hospital;

import java.io.*;
import java.util.List;

public class FileWriterContext {
    public void write(List<String> strs, String filename) {
        File file = new File(filename);

        try {
            BufferedWriter writer
                    = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
            for (String str : strs) {
                writer.write(str);
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String fromTOString(Hospital hospital) {
        return hospital.getId()
                + ",'"+ hospital.getOpenServiceName()
                + "',"+ hospital.getOpenLocalGovernmentCode()
                + ",'"+ hospital.getManagementNumber()
                + "','"+ hospital.getLicenseDate()
                + "',"+ hospital.getBusinessStatus()
                + ","+ hospital.getBusinessStatusCode()
                + ",'"+ hospital.getPhone()
                + "','"+ hospital.getFullAddress()
                + "','"+ hospital.getRoadNameAddress()
                + "','"+ hospital.getHospitalName()
                + "','"+ hospital.getBusinessTypeName()
                + "',"+ hospital.getHealthcareProviderCount()
                + ","+ hospital.getPatientRoomCount()
                + ","+ hospital.getTotalNumberOfBeds()
                + ","+ hospital.getTotalAreaSize()+ "),\n";
    }
}

