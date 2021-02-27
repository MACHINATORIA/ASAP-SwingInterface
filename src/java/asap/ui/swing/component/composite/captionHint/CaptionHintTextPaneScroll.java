package asap.ui.swing.component.composite.captionHint;

import java.awt.BorderLayout;

import asap.ui.swing.component.composite.decorable.ETextPaneScrollPanel;

@SuppressWarnings( "serial" )
public class CaptionHintTextPaneScroll extends CaptionHintPanel {

    public ETextPaneScrollPanel text;

    public CaptionHintTextPaneScroll( ) {
        super( );
        text = new ETextPaneScrollPanel( );
        contentPanel.add( text,
                          BorderLayout.CENTER );
    }
}
