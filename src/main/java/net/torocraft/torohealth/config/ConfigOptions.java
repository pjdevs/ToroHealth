package net.torocraft.torohealth.config;

import java.io.File;
import java.io.FileWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.minecraft.client.option.CyclingOption;
import net.minecraft.client.option.Option;
import net.minecraft.text.TranslatableText;
import net.torocraft.torohealth.ToroHealth;
import net.torocraft.torohealth.config.loader.ConfigFolder;

public class ConfigOptions {

    private final Gson gson;
    private final Config config;
    private final File file;

    public ConfigOptions(Config config) {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.config = config;
        this.file = new File(ConfigFolder.get(), ToroHealth.MODID + ".json");
    }

    private CyclingOption<Config.Mode> inWorldModeOption() {
        return CyclingOption.create(
            "config.inWorld.mode",
            Config.Mode.class.getEnumConstants(),
            value -> new TranslatableText("config.inWorld.mode.".concat(value.toString())),
            ignored -> config.inWorld.mode,
            (ignored, option, value) -> config.inWorld.mode = value
        );
    }

    public void save() {
        try (FileWriter writer = new FileWriter(this.file)) {
            writer.write(this.gson.toJson(this.config));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public Option[] asOptions(Config config) {
		return new Option[] {
            this.inWorldModeOption()
        };
	}
}
