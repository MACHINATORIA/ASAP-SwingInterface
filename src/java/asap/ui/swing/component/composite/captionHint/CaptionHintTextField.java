package asap.ui.swing.component.composite.captionHint;

import java.awt.BorderLayout;

import asap.ui.swing.component.composite.decorable.ETextFieldPanel;

@SuppressWarnings( "serial" )
public class CaptionHintTextField extends CaptionHintPanel {

    public ETextFieldPanel text;

    public CaptionHintTextField( ) {
        super( );
        caption.label.setText( "TextField Caption" );
        contentPanel.setLayout( new BorderLayout( 0,
                                                  0 ) );
        text = new ETextFieldPanel( );
        contentPanel.add( text );
    }
}
