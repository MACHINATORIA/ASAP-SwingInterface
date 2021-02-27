package asap.ui.swing.component.composite.captionHint;

import java.awt.BorderLayout;

import asap.ui.swing.component.composite.decorable.ESpinnerPanel;

@SuppressWarnings( "serial" )
public class CaptionHintSpinner extends CaptionHintPanel {

    public ESpinnerPanel decorable;

    public CaptionHintSpinner( ) {
        super( );
        caption.label.setText( "Spinner Caption" );
        contentPanel.setLayout( new BorderLayout( 0,
                                                  0 ) );
        decorable = new ESpinnerPanel( );
        contentPanel.add( decorable );
    }
}
