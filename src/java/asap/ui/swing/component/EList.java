package asap.ui.swing.component;

import javax.swing.JList;

import asap.primitive.pattern.Lambdas.EventListener;

@SuppressWarnings( "serial" )
public class EList extends JList< String > {

    protected EventListener selectListener;

    public EList( ) {
        super( );
        this._construct( );
    }

    private void _construct( ) {
        this.setPrototypeCellValue( "M" );
        //
        this.onSelect( null );
        //
        this.addListSelectionListener( ( event ) -> {
            if ( ( event.getValueIsAdjusting( ) == false ) && ( EList.this.selectListener != null ) ) {
                if ( EList.this.selectListener != null ) {
                    EList.this.selectListener.happened( );
                }
            }
        } );
    }

    public void onSelect( EventListener selectListener ) {
        this.selectListener = selectListener;
    }
}
