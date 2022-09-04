package devs.mulham.horizontalcalendar.utils;

import devs.mulham.horizontalcalendar.model.CalendarEvent;
import java.util.Calendar;
import java.util.List;

public interface CalendarEventsPredicate {
    List<CalendarEvent> events(Calendar calendar);
}
