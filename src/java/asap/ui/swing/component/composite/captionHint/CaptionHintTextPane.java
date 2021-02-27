package asap.ui.swing.component.composite.captionHint;

import java.awt.BorderLayout;

import asap.ui.swing.component.composite.decorable.ETextPanePanel;

@SuppressWarnings( "serial" )
public class CaptionHintTextPane extends CaptionHintPanel {

    public ETextPanePanel text;

    public CaptionHintTextPane( ) {
        super( );
        caption.label.setText( "TextPane Caption" );
        contentPanel.setLayout( new BorderLayout( 0,
                                                  0 ) );
        text = new ETextPanePanel( );
        contentPanel.add( text );
    }
}
