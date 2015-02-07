package orb.p.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import javax.swing.event.MouseInputAdapter;

public class IClick extends MouseInputAdapter {

    int eventX = 0;
    int eventY = 0;
    int x = 0;
    int y = 0;
    int xdrag = 0;
    int ydrag = 0;
    int clickedX;
    int clickedY;
    boolean clicked;
    int whichClicked = 0;
    boolean leftButton = false;
    boolean middleButton = false;
    boolean rightButton = false;
    int recentScroll = 0;

    public IClick() {
        clicked = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        /**
        if (!rightButton) {
            clicked = true;

            x = e.getX();
            y = e.getY();
            whichClicked = e.getButton();
        }*/
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftButton = true;
        }
        if (e.getButton() == MouseEvent.BUTTON2) {
            middleButton = true;
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            rightButton = true;
        }
        eventX = e.getX();
        eventY = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (rightButton) {
            x = e.getX();
            y = e.getY();
            newDrag(x - eventX, y - eventY);
        } else {
            //   System.out.println(leftButton+" " + middleButton + " " + rightButton);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // clicked = false;
        whichClicked = e.getButton();
        if (e.getButton() == MouseEvent.BUTTON1) {//left
            clicked = true;
            leftButton = false;
        }
        if (e.getButton() == MouseEvent.BUTTON2) {
            middleButton = false;
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            rightButton = false;
        }
        

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

    public boolean getLeft() {
        return leftButton;
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

    @Override
    public void mouseWheelMoved(MouseWheelEvent mwe) {
        recentScroll = mwe.getScrollAmount();
        getScroll();
    }

    public int getScroll() {
        int toreturn = recentScroll;
        System.out.println("SCROLLED WHEEL:");
        recentScroll = 0;
        return toreturn;
    }

    public boolean getRight() {
        return rightButton;
    }
}
