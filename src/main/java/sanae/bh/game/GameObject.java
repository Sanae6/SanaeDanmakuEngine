package sanae.bh.game;

import lombok.Getter;
import lombok.Setter;

public abstract class GameObject {
    @Getter @Setter int x,y,dx,dy,hp;
    boolean dead;
    protected abstract void show();
    protected abstract void act();
}