package asap.ui.swing.component.composite.application;

import java.awt.BorderLayout;
import java.awt.Dimension;

import asap.ui.swing.component.EFrame;
import asap.ui.swing.component.EPanel;

@SuppressWarnings( "serial" )
public abstract class AppSimpleFrame extends EFrame {

    public AppHeaderPanel appHeader;

    public EPanel         appContent;

    public EPanel         appFooter;

    public static int     DIMENSION_X = 1000;

    public static int     DIMENSION_Y = 700;

    public AppSimpleFrame( ) {
        this( null,
              null );
    }

    public AppSimpleFrame( Class< ? > appClass,
                           String logoNameExtension ) {
        this.setMinimumSize( new Dimension( DIMENSION_X,
                                            DIMENSION_Y ) );
        this.setTitle( "Título da Janela do Aplicativo" );
        appHeader = new AppHeaderPanel( appClass,
                                        logoNameExtension );
        getContentPane( ).add( appHeader,
                               BorderLayout.NORTH );
        appContent = new EPanel( );
        getContentPane( ).add( appContent,
                               BorderLayout.CENTER );
        appContent.setLayout( new BorderLayout( 0,
                                                0 ) );
        appFooter = new EPanel( );
        getContentPane( ).add( appFooter,
                               BorderLayout.SOUTH );
        appFooter.setLayout( new BorderLayout( 0,
                                               0 ) );
    }
}
