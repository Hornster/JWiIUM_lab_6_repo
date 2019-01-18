package com.polsl.jwiiumlab6.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.polsl.jwiiumlab6.R;
import com.polsl.jwiiumlab6.viewmodel.DataHandler;
import com.polsl.jwiiumlab6.viewmodel.ICalcExceptionListener;
import com.polsl.jwiiumlab6.viewmodel.ICalcResultListener;
import com.polsl.jwiiumlab6.viewmodel.IQueryDataAcceptor;

/** Main activity of the app. Responsible for accepting data from user.
 * @author Karol Kozuch Group 4 Section 8
 * @version 1.0.0
 */
public class MainActivity extends AppCompatActivity implements ICalcResultListener, ICalcExceptionListener {

    /**
     * Contains class that allows for sending data to model (indirectly, through view-model)
     */
    private IQueryDataAcceptor dataHandler = new DataHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void noticeException(String exceptionMsg) {
        Toast.makeText(this, exceptionMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void retrieveResult(double resultValue) {

    }
}
