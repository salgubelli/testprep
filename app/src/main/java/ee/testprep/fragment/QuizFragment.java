package ee.testprep.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import ee.testprep.DBRow;
import ee.testprep.DataBaseHelper;
import ee.testprep.L;
import ee.testprep.MainActivity;
import ee.testprep.R;
import ee.testprep.fragment.learn.YearFragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuizFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QuizFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizFragment extends Fragment {

    private static String className = QuizFragment.class.getSimpleName();
    private DataBaseHelper dbHelper;
    private String TAG_QUIZ = "quiz";
    private String TAG_QUIZ_QUESTION = "quizQ";
    private Fragment quizQFragment;

    private static final String ARG_PARAM1 = "param1";

    private OnFragmentInteractionListener mListener;

    public QuizFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param dataBaseHelper Parameter 1.
     * @return A new instance of fragment QuizFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuizFragment newInstance(DataBaseHelper dataBaseHelper) {
        QuizFragment fragment = new QuizFragment();
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
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        Button btnStart = view.findViewById(R.id.btn_start_quiz);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dbHelper != null) {
                    ArrayList<DBRow> qList = (ArrayList<DBRow>)dbHelper.queryQuestionsQuiz();
                    quizQFragment = QuizQuestion.newInstance(dbHelper, qList.get(0));

                    Runnable mPendingRunnable = new Runnable() {
                        @Override
                        public void run() {
                            // update the main content by replacing fragments
                            Fragment fragment = quizQFragment;
                            FragmentTransaction fragmentTransaction =
                                    getFragmentManager().beginTransaction();
                            fragmentTransaction.setCustomAnimations(android.R.animator.fade_in,
                                    android.R.animator.fade_out);
                            fragmentTransaction.replace(R.id.frame, fragment, TAG_QUIZ_QUESTION)
                                    .addToBackStack(MainActivity.CURRENT_TAG);
                            fragmentTransaction.commitAllowingStateLoss();
                        }
                    };

                    // If mPendingRunnable is not null, then add to the message queue
                    MainActivity.mUIHandler.post(mPendingRunnable);
                }
                else
                    L.e(className, "DataBaseHelper returned null");
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
