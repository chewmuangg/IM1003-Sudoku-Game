import java.awt.*; // Uses AWT's Layout Managers
import java.awt.event.*; // Uses AWT's Event Handlers
import javax.swing.*; // Uses Swing's Container/Components
import java.io.*; // for bgm
import javax.sound.sampled.*; // for bgm 

/**
 * The Sudoku game. To solve the number puzzle, each row, each column, and each
 * of the nine 3×3 sub-grids shall contain all of the digits from 1 to 9
 */

public class Simple extends GameLayouts {
	// Puzzle to be solved and the mask (which can be used to control the difficulty
	// level).
	// Hardcoded here. Extra credit for automatic puzzle generation with various
	// difficulty levels.
	private int[][] puzzle = 
		  { { 5, 3, 4, 6, 7, 8, 9, 1, 2 }, 
			{ 6, 7, 2, 1, 9, 5, 3, 4, 8 },
			{ 1, 9, 8, 3, 4, 2, 5, 6, 7 }, 
			{ 8, 5, 9, 7, 6, 1, 4, 2, 3 }, 
			{ 4, 2, 6, 8, 5, 3, 7, 9, 1 },
			{ 7, 1, 3, 9, 2, 4, 8, 5, 6 }, 
			{ 9, 6, 1, 5, 3, 7, 2, 8, 4 }, 
			{ 2, 8, 7, 4, 1, 9, 6, 3, 5 },
			{ 3, 4, 5, 2, 8, 6, 1, 7, 9 } };
	
	// Opens 3 (number 8) cells
	private boolean[][] masks = 
		  { { false, false, false, false, false, true, false, false, false },
			{ false, false, false, false, false, false, false, false, true },
			{ false, false, true, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false } };

	/**
	 * Constructor to setup the game and the UI Components
	 */
	public Simple() {
		
		// play audio
		try {
			// Open an audio input stream from a wav file
			File soundFile = new File("C:\\IM1003 Projects\\Eclipse Workspace\\Sudoku Game\\src\\HIP.wav");
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			// Get a sound clip resource.
			Clip clip = AudioSystem.getClip();
			// Open audio clip and load samples from the audio input stream.
			clip.open(audioIn);
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY); 	// repeat forever
			           
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		} catch (LineUnavailableException e) {
		    e.printStackTrace();
		}
		
		// Menu bar
		JMenuBar menuBar; // the menu-bar
		JMenu menu; // each menu in the menu-bar
		JMenuItem menuItem; // an item in a menu

		menuBar = new JMenuBar();

