package asap.ui.swing.component.composite.decorable;

import java.awt.BorderLayout;

import asap.ui.swing.component.EPanel;
import asap.ui.swing.component.ESpinner;

@SuppressWarnings( "serial" )
public class ESpinnerPanel extends EPanel {

    public ESpinner spinner;

    public ESpinnerPanel( ) {
        this.setLayout( new BorderLayout( 0,
                                          0 ) );
        spinner = new ESpinner( );
        add( spinner,
             BorderLayout.CENTER );
    }
}
