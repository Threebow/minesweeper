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
			int flags = tile.getAdjacentFlagCount();
			boolean canExpose = flags == tile.getDisplayNumber();
			int adjTileCount = tile.getUncoveredAdjacentTileCount();

			for(Tile adj : tile.getAdjacentTiles()) {
				if(e.isControlDown() && canExpose) {
					//Expose if control is down
					adj.tryExpose();
				} else if(e.isShiftDown()) {
					//Flag the appropriate amount of mines
					if(flags == 0) {
						adj.setFlagged(true);
					} else if(flags > 0 && flags < adjTileCount) {
						adj.setFlagged(true);
					} else {
						adj.setFlagged(false);
					}
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