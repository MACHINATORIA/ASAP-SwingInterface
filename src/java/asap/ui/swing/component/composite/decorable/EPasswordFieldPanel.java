package asap.ui.swing.component.composite.decorable;

import java.awt.BorderLayout;

import asap.ui.swing.component.EPanel;
import asap.ui.swing.component.EPasswordField;

@SuppressWarnings( "serial" )
public class EPasswordFieldPanel extends EPanel {

    public EPasswordField field;

    public EPasswordFieldPanel( ) {
        this.setLayout( new BorderLayout( 0,
                                          0 ) );
        field = new EPasswordField( );
        add( field,
             BorderLayout.CENTER );
    }
}
