package com.fuh.something.screens.master;

import android.support.annotation.LayoutRes
import android.view.View
import com.fuh.something.models.Model
import com.fuh.something.utils.ui.SimpleRecyclerAdapter
import kotlinx.android.synthetic.main.master_item.view.*
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter

class MasterAdapter(
        @LayoutRes private val layoutId: Int,
        initialData: List<Model> = listOf()
) : SimpleRecyclerAdapter<SimpleRecyclerAdapter.ViewHolder<Model>, Model>(
        layoutId,
        initialData
) {

    override fun createHolder(itemView: View): SimpleRecyclerAdapter.ViewHolder<Model> =
            ViewHolder(itemView)

    class ViewHolder(itemView: View): SimpleRecyclerAdapter.ViewHolder<Model>(itemView) {

        override fun bind(data: Model) {
            with(itemView) {
                tvMasterItemText.text = data.text

                val simpleTimeFormat = DateTimeFormatter.ofPattern("HH:mm")
                val zoneId = ZoneId.of("Europe/Kiev")
                val instant = Instant.ofEpochMilli(data.timestamp)

                tvMasterItemTimestamp.text = LocalDateTime.ofInstant(instant, zoneId)
                        .toLocalTime()
                        .format(simpleTimeFormat)
            }
        }
    }
}