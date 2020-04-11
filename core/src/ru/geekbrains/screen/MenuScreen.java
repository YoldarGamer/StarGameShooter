package ru.geekbrains.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.BaseScreen;

public class MenuScreen extends BaseScreen {

    private Texture img;
    private Texture ship;
    private Vector2 pos;
    private Vector2 v;
    private float rotate;

    @Override
    public void show() {
        super.show();
        img = new Texture("MainBG.jpg");
        ship = new Texture("ship.png");
        pos = new Vector2();
        v = new Vector2(0.0f,0.0f);
        rotate = 0;
    }

    @Override
    public void render(float delta) {
        update(delta);
        rotate += delta*10;
        draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        pos.set(screenX,Gdx.graphics.getHeight() - screenY);
        return false;
    }

    private void update (float delta){
        pos.add(v);
    }

    private void draw(){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, 0, 0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.draw(new TextureRegion(ship), pos.x, pos.y,50,50,100,100,1,1,rotate);
        batch.end();
    }
}
