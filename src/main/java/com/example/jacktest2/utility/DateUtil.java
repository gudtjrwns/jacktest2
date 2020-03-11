package com.example.jacktest2.utility;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateUtil {

    // 현재 날짜 - timestamp
    public Timestamp currentDateInTimestamp() {

        Date newDate = new Date();
        Timestamp sqlDate = new Timestamp(newDate.getTime());

        return sqlDate;
    }


    // 현재 날짜 - string
    public String timestampToString(Timestamp timeValue) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String stringTime = sdf.format(timeValue);

        return stringTime;
    }

}
