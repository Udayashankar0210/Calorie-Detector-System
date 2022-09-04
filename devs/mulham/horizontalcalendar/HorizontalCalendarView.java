package devs.mulham.horizontalcalendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.p001v4.view.ViewCompat;
import android.support.p004v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.TypedValue;
import devs.mulham.horizontalcalendar.adapter.HorizontalCalendarBaseAdapter;
import devs.mulham.horizontalcalendar.model.CalendarItemStyle;
import devs.mulham.horizontalcalendar.model.HorizontalCalendarConfig;

public class HorizontalCalendarView extends RecyclerView {
    private final float FLING_SCALE_DOWN_FACTOR = 0.5f;
    private HorizontalCalendarConfig config;
    private CalendarItemStyle defaultStyle;
    private CalendarItemStyle selectedItemStyle;
    private int shiftCells;

    public HorizontalCalendarView(Context context) {
        super(context);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HorizontalCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, C0544R.styleable.HorizontalCalendarView, 0, 0);
        try {
            int textColorNormal = a.getColor(C0544R.styleable.HorizontalCalendarView_textColorNormal, -3355444);
            int colorTopText = a.getColor(C0544R.styleable.HorizontalCalendarView_colorTopText, textColorNormal);
            int colorMiddleText = a.getColor(C0544R.styleable.HorizontalCalendarView_colorMiddleText, textColorNormal);
            int colorBottomText = a.getColor(C0544R.styleable.HorizontalCalendarView_colorBottomText, textColorNormal);
            int textColorSelected = a.getColor(C0544R.styleable.HorizontalCalendarView_textColorSelected, ViewCompat.MEASURED_STATE_MASK);
            int colorTopTextSelected = a.getColor(C0544R.styleable.HorizontalCalendarView_colorTopTextSelected, textColorSelected);
            int colorMiddleTextSelected = a.getColor(C0544R.styleable.HorizontalCalendarView_colorMiddleTextSelected, textColorSelected);
            int colorBottomTextSelected = a.getColor(C0544R.styleable.HorizontalCalendarView_colorBottomTextSelected, textColorSelected);
            Drawable selectedDateBackground = a.getDrawable(C0544R.styleable.HorizontalCalendarView_selectedDateBackground);
            int selectorColor = a.getColor(C0544R.styleable.HorizontalCalendarView_selectorColor, fetchAccentColor());
            float sizeTopText = getRawSizeValue(a, C0544R.styleable.HorizontalCalendarView_sizeTopText, 14.0f);
            int i = textColorNormal;
            float sizeMiddleText = getRawSizeValue(a, C0544R.styleable.HorizontalCalendarView_sizeMiddleText, 24.0f);
            float sizeBottomText = getRawSizeValue(a, C0544R.styleable.HorizontalCalendarView_sizeBottomText, 14.0f);
            int i2 = textColorSelected;
            this.defaultStyle = new CalendarItemStyle(colorTopText, colorMiddleText, colorBottomText, (Drawable) null);
            this.selectedItemStyle = new CalendarItemStyle(colorTopTextSelected, colorMiddleTextSelected, colorBottomTextSelected, selectedDateBackground);
            this.config = new HorizontalCalendarConfig(sizeTopText, sizeMiddleText, sizeBottomText, Integer.valueOf(selectorColor));
            a.recycle();
        } catch (Throwable th) {
            Throwable th2 = th;
            a.recycle();
            throw th2;
        }
    }

    private float getRawSizeValue(TypedArray a, int index, float defValue) {
        TypedValue outValue = new TypedValue();
        if (!a.getValue(index, outValue)) {
            return defValue;
        }
        return TypedValue.complexToFloat(outValue.data);
    }

    public boolean fling(int velocityX, int velocityY) {
        return super.fling((int) (((float) velocityX) * 0.5f), velocityY);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthSpec, int heightSpec) {
        if (isInEditMode()) {
            setMeasuredDimension(widthSpec, 150);
        } else {
            super.onMeasure(widthSpec, heightSpec);
        }
    }

    public float getSmoothScrollSpeed() {
        return getLayoutManager().getSmoothScrollSpeed();
    }

    public void setSmoothScrollSpeed(float smoothScrollSpeed) {
        getLayoutManager().setSmoothScrollSpeed(smoothScrollSpeed);
    }

    public HorizontalCalendarBaseAdapter getAdapter() {
        return (HorizontalCalendarBaseAdapter) super.getAdapter();
    }

    public HorizontalLayoutManager getLayoutManager() {
        return (HorizontalLayoutManager) super.getLayoutManager();
    }

    private int fetchAccentColor() {
        TypedValue typedValue = new TypedValue();
        TypedArray a = getContext().obtainStyledAttributes(typedValue.data, new int[]{C0544R.attr.colorAccent});
        int color = a.getColor(0, 0);
        a.recycle();
        return color;
    }

    public void applyConfigFromLayout(HorizontalCalendar horizontalCalendar) {
        horizontalCalendar.getConfig().setupDefaultValues(this.config);
        horizontalCalendar.getDefaultStyle().setupDefaultValues(this.defaultStyle);
        horizontalCalendar.getSelectedItemStyle().setupDefaultValues(this.selectedItemStyle);
        this.config = null;
        this.defaultStyle = null;
        this.selectedItemStyle = null;
        this.shiftCells = horizontalCalendar.getNumberOfDatesOnScreen() / 2;
    }

    public int getPositionOfCenterItem() {
        int firstVisiblePosition;
        HorizontalLayoutManager layoutManager = getLayoutManager();
        if (layoutManager == null || (firstVisiblePosition = layoutManager.findFirstCompletelyVisibleItemPosition()) == -1) {
            return -1;
        }
        return this.shiftCells + firstVisiblePosition;
    }
}
