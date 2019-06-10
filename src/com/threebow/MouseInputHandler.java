package com.threebow;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class MouseInputHandler implements MouseListener {
	@Override
	public void mousePressed(MouseEvent e) {
		//Get the tile
		Tile tile = (Tile) e.getSource();

		//Don't allow clicking anything if the game is over
		if(tile.board.gameOver) return;

		if(e.isMetaDown()) {
			//Flag it if we're right-clicking
			tile.toggleFlag();
		} else if(!tile.flagged) {
			//Generate the mines on this board
			if(tile.board.isBuffered) {
				tile.board.generateMines(tile);
			}

			//Otherwise, expose the tile
			if(tile.mine) {
				Main.game.end();
			} else {
				tile.expose();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}