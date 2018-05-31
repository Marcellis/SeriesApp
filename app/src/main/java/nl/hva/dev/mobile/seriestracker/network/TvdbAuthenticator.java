package nl.hva.dev.mobile.seriestracker.network;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.io.IOException;
import nl.hva.dev.mobile.seriestracker.data.LoginCredentials;
import nl.hva.dev.mobile.seriestracker.data.Token;
import nl.hva.dev.mobile.seriestracker.network.services.TvdbAuthenticationService;
import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Call;

class TvdbAuthenticator implements Authenticator {

  private static final String LOGIN_PATH = "/" + TvdbAuthenticationService.LOGIN_PATH;
  private TvdbApi tvdbApi;

  TvdbAuthenticator(TvdbApi tvdbApi) {
    this.tvdbApi = tvdbApi;
  }

  @Nullable
  private static Request handleAuth(Response response, TvdbApi tvdbApi) throws IOException {

    String path = response.request().url().encodedPath();

    if (LOGIN_PATH.equals(path)) {
      return null;
    }

    // get the jsonWebToken
    Call<Token> loginCall = tvdbApi.authenticate().login(new LoginCredentials(tvdbApi.apiKey()));
    retrofit2.Response<Token> loginResponse = loginCall.execute();
    if (!loginResponse.isSuccessful()) {
      return null;
    }

    String jsonWebToken = loginResponse.body().token;
    tvdbApi.jsonWebToken(jsonWebToken);

    return response.request().newBuilder()
        .header(TvdbApi.HEADER_AUTHORIZATION, "Bearer" + " " + jsonWebToken)
        .build();
  }

  @Nullable
  @Override
  public Request authenticate(@NonNull Route route, @NonNull Response response) throws IOException {
    return handleAuth(response, tvdbApi);
  }

}
