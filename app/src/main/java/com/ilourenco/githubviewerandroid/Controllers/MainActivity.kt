package com.ilourenco.githubviewerandroid.Controllers

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.ilourenco.githubviewerandroid.App.BaseAppCompatActivity
import com.ilourenco.githubviewerandroid.Views.Adapter.ListRepos
import com.ilourenco.githubviewerandroid.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchUsername.setOnClickListener {
            if(TextUtils.isEmpty(editTextUsername.text)){
                Toast.makeText(this, R.string.error_enter_username, Toast.LENGTH_LONG).show()
            }else{
                startActivity(ListRepos.newIntent(this, editTextUsername.text.toString()))
            }
        }
    }

}
