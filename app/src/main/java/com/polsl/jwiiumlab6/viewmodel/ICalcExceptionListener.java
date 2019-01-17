package com.polsl.jwiiumlab6.viewmodel;

/** Interface used to transfer message provided by caught exception to the GUI, and thus to the user.
 * @author Karol Kozuch Group 4 Section 8
 * @version 1.0.0
 */
public interface ICalcExceptionListener {
    /**
     * Called when exception occurs. Passes the message connected with it.
     * @param exceptionMsg Message describing the exception.
     */
    void noticeException(String exceptionMsg);
}
