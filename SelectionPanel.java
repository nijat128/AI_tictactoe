package tictac;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class SelectionPanel extends JPanel {

	/**
	 * Allows the user to select the game mode the user wishes to play.
	 */
	public SelectionPanel() {

		Object options[] = { "Single Player", "Multiplayer", "Cancel" };
		Object levels[] = { "Classic", "Intense", "Expert" };

		int level;
		int selection;

		selection = JOptionPane.showOptionDialog(null, "How would you like to play?", "Tic Tac Toe",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

		if (selection == 2) {
			System.exit(0);
		}

		level = JOptionPane.showOptionDialog(null, "Please choose a play mode?", "Tic Tac Toe",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, levels, levels[2]);

		if (level == 0) {

			if (selection == 1) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						JFrame gameframe = new JFrame("TicTacToe");
						gameframe.setLayout(new GridLayout(3, 3));
						gameframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

						final PlayGame play = new PlayGame();
						final JButton[] buttons = new JButton[9];

						for (int i = 0; i < 9; i++) {
							final int idx = i;
							final JButton btn = new JButton();
							buttons[i] = btn;

							btn.setPreferredSize(new Dimension(150, 150));
							btn.setBackground(Color.YELLOW);
							btn.setFont(new Font(null, Font.PLAIN, 125));
							btn.addMouseListener(new MouseListener() {

								@Override
								public void mouseReleased(MouseEvent e) {
								}

								@Override
								public void mousePressed(MouseEvent e) {
								}

								@Override
								public void mouseExited(MouseEvent e) {
								}

								@Override
								public void mouseEntered(MouseEvent e) {
								}

								@Override
								public void mouseClicked(MouseEvent e) {

									if (play.place.validMove(idx) == false) {
										return;
									}

									btn.setText("" + play.place.turn);
									play.move(idx);

									if (play.place.gameEnd()) {
										String message = "";
										if (play.place.win('x')) {
											message = "Congratulations, Player 1 Wins! Would you like to play again?";
										} else if (play.place.win('o')) {
											message = "Congratulations, Player 2 Wins! Would you like to play again?";
										} else {
											message = "Draw! Would you like to play again?";
										}

										int result = JOptionPane.showConfirmDialog(null, message);
										if (result == JOptionPane.YES_OPTION) {

											gameframe.dispose();
											new SelectionPanel();

										} else {
											System.exit(0);
										}
									}
								}
							});
							gameframe.add(btn);
						}
						gameframe.setVisible(true);
						gameframe.pack();
					}

				});

			} else if (selection == 0) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						JFrame gameframe = new JFrame("TicTac");
						gameframe.setLayout(new GridLayout(3, 3));
						gameframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

						final PlayGame play = new PlayGame();
						final JButton[] buttons = new JButton[9];

						for (int i = 0; i < 9; i++) {
							final int idx = i;
							final JButton btn = new JButton();
							buttons[i] = btn;

							btn.setPreferredSize(new Dimension(150, 150));
							btn.setBackground(Color.YELLOW);
							btn.setFont(new Font(null, Font.PLAIN, 125));
							btn.addMouseListener(new MouseListener() {

								@Override
								public void mouseReleased(MouseEvent e) {
								}

								@Override
								public void mousePressed(MouseEvent e) {
								}

								@Override
								public void mouseExited(MouseEvent e) {
								}

								@Override
								public void mouseEntered(MouseEvent e) {
								}

								@Override
								public void mouseClicked(MouseEvent e) {

									if (play.place.validMove(idx) == false) {
										return;
									}

									btn.setText("" + play.place.turn);
									play.move(idx);

									if (!play.place.gameEnd()) {
										int best = play.place.bestMove();
										buttons[best].setText("" + play.place.turn);
										play.move(best);
									}
									if (play.place.gameEnd()) {
										String message = "";
										if (play.place.win('x')) {
											message = "Congratulations, You Win! Would you like to play again?";
										} else if (play.place.win('o')) {
											message = "Computer won, would you like to play again?";
										} else {
											message = "Draw! Would you like to play again?";
										}

										int result = JOptionPane.showConfirmDialog(null, message);
										if (result == JOptionPane.YES_OPTION) {

											gameframe.dispose();
											new SelectionPanel();

										} else {
											System.exit(0);
										}
									}
								}
							});
							gameframe.add(btn);
						}
						gameframe.setVisible(true);
						gameframe.pack();
					}

				});

			}

		} else if (level == 2) {

			if (selection == 1) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						JFrame gameframe = new JFrame("TicTac");
						gameframe.setLayout(new GridLayout(5, 5));
						gameframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

						final PlayGame play = new PlayGame();
						final JButton[][] buttons = new JButton[5][5];
						List<int[]> idx = new ArrayList<int[]>();

						for (int i = 0; i < 5; i++) {
							for (int j = 0; j < 5; j++) {
								idx.add(new int[] { i, j });
							}
						}

						for (int[] index : idx) {
							final JButton btn = new JButton();
							buttons[index[0]][index[1]] = btn;
							btn.setPreferredSize(new Dimension(150, 150));
							btn.setBackground(Color.YELLOW);
							btn.setFont(new Font(null, Font.PLAIN, 125));
							btn.addMouseListener(new MouseListener() {

								@Override
								public void mouseReleased(MouseEvent e) {
								}

								@Override
								public void mousePressed(MouseEvent e) {
								}

								@Override
								public void mouseExited(MouseEvent e) {
								}

								@Override
								public void mouseEntered(MouseEvent e) {
								}

								@Override
								public void mouseClicked(MouseEvent e) {

									if (play.place2.validMove(index) == false) {
										return;
									}

									btn.setText("" + play.place2.turn);
									play.move2(index);

//									if (!play.place2.gameEnd()) {
//										int[] best = play.place2.best_move();
//										System.out.println(best[0]);
//										buttons[best[0]][best[1]].setText("" + play.place2.turn);
//										play.move2(best);
//									}
									if (play.place2.gameEnd()) {
										String message = "";
										if (play.place2.win('x')) {
											message = "Congratulations, Player 1 Wins! Would you like to play again?";
										} else if (play.place2.win('o')) {
											message = "Congratulations, Player 2 Wins! Would you like to play again?";
										} else {
											message = "Draw! Would you like to play again?";
										}

										int result = JOptionPane.showConfirmDialog(null, message);
										if (result == JOptionPane.YES_OPTION) {

											gameframe.dispose();
											new SelectionPanel();

										} else {
											System.exit(0);
										}
									}
								}
							});
							gameframe.add(btn);

						}
						gameframe.setVisible(true);
						gameframe.pack();
					}

				});

			} else if (selection == 0) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						JFrame gameframe = new JFrame("TicTac");
						gameframe.setLayout(new GridLayout(5, 5));
						gameframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

						final PlayGame play = new PlayGame();
						final JButton[][] buttons = new JButton[5][5];
						List<int[]> idx = new ArrayList<int[]>();

						for (int i = 0; i < 5; i++) {
							for (int j = 0; j < 5; j++) {
								idx.add(new int[] { i, j });
							}
						}

						for (int[] index : idx) {
							final JButton btn = new JButton();
							buttons[index[0]][index[1]] = btn;
							btn.setPreferredSize(new Dimension(150, 150));
							btn.setBackground(Color.YELLOW);
							btn.setFont(new Font(null, Font.PLAIN, 125));
							btn.addMouseListener(new MouseListener() {

								@Override
								public void mouseReleased(MouseEvent e) {
								}

								@Override
								public void mousePressed(MouseEvent e) {
								}

								@Override
								public void mouseExited(MouseEvent e) {
								}

								@Override
								public void mouseEntered(MouseEvent e) {
								}

								@Override
								public void mouseClicked(MouseEvent e) {

									if (play.place2.validMove(index) == false) {
										return;
									}

									btn.setText("" + play.place2.turn);
									play.move2(index);

									if (!play.place2.gameEnd()) {
										int[] best = play.place2.best_move();
										System.out.println(best[0]);
										buttons[best[0]][best[1]].setText("" + play.place2.turn);
										play.move2(best);
									}
									if (play.place2.gameEnd()) {
										String message = "";
										if (play.place2.win('x')) {
											message = "Congratulations, You Win! Would you like to play again?";
										} else if (play.place2.win('o')) {
											message = "Computer won, would you like to play again?";
										} else {
											message = "Draw! Would you like to play again?";
										}

										int result = JOptionPane.showConfirmDialog(null, message);
										if (result == JOptionPane.YES_OPTION) {

											gameframe.dispose();
											new SelectionPanel();

										} else {
											System.exit(0);
										}
									}
								}
							});
							gameframe.add(btn);

						}
						gameframe.setVisible(true);
						gameframe.pack();
					}

				});

			}

		} else if (level == 1) {

			if (selection == 1) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						JFrame gameframe = new JFrame("TicTac");
						gameframe.setLayout(new GridLayout(4, 4));
						gameframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

						final PlayGame play = new PlayGame();
						final JButton[][] buttons = new JButton[4][4];
						List<int[]> idx = new ArrayList<int[]>();

						for (int i = 0; i < 4; i++) {
							for (int j = 0; j < 4; j++) {
								idx.add(new int[] { i, j });
							}
						}

						for (int[] index : idx) {
							final JButton btn = new JButton();
							buttons[index[0]][index[1]] = btn;
							btn.setPreferredSize(new Dimension(150, 150));
							btn.setBackground(Color.YELLOW);
							btn.setFont(new Font(null, Font.PLAIN, 125));
							btn.addMouseListener(new MouseListener() {

								@Override
								public void mouseReleased(MouseEvent e) {
								}

								@Override
								public void mousePressed(MouseEvent e) {
								}

								@Override
								public void mouseExited(MouseEvent e) {
								}

								@Override
								public void mouseEntered(MouseEvent e) {
								}

								@Override
								public void mouseClicked(MouseEvent e) {

									if (play.place3.validMove(index) == false) {
										return;
									}

									btn.setText("" + play.place3.turn);
									play.move3(index);

//									if (!play.place2.gameEnd()) {
//										int[] best = play.place2.best_move();
//										System.out.println(best[0]);
//										buttons[best[0]][best[1]].setText("" + play.place2.turn);
//										play.move2(best);
//									}

									if (play.place3.gameEnd()) {
										String message = "";
										if (play.place3.win('x')) {
											message = "Congratulations, Player 1 Wins! Would you like to play again?";
										} else if (play.place3.win('o')) {
											message = "Congratulations, Player 2 Wins! Would you like to play again?";
										} else {
											message = "Draw! Would you like to play again?";
										}

										int result = JOptionPane.showConfirmDialog(null, message);
										if (result == JOptionPane.YES_OPTION) {

											gameframe.dispose();
											new SelectionPanel();

										} else {
											System.exit(0);
										}
									}
								}
							});
							gameframe.add(btn);

						}
						gameframe.setVisible(true);
						gameframe.pack();
					}

				});

			} else if (selection == 0) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						JFrame gameframe = new JFrame("TicTac");
						gameframe.setLayout(new GridLayout(4, 4));
						gameframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

						final PlayGame play = new PlayGame();
						final JButton[][] buttons = new JButton[4][4];
						List<int[]> idx = new ArrayList<int[]>();

						for (int i = 0; i < 4; i++) {
							for (int j = 0; j < 4; j++) {
								idx.add(new int[] { i, j });
							}
						}

						for (int[] index : idx) {
							final JButton btn = new JButton();
							buttons[index[0]][index[1]] = btn;
							btn.setPreferredSize(new Dimension(150, 150));
							btn.setBackground(Color.YELLOW);
							btn.setFont(new Font(null, Font.PLAIN, 125));
							btn.addMouseListener(new MouseListener() {

								@Override
								public void mouseReleased(MouseEvent e) {
								}

								@Override
								public void mousePressed(MouseEvent e) {
								}

								@Override
								public void mouseExited(MouseEvent e) {
								}

								@Override
								public void mouseEntered(MouseEvent e) {
								}

								@Override
								public void mouseClicked(MouseEvent e) {

									if (play.place3.validMove(index) == false) {
										return;
									}

									btn.setText("" + play.place3.turn);
									play.move3(index);

									if (!play.place3.gameEnd()) {
										int[] best = play.place3.best_move();
										System.out.println(best[0]);
										buttons[best[0]][best[1]].setText("" + play.place3.turn);
										play.move3(best);
									}
									if (play.place3.gameEnd()) {
										String message = "";
										if (play.place3.win('x')) {
											message = "Congratulations, You Win! Would you like to play again?";
										} else if (play.place3.win('o')) {
											message = "Computer won, would you like to play again?";
										} else {
											message = "Draw! Would you like to play again?";
										}

										int result = JOptionPane.showConfirmDialog(null, message);
										if (result == JOptionPane.YES_OPTION) {

											gameframe.dispose();
											new SelectionPanel();

										} else {
											System.exit(0);
										}
									}
								}
							});
							gameframe.add(btn);

						}
						gameframe.setVisible(true);
						gameframe.pack();
					}

				});

			}

		}

	}
}