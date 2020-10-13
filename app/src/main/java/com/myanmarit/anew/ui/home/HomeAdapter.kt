package com.myanmarit.anew.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myanmarit.anew.R
import com.myanmarit.anew.model.ArticlesItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_news.view.*

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    var articleList : List<ArticlesItem> = ArrayList()
    class HomeViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView){
        fun bind(articlesItem: ArticlesItem){
            itemView.author.text = articlesItem.author
            itemView.description.text = articlesItem.description
            Picasso.get()
                .load(articlesItem.urlToImage)
                .into(itemView.newsImage)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news,parent,false)
        return  HomeViewHolder(view)
    }

    override fun getItemCount(): Int = articleList.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(articleList [position])
    }
    fun updateArticleList(articlesItem: List<ArticlesItem>) {
        this.articleList = articlesItem
        notifyDataSetChanged()
    }

}