package me.rxndmz.voidskills.Skills.Mining;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

public class ReplaceOre extends BukkitRunnable {

    Block block;
    Material mat;

    public ReplaceOre(Block block, Material mat) {
        this.block = block;
        this.mat = mat;
    }

    @Override
    public void run(){
        block.setType(mat);
        block.getWorld().playSound(block.getLocation(), Sound.BLOCK_METAL_PLACE, 1.00f, 1.00f);
    }
}
