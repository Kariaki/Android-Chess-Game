package com.chess.kchess.Adapter.viewholders

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.chess.kchess.Adapter.MainViewHolder
import com.chess.kchess.Adapter.SuperClickListener
import com.chess.kchess.Adapter.SuperEntity
import com.chess.kchess.BoardSquare
import com.chess.kchess.PieceColor
import com.chess.kchess.PieceType
import com.chess.kchess.R

class SquareViewHolder(var view: View) : MainViewHolder(view) {
    lateinit var fileView: TextView
    lateinit var rankView: TextView
    lateinit var piece: ImageView
    override fun bindPostType(
        types: SuperEntity,
        context: Context,
        clickListener: SuperClickListener
    ) {

        fileView = view.findViewById(R.id.fileName)
        rankView = view.findViewById(R.id.rankName)
        piece = view.findViewById(R.id.piece)

        types as BoardSquare

        var chessPiece = types.chessPiece
        if (chessPiece != null) {
            if (chessPiece.pieceColor == PieceColor.BLACK) {
                if (chessPiece.pieceType == PieceType.PAWN)
                    piece.setImageResource(R.drawable.pawn_black)
            } else {

                if (chessPiece.pieceType == PieceType.PAWN)
                    piece.setImageResource(R.drawable.pawn_white)
            }
        }


        view.setOnClickListener {

            clickListener.onClickItem(layoutPosition)
        }
        clickListener.callOnView(layoutPosition, rankView, fileView)


    }
}