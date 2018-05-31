package nl.hva.dev.mobile.seriestracker.network;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

class TvdbInterceptor implements Interceptor {

  private TvdbApi tvdbApi;

  TvdbInterceptor(TvdbApi tvdbApi) {
    this.tvdbApi = tvdbApi;
  }

  private static Response handleIntercept(Chain chain, @Nullable String jsonWebToken)
      throws IOException {

    Request request = chain.request();
    Request.Builder builder = request.newBuilder();

    if (jsonWebTokenIsNotEmpty(jsonWebToken)) {
      builder.header(TvdbApi.HEADER_AUTHORIZATION, "Bearer" + " " + jsonWebToken);
    }
    return chain.proceed(builder.build());
  }

  private static boolean jsonWebTokenIsNotEmpty(@Nullable String jsonWebToken) {
    return jsonWebToken != null && jsonWebToken.length() != 0;
  }

  @Override
  public Response intercept(@NonNull Chain chain) throws IOException {
    return handleIntercept(chain, jsonWebToken());
  }

  @Nullable
  private String jsonWebToken() {
    return tvdbApi.jsonWebToken();
  }

}
