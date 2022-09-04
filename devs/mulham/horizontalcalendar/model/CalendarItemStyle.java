package devs.mulham.horizontalcalendar.model;

import android.graphics.drawable.Drawable;

public class CalendarItemStyle {
    private Drawable background;
    private int colorBottomText;
    private int colorMiddleText;
    private int colorTopText;

    public CalendarItemStyle() {
    }

    public CalendarItemStyle(int textColor, Drawable background2) {
        this(textColor, textColor, textColor, background2);
    }

    public CalendarItemStyle(int colorTopText2, int colorMiddleText2, int colorBottomText2, Drawable background2) {
        this.colorTopText = colorTopText2;
        this.colorMiddleText = colorMiddleText2;
        this.colorBottomText = colorBottomText2;
        this.background = background2;
    }

    public int getColorTopText() {
        return this.colorTopText;
    }

    public CalendarItemStyle setColorTopText(int colorTopText2) {
        this.colorTopText = colorTopText2;
        return this;
    }

    public int getColorMiddleText() {
        return this.colorMiddleText;
    }

    public CalendarItemStyle setColorMiddleText(int colorMiddleText2) {
        this.colorMiddleText = colorMiddleText2;
        return this;
    }

    public int getColorBottomText() {
        return this.colorBottomText;
    }

    public CalendarItemStyle setColorBottomText(int colorBottomText2) {
        this.colorBottomText = colorBottomText2;
        return this;
    }

    public Drawable getBackground() {
        return this.background;
    }

    public CalendarItemStyle setBackground(Drawable background2) {
        this.background = background2;
        return this;
    }

    public void setupDefaultValues(CalendarItemStyle defaultValues) {
        if (defaultValues != null) {
            if (this.colorTopText == 0) {
                this.colorTopText = defaultValues.colorTopText;
            }
            if (this.colorMiddleText == 0) {
                this.colorMiddleText = defaultValues.colorMiddleText;
            }
            if (this.colorBottomText == 0) {
                this.colorBottomText = defaultValues.colorBottomText;
            }
            if (this.background == null) {
                this.background = defaultValues.background;
            }
        }
    }
}
