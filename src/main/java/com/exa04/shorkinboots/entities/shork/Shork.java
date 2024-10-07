package com.exa04.shorkinboots.entities.shork;

import com.exa04.shorkinboots.ShorkInBootsMod;
import com.exa04.shorkinboots.sound.Sounds;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class Shork extends Animal {
    public Shork(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new TemptGoal(this, 1.2D, Ingredient.of(Items.COOKIE), false));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1f));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 3f));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 20D)
                .add(Attributes.FOLLOW_RANGE, 24D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return itemStack.is(Items.COOKIE);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return ShorkInBootsMod.SHORK_IN_BOOTS.get().create(level);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return Sounds.SHORK_AMBIENT.get();
    }

    @Override
    protected @Nullable SoundEvent getHurtSound(DamageSource p_21239_) {
        return Sounds.SHORK_HURT.get();
    }

    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return Sounds.SHORK_DEATH.get();
    }

    @Override
    public SoundEvent getEatingSound(ItemStack p_21202_) {
        return Sounds.SHORK_NOM.get();
    }
}
