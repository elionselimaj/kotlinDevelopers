package al.logic.android.kotlinapplication.di.components;

import android.app.Application;

import javax.inject.Singleton;

import al.logic.android.kotlinapplication.KotlinUsersApp;
import al.logic.android.kotlinapplication.di.builder.ActivityBuilderModule;
import al.logic.android.kotlinapplication.di.module.KotlinDevApplicationModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * The main application component which initializes all the dependent modules
 */

@Singleton
@Component(modules = {
        KotlinDevApplicationModule.class,
        AndroidInjectionModule.class,
        ActivityBuilderModule.class})

public interface AppComponent {


    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(KotlinUsersApp kotlinDevsApp);
}
