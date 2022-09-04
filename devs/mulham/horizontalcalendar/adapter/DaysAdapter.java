package devs.mulham.horizontalcalendar.adapter;

import android.text.format.DateFormat;
import android.view.View;
import devs.mulham.horizontalcalendar.C0544R;
import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.model.HorizontalCalendarConfig;
import devs.mulham.horizontalcalendar.utils.CalendarEventsPredicate;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarPredicate;
import devs.mulham.horizontalcalendar.utils.Utils;
import java.util.Calendar;
import java.util.List;

public class DaysAdapter extends HorizontalCalendarBaseAdapter<DateViewHolder, Calendar> {
    public DaysAdapter(HorizontalCalendar horizontalCalendar, Calendar startDate, Calendar endDate, HorizontalCalendarPredicate disablePredicate, CalendarEventsPredicate eventsPredicate) {
        super(C0544R.layout.hc_item_calendar, horizontalCalendar, startDate, endDate, disablePredicate, eventsPredicate);
    }

    /* access modifiers changed from: protected */
    public DateViewHolder createViewHolder(View itemView, int cellWidth) {
        DateViewHolder holder = new DateViewHolder(itemView);
        holder.layoutContent.setMinimumWidth(cellWidth);
        return holder;
    }

    public void onBindViewHolder(DateViewHolder holder, int position) {
        Calendar day = getItem(position);
        HorizontalCalendarConfig config = this.horizontalCalendar.getConfig();
        Integer selectorColor = this.horizontalCalendar.getConfig().getSelectorColor();
        if (selectorColor != null) {
            holder.selectionView.setBackgroundColor(selectorColor.intValue());
        }
        holder.textMiddle.setText(DateFormat.format(config.getFormatMiddleText(), day));
        holder.textMiddle.setTextSize(2, config.getSizeMiddleText());
        if (config.isShowTopText()) {
            holder.textTop.setText(DateFormat.format(config.getFormatTopText(), day));
            holder.textTop.setTextSize(2, config.getSizeTopText());
        } else {
            holder.textTop.setVisibility(8);
        }
        if (config.isShowBottomText()) {
            holder.textBottom.setText(DateFormat.format(config.getFormatBottomText(), day));
            holder.textBottom.setTextSize(2, config.getSizeBottomText());
        } else {
            holder.textBottom.setVisibility(8);
        }
        showEvents(holder, day);
        applyStyle(holder, day, position);
    }

    public void onBindViewHolder(DateViewHolder holder, int position, List<Object> payloads) {
        if (payloads == null || payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            applyStyle(holder, getItem(position), position);
        }
    }

    public Calendar getItem(int position) throws IndexOutOfBoundsException {
        if (position >= this.itemsCount) {
            throw new IndexOutOfBoundsException();
        }
        Calendar calendar = (Calendar) this.startDate.clone();
        calendar.add(5, position - this.horizontalCalendar.getShiftCells());
        return calendar;
    }

    /* access modifiers changed from: protected */
    public int calculateItemsCount(Calendar startDate, Calendar endDate) {
        return (this.horizontalCalendar.getShiftCells() * 2) + Utils.daysBetween(startDate, endDate) + 1;
    }
}
