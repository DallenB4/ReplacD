package glue_gunner4.replacd.mixin;


import com.mojang.datafixers.util.Pair;
import glue_gunner4.replacd.Replacd;
import glue_gunner4.replacd.config.ReplacdServerConfig;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;

@Mixin(value = ServerPlayNetworkHandler.class)
public class MixinServerPlayNetworkHandler {

    @Inject(method = "handleDecoratedMessage", at = @At(value = "HEAD"))
    private void handleDecoratedMessage(SignedMessage message, CallbackInfo ci) {
        message.signedBody().content = Replacd.ReplaceText(message.signedBody().content);
    }

}

