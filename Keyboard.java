import java.awt.event.*;

public class Keyboard implements KeyListener{
        private int pressed, released, typed;

        public Keyboard() {
                pressed = -1;
                released = -1;
                typed = -1;
        }

        @Override
        public void keyPressed(KeyEvent e) {
               pressed = e.getKeyCode();
               System.out.println("press works!");
        }

        @Override
        public void keyReleased(KeyEvent e) {
               released = e.getKeyCode();
               System.out.println("release works!");
        }

        @Override
        public void keyTyped(KeyEvent e) {
                typed = e.getKeyCode();
               System.out.println("type works!");
        }

        public int getPressed() {
                return pressed;
        }

        public int getReleased() {
                return released;
        }

        public int getTyped() {
                return typed;
        }
}
