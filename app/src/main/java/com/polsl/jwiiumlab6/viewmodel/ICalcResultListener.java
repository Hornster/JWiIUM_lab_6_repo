package com.polsl.jwiiumlab6.viewmodel;

/** Interface used to retrieve calculation result. Observer pattern.
 * @author Karol Kozuch Group 4 Section 8
 * @version 1.0.0
 */
public interface ICalcResultListener {
    /**
     * Called by calculation performing object to retrieve calculations result.
     * @param resultValue Result value of the calculations.
     */
    void retrieveResult(float resultValue);
}
