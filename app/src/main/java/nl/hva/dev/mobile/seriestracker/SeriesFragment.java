package nl.hva.dev.mobile.seriestracker;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import nl.hva.dev.mobile.seriestracker.dummy.DummyContent;

public class SeriesFragment extends Fragment {

  public SeriesFragment() {
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_series_list, container, false);

    Context context = view.getContext();
    RecyclerView recyclerView = (RecyclerView) view;

    recyclerView.setLayoutManager(new LinearLayoutManager(context));
    recyclerView.setAdapter(new SeriesRecyclerViewAdapter(DummyContent.ITEMS));

    return view;
  }

}
