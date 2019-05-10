package com.threebow;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInputHandler implements MouseListener {
	@Override
	public void mousePressed(MouseEvent e) {
		Tile tile = (Tile) e.getSource();
		if(e.isMetaDown()) {
			tile.toggleFlag();
		} else if(!tile.flagged) {
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