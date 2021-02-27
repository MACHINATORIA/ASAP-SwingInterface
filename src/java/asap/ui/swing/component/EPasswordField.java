package asap.ui.swing.component;

import javax.swing.JPasswordField;

import asap.primitive.pattern.Lambdas.ErrorListener;
import asap.primitive.pattern.Lambdas.EventListener;

@SuppressWarnings( "serial" )
public class EPasswordField extends JPasswordField implements ETextComponent {

    protected ETextExtension< EPasswordField, EPasswordField > extension;

    public EPasswordField( ) {
        super( );
        this._construct( );
    }

    private void _construct( ) {
        this.extension = new ETextExtension< EPasswordField, EPasswordField >( this,
                                                                               true );
        this.putClientProperty( "JPasswordField.cutCopyAllowed",
                                true );
    }

    @Override
    public String getText( ) {
        return new String( this.getPassword( ) );
    }

    @Override
    public int getColumnWidth( ) {
        char tmpEchoChar = this.getEchoChar( );
        if ( tmpEchoChar != '\0' ) {
            return this.getFontMetrics( this.getFont( ) ).charWidth( tmpEchoChar );
        }
        return super.getColumnWidth( );
    }

    @Override
    public ETextExtension< EPasswordField, EPasswordField > getExtension( ) {
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
