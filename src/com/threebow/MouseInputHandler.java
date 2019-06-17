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

		if(tile.exposed) {
			//Go through all adjacent tiles
			boolean canExpose = tile.getAdjacentFlagCount() == tile.getDisplayNumber();

			for(Tile adj : tile.getAdjacentTiles()) {
				if(e.isControlDown() && canExpose) {
					//Expose if control is down
					adj.tryExpose();
				} else if(e.isShiftDown()) {
					//Flag if shift is down
					adj.toggleFlag();
				}
			}

			return;
		}

		if(e.isMetaDown()) {
			//Flag it if we're right-clicking
			tile.toggleFlag();
		} else {
			tile.tryExpose();
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