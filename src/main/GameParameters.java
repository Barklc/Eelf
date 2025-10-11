package main;

public class GameParameters {

    public static final int MaxPop=1;
    public static final int MaxPlants=3;
    public static final int MaxMeat=0;
    public static final int YearInTicks=60;
    public static final int TicksBetweenBites=30;
    public static final int FlippersSegmentConnected = 2;
    public static final int TailSegmentOffsetFromEnd = 0;
    public static final float TailThresholdForSpeedMod=0.75f;
    public static final float MassSpeedModifierPercentage=0.0f;
    public static final int EyesSegmentConnected = 0;
    public static final int MouthSegmentConnected = 0;
    public static final float BiteStrengthVisualThreshold=0.5f;
    public static final float MouthPresentThreshold = 0.5f;
    public static final float EyesPresentThreshold = 0.5f;
    public static final float FlipperPresentThreshold = 0.5f;
    public static final float TailPresentThreshold = 0.5f;
    //This is the base amount of nourishment digested adjusted by DigestionRate gene per tick
    public static final float BaseDigestionAmountPerTick = 10f/YearInTicks;

    //This is the amount of damage an unborn will take if parent is missing need engergy or health.
    public static final float UnbornHealthDamagePerIncrease = 0.1f;

    //This is used to determine what objects are within range of the creature.  This is the radius the range to check.
    public static final float MaxObjectInRangeRadius=300.0f;

    //This is the max distance a scent can start to be detected.  Is has to be less the MaxObjectInRangeRadius as
    // source has to be within this range.
    public static final float MaxScentDistance=255.0f;
    //How much meat nourishments rot per tick.  Larger the number the more per tick.
    public static final float RotPercentagePerTick=0.01f;

    //How much plant nourishments grow per tick.  Larger the number the more per tick.
    public static final float GrowthPercentagePerTick=0.001f;

    //The smaller the number, the larger the Nourishment display size will be
    public static final float NourishmentMassToSizeAdjustment=0.2f;

}



