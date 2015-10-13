package com.wasteofplastic.beaconz;

import java.awt.geom.Point2D;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.generator.BlockPopulator;

public class BeaconPopulator extends BlockPopulator {
    private Beaconz plugin;
    
    /**
     * @param plugin
     */
    public BeaconPopulator(Beaconz plugin) {
	this.plugin = plugin;
    }

    @Override
    public void populate(World world, Random random, Chunk source) {
	// randomly place a beacon
	//Bukkit.getLogger().info("DEBUG: populator called");
	if (random.nextDouble() < plugin.getConfig().getDouble("world.distribution", 0.01D)) {
	    int x = random.nextInt(16);
	    int z = random.nextInt(16);
	    int y = source.getChunkSnapshot().getHighestBlockYAt(x, z) - 1;
	    Block b = source.getBlock(x, y, z);
	    if (b.getType().equals(Material.LEAVES) || b.getType().equals(Material.LEAVES_2)
		    || b.getType().equals(Material.LOG) || b.getType().equals(Material.LOG_2)) {
		return;
	    }
	    // Else make it into a beacon
	    //beacons.add(new Vector(x,y,z));
	    Bukkit.getLogger().info("DEBUG: made beacon at " + (source.getX() * 16 + x) + " " + y + " " + (source.getZ()*16 + z) );
	    b.setType(Material.BEACON);
	    // Add the capstone
	    b.getRelative(BlockFace.UP).setType(Material.OBSIDIAN);
	    // Create the pyramid
	    b = b.getRelative(BlockFace.DOWN);
	    
	    // All diamond blocks for now
	    b.setType(Material.DIAMOND_BLOCK);
	    b.getRelative(BlockFace.SOUTH).setType(Material.DIAMOND_BLOCK);
	    b.getRelative(BlockFace.SOUTH_EAST).setType(Material.DIAMOND_BLOCK);
	    b.getRelative(BlockFace.SOUTH_WEST).setType(Material.DIAMOND_BLOCK);
	    b.getRelative(BlockFace.EAST).setType(Material.DIAMOND_BLOCK);
	    b.getRelative(BlockFace.WEST).setType(Material.DIAMOND_BLOCK);
	    b.getRelative(BlockFace.NORTH).setType(Material.DIAMOND_BLOCK);
	    b.getRelative(BlockFace.NORTH_EAST).setType(Material.DIAMOND_BLOCK);
	    b.getRelative(BlockFace.NORTH_WEST).setType(Material.DIAMOND_BLOCK);
	    
	    // Register the beacon
	    plugin.getRegister().addBeacon(null, new Point2D.Double((source.getX() * 16 + x), (source.getZ()*16 + z)));
	}
    }
}