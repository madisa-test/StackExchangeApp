package com.candyspace.android.apps.stackexchangeapp.userlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.candyspace.android.apps.stackexchangeapp.R
import com.candyspace.android.apps.stackexchangeapp.api.model.User
import java.util.*
import kotlinx.android.synthetic.main.item_user.view.*


class UsersAdapter(listener: UserSelectedInterface) :
    RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {
    private var users: List<User>
    init {
        users = ArrayList()
        UsersAdapter.listener = listener

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UsersViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

    fun setData(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }

   class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: User) {
            itemView.tv_user_name.setText(user.display_name)
            itemView.tv_reputation.setText(user.reputation.toString())
            if (listener != null) {

            }

        }

    }

    companion object {
        private var listener: UserSelectedInterface? = null
    }
}