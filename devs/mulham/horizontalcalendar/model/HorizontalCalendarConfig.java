package devs.mulham.horizontalcalendar.model;

public class HorizontalCalendarConfig {
    public static final String DEFAULT_FORMAT_TEXT_BOTTOM = "EEE";
    public static final String DEFAULT_FORMAT_TEXT_MIDDLE = "dd";
    public static final String DEFAULT_FORMAT_TEXT_TOP = "MMM";
    public static final float DEFAULT_SIZE_TEXT_BOTTOM = 14.0f;
    public static final float DEFAULT_SIZE_TEXT_MIDDLE = 24.0f;
    public static final float DEFAULT_SIZE_TEXT_TOP = 14.0f;
    private String formatBottomText;
    private String formatMiddleText;
    private String formatTopText;
    private Integer selectorColor;
    private boolean showBottomText;
    private boolean showTopText;
    private float sizeBottomText;
    private float sizeMiddleText;
    private float sizeTopText;

    public HorizontalCalendarConfig() {
    }

    public HorizontalCalendarConfig(float sizeTopText2, float sizeMiddleText2, float sizeBottomText2, Integer selectorColor2) {
        this.sizeTopText = sizeTopText2;
        this.sizeMiddleText = sizeMiddleText2;
        this.sizeBottomText = sizeBottomText2;
        this.selectorColor = selectorColor2;
    }

    public HorizontalCalendarConfig setFormatTopText(String formatTopText2) {
        this.formatTopText = formatTopText2;
        return this;
    }

    public HorizontalCalendarConfig setFormatMiddleText(String formatMiddleText2) {
        this.formatMiddleText = formatMiddleText2;
        return this;
    }

    public HorizontalCalendarConfig setFormatBottomText(String formatBottomText2) {
        this.formatBottomText = formatBottomText2;
        return this;
    }

    public HorizontalCalendarConfig setSizeTopText(float sizeTopText2) {
        this.sizeTopText = sizeTopText2;
        return this;
    }

    public HorizontalCalendarConfig setSizeMiddleText(float sizeMiddleText2) {
        this.sizeMiddleText = sizeMiddleText2;
        return this;
    }

    public HorizontalCalendarConfig setSizeBottomText(float sizeBottomText2) {
        this.sizeBottomText = sizeBottomText2;
        return this;
    }

    public HorizontalCalendarConfig setSelectorColor(Integer selectorColor2) {
        this.selectorColor = selectorColor2;
        return this;
    }

    public HorizontalCalendarConfig setShowTopText(boolean showTopText2) {
        this.showTopText = showTopText2;
        return this;
    }

    public HorizontalCalendarConfig setShowBottomText(boolean showBottomText2) {
        this.showBottomText = showBottomText2;
        return this;
    }

    public String getFormatTopText() {
        return this.formatTopText;
    }

    public String getFormatMiddleText() {
        return this.formatMiddleText;
    }

    public String getFormatBottomText() {
        return this.formatBottomText;
    }

    public float getSizeTopText() {
        return this.sizeTopText;
    }

    public float getSizeMiddleText() {
        return this.sizeMiddleText;
    }

    public float getSizeBottomText() {
        return this.sizeBottomText;
    }

    public Integer getSelectorColor() {
        return this.selectorColor;
    }

    public boolean isShowTopText() {
        return this.showTopText;
    }

    public boolean isShowBottomText() {
        return this.showBottomText;
    }

    public void setupDefaultValues(HorizontalCalendarConfig defaultConfig) {
        if (defaultConfig != null) {
            if (this.selectorColor == null) {
                this.selectorColor = defaultConfig.selectorColor;
            }
            if (this.sizeTopText == 0.0f) {
                this.sizeTopText = defaultConfig.sizeTopText;
            }
            if (this.sizeMiddleText == 0.0f) {
                this.sizeMiddleText = defaultConfig.sizeMiddleText;
            }
            if (this.sizeBottomText == 0.0f) {
                this.sizeBottomText = defaultConfig.sizeBottomText;
            }
        }
    }
}
