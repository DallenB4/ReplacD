package glue_gunner4.replacd.mixin;

import glue_gunner4.replacd.Replacd;
import net.minecraft.client.gui.screen.ChatScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = ChatScreen.class)
public class MixinChatScreen {
    @ModifyVariable(method = "sendMessage", at = @At(value = "HEAD"))
    public String injected(String chatText) {
        return Replacd.ReplaceText(chatText);
    }
}
