package dk.martinersej.generator.utils;

import org.apache.commons.lang.Validate;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;

import java.awt.*;

public class ParticleUtils {

    private static final ParticleBuilder PARTICLE_BUILDER = new ParticleBuilder(ParticleEffect.REDSTONE).setAmount(1);

    public static void drawLine(Location point1, Location point2, double space, Color color) {
        World world = point1.getWorld();
        Validate.isTrue(point2.getWorld().equals(world), "Lines cannot be in different worlds!");
        point1 = point1.clone().add(0.5, 0.5, 0.5);
        point2 = point2.clone().add(0.5, 0.5, 0.5);
        double distance = point1.distance(point2);
        if (distance > 100) {
            throw new IllegalArgumentException("Distance between points is too long!");
        }
        Vector p1 = point1.toVector();
        Vector p2 = point2.toVector();
        Vector vector = p2.clone().subtract(p1).normalize().multiply(space);
        double length = 0;
        for (; length < distance; p1.add(vector)) {
            PARTICLE_BUILDER.setLocation(p1.toLocation(world)).setColor(color).display();
            length += space;
        }
    }
}
