package asap.ui.swing.component;

import asap.primitive.pattern.Lambdas.ErrorListener;
import asap.primitive.pattern.Lambdas.EventListener;

public interface ETextComponent extends EComponent {

    public static enum ErrorType {
        InvalidCharacter,
        InvalidDragOperation,
        InvalidDropOperation,
        InvalidCopyOperation,
        InvalidCutOperation,
        InvalidPasteOperation,
        InvalidPastePosition,
        InvalidPasteContent,
    }

    @SuppressWarnings( "rawtypes" )
    public ETextExtension getExtension( );

    public boolean isTranferEnabled( );

    public void setTransferEnabled( boolean transferEnabled );

    public void onError( ErrorListener< ErrorType > errorListener );

    public void onChange( EventListener changeListener );
}
