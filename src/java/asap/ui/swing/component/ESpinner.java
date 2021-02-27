package asap.ui.swing.component;

import javax.swing.JSpinner;

import asap.primitive.pattern.Lambdas.EventListener;

@SuppressWarnings( "serial" )
public class ESpinner extends JSpinner {

    protected EventListener changeListener;

    public ESpinner( ) {
        super( );
        this.onChange( null );
        //
        this.addChangeListener( ( event ) -> {
            if ( this.changeListener != null ) {
                this.changeListener.happened( );
            }
        } );
    }

    public void onChange( EventListener changeListener ) {
        this.changeListener = changeListener;
    }
}
