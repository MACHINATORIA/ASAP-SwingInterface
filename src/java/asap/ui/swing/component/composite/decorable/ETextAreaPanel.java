package asap.ui.swing.component.composite.decorable;

import java.awt.BorderLayout;
import java.awt.Insets;

import asap.ui.swing.component.EPanel;
import asap.ui.swing.component.ETextArea;

@SuppressWarnings( "serial" )
public class ETextAreaPanel extends EPanel {

    public ETextArea area;

    public ETextAreaPanel( ) {
        this.setLayout( new BorderLayout( 0,
                                          0 ) );
        area = new ETextArea( );
        add( area,
             BorderLayout.CENTER );
        area.setText( "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." );
        area.setMargin( new Insets( 2,
                                    5,
                                    1,
                                    5 ) );
    }
}
