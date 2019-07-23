package al.logic.android.kotlinapplication.viewmodel;

import android.view.View;

import androidx.lifecycle.ViewModel;

import com.google.gson.annotations.SerializedName;
import al.logic.android.kotlinapplication.utils.Utils;
import al.logic.android.kotlinapplication.view.activity.UserDetailActivity;

import java.io.Serializable;
import java.text.ParseException;

import javax.inject.Inject;


public class UsersEntity extends ViewModel implements Serializable {

    private UsersEntity users;

    @SerializedName("id")
    private int id;

    @SerializedName("login")
    private String username;

    @SerializedName("name")
    private String fullName;

    @SerializedName("avatar_url")
    private String avatar;

    @SerializedName("created_at")
    private String registrationDate;

    @SerializedName("email")
    private String email;

    @SerializedName("followers")
    private String followers;

    @SerializedName("location")
    private String location;

    @Inject
    public UsersEntity(UsersEntity usersEntity) {

        this.users = usersEntity;
    }

    public UsersEntity() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getRegistrationDate()  {

        try {
            return Utils.calendarToString(Utils.stringToCalendar(registrationDate),Utils.registrationFormat);
        } catch (ParseException e) {
            e.printStackTrace();
            return registrationDate;
        }
    }

    public String getEmail() {
        return email;
    }

    public String getFollowers() {
        return followers;
    }

    public String getLocation() {
        return location;
    }


    public void onItemClick(View view) {
        view.getContext().startActivity(UserDetailActivity.launchDetails(view.getContext(), users));
    }

    public void setUsers(UsersEntity users) {
        this.users = users;
    }
}