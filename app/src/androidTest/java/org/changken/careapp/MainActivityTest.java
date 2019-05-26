package org.changken.careapp;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

import static android.support.test.espresso.matcher.RootMatchers.*;
import static org.hamcrest.Matchers.*;

import android.support.test.rule.ActivityTestRule;

import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import retrofit2.Retrofit;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testOnlyInputIdNumberLogin() {
        String idNumber = "5e4f84e8f";

        onView(withId(R.id.idNumber_edit_text))
                .perform(typeText(idNumber), closeSoftKeyboard());

        onView(withId(R.id.login_button))
                .perform(click());

        onView(withText(R.string.please_type_all_fields))
                .inRoot(withDecorView(not(mainActivityActivityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));

    }

    @Test
    public void testOnlyInputPasswordLogin() {
        String password = "12345678";

        onView(withId(R.id.password_edit_text))
                .perform(typeText(password), closeSoftKeyboard());

        onView(withId(R.id.login_button))
                .perform(click());

        onView(withText(R.string.please_type_all_fields))
                .inRoot(withDecorView(not(mainActivityActivityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    /**
     * 仍是有BUG
     */
    @Test
    public void testInputErrorIdNumberPwLogin() {
        String idNumber = "5e4f84e8f";
        String password = "12345678";

        onView(withId(R.id.idNumber_edit_text))
                .perform(typeText(idNumber), closeSoftKeyboard());

        onView(withId(R.id.password_edit_text))
                .perform(typeText(password), closeSoftKeyboard());

        onView(withId(R.id.login_button))
                .perform(click());

        onView(withId(R.id.progressBar))
                .check(matches(isDisplayed()));

        /*try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            fail("thread error");
        }

        onView(withText(R.string.login_failure_input_error))
                .inRoot(withDecorView(not(mainActivityActivityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));*/
    }

}
