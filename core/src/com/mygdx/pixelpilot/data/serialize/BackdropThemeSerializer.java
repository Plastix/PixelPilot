package com.mygdx.pixelpilot.data.serialize;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.scalar.ScalarSerializer;
import com.mygdx.pixelpilot.effect.background.theme.BackdropFactory;
import com.mygdx.pixelpilot.effect.background.theme.BackdropTheme;

public class BackdropThemeSerializer implements ScalarSerializer<BackdropTheme> {

    @Override
    public String write(BackdropTheme object) throws YamlException {
        return null;
    }

    @Override
    public BackdropTheme read(String value) throws YamlException {
        for(BackdropFactory.ThemePreset preset : BackdropFactory.ThemePreset.values()){
            if(preset.name().toLowerCase().equals(value.toLowerCase())){
                return BackdropFactory.buildTheme(preset);
            }
        }
        throw new YamlException("No ThemePreset with name " + value + " found!");
    }
}
