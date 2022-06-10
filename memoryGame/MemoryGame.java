package memoryGame;


/**
 * SlitherLink does the user interaction for a square Slither Link puzzle.
 * 
 * @author Joshua Wyatt (Project 2 attempted solo)
 * @version 1.1
 */
import java.awt.*;
import java.awt.event.*;

public class MemoryGame implements MouseListener
{    
    private Puzzle game;     // internal representation of the game
    private SimpleCanvas sc; // the display window
    
    private final int sSize = 50;       // the size of each square
    private final int offset = 25;      // the offset so everything is properly visible
    private final int numberoffset = 3; // number offset such that I can finely tune the location of numbers within their respective squares
    private final int dRadius = 5;      // the radius of each dot
    private final int rWidth = 3;       // half of the width of each line
    
    private final Color bgColor = Color.white;

    /**
     * Creates a display for playing the puzzle p.
     */
    public MemoryGame(Puzzle p)
    {
        // COMPLETE THIS 4b
        this.game = p;
        sc = new SimpleCanvas("Slither Link", (game.getSize() + 1) * sSize, (game.getSize() + 2) * sSize + offset, bgColor);
        sc.addMouseListener(this);
        sc.setFont(new Font("Times", 20, sSize / 10 * 3));
        displayPuzzle();
    }
    
    /**
     * Returns the current state of the game.
     */
    public Puzzle getGame()
    {
        return game;
    }

    /**
     * Returns the current state of the canvas.
     */
    public SimpleCanvas getCanvas()
    {
        return sc;
    }

    /**
     * Displays the initial puzzle on sc. 
     * Have a look at puzzle-loop.com for a basic display, or use your imagination. 
     */
    public void displayPuzzle()
    {
        // COMPLETE THIS 4a
        Color c = Color.black;
        Color w = Color.white;
        int s = game.getSize();
        boolean[][] horizontalLines = new boolean[0][0];
        for (int i = 0; i < s + 1; i++)
            for (int j = 0; j < s; j++)
            {
                if (horizontalLines[i][j] == true) {
                    sc.drawRectangle(j * sSize + offset, i * sSize + offset - rWidth, (j + 1) * sSize + offset, i * sSize + offset + rWidth, c);
                }
                if (horizontalLines[i][j] == false) {
                    sc.drawRectangle(j * sSize + offset, i * sSize + offset - rWidth, (j + 1) * sSize + offset, i * sSize + offset + rWidth, w);
                }
            }
        boolean[][] verticalLines = new boolean[0][0];
        for (int i = 0; i < s; i++)
            for (int j = 0; j < s + 1; j++)
            {
                if (verticalLines[i][j] == true) {
                    sc.drawRectangle(j * sSize + offset - rWidth, i * sSize + offset, j * sSize + offset + rWidth, (i + 1) * sSize + offset, c);
                }
                if (verticalLines[i][j] == false) {
                    sc.drawRectangle(j * sSize + offset - rWidth, i * sSize + offset, j * sSize + offset + rWidth, (i + 1) * sSize + offset, w);
                }
            }
        int[][] squareValue = game.getGame();
        for (int i = 0; i < s; i++)
            for (int j = 0; j < s; j++)
            {
                if (squareValue[i][j] != -1) {
                    String n = String.valueOf(squareValue[i][j]);
                    sc.drawString(n, j * sSize + 2 * offset - numberoffset, i * sSize + 2 * offset + 2 * numberoffset, c);
                }
            }
        for (int i = 0; i < s + 1; i++)
            for (int j = 0; j < s + 1; j++)
            {
                sc.drawCircle(i * sSize + offset, j * sSize + offset, dRadius, c);
            }
        sc.drawRectangle(offset - 2 * numberoffset, s * sSize + (2 * offset), 2 * offset + sSize - (2 * numberoffset), s * sSize + (3 * offset), c);
        sc.drawString("clear all", offset + 2 * numberoffset, s * sSize + (2 * offset) + 6 * numberoffset, w);
    }
    /**
     * Makes a horizontal click to the right of Dot r,c.
     * Update game and the display, if the indices are legal; otherwise do nothing.
     */
    public void horizontalClick(int r, int c)
    {
        // COMPLETE THIS 5a
        if (r >= 0 && r < (game.getSize() + 1) && c >= 0 && c < game.getSize()) {
        game.clickSquare(r, c);}
        displayPuzzle();
    }
    
    /**
     * Makes a vertical click below Dot r,c. 
     * Update game and the display, if the indices are legal; otherwise do nothing. 
     */
    public void verticalClick(int r, int c)
    {
        // COMPLETE THIS 5b
        if (r >= 0 && r < game.getSize() && c >= 0 && c < (game.getSize() + 1)) {
        game.clickSquare(r, c);}
        displayPuzzle();
    }
    /**
     * Actions for a mouse press.
     */
    public void mousePressed(MouseEvent e) 
    {
        // COMPLETE THIS 6
        int s = sSize;
        int r = (e.getY() - offset) / s;
        int c = (e.getX() - offset) / s;
        int dToTop = (e.getY() - offset) % s;
        int dToBottom = s - dToTop;
        int dToLeft = (e.getX() - offset) % s;
        int dToRight = s - dToLeft;
        int clearbuttonLeft = offset - 2 * numberoffset;
        int clearbuttonRight = 2 * offset + sSize - (2 * numberoffset);
        int clearbuttonTop = game.getSize() * s + (2 * offset);
        int clearbuttonBottom = game.getSize() * s + (3 * offset);
        if (e.getX() >= clearbuttonLeft && e.getX() <= clearbuttonRight && e.getY() <= clearbuttonBottom && e.getY() >= clearbuttonTop)
            game.clear();
        if (dToLeft <= dToTop && dToLeft <= dToBottom && dToLeft <= dToRight) {     // left vertical is closest or equal closest line segment to the click
            game.clickSquare(r, c);}                                                  // a click in the centre of a square will toggle the left vertical
        if (dToRight <= dToTop && dToRight <= dToBottom && dToRight < dToLeft) {    // right vertical is closest or equal closest line segment to the click
            game.clickSquare(r, c+1);}
        if (dToTop < dToLeft && dToTop < dToRight && dToTop < dToBottom) {          // top horizontal is closest line segment to the click
            game.clickSquare(r, c);}
        if (dToBottom < dToLeft && dToBottom < dToRight && dToBottom < dToTop) {    // bottom horizontal is closest line segment to the click
            game.clickSquare(r+1, c);}
        displayPuzzle();
    }
    public void mouseClicked(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}

