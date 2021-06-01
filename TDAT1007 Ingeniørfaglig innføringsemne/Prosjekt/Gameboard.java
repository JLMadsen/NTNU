import java.util.Arrays;
import lejos.hardware.lcd.*;



/**	Gameboard index layout
  *
  *		0	1	2
  *
  *		3	4	5
  *
  *		6	7	8
  */

class Gameboard
{
	private CellState[] gameboard;
	private final GraphicsLCD lcd;

	private final int[][] gameboardLines;
	private final int[][] gameboardCells;
	private final int cellSize;

	private boolean isMarked;

	public Gameboard(GraphicsLCD lcd)
	{
		gameboard = new CellState[9];
		reset();

		this.lcd =  lcd;

		gameboardLines = new int[][] {{ 10, 35, 100, 35 },
									  { 10, 65, 100, 65 },
									  { 40, 5, 40, 95 },
									  { 70, 5, 70, 95 }};
		gameboardCells = new int[][] {{ 15, 10 }, { 45, 10 }, { 75, 10 },
									  { 15, 40 }, { 45, 40 }, { 75, 40 },
									  { 15, 70 }, { 45, 70 }, { 75, 70 }};
		cellSize = 20;
		isMarked = false;
	}

	public CellState[] getGameboard()
	{ return gameboard; }

	public void markCell(int cell, CellState state)
	{
		gameboard[cell] = state;
		updateLCD();
	}

	public void reset()
	{
		for (int i = 0; i < gameboard.length; i++) { gameboard[i] = CellState.BLANK; }
	}

	private void updateLCD()
	{
		lcd.clear();
		for (int[] line : gameboardLines) lcd.drawLine(line[0], line[1], line[2], line[3]);
		for (int i = 0; i < gameboard.length; i++)
		{
			String tmpString = "";
			if (gameboard[i] == CellState.BLANK) continue;
			else if (gameboard[i] == CellState.CROSS) tmpString = "X";
			else if (gameboard[i] == CellState.CIRCLE) tmpString = "O";
			lcd.drawString(tmpString, gameboardCells[i][0] + 3, gameboardCells[i][1] + 3, 0);
		}
	}

	public void highlightCell(int cell)
	{
		lcd.clear();
		updateLCD();
		lcd.drawRect(gameboardCells[cell][0], gameboardCells[cell][1], cellSize, cellSize);
	}
}