package asap.ui.swing.component.composite.application;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import asap.ui.swing.component.ELabel;
import asap.ui.swing.component.EPanel;

@SuppressWarnings( "serial" )
public class AppHeaderPanel extends EPanel {

    public ELabel               icon;

    public ELabel               name;

    public ELabel               summary;

    public ELabel               version;

    private static final String DEFAULT_LOGO_FILENAMEXTENSION = "logo.gif";

    public AppHeaderPanel( ) {
        this( AppHeaderPanel.class,
              DEFAULT_LOGO_FILENAMEXTENSION );
    }

    private URL getResourceURL( Class< ? > resourceClass,
                                String resourceNameExtension ) {
        if ( ( resourceClass == null ) || ( resourceNameExtension == null ) ) {
            resourceClass = AppHeaderPanel.class;
            resourceNameExtension = DEFAULT_LOGO_FILENAMEXTENSION;
        }
        String tmpResourcePath = String.format( "/%s/resources/%s",
                                                resourceClass.getPackage( ).getName( ).replaceAll( "\\.",
                                                                                                   "/" ),
                                                resourceNameExtension );
        return resourceClass.getResource( tmpResourcePath );
    }

    public AppHeaderPanel( Class< ? > appClass,
                           String logoNameExtension ) {
        URL tmpLogoURL = this.getResourceURL( appClass,
                                              logoNameExtension );
        if ( tmpLogoURL == null ) {
            tmpLogoURL = this.getResourceURL( null,
                                              null );
        }
        //
        setBorder( new EmptyBorder( 5,
                                    5,
                                    20,
                                    5 ) );
        GridBagLayout gridBagLayout = new GridBagLayout( );
        gridBagLayout.columnWeights = new double[ ] { 0.0,
                                                      1.0,
                                                      0.0 };
        setLayout( gridBagLayout );
        icon = new ELabel( "" );
        GridBagConstraints gbc_icon = new GridBagConstraints( );
        gbc_icon.gridheight = 2;
        gbc_icon.fill = GridBagConstraints.VERTICAL;
        gbc_icon.gridx = 0;
        gbc_icon.gridy = 0;
        add( icon,
             gbc_icon );
        icon.setIcon( new ImageIcon( tmpLogoURL ) );
        this.name = new ELabel( "Título do Aplicativo" );
        GridBagConstraints gbc_appName = new GridBagConstraints( );
        gbc_appName.fill = GridBagConstraints.HORIZONTAL;
        gbc_appName.gridx = 1;
        gbc_appName.gridy = 0;
        add( name,
             gbc_appName );
        this.name.setHorizontalAlignment( SwingConstants.CENTER );
        this.name.setFont( name.getFont( ).deriveFont( name.getFont( ).getStyle( )
                                                       | Font.BOLD,
                                                       name.getFont( ).getSize( ) + 10 ) );
        this.version = new ELabel( "vers\u00E3o 1.0.0" );
        GridBagConstraints gbc_appVersion = new GridBagConstraints( );
        gbc_appVersion.anchor = GridBagConstraints.NORTH;
        gbc_appVersion.gridheight = 2;
        gbc_appVersion.fill = GridBagConstraints.HORIZONTAL;
        gbc_appVersion.gridx = 2;
        gbc_appVersion.gridy = 0;
        add( version,
             gbc_appVersion );
        this.summary = new ELabel( "Sum\u00E1rio do Aplicativo" );
        GridBagConstraints gbc_appSummary = new GridBagConstraints( );
        gbc_appSummary.fill = GridBagConstraints.HORIZONTAL;
        gbc_appSummary.gridx = 1;
        gbc_appSummary.gridy = 1;
        add( summary,
             gbc_appSummary );
        summary.setHorizontalAlignment( SwingConstants.CENTER );
        summary.setFont( summary.getFont( ).deriveFont( summary.getFont( ).getStyle( )
                                                        | Font.BOLD,
                                                        summary.getFont( ).getSize( ) + 4 ) );
    }
}
