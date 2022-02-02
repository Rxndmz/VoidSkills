package me.rxndmz.voidskills.Skills.Farming;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.scheduler.BukkitRunnable;

public class ReplaceFarm extends BukkitRunnable {

    Block block;
    Material mat;
    Ageable age;

    public ReplaceFarm(Block block, Material mat) {
        this.block = block;
        if (block.getBlockData() instanceof Ageable) {
            this.age = (Ageable) block.getBlockData();
        }
        this.mat = mat;
    }

    @Override
    public void run(){
        if (age != null) {
            age.setAge(age.getMaximumAge());
            block.setBlockData(age);
        } else {
            block.setType(mat);
        }
        block.getWorld().playSound(block.getLocation(), Sound.BLOCK_GRASS_PLACE, 1.00f, 1.00f);
    }

}
