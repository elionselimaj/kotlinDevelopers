package al.logic.android.kotlinapplication.view.adapter;

import androidx.recyclerview.widget.LinearLayoutManager;

import al.logic.android.kotlinapplication.view.activity.MainActivity;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class UserAdapterProvider {

    @Provides
    UserListAdapter provideUserListAdapter() {
        return new UserListAdapter(new ArrayList<>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(MainActivity activity){
        return new LinearLayoutManager(activity);
    }

}
