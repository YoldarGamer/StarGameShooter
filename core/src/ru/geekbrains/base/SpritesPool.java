package ru.geekbrains.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public abstract class SpritesPool <T extends Sprite> {

    protected final List<T> activetObjects = new ArrayList<>();
    protected final List<T> freeObjects = new ArrayList<>();
    protected abstract T newObject();

    public T obtain(){
         T object;
         if (freeObjects.isEmpty()){
             object = newObject();
         } else {
             object = freeObjects.remove(freeObjects.size() - 1);
         }
         activetObjects.add(object);
         System.out.println(this.getClass().getName() + " active/free: "+activetObjects.size()+"/"+freeObjects.size());
         return object;
    }

    public void updateActiveSprites (float delta) {
        for (Sprite sprite : activetObjects) {
            if(!sprite.isDestroyed()){
                sprite.update(delta);
            }
        }
    }

    public void drawActiveSprites (SpriteBatch batch) {
        for (Sprite sprite : activetObjects) {
            if(!sprite.isDestroyed()){
                sprite.draw(batch);
            }
        }
    }

    public void freeAllDestroyedActiveObjects() {
        for(int i=0; i< activetObjects.size(); i++){
            T sprite = activetObjects.get(i);
            if (sprite.isDestroyed()){
                free(sprite);
                i--;
                sprite.flushDestroy();
            }
        }
    }

    public List<T> getActivetObjects() {
        return activetObjects;
    }

    public void dispose() {
        activetObjects.clear();
        freeObjects.clear();
    }

    private void free(T object){
        if (activetObjects.remove(object)){
            freeObjects.add(object);
        }
        System.out.println(this.getClass().getName() + " active/free: "+activetObjects.size()+"/"+freeObjects.size());
    }

}
