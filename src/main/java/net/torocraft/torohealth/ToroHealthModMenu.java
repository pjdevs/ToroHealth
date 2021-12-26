package net.torocraft.torohealth;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.torocraft.torohealth.gui.ToroHealthOptionsScreen;

public class ToroHealthModMenu implements ModMenuApi {
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return ToroHealthOptionsScreen::new;
	}
}