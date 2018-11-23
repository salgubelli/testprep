package ee.testprep.learn;

import ee.testprep.R;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;

public class LearnFilterAdapter extends ArrayAdapter<LearnFilter>{

    public LearnFilterAdapter(@NonNull Context context, ArrayList<LearnFilter> filters) {
        super(context, 0, filters);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

/*
        // Get the data item for this position
        LearnFilter filter = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.learn_filter_item, parent, false);
        }
        // Lookup view for data population
        Button tvName = convertView.findViewById(R.id.filter);
        // Populate the data into the template view using the data object
        tvName.setText(filter.getFilterName());
        // Return the completed view to render on screen
        return convertView;
*/

        return null;
    }

}
