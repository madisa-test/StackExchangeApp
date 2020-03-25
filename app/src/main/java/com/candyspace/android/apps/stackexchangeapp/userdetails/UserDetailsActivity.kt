package com.candyspace.android.apps.stackexchangeapp.userdetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.candyspace.android.apps.stackexchangeapp.R
import com.candyspace.android.apps.stackexchangeapp.api.model.User
import com.candyspace.android.apps.stackexchangeapp.common.USER_INTENT_KEY
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_user_details.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class UserDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        tb_user_details_screen.setNavigationOnClickListener { finish() }

        var user: User = intent.getParcelableExtra(USER_INTENT_KEY)

        tv_user_name.text = String.format("%s %s", getString(R.string.user_name_label),  user.display_name)
        tv_reputation.text = String.format("%s %s", getString(R.string.user_reputation_label),  user.reputation.toString())
        tv_gold.text = String.format("%s %s", getString(R.string.gold_badges_label),  user.badge_counts?.gold.toString())
        tv_silver.text =  String.format("%s %s", getString(R.string.silver_badges_label),  user.badge_counts?.silver.toString())
        tv_bronze.text = String.format("%s %s", getString(R.string.gold_badges_label),  user.badge_counts?.bronze.toString())
        tv_location.text =  String.format("%s %s", getString(R.string.user_location_label),  user.location)
        tv_age.text = String.format("%s %s", getString(R.string.age_label),  toTimeStampFromDate(
            user.last_access_date?.toLong()?:0))
        tv_creation_date.text =String.format("%s %s", getString(R.string.creation_date_label), toTimeStampFromDate(
            user.creation_date?.toLong()?: 0))

        if (user.profile_image != null) {
            Picasso.get()
                .load(user.profile_image)
                .error(R.mipmap.ic_launcher_round)
                .resize(300, 180)
                .into(iv_user_art);
        } else {
            Picasso.get().load(R.mipmap.ic_launcher_round).into(iv_user_art)
        }

    }

    fun toTimeStampFromDate(timeStamp: Long): String {
        val dateFormat = SimpleDateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault())
        return dateFormat.format(Date(timeStamp * 1000))
    }
}
