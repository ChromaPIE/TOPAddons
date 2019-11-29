package io.github.drmanganese.topaddons.addons.vanilla.blocks;

import io.github.drmanganese.topaddons.api.IBlockInfo;

import io.github.drmanganese.topaddons.util.InfoHelper;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.BlockCocoa;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import static mcjty.theoneprobe.api.TextStyleClass.*;

public class CocoaInfo implements IBlockInfo {

    @Override
    public void getInfo(ProbeMode probeMode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData hitData) {
        InfoHelper.growth(probeInfo, blockState.getValue(BlockCocoa.AGE), 2);
    }
}