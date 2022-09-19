package ham;

public class Board {
	private char[][] board;
	private int rows,columns;
	
	public Board(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		this.board = new char[this.rows][this.columns];
		initialize_board();
	}
	public void initialize_board() {
		for(int i = 0; i < this.rows;i++) {
			for(int j = 0; j < this.columns; j++) {
				this.board[i][j] = '~';				
			}
		}		
	}
	public void mark_board(int y, int x, char symbol) {
		this.board[y][x] = symbol;
	}
	public void display_board() {
		String vWall = "|| ";
		String topWall = "||===";
		String top_line = "";
		for(int i = 0; i < columns;i++) {
			top_line += topWall;
		}
		//  ||===||===||===
		System.out.println(top_line+"||");
		
		for(int i = 0; i < this.rows;i++) {
			for(int j = 0; j < this.columns; j++) {
				System.out.print(vWall + board[i][j]+ " ");
			}
			System.out.print(vWall);
			System.out.println();
			System.out.println(top_line+"||");
		}
	}

}