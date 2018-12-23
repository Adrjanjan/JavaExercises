package Lab10;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class XMasTree implements XmasShape{
    private static final int BRANCHES_NUMBER = 7;
    private List<Branch> branches = new ArrayList<>();

    public XMasTree() {
        for(int i=5; i<BRANCHES_NUMBER+5; ++i ) {
            this.branches.add(new Branch((int) (350-(16)*i),(int) i, (float)(i/10)));
        }
    }

    /**
     * @param x x-coordinate
     * @param y y-coordinate
     * @return true if inside tree false else
     */
    boolean insideTree(int x, int y){
        for (Branch b:branches) {
            if(b.insideBranch(x, y))
                return true;
        }
        return false;
    }

    @Override
    public void transform(Graphics2D g2d) {
        for (Branch b:branches) {
            b.render(g2d);
        }
    }

    @Override
    public void render(Graphics2D g2d) {
        for (Branch b:branches) {
            b.draw(g2d);
        }
    }
}
