package com.runtech.web.action;

import static org.junit.Assert.assertEquals;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.runtech.onlineshop.form.AdminModuleForm;
import com.runtech.web.form.ModelForm;
import com.runtech.web.runtime.ModelException;

public class PageActionTest {

	private PageAction action = new PageAction();

	@Test
	public void testGetModel() {
		Object model = action.getModel();
		assertEquals(null, model);
	}

	@Test
	public void testGetImplementedModel() {
		action.setPageModel("com.runtech.onlineshop.form.SAdminModuleForm");
		Object model = action.getModel();
		assertEquals(true, model instanceof AdminModuleForm);
	}
	@Test
	public void testGetModelList(){
		action.setPageModel("com.runtech.onlineshop.form.SAdminModuleForm");
		try {
			List<ModelForm> modelList = action.getModelList();
			assertEquals(true, modelList.size()>0);
		} catch (ModelException e) {
			Assert.fail();
		}
	}
}
