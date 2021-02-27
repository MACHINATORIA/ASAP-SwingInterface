package asap.ui.swing.component.composite.decorable;

import java.awt.BorderLayout;
import java.awt.SystemColor;

import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import asap.ui.swing.component.EPanel;
import asap.ui.swing.component.EScrollPane;
import asap.ui.swing.component.ETextArea;

@SuppressWarnings( "serial" )
public class ETextAreaScrollPanel extends EPanel {

    public ETextArea   area;

    public EScrollPane scroll;

    public ETextAreaScrollPanel( ) {
        setLayout( new BorderLayout( 0,
                                     0 ) );
        scroll = new EScrollPane( );
        add( scroll,
             BorderLayout.CENTER );
        area = new ETextArea( );
        area.setFont( UIManager.getFont( "TextField.font" ) );
        area.setWrapStyleWord( true );
        area.setLineWrap( true );
        area.setBackground( SystemColor.control );
        area.setBorder( new EmptyBorder( 2,
                                         5,
                                         3,
                                         5 ) );
        area.setText( "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum." );
        area.setEditable( false );
        scroll.setViewportView( area );
    }
}
