package net.sf.latexdraw.model.api.shape;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTextPosition {
	@Test
	public void testGetTextPositionFromText() {
		assertEquals(TextPosition.BOT, TextPosition.getTextPosition("b"));
		assertEquals(TextPosition.BOT_LEFT, TextPosition.getTextPosition("bl"));
		assertEquals(TextPosition.BOT_RIGHT, TextPosition.getTextPosition("br"));
		assertEquals(TextPosition.TOP, TextPosition.getTextPosition("t"));
		assertEquals(TextPosition.TOP_RIGHT, TextPosition.getTextPosition("tr"));
		assertEquals(TextPosition.TOP_LEFT, TextPosition.getTextPosition("tl"));
		assertEquals(TextPosition.CENTER, TextPosition.getTextPosition(""));
		assertEquals(TextPosition.LEFT, TextPosition.getTextPosition("l"));
		assertEquals(TextPosition.RIGHT, TextPosition.getTextPosition("r"));
	}

	@Test
	public void testGetLatexToken() {
		assertEquals("b", TextPosition.BOT.getLatexToken());
		assertEquals("bl", TextPosition.BOT_LEFT.getLatexToken());
		assertEquals("", TextPosition.CENTER.getLatexToken());
		assertEquals("l", TextPosition.LEFT.getLatexToken());
		assertEquals("r", TextPosition.RIGHT.getLatexToken());
		assertEquals("t", TextPosition.TOP.getLatexToken());
		assertEquals("tl", TextPosition.TOP_LEFT.getLatexToken());
		assertEquals("tr", TextPosition.TOP_RIGHT.getLatexToken());
	}
}
