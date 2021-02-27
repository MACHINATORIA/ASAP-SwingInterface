package asap.ui.swing.component;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.AbstractAction;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.DocumentFilter;
import javax.swing.text.JTextComponent;
import javax.swing.text.NavigationFilter;
import javax.swing.text.Position;

import asap.primitive.log.LogService.LogManager;
import asap.primitive.log.LogService.Logger;
import asap.primitive.pattern.Lambdas.ErrorListener;
import asap.primitive.pattern.Lambdas.EventListener;
import asap.primitive.string.StringHelper;
import asap.ui.swing.component.ETextField.AbstractFilter.OverwriteCaret;

@SuppressWarnings( "serial" )
public class ETextField extends JTextField implements ETextComponent {

    protected static Logger filterLog = LogManager.getDesenvLogger( ETextField.class );

    protected static Logger caretLog  = LogManager.getDesenvLogger( OverwriteCaret.class );

    public static enum ContentType {
        FreeText,
        //Integer,
        HexaBytes,
        AESKey,
        //Currency,
        //Date,
        //Horary,
        //DateHorary,
    }

    public static abstract class AbstractFilter {

        protected ETextField          field;

        protected DocumentFilter      documentFilter;

        protected boolean             overwriteEnabled        = false;

        protected static final String TOGGLE_OVERWRITE_ACTION = "toggleOverwrite";

        protected AbstractFilter( ETextField field ) {
            this.field = field;
            //
            this.field.setCaret( new OverwriteCaret( ) );
            this.field.getInputMap( ).put( KeyStroke.getKeyStroke( KeyEvent.VK_INSERT,
                                                                   0 ),
                                           TOGGLE_OVERWRITE_ACTION );
            this.field.getActionMap( ).put( TOGGLE_OVERWRITE_ACTION,
                                            new AbstractAction( ) {

                                                @Override
                                                public void actionPerformed( ActionEvent e ) {
                                                    OverwriteCaret tmpCaret = (OverwriteCaret) AbstractFilter.this.field.getCaret( );
                                                    AbstractFilter.this.setOverwriteEnabled( !AbstractFilter.this.isOverwriteEnabled( ) );
                                                    tmpCaret.update( );
                                                }
                                            } );
            //
            ( (AbstractDocument) this.field.getDocument( ) ).setDocumentFilter( new DocumentFilter( ) {

                @Override
                public void insertString( DocumentFilter.FilterBypass filterBypass,
                                          int offset,
                                          String string,
                                          AttributeSet attr )
                    throws BadLocationException {
                    AbstractFilter.this.docFilterInsert( filterBypass,
                                                         offset,
                                                         string,
                                                         attr );
                }

                public void replace( DocumentFilter.FilterBypass filterBypass,
                                     int offset,
                                     int length,
                                     String string,
                                     AttributeSet attr )
                    throws BadLocationException {
                    AbstractFilter.this.docFilterReplace( filterBypass,
                                                          offset,
                                                          length,
                                                          string,
                                                          attr );
                }

                @Override
                public void remove( DocumentFilter.FilterBypass filterBypass,
                                    int offset,
                                    int length )
                    throws BadLocationException {
                    AbstractFilter.this.docFilterRemove( filterBypass,
                                                         offset,
                                                         length );
                }
            } );
            //
            this.field.setNavigationFilter( new NavigationFilter( ) {

                @Override
                public void setDot( NavigationFilter.FilterBypass filterBypass,
                                    int dot,
                                    Position.Bias bias ) {
                    AbstractFilter.this.navFilterSetDot( filterBypass,
                                                         dot,
                                                         bias );
                }

                @Override
                public void moveDot( NavigationFilter.FilterBypass filterBypass,
                                     int dot,
                                     Position.Bias bias ) {
                    AbstractFilter.this.navFilterMoveDot( filterBypass,
                                                          dot,
                                                          bias );
                }

                @Override
                public int getNextVisualPositionFrom( JTextComponent text,
                                                      int pos,
                                                      Position.Bias bias,
                                                      int direction,
                                                      Position.Bias[ ] biasRet )
                    throws BadLocationException {
                    return AbstractFilter.this.navFilterNextPosition( super.getNextVisualPositionFrom( text,
                                                                                                       pos,
                                                                                                       bias,
                                                                                                       direction,
                                                                                                       biasRet ),
                                                                      pos,
                                                                      direction );
                }
            } );
        }

        public boolean isOverwriteEnabled( ) {
            return this.overwriteEnabled;
        }

        public AbstractFilter setOverwriteEnabled( boolean overwriteEnabled ) {
            this.overwriteEnabled = overwriteEnabled;
            return this;
        }

        protected boolean isOverwriteOverrided( ) {
            return false;
        }

