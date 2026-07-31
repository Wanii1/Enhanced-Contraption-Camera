package net.waniiii.enhancedcontraptioncamera.mixins;


import com.llamalad7.mixinextras.lib.apache.commons.ArrayUtils;
import net.minecraft.client.CameraType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;


@Mixin(EnhanceCameraTypeMixin.class)
public class EnhanceCameraTypeMixin {

    @Shadow
    @Final
    @Mutable
    private static CameraType[] $VALUES;

    @Shadow
    @Final
    @Mutable
    private static CameraType[] VALUES;


    static{
        final var firstPersonSubLevelView = create("FIRST_PERSON_SUBLEVEL_VIEW", $VALUES.length, true, false);

        $VALUES = ArrayUtils.add($VALUES, firstPersonSubLevelView);

        VALUES = ArrayUtils.add(VALUES, firstPersonSubLevelView);
    }


    @Invoker(value = "<init>")
    private static CameraType create(final String name, final int ordinal, final boolean firstPerson, final boolean mirrored) {
        throw new IllegalStateException("Unreachable");
    }



}
