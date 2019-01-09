package com.example.android.habitracker.ui.habit;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.habitracker.R;
import com.example.android.habitracker.di.Injectable;
import com.example.android.habitracker.vo.Habit;

import java.util.List;

import javax.inject.Inject;

public class HabitsFragment extends Fragment implements Injectable {

    @Inject
    HabitsFragment() {}

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private HabitsViewModel mViewModel;

    public static HabitsFragment newInstance() {
        return new HabitsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.habits_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView recyclerView = getView().findViewById(R.id.habits_recycler_view);
        final HabitListAdapter adapter = new HabitListAdapter(getContext());

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(HabitsViewModel.class);

        mViewModel.getHabits().observe(this, new Observer<List<Habit>>() {
            @Override
            public void onChanged(List<Habit> habits) {
                adapter.setHabits(habits);
            }
        });
    }

}
