package asap.ui.swing.component;

import java.awt.Container;
import java.awt.Point;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.event.InputEvent;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.TooManyListenersException;

import javax.swing.DropMode;
import javax.swing.JComponent;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;

import asap.primitive.pattern.Lambdas.ErrorListener;
import asap.primitive.pattern.Lambdas.EventListener;
import asap.ui.swing.component.ETextComponent.ErrorType;

public class ETextExtension< C extends JTextComponent, T extends ETextComponent > {

    public boolean                                    copyEnabled                = false;

    public boolean                                    cutEnabled                 = false;

    public boolean                                    pasteEnabled               = false;

    public boolean                                    dragEnabled                = false;

    public boolean                                    dropEnabled                = false;

    public EventListener                                  changeHandler;

    public ErrorListener< ETextComponent.ErrorType > errorHandler;

    protected C                                       textComponent;

    protected boolean                                 changeNotificationDisabled = false;

    protected boolean                                 dropErrorNotified          = false;

    public ETextExtension( C textComponent,
                           boolean setChangeListener ) {
        this.textComponent = textComponent;
        if ( setChangeListener ) {
            this.textComponent.getDocument( ).addDocumentListener( new DocumentListener( ) {

                @Override
                public void changedUpdate( DocumentEvent docEvent ) {
                    ETextExtension.this.notifyChange( );
                }

                @Override
                public void insertUpdate( DocumentEvent docEvent ) {
                    ETextExtension.this.notifyChange( );
                }

                @Override
                public void removeUpdate( DocumentEvent docEvent ) {
                    ETextExtension.this.notifyChange( );
                }
            } );
        }
        @SuppressWarnings( "serial" )
        TransferHandler tmpTransferHandler = new TransferHandler( ) {

            @Override
            public int getSourceActions( JComponent component ) {
                return COPY_OR_MOVE;
            }

            @Override
            public boolean canImport( TransferSupport support ) {
                if ( !ETextExtension.this.dropEnabled ) {
                    if ( !ETextExtension.this.dropErrorNotified ) {
                        ETextExtension.this.dropErrorNotified = true;
                        ETextExtension.this.notifyError( ErrorType.InvalidDropOperation );
                    }
                    return false;
                }
                return support.isDataFlavorSupported( DataFlavor.stringFlavor );
            }

            @Override
            public boolean importData( TransferSupport support ) {
                if ( !support.isDrop( ) && !ETextExtension.this.pasteEnabled ) {
                    ETextExtension.this.notifyError( ErrorType.InvalidPasteOperation );
                }
                else {
                    try {
                        JTextComponent tmpTextComponent = ETextExtension.this.textComponent;
                        String tmpImportString = (String) support.getTransferable( ).getTransferData( DataFlavor.stringFlavor );
                        int tmpImportPosition;
                        if ( support.isDrop( ) ) {
                            tmpImportPosition = tmpTextComponent.viewToModel( support.getDropLocation( ).getDropPoint( ) );
                            tmpTextComponent.setCaretPosition( tmpImportPosition );
                        }
                        else {
                            tmpImportPosition = tmpTextComponent.getCaretPosition( );
                        }
                        tmpTextComponent.replaceSelection( tmpImportString );
                        tmpTextComponent.select( tmpImportPosition,
                                                 tmpTextComponent.getCaretPosition( ) );
                        return true;
                    }
                    catch ( IOException | UnsupportedFlavorException e ) {
                    }
                }
                return false;
            }

            @Override
            public void exportAsDrag( JComponent component,
                                      InputEvent event,
                                      int action ) {
                if ( !ETextExtension.this.dragEnabled ) {
                    ETextExtension.this.notifyError( ErrorType.InvalidDragOperation );
                    return;
                }
                super.exportAsDrag( component,
                                    event,
                                    action );
            }

            @Override
            public void exportToClipboard( JComponent component,
                                           Clipboard clipboard,
                                           int action )
                throws IllegalStateException {
                if ( action == TransferHandler.COPY ) {
                    if ( !ETextExtension.this.copyEnabled ) {
                        ETextExtension.this.notifyError( ErrorType.InvalidCopyOperation );
                        return;
                    }
                }
                else {
                    if ( !ETextExtension.this.cutEnabled ) {
                        ETextExtension.this.notifyError( ErrorType.InvalidCutOperation );
                        return;
                    }
                }
                super.exportToClipboard( component,
                                         clipboard,
                                         action );
            }

            @Override
            protected Transferable createTransferable( JComponent component ) {
                return new StringSelection( ETextExtension.this.textComponent.getSelectedText( ) );
            }

            @Override
            protected void exportDone( JComponent component,
                                       Transferable transferable,
                                       int action ) {
                if ( action == MOVE ) {
                    int tmpSelectionStart = ETextExtension.this.textComponent.getSelectionStart( );
                    try {
                        ETextExtension.this.textComponent.getDocument( ).remove( tmpSelectionStart,
                                                                                 ( ETextExtension.this.textComponent.getSelectionEnd( )
                                                                                   - tmpSelectionStart ) );
                    }
                    catch ( BadLocationException e ) {
                        throw new Error( e );
                    }
                }
            }
        };
        this.textComponent.setTransferHandler( tmpTransferHandler );
        this.textComponent.setDropMode( DropMode.INSERT );
        try {
            this.textComponent.getDropTarget( ).addDropTargetListener( new DropTargetAdapter( ) {

                @Override
                public void dragExit( DropTargetEvent dte ) {
                    ETextExtension.this.dropErrorNotified = false;
                }

                @Override
                public void drop( DropTargetDropEvent dtde ) {
                    ETextExtension.this.dropErrorNotified = false;
                }
            } );
        }
        catch ( TooManyListenersException e ) {
            throw new Error( e );
        }
        this.textComponent.setDropMode( DropMode.INSERT );
        this.textComponent.setDragEnabled( true );
        //
        this.onChange( null );
        this.onError( null );
    }

