import Color.SquareColor

data class BoardSquare
    (
    var squareColor: SquareColor,
    var chessPiece: ChessPiece?=null,
    var squareName:String=""
)