package mmm.tragicVillager;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

/**
 * 吾己が人の命を絶ち、そのしゝむらを食などする物は、かくぞある。
 *
 */
public class ItemVillagerFlesh extends ItemFood {

	public ItemVillagerFlesh(int phealAmount, float psaturationModifier, boolean isWolfsFavoriteMeat) {
		super(phealAmount, psaturationModifier, isWolfsFavoriteMeat);
	}

	@Override
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		if (par2World.isRemote) {
//			par3EntityPlayer.addChatMessage("message.xeu.eatVillager");
			par3EntityPlayer.func_146105_b(new ChatComponentTranslation("message.tragicVillager.eatVillager"));
		}
		return super.onEaten(par1ItemStack, par2World, par3EntityPlayer);
	}

}
