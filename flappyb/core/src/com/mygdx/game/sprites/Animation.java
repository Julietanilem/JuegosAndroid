package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.utils.Array;

public class Animation {
    private Array<TextureRegion> frames;
    private float maxTiempo;
    private float tiempoActual;
    private int cuentaFrame;
    private int frame;

    public Animation(TextureRegion region,
                     int cuentaFrame,
                     float ciclo){
        frames  = new Array<>();
        int frameWidth = region.getRegionWidth() / cuentaFrame;
        for (int i  = 0; i<cuentaFrame ; i++){
            frames.add(new TextureRegion(region,i*frameWidth, 0, frameWidth, region.getRegionHeight()));
        }
        this.cuentaFrame = cuentaFrame;
        maxTiempo = ciclo / cuentaFrame;
        frame = 0;
    }

    public void update(float dt){
        tiempoActual +=dt;
        if(tiempoActual > maxTiempo){
            frame++;
            tiempoActual =0;
        }
        if(frame >= cuentaFrame){
            frame = 0;
        }
    }
    public TextureRegion getFrame(){
        return frames.get(frame);
    }
}
