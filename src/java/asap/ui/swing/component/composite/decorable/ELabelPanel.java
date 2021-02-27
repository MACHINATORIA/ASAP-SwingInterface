package asap.ui.swing.component.composite.decorable;

import java.awt.BorderLayout;

import asap.ui.swing.component.ELabel;
import asap.ui.swing.component.EPanel;

@SuppressWarnings( "serial" )
public class ELabelPanel extends EPanel {

    public ELabel label;

    public ELabelPanel( ) {
        setLayout( new BorderLayout( 0,
                                     0 ) );
        label = new ELabel( "Label panel" );
        add( label,
             BorderLayout.CENTER );
    }
}
