package asap.ui.swing.component.composite.captionHint;

import javax.swing.border.EmptyBorder;

@SuppressWarnings( "serial" )
public class CaptionHintForm extends CaptionHintPanel {

    public CaptionHintForm( ) {
        captionPanel.setBorder( new EmptyBorder( 0,
                                                 5,
                                                 10,
                                                 0 ) );
        hint.label.setFont( hint.label.getFont( ).deriveFont( hint.label.getFont( ).getSize( ) + 4f ) );
        caption.label.setFont( caption.label.getFont( ).deriveFont( caption.label.getFont( ).getSize( ) + 4f ) );
    }
}
