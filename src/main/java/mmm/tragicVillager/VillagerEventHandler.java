package mmm.tragicVillager;

import java.util.Random;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;

/**
 * 村人に対する追加記述。<br>
 * Forgeのイベントハンドラを使用。
 */
public class VillagerEventHandler {

	@SubscribeEvent
	public void onInterructEvent(EntityInteractEvent pEvent) {
		if (pEvent.entity.worldObj.isRemote) {
			return;
		}
		if (pEvent.target instanceof EntityVillager) {
			// ドナドナ
			EntityVillager lev = (EntityVillager)pEvent.target;
			EntityPlayer lep = pEvent.entityPlayer;
			
			if (lev.getLeashed() && lev.getLeashedToEntity() == lep) {
				lev.clearLeashed(true, !lep.capabilities.isCreativeMode);
				pEvent.setCanceled(true);
			} else {
				ItemStack itemstack = lep.inventory.getCurrentItem();
				
				if (itemstack != null && itemstack.getItem() == Items.lead) {
					lev.setLeashedToEntity(lep, true);
					--itemstack.stackSize;
					pEvent.setCanceled(true);
				}
			}
		}
	}

	@SubscribeEvent
	public void onLivingDropsEvent(LivingDropsEvent pEvent) {
		// Server側でしか呼ばれない
		if (pEvent.entity instanceof EntityVillager) {
			EntityVillager lev = (EntityVillager)pEvent.entity;
			Random lrand = new Random();
			int ldc;
			
			if (!lev.isChild()) {
				if (TragicVillager.dropTrade) {
					// 交易品のドロップ
					MerchantRecipeList lmrl = lev.getRecipes(null);
					if (lmrl != null) {
						for (Object lo : lmrl) {
							MerchantRecipe lml = (MerchantRecipe)lo;
							lev.entityDropItem(lml.getItemToSell(), 0.0F);
						}
					}
				}
				ldc = lrand.nextInt(3);
			} else {
				ldc = lrand.nextInt(2);
			}
			if (TragicVillager.dropFlesh) {
				// ？お肉のドロップ
				if (pEvent.lootingLevel > 0) {
					ldc += lrand.nextInt(pEvent.lootingLevel + 1);
				}
				Item litem = lev.isBurning() ? TragicVillager.itemVillagerCooked : TragicVillager.itemVillagerRaw;
				for (int l = 0; l < ldc; ++l) {
					lev.dropItem(litem, 1);
				}
			}
		}
	}

	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing pEvent) {
		if (pEvent.entity instanceof EntityVillager) {
			if (TragicVillager.dropExp) {
				// 村人に経験値を設定
				EntityLiving lev = (EntityLiving)pEvent.entity;
				// randじゃなくて期待値で。
				ReflectionHelper.setPrivateValue(EntityLiving.class, lev, 2, "experienceValue");
//				lev.experienceValue = 2;
			}
		}
	}

}
