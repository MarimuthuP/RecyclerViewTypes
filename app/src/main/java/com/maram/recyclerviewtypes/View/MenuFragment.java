package com.maram.recyclerviewtypes.View;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maram.recyclerviewtypes.R;
import com.maram.recyclerviewtypes.Utils.MyConstant;


/**
 * Created by Marimuthu on 8/21/17.
 */

public class MenuFragment extends Fragment implements View.OnClickListener {

    /**
     * which is layout object to access the id's
     */
    View viewFragment;

    /**
     * First menu option textview
     */
    TextView textView_first;

    /**
     * which is used to access the activity methods
     */
    IMainCommunicator iMainCommunicator;

    public static String TAG = MenuFragment.class.getSimpleName();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        iMainCommunicator = (IMainCommunicator)activity ;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewFragment = view;
        initFragments();
    }

    /**
     * Initialize the fragments fields
     */
    public void initFragments() {
        textView_first = (TextView) viewFragment.findViewById(R.id.tv_first_option);
        setOnClickListener();
    }

    /**
     * which is used to invoke the onclick listener
     */
    public void setOnClickListener() {
        textView_first.setOnClickListener(this);
        Log.d(TAG, "setOnClickListener: ");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_first_option:
                iMainCommunicator.openNextScreen(MyConstant.NavigateScreen.FIRST_FRAGMENT_KEY);
                break;
            default:
                break;
        }
    }
}
