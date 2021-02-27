package asap.ui.swing.component.composite.captionHint;

import java.awt.BorderLayout;

import asap.ui.swing.component.composite.decorable.EPasswordFieldPanel;

@SuppressWarnings( "serial" )
public class CaptionHintReviewPassword extends CaptionHintReviewPanel {

    public EPasswordFieldPanel password;

    public CaptionHintReviewPassword( ) {
        caption.label.setText( "Password Caption" );
        password = new EPasswordFieldPanel( );
        contentPanel.add( password,
                          BorderLayout.CENTER );
        review.pane.setText( "Password text #1...\r\nPassword text #2..." );
    }
}
