package ru.geekbrains.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.BaseScreen;
import ru.geekbrains.exception.GameException;
import ru.geekbrains.math.Rect;
import ru.geekbrains.sprites.Background;
import ru.geekbrains.sprites.Ship;
import ru.geekbrains.sprites.Star;

public class MenuScreen extends BaseScreen {

    private static final int STAR_COUNT=256;

    private Texture bg;
    private Texture sp;
    private Background background;
    private Ship ship;
    private TextureAtlas atlas;
    private Star[] stars;

    @Override
    public void show() {
        super.show();
        //bg = new Texture("textures/bg.png");
        bg = new Texture("MainBG.jpg");
        sp = new Texture("ship.png");
        atlas = new TextureAtlas(Gdx.files.internal("textures/menuAtlas.tpack"));
        initSprites();
    }

    @Override
    public void render(float delta) {
        update(delta);
        draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        bg.dispose();
        sp.dispose();
        atlas.dispose();
        super.dispose();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        for (Star star : stars){
            star.resize(worldBounds);
        }
        ship.resize(worldBounds);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        ship.touchDown(touch,pointer,button);
        return false;
    }

    private void initSprites(){
        try {
            background = new Background(bg);
            stars = new Star[STAR_COUNT];
            for (int i=0;i<STAR_COUNT;i++){
                stars[i] = new Star(atlas);
            }
            ship = new Ship(sp);
        } catch (GameException e) {
            throw new RuntimeException(e);
        }
    }

    private void update (float delta){
        for (Star star : stars){
            star.update(delta);
        }
        ship.update(delta);
    }

    private void draw(){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (Star star : stars){
            star.draw(batch);
        }
        ship.draw(batch);
        batch.end();
    }
}
