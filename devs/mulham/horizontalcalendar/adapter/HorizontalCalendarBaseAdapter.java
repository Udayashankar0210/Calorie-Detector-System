package devs.mulham.horizontalcalendar.adapter;

import android.os.Build;
import android.support.p004v7.widget.GridLayoutManager;
import android.support.p004v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.adapter.DateViewHolder;
import devs.mulham.horizontalcalendar.model.CalendarEvent;
import devs.mulham.horizontalcalendar.model.CalendarItemStyle;
import devs.mulham.horizontalcalendar.utils.CalendarEventsPredicate;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarPredicate;
import devs.mulham.horizontalcalendar.utils.Utils;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public abstract class HorizontalCalendarBaseAdapter<VH extends DateViewHolder, T extends Calendar> extends RecyclerView.Adapter<VH> {
    private final int cellWidth;
    private final HorizontalCalendarPredicate disablePredicate;
    private CalendarItemStyle disabledItemStyle;
    private final CalendarEventsPredicate eventsPredicate;
    final HorizontalCalendar horizontalCalendar;
    private final int itemResId;
    protected int itemsCount;
    protected Calendar startDate;

    /* access modifiers changed from: protected */
    public abstract int calculateItemsCount(Calendar calendar, Calendar calendar2);

    /* access modifiers changed from: protected */
    public abstract VH createViewHolder(View view, int i);

    public abstract T getItem(int i);

    protected HorizontalCalendarBaseAdapter(int itemResId2, HorizontalCalendar horizontalCalendar2, Calendar startDate2, Calendar endDate, HorizontalCalendarPredicate disablePredicate2, CalendarEventsPredicate eventsPredicate2) {
        this.itemResId = itemResId2;
        this.horizontalCalendar = horizontalCalendar2;
        this.disablePredicate = disablePredicate2;
        this.startDate = startDate2;
        if (disablePredicate2 != null) {
            this.disabledItemStyle = disablePredicate2.style();
        }
        this.eventsPredicate = eventsPredicate2;
        this.cellWidth = Utils.calculateCellWidth(horizontalCalendar2.getContext(), horizontalCalendar2.getNumberOfDatesOnScreen());
        this.itemsCount = calculateItemsCount(startDate2, endDate);
    }

    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        VH viewHolder = createViewHolder(LayoutInflater.from(parent.getContext()).inflate(this.itemResId, parent, false), this.cellWidth);
        viewHolder.itemView.setOnClickListener(new MyOnClickListener(viewHolder));
        viewHolder.itemView.setOnLongClickListener(new MyOnLongClickListener(viewHolder));
        if (this.eventsPredicate != null) {
            initEventsRecyclerView(viewHolder.eventsRecyclerView);
        } else {
            viewHolder.eventsRecyclerView.setVisibility(8);
        }
        return viewHolder;
    }

    private void initEventsRecyclerView(RecyclerView recyclerView) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new EventsAdapter(Collections.emptyList()));
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 4));
    }

    public int getItemCount() {
        return this.itemsCount;
    }

    public boolean isDisabled(int position) {
        if (this.disablePredicate == null) {
            return false;
        }
        return this.disablePredicate.test(getItem(position));
    }

    /* access modifiers changed from: protected */
    public void showEvents(VH viewHolder, Calendar date) {
        if (this.eventsPredicate != null) {
            List<CalendarEvent> events = this.eventsPredicate.events(date);
            if (events == null || events.isEmpty()) {
                viewHolder.eventsRecyclerView.setVisibility(8);
                return;
            }
            viewHolder.eventsRecyclerView.setVisibility(0);
            ((EventsAdapter) viewHolder.eventsRecyclerView.getAdapter()).update(events);
        }
    }

    /* access modifiers changed from: protected */
    public void applyStyle(VH viewHolder, Calendar date, int position) {
        int selectedItemPosition = this.horizontalCalendar.getSelectedDatePosition();
        if (this.disablePredicate != null) {
            boolean isDisabled = this.disablePredicate.test(date);
            viewHolder.itemView.setEnabled(!isDisabled);
            if (isDisabled && this.disabledItemStyle != null) {
                applyStyle(viewHolder, this.disabledItemStyle);
                viewHolder.selectionView.setVisibility(4);
                return;
            }
        }
        if (position == selectedItemPosition) {
            applyStyle(viewHolder, this.horizontalCalendar.getSelectedItemStyle());
            viewHolder.selectionView.setVisibility(0);
            return;
        }
        applyStyle(viewHolder, this.horizontalCalendar.getDefaultStyle());
        viewHolder.selectionView.setVisibility(4);
    }

    /* access modifiers changed from: protected */
    public void applyStyle(VH viewHolder, CalendarItemStyle itemStyle) {
        viewHolder.textTop.setTextColor(itemStyle.getColorTopText());
        viewHolder.textMiddle.setTextColor(itemStyle.getColorMiddleText());
        viewHolder.textBottom.setTextColor(itemStyle.getColorBottomText());
        if (Build.VERSION.SDK_INT >= 16) {
            viewHolder.itemView.setBackground(itemStyle.getBackground());
        } else {
            viewHolder.itemView.setBackgroundDrawable(itemStyle.getBackground());
        }
    }

    public void update(Calendar startDate2, Calendar endDate, boolean notify) {
        this.startDate = startDate2;
        this.itemsCount = calculateItemsCount(startDate2, endDate);
        if (notify) {
            notifyDataSetChanged();
        }
    }

    private class MyOnClickListener implements View.OnClickListener {
        private final RecyclerView.ViewHolder viewHolder;

        MyOnClickListener(RecyclerView.ViewHolder viewHolder2) {
            this.viewHolder = viewHolder2;
        }

        public void onClick(View v) {
            int position = this.viewHolder.getAdapterPosition();
            if (position != -1) {
                HorizontalCalendarBaseAdapter.this.horizontalCalendar.getCalendarView().setSmoothScrollSpeed(125.0f);
                HorizontalCalendarBaseAdapter.this.horizontalCalendar.centerCalendarToPosition(position);
            }
        }
    }

    private class MyOnLongClickListener implements View.OnLongClickListener {
        private final RecyclerView.ViewHolder viewHolder;

        MyOnLongClickListener(RecyclerView.ViewHolder viewHolder2) {
            this.viewHolder = viewHolder2;
        }

        public boolean onLongClick(View v) {
            HorizontalCalendarListener calendarListener = HorizontalCalendarBaseAdapter.this.horizontalCalendar.getCalendarListener();
            if (calendarListener == null) {
                return false;
            }
            int position = this.viewHolder.getAdapterPosition();
            return calendarListener.onDateLongClicked(HorizontalCalendarBaseAdapter.this.getItem(position), position);
        }
    }
}
