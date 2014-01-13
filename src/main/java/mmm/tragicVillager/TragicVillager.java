package mmm.tragicVillager;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLModDisabledEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * 初期化処理とか。
 *
 */
@Mod(
		modid	= "tragicVillager",
		name	= "TragicVillager",
		canBeDeactivated = true,
		guiFactory = "mmm.tragicVillager.TragicVillagerClient",
		version	= "1.7.x-srg-1"
		)
public class TragicVillager {

	public static boolean dropTrade;
	public static boolean dropFlesh;
	public static boolean dropExp;
	
	public static Item itemVillagerRaw;
	public static Item itemVillagerCooked;
	
	protected static Configuration config = null;


	@Mod.EventHandler
	public void disabledEvent(FMLModDisabledEvent pEvent) {
		// disableのイベントは未実装？
		System.out.println("disabledEvent");
		
	}

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent pEvent) {
		// コンフィグの読み出し
		config = new Configuration(pEvent.getSuggestedConfigurationFile());
		config.load();
		dropTrade	= config.get("Villager", "dropTrade", true).getBoolean(true);
		dropFlesh	= config.get("Villager", "dropFlesh", true).getBoolean(true);
		dropExp		= config.get("Villager", "dropExp", true).getBoolean(true);
		config.save();
		
		// アイテムやブロックの宣言はこっちらしい。
		// 村人虐殺どろっぴ
		itemVillagerRaw = (new ItemVillagerFlesh(3, 0.3F, true)).setUnlocalizedName("villagerRaw").setTextureName("mmm:villagerRaw");
		itemVillagerCooked = (new ItemVillagerFlesh(8, 0.8F, true)).setUnlocalizedName("villagerCooked").setTextureName("mmm:villagerCooked");
		GameRegistry.registerItem(itemVillagerRaw, "villagerRaw");
		GameRegistry.registerItem(itemVillagerCooked, "villagerCooked");
	}

	public static void saveConf() {
		if (config == null) return;
		// 現在の設定値を保存
		config.get("Villager", "dropTrade", true).set(dropTrade);
		config.get("Villager", "dropFlesh", true).set(dropFlesh);
		config.get("Villager", "dropExp", true).set(dropExp);
		config.save();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent pEvent) {
		// 一応ドキュメント上ではここでレシピとかを宣言するらしい。
		GameRegistry.addSmelting(itemVillagerRaw, new ItemStack(itemVillagerCooked), 0.35F);
		MinecraftForge.EVENT_BUS.register(new VillagerEventHandler());
	}

}
