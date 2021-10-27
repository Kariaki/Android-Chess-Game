import Color.PieceColor
import Color.SquareColor

object ChessLogic {


    fun getSquareNameFromPosition(position: Int): String {
        /* return the name of the square from a given
        square number ranging from 0 to 63
         */
        val a = charArrayOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h')
        val rank = 8 - (position / 8)
        val file = position % 8
        return "${a[file]}${rank}"
    }

    fun getSquarePositionFromSquareName(squareName:String):IntArray{

        val fileArray = charArrayOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h')
        val split=squareName.split("")
        val file=split[1].toCharArray()[0]
        val rank= Integer.parseInt(split[2])-1
        val fileNumber=fileArray.indexOf(file)
        val position= (rank*8)+fileNumber
        return intArrayOf(rank,fileNumber,position)
    }

    fun oddEven(i: Int, j: Int, count: Int) {

        val squareName = getSquareNameFromPosition(count)
        val chessPiece = placePieceOnTheBoard(i, j)

        val boardSquare = if (isOdd(j)) {
            BoardSquare(squareColor = SquareColor.WHITE, squareName = squareName, chessPiece = chessPiece)
        } else {
            BoardSquare(squareColor = SquareColor.BLACK, squareName = squareName, chessPiece = chessPiece)
        }
        board.add(boardSquare)
    }

    fun evenOdd(i: Int, j: Int, count: Int) {

        val squareName = getSquareNameFromPosition(count)
        val chessPiece = placePieceOnTheBoard(i, j)

        val boardSquare = if (isOdd(j)) {
            BoardSquare(squareColor = SquareColor.BLACK, squareName = squareName, chessPiece = chessPiece)

        } else {
            BoardSquare(squareColor = SquareColor.WHITE, squareName = squareName, chessPiece = chessPiece)
        }

        board.add(boardSquare)
    }

    fun isOdd(value: Int): Boolean = value % 2 != 0

    fun placePieceOnTheBoard(i: Int, j: Int): ChessPiece? {
        val chessPiece = when (i) {
            1 -> {
                ChessPiece(Position(i, j), PieceType.PAWN, PieceColor.BLACK)
            }
            6 -> {
                ChessPiece(Position(i, j), PieceType.PAWN, PieceColor.WHITE)
            }
            else -> {
                pieceSet(i,j)
            }
        }

        return chessPiece
    }


    fun pieceSet(i: Int, j: Int): ChessPiece? {
        /*
        this method calculates x, x is a piece value other than king and queen. Value is
        respective for the pieces at both sides of the rank (Ra1 & Rh1)
         */
        val newJ = j + 1
        val x = if (newJ < 4) {
            4 - newJ
        } else if (newJ > 5) {
            newJ - 5
        } else {
            -1
        }

        return when (i) {
            0 -> {

                pieceArrangement(x, i, j, PieceColor.WHITE)
            }
            7 -> {
                pieceArrangement(x, i, j, PieceColor.BLACK)
            }
            else -> {
                null
            }
        }

    }

    fun pieceArrangement(x: Int, i: Int, j: Int, pieceColor: PieceColor): ChessPiece? {
        // arrange the pieces at the bank rank
        /*
        accept x calculated above and condition x to get the piece that will be at a given position
         */
        val chessPiece = when (x) {
            1 ->

                ChessPiece(Position(i, j), PieceType.BISHOP, pieceColor)
            2 ->

                ChessPiece(Position(i, j), PieceType.KNIGHT, pieceColor)
            3 ->
                ChessPiece(Position(i, j), PieceType.ROOK, pieceColor)
            else ->
                placeKingQueen(i, j, pieceColor)
        }

        return chessPiece
    }

    fun placeKingQueen(i: Int, j: Int, pieceColor: PieceColor): ChessPiece? {
        // self explanatory
        return when (j) {
            3 -> ChessPiece(Position(i, j), PieceType.QUEEN, pieceColor)
            4 -> ChessPiece(Position(i, j), PieceType.KING, pieceColor)
            else -> null
        }
    }


}