package com.by5388.xw.searchframe;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;

/**
 * @author by5388  on 2018/11/10.
 */
public class DateTest {
    @Test
    public void testDate() {
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2018, 11, 25);
        assertEquals(calendar1.get(Calendar.DATE), calendar2.get(Calendar.DATE));

    }
}
