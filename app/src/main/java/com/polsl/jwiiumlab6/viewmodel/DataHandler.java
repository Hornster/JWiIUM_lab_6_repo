package com.polsl.jwiiumlab6.viewmodel;

import pl.polsl.model.queryHistory.SingleQuery;

/** Manages data containers required for calculations and triggers the process itself.
 * @author Karol Kozuch Group 4 Section 8
 * @version 1.0.0
 */
public class DataHandler implements IQueryDataAcceptor {
    private SingleQuery currentQuery = new SingleQuery();
    /**
     * Stores the data testing object.
     */
    private DataTester dataTester = new DataTester();

    @Override
    public boolean setCalcData(String method, String accuracy) {
        if(dataTester.chkCalcMethodParams(method, accuracy))
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
        if(!dataTester.chkIntegralParams(integralFormula, integralBegin, integralEnd))
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
        return dataTester.retreiveErrorMsg();
    }

}
