package boardgame;

public class Board {
	private int rows;
	private int columns;
	private Piece[][] pieces;

	public Board(int rows, int columns) {
		if (rows < 1 || columns < 1) {
			throw new BoardException("Erro ao criar o tabuleiro: é necessarion pelo menos 1 linha e uma coluna");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public Piece piece(int row, int column) {
		if(!positioExists(row, column)) {
			throw new BoardException("Essa posição não existe no tabuleiro");		
		}
		return pieces[row][column];
	}

	public Piece piece(Position position) {
		if(!positioExists(position)) {
			throw new BoardException("Essa posição não existe no tabuleiro");		
		}
		return pieces[position.getRow()][position.getColumn()];
	}

	public void placePiece(Piece piece, Position position) {
		if(thereIsApiece(position)) {
			throw new BoardException("Já existe uma peça nessa posição " + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}
	
	public Piece removePiece (Position position) {
		if(!positioExists(position)) {
			throw new BoardException("Essa posição não existe no tabuleiro");		
		}
		
		if(piece(position) == null) {
			return null;
		}
		
		Piece aux = piece(position);
		aux.position = null;
		pieces[position.getRow()][position.getColumn()] = null;
		
		return aux;
	}

	private boolean positioExists(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}

	public boolean positioExists(Position position) {
		return positioExists(position.getRow(), position.getColumn());
	}

	public boolean thereIsApiece(Position position) {
		if(!positioExists(position)) {
			throw new BoardException("Essa posição não existe no tabuleiro");		
		}
		return piece(position) != null;
	}
}
