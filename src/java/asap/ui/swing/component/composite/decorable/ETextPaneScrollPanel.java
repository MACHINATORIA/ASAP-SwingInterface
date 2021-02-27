package asap.ui.swing.component.composite.decorable;

import java.awt.BorderLayout;
import java.awt.SystemColor;

import asap.ui.swing.component.EPanel;
import asap.ui.swing.component.EScrollPane;
import asap.ui.swing.component.ETextPane;

@SuppressWarnings("serial")
public class ETextPaneScrollPanel extends EPanel {

	public ETextPane pane;

	public EScrollPane scroll;

	public ETextPaneScrollPanel() {
		setLayout(new BorderLayout(0, 0));
		scroll = new EScrollPane();
		add(scroll, BorderLayout.CENTER);
		pane = new ETextPane();
		pane.setBackground(SystemColor.control);
		pane.setContentType("text/html");
		pane.setText(
				"<table width=\"100%\"><tr valign=\"middle\" style=\"background-color: #E8E8E8\"><td nowrap><div>Quisque nisl nibh.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Maecenas dolor arcu.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Aenean cursus vel.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Nunc mi adipiscing.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Nunc dictum maximus.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Nunc sagittis turpis.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Lorem posuere ligula.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Etiam volutpat lorem.</div></td><td width=\"100%\"\r\n nowrap><div><font color=\"#E8E8E8\">-</font></div></td></tr><tr valign=\"middle\" style=\"background-color: #FFFFFF\"><td nowrap><div>Morbi quis interdum.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Nulla egestas lectus.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Nunc laoreet purus.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Lorem augue vehicula.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Cras porttitor leo.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Curabitur purus leo.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Quisque mollis vel.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Donec nisi euismod.</div></td><td width=\"100%\"\r\n nowrap><div><font color=\"#FFFFFF\">-</font></div></td></tr><tr valign=\"middle\" style=\"background-color: #E8E8E8\"><td nowrap><div>Proin orci venenatis.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Vivamus laoreet leo.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Mauris magna nisl.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Maecenas erat nulla.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Cras magna commodo.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Donec sodales nunc.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Aliquam eget eros.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Maecenas dolor arcu.</div></td><td width=\"100%\" nowrap><div><font\r\n color=\"#E8E8E8\">-</font></div></td></tr><tr valign=\"middle\" style=\"background-color: #FFFFFF\"><td nowrap><div>Morbi semper ornare.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Suspendisse risus et.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Sed justo facilisis.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Aliquam dui dolor.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Morbi sapien arcu.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Proin pharetra in.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Mauris at lacinia.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Sed dapibus rutrum.</div></td><td width=\"100%\" nowrap><div><font\r\n color=\"#FFFFFF\">-</font></div></td></tr><tr valign=\"middle\" style=\"background-color: #E8E8E8\"><td nowrap><div>Quisque laoreet a.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Phasellus eros orci.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Aenean placerat odio.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Etiam efficitur orci.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Cras nisi commodo.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Phasellus ante lacus.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Quisque lobortis id.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Cras eros hendrerit.</div></td><td width=\"100%\" nowrap><div><font\r\n color=\"#E8E8E8\">-</font></div></td></tr><tr valign=\"middle\" style=\"background-color: #FFFFFF\"><td nowrap><div>Nam placerat rhoncus.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Duis tincidunt arcu.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Proin lacinia tempus.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Donec id interdum.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Etiam sagittis eu.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Sed enim ultrices.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Nam sagittis mauris.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Vivamus dolor ornare.</div></td><td width=\"100%\" nowrap><div><font\r\n color=\"#FFFFFF\">-</font></div></td></tr><tr valign=\"middle\" style=\"background-color: #E8E8E8\"><td nowrap><div>Nam consequat non.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Lorem auctor maximus.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Lorem eget turpis.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Duis elit volutpat.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Vivamus auctor metus.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Etiam feugiat cursus.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Aenean leo interdum.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Cras lectus pulvinar.</div></td><td width=\"100%\" nowrap><div><font\r\n color=\"#E8E8E8\">-</font></div></td></tr><tr valign=\"middle\" style=\"background-color: #FFFFFF\"><td nowrap><div>Cras vel sagittis.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Morbi sapien arcu.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Ut rhoncus imperdiet.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Aenean id laoreet.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Nam condimentum nisi.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Lorem lectus felis.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Proin sed maximus.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Morbi tincidunt diam.</div></td><td width=\"100%\" nowrap><div><font\r\n color=\"#FFFFFF\">-</font></div></td></tr><tr valign=\"middle\" style=\"background-color: #E8E8E8\"><td nowrap><div>Donec pulvinar eu.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Suspendisse mi erat.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Phasellus at sodales.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Aliquam ornare eu.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Nam odio efficitur.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Ut felis interdum.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Sed rhoncus rutrum.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Vivamus blandit erat.</div></td><td width=\"100%\" nowrap><div><font\r\n color=\"#E8E8E8\">-</font></div></td></tr><tr valign=\"middle\" style=\"background-color: #FFFFFF\"><td nowrap><div>Proin enim luctus.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Phasellus rhoncus mi.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Morbi pretium metus.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Aenean sodales amet.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Vestibulum non purus.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Mauris augue tellus.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Donec arcu ornare.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Lorem laoreet quam.</div></td><td width=\"100%\" nowrap><div><font\r\n color=\"#FFFFFF\">-</font></div></td></tr><tr valign=\"middle\" style=\"background-color: #E8E8E8\"><td nowrap><div>Aenean tristique non.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Vestibulum in lectus.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Mauris quam interdum.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Phasellus euismod eu.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Curabitur pretium at.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Etiam turpis felis.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Nunc sem tristique.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Cras accumsan nec.</div></td><td width=\"100%\" nowrap><div><font\r\n color=\"#E8E8E8\">-</font></div></td></tr><tr valign=\"middle\" style=\"background-color: #FFFFFF\"><td nowrap><div>Quisque vel maximus.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Phasellus non dui.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Nunc blandit urna.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Etiam nibh mauris.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Nulla magna rhoncus.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Lorem mollis aliquet.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Quisque nulla nulla.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Praesent leo dolor.</div></td><td width=\"100%\" nowrap><div><font\r\n color=\"#FFFFFF\">-</font></div></td></tr><tr valign=\"middle\" style=\"background-color: #E8E8E8\"><td nowrap><div>Nulla volutpat mi.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Ut luctus aliquet.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Donec convallis nec.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Proin tellus commodo.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Donec id interdum.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Curabitur odio nec.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Praesent arcu purus.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Proin convallis ex.</div></td><td width=\"100%\" nowrap><div><font\r\n color=\"#E8E8E8\">-</font></div></td></tr><tr valign=\"middle\" style=\"background-color: #FFFFFF\"><td nowrap><div>Phasellus vitae ante.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Quisque felis lacus.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Praesent eros risus.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Sed efficitur semper.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Etiam risus mattis.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Maecenas erat nulla.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Lorem ligula magna.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Praesent aliquet mi.</div></td><td width=\"100%\" nowrap><div><font\r\n color=\"#FFFFFF\">-</font></div></td></tr><tr valign=\"middle\" style=\"background-color: #E8E8E8\"><td nowrap><div>Nunc pretium eros.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Nunc est pharetra.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Donec felis justo.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Quisque non faucibus.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Donec semper dapibus.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Aliquam a lobortis.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Vivamus mattis est.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Morbi rhoncus nulla.</div></td><td width=\"100%\" nowrap><div><font\r\n color=\"#E8E8E8\">-</font></div></td></tr><tr valign=\"middle\" style=\"background-color: #FFFFFF\"><td nowrap><div>Quisque metus mattis.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Vivamus porta sapien.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Quisque nulla quis.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Morbi vitae lectus.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Aenean ligula risus.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Mauris lectus quis.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Vivamus justo porta.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Vivamus dictum in.</div></td><td width=\"100%\" nowrap><div><font\r\n color=\"#FFFFFF\">-</font></div></td></tr><tr valign=\"middle\" style=\"background-color: #E8E8E8\"><td nowrap><div>Phasellus ac justo.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Quisque auctor dolor.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Quisque semper urna.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Nunc felis vehicula.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Vivamus ac aliquet.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Proin nibh pretium.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Morbi dolor turpis.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Duis condimentum et.</div></td><td width=\"100%\" nowrap><div><font\r\n color=\"#E8E8E8\">-</font></div></td></tr><tr valign=\"middle\" style=\"background-color: #FFFFFF\"><td nowrap><div>Aliquam tempus urna.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Vivamus lacus lacus.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Maecenas felis et.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Curabitur eget augue.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Donec enim bibendum.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Aenean sed porttitor.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Morbi varius posuere.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Donec feugiat odio.</div></td><td width=\"100%\" nowrap><div><font\r\n color=\"#FFFFFF\">-</font></div></td></tr><tr valign=\"middle\" style=\"background-color: #E8E8E8\"><td nowrap><div>Morbi dolor turpis.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Sed consequat sit.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Maecenas metus leo.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Donec ligula metus.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Donec convallis at.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Mauris sed facilisis.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Donec nisi euismod.</div></td><td width=\"1%\" nowrap><div><font color=\"#E8E8E8\">-</font></div></td><td nowrap><div>Phasellus tempus in.</div></td><td width=\"100%\" nowrap><div><font\r\n color=\"#E8E8E8\">-</font></div></td></tr><tr valign=\"middle\" style=\"background-color: #FFFFFF\"><td nowrap><div>Ut fermentum augue.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Aenean augue justo.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Sed imperdiet varius.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Nulla aliquam vel.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Donec volutpat metus.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Morbi velit justo.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Aenean lacus posuere.</div></td><td width=\"1%\" nowrap><div><font color=\"#FFFFFF\">-</font></div></td><td nowrap><div>Mauris odio augue.</div></td><td width=\"100%\" nowrap><div><font\r\n color=\"#FFFFFF\">-</font></div></td></tr></table>");
		pane.setEditable(false);
		scroll.setViewportView(pane);
	}
}