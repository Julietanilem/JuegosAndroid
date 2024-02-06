package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

//--Pajarito
public class Bird {
    //Valor de gravedad
    private static final int GRAVEDAD = -15;
    //Posición
    private Vector3 pos;
    //velocidad
    private Vector3 velocidad;

    //Velocidad de movimiento
    private static final int MOVIMIENTO = 100;

    private Sound aleteo;
    private Animation birdAnimation;
    private Texture texture;
    private Rectangle contorno;
    public Bird (int x, int y){
        pos = new Vector3(x, y, 0);
        velocidad = new Vector3(0, 0,0);
        texture = new Texture("birdanimation.png");
        birdAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);
        aleteo = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
        contorno = new Rectangle(x, y, texture.getWidth() /3, texture.getHeight());
    }

    public void update(float dt){
        birdAnimation.update(dt);
        if(pos.y >0) {
            velocidad.add(0, GRAVEDAD, 0);
        }
        velocidad.scl(dt);
        pos.add(MOVIMIENTO * dt, velocidad.y, 0);
        if(pos.y <0){
            pos.y = 0;
        }

        velocidad.scl(1/dt);//1 vez por segundo

        contorno.setPosition(pos.x, pos.y);
    }
    public Rectangle getContorno(){
        return contorno;
    }
    public Vector3 getPos() {
        return pos;
    }

    public TextureRegion getBird() {
        return birdAnimation.getFrame();
    }

    public void jump(){
        velocidad.y = 250;
        aleteo.play(0.5f);
    }

    public void dispose(){
        texture.dispose();
        aleteo.dispose();
    }
}
