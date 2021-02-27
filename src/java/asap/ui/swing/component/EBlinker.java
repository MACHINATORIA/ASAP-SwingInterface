package asap.ui.swing.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.Timer;

public class EBlinker {

    public static class EBlinkerRate {

        public int cycleUnits;

        public EBlinkerRate( int cycleUnits ) {
            this.cycleUnits = cycleUnits;
        }
    }

    public static class EBlinkerCycle {

        public int     showRatio;

        public int     hideRatio;

        public boolean async;

        public EBlinkerCycle( int showRatio,
                              int hideRatio,
                              boolean async ) {
            this.showRatio = showRatio;
            this.hideRatio = hideRatio;
            this.async = async;
        }
    }

    public static class EBlinkerDuration {

        public int     cycleCount;

        public boolean stopOnShow;

        public EBlinkerDuration( int cycleCount,
                                 boolean stopOnShow ) {
            this.cycleCount = cycleCount;
            this.stopOnShow = stopOnShow;
        }
    }

    protected static class RateContext {
    
        public boolean                 isDefault;
    
        public EBlinkerRate            rate;
    
        public Set< ComponentContext > componentContexts;
    
        public int                     syncCycleUnitCounter;
    
        public int                     cycleCounter;
    
        public RateContext( boolean isDefault,
                            EBlinkerRate rate ) {
            this.isDefault = isDefault;
            this.rate = rate;
            this.componentContexts = new HashSet< ComponentContext >( );
            this.syncCycleUnitCounter = 0;
            this.cycleCounter = 0;
        }
    }

    protected class ComponentContext {
    
        public EComponent       component;
    
        public RateContext      rateContext;
    
        public EBlinkerCycle    cycle;
    
        public EBlinkerDuration duration;
    
        public int              asyncCycleUnitCounter;
    
        public int              durationCycleCounter;
    
        public ComponentContext( EComponent component,
                                 RateContext rateContext,
                                 EBlinkerCycle cycle,
                                 EBlinkerDuration duration ) {
            this.component = component;
            this.rateContext = rateContext;
            this.cycle = cycle;
            this.duration = duration;
            this.asyncCycleUnitCounter = 0;
        }
    }

    public static EBlinker                        singleton                 = new EBlinker( );

    public int                                    defaultRateCycleUnits     = 15;

    public int                                    defaultCycleShowRatio     = 5;

    public int                                    defaultCycleHideRatio     = 5;

    public boolean                                defaultCycleAsync         = false;

    public int                                    defaultDurationCycleCount = 0;

    public boolean                                defaultDurationStopOnShow = false;

    protected Map< EBlinkerRate, RateContext >    rateContexts;

    protected Map< EComponent, ComponentContext > componentContexts;

    protected Map< Integer, EBlinkerRate >        defaultRates;

    protected Timer                               blinkTimer;

    protected void performBlinking( ) {
        for ( Map.Entry< EBlinkerRate, RateContext > tmpRateEntry : this.rateContexts.entrySet( ) ) {
            //
            EBlinkerRate tmpRate = tmpRateEntry.getKey( );
            RateContext tmpRateContext = tmpRateEntry.getValue( );
            //
            int tmpSyncCycleUnitCounter = tmpRateContext.syncCycleUnitCounter;
            tmpRateContext.syncCycleUnitCounter = ( ( tmpRateContext.syncCycleUnitCounter + 1 ) % tmpRate.cycleUnits );
            //
            for ( ComponentContext tmpComponentContext : tmpRateContext.componentContexts ) {
                //
                int tmpAsyncCycleUnitCounter = tmpComponentContext.asyncCycleUnitCounter;
                tmpComponentContext.asyncCycleUnitCounter = ( ( tmpComponentContext.asyncCycleUnitCounter + 1 )
                                                              % tmpRate.cycleUnits );
                //
                EBlinkerCycle tmpCycle = tmpComponentContext.cycle;
                int tmpCurrentCycleUnitCounter = ( tmpCycle.async ? tmpAsyncCycleUnitCounter
                                                                  : tmpSyncCycleUnitCounter );
                //
                boolean tmpStopBlinking = false;
                EBlinkerDuration tmpDuration = tmpComponentContext.duration;
                if ( tmpDuration.cycleCount > 0 ) {
                    int tmpNextCycleUnitCounter = ( tmpCycle.async ? tmpComponentContext.asyncCycleUnitCounter
                                                                   : tmpRateContext.syncCycleUnitCounter );
                    if ( tmpNextCycleUnitCounter == 0 ) {
                        tmpComponentContext.durationCycleCounter += 1;
                    }
                    tmpStopBlinking = ( tmpComponentContext.durationCycleCounter >= tmpDuration.cycleCount );
                }
                //
                boolean tmpBlinkStopped = false;
                if ( tmpCurrentCycleUnitCounter == 0 ) {
                    tmpComponentContext.component.setVisible( true );
                    tmpBlinkStopped = ( tmpStopBlinking && tmpDuration.stopOnShow );
                }
                else {
                    int tmpHideThreshold = Math.round( ( tmpCycle.showRatio * tmpRate.cycleUnits )
                                                       / ( (float) ( tmpCycle.showRatio + tmpCycle.hideRatio ) ) );
                    if ( tmpCurrentCycleUnitCounter >= tmpHideThreshold ) {
                        if ( tmpCurrentCycleUnitCounter == tmpHideThreshold ) {
                            tmpComponentContext.component.setVisible( false );
                        }
                        tmpBlinkStopped = ( tmpStopBlinking && !tmpDuration.stopOnShow );
                    }
                }
                if ( tmpBlinkStopped ) {
                    this.removeBlinker( tmpComponentContext.component );
                }
            }
        }
    }

