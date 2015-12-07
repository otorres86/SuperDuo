package barqsoft.footballscores.widget;

import android.annotation.TargetApi;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import barqsoft.footballscores.DatabaseContract;
import barqsoft.footballscores.R;
import barqsoft.footballscores.Utilities;


/**
 * Created by Olga on 12/2/2015.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class CollectionWidgetRemoteViewsService extends RemoteViewsService {

    private static final String[] SCORES_COLUMNS = {
            DatabaseContract.scores_table.MATCH_ID,
            DatabaseContract.scores_table.HOME_COL,
            DatabaseContract.scores_table.HOME_GOALS_COL,
            DatabaseContract.scores_table.AWAY_COL,
            DatabaseContract.scores_table.AWAY_GOALS_COL,
            DatabaseContract.scores_table.TIME_COL
    };
    //Column numbers from the above declared projection
    static final int MATCH_ID = 0;
    static final int HOME_COL = 1;
    static final int HOME_GOALS_COL = 2;
    static final int AWAY_COL = 3;
    static final int AWAY_GOALS_COL = 4;
    static final int TIME_COL = 5;

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RemoteViewsFactory() {
            private Cursor data = null;

            @Override
            public void onCreate() {
                // Nothing to do
            }

            @Override
            public void onDataSetChanged() {
                if (data != null) {
                    data.close();
                }
                // This method is called by the app hosting the widget (e.g., the launcher)
                // However, our ContentProvider is not exported so it doesn't have access to the
                // data. Therefore we need to clear (and finally restore) the calling identity so
                // that calls use our process and permission
                final long identityToken = Binder.clearCallingIdentity();
                String currentDate = Utilities.getCurrentDate();
                String[] args = new String[1]; args[0] = currentDate;
                Uri footballScoresForTodayUri = DatabaseContract.scores_table.buildScoreWithDate();
                data = getContentResolver().query(footballScoresForTodayUri,
                        SCORES_COLUMNS,
                        null,
                        args,
                        null);
                Binder.restoreCallingIdentity(identityToken);

            }

            @Override
            public void onDestroy() {
                if (data != null) {
                    data.close();
                    data = null;
                }
            }

            @Override
            public int getCount() {
                return data == null ? 0 : data.getCount();
            }

            @Override
            public RemoteViews getViewAt(int position) {
                if (position == AdapterView.INVALID_POSITION ||
                        data == null || !data.moveToPosition(position)) {
                    return null;
                }
                RemoteViews views = new RemoteViews(getPackageName(),
                        R.layout.widget_list_item);
                String description = getString(R.string.app_icon_description);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
                    setRemoteContentDescription(views, description);
                }
                views.setTextViewText(R.id.widget_home_name, data.getString(HOME_COL));
                views.setTextViewText(R.id.widget_away_name, data.getString(AWAY_COL));
                int score1 = data.getInt(HOME_GOALS_COL);
                int score2 = data.getInt(AWAY_GOALS_COL);
                boolean isRtl = getResources().getBoolean((R.bool.isRtl));
                if(isRtl){
                    int tmp = score1;
                    score1 = score2;
                    score2 = tmp;
                }
                views.setTextViewText(R.id.widget_score_textview, Utilities.getScores(score1, score2));
                views.setTextViewText(R.id.widget_data_textview, data.getString(TIME_COL));

                final Intent fillInIntent = new Intent();

                Uri currentDateScoresUri = DatabaseContract.scores_table.buildScoreWithDate();
                fillInIntent.setData(currentDateScoresUri);
                views.setOnClickFillInIntent(R.id.widget_list_item, fillInIntent);
                return views;
            }

            @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
            private void setRemoteContentDescription(RemoteViews views, String description) {
                views.setContentDescription(R.id.widget_icon, description);
            }

            @Override
            public RemoteViews getLoadingView() {
                return new RemoteViews(getPackageName(), R.layout.widget_list_item);

            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public long getItemId(int position) {
                if (data.moveToPosition(position))
                    return data.getLong(MATCH_ID);
                return position;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }
        };
    }
}
