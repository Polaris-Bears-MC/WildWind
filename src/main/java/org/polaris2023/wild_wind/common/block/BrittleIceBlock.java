package org.polaris2023.wild_wind.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.IceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.level.pathfinder.PathType;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

public class BrittleIceBlock extends IceBlock {
	public static final MapCodec<BrittleIceBlock> CODEC = simpleCodec(BrittleIceBlock::new);
	public static final int MAX_AGE = 3;
	public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
	public static final BooleanProperty UNSTABLE = BlockStateProperties.UNSTABLE;

	public BrittleIceBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0).setValue(UNSTABLE, false));
	}

	@Override
	public MapCodec<? extends BrittleIceBlock> codec() {
		return CODEC;
	}

	@Override
	public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
		if(level instanceof ServerLevel serverLevel && entity instanceof LivingEntity && fallDistance > 0.75F) {
			this.crash(serverLevel, pos, entity);
		}
		super.fallOn(level, state, pos, entity, fallDistance);
	}

	@Override
	public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
		super.stepOn(level, pos, state, entity);

		if (!level.isClientSide() && !level.getBlockTicks().willTickThisTick(pos, this) && !entity.isSteppingCarefully()) {
			level.scheduleTick(pos, this, 10);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void tick(BlockState blockState, ServerLevel level, BlockPos blockPos, RandomSource random) {
		int age = blockState.getValue(AGE);
		boolean unstable = blockState.getValue(UNSTABLE);
		if(unstable) {
			this.crash(level, blockPos, null);
			return;
		}
		Predicate<Entity> entityPredicate = entity -> entity.onGround() && entity.getOnPosLegacy().equals(blockPos) && !entity.isSteppingCarefully();
		List<? extends Entity> entitiesOnBlock = level.getEntities(EntityTypeTest.forClass(LivingEntity.class), entityPredicate);
		if(entitiesOnBlock.isEmpty()) {
			if(age > 0) {
				if(random.nextInt(100 / age / age) < 10) {
					level.setBlock(blockPos, blockState.setValue(AGE, age - 1), Block.UPDATE_ALL);
				}
				level.scheduleTick(blockPos, this, 10);
			}
		} else if(age == MAX_AGE) {
			this.crash(level, blockPos, entitiesOnBlock.get(random.nextInt(entitiesOnBlock.size())));
		} else {
			level.setBlock(blockPos, blockState.setValue(AGE, age + 1), Block.UPDATE_ALL);
			level.playSound(null, blockPos, SoundEvents.GLASS_HIT, SoundSource.BLOCKS, 0.5F, 0.9F + random.nextFloat() * 0.2F);
			level.scheduleTick(blockPos, this, 10);
		}
	}

	private void crash(ServerLevel level, BlockPos blockPos, @Nullable Entity entity) {
		level.destroyBlock(blockPos, false, entity);
		//this.tryChainReactAt(level, blockPos.south());
		//this.tryChainReactAt(level, blockPos.east());
		//this.tryChainReactAt(level, blockPos.above());
		//this.tryChainReactAt(level, blockPos.north());
		//this.tryChainReactAt(level, blockPos.west());
		this.tryChainReactAt(level, blockPos.below());
	}

	private void tryChainReactAt(ServerLevel level, BlockPos blockPos) {
		BlockState blockState = level.getBlockState(blockPos);
		if(blockState.is(this)) {
			level.setBlock(blockPos, blockState.setValue(UNSTABLE, true), Block.UPDATE_ALL);
			if(!level.getBlockTicks().willTickThisTick(blockPos, this)) {
				level.scheduleTick(blockPos, this, 2);
			}
		}
	}

	@Override
	public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity te, ItemStack stack) {
		player.awardStat(Stats.BLOCK_MINED.get(this));
		player.causeFoodExhaustion(0.005F);
		dropResources(state, level, pos, te, player, stack);
	}

	@Override
	protected void melt(BlockState state, Level level, BlockPos pos) {
		level.removeBlock(pos, false);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(AGE, UNSTABLE);
	}

	@Override
	protected boolean skipRendering(BlockState state, BlockState adjacentBlockState, Direction side) {
		return adjacentBlockState.is(Blocks.ICE) || super.skipRendering(state, adjacentBlockState, side);
	}

	@Override
	public PathType getAdjacentBlockPathType(BlockState state, BlockGetter level, BlockPos pos, @Nullable Mob mob, PathType originalType) {
		return PathType.DANGER_OTHER;
	}
}
