package al.logic.android.kotlinapplication.di.module;

import al.logic.android.kotlinapplication.utils.DataManager;
import al.logic.android.kotlinapplication.viewmodel.UserActivityViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class UserListModule {

    @Provides
    UserActivityViewModel provideUserListViewModel(DataManager manager){

        return new UserActivityViewModel(manager);
    }
}
