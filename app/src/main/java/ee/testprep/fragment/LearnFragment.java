package ee.testprep.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.Serializable;

import ee.testprep.DataBaseHelper;
import ee.testprep.L;
import ee.testprep.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LearnFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LearnFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LearnFragment extends Fragment {
    private static final String ARG_PARAM1 = "dbhelper";
    private OnFragmentInteractionListener mListener;
    private DataBaseHelper dbHelper;
    private static String className = LearnFragment.class.getSimpleName();

    public LearnFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param dataBaseHelper pointer to database.
     * @return A new instance of fragment LearnFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LearnFragment newInstance(DataBaseHelper dataBaseHelper) {
        LearnFragment fragment = new LearnFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_PARAM1, dataBaseHelper);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            dbHelper = (DataBaseHelper) getArguments().getSerializable(ARG_PARAM1);
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
                if(dbHelper != null)
                    dbHelper.queryByYear("year=2015");
                else
                    L.e(className, "DataBaseHelper returned null");
            }
        });

        Button btnSubject = view.findViewById(R.id.bt_learn_subject);
        btnSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dbHelper != null)
                    dbHelper.queryByYear("subject=\"POLI\"");
                else
                    L.e(className, "DataBaseHelper returned null");
            }
        });

        Button btnExam = view.findViewById(R.id.bt_learn_exam);
        btnExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dbHelper != null)
                    dbHelper.queryByYear("examName=\"CSP\"");
                else
                    L.e(className, "DataBaseHelper returned null");
            }
        });

        Button btnUserStatus = view.findViewById(R.id.bt_learn_userstatus);
        btnUserStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //query
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
