package tictac;

import java.util.ArrayList;
import java.util.List;

public class positionIntense5x5 {

	public char[][] board;
	public char turn;
	public int dim = 5;
	final int ROWS = 5;
	final int COLS = 5;

	public positionIntense5x5() {
		String[] string = { "     ", "     ", "     ", "     ", "     " };
		this.board = new char[string.length][];// make this 2d
		for (int i = 0; i < this.board.length; i++)
			this.board[i] = string[i].toCharArray();

		this.turn = 'x';
	}

	public positionIntense5x5(char[][] board, char turn) {
		this.board = board;
		this.turn = turn;
	}

	public boolean validMove(int[] index) {

		for (int[] idx : possibleMoves()) {
			if (idx[0] == index[0] && idx[1] == index[1]) {
				return true;
			}
		}
		return false;
	}

	public boolean gameEnd() {
		return win('x') || win('o');
	}

	public positionIntense5x5 move(int[] idx) {
		char[][] newBoard = board.clone();
		newBoard[idx[0]][idx[1]] = turn;
		return new positionIntense5x5(newBoard, turn == 'x' ? 'o' : 'x');
	}

	/** Get next best move for computer. Return int[2] of {row, col} */
	int[] best_move() {
		int[] result = minimax(7, Integer.MIN_VALUE, Integer.MAX_VALUE);
		// depth, max-turn, alpha, beta
		return new int[] { result[1], result[2] }; // row, col
	}

	/**
	 * Minimax (recursive) at level of depth for maximizing or minimizing player
	 * with alpha-beta cut-off. Return int[3] of {score, row, col}
	 */
	private int[] minimax(int depth, int alpha, int beta) {
		// mySeed is maximizing; while oppSeed is minimizing
		int score;
		int bestRow = -1;
		int bestCol = -1;

//		if (turn == 'x') {
//			turn = 'o';
//		} else {
//			turn = 'x';
//		}

		if (win('x')) {
			score = evaluate();
			return new int[] { score, bestRow, bestCol };
		}
		if (win('o')) {
			score = evaluate();
			return new int[] { score, bestRow, bestCol };
		}

		// Generate possible next moves in a list of int[2] of {row, col}.
		List<int[]> nextMoves = possibleMoves();

		if (nextMoves.isEmpty() || depth == 0) {
			// Gameover or depth reached, evaluate score
			score = evaluate();
			return new int[] { score, bestRow, bestCol };
		} else {
			for (int[] move : nextMoves) {
				// try this move for the current "player"
				board[move[0]][move[1]] = turn;
				if (turn == 'x') { // mySeed (computer) is maximizing player
					score = minimax(depth - 1, alpha, beta)[0];
					if (score > alpha) {
						alpha = score;
						bestRow = move[0];
						bestCol = move[1];
					}
				} else { // oppSeed is minimizing player
					score = minimax(depth - 1, alpha, beta)[0];
					if (score < beta) {
						beta = score;
						bestRow = move[0];
						bestCol = move[1];
					}
				}
				// undo move
				board[move[0]][move[1]] = ' ';
				// cut-off
				if (alpha >= beta) {
					break;
				}
			}
			return new int[] { (turn == 'x') ? alpha : beta, bestRow, bestCol };
		}
	}

	/**
	 * Find all valid next moves. Return List of moves in int[2] of {row, col} or
	 * empty list if gameover
	 */
	private List<int[]> possibleMoves() {
		List<int[]> nextMoves = new ArrayList<int[]>(); // allocate List

		// If gameover, i.e., no next move
		if (win('x') || win('o')) {
			return nextMoves; // return empty list
		}

		// Search for empty cells and add to the List
		for (int row = 0; row < ROWS; ++row) {
			for (int col = 0; col < COLS; ++col) {
				if (board[row][col] == ' ') {
					nextMoves.add(new int[] { row, col });
				}
			}
		}
		return nextMoves;
	}

