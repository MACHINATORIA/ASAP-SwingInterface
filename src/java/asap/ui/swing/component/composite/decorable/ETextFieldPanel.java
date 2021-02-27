package asap.ui.swing.component.composite.decorable;

import java.awt.BorderLayout;
import java.awt.Insets;

import asap.ui.swing.component.EPanel;
import asap.ui.swing.component.ETextField;

@SuppressWarnings( "serial" )
public class ETextFieldPanel extends EPanel {

    public ETextField field;

    public ETextFieldPanel( ) {
        setLayout( new BorderLayout( 0,
                                     0 ) );
        field = new ETextField( );
        add( field );
        field.setText( "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." );
        field.setMargin( new Insets( 2,
                                     5,
                                     1,
                                     5 ) );
    }
}
