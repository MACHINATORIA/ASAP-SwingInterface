package asap.ui.swing.component.composite.decorable;

import java.awt.BorderLayout;
import java.awt.Insets;

import asap.ui.swing.component.EPanel;
import asap.ui.swing.component.ETextPane;

@SuppressWarnings( "serial" )
public class ETextPanePanel extends EPanel {

    public ETextPane pane;

    public ETextPanePanel( ) {
        setLayout( new BorderLayout( 0,
                                     0 ) );
        pane = new ETextPane( );
        add( pane );
        pane.setText( "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." );
        pane.setMargin( new Insets( 2,
                                    5,
                                    1,
                                    5 ) );
    }
}
