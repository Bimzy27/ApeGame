package com.bimzygames.apegame.services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class FontService
{
    public final BitmapFont headerFont;
    public final BitmapFont bodyFont;

    public FontService()
    {
        headerFont = loadFont("Fonts/AncientMedium.ttf", 60);
        bodyFont = loadFont("Fonts/AncientMedium.ttf", 36);
    }

    public BitmapFont loadFont(String fontPath, int size) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(fontPath));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = size;
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose(); // Dispose the generator when done
        return font;
    }
}
