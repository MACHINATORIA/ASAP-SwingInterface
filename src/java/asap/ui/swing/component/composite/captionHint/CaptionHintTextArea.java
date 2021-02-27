package asap.ui.swing.component.composite.captionHint;

import java.awt.BorderLayout;

import asap.ui.swing.component.composite.decorable.ETextAreaScrollPanel;

@SuppressWarnings( "serial" )
public class CaptionHintTextArea extends CaptionHintPanel {

    public ETextAreaScrollPanel text;

    public CaptionHintTextArea( ) {
        super( );
        contentPanel.setLayout( new BorderLayout( 0,
                                                  0 ) );
        text = new ETextAreaScrollPanel( );
        contentPanel.add( text,
                          BorderLayout.CENTER );
    }
}
