package com.fauzify.customsearchsuggestion

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.R
import android.widget.Filter

//class ExampleItemAdapter (private val mContext: Context?,
//                          private val items: MutableList<ExampleItem>,
//                          private val listener: (ExampleItem) -> Unit)
//    : ArrayAdapter<ExampleItem>() {
//
//    private lateinit var itemListFull: MutableList<ExampleItem>

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val layoutInflater = LayoutInflater.from(parent.context)
//        val inflatedView: View = layoutInflater.inflate(R.layout.personal_meeting_item,
//                parent, false)
//        return ViewHolder(inflatedView)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bindItem(mContext, items[position], listener)
//    }
//    override fun getItemCount(): Int {
//        return items.size
//    }
//
//}
//
//class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//    fun bindItem(context: Context?, personalMeeting: ExampleItem, listener: (ExampleItem) -> Unit) {
//
//        itemView.setOnClickListener {
//            listener(personalMeeting)
//        }
//
//    }
//}

class ExampleItemAdapter(context: Context, itemList: MutableList<ExampleItem>) :
    ArrayAdapter<ExampleItem>(context, 0, itemList) {
    private var countryListFull: MutableList<ExampleItem> = itemList

    private val countryFilter = object : Filter() {
        protected override fun performFiltering(constraint: CharSequence?): FilterResults {
            val results = FilterResults()
            lateinit var suggestions: MutableList<ExampleItem>

            if (constraint == null || constraint.length == 0) {
                suggestions.addAll(countryListFull)
            } else {
                val filterPattern = constraint.toString().toLowerCase().trim { it <= ' ' }

                for (item  in countryListFull) {
                    if (item.itemName.toLowerCase().contains(filterPattern)) {
                        suggestions.add(item)
                    }
                }
            }

            results.values = suggestions
            results.count = suggestions.size

            return results
        }

        protected override fun publishResults(constraint: CharSequence, results: FilterResults) {
            clear()
            addAll(results.values as MutableList<ExampleItem>)
            notifyDataSetChanged()
        }

        override fun convertResultToString(resultValue: Any): CharSequence {
            return (resultValue as ExampleItem).itemName
        }
    }

    init {
        countryListFull = ArrayList(itemList)
    }

    override fun getFilter(): Filter {
        return countryFilter
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView = convertView
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                R.layout.item_row, parent, false
            )
        }

        val textViewName = convertView?.findViewById(R.id.tv_item_name)
        val imageViewFlag = convertView?.findViewById(R.id.image_item)

        val countryItem = getItem(position)

        if (countryItem != null) {
            textViewName.setText(countryItem.itemName)
            imageViewFlag.setImageResource(countryItem.itemImage)
        }

        return convertView
    }
}
