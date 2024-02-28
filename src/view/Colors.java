package view;

import java.awt.Color;

public enum Colors {
    BACKGROUND(new Color(192, 214, 223)),
    BAR(new Color(61, 90, 128)),
    BUTTON(new Color(224, 236, 243)),
    WHITE(new Color(255, 255, 255));

    private final Color color;

    Colors(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
