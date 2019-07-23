package al.logic.android.kotlinapplication.di.builder;


import al.logic.android.kotlinapplication.di.module.UserListModule;
import al.logic.android.kotlinapplication.view.activity.MainActivity;
import al.logic.android.kotlinapplication.view.activity.UserDetailActivity;
import al.logic.android.kotlinapplication.view.adapter.UserAdapterProvider;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * The module which provides the android injection service to Activities.
 */
@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = {UserAdapterProvider.class, UserListModule.class})
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector
    abstract UserDetailActivity userDetailActivity();

}
