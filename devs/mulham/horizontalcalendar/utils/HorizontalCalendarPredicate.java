package devs.mulham.horizontalcalendar.utils;

import devs.mulham.horizontalcalendar.model.CalendarItemStyle;
import java.util.Calendar;

public interface HorizontalCalendarPredicate {
    CalendarItemStyle style();

    boolean test(Calendar calendar);

    /* renamed from: devs.mulham.horizontalcalendar.utils.HorizontalCalendarPredicate$Or */
    public static class C0547Or implements HorizontalCalendarPredicate {
        private final HorizontalCalendarPredicate firstPredicate;
        private final HorizontalCalendarPredicate secondPredicate;

        public C0547Or(HorizontalCalendarPredicate firstPredicate2, HorizontalCalendarPredicate secondPredicate2) {
            this.firstPredicate = firstPredicate2;
            this.secondPredicate = secondPredicate2;
        }

        public boolean test(Calendar date) {
            return this.firstPredicate.test(date) || this.secondPredicate.test(date);
        }

        public CalendarItemStyle style() {
            return this.firstPredicate.style();
        }
    }
}
