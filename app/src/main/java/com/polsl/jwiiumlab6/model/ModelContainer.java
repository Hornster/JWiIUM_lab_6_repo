package com.polsl.jwiiumlab6.model;

import android.graphics.ColorSpace;

import java.util.LinkedList;
import java.util.List;

import pl.polsl.controller.calculation.CalculationModule;
import pl.polsl.model.queryHistory.QueryManager;
import pl.polsl.model.queryHistory.SingleQuery;

/** Manages data containers required for calculations and triggers the process itself.
 * @author Karol Kozuch Group 4 Section 8
 * @version 1.0
 */
public class ModelContainer {
    /**
     * Main calculation module.
     */
    private CalculationModule calculationModule = new CalculationModule();
    /**
     * Input data testing class.
     */
    private DataTester dataTester = new DataTester();
    /**
    Instance of this singleton.
     */
    private static ModelContainer modelContainer;
    /**
     * The query manager that manages all the queries made in this session. All of them. All.
     */
    private QueryManager queryManager = new QueryManager();

    private ModelContainer()
    {
        calculationModule.addListener(queryManager);
    }

    /**
     * If singleton hasn't got any instance so far - creates one. Then returns it.
     * @return Instance of this singleton.
     */
    public static ModelContainer getInstance()
    {
        if(modelContainer == null)
        {
            modelContainer = new ModelContainer();
        }
        return modelContainer;
    }

    /**
     * Gathers all queries made so far and returns list of them.
     * @return List containing all queries made so far. List is empty if none made yet.
     */
    public List<SingleQuery> getQueries()
    {
        List<SingleQuery> queries = new LinkedList<>();
        for(SingleQuery query : queryManager.getQueryHistory())
        {
            queries.add(query);
        }

        return queries;
    }

    public DataTester getDataTester()
    {
        return dataTester;
    }

    public CalculationModule getCalculationModule()
    {
        return calculationModule;
    }
}
