package garden.hestia.feed_the_bees.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(BeeEntity.class)
public abstract class BeeEntityMixin extends AnimalEntity {
	protected BeeEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
		super(entityType, world);
	}

	@Shadow
	protected abstract void setHasStung(boolean hasStung);

	@ModifyArgs(method = "mobTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/BeeEntity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"))
	protected void mobTick(Args args)
	{
		if (args.get(0) == DamageSource.GENERIC)
		{
			args.set(1, 1.0F);
			this.setHasStung(false);
		}
	}


}
