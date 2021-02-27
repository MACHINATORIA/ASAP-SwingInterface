package asap.ui.swing.component.composite.decorable;

import java.awt.BorderLayout;

import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import asap.ui.swing.component.EList;
import asap.ui.swing.component.EPanel;
import asap.ui.swing.component.EScrollPane;

@SuppressWarnings( "serial" )
public class EListPanel extends EPanel {

    public EList       list;

    public EScrollPane scroll;

    public EListPanel( ) {
        setLayout( new BorderLayout( 0,
                                     0 ) );
        scroll = new EScrollPane( );
        add( scroll,
             BorderLayout.CENTER );
        list = new EList( );
        list.setBorder( new EmptyBorder( 2,
                                         3,
                                         2,
                                         3 ) );
        list.setVisibleRowCount( 2 );
        list.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        list.setModel( new AbstractListModel< String >( ) {

            String[ ] values = new String[ ] { "0-Item '99-99-99-99'",
                                               "0-File '2000-01-01-Start.xml'",
                                               "0-File '2000-01-01-Primary.xml'",
                                               "0-File '2000-01-01-Secondary.xml'",
                                               "0-File '2000-01-01-Finish.xml'",
                                               "1-Item '99-99-99-99'",
                                               "1-File '2000-01-01-Start.xml'",
                                               "1-File '2000-01-01-Primary.xml'",
                                               "1-File '2000-01-01-Secondary.xml'",
                                               "1-File '2000-01-01-Finish.xml'",
                                               "2-Item '99-99-99-99'",
                                               "2-File '2000-01-01-Start.xml'",
                                               "2-File '2000-01-01-Primary.xml'",
                                               "2-File '2000-01-01-Secondary.xml'",
                                               "2-File '2000-01-01-Finish.xml'",
                                               "3-Item '99-99-99-99'",
                                               "3-File '2000-01-01-Start.xml'",
                                               "3-File '2000-01-01-Primary.xml'",
                                               "3-File '2000-01-01-Secondary.xml'",
                                               "3-File '2000-01-01-Finish.xml'",
                                               "4-Item '99-99-99-99'",
                                               "4-File '2000-01-01-Start.xml'",
                                               "4-File '2000-01-01-Primary.xml'",
                                               "4-File '2000-01-01-Secondary.xml'",
                                               "4-File '2000-01-01-Finish.xml'", //
            };

            @Override
            public int getSize( ) {
                return values.length;
            }

            @Override
            public String getElementAt( int index ) {
                return values[ index ];
            }
        } );
        scroll.setViewportView( list );
    }
}
