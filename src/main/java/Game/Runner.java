package Game;

import Graphics.MenuPanel;
import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatDraculaContrastIJTheme;

public class    Runner {
    public static void main(String[] args) {
        FlatNordIJTheme.setup(); //setup theme
        Initialize.init();
        MenuPanel menuPanel = new MenuPanel();
    }
}
