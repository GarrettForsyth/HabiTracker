package com.example.android.habitracker;

import com.example.android.habitracker.data.HabitRepository;
import com.example.android.habitracker.testing.TestUtils;
import com.example.android.habitracker.ui.habit.HabitViewModel;
import com.example.android.habitracker.ui.habit.HabitsViewModel;
import com.example.android.habitracker.vo.Habit;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HabitsViewModelTest {

    private HabitsViewModel habitsViewModel;
    private HabitRepository habitRepository;

    @Before
    public void setup() {
        habitRepository = mock(HabitRepository.class);
        habitsViewModel = new HabitsViewModel(habitRepository);
    }

    @Test
    public void getHabits() {
        Habit habit = TestUtils.createHabit();
        habitsViewModel.getHabits();
        verify(habitRepository).getHabits();
    }
}
