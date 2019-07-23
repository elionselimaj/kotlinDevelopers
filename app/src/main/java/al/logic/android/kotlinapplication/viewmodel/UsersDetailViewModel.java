package al.logic.android.kotlinapplication.viewmodel;

import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import al.logic.android.kotlinapplication.utils.DataManager;
import al.logic.android.kotlinapplication.view.activity.UserDetailActivity;
import al.logic.android.kotlinapplication.view.base.BaseViewModel;

import javax.inject.Inject;

public class UsersDetailViewModel extends BaseViewModel {

    private UsersEntity usersEntity;

    @Inject
    public UsersDetailViewModel(DataManager dataManager,UsersEntity users) {
        super(dataManager);
        this.usersEntity = users;
    }

    public String getUserName() {
        return usersEntity.getFullName();
    }

    public String getEmail() {
        return usersEntity.getEmail() != null ? usersEntity.getEmail():" not existing email address ";
    }

    public String getLocation() {
        return usersEntity.getLocation() != null ? usersEntity.getLocation():" location is not available ";
    }

    public String getFollowers() {
        return usersEntity.getFollowers();
    }

    public String getPictureProfile() {
        return usersEntity.getAvatar();
    }


    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext()).load(imageUrl).into(view);
    }

    public void onItemClick(View view) {
        view.getContext().startActivity(UserDetailActivity.launchEmail());
    }


}
