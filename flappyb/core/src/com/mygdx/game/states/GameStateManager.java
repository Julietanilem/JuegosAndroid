package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

//Clase para administrar todos los estados del juego.
public class GameStateManager {
    //Pila de estados
    private Stack<State> states;

    public GameStateManager (){
        states = new Stack<State>();
    }

    //Aregar estados
    public void push (State state){
        states.push(state);
    }

    //Eliminar último estado
    public void pop(){
        states.pop().dispose();
    }

    //Ingresar remplazando algún estado ya puesto
    public void set(State state){
        states.pop().dispose();
        states.push(state);
    }

    public void update(float dt){
        states.peek().update(dt); //Obtiene el estado actual y actualiza
    }

    public void render (SpriteBatch sb){ //sb- Contenedor que almacena los sprites.
        states.peek().render(sb);
    }
}
