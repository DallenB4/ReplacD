package glue_gunner4.replacd.mixin;

import glue_gunner4.replacd.config.ReplacdServerConfig;
import net.minecraft.server.command.ReloadCommand;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;

@Mixin(value = ReloadCommand.class)
public class MixinReloadCommand {

    @Inject(method = "tryReloadDataPacks", at = @At(value = "HEAD"))
    private static void tryReloadDataPacks(Collection<String> dataPacks, ServerCommandSource source, CallbackInfo ci) {
        ReplacdServerConfig.reloadConfig();
    }
}
