package garden.hestia.feed_the_bees.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.entity.passive.PassiveEntity.m_eyazmlyb;

@Mixin(AnimalEntity.class)
public abstract class AnimalEntityMixin extends PassiveEntity {
	@Shadow
	protected abstract void eat(PlayerEntity player, Hand hand, ItemStack stack);

	protected AnimalEntityMixin(EntityType<? extends PassiveEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(method = "interactMob",
		  at = @At(value = "HEAD"), cancellable = true)
	public void interactMob(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir)
	{
		if ((Object) this instanceof BeeEntity)
		{
			ItemStack itemStack = player.getStackInHand(hand);
			if (itemStack.isOf(Items.SUGAR))
			{
				this.eat(player, hand, itemStack);
				cir.setReturnValue(ActionResult.success(this.world.isClient));
			}
		}
	}

}
