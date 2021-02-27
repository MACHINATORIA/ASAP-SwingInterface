package asap.ui.swing.component;

import javax.swing.JButton;

import asap.primitive.pattern.Lambdas.EventListener;

@SuppressWarnings( "serial" )
public class EButton extends JButton {

    protected EventListener clickListener;

    public EButton( ) {
        super( );
        this._construct( );
    }

    public EButton( String text ) {
        super( text );
        this._construct( );
    }

    private void _construct( ) {
        //
        this.onClick( null );
        //
        this.addActionListener( ( event ) -> {
            if ( this.clickListener != null ) {
                this.clickListener.happened( );
            }
        } );
    }

    public void onClick( EventListener clickListener ) {
        this.clickListener = clickListener;
    }
}
