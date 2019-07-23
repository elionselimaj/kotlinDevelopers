package al.logic.android.kotlinapplication.view.base;

import androidx.lifecycle.ViewModel;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

import al.logic.android.kotlinapplication.utils.DataManager;
import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel<N> extends ViewModel {

    private CompositeDisposable compositeDisposable;

    private DataManager dataManager;

    private WeakReference<N> mNavigator;

    @Inject
    public BaseViewModel(DataManager dataManager) {

        this.compositeDisposable = new CompositeDisposable();
        this.dataManager = dataManager;
    }

    @Override
    protected void onCleared() {
        compositeDisposable.dispose();
        super.onCleared();
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public N getNavigator() {
        return mNavigator.get();
    }

    public void setNavigator(N navigator) {
        this.mNavigator = new WeakReference<>(navigator);
    }

}


