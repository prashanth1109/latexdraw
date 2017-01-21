package test.gui;

import org.junit.Before;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TitledPane;
import net.sf.latexdraw.models.interfaces.shape.PlotStyle;
import net.sf.latexdraw.instruments.ShapePlotCustomiser;

public abstract class TestPlotStyleGUI extends TestShapePropGUI<ShapePlotCustomiser> {
	protected Spinner<Integer> nbPtsSpinner;
	protected Spinner<Double> minXSpinner;
	protected Spinner<Double> maxXSpinner;
	protected Spinner<Double> xScaleSpinner;
	protected Spinner<Double> yScaleSpinner;
	protected CheckBox polarCB;
	protected ComboBox<PlotStyle> plotStyleCB;
	protected TitledPane mainPane;

	protected final GUICommand<PlotStyle> selectplotStyleCB = (style) -> selectGivenComboBoxItem(plotStyleCB, style);
	protected final GUIVoidCommand incrementnbPtsSpinner = () -> incrementSpinner(nbPtsSpinner);
	protected final GUIVoidCommand incrementminXSpinner = () -> incrementSpinner(minXSpinner);
	protected final GUIVoidCommand incrementmaxXSpinner = () -> incrementSpinner(maxXSpinner);
	protected final GUIVoidCommand incrementxScaleSpinner = () -> incrementSpinner(xScaleSpinner);
	protected final GUIVoidCommand incrementyScaleSpinner = () -> incrementSpinner(yScaleSpinner);
	protected final GUIVoidCommand clickpolarCB = () -> clickOn(polarCB);

	@Override
	public String getFXMLPathFromLatexdraw() {
		return "/fxml/PlotStyle.fxml";
	}

	@Override
	@Before
	public void setUp() {
		super.setUp();
		polarCB = find("#polarCB");
		yScaleSpinner = find("#yScaleSpinner");
		xScaleSpinner = find("#xScaleSpinner");
		maxXSpinner = find("#maxXSpinner");
		minXSpinner = find("#minXSpinner");
		nbPtsSpinner = find("#nbPtsSpinner");
		plotStyleCB = find("#plotStyleCB");
		mainPane = find("#mainPane");
		ins = (ShapePlotCustomiser)guiceFactory.call(ShapePlotCustomiser.class);
		ins.setActivated(true);
		mainPane.setAnimated(false);
		expandPane(mainPane);
	}
}
