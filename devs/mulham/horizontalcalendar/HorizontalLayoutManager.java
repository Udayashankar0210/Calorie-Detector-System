package devs.mulham.horizontalcalendar;

import android.content.Context;
import android.support.p004v7.widget.LinearLayoutManager;
import android.support.p004v7.widget.LinearSmoothScroller;
import android.support.p004v7.widget.RecyclerView;
import android.util.DisplayMetrics;

public class HorizontalLayoutManager extends LinearLayoutManager {
    public static final float SPEED_NORMAL = 90.0f;
    public static final float SPEED_SLOW = 125.0f;
    float smoothScrollSpeed = 90.0f;

    HorizontalLayoutManager(Context context, boolean reverseLayout) {
        super(context, 0, reverseLayout);
    }

    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        LinearSmoothScroller smoothScroller = new LinearSmoothScroller(recyclerView.getContext()) {
            /* access modifiers changed from: protected */
            public float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return HorizontalLayoutManager.this.smoothScrollSpeed / ((float) displayMetrics.densityDpi);
            }
        };
        smoothScroller.setTargetPosition(position);
        startSmoothScroll(smoothScroller);
    }

    /* access modifiers changed from: package-private */
    public float getSmoothScrollSpeed() {
        return this.smoothScrollSpeed;
    }

    /* access modifiers changed from: package-private */
    public void setSmoothScrollSpeed(float smoothScrollSpeed2) {
        this.smoothScrollSpeed = smoothScrollSpeed2;
    }
}
