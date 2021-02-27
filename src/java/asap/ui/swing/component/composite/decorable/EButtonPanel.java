package asap.ui.swing.component.composite.decorable;

import java.awt.BorderLayout;

import asap.ui.swing.component.EButton;
import asap.ui.swing.component.EPanel;

@SuppressWarnings( "serial" )
public class EButtonPanel extends EPanel {

    public EButton button;

    public EButtonPanel( ) {
        setLayout( new BorderLayout( 0,
                                     0 ) );
        button = new EButton( "Button panel" );
        add( button,
             BorderLayout.CENTER );
    }
}
