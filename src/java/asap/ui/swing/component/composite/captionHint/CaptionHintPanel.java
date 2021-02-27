package asap.ui.swing.component.composite.captionHint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.border.EmptyBorder;

import asap.ui.swing.component.EPanel;
import asap.ui.swing.component.composite.decorable.ELabelPanel;

@SuppressWarnings( "serial" )
public class CaptionHintPanel extends EPanel {

    public EPanel      captionPanel;

    public ELabelPanel caption;

    public ELabelPanel hint;

    public EPanel      contentPanel;

    public CaptionHintPanel( ) {
        setLayout( new BorderLayout( 0,
                                     0 ) );
        captionPanel = new EPanel( );
        captionPanel.setBorder( new EmptyBorder( 2,
                                                 5,
                                                 0,
                                                 0 ) );
        captionPanel.setLayout( new FlowLayout( FlowLayout.LEFT,
                                                0,
                                                0 ) );
        caption = new ELabelPanel( );
        caption.label.setAlignmentX( Component.CENTER_ALIGNMENT );
        caption.label.setFont( caption.label.getFont( ).deriveFont( caption.label.getFont( ).getSize( ) - 1f ) );
        caption.label.setText( "Panel Caption" );
        captionPanel.add( caption );
        hint = new ELabelPanel( );
        hint.label.setVisible( false );
        hint.label.setFont( hint.label.getFont( ).deriveFont( hint.label.getFont( ).getSize( ) - 1f ) );
        hint.label.setText( "Panel Hint" );
        hint.label.setForeground( Color.RED );
        hint.setBorder( new EmptyBorder( 0,
                                         5,
                                         0,
                                         0 ) );
        captionPanel.add( hint );
        add( captionPanel,
             BorderLayout.NORTH );
        contentPanel = new EPanel( );
        contentPanel.setLayout( new BorderLayout( 0,
                                                  0 ) );
        add( contentPanel );
    }
}
