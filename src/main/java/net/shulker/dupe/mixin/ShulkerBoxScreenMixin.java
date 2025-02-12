package net.shulker.dupe.mixin;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.ShulkerBoxScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.shulker.dupe.SharedVariables.*;

@Mixin(ShulkerBoxScreen.class)
public class ShulkerBoxScreenMixin extends Screen {
    public ShulkerBoxScreenMixin(Text title) {
        super(title);
    }

    @Inject(at = @At("TAIL"), method = "render(Lnet/minecraft/client/util/math/MatrixStack;IIF)V")
    public void renderScreen(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        this.addDrawableChild(new ButtonWidget(232, 60, 50, 20, Text.of("Dupe"), (button) -> {
            if (shouldDupeAll) shouldDupeAll = false;
            shouldDupe = true;
        }));

        this.addDrawableChild(new ButtonWidget(285, 60, 50, 20, Text.of("Dupe All"), (button) -> {
            if (shouldDupe) shouldDupe = false;
            shouldDupeAll = true;
        }));
    }
}
