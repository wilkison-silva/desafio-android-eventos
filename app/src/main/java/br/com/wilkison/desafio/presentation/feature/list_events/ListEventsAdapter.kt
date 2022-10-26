package br.com.wilkison.desafio.presentation.feature.list_events

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.wilkison.desafio.databinding.ItemShortEventBinding
import br.com.wilkison.desafio.presentation.model.ShortEventView

class ListEventsAdapter(
    private val context: Context,
    shortEventViewList: List<ShortEventView> = listOf(),
    var onClickItem: (eventId: String) -> Unit = {}
) : RecyclerView.Adapter<ListEventsAdapter.ViewHolder>() {

    private var shortEventViewList = shortEventViewList.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemShortEventBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shortEventView: ShortEventView = shortEventViewList[position]
        holder.bind(shortEventView)
    }

    override fun getItemCount(): Int {
        return shortEventViewList.size
    }

    fun updateList(list: List<ShortEventView>) {
        shortEventViewList.clear()
        shortEventViewList.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding: ItemShortEventBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(shortEventView: ShortEventView) {
            binding.tvItemShortEventTitle.text = shortEventView.title
            binding.tvItemShortEventDate.text = shortEventView.date
            binding.tvItemShortEventPrice.text = shortEventView.price

            binding.itemviewShortEvent.setOnClickListener {
                onClickItem(shortEventView.id)
            }
        }
    }
}