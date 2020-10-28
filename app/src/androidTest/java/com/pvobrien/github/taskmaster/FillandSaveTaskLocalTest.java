package com.pvobrien.github.taskmaster;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class FillandSaveTaskLocalTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void fillandSaveTaskTest() {
        ViewInteraction materialButton = onView(
                allOf(withId(R.id.addTask), withText("Add TaskLocal"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.taskName),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        textInputEditText.perform(replaceText("New TaskLocal!"), closeSoftKeyboard());

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.taskDetails),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        textInputEditText2.perform(replaceText("to Do"), closeSoftKeyboard());

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.taskStatusTv),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        textInputEditText3.perform(replaceText("next up!"), closeSoftKeyboard());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.addTaskButton), withText("Add TaskLocal"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.taskNameTextView), withText("New TaskLocal!"),
                        withParent(allOf(withId(R.id.frameLayout),
                                withParent(withId(R.id.tasksRv)))),
                        isDisplayed()));
        textView.check(matches(withText("New TaskLocal!")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.taskDetailTextView), withText("to Do"),
                        withParent(allOf(withId(R.id.frameLayout),
                                withParent(withId(R.id.tasksRv)))),
                        isDisplayed()));
        textView2.check(matches(withText("to Do")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.taskStateTextView), withText("next up!"),
                        withParent(allOf(withId(R.id.frameLayout),
                                withParent(withId(R.id.tasksRv)))),
                        isDisplayed()));
        textView3.check(matches(withText("next up!")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
