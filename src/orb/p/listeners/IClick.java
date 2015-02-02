package orb.p.listeners;

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

public class IClick extends MouseInputAdapter {

    int eventX = 0;
    int eventY = 0;
    int x = 0;
    int y = 0;
    int xdrag = 0;
    int ydrag = 0;
    boolean clicked;
    int whichClicked = 0;
    private boolean pressed = false;

    public IClick() {
        clicked = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        clicked = true;
        whichClicked = e.getButton();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        eventX = e.getX();
        eventY = e.getY();
    }

    public void mouseDragged(MouseEvent e) {
        pressed = true;

        x = e.getX();
        y = e.getY();
        newDrag(x-eventX, y-eventY);

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        clicked = false;
        pressed = false;
        whichClicked = e.getButton();

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public boolean getClicked() {
        if (clicked) {
            resetClicked();
            return true;
        } else {
            return false;
        }

    }

    public void resetClicked() {
        clicked = false;
    }

    public int getEX() {
        return eventX;
    }

    public int getEY() {
        return eventY;
    }

    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean getPress() {
        return pressed;
    }

    private void newDrag(int xslide, int yslide) {
        xdrag = xslide;
        ydrag = yslide;
    }

    public int getXDrag() {
        return xdrag;
    }

    public int getYDrag() {
        return ydrag;
    }

    public void resetDrag() {
        xdrag = 0;
        ydrag = 0;
        eventX = getX();
        eventY = getY();
    }

    public boolean getDragged() {
        return !(xdrag == 0 && ydrag == 0);
    }

}
