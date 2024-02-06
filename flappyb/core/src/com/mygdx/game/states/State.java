package com.mygdx.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

//Definición de un estado -- Parte del juego
public abstract class State {
    protected OrthographicCamera camera; //Ver solo una parte
    protected Vector3 mouse; //Donde se da el click
    protected GameStateManager  gsm; //Manejar el estado del juego

    protected State(GameStateManager gsm){
        this.gsm = gsm;
        camera = new OrthographicCamera();
        mouse = new Vector3();
    }

    protected abstract void handleInput();//Ejecutar entradas
    //Componen el loop game
    public abstract void update(float dt); //Actualiza el juego todo el tiempo para saber su estado
    public abstract void render(SpriteBatch sb); //Carga las partes visuales

    public abstract void dispose();

}
