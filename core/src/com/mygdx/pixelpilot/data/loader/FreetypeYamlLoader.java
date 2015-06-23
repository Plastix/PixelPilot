package com.mygdx.pixelpilot.data.loader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.tools.bmfont.BitmapFontWriter;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.yamlbeans.YamlConfig;
import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.mygdx.pixelpilot.data.serialize.ColorSerializer;

import java.io.Reader;

public class FreetypeYamlLoader extends AsynchronousAssetLoader<BitmapFont, FreetypeYamlLoader.FreeTypeYamlLoaderParam> {
    public FreetypeYamlLoader(InternalFileHandleResolver resolver) {
        super(resolver);
    }

    private FreeTypeFontGenerator.FreeTypeFontParameter param;
    private String fontdir = "generated-fonts/";

    @Override
    public void loadAsync(AssetManager manager, String fileName, FileHandle file, FreeTypeYamlLoaderParam parameter) {
        FileHandle cachedFont = Gdx.files.local(fontdir + file.nameWithoutExtension() + ".fnt");
        if (!cachedFont.exists()) {
            YamlConfig yamlConfig = new YamlConfig();
            yamlConfig.setScalarSerializer(Color.class, new ColorSerializer());
            Reader yamlFile = Gdx.files.internal(fileName).reader();
            YamlReader reader = new YamlReader(yamlFile, yamlConfig);
            try {
                param = reader.read(FreeTypeFontGenerator.FreeTypeFontParameter.class);
            } catch (YamlException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public BitmapFont loadSync(AssetManager manager, String fileName, FileHandle file, FreeTypeYamlLoaderParam parameter) {
        FileHandle cachedFont = Gdx.files.local(fontdir + file.nameWithoutExtension() + ".fnt");
        if (cachedFont.exists()) {
            return new BitmapFont(cachedFont);
        } else {
            FreeTypeFontGenerator generator = manager.get(parameter.ttf, FreeTypeFontGenerator.class);
            BitmapFont font = generator.generateFont(param);

            // write out...
            BitmapFontWriter.setOutputFormat(BitmapFontWriter.OutputFormat.Text);
            Pixmap[] myPixmaps = new Pixmap[font.getRegions().size];

            for (int i = 0; i < font.getRegions().size; i++) {
                myPixmaps[i] = font.getRegions().get(i).getTexture().getTextureData().consumePixmap();
            }

            BitmapFontWriter.writeFont(
                    font.getData(),
                    myPixmaps,
                    cachedFont,
                    new BitmapFontWriter.FontInfo("regular", param.size)
            );

            return font;

            // todo: generator is never disposed of. Can't dispose here, as it may be needed by the next FreetypeYamlLoader.

        }
    }


    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, FreeTypeYamlLoaderParam parameter) {
        return null;
    }

    public static class FreeTypeYamlLoaderParam extends AssetLoaderParameters<BitmapFont> {
        public String ttf;

        public FreeTypeYamlLoaderParam(String ttf) {
            this.ttf = ttf;
        }
    }

}
