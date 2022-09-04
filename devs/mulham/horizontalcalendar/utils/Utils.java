package devs.mulham.horizontalcalendar.utils;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public final class Utils {
    public static int calculateCellWidth(Context context, int itemsOnScreen) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager == null) {
            return -2;
        }
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x / itemsOnScreen;
    }

    public static int calculateRelativeCenterPosition(int position, int centerItem, int shiftCells) {
        int relativeCenterPosition = position;
        if (position > centerItem) {
            return position + shiftCells;
        }
        if (position < centerItem) {
            return position - shiftCells;
        }
        return relativeCenterPosition;
    }

    public static boolean isSameDate(Calendar calendar1, Calendar calendar2) {
        return isSameMonth(calendar1, calendar2) && calendar1.get(5) == calendar2.get(5);
    }

    public static boolean isSameMonth(Calendar calendar1, Calendar calendar2) {
        int month = calendar1.get(2);
        if (calendar1.get(1) == calendar2.get(1) && month == calendar2.get(2)) {
            return true;
        }
        return false;
    }

    public static boolean isDateBefore(Calendar date, Calendar origin) {
        int dayOfYear = date.get(6);
        int year = date.get(1);
        if (year < origin.get(1)) {
            return true;
        }
        if (year != origin.get(1) || dayOfYear >= origin.get(6)) {
            return false;
        }
        return true;
    }

    public static boolean isDateAfter(Calendar date, Calendar origin) {
        int dayOfYear = date.get(6);
        int year = date.get(1);
        if (year > origin.get(1)) {
            return true;
        }
        if (year != origin.get(1) || dayOfYear <= origin.get(6)) {
            return false;
        }
        return true;
    }

    public static int daysBetween(Calendar startInclusive, Calendar endExclusive) {
        zeroTime(startInclusive);
        zeroTime(endExclusive);
        return (int) TimeUnit.MILLISECONDS.toDays(endExclusive.getTimeInMillis() - startInclusive.getTimeInMillis());
    }

    public static int monthsBetween(Calendar startInclusive, Calendar endExclusive) {
        int startMonth = startInclusive.get(2);
        return (endExclusive.get(2) - startMonth) + ((endExclusive.get(1) - startInclusive.get(1)) * 12);
    }

    public static void zeroTime(Calendar calendar) {
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
    }
}