		// First Menu
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_A); // alt short-cut key
		menuBar.add(menu); // the menu-bar adds this menu

		menuItem = new JMenuItem("Restart", KeyEvent.VK_R); // alt short-cut key
		menu.add(menuItem); // the menu adds this item
		InputListener listener = new InputListener();
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				masks[1][8] = true;
				masks[2][2] = true;
				masks[0][5] = true;
				
				// reset to default game layout
				for (int row = 0; row < GRID_SIZE; ++row) {
					for (int col = 0; col < GRID_SIZE; ++col) {
						if (masks[row][col]) { // if masks [row][col] == true
							tfCells[row][col].setText(""); // set to empty string
							tfCells[row][col].setEditable(true);
							tfCells[row][col].setBackground(OPEN_CELL_BGCOLOR);

							// Add ActionEvent listener to process the input
							// [TODO 4]
							tfCells[row][col].addActionListener(listener); // For all editable rows and cols
						} else { // if masks [row][col] == false
							tfCells[row][col].setText(puzzle[row][col] + "");
							tfCells[row][col].setEditable(false);
							tfCells[row][col].setBackground(CLOSED_CELL_BGCOLOR);
							tfCells[row][col].setForeground(CLOSED_CELL_TEXT);
						}
					}
				}
			}
		});

		setJMenuBar(menuBar); // "this" JFrame sets its menu-bar

		Container cp = getContentPane();
		cp.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE)); // 9x9 GridLayout

		// Allocate a common listener as the ActionEvent listener for all the
		// JTextFields
		// [TODO 3]

		// Construct 9x9 JTextFields and add to the content-pane
		for (int row = 0; row < GRID_SIZE; ++row) {
			for (int col = 0; col < GRID_SIZE; ++col) {
				tfCells[row][col] = new JTextField(); // Allocate element of array
				cp.add(tfCells[row][col]); // ContentPane adds JTextField
				if (masks[row][col]) { // if masks [row][col] == true
					tfCells[row][col].setText(""); // set to empty string
					tfCells[row][col].setEditable(true);
					tfCells[row][col].setBackground(OPEN_CELL_BGCOLOR);

					// Add ActionEvent listener to process the input
					// [TODO 4]
					tfCells[row][col].addActionListener(listener); // For all editable rows and cols
				} else { // if masks [row][col] == false
					tfCells[row][col].setText(puzzle[row][col] + "");
					tfCells[row][col].setEditable(false);
					tfCells[row][col].setBackground(CLOSED_CELL_BGCOLOR);
					tfCells[row][col].setForeground(CLOSED_CELL_TEXT);
				}
				// Beautify all the cells
				tfCells[row][col].setHorizontalAlignment(JTextField.CENTER);
				tfCells[row][col].setFont(FONT_NUMBERS);
			}
		}

		// Set the size of the content-pane and pack all the components
		// under this container.
		cp.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
		pack();
		setLocation(370, 50); // set where the window is open
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Handle window closing
		setTitle("Sudoku - Simple");
		setVisible(true);
	}

	/** The entry main() entry method */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPage frame = new MenuPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// [TODO 2]
	// Define the Listener Inner Class
	// Inner class to be used as ActionEvent listener for ALL JTextFields
	private class InputListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			// All the 9*9 JTextFileds invoke this handler. We need to determine
			// which JTextField (which row and column) is the source for this invocation.
			int rowSelected = -1;
			int colSelected = -1;

			// Get the source object that fired the event
			JTextField source = (JTextField) e.getSource();
			// Scan JTextFields for all rows and columns, and match with the source object
			boolean found = false;
			boolean solved = true;
			for (int row = 0; row < GRID_SIZE && !found; ++row) {
				for (int col = 0; col < GRID_SIZE && !found; ++col) {
					
					//resetting the highlighted red cells back to CLOSED_CELL_BGCOLOR
					if (tfCells[row][col] == source) {
						// reset color for row
						for (int checkrow = 0; checkrow < GRID_SIZE; ++checkrow) {
							if (tfCells[checkrow][col].getBackground().equals(Color.red)) {
								tfCells[checkrow][col].setBackground(CLOSED_CELL_BGCOLOR);
							}
						}

						// reset color for column
						for (int checkcol = 0; checkcol < GRID_SIZE; ++checkcol) {
							if (tfCells[row][checkcol].getBackground().equals(Color.red)) {
								tfCells[row][checkcol].setBackground(CLOSED_CELL_BGCOLOR);
							}
						}

						// reset color for subgrid
						int subGridrow = (row / 3) * 3;
						int subGridcol = (col / 3) * 3;
						for (int checkboxrow = subGridrow; checkboxrow < subGridrow + 3; ++checkboxrow) {
							for (int checkboxcol = subGridcol; checkboxcol < subGridcol + 3; ++checkboxcol) {
								if (tfCells[checkboxrow][checkboxcol].getBackground().equals(Color.red)) {
									tfCells[checkboxrow][checkboxcol].setBackground(CLOSED_CELL_BGCOLOR);
								}
							}
						}
						
						rowSelected = row;
						colSelected = col;
						// [TODO 5]
						int input = Integer.parseInt(tfCells[rowSelected][colSelected].getText());
						// check if correct
						if (input == puzzle[rowSelected][colSelected]) {
							tfCells[row][col].setBackground(OPEN_CELL_TEXT_YES); // set background to Green on correct guess
							masks[row][col] = false; // update masks[][] on correct guess
							tfCells[row][col].setEditable(false); // make cell not editable after correct guess
						} else {
							tfCells[row][col].setBackground(OPEN_CELL_TEXT_NO); // set background to Red on wrong guess
							
							// check for error at same row
							for (int checkrow = 0; checkrow < GRID_SIZE; ++checkrow) {
								if (Integer.parseInt(tfCells[checkrow][col].getText()) == input) {
									tfCells[checkrow][col].setBackground(OPEN_CELL_TEXT_NO);
								}
							}

							// check for error at same col
							for (int checkcol = 0; checkcol < GRID_SIZE; ++checkcol) {
								if (Integer.parseInt(tfCells[row][checkcol].getText()) == input) {
									tfCells[row][checkcol].setBackground(OPEN_CELL_TEXT_NO);
								}
							}

							// check for error in subgrid
							for (int checkboxrow = subGridrow; checkboxrow < subGridrow + 3; ++checkboxrow) {
								for (int checkboxcol = subGridcol; checkboxcol < subGridcol + 3; ++checkboxcol) {
									if (Integer.parseInt(tfCells[checkboxrow][checkboxcol].getText()) == input) {
										tfCells[checkboxrow][checkboxcol].setBackground(OPEN_CELL_TEXT_NO);
									}
								}
							}
						}

						found = true; // break the inner/outer loops
						// [TODO 6]
						for (int i = 0; i < GRID_SIZE; ++i) {
							for (int j = 0; j < GRID_SIZE; ++j) {
								if (masks[i][j] == true) {
									solved = false;
									break;
								}
							}
						}
						if (solved == true)
							JOptionPane.showMessageDialog(null, "Congratulation!");
					}
				}
			}

			/*
			 * [TODO 5] 1. Get the input String via
			 * tfCells[rowSelected][colSelected].getText() 2. Convert the String to int via
			 * Integer.parseInt(). 3. Assume that the solution is unique. Compare the input
			 * number with the number in the puzzle[rowSelected][colSelected]. If they are
			 * the same, set the background to green (Color.GREEN); otherwise, set to red
			 * (Color.RED).
			 */

			/*
			 * [TODO 6] Check if the player has solved the puzzle after this move. You could
			 * update the masks[][] on correct guess, and check the masks[][] if any input
			 * cell pending.
			 */
		}
	}
}
