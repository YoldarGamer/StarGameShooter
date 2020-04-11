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
    private Vector2 tmp;
    private float rotate;
    private float distance;

    @Override
    public void show() {
        super.show();
        img = new Texture("MainBG.jpg");
        ship = new Texture("ship.png");
        pos = new Vector2();
        v = new Vector2(0.0f,0.0f);
        rotate = 0;
        tmp = new Vector2();
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
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        pos.set(touch);
        return false;
    }

    private void update (float delta){
//        tmp.set(touch);
        distance = tmp.sub(pos).len();
        if ( distance > 0.5f) {pos.add(v);}
    }

    private void draw(){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, -1f, -1f, 2f,2f);
  //      batch.draw(new TextureRegion(ship), pos.x, pos.y,50,50,100,100,1,1,rotate);
        batch.draw(ship, pos.x, pos.y, 0.25f, 0.25f);
        batch.end();
    }
}
