package com.telezone.service;

import com.telezone.model.DateUtils;
import com.telezone.model.Device;
import com.telezone.repository.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class InfoService {

    @Autowired
    private InfoRepository infoRepository;

    public List<Device> getDevice(){

        List<Object[]> list = infoRepository.getDevice();

        List<Device> arr = new ArrayList<>();
        for (Object[] obj :  list) {
            Device device = new Device();
            device.setPoint(String.valueOf(obj[0]));
            device.setFzh(String.valueOf(obj[1]));
            device.setName((String)obj[2]);
            device.setDuty((String)obj[3]);
            device.setDep((String)obj[4]);
            device.setWz(String.valueOf(obj[6]));
            device.setInTime(DateUtils.format((Date) obj[9]));
            device.setOutTime(DateUtils.format((Date) obj[10]));
            device.setState((Integer)obj[11]);
            device.setK1((Integer)obj[12]);
            device.setAlarm((Integer)obj[13]);
            device.setZts(DateUtils.format((Date) obj[14]));

            arr.add(device);
        }
        return arr;
    }
}
