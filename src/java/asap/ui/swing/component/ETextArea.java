package asap.ui.swing.component;

import javax.swing.JTextArea;

import asap.primitive.pattern.Lambdas.ErrorListener;
import asap.primitive.pattern.Lambdas.EventListener;

@SuppressWarnings( "serial" )
public class ETextArea extends JTextArea implements ETextComponent {

    protected ETextExtension< ETextArea, ETextArea > extension;

    public ETextArea( ) {
        super( );
        this._construct( );
    }

    public ETextArea( int rows,
                      int columns ) {
        super( rows,
               columns );
        this._construct( );
    }

    protected void _construct( ) {
        //
        this.extension = new ETextExtension< ETextArea, ETextArea >( this,
                                                                     false );
    }

    @Override
    public void setText( String text ) {
        super.setText( text );
        this.extension.gotoLine( 0 );
    }

    @Override
    public ETextExtension< ETextArea, ETextArea > getExtension( ) {
        return this.extension;
    }

    @Override
    public boolean isTranferEnabled( ) {
        return this.extension.isTransferEnabled( );
    }

    @Override
    public void setTransferEnabled( boolean transferEnabled ) {
        this.extension.setTransferEnabled( transferEnabled );
    }

    @Override
    public void onError( ErrorListener< ErrorType > errorListener ) {
        this.extension.onError( errorListener );
    }

    @Override
    public void onChange( EventListener changeListener ) {
        this.extension.onChange( changeListener );
    }
}
