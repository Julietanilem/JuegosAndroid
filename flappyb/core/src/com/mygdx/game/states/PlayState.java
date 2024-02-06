package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Flappy;
import com.mygdx.game.sprites.Bird;
import com.mygdx.game.sprites.Tubo;

import sun.awt.geom.AreaOp;

public class PlayState extends State {
    private static final int ESPACIO = 125;
    private static final int TUBOS = 4;
    private static final int GROUND_Y_OFFSET = -30;
    private Array<Tubo> tubos;
    private Bird bird;

    private Texture back;

    private Texture ground;
    private Vector2 groundPos1;
    private Vector2 groundPos2;
    public PlayState(GameStateManager gsm) {
        super(gsm);
       bird = new Bird(50, 320);
        //ViewPort. La parte que se ve
        camera.setToOrtho(false, Flappy.WIDTH/2, Flappy.HEIGHT/2);
        back = new Texture("bg.png");
        tubos = new Array<>();
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(camera.position.x - (camera.viewportWidth/2), GROUND_Y_OFFSET);
        groundPos2 = new Vector2(camera.position.x - (camera.viewportWidth/2) + ground.getWidth(), GROUND_Y_OFFSET);
        for (int i = 1; i<=TUBOS; i++) {
            tubos.add(new Tubo(i*(ESPACIO + Tubo.ANCHO)));
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        bird.update(dt);
        //Posición va siguiendo al pajarito
        camera.position.x = bird.getPos().x + 80;
        for(int i = 0 ; i < tubos.size; i++ ){
            Tubo tubo = tubos.get(i);
            if (camera.position.x - (camera.viewportWidth/2) >
                tubo.getPosTopTubo().x + tubo.getTopTubo().getWidth()){
                tubo.reposicion(tubo.getPosTopTubo().x + ((Tubo.ANCHO + ESPACIO) * TUBOS));
            }
            if (tubo.collides(bird.getContorno())){
                gsm.set( new PlayState(gsm));
            }
        }
        if(bird.getPos().y <= ground.getHeight() + GROUND_Y_OFFSET){
            gsm.set(new PlayState(gsm));
        }
        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined); //Solo dibuja lo que se ve
        sb.begin();
        //Escalar bien la imagen, mandandole la proyeccion de la camara
        sb.draw(back, camera.position.x - (camera.viewportWidth/2), camera.position.y - (camera.viewportHeight/2));
        sb.draw(bird.getBird(), bird.getPos().x, bird.getPos().y);

        for(Tubo tubo : tubos){
            sb.draw(tubo.getTopTubo(), tubo.getPosTopTubo().x,tubo.getPosTopTubo().y );
            sb.draw(tubo.getBottomTubo(), tubo.getPosBotTubo().x,tubo.getPosBotTubo().y );
        }
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        sb.end();

    }

    @Override
    public void dispose() {
        back.dispose();
        ground.dispose();
        bird.dispose();
        for(Tubo tubo : tubos){
            tubo.dispose();
        }
        System.out.println("Play State dispose");
    }

    private void updateGround(){
        if(camera.position.x-(camera.viewportWidth/2) > groundPos1.x + ground.getWidth()){
            groundPos1.add(ground.getWidth() * 2, 0);
        }
        if(camera.position.x-(camera.viewportWidth/2) > groundPos2.x + ground.getWidth()){
            groundPos2.add(ground.getWidth() * 2, 0);
        }
    }
}
