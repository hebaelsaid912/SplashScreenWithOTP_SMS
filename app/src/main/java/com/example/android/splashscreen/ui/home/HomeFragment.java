package com.example.android.splashscreen.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.splashscreen.R;
import com.example.android.splashscreen.RecycleViewBackEnd.Home_RV.Name;
import com.example.android.splashscreen.RecycleViewBackEnd.Home_RV.NameAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
RecyclerView recyclerView;
RelativeLayout emptyView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        System.out.println("Home");
        emptyView = (RelativeLayout) root.findViewById(R.id.empty_view_book) ;
        recyclerView = (RecyclerView) root.findViewById(R.id.Names_rv);
        ArrayList<Name> names = new ArrayList<>();
        names.add(new Name("Heba"));
        names.add(new Name("Ali"));
        names.add(new Name("Ahmed"));
        names.add(new Name("Mohammed"));
        names.add(new Name("Naira"));
        names.add(new Name("Esraa"));
        NameAdapter adapter = new NameAdapter(names);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if (names.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }
        else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
        return root;
    }
}