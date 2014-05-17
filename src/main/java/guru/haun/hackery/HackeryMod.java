package guru.haun.hackery;

import java.util.EnumMap;

import org.apache.logging.log4j.Logger;

import guru.haun.hackery.blocks.EmeraldGlassBlock;
import guru.haun.hackery.blocks.GlitchBlock;
import guru.haun.hackery.blocks.GlitchCoreBlock;
import guru.haun.hackery.blocks.GuildedRedstoneBlock;
import guru.haun.hackery.blocks.TraceAnalyzerBlock;
import guru.haun.hackery.blocks.TraceAnalyzerTE;
import guru.haun.hackery.blocks.gui.GuiHandler;
import guru.haun.hackery.exploits.CrashExploit;
import guru.haun.hackery.exploits.RandomTpExploit;
import guru.haun.hackery.items.ExploitItem;
import guru.haun.hackery.items.GlitchHarvester;
import guru.haun.hackery.material.GlitchMaterial;
import guru.haun.hackery.worldgen.GlitchPlacer;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;


@Mod(modid="hackery", name="Hackers", version="modjam")
public class HackeryMod {
	
	@Instance("hackery")
	public static HackeryMod instance;
	
	@SidedProxy(clientSide = "guru.haun.hackery.client.HackeryClientProxy", serverSide = "guru.haun.hackery.HackeryCommonProxy")
	public static HackeryCommonProxy proxy;
	
	public static Logger logger;
	
	public static EnumMap<Side,FMLEmbeddedChannel> channel;
	
	//Glitch Blocks
	public static GlitchMaterial		matGlitch			= new GlitchMaterial(MapColor.purpleColor);
	public static GlitchBlock 			blockGlitch			= new GlitchBlock(matGlitch);
	public static GlitchCoreBlock		blockGlitchCore		= new GlitchCoreBlock(matGlitch);
	
	public static TraceAnalyzerBlock	blockTraceAnalyzer	= new TraceAnalyzerBlock(Material.iron);
	public static GuildedRedstoneBlock	blockGuildedRedstone = new GuildedRedstoneBlock();
	public static EmeraldGlassBlock		blockEmeraldGlass	= new EmeraldGlassBlock();
	
	//Items
	public static GlitchHarvester		glitchHarvester		= new GlitchHarvester();
	public static ExploitItem			itemExploit			= new ExploitItem();
		
	
	public static CreativeTabs creativetab = new CreativeTabs("tabHackery") {
		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(HackeryMod.blockGlitchCore);
		}
	};
	

	@EventHandler
	public void HackeryPreInit(FMLPreInitializationEvent e){
		
		this.logger = e.getModLog();
		proxy.registerRenderThings();
		proxy.registerKeyBinds();
		
		channel = NetworkRegistry.INSTANCE.newChannel("hackery", new HacketHandler());
		
		GameRegistry	.registerBlock(blockGlitchCore, "blockGlitchCore");
		GameRegistry	.registerBlock(blockGlitch, "blockGlitch");

		blockGlitchCore	.setCreativeTab(creativetab);
		blockGlitch		.setCreativeTab(creativetab);
		
		GameRegistry	.registerBlock(blockGuildedRedstone, "guildedRedstone");
		blockGuildedRedstone.registerRecipie();
		blockGuildedRedstone.setCreativeTab(creativetab);
		GameRegistry	.registerBlock(blockEmeraldGlass, "emeraldGlass");
		blockEmeraldGlass.registerRecipie();
		blockEmeraldGlass.setCreativeTab(creativetab);
		
		GameRegistry	.registerBlock(blockTraceAnalyzer,"traceAnalyzer");
		blockTraceAnalyzer.setCreativeTab(creativetab);
		blockTraceAnalyzer.registerRecipie();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		
		GameRegistry	.registerTileEntity(TraceAnalyzerTE.class, "tileEntitiyTraceAnalyzer");
		
		GameRegistry	.registerWorldGenerator(new GlitchPlacer(), 0);
		
		GameRegistry	.registerItem(glitchHarvester, "glitchHarvester");
			glitchHarvester.registerRecipie();
		
		glitchHarvester	.setCreativeTab(creativetab);
		
		ExploitUtils.addExploit(new RandomTpExploit());
		ExploitUtils.addExploit(new CrashExploit());
		GameRegistry	.registerItem(itemExploit, "itemExploit");
		itemExploit.setCreativeTab(creativetab);
		
		HackPotions.register();
		
	}
	
	
	@EventHandler
	public void HackeryInit(FMLInitializationEvent e){
		
	}
	
	
	@EventHandler
	public void HackeryPostInit(FMLPostInitializationEvent e){
		
	}
	
	
}
