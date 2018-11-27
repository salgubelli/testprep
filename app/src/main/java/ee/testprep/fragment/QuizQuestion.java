package ee.testprep.fragment;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import ee.testprep.DBRow;
import ee.testprep.DataBaseHelper;
import ee.testprep.L;
import ee.testprep.R;

public class QuizQuestion extends Fragment{

    private static String className = QuizQuestion.class.getSimpleName();
    private String TAG_QUIZ = "quizQ";

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private DataBaseHelper mDBHelper;
    private DBRow mQuestion;

    private QuizQuestion.OnFragmentInteractionListener mListener;

    public QuizQuestion() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param question Parameter 1.
     * @return A new instance of fragment QuizFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuizQuestion newInstance(DataBaseHelper dbHelper, DBRow question) {
        QuizQuestion fragment = new QuizQuestion();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_PARAM1, dbHelper);
        bundle.putSerializable(ARG_PARAM2, question);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDBHelper = (DataBaseHelper) getArguments().getSerializable(ARG_PARAM1);
            mQuestion = (DBRow) getArguments().getSerializable(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.quiz_question, container, false);

        TextView tvQuestion = view.findViewById(R.id.question);
        tvQuestion.setText(mQuestion.question);
        tvQuestion.setMovementMethod(new ScrollingMovementMethod());

        RadioButton tvOptA = view.findViewById(R.id.rb_optA);
        tvOptA.setText(mQuestion.optionA);
        RadioButton tvOptB = view.findViewById(R.id.rb_optB);
        tvOptB.setText(mQuestion.optionB);
        RadioButton tvOptC = view.findViewById(R.id.rb_optC);
        tvOptC.setText(mQuestion.optionC);
        RadioButton tvOptD = view.findViewById(R.id.rb_optD);
        tvOptD.setText(mQuestion.optionD);

        // populate the question
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
