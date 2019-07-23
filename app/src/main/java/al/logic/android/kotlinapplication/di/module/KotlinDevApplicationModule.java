package al.logic.android.kotlinapplication.di.module;

import android.app.Application;
import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import al.logic.android.kotlinapplication.data.remote.ApiService;
import al.logic.android.kotlinapplication.rx.AppSchedulerProvider;
import al.logic.android.kotlinapplication.rx.SchedulerProvider;
import al.logic.android.kotlinapplication.utils.AppConstants;
import al.logic.android.kotlinapplication.utils.AppDataManager;
import al.logic.android.kotlinapplication.utils.DataManager;
import al.logic.android.kotlinapplication.viewmodel.UsersEntity;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class KotlinDevApplicationModule {

    @Provides
    @Singleton
    Context provideContext(Application application){
        return application;
    }

    @Provides
    @Singleton
    UsersEntity provideUsersModel(){
        return new UsersEntity();
    }

    @Provides
    @Singleton
    CompositeDisposable getCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider providerScheduler() {
        return new AppSchedulerProvider();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder builder =
                new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return builder.setLenient().create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofitForUsers(Gson gson, OkHttpClient okHttpClient) {

        return new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }


    @Provides
    @Singleton
    OkHttpClient getRequestHeader() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .build();
            return chain.proceed(request);
        })
                .connectTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS);

        return httpClient.build();
    }

    @Provides
    ApiService getApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

}