        protected boolean isOverwriteActivated( ) {
            return ( this.isOverwriteOverrided( ) || this.overwriteEnabled );
        }

        protected void docFilterInsert( DocumentFilter.FilterBypass filterBypass,
                                        int offset,
                                        String string,
                                        AttributeSet attr )
            throws BadLocationException {
            filterBypass.insertString( offset,
                                       string,
                                       attr );
            this.field.extension.notifyChange( );
        }

        protected void docFilterReplace( DocumentFilter.FilterBypass filterBypass,
                                         int offset,
                                         int length,
                                         String string,
                                         AttributeSet attr )
            throws BadLocationException {
            int tmpLength = length;
            if ( this.isOverwriteActivated( )
                 && ( tmpLength == 0 )
                 && ( offset < this.field.getDocument( ).getLength( ) ) ) {
                tmpLength = 1;
            }
            filterBypass.replace( offset,
                                  tmpLength,
                                  string,
                                  attr );
            this.field.extension.notifyChange( );
        }

        protected void docFilterRemove( DocumentFilter.FilterBypass filterBypass,
                                        int offset,
                                        int length )
            throws BadLocationException {
            filterBypass.remove( offset,
                                 length );
            this.field.extension.notifyChange( );
        }

        protected void navFilterSetDot( NavigationFilter.FilterBypass filterBypass,
                                        int dot,
                                        Position.Bias bias ) {
            filterBypass.setDot( dot,
                                 bias );
        }

        protected void navFilterMoveDot( NavigationFilter.FilterBypass filterBypass,
                                         int dot,
                                         Position.Bias bias ) {
            filterBypass.moveDot( dot,
                                  bias );
        }

        protected Integer navFilterNextPosition( int defaultPosition,
                                                 int pos,
                                                 int direction ) {
            return defaultPosition;
        }

        protected class OverwriteCaret extends DefaultCaret {

            protected int paintWidth = ( -1 );

            public OverwriteCaret( ) {
                this.setBlinkRate( AbstractFilter.this.field.getCaret( ).getBlinkRate( ) );
            }

            protected void update( ) {
                if ( this.isActive( ) ) {
                    this.setVisible( false );
                    this.setVisible( true );
                }
            }

            @Override
            public void paint( Graphics graphics ) {
                JTextComponent tmpComponent = this.getComponent( );
                if ( tmpComponent == null ) {
                    return;
                }
                int tmpDotPos = this.getDot( );
                try {
                    Rectangle2D tmpDotRect = tmpComponent.modelToView( tmpDotPos );
                    if ( tmpDotRect == null ) {
                        return;
                    }
                    if ( ( this.x != tmpDotRect.getX( ) ) || ( this.y != tmpDotRect.getY( ) ) ) {
                        this.x = java.lang.Double.valueOf( tmpDotRect.getX( ) ).intValue( );
                        this.y = java.lang.Double.valueOf( tmpDotRect.getY( ) ).intValue( );
                        this.height = java.lang.Double.valueOf( tmpDotRect.getHeight( ) ).intValue( );
                    }
                    this.paintWidth = ( -1 );
                    Rectangle tmpClipBounds = graphics.getClipBounds( );
                    if ( tmpClipBounds.height == tmpDotRect.getHeight( ) ) {
                        char tmpDotChar = tmpComponent.getText( tmpDotPos,
                                                                1 ).charAt( 0 );
                        //
                        if ( AbstractFilter.this.isOverwriteActivated( )
                             && ( tmpDotPos < tmpComponent.getDocument( ).getLength( ) ) ) {
                            int tmpDotWidth;
                            if ( tmpDotChar == '\n' ) {
                                tmpDotWidth = graphics.getFontMetrics( ).charWidth( ' ' );
                            }
                            else if ( tmpDotChar == '\t' ) {
                                Rectangle2D tmpNextDotRect = tmpComponent.modelToView( tmpDotPos + 1 );
                                if ( ( tmpNextDotRect != null )
                                     && ( tmpDotRect.getY( ) == tmpNextDotRect.getY( ) )
                                     && ( tmpDotRect.getX( ) < tmpNextDotRect.getX( ) ) ) {
                                    tmpDotWidth = java.lang.Double.valueOf( tmpNextDotRect.getX( )
                                                                            - tmpDotRect.getX( ) ).intValue( );
                                }
                                else {
                                    tmpDotWidth = graphics.getFontMetrics( ).charWidth( ' ' );
                                }
                            }
                            else {
                                tmpDotWidth = graphics.getFontMetrics( ).charWidth( tmpDotChar );
                            }
                            this.width = tmpDotWidth;
                        }
                        else {
                            this.width = 1;
                        }
                        graphics.setColor( tmpComponent.getCaretColor( ) );
                        graphics.setXORMode( tmpComponent.getBackground( ) );
                        if ( this.isVisible( ) ) {
                            this.paintWidth = this.width;
                            if ( caretLog.isTraceEnabled( ) ) {
                                caretLog.trace( "clip( %s ) -  paint( %s )",
                                                this.rectDump( tmpClipBounds ),
                                                this.rectDump( this ) );
                            }
                            graphics.fillRect( this.x,
                                               this.y,
                                               this.width,
                                               this.height );
                        }
                    }
                }
                catch ( BadLocationException e ) {
                    return;
                }
            }

