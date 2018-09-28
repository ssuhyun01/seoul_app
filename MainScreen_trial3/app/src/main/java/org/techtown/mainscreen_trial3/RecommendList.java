package org.techtown.mainscreen_trial3;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RecommendList extends Fragment {
  public RecommendList() {
        // Required empty public constructor
    }

    public static RecommendList newInstance() {
        RecommendList fragment = new RecommendList();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recommend_list, container, false);
    }
}
