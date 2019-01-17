package com.polsl.jwiiumlab6.viewmodel;

import com.polsl.jwiiumlab6.model.ModelContainer;

import java.util.LinkedList;
import java.util.List;

import pl.polsl.model.PredefinedCommunicates;
import pl.polsl.model.queryHistory.SingleQuery;
/** Retrieves queries made during the session and converts them to easily usable by UI controls form (String).
 * @author Karol Kozuch Group 4 Section 8
 * @version 1.0.0
 */
public class QueryHistoryRetriever implements IQueryHistoryRetreiver {
    /**
     * Converts SingleQuery objects to String form and groups these strings into a list. If no queries retrieved - puts an error message
     * into the list.
     * @return List containing either queries made during session or message explaining that no queries have been made yet.
     */
    @Override
    public List<String> getQueryHistory() {
        List<SingleQuery> queries = ModelContainer.getInstance().getQueries();
        List<String> queriesDescriptions = new LinkedList<>();

        if(queries.size() > 0) {
            for (SingleQuery query : queries) {
                query.setLineSeparator("\n");
                queriesDescriptions.add(query.toString());
            }
        }
        else
        {
            queriesDescriptions.add(PredefinedCommunicates.noQueriesMadeYet);
        }

        return queriesDescriptions;
    }
}
