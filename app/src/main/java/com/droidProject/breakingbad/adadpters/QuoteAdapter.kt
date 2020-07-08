package com.droidProject.breakingbad.adadpters

import android.annotation.SuppressLint
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.droidProject.breakingbad.R
import com.droidProject.breakingbad.models.Quotes
import kotlinx.android.synthetic.main.quote_sample.view.*


class QuoteAdapter(private val list: ArrayList<Quotes>, private val context:Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.quote_sample, parent, false)
        return QuoteViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is QuoteViewHolder ->
                holder.bind(list[position],position)
        }
    }

    class QuoteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        private val quoteNumber:TextView = itemView.qsNumber
        private val quote: TextView = itemView.qsQuote

        @SuppressLint("SetTextI18n")
        fun bind(quotes: Quotes, position: Int){
            quoteNumber.text = "${position+1}."
            quote.text = "\"${quotes.quote}\" - ${quotes.author}"


        }


    }
}