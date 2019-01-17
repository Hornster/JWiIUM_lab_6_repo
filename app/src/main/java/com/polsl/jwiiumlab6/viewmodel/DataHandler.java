package com.polsl.jwiiumlab6.viewmodel;

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
    List<ICalcResultListener> resultListeners = new LinkedList<>();
    /**
     * Contains list of listeners for calculation connected exceptions.
     */
    List<ICalcExceptionListener> calcExceptionListeners = new LinkedList<>();
    /**
     * Single query object that temporarily stores info about next query.
     */
    private SingleQuery currentQuery = new SingleQuery();


    @Override
    public boolean setCalcData(String method, String accuracy) {
        if(!ModelContainer.getInstance().getDataTester().chkCalcMethodParams(method, accuracy))
        {
            return false;
        }
        int readyAccuracy = Integer.parseInt(accuracy);
        currentQuery.setAccuracy(readyAccuracy);
        currentQuery.setMethod(method.charAt(0));

        return true;
    }

    @Override
    public boolean setIntegralData(String integralFormula, String integralBegin, String integralEnd) {
        if(!ModelContainer.getInstance().getDataTester().chkIntegralParams(integralFormula, integralBegin, integralEnd))
        {
            return false;
        }

        currentQuery.setMathFunction(integralFormula);
        double parsedRangePart = Double.parseDouble(integralBegin);
        currentQuery.setRangeBegin(parsedRangePart);
        parsedRangePart = Double.parseDouble(integralEnd);
        currentQuery.setRangeEnd(parsedRangePart);

        return true;
    }

    @Override
    public String retrieveErrorMsg() {
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

    @Override
    public void triggerAction() {
        try {
            double result = ModelContainer.getInstance().getCalculationModule().performCalculation();
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
