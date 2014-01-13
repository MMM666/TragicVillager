package mmm.tragicVillager;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class GuiIngameOptionsVillager extends GuiScreen {

	protected final GuiScreen fowner;
	protected String title = "TragicVillager Options";
	protected GuiModOptionListVillager optionList;


	public GuiIngameOptionsVillager(GuiScreen parentScreen) {
		super();
		
		fowner = parentScreen;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initGui() {
		this.optionList = new GuiModOptionListVillager(this);
		this.optionList.registerScrollButtons(this.field_146292_n, 7, 8);
		this.field_146292_n.add(new GuiButton(200,
				this.field_146294_l / 2 - 100, this.field_146295_m / 6 + 168,
				I18n.getStringParams("gui.done", new Object[0])));
	}

	@Override
	protected void func_146284_a(GuiButton p_146284_1_) {
		if (p_146284_1_.field_146124_l) {
			if (p_146284_1_.field_146127_k == 200) {
				// データのセーブ
//				this.field_146297_k.gameSettings.saveOptions();
				TragicVillager.saveConf();
				this.field_146297_k.func_147108_a(this.fowner);
			}
		}
	}

	/**
	 * Draws the screen and all the components in it.
	 */
	@Override
	public void drawScreen(int par1, int par2, float par3) {
		// force a non-transparent background
		int offset = 200; //this.listWidth  + 20;
		int rightSide = 300; //this.field_146294_l - offset - 20;
		int shifty = 35;

		this.func_146276_q_();
		this.optionList.drawScreen(par1, par2, par3);
		this.drawCenteredString(this.field_146289_q, this.title,
				this.field_146294_l / 2, 15, 0xFFFFFF);
		this.getFontRenderer().drawSplitString(optionList.getOptionText(), offset, shifty + 10, rightSide, 0xDDDDDD);

		super.drawScreen(par1, par2, par3);
	}

	FontRenderer getFontRenderer() {
		return field_146289_q;
	}

}