            @Override
            protected synchronized void damage( Rectangle rectangle ) {
                if ( caretLog.isTraceEnabled( ) ) {
                    caretLog.trace( "rect( %s ) - damage( %s )",
                                    this.rectDump( rectangle ),
                                    this.rectDump( this ) );
                }
                if ( rectangle != null ) {
                    if ( true && ( this.paintWidth > 0 ) ) {
                        rectangle.width = this.paintWidth;
                        this.paintWidth = ( -1 );
                    }
                    this.x = rectangle.x;
                    this.y = rectangle.y;
                    this.height = rectangle.height;
                    this.width = rectangle.width;
                    this.repaint( );
                }
                super.damage( rectangle );
            }

            protected String rectDump( Rectangle rect ) {
                return ( rect == null ) ? StringHelper.NULL
                                        : String.format( "x: %3d - window: %3d - y: %3d - h: %3d",
                                                         rect.x,
                                                         rect.width,
                                                         rect.y,
                                                         rect.height );
            }
        }
    }

    public static class FreeFilter extends AbstractFilter {

        public FreeFilter( ETextField field ) {
            super( field );
        }
    }

    public static class HexadecimalFilter extends AbstractFilter {

        protected static Pattern hexaSpacePattern      = Pattern.compile( "[\\h\\v]+" );

        protected static Pattern hexaDigitPattern      = Pattern.compile( "[0-9A-Fa-f]" );

        protected static Pattern hexaBlockPattern      = Pattern.compile( "(?:0x)?([0-9A-Fa-f]+)" );

        protected int            digitsPerBlock;

        protected int            spacesBtwBlocks;

        protected int            maximumBlocks;

        protected int            blockLength;

        protected boolean        navFilterIgnore       = false;

        protected int            navFilterForceTarget  = ( -1 );

        protected int            navFilterForceTrigger = ( -1 );

        protected volatile int   navFilterLastSetDot   = ( -1 );

        protected volatile int   navFilterLastMoveDot  = ( -1 );

        public HexadecimalFilter( ETextField field,
                                  int digitsPerBlock,
                                  int spacesBtwBlocks,
                                  int maximumBlocks ) {
            super( field );
            this.digitsPerBlock = digitsPerBlock;
            this.spacesBtwBlocks = spacesBtwBlocks;
            this.maximumBlocks = maximumBlocks;
            this.paramsChanged( );
        }

        public int getDigitsPerBlock( ) {
            return this.digitsPerBlock;
        }

        public HexadecimalFilter setDigitsPerBlock( int digitsPerBlock ) {
            this.digitsPerBlock = digitsPerBlock;
            this.paramsChanged( );
            return this;
        }

        public int getSpacesBtwBlocks( ) {
            return this.spacesBtwBlocks;
        }

        public HexadecimalFilter setSpacesBtwBlocks( int spacesBtwBlocks ) {
            this.spacesBtwBlocks = spacesBtwBlocks;
            this.paramsChanged( );
            return this;
        }

        public int getMaximumBlocks( ) {
            return this.maximumBlocks;
        }

        public HexadecimalFilter setMaximumBlocks( int maximumBlocks ) {
            this.maximumBlocks = maximumBlocks;
            this.paramsChanged( );
            return this;
        }

        protected void paramsChanged( ) {
            this.blockLength = ( this.digitsPerBlock + this.spacesBtwBlocks );
        }

        protected int computeBlockNum( int cursorPosition ) {
            return ( cursorPosition / this.blockLength );
        }

        protected int computeBlockPosition( int cursorPosition ) {
            return ( cursorPosition - ( cursorPosition % this.blockLength ) );
        }

        protected int computeBlockOffset( int cursorPosition ) {
            return ( cursorPosition % this.blockLength );
        }

        @Override
        protected boolean isOverwriteOverrided( ) {
            return ( this.computeBlockOffset( this.field.getCaretPosition( ) ) > 0 );
        }

        @Override
        protected void docFilterInsert( DocumentFilter.FilterBypass filterBypass,
                                        int offset,
                                        String string,
                                        AttributeSet attr )
            throws BadLocationException {
            // Não permite
        }

