package com.polsl.jwiiumlab6.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.polsl.jwiiumlab6.R;
import com.polsl.jwiiumlab6.viewmodel.DataHandler;
import com.polsl.jwiiumlab6.viewmodel.IActionTrigger;
import com.polsl.jwiiumlab6.viewmodel.ICalcExceptionListener;
import com.polsl.jwiiumlab6.viewmodel.ICalcResultListener;
import com.polsl.jwiiumlab6.viewmodel.IQueryDataAcceptor;

/** Main activity of the app. Responsible for accepting data from user.
 * @author Karol Kozuch Group 4 Section 8
 * @version 1.0.0
 */
public class MainActivity extends AppCompatActivity implements ICalcResultListener, ICalcExceptionListener {
    /**
     * Reference to calculations trigger button.
     */
    private Button calcTriggerButton;
    /**
     * Reference to button that triggers showing the query history (and changing activity).
     */
    private Button queryHistoryButton;
    /**
     * Text field with integral formula.
     */
    private EditText formulaTextBox;
    /**
     * Text field with beginning of the approximation range.
     */
    private EditText rangeBeginTextBox;
    /**
     * Text field with end of the approximation range.
     */
    private EditText rangeEndTextBox;
    /**
     * Text field with approximation method.
     */
    private EditText approxMethodTextBox;
    /**
     * Text field with amount of geometrical shapes used during approximation.
     */
    private EditText accuracyTextBox;
    /**
     * Label that shows to the user the calculation result.
     */
    private TextView resultShowingLabel;
    /**
     * Contains class that allows for sending data to model (indirectly, through view-model)
     */
    private IQueryDataAcceptor dataHandler = new DataHandler();
    private IActionTrigger startCalculations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startCalculations = (DataHandler)dataHandler;               //Safe since DataHandler implements both interfaces.
        //Regain references to UI elements before waking up the app.
        calcTriggerButton = findViewById(R.id.triggerCalculationButton);
        queryHistoryButton = findViewById(R.id.showQueriesButton);
        formulaTextBox = findViewById(R.id.formulaTextBox);
        rangeBeginTextBox = findViewById(R.id.rangeBeginTextBox);
        rangeEndTextBox = findViewById(R.id.rangeEndTextBox);
        approxMethodTextBox = findViewById(R.id.approxMethodTextBox);
        accuracyTextBox = findViewById(R.id.accuracyTextBox);
        resultShowingLabel = findViewById(R.id.resultShowingLabel);
        //Assign triggers
        calcTriggerButton.setOnClickListener(new HandleCalcButton());
            //placeholder for queryHistoryButton
    }

    @Override
    protected void onStop()
    {
        super.onStop();

    }

    @Override
    public void noticeException(String exceptionMsg) {
        Toast.makeText(this, exceptionMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void retrieveResult(Double resultValue) {
        resultShowingLabel.setText(resultValue.toString());
    }

    /**
     * Event handler - when calculation triggering button is pressed - this class will handle it.
     */
    class HandleCalcButton implements View.OnClickListener
    {
        /**
         * Retrieves arguments and passes them to view-model for processing. If arguments are correct - triggers calculation process.
         * @param v
         */
        @Override
        public void onClick(View v) {
            String arg1, arg2, arg3;    //Temporary string variables for easier debugging

            arg1 = formulaTextBox.getText().toString();
            arg2 = rangeBeginTextBox.getText().toString();
            arg3 = rangeEndTextBox.getText().toString();
            if(!dataHandler.setIntegralData(arg1, arg2, arg3))
            {
                return;    //At least one of arguments was wrong. Stop calculation procedure.
            }

            arg1 = approxMethodTextBox.getText().toString();
            arg2 = accuracyTextBox.getText().toString();
            if(!dataHandler.setCalcData(arg1, arg2))
            {
                return;    //At least one of arguments was wrong. Stop calculation procedure.
            }
            startCalculations.triggerAction();  //Provided data was correct - perform calculation.
        }
    }
}
