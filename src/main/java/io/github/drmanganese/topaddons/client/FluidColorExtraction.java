package io.github.drmanganese.topaddons.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.container.PlayerContainer;

public class FluidColorExtraction {

    private static final AtlasTexture TEXTURE = Minecraft.getInstance().getModelManager().getAtlasTexture(PlayerContainer.LOCATION_BLOCKS_TEXTURE);

    static int extractTopLeftFluidColorFromTexture(Fluid fluid) {
        final TextureAtlasSprite sprite = getStillFluidTexture(fluid);
        final int abgr = sprite.getPixelRGBA(0, 0, 0);
        return (0xff << 24) | (red(abgr) << 16) | (green(abgr) << 8) | blue(abgr);
    }

    static int extractAvgFluidColorFromTexture(Fluid fluid) {
        final TextureAtlasSprite sprite = getStillFluidTexture(fluid);
        final int width = sprite.getWidth();
        final int n = width * width;
        int r = 0;
        int g = 0;
        int b = 0;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < width; y++) {
                final int abgr = sprite.getPixelRGBA(0, x, y);
                r += Math.pow(red(abgr), 2);
                g += Math.pow(green(abgr), 2);
                b += Math.pow(blue(abgr), 2);
            }
        }
        return (0xff << 24) | (sqrt(r / n) << 16) | (sqrt(g / n) << 8) | sqrt(b / n);
    }
    
    public static TextureAtlasSprite getStillFluidTexture(Fluid fluid) {
        return TEXTURE.getSprite(fluid.getAttributes().getStillTexture());
    }

    private static int red(int abgr) {
        return abgr & 0xff;
    }

    private static int green(int abgr) {
        return abgr >> 8 & 0xff;
    }

    private static int blue(int abgr) {
        return abgr >> 16 & 0xff;
    }

    private static int sqrt(int i) {
        return (int) Math.sqrt(i);
    }
}