package com.example.android.habitracker.ui.habit;

import androidx.lifecycle.ViewModel;

import com.example.android.habitracker.data.HabitRepository;
import com.example.android.habitracker.vo.Habit;

import javax.inject.Inject;

public class HabitViewModel extends ViewModel {

    private HabitRepository habitRepository;

    @Inject
    public HabitViewModel(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public void addHabit(Habit habit) {
        habitRepository.addHabit(habit);
    }
}
