package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Flappy;

public class MenuState extends State {
    //Imagenes. Sprites.
    private Texture background;
    private Texture playButton;
    public MenuState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, Flappy.WIDTH/2, Flappy.HEIGHT/2);
        background = new Texture("bg.png");
        playButton = new Texture("playbtn.png");
    }

    @Override
    public void handleInput() {
        //Evalua interacciones con él
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        //Todo el tiempo esta revisando que hay en la pantalla
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin(); // inicializar
        //DIBUJAR
        //que, coordenadas px, tamaños px. Esquina inferior izquierda como base (0,0).
        sb.draw(background, 0,0);
        sb.draw(playButton, camera.position.x - playButton.getWidth()/2, camera.position.y);
        sb.end(); //finalizar
    }

    @Override
    public void dispose() {
        //Eliminar de la memoria esto cuando no se use
        background.dispose();
        playButton.dispose();
        System.out.println("Menu state dispose");
    }
}
