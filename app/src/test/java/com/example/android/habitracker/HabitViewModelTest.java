package com.example.android.habitracker;

import com.example.android.habitracker.data.HabitRepository;
import com.example.android.habitracker.ui.habit.HabitViewModel;
import com.example.android.habitracker.vo.Habit;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HabitViewModelTest {

    private HabitViewModel habitViewModel;
    private HabitRepository habitRepository;

    @Before
    public void setup() {
        habitRepository = mock(HabitRepository.class);
        habitViewModel = new HabitViewModel(habitRepository);
    }

    @Test
    public void addHabit() {
        Habit habit = TestUtils.createHabit();
        habitViewModel.addHabit(habit);
        verify(habitRepository).addHabit(habit);
    }
}
