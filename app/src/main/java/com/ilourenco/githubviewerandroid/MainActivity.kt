package com.ilourenco.githubviewerandroid

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.text.TextUtils
import android.widget.Toast
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
