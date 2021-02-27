package com.example.eostoy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eostoy.adapter.IntentAdapter;
import com.example.eostoy.data.Manager;

public class IntentFragment extends Fragment {
    private RecyclerView intentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intent, container, false);

        intentView = (RecyclerView)view.findViewById(R.id.intent_view);

        // RecyclerView 에 LinearLayoutManager를 셋팅
        intentView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // 만들어 둔 IntentAdapter를 RecyclerView에 셋팅
        intentView.setAdapter(new IntentAdapter(getActivity(), Manager.getIntentList()));

        return view;
    }
}
