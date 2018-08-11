package com.mtw.juancarlos.booksqueryapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.mtw.diego.booksqueryapp.R
import com.mtw.diego.booksqueryapp.api.BooksModel
import com.mtw.diego.booksqueryapp.api.BooksModel.BookItem
import kotlinx.android.synthetic.main.book_card_item.view.*


class BooksAdapter(booksList: List<BookItem>, listener: OnItemClickListener) : RecyclerView.Adapter<BooksAdapter.BooksViewHolder>() {

    private var listBooks: List<BookItem> = booksList

    private var listenerBook: OnItemClickListener = listener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        return BooksViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.book_card_item, parent, false))
    }


    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        var current: BookItem = listBooks[position]

        holder.bind(current, listenerBook)
    }

    override fun getItemCount() = listBooks.size

    interface OnItemClickListener {
        fun onItemClick(book: BookItem)
    }

    fun setBooks(books: List<BookItem>) {
        listBooks = books
        notifyDataSetChanged()
    }

    class BooksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(book: BooksModel.BookItem, listener: OnItemClickListener) {
            itemView.tvTitle.text = book.volumeInfo.title
            if (book.volumeInfo?.authors != null) {
                itemView.tvAuthor.text = book.volumeInfo.authors[0]
            }
            if (book.volumeInfo?.imageLinks?.smallThumbnail != null) {
                Glide.with(itemView.context).load(book.volumeInfo.imageLinks.smallThumbnail).into(itemView.imgCover)
            }
            itemView.setOnClickListener {
                listener.onItemClick(book)
            }
        }

    }
}