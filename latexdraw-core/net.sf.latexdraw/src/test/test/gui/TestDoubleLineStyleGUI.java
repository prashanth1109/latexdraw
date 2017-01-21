package test.gui;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TitledPane;
import net.sf.latexdraw.instruments.ShapeDoubleBorderCustomiser;
import org.junit.Before;

public abstract class TestDoubleLineStyleGUI extends TestShapePropGUI<ShapeDoubleBorderCustomiser> {
	protected CheckBox dbleBoundCB;
	protected ColorPicker dbleBoundColB;
	protected Spinner<Double> dbleSepField;
	protected TitledPane mainPane;

	protected final GUIVoidCommand pickDbleColour = () -> pickColour(dbleBoundColB);
	protected final GUIVoidCommand incrementDbleSep = () -> incrementSpinner(dbleSepField);
	protected final GUIVoidCommand selectdbleLine = () -> clickOn(dbleBoundCB);

	@Override
	public String getFXMLPathFromLatexdraw() {
		return "/fxml/DoubleLineStyle.fxml";
	}

	@Override
	@Before
	public void setUp() {
		super.setUp();
		mainPane = find("#mainPane");
		dbleBoundCB = find("#dbleBoundCB");
		dbleBoundColB = find("#dbleBoundColB");
		dbleSepField = find("#dbleSepField");
		ins = (ShapeDoubleBorderCustomiser) guiceFactory.call(ShapeDoubleBorderCustomiser.class);
		ins.setActivated(true);
		mainPane.setAnimated(false);
		expandPane(mainPane);
	}
}
