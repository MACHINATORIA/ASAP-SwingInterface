package asap.ui.swing.component.composite.captionHint;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.SystemColor;

import javax.swing.border.EmptyBorder;

import asap.ui.swing.component.EPanel;
import asap.ui.swing.component.composite.decorable.ETextPanePanel;

@SuppressWarnings( "serial" )
public class CaptionHintReviewPanel extends CaptionHintPanel {

    public EPanel         reviewPanel;

    public ETextPanePanel review;

    public CaptionHintReviewPanel( ) {
        reviewPanel = new EPanel( );
        add( reviewPanel,
             BorderLayout.SOUTH );
        reviewPanel.setBorder( new EmptyBorder( 0,
                                                5,
                                                0,
                                                0 ) );
        reviewPanel.setLayout( new BorderLayout( 0,
                                                 0 ) );
        review = new ETextPanePanel( );
        //review.pane.setVisibleRowCount(2);
        reviewPanel.add( review );
        review.pane.setEditable( false );
        review.pane.setMargin( new Insets( 0,
                                           0,
                                           0,
                                           0 ) );
        review.pane.setBackground( SystemColor.control );
        review.pane.setText( "Review text #1...\nReview text #2...\nReview text #3..." );
    }
}
