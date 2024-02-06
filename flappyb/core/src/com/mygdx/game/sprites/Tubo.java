package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tubo {

    private static final int FLUCTUACION = 130;
    private static final int ESPACIO  = 100 ;
    private static final int ALTURA_MIN = 120;

    public static final int ANCHO = 52;
    private Texture topTubo;
    private Texture bottomTubo;

    private Vector2 posTopTubo;
    private Vector2 posBotTubo;
    private Random rand;

    private Rectangle contornoArriba;
    private Rectangle contornoAbajo;

    public Tubo(float x){
        topTubo = new Texture("toptube.png");
        bottomTubo = new Texture("bottomtube.png");
        rand = new Random();
        //Posicionar
        posTopTubo = new Vector2(x, rand.nextInt(FLUCTUACION) + ESPACIO + ALTURA_MIN);
        posBotTubo = new Vector2(x,posTopTubo.y - ESPACIO - bottomTubo.getHeight());

        contornoArriba = new Rectangle(posTopTubo.x, posTopTubo.y, topTubo.getWidth(), topTubo.getHeight());
        contornoAbajo = new Rectangle(posBotTubo.x, posBotTubo.y, bottomTubo.getWidth(), bottomTubo.getHeight());

    }

    public void reposicion(float x){
        posTopTubo.set(x,rand.nextInt(FLUCTUACION) + ESPACIO + ALTURA_MIN );
        posBotTubo.set(x, posTopTubo.y - ESPACIO - bottomTubo.getHeight());
        contornoArriba.setPosition(posTopTubo.x, posTopTubo.y);
        contornoAbajo.setPosition(posBotTubo.x, posBotTubo.y);
    }

    //saber cuando hay colisión
    public boolean collides (Rectangle player){
        return player.overlaps(contornoAbajo) || player.overlaps(contornoArriba);
    }
    public Texture getBottomTubo() {
        return bottomTubo;
    }

    public Vector2 getPosTopTubo() {
        return posTopTubo;
    }

    public void setPosTopTubo(Vector2 posTopTubo) {
        this.posTopTubo = posTopTubo;
    }

    public void setBottomTubo(Texture bottomTubo) {
        this.bottomTubo = bottomTubo;
    }

    public Texture getTopTubo() {
        return topTubo;
    }

    public void setTopTubo(Texture topTubo) {
        this.topTubo = topTubo;
    }

    public Vector2 getPosBotTubo() {
        return posBotTubo;
    }

    public void setPosBotTubo(Vector2 posBotTubo) {
        this.posBotTubo = posBotTubo;
    }

    public void dispose(){
        topTubo.dispose();
        bottomTubo.dispose();
    }
}
