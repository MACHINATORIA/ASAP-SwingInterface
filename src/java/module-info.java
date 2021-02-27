module asap.swingInterface {
	requires transitive java.desktop;
	requires transitive asap.primitive;

	exports asap.ui.swing.component;
	exports asap.ui.swing.component.composite.application;
	exports asap.ui.swing.component.composite.decorable;
	exports asap.ui.swing.component.composite.captionHint;
}
