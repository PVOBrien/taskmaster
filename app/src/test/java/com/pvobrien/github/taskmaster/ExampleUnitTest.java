package com.pvobrien.github.taskmaster;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void emptyTaskTest(){
        TaskLocal testTaskLocal = null;
        assertNull("nothing here", testTaskLocal);
    }

    @Test
    public void taskCreatorTest(){
        TaskLocal testTaskLocal = new TaskLocal("a task", "something to do", "In Progress");
        assertEquals("a task title should be present", "a task", testTaskLocal.taskTitle);
        assertEquals("task details should be present", "something to do", testTaskLocal.taskDetails);
        assertEquals("task status should be present", "In Progress", testTaskLocal.taskStateOfDoing);
    }
}