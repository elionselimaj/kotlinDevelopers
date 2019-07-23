package al.logic.android.kotlinapplication.view.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moovel.android.coding.challenge.BR;
import com.moovel.android.coding.challenge.R;
import com.moovel.android.coding.challenge.databinding.UsersActivityBinding;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import al.logic.android.kotlinapplication.view.adapter.UserListAdapter;
import al.logic.android.kotlinapplication.view.base.BaseActivity;
import al.logic.android.kotlinapplication.view.navigators.MainNavigator;
import al.logic.android.kotlinapplication.viewmodel.UserActivityViewModel;
import al.logic.android.kotlinapplication.viewmodel.UsersEntity;

import static al.logic.android.kotlinapplication.utils.AppConstants.EXTRA_USERS;

public class MainActivity extends BaseActivity<UserActivityViewModel, UsersActivityBinding> implements MainNavigator {

    @Inject
    UserListAdapter mAdapter;

    @Inject
    LinearLayoutManager mLayManager;
    private RecyclerView recyclerView;



    private RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {


            int visibleItemCount = mLayManager.getChildCount();
            int totalItemCount = mLayManager.getItemCount();
            int firstVisibleItemPosition = mLayManager.findFirstVisibleItemPosition();
            int lastCompleteVisibleItemPosition =  mLayManager.findLastCompletelyVisibleItemPosition();
            if (!mViewModel.isLoading() && !mViewModel.isLastPage()) {
                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0 && lastCompleteVisibleItemPosition == totalItemCount-1)
                    mViewModel.getKotlinUsers(false);
            }
        }
    };

    @Override
    protected int getLayoutRes() {
        return R.layout.users_activity;
    }

    @Override
    protected Class<UserActivityViewModel> getViewModel() {
        return UserActivityViewModel.class;
    }

    @Override
    public int getBindingVariable() {
        return BR.usersActivity;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel.initViews();
        recyclerView = dataBinding.listPeople;
        setupAdapter();
        //setOnScrollListener has been deprecated and changed to addOnScrollListener
        //recyclerView.setOnScrollListener(scrollListener)
        recyclerView.addOnScrollListener(scrollListener);
        mViewModel.setNavigator(this);

     if (getExtrasFromIntent()==null)
            mViewModel.getKotlinUsers(true);
    }

    private void setupAdapter() {
        mLayManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(mLayManager);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter = null;
    }


    private void getDetailedInfo(List<UsersEntity> usersEntities,boolean refresh) {

        // Request details for each user to display registration date
        for (int i = 0; i < usersEntities.size(); i++) {

            mViewModel.getDetails(usersEntities.get(i).getUsername(),refresh,usersEntities.size());
        }
    }

    @Override
    public void fetchUsers(List<UsersEntity> users,boolean refresh) {

        getDetailedInfo(users,refresh);
    }

    @Override
    public void fetchDetails(List<UsersEntity> users,boolean refresh, int size) {

        mAdapter.addItems(new ArrayList<>(users),refresh,size);
    }

    private UsersEntity getExtrasFromIntent(){

        return (UsersEntity) getIntent().getSerializableExtra(EXTRA_USERS);
    }
}
