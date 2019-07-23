package al.logic.android.kotlinapplication.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.moovel.android.coding.challenge.R;
import com.moovel.android.coding.challenge.databinding.ItemLoadingBinding;
import com.moovel.android.coding.challenge.databinding.UsersListBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import al.logic.android.kotlinapplication.viewmodel.UsersEntity;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UsersViewHolder> {

    public List<UsersEntity> kotlinUsers;
    private List<UsersEntity> response = new ArrayList<>();

    private static final int USER_LIST = 0;
    private static final int USER_LOAD = 1;

    public UserListAdapter(ArrayList<UsersEntity> usersEntities) {
        this.kotlinUsers = usersEntities;
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ViewDataBinding binding;
        switch (viewType) {
            case USER_LIST:
                binding =
                        DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.users_list,
                                parent, false);
                return new UsersViewHolder((UsersListBinding) binding);
            case USER_LOAD:
                binding =
                        DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_loading,
                                parent, false);
                return new UsersViewHolder((ItemLoadingBinding) binding);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        if (kotlinUsers.get(position) != null && getItemViewType(position) == USER_LIST) {
            holder.mUserListBinding.setUsers(kotlinUsers.get(position));
            holder.bindUsers(kotlinUsers.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return kotlinUsers == null ? 0 : kotlinUsers.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (kotlinUsers.get(position) != null)
            return USER_LIST;
        return USER_LOAD;
    }

    static class UsersViewHolder extends RecyclerView.ViewHolder {

        UsersListBinding mUserListBinding;
        ItemLoadingBinding itemLoadingBinding;

        UsersViewHolder(UsersListBinding usersListBinding) {
            super(usersListBinding.getRoot());
            this.mUserListBinding = usersListBinding;
        }

        UsersViewHolder(ItemLoadingBinding itemLoadingBinding) {
            super(itemLoadingBinding.getRoot());
            this.itemLoadingBinding = itemLoadingBinding;
        }


        void bindUsers(UsersEntity users) {

            if (mUserListBinding.getUsers() == null) {

                mUserListBinding.setUsers(new UsersEntity(users));
            } else {
                mUserListBinding.getUsers().setUsers(users);
            }
        }
    }

    public void addItems(ArrayList<UsersEntity> usersEntities, boolean refresh, int usersSize) {

        this.response.addAll(usersEntities);

        if ((response.size() % usersSize) == 0) {
            loadData(new ArrayList<>(response), refresh);
            this.response.clear();
        }
    }

    private void loadData(List<UsersEntity> userResponses, boolean refresh) {

        if (refresh)
            this.kotlinUsers.clear();
        //  order by username ascending (top 10 for each request)
        Collections.sort(userResponses,  (s1, s2) -> s1.getUsername().compareToIgnoreCase(s2.getUsername()));
        this.kotlinUsers.addAll(userResponses);
        if (this.kotlinUsers.size() > 10)
            if (this.kotlinUsers.get(this.kotlinUsers.size() - userResponses.size() - 1) == null)
                this.kotlinUsers.remove(this.kotlinUsers.size() - userResponses.size() - 1);
        if (userResponses.size() >= 10)
            this.kotlinUsers.add(null);
        notifyItemInserted(this.kotlinUsers.size() - 1);
        notifyDataSetChanged();
    }

}
