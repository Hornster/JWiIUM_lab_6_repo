package com.polsl.jwiiumlab6.viewmodel;

import android.graphics.ColorSpace;

import com.polsl.jwiiumlab6.model.DataTester;
import com.polsl.jwiiumlab6.model.ModelContainer;

import java.util.LinkedList;
import java.util.List;

import pl.polsl.model.exceptions.IntegralCalculationException;
import pl.polsl.model.queryHistory.SingleQuery;

/** Manages data containers required for calculations and triggers the process itself.
 * @author Karol Kozuch Group 4 Section 8
 * @version 1.0.0
 */
public class DataHandler implements IQueryDataAcceptor, IActionTrigger {
    /**
     * Contains list of listeners for the calculations results.
     */
    private List<ICalcResultListener> resultListeners = new LinkedList<>();
    /**
     * Contains list of listeners for calculation connected exceptions.
     */
    private List<ICalcExceptionListener> calcExceptionListeners = new LinkedList<>();
    /**
     * Single query object that temporarily stores info about next query.
     */
    private SingleQuery currentQuery = new SingleQuery();

    /**
     * Saves the configuration for calculation of the approximation method.
     * @param method Character indicating requested approximation method.
     * @param accuracy Amount of geometrical shapes used in approximation.
     * @return FALSE if provided data was incorrect. TRUE otherwise.
     */
    @Override
    public boolean setCalcData(String method, String accuracy) {
        if(!ModelContainer.getInstance().getDataTester().chkCalcMethodParams(method, accuracy))
        {
            String exception = retrieveErrorMsg();
            for(ICalcExceptionListener listener : calcExceptionListeners)
            {
                listener.noticeException(exception);
            }
            return false;
        }
        int readyAccuracy = Integer.parseInt(accuracy);
        currentQuery.setAccuracy(readyAccuracy);
        currentQuery.setMethod(method.charAt(0));

        return true;
    }

    /**
     * Saves the configuration for calculation of the integral data.
     * @param integralFormula Formula of integral that is used in calculations.
     * @param integralBegin Beginning of the range of the finite integral.
     * @param integralEnd End of the range of the finite integral.
     * @return FALSE if provided data was incorrect. TRUE otherwise.
     */
    @Override
    public boolean setIntegralData(String integralFormula, String integralBegin, String integralEnd) {
        if(!ModelContainer.getInstance().getDataTester().chkIntegralParams(integralFormula, integralBegin, integralEnd))
        {
            String exception = retrieveErrorMsg();
            for(ICalcExceptionListener listener : calcExceptionListeners)
            {
                listener.noticeException(exception);
            }
            return false;
        }

        currentQuery.setMathFunction(integralFormula);
        double parsedRangePart = Double.parseDouble(integralBegin);
        currentQuery.setRangeBegin(parsedRangePart);
        parsedRangePart = Double.parseDouble(integralEnd);
        currentQuery.setRangeEnd(parsedRangePart);

        return true;
    }

    /**
     * Assigns error listener which will be informed about errors whenever they happen.
     * @param listener New listener.
     */
    @Override
    public void registerErrorListener(ICalcExceptionListener listener) {
        calcExceptionListeners.add(listener);
    }

    /**
     * Retrieves the error message from the checking class.
     * @return String containing the message describing the error that has occurred.
     */
    private String retrieveErrorMsg() {
        return ModelContainer.getInstance().getDataTester().retreiveErrorMsg();
    }

    /**
     * Adds another listener to the list. From now on it shall be informed each time new calculation is performed about its results.
     * @param listener Listener to add.
     */
    public void registerCalcResultListener(ICalcResultListener listener)
    {
        resultListeners.add(listener);
    }

    /**
     * Triggers the calculation process, passing required data.
     */
    @Override
    public void triggerAction() {
        try {
            double result = ModelContainer.getInstance().getCalculationModule().performCalculation(currentQuery);
            for (ICalcResultListener listener : resultListeners) {
                listener.retrieveResult(result);
            }
        } catch (IntegralCalculationException e) {

            for (ICalcExceptionListener listener : calcExceptionListeners) {
                listener.noticeException(e.getMessage());
            }
            e.printStackTrace();
        }
    }
}
