package com.neohg.jsonlistviewbasico;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.neohg.jsonlistviewbasico.R;

import android.os.Bundle;
import android.app.ListActivity;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends ListActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String,String>>();

        //get data
        JSONObject json = JSONfunctions.getJSONfromURL("http://api.geonames.org/earthquakesJSON?formatted=true&north=44.1&south=-9.9&east=-22.4&west=55.2&username=neohgx&style=full");
      
        try {
        	JSONArray earthquakes = json.getJSONArray("earthquakes");

        	for (int i = 0; i < earthquakes.length(); i++) {
        		HashMap<String, String> map = new HashMap<String, String>();
        		JSONObject jobject = earthquakes.getJSONObject(i);

        		map.put("id", String.valueOf(i));
        		map.put("name", "Earthquake name: " + jobject.getString("eqid"));
        		map.put("magnitude", "Magnitude: " + jobject.getString("magnitude"));
        		mylist.add(map);
        	}
    	} catch (JSONException e) {
        	Log.e("log_tag","Error Parsing data" + e.toString());
    	}
        
        ListAdapter adapter = new SimpleAdapter(
			this, 
			mylist, 
			R.layout.list_item_layout, 
			new String[] { "name", "magnitude" }, 
			new int[] { R.id.item_title, R.id.item_subtitle 
        });

        setListAdapter(adapter);

        final ListView listview = getListView();
        
        listview.setTextFilterEnabled(true);
       
    }
}
