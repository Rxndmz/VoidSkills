package me.rxndmz.voidskills.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class XPGained extends Event {

    private static final HandlerList handlers = new HandlerList();

    private int amount;
    private String type;
    private Player player;

    public XPGained(int amount, String type, Player player) {
        this.amount = amount;
        this.type = type.toUpperCase();
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() { return handlers; }

}