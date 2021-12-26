package net.torocraft.torohealth.gui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonListWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.OrderedText;
import net.minecraft.text.TranslatableText;
import net.torocraft.torohealth.ToroHealth;
import net.torocraft.torohealth.config.Config;
import net.torocraft.torohealth.config.ConfigOptions;

import java.util.List;

public class ToroHealthOptionsScreen extends GameOptionsScreen {

	private Screen previous;
	private ButtonListWidget list;
	private Config config;
	private ConfigOptions options;

	@SuppressWarnings("resource")
	public ToroHealthOptionsScreen(Screen previous) {
		super(previous, MinecraftClient.getInstance().options, new TranslatableText("config.title"));
		this.previous = previous;
		this.config = ToroHealth.CONFIG;
		this.options = new ConfigOptions(config);
	}
	

	protected void init() {
		this.list = new ButtonListWidget(this.client, this.width, this.height, 32, this.height - 32, 25);
		this.list.addAll(options.asOptions(config));
		this.addSelectableChild(this.list);
		this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height - 27, 200, 20, ScreenTexts.DONE, (button) -> {
			options.save();
			this.client.setScreen(this.previous);
		}));
	}	

	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
		this.renderBackground(matrices);
		this.list.render(matrices, mouseX, mouseY, delta);
		drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 5, 0xffffff);
		super.render(matrices, mouseX, mouseY, delta);
		List<OrderedText> tooltips = getHoveredButtonTooltip(this.list, mouseX, mouseY);
		if (tooltips != null) {
			this.renderOrderedTooltip(matrices, tooltips, mouseX, mouseY);
		}
	}

	public void removed() {
		// Juste cancel changes
	}
}