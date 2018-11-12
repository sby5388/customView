package com.by5388.xw.searchframe;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author by5388  on 2018/11/8.
 */
public class StringTest {
    public static final String[] OLD_STRINGS = {"  ab c", "12 22 22", "00    9  ", "88 a s0","  "};
    public static final String[] NEW_STRINGS = {"abc", "122222", "009", "88as0",""};
    public static final int MAX_COUNT = 5;

    @Test
    public void testRemoveSpace() {
        for (int i = 0; i < MAX_COUNT; i++) {
            assertEquals(NEW_STRINGS[i], removeSpace(OLD_STRINGS[i]));
        }
    }

    private String removeSpace(String oldString) {
        String newString = oldString.replaceAll(" ", "");
        return newString;
    }
}
