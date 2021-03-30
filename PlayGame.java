package tictac;

public class PlayGame {

	Position place = new Position();
	positionIntense5x5 place2 = new positionIntense5x5();
	PositionIntense place3 = new PositionIntense();

	protected void move(int idx) {

		place = place.move(idx);

	}

	protected void move2(int[] idx) {

		place2 = place2.move(idx);

	}

	protected void move3(int[] idx) {

		place3 = place3.move(idx);

	}

	public static void main(String[] args) {
		new SelectionPanel();

	}
}