        @Override
        protected void docFilterRemove( DocumentFilter.FilterBypass filterBypass,
                                        int offset,
                                        int length )
            throws BadLocationException {
            int tmpOffset = offset;
            int tmpLength = length;
            int tmpCaretPosition = this.field.getCaretPosition( );
            if ( tmpLength == 1 ) {
                /*
                 * Cursor   BackSpc     Bloco
                 * antes      nao       atual
                 * antes      sim       anterior
                 * meio       ---       ignora
                 * depois     nao       posterior
                 * depois     sim       atual
                 */
                // Sobreescrita de um caracter
                int tmpBlockOffset = this.computeBlockOffset( tmpCaretPosition );
                boolean tmpIsBackspace = ( tmpOffset < tmpCaretPosition );
                // Remova sempre um bloco inteiro
                tmpLength = this.blockLength;
                // Prepare para remover o bloco em que esta o cursor
                tmpOffset = ( tmpCaretPosition - tmpBlockOffset );
                if ( tmpBlockOffset == 0 ) {
                    // O cursor esta antes do primeiro digito
                    if ( tmpIsBackspace ) {
                        // Se for backspace remova o bloco anterior ao cursor
                        tmpOffset -= this.blockLength;
                    }
                }
                else if ( tmpBlockOffset >= this.digitsPerBlock ) {
                    // O cursor esta depois do ultimo digito
                    if ( !tmpIsBackspace ) {
                        // Se nao for backspace remova o bloco posterior ao cursor
                        tmpOffset += this.blockLength;
                    }
                }
                else {
                    // O cursor está entre os dígitos
                    // Não remova nada
                    tmpLength = 0;
                    // Restaure o parametro 'offset'
                    tmpOffset = offset;
                }
            }
            else {
                // Sobreescrita de um texto colado
            }
            if ( tmpOffset < 0 ) {
                // Descarte a regiao da remocao anterior ao inicio do texto
                tmpLength += tmpOffset;
                if ( tmpLength < 0 ) {
                    tmpLength = 0;
                }
                tmpOffset = 0;
            }
            else {
                int tmpTextLength = this.field.getDocument( ).getLength( );
                if ( ( tmpOffset + tmpLength ) >= tmpTextLength ) {
                    // Se atingir o final remova a partir do ultimo digito do bloco anterior
                    tmpOffset -= this.spacesBtwBlocks;
                    if ( tmpOffset < 0 ) {
                        tmpOffset = 0;
                    }
                    tmpLength = ( tmpTextLength - tmpOffset );
                }
            }
            if ( filterLog.isTraceEnabled( ) ) {
                filterLog.trace( this.logDocOperation( offset,
                                                       tmpOffset,
                                                       length,
                                                       tmpLength ) );
            }
            if ( tmpLength > 0 ) {
                filterBypass.remove( tmpOffset,
                                     tmpLength );
                this.field.extension.notifyChange( );
            }
        }

