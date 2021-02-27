package asap.ui.swing.component;

import java.awt.Color;

public interface EComponent {

    public String getText( );

    public void setText( String text );

    public boolean isEnabled( );

    public void setEnabled( boolean enable );

    public boolean isVisible( );

    public void setVisible( boolean visible );

    public void setForeground( Color color );

    public void setBackground( Color color );
}
