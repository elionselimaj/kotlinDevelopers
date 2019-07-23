package al.logic.android.kotlinapplication.utils;

import com.google.gson.JsonElement;

import javax.inject.Inject;
import javax.inject.Singleton;

import al.logic.android.kotlinapplication.data.UsersResponse;
import al.logic.android.kotlinapplication.data.remote.ApiService;
import al.logic.android.kotlinapplication.rx.SchedulerProvider;
import io.reactivex.Observable;
import io.reactivex.Scheduler;

@Singleton
public class AppDataManager implements DataManager {

    private ApiService apiService;
    private SchedulerProvider provider;

    @Inject
    public AppDataManager(ApiService mApiService,  SchedulerProvider schedulerProvider) {
        apiService = mApiService;
        provider = schedulerProvider;
    }

    @Override
    public Observable<UsersResponse> fetchKotlinUsers(String language,int page, int perPage) {
        return apiService.fetchKotlinUsers(language,page,perPage);
    }

    @Override
    public Observable<JsonElement> getUserDetails(String url,String clientId,String clientSecret) {
        return apiService.getUserDetails(url,clientId,clientSecret);
    }

    @Override
    public Scheduler io() {
        return provider.io();
    }

    @Override
    public Scheduler ui() {
        return provider.ui();
    }

    @Override
    public Scheduler computation() {
        return provider.computation();
    }
}
