package al.logic.android.kotlinapplication.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import al.logic.android.kotlinapplication.utils.DataManager;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private DataManager manager;
    private UsersEntity users;

    @Inject
    AppViewModelFactory( DataManager dataManager,UsersEntity usersEntity) {

        this.manager = dataManager;
        this.users = usersEntity;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
         if (modelClass.isAssignableFrom(UserActivityViewModel.class)) {
            return (T) new UserActivityViewModel(manager);
        }
         else if (modelClass.isAssignableFrom(UsersDetailViewModel.class))
         {
             return (T) new UsersDetailViewModel(manager,users);
         }
         else if (modelClass.isAssignableFrom(UsersEntity.class))
         {
             return (T) new UsersEntity(users);
         }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());

    }
}
