package devs.mulham.horizontalcalendar.model;

public class CalendarEvent {
    private int color;
    private String description;

    public CalendarEvent(int color2) {
        this.color = color2;
    }

    public CalendarEvent(int color2, String description2) {
        this.color = color2;
        this.description = description2;
    }

    public int getColor() {
        return this.color;
    }

    public void setColor(int color2) {
        this.color = color2;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description2) {
        this.description = description2;
    }
}
