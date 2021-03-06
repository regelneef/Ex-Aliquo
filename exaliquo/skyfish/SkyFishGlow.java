package exaliquo.skyfish; 

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import mariculture.api.fishery.EnumRodQuality;
import mariculture.api.fishery.Fishing;
import mariculture.fishery.fish.FishGlow;
import exaliquo.SkyFish;

public class SkyFishGlow extends FishGlow
{
	public SkyFishGlow(int id)
	{
		super(id);
	}
	
	@Override
	public String getSpecies()
	{
		return this.getClass().getSimpleName().toLowerCase().substring(7);
	}
	
	@Override
	public boolean canCatch(Random rand, World world, int x, int y, int z, EnumRodQuality quality)
	{
		if(isWorldCorrect(world) && quality.getRank() >= getRodNeeded().getRank() && rand.nextInt(100) < getCatchChance())
		{
			return Fishing.fishHelper.biomeMatches(world.getWorldChunkManager().getBiomeGenAt(x, z), getGroup().getBiomes());
		}
		return false;
	}
	
	@Override
	public boolean canLive(World world, int x, int y, int z) {
		return getGroup().canLive(world, x, y, z);
	}
	
	@Override
	public void onConsumed(World world, EntityPlayer player)
	{
		if (SkyFish.tastyfish)
		{
			player.getFoodStats().addStats(1, 0.05F);
			world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
		}
		else
		{
			player.getFoodStats().addStats(1, 0.3F);
			world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
		}
	}
}