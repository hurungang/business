package com.runtech.web.tag;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.runtech.web.tag.LayoutTag.Token;

public class LayoutTagTest {

	@Test
	public void testListToken() {
		LayoutTag layoutTag = new LayoutTag();
		List<Token> listToken = layoutTag.listToken("helooo$body$defefe");
		assertEquals(1, listToken.size());
		assertEquals("$body$", listToken.get(0).getName());
	}

}