    public void setTextNoChange( String text ) {
        this.changeNotificationDisabled = true;
        try {
            this.textComponent.setText( text );
        }
        finally {
            this.changeNotificationDisabled = false;
        }
    }

    public C onError( ErrorListener< ErrorType > errorHandler ) {
        this.errorHandler = errorHandler;
        return this.textComponent;
    }

    public C onChange( EventListener changeHandler ) {
        this.changeHandler = changeHandler;
        return this.textComponent;
    }

    protected void notifyChange( ) {
        if ( !this.changeNotificationDisabled && ( this.changeHandler != null ) ) {
            this.changeHandler.happened( );
        }
    }

    protected void notifyError( ETextComponent.ErrorType error ) {
        if ( this.errorHandler != null ) {
            this.errorHandler.occurred( error );
        }
    }

    public void gotoLine( int lineNumber ) {
        Element tmpRootElement = this.textComponent.getDocument( ).getDefaultRootElement( );
        lineNumber = Math.max( lineNumber,
                               1 );
        lineNumber = Math.min( lineNumber,
                               tmpRootElement.getElementCount( ) );
        int tmpLineOffset = tmpRootElement.getElement( lineNumber - 1 ).getStartOffset( );
        this.textComponent.setCaretPosition( tmpLineOffset );
    }

    public void centerLine( ) {
        Container tmpContainer = SwingUtilities.getAncestorOfClass( JViewport.class,
                                                                    this.textComponent );
        if ( tmpContainer == null ) {
            return;
        }
        try {
            Rectangle2D tmpRect = this.textComponent.modelToView( this.textComponent.getCaretPosition( ) );
            JViewport tmpViewport = (JViewport) tmpContainer;
            int tmpExtentHeight = tmpViewport.getExtentSize( ).height;
            int tmpViewHeight = tmpViewport.getViewSize( ).height;
            int tmpPosY = Math.max( 0,
                                    Double.valueOf( tmpRect.getY( )
                                                    - ( ( tmpExtentHeight - tmpRect.getHeight( ) )
                                                        / 2 ) ).intValue( ) );
            tmpPosY = Math.min( tmpPosY,
                                tmpViewHeight - tmpExtentHeight );
            tmpViewport.setViewPosition( new Point( 0,
                                                    tmpPosY ) );
        }
        catch ( BadLocationException ble ) {
        }
    }

    public boolean isTransferEnabled( ) {
        return ( this.copyEnabled //
                 && this.cutEnabled
                 && this.pasteEnabled
                 && this.dragEnabled
                 && this.dropEnabled );
    }

    public C setTransferEnabled( boolean transferEnabled ) {
        this.copyEnabled = transferEnabled;
        this.cutEnabled = transferEnabled;
        this.pasteEnabled = transferEnabled;
        this.dragEnabled = transferEnabled;
        this.dropEnabled = transferEnabled;
        return this.textComponent;
    }

    public boolean isCopyEnabled( ) {
        return this.copyEnabled;
    }

    public void setCopyEnabled( boolean copyEnabled ) {
        this.copyEnabled = copyEnabled;
    }

    public boolean isCutEnabled( ) {
        return this.cutEnabled;
    }

    public void setCutEnabled( boolean cutEnabled ) {
        this.cutEnabled = cutEnabled;
    }

    public boolean isPasteEnabled( ) {
        return this.pasteEnabled;
    }

    public void setPasteEnabled( boolean pasteEnabled ) {
        this.pasteEnabled = pasteEnabled;
    }

    public boolean isDragEnabled( ) {
        return this.dragEnabled;
    }

    public void setDragEnabled( boolean dragEnabled ) {
        this.dragEnabled = dragEnabled;
    }

    public boolean isDropEnabled( ) {
        return this.dropEnabled;
    }

    public void setDropEnabled( boolean dropEnabled ) {
        this.dropEnabled = dropEnabled;
    }
}
