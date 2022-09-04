package devs.mulham.horizontalcalendar.utils;

import android.support.annotation.Nullable;
import android.support.p004v7.widget.LinearSnapHelper;
import android.support.p004v7.widget.RecyclerView;
import android.view.View;
import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;

public class HorizontalSnapHelper extends LinearSnapHelper {
    private HorizontalCalendarView calendarView;
    private HorizontalCalendar horizontalCalendar;

    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        int selectedItemPosition;
        View snapView = super.findSnapView(layoutManager);
        if (this.calendarView.getScrollState() != 1) {
            if (snapView == null) {
                selectedItemPosition = this.horizontalCalendar.getSelectedDatePosition();
            } else {
                int[] snapDistance = calculateDistanceToFinalSnap(layoutManager, snapView);
                if (snapDistance[0] != 0 || snapDistance[1] != 0) {
                    return snapView;
                }
                selectedItemPosition = layoutManager.getPosition(snapView);
            }
            notifyCalendarListener(selectedItemPosition);
        }
        return snapView;
    }

    private void notifyCalendarListener(int selectedItemPosition) {
        if (!this.horizontalCalendar.isItemDisabled(selectedItemPosition)) {
            this.horizontalCalendar.getCalendarListener().onDateSelected(this.horizontalCalendar.getDateAt(selectedItemPosition), selectedItemPosition);
        }
    }

    public void attachToRecyclerView(@Nullable RecyclerView recyclerView) throws IllegalStateException {
    }

    public void attachToHorizontalCalendar(@Nullable HorizontalCalendar horizontalCalendar2) throws IllegalStateException {
        this.horizontalCalendar = horizontalCalendar2;
        this.calendarView = horizontalCalendar2.getCalendarView();
        super.attachToRecyclerView(this.calendarView);
    }
}
