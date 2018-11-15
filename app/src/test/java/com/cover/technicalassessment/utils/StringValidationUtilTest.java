package com.cover.technicalassessment.utils;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StringValidationUtilTest {

    @Test
    public void testhasMinimumRequiredTrue() {
        String test = "123 Main Street";
        int minRequired = 8;

        boolean result = StringValidationUtil.hasMinimumRequired(test, minRequired);
        assertTrue(result);
    }

    @Test
    public void testhasMinimumRequiredFalse() {
        String test = "123 Ma";
        int minRequired = 8;

        boolean result = StringValidationUtil.hasMinimumRequired(test, minRequired);
        assertFalse(result);
    }
}
