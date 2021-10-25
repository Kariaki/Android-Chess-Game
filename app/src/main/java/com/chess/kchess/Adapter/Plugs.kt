package com.chess.kchess.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chess.kchess.Adapter.viewholders.SquareViewHolder
import com.chess.kchess.R

object Plugs {

    val WHITE = 1
    val BLACK = 2
    val plug = object : GeneralAdapter.ViewHolderPlug {
        override fun setPlug(group: ViewGroup, viewType: Int): MainViewHolder {
            val contentInflater = LayoutInflater.from(group.context)
            return when (viewType) {

                WHITE -> SquareViewHolder(
                    contentInflater.inflate(
                        R.layout.light_square,
                        group,
                        false
                    )
                )

                BLACK -> SquareViewHolder(
                    contentInflater.inflate(
                        R.layout.dark_square,
                        group,
                        false
                    )
                )
                else -> null!!
            }
        }
    }
}