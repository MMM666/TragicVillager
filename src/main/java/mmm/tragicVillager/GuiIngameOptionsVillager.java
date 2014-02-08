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
		optionList = new GuiModOptionListVillager(this);
		optionList.registerScrollButtons(buttonList, 7, 8);
		buttonList.add(new GuiButton(200,
				width / 2 - 100, height / 6 + 168,
				I18n.format("gui.done", new Object[0])));
	}

	@Override
	protected void actionPerformed(GuiButton p_146284_1_) {
		if (p_146284_1_.enabled) {
			if (p_146284_1_.id == 200) {
				// データのセーブ
//				this.field_146297_k.gameSettings.saveOptions();
				TragicVillager.saveConf();
				mc.displayGuiScreen(this.fowner);
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

		drawDefaultBackground();
		optionList.drawScreen(par1, par2, par3);
		drawCenteredString(fontRendererObj, this.title, width / 2, 15, 0xFFFFFF);
		fontRendererObj.drawSplitString(optionList.getOptionText(), offset, shifty + 10, rightSide, 0xDDDDDD);

		super.drawScreen(par1, par2, par3);
	}

	public FontRenderer getFontRenderer() {
		return fontRendererObj;
	}

}