        /*
         * string.length == 1 (caracter digitado)
         * Selecao Posicao Overwrite Ação                          EspAntes Caracter Zeros EspDepois SetOvwr NavFilter Obervação
         *    0     antes     não    Insere novo bloco                -       SIM     sim     sim*     -       forca   *: se não estiver no final
         *    0     antes     sim    Sobreescreve primeiro dígito     -       SIM      -       -      sim      ignora 
         *    0     meio     irrel   Sobreescreve dígitos do meio     -       SIM      -       -      sim      ignora 
         *    0     último   irrel   Sobreescreve último dígito       -       SIM      -       -      sim        -  
         *    0     depois*  irrel   Adiciona novo bloco             sim      SIM     sim      -       -       forca   *: navFilter só deve permitir no último bloco
         *   > 0    antes*   irrel   Substitui por novo bloco         -       SIM     sim     sim**    -       forca   *: navFilter deve forçar posição, **: se não atingir o final
         *    
         * string.length > 1 (texto colado)
         * length Posicao Overwrite Ação                          EspAntes Texto EspDepois length  Observações
         *    0     antes     não   insere blocos                    não     sim    sim*    nao    *: se não estiver no final
         *    0     antes     sim   sobreescreve blocos              não     sim    não     sim*   *: seleciona tamanho igual
         *    0     meio     irrel  ignora modificação               não     não    não      -     notifica erro
         *    0     depois*  irrel  adiciona blocos                  sim     sim    não     nao    *: navFilter só permite no último bloco     
         *   > 0    antes*   irrel  substitui blocos                 não     sim    sim**   nao    *: navFilter força posição, **: se não atingir o final
         *   
         */
        @Override
        protected void docFilterReplace( DocumentFilter.FilterBypass filterBypass,
                                         int offset,
                                         int length,
                                         String string,
                                         AttributeSet attr )
            throws BadLocationException {
            int tmpOffset = offset;
            int tmpLength = length;
            //
            int tmpTotalLength = this.field.getDocument( ).getLength( );
            int tmpBlockOffset = this.computeBlockOffset( offset );
            StringBuilder tmpContent = new StringBuilder( );
            if ( string.length( ) == 1 ) {
                // Um caracter digitado
                if ( !hexaDigitPattern.matcher( string ).matches( ) ) {
                    // Emitir notificação de erro: caracter inválido 
                    this.field.extension.notifyError( ErrorType.InvalidCharacter );
                    return;
                }
                //
                boolean tmpPrependSpaces = false;
                boolean tmpAppendZeroes = false;
                boolean tmpAppendSpaces = false;
                boolean tmpSetOverwrite = false;
                boolean tmpNavFilterForce = false;
                // Verifica as condições e seleciona as operações (conforme comentário inicial)
                if ( tmpLength == 0 ) {
                    if ( tmpBlockOffset == 0 ) {
                        if ( ( tmpTotalLength == 0 ) || !this.isOverwriteEnabled( ) ) {
                            // Insere novo bloco
                            tmpAppendZeroes = true;
                            tmpAppendSpaces = true;
                            tmpNavFilterForce = true;
                        }
                        else {
                            // Sobreescreve primeiro dígito
                            tmpSetOverwrite = true;
                            this.navFilterIgnore = true;
                        }
                    }
                    else if ( tmpBlockOffset < ( this.digitsPerBlock - 1 ) ) {
                        // Sobreescreve dígitos do meio
                        tmpSetOverwrite = true;
                        this.navFilterIgnore = true;
                    }
                    else if ( tmpBlockOffset == ( this.digitsPerBlock - 1 ) ) {
                        // Sobreescreve último dígito
                        tmpSetOverwrite = true;
                    }
                    else {
                        // Adiciona novo bloco
                        tmpPrependSpaces = true;
                        tmpAppendZeroes = true;
                        tmpNavFilterForce = true;
                    }
                }
                else {
                    // Substitui por novo bloco
                    tmpAppendZeroes = true;
                    tmpAppendSpaces = true;
                    tmpNavFilterForce = true;
                }
                // Executa as operações selecionadas (conforme comentário inicial)
                if ( tmpPrependSpaces ) {
                    tmpContent.append( StringHelper.spaces( this.blockLength - tmpBlockOffset ) );
                }
                //
                tmpContent.append( string.toUpperCase( ) );
                //
                if ( tmpNavFilterForce ) {
                    this.navFilterForceTarget = ( tmpOffset + tmpContent.length( ) );
                }
                //
                if ( tmpAppendZeroes ) {
                    tmpContent.append( StringHelper.repeatedChar( ( this.digitsPerBlock - 1 ),
                                                                  '0' ) );
                }
                if ( tmpAppendSpaces ) {
                    if ( ( tmpLength == 0 ) ? ( tmpOffset < tmpTotalLength )
                                            : ( ( tmpOffset + tmpLength ) < tmpTotalLength ) ) {
                        tmpContent.append( StringHelper.spaces( this.spacesBtwBlocks ) );
                    }
                }
                if ( tmpNavFilterForce ) {
                    this.navFilterForceTrigger = ( tmpOffset + tmpContent.length( ) );
                }
                //
                if ( tmpSetOverwrite ) {
                    tmpLength += 1;
                }
            }
            else {
                // Texto colado
                if ( ( tmpBlockOffset > 0 ) && ( tmpBlockOffset < this.digitsPerBlock ) ) {
                    // Emitir notificação de erro: só é permitido colar antes do primeiro dígito ou depois do último
                    this.field.extension.notifyError( ErrorType.InvalidPastePosition );
                }
                else {
                    //
                    boolean tmpPrependSpaces = false;
                    boolean tmpAppendSpaces = false;
                    boolean tmpSetOverwrite = false;
                    // Verifica as condições e seleciona as operações (conforme comentário inicial)
                    if ( tmpLength == 0 ) {
                        if ( tmpBlockOffset == 0 ) {
                            if ( !this.isOverwriteEnabled( ) ) {
                                // Insere blocos
                                tmpAppendSpaces = true;
                            }
                            else {
                                // Sobreescreve blocos
                                tmpSetOverwrite = true;
                            }
                        }
                        else if ( tmpBlockOffset < this.digitsPerBlock ) {
                            // Ignora modificação
                            return;
                        }
                        else {
                            // Adiciona blocos
                            tmpPrependSpaces = true;
                        }
                    }
                    else if ( string.length( ) > 0 ) {
                        // Substitui blocos
                        tmpAppendSpaces = true;
                    }
                    // Executa as operações selecionadas (conforme comentário inicial)
                    if ( tmpPrependSpaces ) {
                        tmpContent.append( StringHelper.spaces( this.blockLength - tmpBlockOffset ) );
                    }
                    // Interpreta e formata o conteúdo colado
                    int tmpDigitCount = 0;
                    String[ ] tmpSegments = hexaSpacePattern.split( string,
                                                                    -1 );
                    for ( String tmpSegment : tmpSegments ) {
                        if ( tmpSegment.length( ) == 0 ) {
                            // Ignore segmentos de tamanho zero
                            continue;
                        }
                        // Para cada conjunto de caracteres separados por um ou mais espaços de qualquer tipo
                        Matcher tmpBlockMatcher = hexaBlockPattern.matcher( tmpSegment );
                        if ( !tmpBlockMatcher.matches( ) ) {
                            tmpContent.delete( 0,
                                               tmpContent.length( ) );
                            // Emite notificação de erro: bloco de caracteres não é um padrão numérico hexadecimal
                            this.field.extension.notifyError( ErrorType.InvalidPasteContent );
                            return;
                        }
                        // Processe apenas os dígitos
                        tmpSegment = tmpBlockMatcher.group( 1 );
                        // Formate o segmento em blocos
                        int tmpSegmentAlign = ( tmpSegment.length( ) % this.digitsPerBlock );
                        if ( tmpSegmentAlign != 0 ) {
                            // O tamanho do segmento não é múltiplo da quantidade de dígitos por bloco
                            // Adiciona zeros à esquerda para completar o primeiro bloco
                            tmpSegment = StringHelper.repeatedChar( ( this.digitsPerBlock - tmpSegmentAlign ),
                                                                    '0' ).concat( tmpSegment );
                        }
                        // Percorre os caracters do segmento
                        for ( char tmpChar : tmpSegment.toCharArray( ) ) {
                            if ( ( ( tmpDigitCount % this.digitsPerBlock ) == 0 ) && ( tmpDigitCount > 0 ) ) {
                                tmpContent.append( StringHelper.spaces( this.spacesBtwBlocks ) );
                            }
                            tmpContent.append( Character.toUpperCase( tmpChar ) );
                            tmpDigitCount += 1;
                        }
                    }
                    //
                    if ( tmpAppendSpaces ) {
                        if ( ( tmpLength == 0 ) ? ( tmpOffset < tmpTotalLength )
                                                : ( ( tmpOffset + tmpLength ) < tmpTotalLength ) ) {
                            tmpContent.append( StringHelper.spaces( this.spacesBtwBlocks ) );
                        }
                    }
                    //
                    if ( tmpSetOverwrite ) {
                        int tmpContentEnd = ( tmpOffset + tmpContent.length( ) );
                        if ( tmpContentEnd > tmpTotalLength ) {
                            tmpContentEnd = tmpTotalLength;
                        }
                        tmpLength = ( tmpContentEnd - tmpOffset );
                    }
                }
            }
            if ( filterLog.isTraceEnabled( ) ) {
                filterLog.trace( "%s - string: '%s' -> '%s'",
                                 this.logDocOperation( offset,
                                                       tmpOffset,
                                                       length,
                                                       tmpLength ),
                                 string,
                                 tmpContent.toString( ) );
            }
            filterBypass.replace( tmpOffset,
                                  tmpLength,
                                  tmpContent.toString( ),
                                  attr );
            this.field.extension.notifyChange( );
        }

