package com.example.appcircle_sample_android;
import org.junit.Test;
import java.util.Date;

import static org.junit.Assert.assertEquals;
public class DateUtilsTest {

        @Test
        public void dateUtilsFormat_isCorrect() throws Exception {
            long epoc = 1446885450; //7th Nov 2015
            Date date = DateUtils.epocSecondsToDate(epoc);
            assertEquals("Date time in millis is wrong",
                    epoc * 1000, date.getTime());
            String day = DateUtils.dateToDayDateString(date, true);
            assertEquals("Day is wrong", "SAT", day);

    }
}
