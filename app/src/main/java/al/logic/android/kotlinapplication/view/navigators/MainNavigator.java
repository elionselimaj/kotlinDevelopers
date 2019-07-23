package al.logic.android.kotlinapplication.view.navigators;


import java.util.List;

import al.logic.android.kotlinapplication.viewmodel.UsersEntity;

public interface MainNavigator {

    void fetchUsers(List<UsersEntity> usersEntities,boolean refresh);

    void fetchDetails(List<UsersEntity> usersEntities,boolean refresh,int size);

}
