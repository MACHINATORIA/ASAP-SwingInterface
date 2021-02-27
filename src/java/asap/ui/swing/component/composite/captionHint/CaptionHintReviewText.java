package asap.ui.swing.component.composite.captionHint;

import asap.ui.swing.component.composite.decorable.ETextFieldPanel;

@SuppressWarnings( "serial" )
public class CaptionHintReviewText extends CaptionHintReviewPanel {

    public ETextFieldPanel text;

    public CaptionHintReviewText( ) {
        review.pane.setText( "Text text #1...\r\nText text #2..." );
        caption.label.setText( "Text Caption" );
        text = new ETextFieldPanel( );
        contentPanel.add( text );
    }
}
