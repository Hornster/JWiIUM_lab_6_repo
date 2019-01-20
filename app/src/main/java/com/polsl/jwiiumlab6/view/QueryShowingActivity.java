package com.polsl.jwiiumlab6.view;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.polsl.jwiiumlab6.R;
import com.polsl.jwiiumlab6.model.ModelContainer;
import com.polsl.jwiiumlab6.viewmodel.IQueryHistoryRetreiver;
import com.polsl.jwiiumlab6.viewmodel.QueryHistoryRetriever;

import java.util.List;

/**
 * Activity class responsible for maintaining view that shows query history to the user.
 */
public class QueryShowingActivity extends AppCompatActivity {
    /**
     * Object that will retrieve the queries history
     */
    IQueryHistoryRetreiver queryHistoryRetriever;
    /**
     * Strings that describe queries will be stored here.
     */
    private List<String> queries;
    /**
     * Adapter that will be used to show stored queries to the user.
     */
    private ArrayAdapter<String> queriesAdapter;
    /**
     * reference to the UI object that shows queries to the user.
     */
    private ListView shownQueries;
    /**
     * A button that allows for returning from activity to caller.
     */
    private Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_showing);
        queryHistoryRetriever = new QueryHistoryRetriever();
    }
    @Override
    public void onStart()
    {
        super.onStart();
        shownQueries = findViewById(R.id.queriesListView);
        returnButton = findViewById(R.id.returnButton);
        //Assign events
        if(!returnButton.hasOnClickListeners()) {
            returnButton.setOnClickListener(new HandleReturnButton());
        }
    }
    @Override
    public void onResume()
    {
        super.onResume();
        Bundle extras = getIntent().getExtras();        //Retrieve the intent but do not use it - no useful info in there, currently.

        queries = queryHistoryRetriever.getQueryHistory();
        queriesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, queries);
        shownQueries.setAdapter(queriesAdapter);
        //queriesAdapter.notifyDataSetChanged();
    }

    /**
     * Class that handles the return button clicking and forces return to calling activity.
     */
    class HandleReturnButton implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            finish();
        }
    }
}
