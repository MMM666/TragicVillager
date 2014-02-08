package mmm.tragicVillager;

import net.minecraft.client.renderer.Tessellator;
import cpw.mods.fml.client.GuiScrollingList;

public class GuiModOptionListVillager extends GuiScrollingList {

	private GuiIngameOptionsVillager parent;
	protected String fConfigName[] = {
		"Drop Flesh",
		"Drop Trade",
		"Drop Exp"
	};
	protected String fConfigText[] = {
		"Villager is drop unknown flesh.",
		"Villager is drop Trade item.",
		"Villager is drop experience Orb."
	};
	public int fselect = -1;

	public GuiModOptionListVillager(GuiIngameOptionsVillager parent) {
		super(parent.mc, 150, parent.width, 32,
				parent.height - 65 + 4, 10, 35);
		this.parent = parent;
	}

	@Override
	protected int getSize() {
		return 3;
	}

	@Override
	protected void elementClicked(int index, boolean doubleClick) {
		fselect = index;
		if (doubleClick) {
			setOptionValue(index, !getOptionValue(index));
		}
	}

	@Override
	protected boolean isSelected(int index) {
		return fselect == index;
	}

	@Override
	protected void drawBackground() {
	}

	@Override
	protected void drawSlot(int var1, int var2, int var3, int var4, Tessellator var5) {
		String lname = "";
		String lvalue = "";
		int lcolor = 0xff2222;
		
		if (var1 < 3) {
			boolean lflag = getOptionValue(var1);
			lname = fConfigName[var1];
			lvalue = lflag ? "ENABLE" : "DISABLE";
			lcolor = lflag ? 0xffffff : 0xff2222;
		}
		this.parent.getFontRenderer().drawString(
				this.parent.getFontRenderer().trimStringToWidth(lname,
						listWidth - 10), this.left + 3, var3 + 7, lcolor);
		this.parent.getFontRenderer().drawString(
				this.parent.getFontRenderer().trimStringToWidth(lvalue,
						listWidth - 10), this.left + 3, var3 + 17, lcolor);
	}

	protected boolean getOptionValue(int pIndex) {
		switch (pIndex) {
		case 0:
			return TragicVillager.dropFlesh;
		case 1:
			return TragicVillager.dropTrade;
		case 2:
			return TragicVillager.dropExp;
		}
		return false;
	}

	protected void setOptionValue(int pIndex, boolean pValue) {
		switch (pIndex) {
		case 0:
			TragicVillager.dropFlesh = pValue;
			break;
		case 1:
			TragicVillager.dropTrade = pValue;
			break;
		case 2:
			TragicVillager.dropExp = pValue;
			break;
		}
	}

	protected String getOptionText() {
		if (fselect > -1 && fselect < 3) {
			return fConfigText[fselect];
		}
		return "";
	}

}
