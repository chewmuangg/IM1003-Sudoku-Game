import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class GameLayouts extends JFrame {
	// Name-constants for the game properties
		public static final int GRID_SIZE = 9; // Size of the board
		public static final int SUBGRID_SIZE = 3; // Size of the sub-grid

		// Name-constants for UI control (sizes, colors and fonts)
		public static final int CELL_SIZE = 60; // Cell width/height in pixels
		public static final int CANVAS_WIDTH = CELL_SIZE * GRID_SIZE;
		public static final int CANVAS_HEIGHT = CELL_SIZE * GRID_SIZE;
		// Board width/height in pixels
		public static final Color OPEN_CELL_BGCOLOR = Color.YELLOW;
		public static final Color OPEN_CELL_TEXT_YES = new Color(0, 255, 0); // RGB - Green
		public static final Color OPEN_CELL_TEXT_NO = Color.RED;
		public static final Color CLOSED_CELL_BGCOLOR = new Color(240, 240, 240); // RGB
		public static final Color CLOSED_CELL_TEXT = Color.BLACK;
		public static final Font FONT_NUMBERS = new Font("Monospaced", Font.BOLD, 20);

		// The game board composes of 9x9 JTextFields,
		// each containing String "1" to "9", or empty String
		protected JTextField[][] tfCells = new JTextField[GRID_SIZE][GRID_SIZE];
}

