package al.logic.android.kotlinapplication.data.remote;

import com.google.gson.JsonElement;

import al.logic.android.kotlinapplication.data.UsersResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("search/users")
    Observable<UsersResponse> fetchKotlinUsers(@Query("q") String language,
                                               @Query("page") int page,
                                               @Query("per_page") int perPage);

    @GET("users/{username}")
    Observable<JsonElement> getUserDetails(
            @Path("username") String url,
            @Query("client_id") String clientId,
            @Query("client_secret") String clientSecret
    );
}

