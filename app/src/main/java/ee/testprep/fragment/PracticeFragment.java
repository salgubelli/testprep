package ee.testprep.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ee.testprep.MainActivity;
import ee.testprep.R;

public class PracticeFragment extends Fragment {

    private static String className = PracticeFragment.class.getSimpleName();
    private OnFragmentInteractionListener mListener;

    public PracticeFragment() {
    }

    public static PracticeFragment newInstance() {
        PracticeFragment fragment = new PracticeFragment();
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
        View view =  inflater.inflate(R.layout.fragment_practice, container, false);

        Button btnYear = view.findViewById(R.id.bt_learn_year);
        btnYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonPressed(MainActivity.STATUS_PRACTICE_YEAR);
            }
        });

        Button btnSubject = view.findViewById(R.id.bt_learn_subject);
        btnSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonPressed(MainActivity.STATUS_PRACTICE_SUBJECT);
            }
        });

        Button btnExam = view.findViewById(R.id.bt_learn_exam);
        btnExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonPressed(MainActivity.STATUS_PRACTICE_EXAM);
            }
        });

        Button btnUserStatus = view.findViewById(R.id.bt_learn_userstatus);
        btnUserStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonPressed(MainActivity.STATUS_PRACTICE_USERSTATUS);
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
