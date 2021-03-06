package net.sf.latexdraw.command.shape;

import io.github.interacto.jfx.test.UndoableCmdTest;
import java.util.List;
import java.util.stream.Stream;
import net.sf.latexdraw.model.ShapeFactory;
import net.sf.latexdraw.model.api.shape.Drawing;
import net.sf.latexdraw.model.api.shape.Group;
import net.sf.latexdraw.model.api.shape.Shape;
import net.sf.latexdraw.service.PreferencesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the command SeparateShapes. Generated by Interacto test-gen.
 */
@Tag("command")
class SeparateShapesTest extends UndoableCmdTest<SeparateShapes> {
	Drawing drawing;
	Group shape;
	Shape s0;
	Shape sg1;
	Shape s2;
	Shape sg2;
	Shape s4;

	@BeforeEach
	void setUp() {
		bundle = new PreferencesService().getBundle();
	}

	@Override
	protected Stream<Runnable> canDoFixtures() {
		return Stream.of(() -> {
			shape.addShape(sg2);
			drawing.addShape(s0);
			drawing.addShape(shape);
			drawing.addShape(s2);
			drawing.addShape(s4);
			cmd = new SeparateShapes(drawing, shape);
		}, () -> {
			shape.addShape(sg2);
			drawing.addShape(s0);
			drawing.addShape(s2);
			drawing.addShape(s4);
			drawing.addShape(shape);
			cmd = new SeparateShapes(drawing, shape);
		}, () -> {
			drawing.addShape(s0);
			drawing.addShape(sg2);
			drawing.addShape(s2);
			drawing.addShape(s4);
			drawing.addShape(shape);
			cmd = new SeparateShapes(drawing, shape);
		}, () -> {
			drawing.addShape(shape);
			drawing.addShape(s0);
			drawing.addShape(sg2);
			drawing.addShape(s2);
			drawing.addShape(s4);
			cmd = new SeparateShapes(drawing, shape);
		});
	}

	@Override
	protected void commonCanDoFixture() {
		drawing = ShapeFactory.INST.createDrawing();
		shape = ShapeFactory.INST.createGroup();
		s0 = ShapeFactory.INST.createRectangle();
		sg1 = ShapeFactory.INST.createRhombus();
		s2 = ShapeFactory.INST.createGroup(ShapeFactory.INST.createCircle());
		sg2 = ShapeFactory.INST.createCircle();
		s4 = ShapeFactory.INST.createEllipse();
		shape.addShape(sg1);
	}

	@Override
	protected Stream<Runnable> cannotDoFixtures() {
		return Stream.of(
			() -> cmd = new SeparateShapes(ShapeFactory.INST.createDrawing(), ShapeFactory.INST.createGroup()),
			() -> cmd = new SeparateShapes(ShapeFactory.INST.createDrawing(), ShapeFactory.INST.createGroup(ShapeFactory.INST.createRectangle())));
	}

	@Override
	protected Stream<Runnable> doCheckers() {
		return Stream.of(
			() -> assertThat(drawing.getShapes().get()).isEqualTo(List.of(s0, sg1, sg2, s2, s4)),
			() -> assertThat(drawing.getShapes().get()).isEqualTo(List.of(s0, s2, s4, sg1, sg2)),
			() -> assertThat(drawing.getShapes().get()).isEqualTo(List.of(s0, sg2, s2, s4, sg1)),
			() -> assertThat(drawing.getShapes().get()).isEqualTo(List.of(sg1, s0, sg2, s2, s4)));
	}

	@Override
	protected void commonDoCheckers() {
		assertThat(drawing.isModified()).isTrue();
	}

	@Override
	protected Stream<Runnable> undoCheckers() {
		return Stream.of(
			() -> assertThat(drawing.getShapes().get()).isEqualTo(List.of(s0, shape, s2, s4)),
			() -> assertThat(drawing.getShapes().get()).isEqualTo(List.of(s0, s2, s4, shape)),
			() -> assertThat(drawing.getShapes().get()).isEqualTo(List.of(s0, sg2, s2, s4, shape)),
			() -> assertThat(drawing.getShapes().get()).isEqualTo(List.of(shape, s0, sg2, s2, s4)));
	}

	@Override
	protected void commonUndoCheckers() {
		assertThat(drawing.isModified()).isFalse();
	}
}
