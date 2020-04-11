package com.example.memorygame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Button;

import androidx.appcompat.widget.AppCompatDrawableManager;

public class GameCard extends androidx.appcompat.widget.AppCompatButton {

    private Boolean isTurnedFront = false;

    public Boolean getCanBeTurned() {
        return canBeTurned;
    }

    public void setCanBeTurned(Boolean canBeTurned) {
        this.canBeTurned = canBeTurned;
    }

    /*
     * Kartların arka plan resmidir.
     */
    private int backImageId;

    private int frontImageId;

    private Boolean canBeTurned = true;

    private Drawable drawableFrontImage;

    public Boolean getTurnedFront() {
        return isTurnedFront;
    }

    public void setTurnedFront(Boolean turnedFront) {
        isTurnedFront = turnedFront;
    }

    public int getBackImageId() {
        return backImageId;
    }

    public void setBackImageId(int backImageId) {
        this.backImageId = backImageId;
    }

    public int getFrontImageId() {
        return frontImageId;
    }

    public void setFrontImageId(int frontImageId) {
        this.frontImageId = frontImageId;
    }

    public Drawable getDrawableFrontImage() {
        return drawableFrontImage;
    }

    public void setDrawableFrontImage(Drawable drawableFrontImage) {
        this.drawableFrontImage = drawableFrontImage;
    }

    public Drawable getDrawableBackImage() {
        return drawableBackImage;
    }

    public void setDrawableBackImage(Drawable drawableBackImage) {
        this.drawableBackImage = drawableBackImage;
    }

    private Drawable drawableBackImage;

    /**
     * @param context
     * @param id      resim id
     */
    @SuppressLint("RestrictedApi")
    public GameCard(Context context, int id) {
        super(context);
        setId(id);
        backImageId = R.drawable.back_image;

        frontImageId = 0;
        switch (id % 8) {
            case 1:
                frontImageId = R.drawable.c1;
                break;
            case 2:
                frontImageId = R.drawable.c2;
                break;
            case 3:
                frontImageId = R.drawable.c3;
                break;
            case 4:
                frontImageId = R.drawable.c4;
                break;
            case 5:
                frontImageId = R.drawable.c5;
                break;
            case 6:
                frontImageId = R.drawable.c6;
                break;
            case 7:
                frontImageId = R.drawable.c7;
                break;
            case 0:
                frontImageId = R.drawable.c8;
                break;
            default:
                break;
        }

        drawableFrontImage= AppCompatDrawableManager.get().getDrawable(context, frontImageId);
        drawableBackImage = AppCompatDrawableManager.get().getDrawable(context, backImageId);
        setBackground(drawableBackImage);
    }

    public void turnCard () {
        if (!canBeTurned) return;
        if (isTurnedFront) {
            isTurnedFront = false;
            this.setBackground(drawableBackImage);
        } else {
            isTurnedFront = true;
            this.setBackground(drawableFrontImage);
        }
    }
}
