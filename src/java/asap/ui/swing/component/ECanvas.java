package asap.ui.swing.component;

import java.awt.Canvas;
import java.awt.Graphics;

import asap.primitive.pattern.Lambdas.VoidHandler;

@SuppressWarnings( "serial" )
public class ECanvas extends Canvas {

    protected VoidHandler< Graphics > paintHandler;

    protected VoidHandler< Graphics > updateHandler;

    public ECanvas( ) {
        super( );
        this.onPaint( null );
    }

    @Override
    public void paint( Graphics graphics ) {
        if ( this.paintHandler != null ) {
            this.paintHandler.handle( graphics );
        }
    }

    @Override
    public void update( Graphics graphics ) {
        super.update( graphics );
        if ( this.updateHandler != null ) {
            this.updateHandler.handle( graphics );
        }
    }

    public void onPaint( VoidHandler< Graphics > paintHandler ) {
        this.paintHandler = paintHandler;
    }

    public void onUpdate( VoidHandler< Graphics > updateHandler ) {
        this.updateHandler = updateHandler;
    }
}
