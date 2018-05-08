package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.krohn.lab7completed.R;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MathFragment.OnBiddingFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class MathFragment extends Fragment {

    /**
     * There is no checkMath method in this class. It is in the MainActivity class.
     */

    // TODO: Rename parameter arguments, choose names that match
    // currently not used by may be used in the final labs
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    // currently not used by may be used in the final labs
    private String mParam1;
    private String mParam2;

    private int first, second, operator;

    private OnBiddingFragmentInteractionListener mListener;

    public MathFragment() {
        // Required empty public constructor
    }

    //currently not used but may be used in final labs
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bidding, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    // currently not used by may be used in the final labs
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {

        }
    }

    public String getInfo(){
        if(operator==0){
            //addition
            return first+" + "+second + " = " + getSolution();
        } else if(operator==1){
            //subtraction
            return first+" - "+second + " = " + getSolution();
        }
        return first+" * "+second + " = " + getSolution();
    }

    public int getSolution(){
        if(operator==0){
            //addition
            return first+second;
        } else if(operator==1){
            //subtraction
            return first - second;
        }
        return first * second;
    }

    public void addMath(int first, int second, int operator){
        this.first = first;
        this.second = second;
        this.operator = operator;
        //main activity is telling us what the equation was last time
        TextView textView = (TextView)(getView().findViewById(R.id.text_math_question));
        if(operator==0){
            //addition
            textView.setText(first+ " + "+ second);
        } else if(operator==1){
            //subtraction
            if(first<second){
                int temp = first;
                first = second;
                second = temp;
            }
            textView.setText(first+ " - "+ second);
        } else{
            textView.setText(first+ " * "+ second);
        }
    }

    public void addMath(){
        TextView textView = (TextView)(getView().findViewById(R.id.text_math_question));
        Random random = new Random();
        first = random.nextInt(16);
        second = random.nextInt(16);
        operator = random.nextInt(3);
        if(operator==0){
            //addition
            textView.setText(first+ " + "+ second);
        } else if(operator==1){
            //subtraction
            if(first<second){
                int temp = first;
                first = second;
                second = temp;
            }
            textView.setText(first+ " - "+ second);
        } else{
            textView.setText(first+ " * "+ second);
        }
        //tells the activity what the equation is
        mListener.setEquation(first, second, operator);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        if(!mListener.equationExists()){
            addMath();
        } else {
            //don't get a new equation, use the old one
            addMath(mListener.getFirst(), mListener.getSecond(), mListener.getOperator());
        }
    }

    // currently not used by may be used in the final labs
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnBiddingFragmentInteractionListener) {
            mListener = (OnBiddingFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnBiddingFragmentInteractionListener");
        }
    }

    // currently not used by may be used in the final labs
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnBiddingFragmentInteractionListener {
        // TODO: Update argument type and name
        // currently not used by may be used in the final labs
        public void setEquation(int first, int second, int operator);
        public boolean equationExists();
        public int getFirst();
        public int getSecond();
        public int getOperator();
    }
}
