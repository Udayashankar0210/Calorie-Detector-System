package devs.mulham.horizontalcalendar.utils;

import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import java.util.Calendar;

public abstract class HorizontalCalendarListener {
    public abstract void onDateSelected(Calendar calendar, int i);

    public void onCalendarScroll(HorizontalCalendarView calendarView, int dx, int dy) {
    }

    public boolean onDateLongClicked(Calendar date, int position) {
        return false;
    }
}