    public void addBlinker( EComponent component,
                            int duration,
                            boolean stopOnShow ) {
        this.addBlinker( component,
                         null,
                         null,
                         new EBlinkerDuration( duration,
                                               stopOnShow ) );
    }

    public void addBlinker( EComponent component,
                            EBlinkerRate rate,
                            EBlinkerCycle cycle,
                            EBlinkerDuration duration ) {
        //
        this.removeBlinker( component );
        //
        boolean tmpDefaultRate = ( rate == null );
        if ( tmpDefaultRate ) {
            rate = this.defaultRates.get( this.defaultRateCycleUnits );
            if ( rate == null ) {
                rate = new EBlinkerRate( this.defaultRateCycleUnits );
                this.defaultRates.put( this.defaultRateCycleUnits,
                                       rate );
            }
        }
        RateContext tmpRateContext = this.rateContexts.get( rate );
        if ( tmpRateContext == null ) {
            tmpRateContext = new RateContext( tmpDefaultRate,
                                              rate );
            this.rateContexts.put( rate,
                                   tmpRateContext );
        }
        if ( cycle == null ) {
            cycle = new EBlinkerCycle( this.defaultCycleShowRatio,
                                       this.defaultCycleHideRatio,
                                       this.defaultCycleAsync );
        }
        if ( duration == null ) {
            duration = new EBlinkerDuration( this.defaultDurationCycleCount,
                                             this.defaultDurationStopOnShow );
        }
        //
        ComponentContext tmpComponentContext = new ComponentContext( component,
                                                                     tmpRateContext,
                                                                     cycle,
                                                                     duration );
        this.componentContexts.put( component,
                                    tmpComponentContext );
        tmpRateContext.componentContexts.add( tmpComponentContext );
        if ( !this.blinkTimer.isRunning( ) ) {
            this.blinkTimer.start( );
        }
    }

    public void removeBlinker( EComponent component ) {
        ComponentContext tmpComponentContext = this.componentContexts.get( component );
        if ( tmpComponentContext != null ) {
            RateContext tmpRateContext = tmpComponentContext.rateContext;
            tmpRateContext.componentContexts.remove( tmpComponentContext );
            if ( tmpRateContext.componentContexts.size( ) == 0 ) {
                this.rateContexts.remove( tmpRateContext.rate );
                if ( tmpRateContext.isDefault ) {
                    this.defaultRates.remove( tmpRateContext.rate.cycleUnits );
                }
            }
            this.componentContexts.remove( component );
        }
        if ( this.componentContexts.size( ) == 0 ) {
            this.blinkTimer.stop( );
        }
    }

    private EBlinker( ) {
        this.componentContexts = new HashMap< EComponent, ComponentContext >( );
        this.rateContexts = new HashMap< EBlinkerRate, RateContext >( );
        this.defaultRates = new HashMap< Integer, EBlinkerRate >( );
        this.blinkTimer = new Timer( 100,
                                     new ActionListener( ) {

                                         @Override
                                         public void actionPerformed( ActionEvent action ) {
                                             EBlinker.this.performBlinking( );
                                         }
                                     } );
    }
}
