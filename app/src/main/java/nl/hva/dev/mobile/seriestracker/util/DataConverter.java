package nl.hva.dev.mobile.seriestracker.util;

import android.arch.persistence.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class DataConverter {

  @TypeConverter
  public String fromStringList(List<String> list) {
    if (list == null) {
      return (null);
    }
    Gson gson = new Gson();
    Type type = new TypeToken<List<String>>() {
    }.getType();
    return gson.toJson(list, type);
  }

  @TypeConverter
  public List<String> toStringList(String listString) {
    if (listString == null) {
      return (null);
    }
    Gson gson = new Gson();
    Type type = new TypeToken<List<String>>() {
    }.getType();
    return gson.fromJson(listString, type);
  }
}