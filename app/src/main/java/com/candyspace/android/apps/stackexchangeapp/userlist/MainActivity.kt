package com.candyspace.android.apps.stackexchangeapp.userlist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.candyspace.android.apps.stackexchangeapp.R
import com.candyspace.android.apps.stackexchangeapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), UserSelectedInterface {
    @Inject
    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit  var usersAdapter: UsersAdapter

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        activityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        activityMainBinding.progressVisibility = mainViewModel.getProgressObservable()

        setupRecyclerView()

        mainViewModel.getUsersObservable().observe(this, Observer {
            it?.let { users -> usersAdapter.setData(users) }

        })


        mainViewModel.getErrorObservable().observe(this,
            Observer { Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show() })

    }

    private fun setupRecyclerView() {
        rv_user_list.setAdapter(usersAdapter)
        rv_user_list.setLayoutManager(LinearLayoutManager(this))
    }

    fun onClickSearchButton(view: View) {
        val name = et_name.text.toString()
        if (name.isNotEmpty()) {
            mainViewModel.getData(name)
        } else {
            Snackbar.make(view, "Please enter a name", Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onResultSelected(position: Int) {
        TODO("Not yet implemented")
    }
}
