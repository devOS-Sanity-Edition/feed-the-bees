package garden.hestia.feed_the_bees.mixin;

import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.BeeEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(BeeEntity.class)
public class BeeEntityMixin {
	@ModifyArgs(method = "createBeeAttributes",
	at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;add(Lnet/minecraft/entity/attribute/EntityAttribute;D)Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;"))
	private static void modifyBeeSpeed(Args args)
	{
		if (args.get(0) == EntityAttributes.GENERIC_MOVEMENT_SPEED)
		{
			args.set(1, (double) args.get(1) * 2.0F);
		}
	}

}
