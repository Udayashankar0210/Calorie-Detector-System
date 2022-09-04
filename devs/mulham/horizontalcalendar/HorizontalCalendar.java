package devs.mulham.horizontalcalendar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.p004v7.widget.RecyclerView;
import android.view.View;
import devs.mulham.horizontalcalendar.adapter.DaysAdapter;
import devs.mulham.horizontalcalendar.adapter.HorizontalCalendarBaseAdapter;
import devs.mulham.horizontalcalendar.adapter.MonthsAdapter;
import devs.mulham.horizontalcalendar.model.CalendarItemStyle;
import devs.mulham.horizontalcalendar.model.HorizontalCalendarConfig;
import devs.mulham.horizontalcalendar.utils.CalendarEventsPredicate;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarPredicate;
import devs.mulham.horizontalcalendar.utils.HorizontalSnapHelper;
import devs.mulham.horizontalcalendar.utils.Utils;
import java.util.Calendar;

public final class HorizontalCalendar {
    private final int calendarId;
    HorizontalCalendarListener calendarListener;
    HorizontalCalendarView calendarView;
    private final HorizontalCalendarConfig config;
    private final HorizontalCalendarPredicate defaultDisablePredicate = new HorizontalCalendarPredicate() {
        public boolean test(Calendar date) {
            return Utils.isDateBefore(date, HorizontalCalendar.this.startDate) || Utils.isDateAfter(date, HorizontalCalendar.this.endDate);
        }

        public CalendarItemStyle style() {
            return new CalendarItemStyle(-7829368, (Drawable) null);
        }
    };
    private final CalendarItemStyle defaultStyle;
    Calendar endDate;
    private HorizontalCalendarBaseAdapter mCalendarAdapter;
    private Mode mode;
    private final int numberOfDatesOnScreen;
    private final CalendarItemStyle selectedItemStyle;
    Calendar startDate;

    public enum Mode {
        DAYS,
        MONTHS
    }

    HorizontalCalendar(Builder builder, HorizontalCalendarConfig config2, CalendarItemStyle defaultStyle2, CalendarItemStyle selectedItemStyle2) {
        this.numberOfDatesOnScreen = builder.numberOfDatesOnScreen;
        this.calendarId = builder.viewId;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.config = config2;
        this.defaultStyle = defaultStyle2;
        this.selectedItemStyle = selectedItemStyle2;
        this.mode = builder.mode;
    }

    /* access modifiers changed from: package-private */
    public void init(View rootView, final Calendar defaultSelectedDate, HorizontalCalendarPredicate disablePredicate, CalendarEventsPredicate eventsPredicate) {
        HorizontalCalendarPredicate disablePredicate2;
        this.calendarView = (HorizontalCalendarView) rootView.findViewById(this.calendarId);
        this.calendarView.setHasFixedSize(true);
        this.calendarView.setHorizontalScrollBarEnabled(false);
        this.calendarView.applyConfigFromLayout(this);
        new HorizontalSnapHelper().attachToHorizontalCalendar(this);
        if (disablePredicate == null) {
            disablePredicate2 = this.defaultDisablePredicate;
        } else {
            disablePredicate2 = new HorizontalCalendarPredicate.C0547Or(disablePredicate, this.defaultDisablePredicate);
        }
        if (this.mode == Mode.MONTHS) {
            this.mCalendarAdapter = new MonthsAdapter(this, this.startDate, this.endDate, disablePredicate2, eventsPredicate);
        } else {
            this.mCalendarAdapter = new DaysAdapter(this, this.startDate, this.endDate, disablePredicate2, eventsPredicate);
        }
        this.calendarView.setAdapter(this.mCalendarAdapter);
        this.calendarView.setLayoutManager(new HorizontalLayoutManager(this.calendarView.getContext(), false));
        this.calendarView.addOnScrollListener(new HorizontalCalendarScrollListener());
        post(new Runnable() {
            public void run() {
                HorizontalCalendar.this.centerToPositionWithNoAnimation(HorizontalCalendar.this.positionOfDate(defaultSelectedDate));
            }
        });
    }

    public HorizontalCalendarListener getCalendarListener() {
        return this.calendarListener;
    }

    public void setCalendarListener(HorizontalCalendarListener calendarListener2) {
        this.calendarListener = calendarListener2;
    }

    public void goToday(boolean immediate) {
        selectDate(Calendar.getInstance(), immediate);
    }

