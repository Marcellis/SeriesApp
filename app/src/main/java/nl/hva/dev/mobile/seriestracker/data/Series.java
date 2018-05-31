package nl.hva.dev.mobile.seriestracker.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import java.util.ArrayList;
import java.util.List;
import nl.hva.dev.mobile.seriestracker.util.DataConverter;

@Entity
public class Series {

  @PrimaryKey
  public Integer id;
  public String seriesName;
  @TypeConverters(DataConverter.class)
  public List<String> aliases = new ArrayList<>();
  public String banner;
  public String status;
  public String firstAired;
  public String network;
  public String networkId;
  public String runtime;
  @TypeConverters(DataConverter.class)
  public List<String> genre = new ArrayList<>();
  public String overview;
  public Long lastUpdated;
  public String airsDayOfWeek;
  public String airsTime;
  public String rating;
  public String imdbId;
  public String zap2itId;
  public String added;
  public Double siteRating;
  public Integer siteRatingCount;
}