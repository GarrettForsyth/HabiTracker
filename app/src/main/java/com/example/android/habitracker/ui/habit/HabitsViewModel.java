package com.example.android.habitracker.ui.habit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.android.habitracker.data.HabitRepository;
import com.example.android.habitracker.vo.Habit;

import java.util.List;

import javax.inject.Inject;

public class HabitsViewModel extends ViewModel {

    private HabitRepository habitRepository;

    @Inject
    public HabitsViewModel(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public LiveData<List<Habit>> getHabits() {
        return habitRepository.getHabits();
    }

}
