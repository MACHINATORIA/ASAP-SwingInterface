package asap.ui.swing.component;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JEditorPane;
import javax.swing.JTextPane;

import asap.primitive.pattern.Lambdas.ErrorListener;
import asap.primitive.pattern.Lambdas.EventListener;

@SuppressWarnings( "serial" )
public class ETextPane extends JTextPane implements ETextComponent {

    public static Boolean                            HONOR_DISPLAY_PROPERTIES = true;

    protected int                                    visibleRowCount          = 0;

    protected ETextExtension< ETextPane, ETextPane > extension;

    public ETextPane( ) {
        super( );
        this._construct( );
    }

    private void _construct( ) {
        this.putClientProperty( JEditorPane.HONOR_DISPLAY_PROPERTIES,
                                HONOR_DISPLAY_PROPERTIES );
        //
        this.extension = new ETextExtension< ETextPane, ETextPane >( this,
                                                                     true );
    }

    public void setText( String text ) {
        super.setText( text );
        this.extension.gotoLine( 0 );
    }

    public int getVisibleRowCount( ) {
        return this.visibleRowCount;
    }

    public void setVisibleRowCount( int visibleRowCount ) {
        this.visibleRowCount = visibleRowCount;
        Dimension tmpPreferredSize = null;
        if ( this.visibleRowCount > 1 ) {
            int tmpRowHeight = this.getFontMetrics( this.getFont( ) ).getHeight( );
            tmpPreferredSize = this.getPreferredSize( );
            Insets tmpMargin = this.getMargin( );
            Insets tmpInsets = this.getInsets( );
            //    tmpMargin = new Insets( 0,
            //                            0,
            //                            0,
            //                            0 );
            //    tmpInsets = new Insets( 0,
            //                            0,
            //                            0,
            //                            0 );
            tmpPreferredSize.height = ( ( tmpMargin.top + tmpInsets.top )
                                        + ( this.visibleRowCount * tmpRowHeight )
                                        + ( tmpInsets.bottom + tmpMargin.bottom ) );
        }
        this.setPreferredSize( tmpPreferredSize );
        this.setMinimumSize( tmpPreferredSize );
        this.setMaximumSize( tmpPreferredSize );
    }

    @Override
    public ETextExtension< ETextPane, ETextPane > getExtension( ) {
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
