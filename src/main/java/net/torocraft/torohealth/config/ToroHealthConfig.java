package net.torocraft.torohealth.config;

import net.minecraft.client.option.CyclingOption;
import net.minecraft.client.option.Option;
import net.minecraft.text.TranslatableText;
import net.torocraft.torohealth.ToroHealth;

public class ToroHealthConfig {

    public static final CyclingOption<Config.Mode> INWORLD_MODE = CyclingOption.create(
        "config.inWorld.mode",
        Config.Mode.class.getEnumConstants(),
        value -> new TranslatableText("config.inWorld.mode.".concat(value.toString())),
        ignored -> ToroHealth.CONFIG.inWorld.mode,
        (ignored, option, value) -> ToroHealth.CONFIG.inWorld.mode = value
    );

	public static Option[] asOptions() {
		return new Option[] {
            INWORLD_MODE
        };
	}
}
