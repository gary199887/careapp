package org.changken.careapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.app.AlertDialog;

import org.changken.careapp.tools.Helper;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class HelperTest {
    private static Context context;
    private static String userId = "C876387631";
    private static String userRecordId = "vekokfwfebeevb1";

    @BeforeClass
    public static void beforeClass() {
        context = InstrumentationRegistry.getTargetContext();
    }

    @Before
    public void before() {
        Helper.loginProcess(context, userId, userRecordId);
    }

    @Test
    public void testIsLogin() {
        assertTrue(Helper.isLogin(context));
    }

    @Test
    public void testGetUserId() {
        assertEquals(userId, Helper.getUserId(context));
    }

    @Test
    public void testGetUserRecordId() {
        assertEquals(userRecordId, Helper.getUserRecordId(context));
    }

    @Test
    public void testParseDateToDay() throws ParseException {
        String dateString = "2019-05-26";
        String day = "7";

        assertEquals(day, Helper.parseDateToDay(dateString));
    }

    @Test(expected = ParseException.class)
    public void testParseDateToDayError() throws ParseException {
        String dateString = "2019--26";
        String day = "7";

        assertEquals(day, Helper.parseDateToDay(dateString));
    }

    @After
    public void after() {
        Helper.logoutProcess(context);
    }

    @AfterClass
    public static void afterClass() {
        context = null;
    }
}
