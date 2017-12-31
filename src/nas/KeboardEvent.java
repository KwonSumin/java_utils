package nas;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.peer.RobotPeer;
import java.io.File;
import java.util.Enumeration;

public class KeboardEvent {
	
	public static void main(String[] args) throws Exception{
		Robot robot = new Robot();
		
		robot.keyPress(KeyEvent.VK_F4);
		
	}
}