    public void selectDate(Calendar date, boolean immediate) {
        int datePosition = positionOfDate(date);
        if (immediate) {
            centerToPositionWithNoAnimation(datePosition);
            if (this.calendarListener != null) {
                this.calendarListener.onDateSelected(date, datePosition);
                return;
            }
            return;
        }
        this.calendarView.setSmoothScrollSpeed(90.0f);
        centerCalendarToPosition(datePosition);
    }

    public void centerCalendarToPosition(int position) {
        int relativeCenterPosition;
        if (position != -1 && (relativeCenterPosition = Utils.calculateRelativeCenterPosition(position, this.calendarView.getPositionOfCenterItem(), this.numberOfDatesOnScreen / 2)) != position) {
            this.calendarView.smoothScrollToPosition(relativeCenterPosition);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0003, code lost:
        r0 = r4.calendarView.getPositionOfCenterItem();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void centerToPositionWithNoAnimation(int r5) {
        /*
            r4 = this;
            r0 = -1
            if (r5 == r0) goto L_0x0023
            devs.mulham.horizontalcalendar.HorizontalCalendarView r0 = r4.calendarView
            int r0 = r0.getPositionOfCenterItem()
            int r1 = r4.numberOfDatesOnScreen
            int r1 = r1 / 2
            int r1 = devs.mulham.horizontalcalendar.utils.Utils.calculateRelativeCenterPosition(r5, r0, r1)
            if (r1 != r5) goto L_0x0014
            return
        L_0x0014:
            devs.mulham.horizontalcalendar.HorizontalCalendarView r2 = r4.calendarView
            r2.scrollToPosition(r1)
            devs.mulham.horizontalcalendar.HorizontalCalendarView r2 = r4.calendarView
            devs.mulham.horizontalcalendar.HorizontalCalendar$2 r3 = new devs.mulham.horizontalcalendar.HorizontalCalendar$2
            r3.<init>(r0)
            r2.post(r3)
        L_0x0023:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: devs.mulham.horizontalcalendar.HorizontalCalendar.centerToPositionWithNoAnimation(int):void");
    }

    /* access modifiers changed from: package-private */
    public void refreshItemsSelector(int position1, int... positions) {
        this.mCalendarAdapter.notifyItemChanged(position1, "UPDATE_SELECTOR");
        if (positions != null && positions.length > 0) {
            for (int pos : positions) {
                this.mCalendarAdapter.notifyItemChanged(pos, "UPDATE_SELECTOR");
            }
        }
    }

    public boolean isItemDisabled(int position) {
        return this.mCalendarAdapter.isDisabled(position);
    }

    public void refresh() {
        this.mCalendarAdapter.notifyDataSetChanged();
    }

    public void show() {
        this.calendarView.setVisibility(0);
    }

    public void hide() {
        this.calendarView.setVisibility(4);
    }

    public void post(Runnable runnable) {
        this.calendarView.post(runnable);
    }

    @TargetApi(21)
    public void setElevation(float elevation) {
        this.calendarView.setElevation(elevation);
    }

    public Calendar getSelectedDate() {
        return this.mCalendarAdapter.getItem(this.calendarView.getPositionOfCenterItem());
    }

    public int getSelectedDatePosition() {
        return this.calendarView.getPositionOfCenterItem();
    }

    public Calendar getDateAt(int position) throws IndexOutOfBoundsException {
        return this.mCalendarAdapter.getItem(position);
    }

    public boolean contains(Calendar date) {
        return positionOfDate(date) != -1;
    }

    public HorizontalCalendarView getCalendarView() {
        return this.calendarView;
    }

    public Context getContext() {
        return this.calendarView.getContext();
    }

    public void setRange(Calendar startDate2, Calendar endDate2) {
        this.startDate = startDate2;
        this.endDate = endDate2;
        this.mCalendarAdapter.update(startDate2, endDate2, false);
    }

    public CalendarItemStyle getDefaultStyle() {
        return this.defaultStyle;
    }

    public CalendarItemStyle getSelectedItemStyle() {
        return this.selectedItemStyle;
    }

    public HorizontalCalendarConfig getConfig() {
        return this.config;
    }

    public int getNumberOfDatesOnScreen() {
        return this.numberOfDatesOnScreen;
    }

    public int getShiftCells() {
        return this.numberOfDatesOnScreen / 2;
    }

    public int positionOfDate(Calendar date) {
        int position;
        if (Utils.isDateBefore(date, this.startDate) || Utils.isDateAfter(date, this.endDate)) {
            return -1;
        }
        if (this.mode == Mode.DAYS) {
            if (Utils.isSameDate(date, this.startDate)) {
                position = 0;
            } else {
                position = Utils.daysBetween(this.startDate, date);
            }
        } else if (Utils.isSameMonth(date, this.startDate)) {
            position = 0;
        } else {
            position = Utils.monthsBetween(this.startDate, date);
        }
        return position + (this.numberOfDatesOnScreen / 2);
    }

    public static class Builder {
        private ConfigBuilder configBuilder;
        Calendar defaultSelectedDate;
        private HorizontalCalendarPredicate disablePredicate;
        Calendar endDate;
        private CalendarEventsPredicate eventsPredicate;
        Mode mode;
        int numberOfDatesOnScreen;
        final View rootView;
        Calendar startDate;
        final int viewId;

        public Builder(View rootView2, int viewId2) {
            this.rootView = rootView2;
            this.viewId = viewId2;
        }

        public Builder(Activity activity, int viewId2) {
            this.rootView = activity.getWindow().getDecorView();
            this.viewId = viewId2;
        }

        public Builder range(Calendar startDate2, Calendar endDate2) {
            this.startDate = startDate2;
            this.endDate = endDate2;
            return this;
        }

        public Builder mode(Mode mode2) {
            this.mode = mode2;
            return this;
        }

        public Builder datesNumberOnScreen(int numberOfItemsOnScreen) {
            this.numberOfDatesOnScreen = numberOfItemsOnScreen;
            return this;
        }

        public Builder defaultSelectedDate(Calendar date) {
            this.defaultSelectedDate = date;
            return this;
        }

        public Builder disableDates(HorizontalCalendarPredicate predicate) {
            this.disablePredicate = predicate;
            return this;
        }

        public Builder addEvents(CalendarEventsPredicate predicate) {
            this.eventsPredicate = predicate;
            return this;
        }

        public ConfigBuilder configure() {
            if (this.configBuilder == null) {
                this.configBuilder = new ConfigBuilder(this);
            }
            return this.configBuilder;
        }

        private void initDefaultValues() throws IllegalStateException {
            if (this.startDate == null || this.endDate == null) {
                throw new IllegalStateException("HorizontalCalendar range was not specified, either startDate or endDate is null!");
            }
            if (this.mode == null) {
                this.mode = Mode.DAYS;
            }
            if (this.numberOfDatesOnScreen <= 0) {
                this.numberOfDatesOnScreen = 5;
            }
            if (this.defaultSelectedDate == null) {
                this.defaultSelectedDate = Calendar.getInstance();
            }
        }

        public HorizontalCalendar build() throws IllegalStateException {
            initDefaultValues();
            if (this.configBuilder == null) {
                this.configBuilder = new ConfigBuilder(this);
                this.configBuilder.end();
            }
            HorizontalCalendar horizontalCalendar = new HorizontalCalendar(this, this.configBuilder.createConfig(), this.configBuilder.createDefaultStyle(), this.configBuilder.createSelectedItemStyle());
            horizontalCalendar.init(this.rootView, this.defaultSelectedDate, this.disablePredicate, this.eventsPredicate);
            return horizontalCalendar;
        }
    }

    private class HorizontalCalendarScrollListener extends RecyclerView.OnScrollListener {
        int lastSelectedItem = -1;
        final Runnable selectedItemRefresher = new SelectedItemRefresher();

        HorizontalCalendarScrollListener() {
        }

        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            HorizontalCalendar.this.post(this.selectedItemRefresher);
            if (HorizontalCalendar.this.calendarListener != null) {
                HorizontalCalendar.this.calendarListener.onCalendarScroll(HorizontalCalendar.this.calendarView, dx, dy);
            }
        }

        private class SelectedItemRefresher implements Runnable {
            SelectedItemRefresher() {
            }

            public void run() {
                int positionOfCenterItem = HorizontalCalendar.this.calendarView.getPositionOfCenterItem();
                if (HorizontalCalendarScrollListener.this.lastSelectedItem == -1 || HorizontalCalendarScrollListener.this.lastSelectedItem != positionOfCenterItem) {
                    HorizontalCalendar.this.refreshItemsSelector(positionOfCenterItem, new int[0]);
                    if (HorizontalCalendarScrollListener.this.lastSelectedItem != -1) {
                        HorizontalCalendar.this.refreshItemsSelector(HorizontalCalendarScrollListener.this.lastSelectedItem, new int[0]);
                    }
                    HorizontalCalendarScrollListener.this.lastSelectedItem = positionOfCenterItem;
                }
            }
        }
    }
}
