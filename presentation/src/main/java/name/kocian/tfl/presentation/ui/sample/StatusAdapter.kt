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
        val colorId = holder.vLineColor.context.resources.getIdentifier(
                "line_color_" + line.id.replace("-", "_"), "color",
                holder.vLineColor.context.packageName)

        holder.vLineColor.setBackgroundResource(colorId)
        holder.tvName.text = line.name

        when (line.severity) {
            in 0..SEVERITY_TODO -> {
                holder.tvDescription.text = line.description
                holder.tvSeverity.text = line.severityTitle
                holder.tvDescription.visibility = View.VISIBLE
                holder.tvStatusIcon.visibility = View.VISIBLE
                holder.tvSeverity.visibility = View.VISIBLE
            }
            SEVERITY_CLOSED -> {
                holder.tvDescription.visibility = View.GONE
                holder.tvSeverity.visibility = View.VISIBLE
                holder.tvSeverity.text = line.severityTitle
                holder.tvStatusIcon.visibility = View.INVISIBLE
            }
            else -> {
                holder.tvDescription.visibility = View.GONE
                holder.tvSeverity.visibility = View.INVISIBLE
                holder.tvStatusIcon.visibility = View.INVISIBLE
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
        val tvName: TextView = itemView.tv_line_status_name
        val vLineColor: View = itemView.v_line_status_color
        val tvStatusIcon: ImageView = itemView.iv_line_status_icon
        val tvDescription: TextView = itemView.tv_line_status_description
        val tvSeverity: TextView = itemView.tv_line_status_severity
    }

    companion object {
        private const val SEVERITY_TODO = 9
        private const val SEVERITY_GOOD_SERVICE = 10
        private const val SEVERITY_CLOSED = 20
    }
}
