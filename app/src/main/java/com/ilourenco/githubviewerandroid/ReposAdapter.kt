package com.ilourenco.githubviewerandroid

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

/**
 * Created by itamarlourenco on 10/06/2018.
 */
open class ReposAdapter(context: Context, var repos: List<Repos>): BaseAdapter(){

    private val inflator: LayoutInflater

    init {
        this.inflator = LayoutInflater.from(context)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val view: View?
        val listRowHolder: ListRowHolder

        if (convertView == null) {
            view = this.inflator.inflate(R.layout.list_row, parent, false)
            listRowHolder = ListRowHolder(view)
            view.tag = listRowHolder
        } else {
            view = convertView
            listRowHolder = view.tag as ListRowHolder
        }

        val repo = getItem(position)


        listRowHolder.name?.text = repo.name
        listRowHolder.language?.text = repo.language
        return view
    }

    override fun getItem(index: Int): Repos {
        return repos[index]
    }

    override fun getItemId(index: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return repos.size
    }

    private class ListRowHolder(row: View?) {
        var name: TextView? = null
        var language: TextView? = null

        init {
            this.name = row?.findViewById(R.id.name)
            this.language = row?.findViewById(R.id.language)
        }
    }
}