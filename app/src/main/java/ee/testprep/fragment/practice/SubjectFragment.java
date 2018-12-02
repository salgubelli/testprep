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

public class SubjectFragment extends Fragment {

    private static String className = SubjectFragment.class.getSimpleName();
    private static final String ARG_PARAM1 = "param1";
    private ArrayList<String> subjects;
    private OnFragmentInteractionListener mListener;

    public SubjectFragment() {
    }

    public static SubjectFragment newInstance(ArrayList<String> subjects) {
        SubjectFragment fragment = new SubjectFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, subjects);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            subjects = (ArrayList<String>) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_subject, container, false);

        final ListView listview = view.findViewById(R.id.lv_subject);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(container.getContext(), R.layout.listview_item, R.id.icon, subjects);

        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                onButtonPressed(MainActivity.STATUS_PRACTICE_SUBJECT_XX, item);
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
    public void onButtonPressed(int status, String subject) {
        if (mListener != null) {
            mListener.onFragmentInteraction(status, subject);
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
