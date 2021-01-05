package com.codewithgolap.radioapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.codewithgolap.radioapp.adapters.HorizontalAdapter;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView;

public class HomeFragment extends Fragment {

    TextView smallFmTitile, smallFmFreq;
    ImageView playPauseButton, closeButton, blurFmTitle, blurFav;
    View divider;

    SlidingUpPanelLayout slidingUpPanelLayout;


    int[] fmTitielFirst = {R.string.firstFm};
    int[] fmFreqFirst={R.string.firstFmF};

    int[] fmTitielSecond = {R.string.sixFm,R.string.SecondFm,R.string.ThirdFm,R.string.FourFm};
    int[] fmFreqSecond={R.string.sixFmF,R.string.SecondFmF,R.string.ThirdFmF,R.string.FourFmF};

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        smallFmTitile = view.findViewById(R.id.smallFmTitle);
        smallFmFreq = view.findViewById(R.id.smallFmFreq);
        playPauseButton = view.findViewById(R.id.playPause);
        closeButton = view.findViewById(R.id.closeWindow);
        divider = view.findViewById(R.id.divider);
        blurFmTitle = view.findViewById(R.id.blurText);
        blurFav = view.findViewById(R.id.blurfavourite);

        slidingUpPanelLayout = view.findViewById(R.id.sliding_layout);

        HorizontalAdapter firstAdapter = new HorizontalAdapter(fmTitielFirst, fmFreqFirst);
        MultiSnapRecyclerView firstRecyclerView = view.findViewById(R.id.first_recyclerView);
        LinearLayoutManager firstLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        firstRecyclerView.setLayoutManager(firstLayoutManager);
        firstRecyclerView.setAdapter(firstAdapter);

        HorizontalAdapter secondAdapter = new HorizontalAdapter(fmTitielSecond, fmFreqSecond);
        MultiSnapRecyclerView secondRecyclerView = view.findViewById(R.id.second_recyclerView);
        LinearLayoutManager secondLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        secondRecyclerView.setLayoutManager(secondLayoutManager);
        secondRecyclerView.setAdapter(secondAdapter);

        closeButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // we have to collapse the sliding window and make it invisible
                        slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                        closeButton.setVisibility(View.INVISIBLE);
                    }
                });

        //add sliding up panel listener
        slidingUpPanelLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {

            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                //check new state is collapsed or expand
                if (newState.toString().equals("EXPANDED")){
                    //show close button and disable touch
                    slidingUpPanelLayout.setTouchEnabled(false);
                    divider.setVisibility(View.INVISIBLE);

                    MainActivity.chipNavigationBar.setVisibility(View.INVISIBLE);

                    RelativeLayout.LayoutParams relativeLayoutPlayPauseButton = new RelativeLayout.LayoutParams(100,100);
                    relativeLayoutPlayPauseButton.addRule(RelativeLayout.CENTER_HORIZONTAL);
                    relativeLayoutPlayPauseButton.topMargin = 850;
                    playPauseButton.setLayoutParams(relativeLayoutPlayPauseButton);

                    RelativeLayout.LayoutParams relativeLayoutFmFreq = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    relativeLayoutFmFreq.addRule(RelativeLayout.CENTER_HORIZONTAL);
                    relativeLayoutFmFreq.topMargin = 700;
                    smallFmFreq.setTextSize(16);
                    smallFmFreq.setLayoutParams(relativeLayoutFmFreq);


                    RelativeLayout.LayoutParams relativeLayoutFmTitle = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    relativeLayoutFmTitle.addRule(RelativeLayout.CENTER_HORIZONTAL);
                    relativeLayoutFmTitle.topMargin = 300;
                    smallFmTitile.setTextSize(70);

                    smallFmTitile.setLayoutParams(relativeLayoutFmTitle);

                    closeButton.setVisibility(View.VISIBLE);
                    blurFmTitle.setVisibility(View.VISIBLE);
                    blurFav.setVisibility(View.VISIBLE);
                }
                else {
                    slidingUpPanelLayout.setTouchEnabled(true);
                    divider.setVisibility(View.VISIBLE);
                    MainActivity.chipNavigationBar.setVisibility(View.VISIBLE);

                    RelativeLayout.LayoutParams relativeLayoutPlayPauseButton = new RelativeLayout.LayoutParams(70,70);
                    relativeLayoutPlayPauseButton.addRule(RelativeLayout.RIGHT_OF, smallFmTitile.getId());
                    relativeLayoutPlayPauseButton.leftMargin = 520;
                    relativeLayoutPlayPauseButton.topMargin = 30;
                    playPauseButton.setLayoutParams(relativeLayoutPlayPauseButton);


                    RelativeLayout.LayoutParams relativeLayoutFmFreq = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    relativeLayoutFmFreq.leftMargin = 10;
                    relativeLayoutFmFreq.addRule(RelativeLayout.BELOW, smallFmTitile.getId());
                    smallFmFreq.setLayoutParams(relativeLayoutFmFreq);
                    smallFmFreq.setTextSize(14);

                    RelativeLayout.LayoutParams relativeLayoutFmTitle = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    relativeLayoutFmTitle.topMargin = 10;
                    relativeLayoutFmTitle.leftMargin = 10;
                    smallFmTitile.setLayoutParams(relativeLayoutFmTitle);
                    smallFmTitile.setTextSize(20);

                    closeButton.setVisibility(View.INVISIBLE);
                }
            }
        });


        return view;
    }
}