package com.chess.kchess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chess.kchess.Adapter.*
import com.chess.kchess.Adapter.viewholders.SquareViewHolder

class MainActivity : AppCompatActivity() {

    val generalAdapter = GeneralAdapter()
    val boardSquare: MutableList<SuperEntity> = mutableListOf()
    lateinit var board: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        board = findViewById(R.id.board)

        populateSquares()
        pawnsArrangement()

        generalAdapter.apply {
            items = boardSquare
            viewHolderPlug = Plugs.plug
            superClickListener = listener
        }
        board.apply {
            hasFixedSize()
            layoutManager = GridLayoutManager(this@MainActivity, 8)
            adapter = generalAdapter
        }


    }

    val listener = object : SuperClickListener {
        override fun onClickItem(position: Int) {

            val result = SquareNames.getSquareNames(position)
            Toast.makeText(this@MainActivity, result, Toast.LENGTH_LONG).show()

        }

        override fun callOnView(position: Int, rankView: TextView, fileView: TextView) {
            val nameSplit = SquareNames.getSquareNames(position).split(" ")
            val file = nameSplit[0]
            val rank = nameSplit[1]
            if (rank == "1")
                fileView.text = file

            if (file == "h")
                rankView.text = rank

        }
    }

    fun populateSquares() {
        val range = 0..7

        for (i in range) {
            for (j in range) {
                if (isOdd(i))
                    oddEven(j)
                else
                    evenOdd(j)

            }
        }
    }


    fun oddEven(value: Int) {
        if (isOdd(value))
            boardSquare.add(BoardSquare(Plugs.WHITE))
        else
            boardSquare.add(BoardSquare(Plugs.BLACK))

    }

    fun evenOdd(value: Int) {
        if (isOdd(value))
            boardSquare.add(SuperEntity(Plugs.BLACK))
        else
            boardSquare.add(SuperEntity(Plugs.WHITE))

    }

    private fun isOdd(value: Int): Boolean = value % 2 != 0

    private fun pawnsArrangement() {
        val range = 0..63
        for (square in range) {
            val rank = 8 - (square / 8)
            val file = square % 8
            var newBoardSquare = BoardSquare(boardSquare[square].type)
            if (rank == 2)
                newBoardSquare.chessPiece = ChessPiece(PieceType.PAWN, PieceColor.WHITE)
            else if (rank == 7)
                newBoardSquare.chessPiece = ChessPiece(PieceType.PAWN, PieceColor.BLACK)

            boardSquare[square] = newBoardSquare
            generalAdapter.notifyDataSetChanged()
        }

    }

}