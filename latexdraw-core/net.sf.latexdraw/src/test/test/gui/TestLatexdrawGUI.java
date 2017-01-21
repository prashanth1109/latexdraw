package test.gui;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.util.Callback;
import net.sf.latexdraw.LaTeXDraw;
import net.sf.latexdraw.badaboom.BadaboomCollector;
import net.sf.latexdraw.util.LangTool;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.Assert.assertTrue;

public abstract class TestLatexdrawGUI extends ApplicationTest {
	protected Callback<Class<?>, Object> guiceFactory;

	protected final GUIVoidCommand waitFXEvents = WaitForAsyncUtils::waitForFxEvents;

	@Before
	public void setUp() {
		BadaboomCollector.INSTANCE.clear();
		WaitForAsyncUtils.waitForFxEvents();
	}

	@After
	public void tearDown() throws TimeoutException {
		FxToolkit.hideStage();
		FxToolkit.cleanupStages();
		release(new KeyCode[] {});
		release(new MouseButton[] {});
	}

	@Override
	public void start(Stage stage) {
		try {
			final Injector injector = Guice.createInjector(createModule());
			guiceFactory = injector::getInstance;
			final Parent root = FXMLLoader.load(LaTeXDraw.class.getResource(getFXMLPathFromLatexdraw()), LangTool.INSTANCE.getBundle(),
				new LatexdrawBuilderFactory(injector), guiceFactory);
			final BorderPane pane = new BorderPane();
			pane.setCenter(root);
			final Scene scene = new Scene(pane);
			stage.setScene(scene);
			stage.show();
			stage.toFront();
			if(root instanceof Region) {
				stage.minHeightProperty().bind(((Region)root).heightProperty());
				stage.minWidthProperty().bind(((Region)root).widthProperty());
			}
			stage.sizeToScene();
		}catch(final IOException ex) {
			ex.printStackTrace();
		}
	}

	public <T extends Node> T find(String query) {
		return lookup(query).query();
	}

	protected abstract String getFXMLPathFromLatexdraw();

	protected AbstractModule createModule() {
		return new AbstractModule() {
			@Override
			protected void configure() {
				//
			}
		};
	}

	@Test
	public void testLaunchNoCrash() {
		assertTrue(BadaboomCollector.INSTANCE.isEmpty());
	}
}