        /*
         *  Entrada                     Saída    
         *  Antes ou entre os dígitos   Início do bloco atual
         *  Antes ou entre os espaços   Início do bloco seguinte
         */
        @Override
        protected void navFilterSetDot( NavigationFilter.FilterBypass filterBypass,
                                        int dot,
                                        Position.Bias bias ) {
            int tmpDot = dot;
            int tmpCaret = this.field.getCaretPosition( );
            if ( tmpDot != tmpCaret ) {
                int tmpLastSet = this.navFilterLastSetDot;
                this.navFilterLastSetDot = dot;
                int tmpLastMove = this.navFilterLastMoveDot;
                this.navFilterLastMoveDot = ( -1 );
                if ( ( this.navFilterForceTarget >= 0 ) && ( tmpDot == this.navFilterForceTrigger ) ) {
                    tmpDot = this.navFilterForceTarget;
                    this.navFilterForceTarget = ( -1 );
                    this.navFilterIgnore = false;
                }
                else {
                    if ( this.navFilterIgnore ) {
                        this.navFilterIgnore = false;
                    }
                    else {
                        int tmpBlockOffset = this.computeBlockOffset( tmpDot );
                        if ( tmpBlockOffset < this.digitsPerBlock ) {
                            // A posição é antes ou entre os dígitos
                            // Calcule a posição do início do bloco atual
                            tmpDot -= tmpBlockOffset;
                        }
                        else {
                            // A posição é antes ou entre os espaços
                            // Calcule a posição do início do bloco seguinte
                            tmpDot += ( this.blockLength - tmpBlockOffset );
                            // Não deixe ultrapassar o final
                            int tmpTotalLength = this.field.getDocument( ).getLength( );
                            if ( tmpDot > tmpTotalLength ) {
                                tmpDot = tmpTotalLength;
                            }
                        }
                    }
                }
                if ( filterLog.isTraceEnabled( ) ) {
                    filterLog.trace( this.logNavOperation( tmpLastSet,
                                                           tmpLastMove,
                                                           dot,
                                                           tmpDot ) );
                }
            }
            filterBypass.setDot( tmpDot,
                                 bias );
        }

