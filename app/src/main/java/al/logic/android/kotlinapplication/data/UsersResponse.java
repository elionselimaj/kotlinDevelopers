package al.logic.android.kotlinapplication.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import al.logic.android.kotlinapplication.viewmodel.UsersEntity;


public class UsersResponse {

    @SerializedName("items")
    private List<UsersEntity> kotlinUsers;

    public List<UsersEntity> getKotlinUsers(){
        return kotlinUsers;
    }
}

