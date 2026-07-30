package net.waniiii.enhancedcontraptioncamera.nestedsublevel;



import dev.ryanhcode.sable.Sable;
import dev.ryanhcode.sable.sublevel.ClientSubLevel;
import dev.ryanhcode.sable.sublevel.SubLevel;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import dev.ryanhcode.sable.companion.math.BoundingBox3d;
import java.util.Collection;
import java.util.logging.Logger;

import net.minecraft.client.Minecraft;
import com.mojang.logging.LogUtils;
import net.neoforged.fml.Logging;

public class NestedSubLevel {

    private static Minecraft minecraft;
    public static LogUtils logger;

    //Gets the sublevels within render distance.
    public static Collection<ClientSubLevel> getRenderedChain (final ClientSubLevel subLevel){
        final ObjectOpenHashSet<ClientSubLevel> visited = new ObjectOpenHashSet<>();
        final ObjectOpenHashSet<ClientSubLevel> frontier = new ObjectOpenHashSet<>();

        final SubLevel sub = Sable.HELPER.getVehicleSubLevel(minecraft.player);

        frontier.add(subLevel);


        while(!frontier.isEmpty()){
            final ClientSubLevel current = frontier.iterator().next();
            if (current != sub){
                continue;
            }
            frontier.remove(current);
            visited.add(current);

            final Iterable<SubLevel> intersecting = Sable.HELPER.getAllIntersecting(current.getLevel(), new BoundingBox3d(current.boundingBox()));


            //Gets connected sublevels, sublevels.
            for (final SubLevel neighbor : intersecting){
                final ClientSubLevel serverNeighbor = (ClientSubLevel) neighbor;

                if (!visited.contains(serverNeighbor)){
                    frontier.add(serverNeighbor);
                }
            }

        }
        return visited;
    }
}
