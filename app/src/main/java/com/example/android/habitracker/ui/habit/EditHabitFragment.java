package com.example.android.habitracker.ui.habit;

import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.util.StringUtil;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.example.android.habitracker.R;
import com.example.android.habitracker.di.Injectable;
import com.example.android.habitracker.vo.Habit;
import com.example.android.habitracker.vo.HabitFrequency;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

public class EditHabitFragment extends Fragment implements Injectable {

    @Inject
    public ViewModelProvider.Factory mViewModelFactory;

    public HabitViewModel mViewModel;

    @Inject
    public EditHabitFragment() { }

    private Calendar mCalendar;
    private TimePickerDialog.OnTimeSetListener mTimeListener;

    private EditText mHabitNameEditText;
    private EditText mHabitDescriptionEditText;
    private Spinner mHabitFrequencySpinner;
    private EditText mReminderTimeEditText;

    private Button mSaveButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_habit, container, false);

        mHabitNameEditText = view.findViewById(R.id.habit_name);
        mHabitDescriptionEditText = view.findViewById(R.id.habit_description);

        // set up spinner
        mHabitFrequencySpinner = (Spinner) view.findViewById(R.id.habit_frequency_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.habit_frequencies,
                android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        mHabitFrequencySpinner.setAdapter(adapter);

        // set up time picker
        mReminderTimeEditText = view.findViewById(R.id.habit_reminder_time);
        mReminderTimeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePicker(view);
            }
        });

        // set up save button
        mSaveButton = view.findViewById(R.id.save_habit_button);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("testtag", "saveHabit: " + mReminderTimeEditText.getText().toString());
                saveHabit();
            }
        });

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(this, mViewModelFactory)
                .get(HabitViewModel.class);

        mCalendar = Calendar.getInstance();
        mTimeListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                mCalendar.set(Calendar.HOUR_OF_DAY, hour);
                mCalendar.set(Calendar.MINUTE, minute);
                updateReminderTime();
            }
        };

    }

    private void updateReminderTime() {
        String dateFormat = "hh:mm";
        //TODO Adjust to the user's locale
        SimpleDateFormat sdf = new SimpleDateFormat(
                dateFormat,
                Locale.CANADA
        );
        mReminderTimeEditText.setText(sdf.format(mCalendar.getTime()));
    }

    public void showTimePicker(View view) {
        new TimePickerDialog(
                getContext(),
                mTimeListener,
                mCalendar.get(Calendar.HOUR_OF_DAY),
                mCalendar.get(Calendar.MINUTE),
                false
        ).show();
    }


    private void saveHabit() {
        if (isBlank(mHabitNameEditText.getText().toString())) {
            Snackbar.make(
                    getActivity().findViewById(R.id.activity_constraint_layout),
                    getString(R.string.invalid_habit_name),
                    Snackbar.LENGTH_SHORT
            ).show();
        } else if (isBlank(mHabitDescriptionEditText.getText().toString())) {
            Snackbar.make(
                    getActivity().findViewById(R.id.activity_constraint_layout),
                    getString(R.string.invalid_habit_description),
                    Snackbar.LENGTH_SHORT
            ).show();
        } else if (isBlank(mReminderTimeEditText.getText().toString())) {
            Snackbar.make(
                    getActivity().findViewById(R.id.activity_constraint_layout),
                    getString(R.string.invalid_habit_reminder_time),
                    Snackbar.LENGTH_SHORT
            ).show();
        } else { // Habit is valid
            HabitFrequency habitFrequency = null;
            int id = (int) mHabitFrequencySpinner.getSelectedItemId();
            switch (id) {
                case 0:
                    habitFrequency = HabitFrequency.DAILY;
                    break;
                case 1:
                    habitFrequency = HabitFrequency.WEEKLY;
                    break;
                case 2:
                    habitFrequency = HabitFrequency.MONTHLY;
                    break;
                default:
                    habitFrequency = null;
            }
            mViewModel.addHabit(new Habit(
                    mHabitNameEditText.getText().toString(),
                    mHabitDescriptionEditText.getText().toString(),
                    habitFrequency,
                    mCalendar.getTimeInMillis()
            ));
            getActivity().getSupportFragmentManager().popBackStack();
        }
    }

    private boolean isBlank(String text) {
        if (text == null) return true;
        return text.trim().length() == 0;
    }


}
