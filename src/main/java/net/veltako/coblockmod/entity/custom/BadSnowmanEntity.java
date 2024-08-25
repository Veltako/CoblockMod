package net.veltako.coblockmod.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;


public class BadSnowmanEntity extends Monster implements IAnimatable {

    private final AnimationFactory factory = new AnimationFactory(this);
    private boolean isChasing = false;
    private boolean isJumping = false;
    private boolean isFalling = false;
    private boolean isAttacking = false;
    private long attackAnimationEndTime = 0;
    private long fallAnimationEndTime = 0;

    public BadSnowmanEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier setAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 50.0D)
                .add(Attributes.ATTACK_DAMAGE, 7.0f)
                .add(Attributes.ATTACK_SPEED, 0.5f)
                .add(Attributes.MOVEMENT_SPEED, 0.23f)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new CustomMeleeAttackGoal(this, 1.5D, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, LivingEntity.class, true));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.getEntity() instanceof Player) {
            Player player = (Player) source.getEntity();
            ItemStack itemInHand = player.getMainHandItem();

            if (itemInHand.getItem() instanceof SwordItem || itemInHand.getItem() instanceof AxeItem) {
                amount *= 0.5;
            } else if (itemInHand.getItem() instanceof ShovelItem) {
                amount *= 2.0;
            }
        }

        if (source == DamageSource.FALL && !this.isFalling) {
            this.isFalling = true;
            this.fallAnimationEndTime = System.currentTimeMillis() + 2000; // Fall animation duration
        }

        return super.hurt(source, amount);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        applyFrostWalker();

        if (this.isFalling && System.currentTimeMillis() > this.fallAnimationEndTime) {
            this.isFalling = false;
        }

        if (!this.onGround && !this.isJumping && this.getDeltaMovement().y > 0) {
            this.isJumping = true;
            this.fallAnimationEndTime = System.currentTimeMillis() + 2000; // Jump animation duration
        }

        if (this.onGround && this.isJumping && System.currentTimeMillis() > this.fallAnimationEndTime) {
            this.isJumping = false;
        }
    }

    private void applyFrostWalker() {
        BlockPos blockpos = this.blockPosition();
        for (BlockPos pos : BlockPos.betweenClosed(blockpos.offset(-1, -1, -1), blockpos.offset(1, -1, 1))) {
            BlockState blockState = this.level.getBlockState(pos);
            if (blockState.getMaterial() == Material.WATER && this.level.getBlockState(pos.above()).isAir()) {
                this.level.setBlockAndUpdate(pos, Blocks.FROSTED_ICE.defaultBlockState());
            }
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.isOnFire()) {
            doubleFireDamage();
        }
    }

    private void doubleFireDamage() {
        this.hurt(DamageSource.ON_FIRE, 2.0F);
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        super.dropCustomDeathLoot(source, looting, recentlyHit);
        this.spawnAtLocation(new ItemStack(Items.CARROT));
        this.spawnAtLocation(new ItemStack(Blocks.SNOW_BLOCK, 3));
        this.spawnAtLocation(new ItemStack(Items.STICK, 2));
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
        data.addAnimationController(new AnimationController<>(this, "attackController", 0, this::attackPredicate));
        data.addAnimationController(new AnimationController<>(this, "damageController", 0, this::damagePredicate));
        data.addAnimationController(new AnimationController<>(this, "deathController", 0, this::deathPredicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        long currentTime = System.currentTimeMillis();

        if (this.isAttacking) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("attack", true));
            return PlayState.CONTINUE;
        } else if (this.isFalling) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("fall", true));
            return PlayState.CONTINUE;
        } else if (this.isJumping) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("jump", true));
            return PlayState.CONTINUE;
        } else if (this.isChasing) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("run", true));
            return PlayState.CONTINUE;
        } else if (this.isIdle()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("pose", true));
            return PlayState.CONTINUE;
        }

        return PlayState.STOP;
    }

    private <E extends IAnimatable> PlayState attackPredicate(AnimationEvent<E> event) {
        long currentTime = System.currentTimeMillis();

        if (this.swinging && !this.isAttacking) {
            this.isAttacking = true;
            this.attackAnimationEndTime = currentTime + 2000; // Attack animation duration of 2 seconds
            event.getController().setAnimation(new AnimationBuilder().addAnimation("attack", true));
            return PlayState.CONTINUE;
        } else if (!this.swinging && this.isAttacking && currentTime > this.attackAnimationEndTime) {
            this.isAttacking = false;
        }

        return PlayState.STOP;
    }

    private <E extends IAnimatable> PlayState damagePredicate(AnimationEvent<E> event) {
        if (this.hurtTime > 0) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("damage", true));
            return PlayState.CONTINUE;
        }

        return PlayState.STOP;
    }

    private <E extends IAnimatable> PlayState deathPredicate(AnimationEvent<E> event) {
        if (this.isDeadOrDying()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("death", false));
            return PlayState.CONTINUE;
        }

        return PlayState.STOP;
    }

    private boolean isIdle() {
        return !this.isChasing && !this.isAttacking && !this.isJumping && !this.isFalling;
    }

    // Custom MeleeAttackGoal with increased speed
    private static class CustomMeleeAttackGoal extends MeleeAttackGoal {
        private final BadSnowmanEntity entity;

        public CustomMeleeAttackGoal(BadSnowmanEntity entity, double speedModifier, boolean pauseWhenMobIdle) {
            super(entity, speedModifier, pauseWhenMobIdle);
            this.entity = entity;
        }

        @Override
        public void start() {
            super.start();
            this.entity.isChasing = true;
        }

        @Override
        public void stop() {
            super.stop();
            this.entity.isChasing = false;
        }
    }
}
