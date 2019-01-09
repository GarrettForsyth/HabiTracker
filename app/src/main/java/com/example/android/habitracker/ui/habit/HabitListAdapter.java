package com.example.android.habitracker.ui.habit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.habitracker.R;
import com.example.android.habitracker.vo.Habit;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class HabitListAdapter extends RecyclerView.Adapter<HabitListAdapter.HabitViewHolder> {

    private final LayoutInflater mInflater;
    private List<Habit> mHabits;

    HabitListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @NonNull
    @Override
    public HabitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.habitlist_item, parent, false);
        return new HabitViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HabitViewHolder holder, int position) {
        if (mHabits != null) {
            Habit current = mHabits.get(position);
            holder.nameView.setText(current.getName());
            holder.descriptionView.setText(current.getDescription());

            String dateFormat = "hh:mm";
            //TODO Adjust to the user's locale
            SimpleDateFormat sdf = new SimpleDateFormat(
                    dateFormat,
                    Locale.CANADA
            );
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(current.getReminderTime());
            holder.reminderTimeView.setText(sdf.format(calendar.getTime()));
        }

    }

    @Override
    public int getItemCount() {
        if (mHabits != null) {
            return mHabits.size();
        } else {
            return 0;
        }
    }

    void setHabits(List<Habit> habits) {
        mHabits = habits;
        notifyDataSetChanged();
    }

    class HabitViewHolder extends RecyclerView.ViewHolder {

        TextView nameView;
        TextView descriptionView;
        TextView reminderTimeView;

        public HabitViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.habitlist_item_name);
            descriptionView = itemView.findViewById(R.id.habitlist_item_description);
            reminderTimeView = itemView.findViewById(R.id.habitlist_item_reminder_time);
        }
    }
}
