package com.threebow;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class MouseInputHandler implements MouseListener {
	@Override
	public void mousePressed(MouseEvent e) {
		//Get the tile
		Tile tile = (Tile) e.getSource();
		if(e.isMetaDown()) {
			//Flag it if we're right-clicking
			tile.toggleFlag();
		} else if(!tile.flagged) {
			//Otherwise, expose the tile
			tile.expose();
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