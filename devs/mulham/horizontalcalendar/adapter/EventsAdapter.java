package devs.mulham.horizontalcalendar.adapter;

import android.content.Context;
import android.support.p001v4.content.ContextCompat;
import android.support.p001v4.graphics.drawable.DrawableCompat;
import android.support.p004v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import devs.mulham.horizontalcalendar.C0544R;
import devs.mulham.horizontalcalendar.model.CalendarEvent;
import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventViewHolder> {
    private List<CalendarEvent> eventList;

    public EventsAdapter(List<CalendarEvent> eventList2) {
        this.eventList = eventList2;
    }

    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        ImageView imageView = new ImageView(context);
        imageView.setImageDrawable(DrawableCompat.wrap(ContextCompat.getDrawable(context, C0544R.C0545drawable.ic_circle_white_8dp)));
        return new EventViewHolder(imageView);
    }

    public void onBindViewHolder(EventViewHolder holder, int position) {
        CalendarEvent event = getItem(position);
        ImageView imageView = (ImageView) holder.itemView;
        imageView.setContentDescription(event.getDescription());
        DrawableCompat.setTint(imageView.getDrawable(), event.getColor());
    }

    public CalendarEvent getItem(int position) throws IndexOutOfBoundsException {
        return this.eventList.get(position);
    }

    public int getItemCount() {
        return this.eventList.size();
    }

    public void update(List<CalendarEvent> eventList2) {
        this.eventList = eventList2;
        notifyDataSetChanged();
    }

    static class EventViewHolder extends RecyclerView.ViewHolder {
        EventViewHolder(View itemView) {
            super(itemView);
        }
    }
}
