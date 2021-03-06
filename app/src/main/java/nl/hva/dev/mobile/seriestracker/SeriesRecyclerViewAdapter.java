package nl.hva.dev.mobile.seriestracker;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import nl.hva.dev.mobile.seriestracker.dummy.DummyContent.DummyItem;

public class SeriesRecyclerViewAdapter extends
    RecyclerView.Adapter<SeriesRecyclerViewAdapter.ViewHolder> {

  private final List<DummyItem> mValues;

  public SeriesRecyclerViewAdapter(List<DummyItem> items) {
    mValues = items;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.fragment_item, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final ViewHolder holder, int position) {
    holder.mItem = mValues.get(position);
    holder.mContentView.setText(mValues.get(position).content);

    holder.mView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
      }
    });
  }

  @Override
  public int getItemCount() {
    return mValues.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    public final View mView;
    public final TextView mContentView;
    public DummyItem mItem;

    public ViewHolder(View view) {
      super(view);
      mView = view;
      mContentView = view.findViewById(R.id.seriesNameTextView);
    }

    @Override
    public String toString() {
      return super.toString() + " '" + mContentView.getText() + "'";
    }
  }
}
