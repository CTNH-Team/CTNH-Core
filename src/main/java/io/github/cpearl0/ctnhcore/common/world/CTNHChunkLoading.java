package io.github.cpearl0.ctnhcore.common.world;

import io.github.cpearl0.ctnhcore.CTNHCore;
import io.github.cpearl0.ctnhcore.api.machine.feature.IDigitalMiner;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.world.ForgeChunkManager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public final class CTNHChunkLoading {

    private CTNHChunkLoading() {}

    /**
     * Registers a validation callback for Forge persistent chunk tickets.
     *
     * This prevents stale chunk tickets (e.g. from crashes) from being reinstated forever.
     */
    public static void registerValidationCallback() {
        ForgeChunkManager.setForcedChunkLoadingCallback(CTNHCore.MODID, CTNHChunkLoading::validateTickets);
    }

    private static void validateTickets(@NotNull ServerLevel level,
                                        @NotNull ForgeChunkManager.TicketHelper ticketHelper) {
        var owners = new ArrayList<>(ticketHelper.getBlockTickets().keySet());
        for (BlockPos ownerPos : owners) {
            if (!shouldKeepBlockOwner(level, ownerPos)) {
                ticketHelper.removeAllTickets(ownerPos);
            }
        }

        // We don't use entity-based tickets; if any exist under our modid, remove them.
        var entityOwners = new ArrayList<>(ticketHelper.getEntityTickets().keySet());
        for (var ownerId : entityOwners) {
            ticketHelper.removeAllTickets(ownerId);
        }
    }

    private static boolean shouldKeepBlockOwner(@NotNull ServerLevel level, @NotNull BlockPos ownerPos) {
        BlockEntity be;
        try {
            be = level.getBlockEntity(ownerPos);
        } catch (Throwable t) {
            return false;
        }
        if (!(be instanceof IMachineBlockEntity machineBlockEntity)) return false;

        MetaMachine metaMachine = machineBlockEntity.getMetaMachine();
        if (!(metaMachine instanceof IDigitalMiner miner)) return false;

        return miner.isWorkingEnabled() && !miner.getWorkLogic().isDone();
    }
}
