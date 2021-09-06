package game.swing;

import javax.swing.*;
import java.util.ArrayList;

public class PanelHandler {

    private static PanelHandler panelHandler = null;
    ArrayList<JPanel> jPanels = new ArrayList<>();

    public PanelHandler() {

    }
    public static PanelHandler getInstance(){
        if (panelHandler == null)
            panelHandler = new PanelHandler();

        return panelHandler;
    }
}

