package orb.p.listeners;



import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class IPress implements KeyListener {

    boolean left, right, up, down, delete, clear, fill, esc, space;
    int kUp = 38, kLeft = 37, kDown = 40, kRight = 39, kDelete = 127, kQ=81, kE=69, kEsc=27, kSpace = 32;

    //keycodes:
    //87 = w
    //65 = a
    //83 = s
    //68 = d
    //74=j
    //107 = numpad +
    //109 = numpad - 
    public IPress() {
        left = false;
        right = false;
        up = false;
        down = false;
        delete = false;
        clear = false;
        fill = false;
        esc = false;
        space = false;

    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == kUp || e.getKeyCode() == kDown || e.getKeyCode() == kLeft || e.getKeyCode() == kRight) {
            directional(e.getKeyCode());
        }
        if (e.getKeyCode() == kDelete) {
            delete = true;         
        }
        if (e.getKeyCode() == kQ) {
            clear = true;         
        }
        if (e.getKeyCode() == kE) {
            fill = true;         
        }
        if(e.getKeyCode() == kEsc){
            esc = true;
        }
        if(e.getKeyCode() == kSpace){
            space = true;
        }
       // System.out.println(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == kLeft || e.getKeyCode() == kRight || e.getKeyCode() == kDown || e.getKeyCode() == kUp) {
            directionalRelease(e.getKeyCode());
        }

    }

    private void directional(int dir) {
        if (dir == kLeft) {
            left = true;
        }
        if (dir == kUp) {
            up = true;
        }
        if (dir == kRight) {
            right = true;
        }
        if (dir == kDown) {
            down = true;
        }
    }

    public boolean getKeyPressed(String actionName) {
        switch (actionName) {
            case "left":
                return left;
            case "right":
                return right;
            case "up":
                return up;
            case "down":
                return down;
            case "delete":
                if (delete) {
                    delete = false;
                    return true;
                } else {
                    return false;
                }
            case "clear":
                if (clear) {
                    clear = false;
                    return true;
                } else {
                    return false;
                }
            case "fill":
                if (fill) {
                    fill = false;
                    return true;
                } else {
                    return false;
                }
            case "esc":
                if (esc) {
                    esc = false;                 
                    return true;
                } else {
                    return false;
                }
            case "space":
                if (space) {
                    space = false;                 
                    return true;
                } else {
                    return false;
                }

        }

        return false;
    }

    private void directionalRelease(int dir) {
        if (dir == kLeft) {
            left = false;
        }
        if (dir == kUp) {
            up = false;
        }
        if (dir == kRight) {
            right = false;
        }
        if (dir == kDown) {
            down = false;
        }
    }

}
