package guru.haun.hackery.client;

import guru.haun.hackery.HackeryCommonProxy;
import guru.haun.hackery.blocks.TraceAnalyzerTE;
import guru.haun.hackery.client.render.TraceAnalyzerRenderer;
import cpw.mods.fml.client.registry.ClientRegistry;

public class HackeryClientProxy extends HackeryCommonProxy {
	
	public void registerRenderThings(){
		ClientRegistry.bindTileEntitySpecialRenderer(TraceAnalyzerTE.class, new TraceAnalyzerRenderer());
	}
}
