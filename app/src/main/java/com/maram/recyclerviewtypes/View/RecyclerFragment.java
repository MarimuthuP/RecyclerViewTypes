package com.maram.recyclerviewtypes.View;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maram.recyclerviewtypes.Adapter.MyCustomAdapter;
import com.maram.recyclerviewtypes.R;
import com.maram.recyclerviewtypes.customView.CustomDividerItemDecoration;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Marimuthu on 10/8/17.
 */

public class RecyclerFragment extends Fragment implements IRecyclerCommunicator{

    View mainView;
    RecyclerView recyclerViewList;
    RecyclerView.LayoutManager layoutManager;
    MyCustomAdapter myCustomAdapter;
    ArrayList<String> arrayListContent = new ArrayList<>(
            Arrays.asList(  "Convenience Store",
                    "Supermarkets or Grocery Stores",
                    "Shopping Centres",
                    "Department Stores",
                    "Clothing & Accessories",
                    "Fabric Stores",
                    "Fashion Shop",
                    "Cosmetics & Beauty Retailers",
                    "Eyewear Retailers",
                    "Watch Retailers",
                    "Jewellery Retailers",
                    "Bags/Luggage Retailers",
                    "Shoe Stores",
                    "Leather Goods Retailers"));

    ArrayList<String> arrayListSubContent = new ArrayList<>(
            Arrays.asList(  "Convenience",
                    "Grocery Stores",
                    " Centres",
                    " Stores",
                    "Accessories",
                    " Stores",
                    " Shop",
                    "Retailers",
                    " Retailers",
                    " Retailers",
                    " Retailers",
                    "Retailers",
                    " Stores",
                    "Retailers"));
    IMainCommunicator iMainCommunicator;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        iMainCommunicator = (IMainCommunicator)activity;
        ((MainActivity)activity).iRecyclerCommunicator = this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainView = view;
        iMainCommunicator.setupToolBarTitle(getResources().getString(R.string.first_title));
        initFragment();
    }

    private void initFragment() {
        recyclerViewList = (RecyclerView)mainView.findViewById(R.id.recycler_list);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewList.setLayoutManager(layoutManager);
        recyclerViewList.setHasFixedSize(true);
        recyclerViewList.addItemDecoration(new CustomDividerItemDecoration(ContextCompat.getDrawable(getActivity(), R.drawable.divider_horizontal)));
        setAdapter();
    }

    private void setAdapter() {
        myCustomAdapter = new MyCustomAdapter(getActivity(),arrayListContent,arrayListSubContent,iMainCommunicator);
        recyclerViewList.setAdapter(myCustomAdapter);
    }

    @Override
    public void showListView() {
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewList.setLayoutManager(layoutManager);
        recyclerViewList.setHasFixedSize(true);
        recyclerViewList.addItemDecoration(new CustomDividerItemDecoration(ContextCompat.getDrawable(getActivity(), R.drawable.divider_horizontal)));
        setAdapter();
    }

    @Override
    public void showGridView() {
        layoutManager = new GridLayoutManager(getActivity(),2);
        recyclerViewList.setLayoutManager(layoutManager);
        recyclerViewList.setHasFixedSize(true);
        recyclerViewList.addItemDecoration(new CustomDividerItemDecoration(ContextCompat.getDrawable(getActivity(), R.drawable.divider_horizontal)));
        setAdapter();
    }
}
