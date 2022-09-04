package devs.mulham.horizontalcalendar.adapter;

import android.support.p004v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import devs.mulham.horizontalcalendar.C0544R;

class DateViewHolder extends RecyclerView.ViewHolder {
    RecyclerView eventsRecyclerView;
    View layoutContent;
    View selectionView;
    TextView textBottom;
    TextView textMiddle;
    TextView textTop;

    DateViewHolder(View rootView) {
        super(rootView);
        this.textTop = (TextView) rootView.findViewById(C0544R.C0546id.hc_text_top);
        this.textMiddle = (TextView) rootView.findViewById(C0544R.C0546id.hc_text_middle);
        this.textBottom = (TextView) rootView.findViewById(C0544R.C0546id.hc_text_bottom);
        this.layoutContent = rootView.findViewById(C0544R.C0546id.hc_layoutContent);
        this.selectionView = rootView.findViewById(C0544R.C0546id.hc_selector);
        this.eventsRecyclerView = (RecyclerView) rootView.findViewById(C0544R.C0546id.hc_events_recyclerView);
    }
}
