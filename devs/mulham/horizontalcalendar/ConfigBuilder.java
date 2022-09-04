package devs.mulham.horizontalcalendar;

import android.graphics.drawable.Drawable;
import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.model.CalendarItemStyle;
import devs.mulham.horizontalcalendar.model.HorizontalCalendarConfig;

public class ConfigBuilder {
    private final HorizontalCalendar.Builder calendarBuilder;
    private int colorTextBottom;
    private int colorTextBottomSelected;
    private int colorTextMiddle;
    private int colorTextMiddleSelected;
    private int colorTextTop;
    private int colorTextTopSelected;
    private String formatBottomText;
    private String formatMiddleText;
    private String formatTopText;
    private Drawable selectedItemBackground;
    private Integer selectorColor;
    private boolean showBottomText = true;
    private boolean showTopText = true;
    private float sizeBottomText;
    private float sizeMiddleText;
    private float sizeTopText;

    public ConfigBuilder(HorizontalCalendar.Builder calendarBuilder2) {
        this.calendarBuilder = calendarBuilder2;
    }

    public ConfigBuilder textSize(float sizeTopText2, float sizeMiddleText2, float sizeBottomText2) {
        this.sizeTopText = sizeTopText2;
        this.sizeMiddleText = sizeMiddleText2;
        this.sizeBottomText = sizeBottomText2;
        return this;
    }

    public ConfigBuilder sizeTopText(float size) {
        this.sizeTopText = size;
        return this;
    }

    public ConfigBuilder sizeMiddleText(float size) {
        this.sizeMiddleText = size;
        return this;
    }

    public ConfigBuilder sizeBottomText(float size) {
        this.sizeBottomText = size;
        return this;
    }

    public ConfigBuilder selectorColor(Integer selectorColor2) {
        this.selectorColor = selectorColor2;
        return this;
    }

    public ConfigBuilder formatTopText(String format) {
        this.formatTopText = format;
        return this;
    }

    public ConfigBuilder formatMiddleText(String format) {
        this.formatMiddleText = format;
        return this;
    }

    public ConfigBuilder formatBottomText(String format) {
        this.formatBottomText = format;
        return this;
    }

    public ConfigBuilder showTopText(boolean value) {
        this.showTopText = value;
        return this;
    }

    public ConfigBuilder showBottomText(boolean value) {
        this.showBottomText = value;
        return this;
    }

    public ConfigBuilder textColor(int textColorNormal, int textColorSelected) {
        this.colorTextTop = textColorNormal;
        this.colorTextMiddle = textColorNormal;
        this.colorTextBottom = textColorNormal;
        this.colorTextTopSelected = textColorSelected;
        this.colorTextMiddleSelected = textColorSelected;
        this.colorTextBottomSelected = textColorSelected;
        return this;
    }

    public ConfigBuilder colorTextTop(int textColorNormal, int textColorSelected) {
        this.colorTextTop = textColorNormal;
        this.colorTextTopSelected = textColorSelected;
        return this;
    }

    public ConfigBuilder colorTextMiddle(int textColorNormal, int textColorSelected) {
        this.colorTextMiddle = textColorNormal;
        this.colorTextMiddleSelected = textColorSelected;
        return this;
    }

    public ConfigBuilder colorTextBottom(int textColorNormal, int textColorSelected) {
        this.colorTextBottom = textColorNormal;
        this.colorTextBottomSelected = textColorSelected;
        return this;
    }

    public ConfigBuilder selectedDateBackground(Drawable background) {
        this.selectedItemBackground = background;
        return this;
    }

    public HorizontalCalendar.Builder end() {
        if (this.formatMiddleText == null) {
            this.formatMiddleText = HorizontalCalendarConfig.DEFAULT_FORMAT_TEXT_MIDDLE;
        }
        if (this.formatTopText == null && this.showTopText) {
            this.formatTopText = HorizontalCalendarConfig.DEFAULT_FORMAT_TEXT_TOP;
        }
        if (this.formatBottomText == null && this.showBottomText) {
            this.formatBottomText = HorizontalCalendarConfig.DEFAULT_FORMAT_TEXT_BOTTOM;
        }
        return this.calendarBuilder;
    }

    /* access modifiers changed from: package-private */
    public HorizontalCalendarConfig createConfig() {
        HorizontalCalendarConfig config = new HorizontalCalendarConfig(this.sizeTopText, this.sizeMiddleText, this.sizeBottomText, this.selectorColor);
        config.setFormatTopText(this.formatTopText);
        config.setFormatMiddleText(this.formatMiddleText);
        config.setFormatBottomText(this.formatBottomText);
        config.setShowTopText(this.showTopText);
        config.setShowBottomText(this.showBottomText);
        return config;
    }

    /* access modifiers changed from: package-private */
    public CalendarItemStyle createDefaultStyle() {
        return new CalendarItemStyle(this.colorTextTop, this.colorTextMiddle, this.colorTextBottom, (Drawable) null);
    }

    /* access modifiers changed from: package-private */
    public CalendarItemStyle createSelectedItemStyle() {
        return new CalendarItemStyle(this.colorTextTopSelected, this.colorTextMiddleSelected, this.colorTextBottomSelected, this.selectedItemBackground);
    }
}
