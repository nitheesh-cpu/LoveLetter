package Game;

import Graphics.MenuPanel;
import com.formdev.flatlaf.intellijthemes.FlatGruvboxDarkHardIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatDraculaContrastIJTheme;

public class Runner {
    public static void main(String[] args) {
        FlatDraculaContrastIJTheme.setup(); //setup theme
        MenuPanel menuPanel = new MenuPanel();
    }
}