        @Override
        protected void navFilterMoveDot( NavigationFilter.FilterBypass filterBypass,
                                         int dot,
                                         Position.Bias bias ) {
            int tmpDot = dot;
            int tmpCaret = this.field.getCaretPosition( );
            //
            int tmpLastMove = this.navFilterLastMoveDot;
            this.navFilterLastMoveDot = dot;
            //
            int tmpPosition = this.computeBlockPosition( tmpDot );
            int tmpTotalLength = this.field.getDocument( ).getLength( );
            if ( this.navFilterLastSetDot <= tmpDot ) {
                // Movimento depois da posição inicial da seleção
                // A seleção é no sentido esquerda -> direita
                if ( ( this.navFilterLastSetDot < tmpTotalLength ) && ( tmpLastMove < this.navFilterLastSetDot ) ) {
                    // A origem da seleção não é o final do texto (exceção para o ajuste abaixo)
                    // É o primeiro movimento ou o anterior foi no sentido oposto
                    // Ajuste o lado esquerdo da seleção no início do bloco origem
                    int tmpBlockOffset = this.computeBlockOffset( this.navFilterLastSetDot );
                    int tmpNewLeft = ( this.navFilterLastSetDot - tmpBlockOffset );
                    // Se a origem da seleção for entre dois blocos 
                    if ( tmpBlockOffset >= this.digitsPerBlock ) {
                        // Ajuste o lado esquerdo da seleção no início do bloco seguinte ao bloco origem
                        tmpNewLeft += this.blockLength;
                    }
                    filterBypass.setDot( tmpNewLeft,
                                         Position.Bias.Forward );
                }
                // Ajuste o lado direito da seleção, se necessário
                if ( tmpPosition != tmpDot ) {
                    // Não é o início do bloco, mova para o início do bloco seguinte
                    tmpDot = ( tmpPosition + this.blockLength );
                    // Não deixe ultrapassar o final
                    if ( tmpDot > tmpTotalLength ) {
                        tmpDot = tmpTotalLength;
                    }
                }
            }
            else {
                // Movimento antes da posição inicial da seleção
                // A seleção é no sentido esquerda <- direita
                if ( ( this.navFilterLastSetDot <= tmpLastMove ) || ( tmpLastMove < 0 ) ) {
                    // É o primeiro movimento ou o anterior foi no sentido oposto
                    // Ajuste o lado direito da seleção, se necessário
                    int tmpNewRight = this.computeBlockPosition( this.navFilterLastSetDot );
                    if ( tmpNewRight != this.navFilterLastSetDot ) {
                        // Não é o início do bloco, mova para início do bloco seguinte ao origem
                        tmpNewRight += this.blockLength;
                        // Não deixe ultrapassar o final
                        if ( tmpNewRight > tmpTotalLength ) {
                            tmpNewRight = tmpTotalLength;
                        }
                        filterBypass.setDot( tmpNewRight,
                                             Position.Bias.Forward );
                    }
                }
                // Ajuste o lado esquerdo da seleção para o início do bloco do movimento
                if ( tmpDot < tmpTotalLength ) {
                    tmpDot = tmpPosition;
                }
            }
            if ( filterLog.isTraceEnabled( ) //
                 && ( tmpDot != tmpCaret )
                 && ( tmpLastMove != dot ) ) {
                filterLog.trace( this.logNavOperation( this.navFilterLastSetDot,
                                                       tmpLastMove,
                                                       dot,
                                                       tmpDot ) );
            }
            filterBypass.moveDot( tmpDot,
                                  bias );
        }

