package com.chess.kchess

object SquareNames {


    fun getSquareNames(position: Int): String {

        val a = charArrayOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h')
        val rank = 8-(position / 8)
        val file = position % 8
        return "${a[file]} ${rank}"

    }

}