package nl.hva.dev.mobile.seriestracker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import java.io.IOException;
import nl.hva.dev.mobile.seriestracker.data.Series;
import nl.hva.dev.mobile.seriestracker.data.SeriesResponse;
import nl.hva.dev.mobile.seriestracker.network.TvdbApi;
import nl.hva.dev.mobile.seriestracker.util.Constants;

public class HomeActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

  private TvdbApi api;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    api = new TvdbApi(Constants.API_KEY);

    ViewPager viewPager = findViewById(R.id.container);
    setupViewPager(viewPager);

    TabLayout tabLayout = findViewById(R.id.tabs);
    tabLayout.setupWithViewPager(viewPager);
    setupTabLayout(tabLayout, viewPager);

    // SIDE NAV
    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

    //FAB
    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        startApiThread();
      }
    });

  }

  private void startApiThread() {
    Thread thread = new Thread(new Runnable() {
      public void run() {
        try {
          TvdbApi api = new TvdbApi(Constants.API_KEY);

          try {
            // test series
            retrofit2.Response<SeriesResponse> response = api.series().series(83462, "en")
                .execute();

            if (response.isSuccessful()) {
              Series series = response.body().data;
              System.out.println(series.seriesName + " is awesome!");
            }


          } catch (IOException e) {
            e.printStackTrace();
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
    thread.start();
  }

  private void setupViewPager(ViewPager viewPager) {
    viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

      @Override
      public Fragment getItem(int position) {
        switch (position) {
          case 0:
            return new SeriesFragment();
          case 1:
            return new SeriesFragment();
          case 2:
            return new SeriesFragment();
          default:
            return new SeriesFragment();
        }
      }

      @Override
      public int getCount() {
        return 3;
      }

      @Override
      public CharSequence getPageTitle(int position) {
        switch (position) {
          case 0:
            return "Series";
          case 1:
            return "Upcoming";
          case 2:
            return "News";
        }
        return "";
      }

    });
  }

  private void setupTabLayout(TabLayout tabLayout, final ViewPager mViewPager) {
    tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
      @Override
      public void onTabSelected(TabLayout.Tab tab) {
        mViewPager.setCurrentItem(tab.getPosition());
      }

      @Override
      public void onTabUnselected(TabLayout.Tab tab) {
      }

      @Override
      public void onTabReselected(TabLayout.Tab tab) {
      }
    });
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if (id == R.id.nav_series) {

    } else if (id == R.id.nav_movies) {

    } else if (id == R.id.nav_settings) {

    } else if (id == R.id.nav_share) {

    }

    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

}
