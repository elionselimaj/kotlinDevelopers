package al.logic.android.kotlinapplication.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;

import com.moovel.android.coding.challenge.BR;
import com.moovel.android.coding.challenge.R;
import com.moovel.android.coding.challenge.databinding.UsersDetailActivityBinding;

import javax.inject.Inject;

import al.logic.android.kotlinapplication.view.base.BaseActivity;
import al.logic.android.kotlinapplication.viewmodel.UsersDetailViewModel;
import al.logic.android.kotlinapplication.viewmodel.UsersEntity;

import static al.logic.android.kotlinapplication.utils.AppConstants.EXTRA_USERS;
public class UserDetailActivity extends BaseActivity<UsersDetailViewModel, UsersDetailActivityBinding> {

    @Inject
    Context mContext;

    @Override
    protected int getLayoutRes() {
        return R.layout.users_detail_activity;
    }

    @Override
    protected Class<UsersDetailViewModel> getViewModel() {
        return UsersDetailViewModel.class;
    }

    @Override
    public int getBindingVariable() {
        return BR.usersDetailViewModel;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getExtrasFromIntent();
    }

    public static Intent launchDetails(Context mContext, UsersEntity users) {

        Intent intent = new Intent(mContext, UserDetailActivity.class);
        intent.putExtra(EXTRA_USERS, users);
        return intent;
    }

    public static Intent launchEmail(){

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_APP_EMAIL);
        return intent;
    }

    private void getExtrasFromIntent() {

        UsersEntity usersEntity = (UsersEntity) getIntent().getSerializableExtra(EXTRA_USERS);
        UsersDetailViewModel usersDetailViewModel = new UsersDetailViewModel( null, usersEntity);
        dataBinding.setUsersDetailViewModel(usersDetailViewModel);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
