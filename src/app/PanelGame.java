package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelGame extends JPanel {
	private static final int MARGIN_CASE = 5;
	private boolean etitableMode = false;

	// private PuzzleContext<String> puzzleContext;

	public PanelGame() {
		setFocusable(true);
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("a");
			}
		});
	}

	@Override
	public void paint(Graphics graph) {
		Graphics2D g = (Graphics2D) graph;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(new Color(150, 150, 150));
		g.fillRect(0, 0, getWidth(), getHeight());

		int n = 5;
		int sizeCase = Math.min(getWidth(), getHeight()) / n - MARGIN_CASE;
		Font font = new Font("Default", Font.BOLD, sizeCase / 2);
		FontMetrics metrics = g.getFontMetrics(font);
		g.setFont(font);

		// Liste des cases
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {

				g.setColor(new Color(200, 200, 200));
				g.fillRoundRect(2 * MARGIN_CASE + i * sizeCase, 2 * MARGIN_CASE + j * sizeCase, sizeCase - MARGIN_CASE,
						sizeCase - MARGIN_CASE, 16, 16);

				g.setColor(new Color(100, 100, 100));
				g.drawRoundRect(2 * MARGIN_CASE + i * sizeCase, 2 * MARGIN_CASE + j * sizeCase, sizeCase - MARGIN_CASE,
						sizeCase - MARGIN_CASE, 16, 16);

				String str = (1 + i + n * j) + "";
				int fontX = (sizeCase - metrics.stringWidth(str)) / 2;
				int fontY = (sizeCase + metrics.getAscent() - metrics.getDescent()) / 2;
				g.drawString(str, 2 * MARGIN_CASE + i * sizeCase + fontX, 2 * MARGIN_CASE + j * sizeCase + fontY);
			}
		}
	}
}
