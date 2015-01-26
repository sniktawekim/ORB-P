package orb.p.OnScreenObjects;



/**
 * @author MWatkins
 */
public class HudObject extends OnScreenObject {

    private String actionName;


    public HudObject(int xPos, int yPos, int xSize, int ySize, String imagePath, String aName) {
        super(xPos, yPos, xSize, ySize);
        actionName = aName;
        setGraphic(imagePath);
    }
    public HudObject(boolean visible, int xPos, int yPos, int xSize, int ySize, String imagePath, String aName) {
        super(xPos, yPos, xSize, ySize, visible);
        actionName = aName;
        setGraphic(imagePath);
    }

    public String getAction() {
        return actionName;
    }

    public boolean matches(String command) {
        return actionName.compareToIgnoreCase(command) == 0;
    }

    public boolean contains(String toLook) {
        return actionName.contains(toLook);
    }



}
