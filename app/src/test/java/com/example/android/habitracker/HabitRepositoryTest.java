package com.example.android.habitracker;

import com.example.android.habitracker.data.HabitRepository;
import com.example.android.habitracker.db.HabitDao;
import com.example.android.habitracker.vo.Habit;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HabitRepositoryTest {

    private HabitRepository habitRepository;
    private HabitDao habitDao;

    @Before
    public void setup() {
        habitDao = mock(HabitDao.class);
        habitRepository = new HabitRepository(habitDao);
    }

    @Test
    public void addHabitTest() {
        Habit habit = TestUtils.createHabit();
        habitRepository.addHabit(habit);
        verify(habitDao).insert(habit);
    }
}
