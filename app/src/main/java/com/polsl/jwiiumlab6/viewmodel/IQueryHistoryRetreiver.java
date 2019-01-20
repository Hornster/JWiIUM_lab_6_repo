package com.polsl.jwiiumlab6.viewmodel;


import java.util.List;

/** Interface used to retrieve query history by the view.
 * @author Karol Kozuch Group 4 Section 8
 * @version 1.0.0
 */
public interface IQueryHistoryRetreiver {
    /**
     * Called in order to retrieve view-friendly form of query history.
     * @return List containing all made so far queries. Each query data is converted to a single String in the list and
     * ready to be shown in a UI control.
     */
    List<String> getQueryHistory();
}
