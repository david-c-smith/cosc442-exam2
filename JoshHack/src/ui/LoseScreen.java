package ui;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;
import game.CreatureInterface;

public class LoseScreen implements Screen {
	private CreatureInterface player;
	
	public LoseScreen(CreatureInterface player){
		this.player = player;
	}
	
	@Override
	public void displayOutput(AsciiPanel terminal) {
		terminal.writeCenter("R.I.P.", 3);
		terminal.writeCenter(player.causeOfDeath(), 5);
		terminal.writeCenter("-- press [enter] to restart --", 22);
	}

	@Override
	public Screen respondToUserInput(KeyEvent key) {
		return key.getKeyCode() == KeyEvent.VK_ENTER ? new PlayScreen() : this;
	}
}
