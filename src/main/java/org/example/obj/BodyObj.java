package org.example.obj;

import org.example.GameWin;

import java.awt.*;

public class BodyObj extends GameObj {
    public BodyObj(Image img, int x, int y, GameWin frame) {
        super(img, x, y, frame);
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
    }
}