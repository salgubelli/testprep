package ee.testprep.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Random;

import ee.testprep.R;

public class HomeFragment extends Fragment {
    private static String className = HomeFragment.class.getSimpleName();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private OnFragmentInteractionListener mListener;

    private static String author_kalam = "- APJ Kalam";
    private static String author_gandhi = "- MK Gandhi";

    private String[][] quotes = {
            {"All Birds find shelter during rain, but Eagle avoids rain by flying above clouds.", author_kalam},
            {"Don’t take rest after your first victory because if you fail in second, " +
                    "more lips are waiting to say that your first victory was just luck.", author_kalam},
            {"All of us do not have equal talent. But, all of us have an equal opportunity " +
                    "to develop our talents.", author_kalam},
            {"You have to dream before your dreams can come true.", "- APJ Kalam"},
            {"Failure will never overtake me if my definition to succeed is strong enough.", author_kalam},
            {"I'm not a handsome guy, but I can give my hand to someone who needs help. " +
                    "Beauty is in the heart, not in the face.", author_kalam},
            {"The best brains of the nations may be found on the last benches of the classrooms.", author_kalam},
            {"You cannot change your future, but, you can change your habits, and surely your " +
                    "habits will change your future.", author_kalam},
            {"If you fail, never give up because F.A.I.L. means \"First Attempt In Learning\". " +
                    "End is not the end, if fact E.N.D. means \"Effort Never Dies.\" " +
                    "If you get No as an answer, remember N.O. means \"Next Opportunity\", " +
                    "So let’s be positive.", author_kalam},
            {"Look at the sky. We are not alone. The whole universe is friendly to us and " +
                    "conspires only to those who dream and work.", author_kalam},
            {"I will work and sweat for a great vision, the vision of transforming India into a " +
                    "developed nation.", author_kalam},
            {"Don’t read success stories, you will only get message. Read failure stories, " +
                    "you will get some ideas to get success.", author_kalam},
            {"Your best teacher is your last mistake.", author_kalam},
            {"One best book is equal to hundred good friends, but one good fried is equal to a " +
                    "library.", author_kalam},
            {"No matter what is the environment around you, it is always possible to maintain brand " +
                    "of integrity.", author_kalam},
            {"Man needs difficulties in life because they are necessary to enjoy the success.",
                    author_kalam},
            {"Unless India stands up to the world, no one will respect us. In this world, fear " +
                    "has no place. Only strength respects strength.", author_kalam},
            {"Climbing to the top demands strength, whether it is to the top of Mount Everest or " +
                    "to the top of your career.", author_kalam},
            {"Thinking is the capital, Enterprise is the way, Hard Work is the solution", author_kalam},
            {"Be active! Take on responsibility! Work for the things you believe in. If you do not, " +
                    "you are surrendering your fate to others.", author_kalam},
            {"Strength does not come from physical capacity. It comes from an indomitable will.", author_gandhi},
            {"Action is no less necessary than thought to the instinctive tendencies of the human " +
                    "frame.", author_gandhi},
            {"There is more to life than increasing its speed.", author_gandhi},
    };

    public HomeFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PracticeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        TextView greeting = view.findViewById(R.id.tv_home_wish);
        greeting.setText(getGreeting());

        TextView quote = view.findViewById(R.id.tv_home_quote);
        Random rand = new Random();
        int index = rand.nextInt(quotes.length);
        quote.setText(quotes[index][0]);

        TextView author = view.findViewById(R.id.tv_home_author);
        author.setText(quotes[index][1]);


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
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

    private String getGreeting() {
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if(timeOfDay >= 0 && timeOfDay < 12){
            return "Hello, Good Morning!";
        }else if(timeOfDay >= 12 && timeOfDay < 16){
            return "Hello, Good Afternoon!";
        }else if(timeOfDay >= 16 && timeOfDay < 24){
            return "Hello, Good Evening!";
        } else {
            return "Hello, Good Day!";
        }
    }
}