        @Override
        protected Integer navFilterNextPosition( int defaultResult,
                                                 int pos,
                                                 int direction ) {
            Integer tmpResult = null;
            int tmpTotalLength = this.field.getDocument( ).getLength( );
            int tmpBlockOffset = this.computeBlockOffset( pos );
            // Calcule a posição do bloco atual
            int tmpBlockPosition = ( pos - tmpBlockOffset );
            /*
             * Sentido      Cursor      Selecao     Bloco
             * esquerda     antes        irrel      anterior
             * esquerda     meio         irrel      atual
             * esquerda     depois       irrel      atual
             * direita      antes        irrel      proximo
             * direita      meio         irrel      proximo
             * direita      depois        sim       proximo 
             */
            String tmpDiretion = "IllegalArgumentException";
            switch ( direction ) {
                // Sentido 'esquerda'
                case SwingConstants.WEST:
                    tmpDiretion = "WEST";
                    // Posicione no início do bloco atual
                    tmpResult = tmpBlockPosition;
                    if ( ( tmpBlockPosition > 0 ) && ( tmpBlockOffset == 0 ) ) {
                        // O cursor está antes dos dígitos
                        tmpResult -= this.blockLength;
                    }
                    break;
                // Sentido 'direita'
                case SwingConstants.EAST:
                    tmpDiretion = "EAST";
                    // Posicione no início do proximo bloco
                    tmpResult = ( tmpBlockPosition + this.blockLength );
                    if ( tmpResult > tmpTotalLength ) {
                        tmpResult = tmpTotalLength;
                    }
                    break;
                case SwingConstants.NORTH:
                    tmpDiretion = "NORTH";
                    break;
                case SwingConstants.SOUTH:
                    tmpDiretion = "SOUTH";
                    break;
                default:
                    break;
            }
            if ( filterLog.isTraceEnabled( ) ) {
                filterLog.trace( "pos: %2d -> %2d - direction: %s",
                                 pos,
                                 ( tmpResult == null ) ? pos
                                                       : tmpResult,
                                 tmpDiretion );
            }
            return ( tmpResult == null ) ? pos
                                         : tmpResult;
        }

        protected String logNavStatus( int lastSetDot,
                                       int lastMoveDot ) {
            return String.format( "lastSet/Move: %s / %s - caret: %2d - selection: %2d / %2d",
                                  ( lastSetDot < 0 ) ? "??"
                                                     : String.format( "%2d",
                                                                      lastSetDot ),
                                  ( lastMoveDot < 0 ) ? "??"
                                                      : String.format( "%2d",
                                                                       lastMoveDot ),
                                  this.field.getCaretPosition( ),
                                  this.field.getSelectionStart( ),
                                  this.field.getSelectionEnd( ) );
        }

        protected String logNavOperation( int lastSetDot,
                                          int lastMoveDot,
                                          int requestedDot,
                                          int actualDot ) {
            return String.format( "%s - dot: %2d -> %2d",
                                  this.logNavStatus( lastSetDot,
                                                     lastMoveDot ),
                                  requestedDot,
                                  actualDot );
        }

        protected String logDocOperation( int requestedOffset,
                                          int actualOffset,
                                          int requestedLength,
                                          int actualLength ) {
            return String.format( "%s - offset: %2d -> %2d - length: %2d -> %2d",
                                  this.logNavStatus( this.navFilterLastSetDot,
                                                     this.navFilterLastMoveDot ),
                                  requestedOffset,
                                  actualOffset,
                                  requestedLength,
                                  actualLength );
        }
    }

    protected ContentType                              contentType;

    protected AbstractFilter                           contentFilter;

    protected ETextExtension< ETextField, ETextField > extension;

    public ETextField( ) {
        super( );
        this._construct( );
    }

    public ETextField( int columns ) {
        super( columns );
        this._construct( );
    }

    private void _construct( ) {
        this.extension = new ETextExtension< ETextField, ETextField >( this,
                                                                       false );
        this.setContentType( ContentType.FreeText );
    }

    public ContentType getContentType( ) {
        return this.contentType;
    }

    public ETextField setContentType( ContentType contentType ) {
        if ( ( contentType != null ) && ( contentType != this.contentType ) ) {
            AbstractFilter tmpNewFilter;
            switch ( contentType ) {
                case HexaBytes:
                    tmpNewFilter = new HexadecimalFilter( this,
                                                          2,
                                                          1,
                                                          0 );
                    break;
                case AESKey:
                    tmpNewFilter = new HexadecimalFilter( this,
                                                          2,
                                                          1,
                                                          16 );
                    break;
                default:
                    tmpNewFilter = new FreeFilter( this );
                    break;
            }
            this.contentType = contentType;
            this.contentFilter = tmpNewFilter;
            this.extension.setTextNoChange( "" );
        }
        return this;
    }

    public AbstractFilter getContentFilter( ) {
        return this.contentFilter;
    }

    @Override
    public ETextExtension< ETextField, ETextField > getExtension( ) {
        return this.extension;
    }

    @Override
    public boolean isTranferEnabled( ) {
        return this.extension.isTransferEnabled( );
    }

    @Override
    public void setTransferEnabled( boolean transferEnabled ) {
        this.extension.setTransferEnabled( transferEnabled );
    }

    @Override
    public void onError( ErrorListener< ErrorType > errorListener ) {
        this.extension.onError( errorListener );
    }

    @Override
    public void onChange( EventListener changeListener ) {
        this.extension.onChange( changeListener );
    }
}
