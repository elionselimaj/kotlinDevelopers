package al.logic.android.kotlinapplication.view.base;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProviders;

import javax.inject.Inject;

import al.logic.android.kotlinapplication.viewmodel.AppViewModelFactory;
import dagger.android.AndroidInjection;

public abstract class BaseActivity<V extends BaseViewModel,D extends ViewDataBinding> extends AppCompatActivity {


    @Inject
    AppViewModelFactory viewModelFactory;

    protected D dataBinding;
    protected V mViewModel;

    @LayoutRes
    protected abstract int getLayoutRes();
    protected abstract Class<V> getViewModel();
    public abstract int getBindingVariable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        performDataBinding();
    }

    private void performDataBinding(){

        dataBinding = DataBindingUtil.setContentView(this,getLayoutRes());
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModel());
        dataBinding.setVariable(getBindingVariable(),mViewModel);
        dataBinding.executePendingBindings();
    }

}
