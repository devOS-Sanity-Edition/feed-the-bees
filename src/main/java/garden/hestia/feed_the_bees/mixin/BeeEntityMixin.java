package garden.hestia.feed_the_bees.mixin;

import net.minecraft.entity.passive.BeeEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(BeeEntity.class)
public abstract class BeeEntityMixin {

	@Shadow
	protected abstract void setHasStung(boolean hasStung);

	@ModifyArg(method = "mobTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/BeeEntity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z", ordinal = 1), index = 1)
	protected float regenerateStinger(float amount) {
		this.setHasStung(false);
		return 1.0F;
	}


}