	/**
	 * The heuristic evaluation function for the current board
	 * 
	 * @Return +100, +10, +1 for EACH 3-, 2-, 1-in-a-line for computer. -100, -10,
	 *         -1 for EACH 3-, 2-, 1-in-a-line for opponent. 0 otherwise
	 */
	private int evaluate() {
		int score = 0;
		// Evaluate score for each of the 8 lines (3 rows, 3 columns, 2 diagonals)
		score += evaluateLine(0, 0, 0, 1, 0, 2); // row 0
		score += evaluateLine(1, 0, 1, 1, 1, 2); // row 1
		score += evaluateLine(2, 0, 2, 1, 2, 2); // row 2
		score += evaluateLine(0, 0, 1, 0, 2, 0); // col 0
		score += evaluateLine(0, 1, 1, 1, 2, 1); // col 1
		score += evaluateLine(0, 2, 1, 2, 2, 2); // col 2
		score += evaluateLine(0, 0, 1, 1, 2, 2); // diagonal
		score += evaluateLine(0, 2, 1, 1, 2, 0); // alternate diagonal
		return score;
	}

	/**
	 * The heuristic evaluation function for the given line of 3 cells
	 * 
	 * @Return +100, +10, +1 for 3-, 2-, 1-in-a-line for computer. -100, -10, -1 for
	 *         3-, 2-, 1-in-a-line for opponent. 0 otherwise
	 */
	private int evaluateLine(int row1, int col1, int row2, int col2, int row3, int col3) {
		int score = 0;

		// First cell
		if (board[row1][col1] == 'x') {
			score = 1;
		} else if (board[row1][col1] == 'o') {
			score = -1;
		}

		// Second cell
		if (board[row2][col2] == 'x') {
			if (score == 1) { // cell1 is mySeed
				score = 10;
			} else if (score == -1) { // cell1 is oppSeed
				return 0;
			} else { // cell1 is empty
				score = 1;
			}
		} else if (board[row2][col2] == 'o') {
			if (score == -1) { // cell1 is oppSeed
				score = -10;
			} else if (score == 1) { // cell1 is mySeed
				return 0;
			} else { // cell1 is empty
				score = -1;
			}
		}

		// Third cell
		if (board[row3][col3] == 'x') {
			if (score > 0) { // cell1 and/or cell2 is mySeed
				score *= 10;
			} else if (score < 0) { // cell1 and/or cell2 is oppSeed
				return 0;
			} else { // cell1 and cell2 are empty
				score = 1;
			}
		} else if (board[row3][col3] == 'o') {
			if (score < 0) { // cell1 and/or cell2 is oppSeed
				score *= 10;
			} else if (score > 1) { // cell1 and/or cell2 is mySeed
				return 0;
			} else { // cell1 and cell2 are empty
				score = -1;
			}
		}
		return score;
	}

	private int[] winningPatterns = { 0b1111100000000000000000000, 0b0000011111000000000000000,
			0b0000000000111110000000000, 0b0000000000000001111100000, 0b0000000000000000000011111, // rows
			0b1000010000100001000010000, 0b0100001000010000100001000, 0b0010000100001000010000100,
			0b0001000010000100001000010, 0b0000100001000010000100001, // cols
			0b1000001000001000001000001, 0b0000100010001000100010000 // diagonals
	};

	/** Returns true if thePlayer wins */
	public boolean win(char thePlayer) {
		int pattern = 0b0000000000000000000000000; // 9-bit pattern for the 9 cells
		for (int row = 0; row < ROWS; ++row) {
			for (int col = 0; col < COLS; ++col) {
				if (board[row][col] == thePlayer) {
					pattern |= (1 << (row * COLS + col));
				}
			}
		}
		for (int winningPattern : winningPatterns) {
			if ((pattern & winningPattern) == winningPattern)
				return true;
		}
		return false;
	}

}
