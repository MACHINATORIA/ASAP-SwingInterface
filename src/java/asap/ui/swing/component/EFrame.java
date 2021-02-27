package asap.ui.swing.component;

import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import asap.primitive.pattern.Lambdas.EventListener;

@SuppressWarnings( "serial" )
public class EFrame extends JFrame {

    protected EventListener closeListener;

    protected EventListener moveListener;

    protected EventListener resizeListener;

    protected Rectangle lastMoveBounds = null;

    public EFrame( ) {
        super( );
        this._construct( );
    }

    public EFrame( String caption ) {
        super( caption );
        this._construct( );
    }

    private void _construct( ) {
        this.setLocationRelativeTo( null );
        //
        this.onClose( null );
        this.onMove( null );
        this.onResize( null );
        //
        this.addWindowListener( new WindowAdapter( ) {

            public void windowClosing( WindowEvent e ) {
                if ( EFrame.this.closeListener != null ) {
                    EFrame.this.closeListener.happened( );
                }
            }
        } );
        this.addComponentListener( new ComponentAdapter( ) {

            public void componentMoved( ComponentEvent e ) {
                if ( EFrame.this.moveListener != null ) {
                    Rectangle tmpBounds = EFrame.this.getBounds( );
                    if ( !tmpBounds.equals( EFrame.this.lastMoveBounds ) ) {
                        EFrame.this.lastMoveBounds = tmpBounds;
                        EFrame.this.moveListener.happened( );
                    }
                }
            }

            @Override
            public void componentResized( ComponentEvent arg0 ) {
                if ( EFrame.this.resizeListener != null ) {
                    EFrame.this.resizeListener.happened( );
                }
            }
        } );
    }

    public void onClose( EventListener closeListener ) {
        this.setDefaultCloseOperation( ( closeListener == null ) ? JFrame.EXIT_ON_CLOSE
                                                                 : JFrame.DO_NOTHING_ON_CLOSE );
        this.closeListener = closeListener;
    }

    public void onMove( EventListener moveListener ) {
        this.moveListener = moveListener;
    }

    public void onResize( EventListener resizeListener ) {
        this.resizeListener = resizeListener;
    }
}
