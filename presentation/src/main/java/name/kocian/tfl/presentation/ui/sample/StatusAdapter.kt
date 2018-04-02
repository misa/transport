package name.kocian.tfl.presentation.ui.sample

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.status_item.view.*
import name.kocian.tfl.R
import name.kocian.tfl.presentation.model.StatusModel
import java.util.*

internal class LineStatusAdapter(
        private var items: List<StatusModel> = ArrayList()
) : RecyclerView.Adapter<LineStatusAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.status_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val line = items[holder.adapterPosition]
        val colorId = holder.lineColorView.context.resources.getIdentifier(
                "line_color_" + line.id.replace("-", "_"), "color",
                holder.lineColorView.context.packageName)

        holder.lineColorView.setBackgroundResource(colorId)
        holder.nameTextView.text = line.name

        when (line.severity) {
            in 0..SEVERITY_TODO -> {
                holder.descriptionTextView.text = line.description
                holder.severityTextView.text = line.severityTitle
                holder.descriptionTextView.visibility = View.VISIBLE
                holder.statusIconImageView.visibility = View.VISIBLE
                holder.severityTextView.visibility = View.VISIBLE
            }
            SEVERITY_CLOSED -> {
                holder.descriptionTextView.visibility = View.GONE
                holder.severityTextView.visibility = View.VISIBLE
                holder.severityTextView.text = line.severityTitle
                holder.statusIconImageView.visibility = View.INVISIBLE
            }
            else -> {
                holder.descriptionTextView.visibility = View.GONE
                holder.severityTextView.visibility = View.INVISIBLE
                holder.statusIconImageView.visibility = View.INVISIBLE
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setStatuses(lines: List<StatusModel>) {
        items = lines
        notifyDataSetChanged()
    }

    internal inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.nameTextView
        val lineColorView: View = itemView.lineColorView
        val statusIconImageView: ImageView = itemView.statusIconImageView
        val descriptionTextView: TextView = itemView.descriptionTextView
        val severityTextView: TextView = itemView.statusSeverityTextView
    }

    companion object {
        private const val SEVERITY_TODO = 9
        private const val SEVERITY_GOOD_SERVICE = 10
        private const val SEVERITY_CLOSED = 20
    }
}
