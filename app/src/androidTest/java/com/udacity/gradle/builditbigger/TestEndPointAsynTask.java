package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;


import static org.junit.Assert.assertTrue;

/**
 * Created by Samir Thebti  on 30/10/16.
 * ----->> thebtisam@gmail.com <<-----
 */

@RunWith(AndroidJUnit4.class)
public class TestEndPointAsynTask {

    @Test
    public void testDoInBackground() throws Exception {
        MainActivityFragment fragment = new MainActivityFragment();
        fragment.testFlag = true;
        new EndPointAsynTask().execute(fragment);
        Thread.sleep(5000);
        assertTrue("Error: Fetched Joke = " + fragment.loadedJock, fragment.loadedJock != null);
    }
}
