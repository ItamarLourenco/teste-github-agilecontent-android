package com.ilourenco.githubviewerandroid

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_list_respos.*
import kotlinx.android.synthetic.main.toolbar_user.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListRepos : BaseAppCompatActivity() {

    var repos: List<Repos> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_respos)

        setSupportActionBar(toolbar_user)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        getRepos()
    }

    private fun getRepos() {
        showList(false)
        val username:String = intent.getStringExtra(paramsUsername)
        RetrofitInitializer().reposService().list(username).enqueue(object : Callback<List<Repos>?> {
            override fun onResponse(call: Call<List<Repos>?>?, response: Response<List<Repos>?>?) {
                if(response?.code() != RetrofitInitializer.HTTP_CODE_SUCCESS){
                    alert("Usuário não encontrado.", "Favor inserir outro nome.")
                    return
                }

                response?.body()?.let {
                    repos = it
                    listView.adapter = ReposAdapter(baseContext, repos)

                    if(repos.isNotEmpty()){
                        val repo = repos[0]
                        Picasso.get().load(repo.owner.avatar_url).into(toolbar_user.findViewById<CircleImageView>(R.id.usernameImage))
                        toolbar_user.findViewById<TextView>(R.id.usernameText).text = repo.owner.login
                    }
                    showList()
                }
            }

            override fun onFailure(call: Call<List<Repos>?>?, t: Throwable?) {
                alert("Ocorreu um erro de rede.", "Verifique sua conexão com a Internet e tente novamente mais tarde.")
            }
        })
    }

    fun showList(show:Boolean = true){
        listView.visibility = if(show)  View.VISIBLE else View.GONE
        progressBar.visibility = if(!show)  View.VISIBLE else View.GONE
    }

    fun alert(title:String, message:String, buttonText:String = "OK"){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(buttonText){ dialog, which ->
            finish()
        }
        builder.create().show()
    }

    companion object {
        val paramsUsername:String = "username"
        fun newIntent(context: Context, username:String): Intent {
            val intent =  Intent(context, ListRepos::class.java)
            intent.putExtra(paramsUsername, username)
            return intent
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}

