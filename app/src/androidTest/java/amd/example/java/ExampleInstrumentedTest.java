package amd.example.java;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @org.junit.Test
    public void useAppContext() {
        // Context of the app under TestAA.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.java", appContext.getPackageName());
    }
}