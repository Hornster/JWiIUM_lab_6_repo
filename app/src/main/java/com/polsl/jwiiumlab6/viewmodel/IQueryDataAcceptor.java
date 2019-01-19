package com.polsl.jwiiumlab6.viewmodel;

/** Interface used by the view to send data required for calculation performing to viewmodel.
 * @author Karol Kozuch Group 4 Section 8
 * @version 1.0.0
 */
public interface IQueryDataAcceptor {
    /**
     * Used for passing calculation method data to view model.
     * @param method Character indicating requested approximation method.
     * @param accuracy Amount of geometrical shapes used in approximation.
     * @return TRUE if passed values were correct.FALSE otherwise.
     */
    boolean setCalcData(String method, String accuracy);

    /**
     * Used for passing integral data to view model.
     * @param integralFormula Formula of integral that is used in calculations.
     * @param integralBegin Beginning of the range of the finite integral.
     * @param integralEnd End of the range of the finite integral.
     * @return TRUE if passed values were correct. FALSE otherwise.
     */
    boolean setIntegralData(String integralFormula, String integralBegin, String integralEnd);

    /**
     * Used to retrieve error description. Registers a listener that will be informed whenever an error occurs.
     */
    void registerErrorListener(ICalcExceptionListener listener);
}
