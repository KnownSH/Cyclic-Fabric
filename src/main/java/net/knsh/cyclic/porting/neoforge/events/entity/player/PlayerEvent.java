package net.knsh.cyclic.porting.neoforge.events.entity.player;

import net.knsh.cyclic.porting.neoforge.events.entity.living.LivingEvent;
import net.minecraft.world.entity.player.Player;

public abstract class PlayerEvent extends LivingEvent {
    private final Player player;

    public PlayerEvent(Player player) {
        super(player);
        this.player = player;
    }

    @Override
    public Player getEntity() {
        return player;
    }
}
