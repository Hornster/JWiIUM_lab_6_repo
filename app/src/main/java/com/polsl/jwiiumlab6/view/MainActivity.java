package com.polsl.jwiiumlab6.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.polsl.jwiiumlab6.R;

/** Main activity of the app. Responsible for accepting data from user.
 * @author Karol Kozuch Group 4 Section 8
 * @version 1.0.0
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
