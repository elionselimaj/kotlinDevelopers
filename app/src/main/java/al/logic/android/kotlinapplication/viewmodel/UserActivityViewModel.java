package al.logic.android.kotlinapplication.viewmodel;


import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableInt;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import al.logic.android.kotlinapplication.utils.DataManager;
import al.logic.android.kotlinapplication.view.base.BaseViewModel;
import al.logic.android.kotlinapplication.view.navigators.MainNavigator;

import static al.logic.android.kotlinapplication.utils.AppConstants.CLIENT_ID;
import static al.logic.android.kotlinapplication.utils.AppConstants.CLIENT_SECRET;

public class UserActivityViewModel extends BaseViewModel<MainNavigator> {


    private ObservableInt usersProgress;
    private ObservableInt usersRecycler;
    private ObservableInt errorMessage;
    private ObservableInt retryButton;

    private boolean loading;
    private boolean lastPage;
    private int PAGE = 0;


    @Inject
    public UserActivityViewModel(DataManager dataManager) {

        super(dataManager);

        usersProgress = new ObservableInt(View.GONE);
        usersRecycler = new ObservableInt(View.GONE);
        errorMessage = new ObservableInt(View.GONE);
        retryButton = new ObservableInt(View.GONE);
    }

    public void initViews() {

        usersRecycler.set(View.GONE);
        usersProgress.set(View.VISIBLE);
        retryButton.set(View.GONE);
        errorMessage.set(View.GONE);
    }

    private void fetchKotlinUsers(String language, int page, int size,boolean refresh) {


        getCompositeDisposable().add(getDataManager().fetchKotlinUsers(language, page, size)
                .subscribeOn(getDataManager().io())
                .observeOn(getDataManager().ui())
                .subscribe(usersResponse -> {

                            successView();
                            getNavigator().fetchUsers(usersResponse.getKotlinUsers(),refresh);
                            if (usersResponse.getKotlinUsers().size() < 10) {
                                setLastPage(true);
                            } else {
                                setLastPage(false);
                            }
                            if (usersResponse.getKotlinUsers().size() == 0)
                                setLoading();

                        }, throwable -> {

                            errorView();
                            throwable.printStackTrace();
                        }
                ));

    }

    public void getDetails(String username,boolean refresh,int usersSize) {

        getCompositeDisposable().add(getDataManager().getUserDetails(username,CLIENT_ID,CLIENT_SECRET)
                .subscribeOn(getDataManager().io())
                .observeOn(getDataManager().ui())
                .subscribe(usersResponse -> {
                            List<UsersEntity> json = Collections.singletonList(new Gson().fromJson(usersResponse, UsersEntity.class));
                            successView();
                            getNavigator().fetchDetails(json,refresh,usersSize);

                        }, throwable -> {

                            errorView();
                            throwable.printStackTrace();
                        }
                ));
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public ObservableInt getUsersProgress() {
        return usersProgress;
    }

    public ObservableInt getUsersRecycler() {
        return usersRecycler;
    }

    public ObservableInt getErrorMessage() {
        return errorMessage;
    }

    public ObservableInt getRetryButton() {
        return retryButton;
    }

    public void onRetryClick(View v) {
        initViews();
        fetchKotlinUsers("language:kotlin", 1, 10,true);
    }


    private void successView() {

        usersProgress.set(View.GONE);
        usersRecycler.set(View.VISIBLE);
        retryButton.set(View.GONE);
        errorMessage.set(View.GONE);
    }

    private void errorView() {

        usersProgress.set(View.GONE);
        usersRecycler.set(View.GONE);
        retryButton.set(View.VISIBLE);
        errorMessage.set(View.VISIBLE);
    }

    public void getKotlinUsers(boolean refresh) {

        if (refresh) {
            setLastPage(false);
            PAGE = 1;
        } else
            PAGE = PAGE + 1;
        fetchKotlinUsers("language:kotlin", PAGE, 10,refresh);

    }

    public boolean isLoading() {
        return loading;
    }

    private void setLoading() {
        this.loading = false;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    private void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }
}
