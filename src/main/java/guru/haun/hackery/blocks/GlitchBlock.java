package guru.haun.hackery.blocks;

import guru.haun.hackery.HackeryMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;



public class GlitchBlock extends Block {

	public GlitchBlock(Material mat) {
		super(mat);
		setResistance(9000000);
		setHardness(4000);
		setBlockName("blockGlitch");
		setLightOpacity(3);
		setBlockTextureName("hackery:Glitch");
		setBlockBounds(.35F,.35F,.35F,.65F,.65F,.65F);
		setHarvestLevel("GlitchHarvester", 1);
	}
	
	public boolean removedByPlayer(World w, EntityPlayer p, int x, int y, int z){
		if(p.capabilities.isCreativeMode) return false;
		if(p.getHeldItem() == null) return true;
		if(p.getHeldItem().getItem() == null) return true;
		if(p.getHeldItem().getItem() == HackeryMod.glitchHarvester) return false;
		return false;
	}
	
	
	@SideOnly(Side.CLIENT)
	public boolean renderAsNormalBlock() {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return 1;
	}
	
	@SideOnly(Side.CLIENT)
	public boolean isOpaqueCube() {
		return false;
	}
	
	public boolean getBlocksMovement(IBlockAccess ba, int x, int y, int z){
		return true;
	}
	
}
