package widget.base.data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.android.black.R;

import java.util.LinkedList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by etiennelawlor on 7/17/14.
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // region Constants
    // endregion

    // region Member Variables
    private Context mContext;
    private final LayoutInflater mInflater;
    private List<String> datas = new LinkedList<>();
    // endregion

    // region Constructors
    public RecycleViewAdapter(Context context) {
        mContext = context;
        for (int i = 0; i < 20; i++) {
            datas.add(""+i);
        }
        mInflater = LayoutInflater.from(mContext);
    }
    // endregion

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recyleview, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String post = datas.get(position);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }
}
