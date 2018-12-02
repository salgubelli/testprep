package ee.testprep.fragment.practice;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import ee.testprep.MainActivity;
import ee.testprep.R;
import ee.testprep.fragment.OnFragmentInteractionListener;

public class YearFragment extends Fragment{

    private static String className = YearFragment.class.getSimpleName();
    private static final String ARG_PARAM1 = "param1";
    private ArrayList<String> years;
    private OnFragmentInteractionListener mListener;

    public YearFragment() {
        // Required empty public constructor
    }

    public static YearFragment newInstance(ArrayList<String> years) {
        YearFragment fragment = new YearFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, years);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            years = (ArrayList<String>) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_year, container, false);

        final ListView listview = view.findViewById(R.id.lv_year);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(container.getContext(), R.layout.listview_item, R.id.icon, years);

        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                onButtonPressed(MainActivity.STATUS_PRACTICE_YEAR_XX, item);
            }

        });

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if( keyCode == KeyEvent.KEYCODE_BACK && keyEvent.getAction() == KeyEvent.ACTION_UP) {
                    getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return true;
                }
                return false;
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(int status, String year) {
        if (mListener != null) {
            mListener.onFragmentInteraction(status, year);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
