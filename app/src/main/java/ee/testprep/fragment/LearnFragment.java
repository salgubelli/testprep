package ee.testprep.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import ee.testprep.DataBaseHelper;
import ee.testprep.L;
import ee.testprep.MainActivity;
import ee.testprep.R;
import ee.testprep.fragment.learn.ExamFragment;
import ee.testprep.fragment.learn.SubjectFragment;
import ee.testprep.fragment.learn.UserStatusFragment;
import ee.testprep.fragment.learn.YearFragment;

public class LearnFragment extends Fragment {

    private static String className = LearnFragment.class.getSimpleName();
    private OnFragmentInteractionListener mListener;

    public LearnFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LearnFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LearnFragment newInstance() {
        LearnFragment fragment = new LearnFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_learn, container, false);

        Button btnYear = view.findViewById(R.id.bt_learn_year);
        btnYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonPressed(MainActivity.STATUS_LEARN_YEAR);
            }
        });

        Button btnSubject = view.findViewById(R.id.bt_learn_subject);
        btnSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonPressed(MainActivity.STATUS_LEARN_SUBJECT);
            }
        });

        Button btnExam = view.findViewById(R.id.bt_learn_exam);
        btnExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonPressed(MainActivity.STATUS_LEARN_EXAM);
            }
        });

        Button btnUserStatus = view.findViewById(R.id.bt_learn_userstatus);
        btnUserStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonPressed(MainActivity.STATUS_LEARN_USERSTATUS);
            }
        });

        return view;
    }

    public void onButtonPressed(int status) {
        if (mListener != null) {
            mListener.onFragmentInteraction(status);
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
