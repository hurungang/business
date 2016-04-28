package com.runtech.web.tag;

import java.util.List;
import java.util.Stack;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.runtech.web.model.Menu;
import com.runtech.web.runtime.ModelException;

/**
 * @author Harry
 *
 */
public class MenuTag extends BodyTagSupport {

	private Menu root;
	private boolean showRoot = true;
	private String name;
	private Integer depth;
	private Stack<Integer> cursors = new Stack<Integer>();;
	private String depthName;
	private Menu menuCursor;
	
	public MenuTag() {
	}

	public Menu getRoot() {
		return root;
	}

	public void setRoot(Menu menu) {
		this.root = menu;
		this.menuCursor = menu;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int doStartTag() throws JspException {
		if(this.root!=null){
			if(!this.showRoot){
				if(this.depth!=null){
					this.depth++;
				}
				try {
					this.menuCursor = this.getNextMenu(this.cursors, this.root);
				} catch (ModelException e) {
					throw new JspException(e.fillInStackTrace());
				}
				this.showRoot = true;
			}
			if(this.menuCursor!=null){
				this.pageContext.setAttribute(this.name,this.menuCursor);
				this.pageContext.setAttribute(this.depthName,this.cursors.size());
				return EVAL_BODY_INCLUDE;
			}
		}
		return SKIP_BODY;
	}

	@Override
	public void doInitBody() throws JspException {
		// TODO Auto-generated method stub
		super.doInitBody();
	}

	@Override
	public int doAfterBody() throws JspException {
		try {
			this.pageContext.removeAttribute(this.name);
			this.pageContext.removeAttribute(this.depthName);
			if(this.menuCursor!=null){
				this.menuCursor = getNextMenu(cursors,this.menuCursor);
				if(depth==null||this.cursors.size()<depth){
					this.pageContext.setAttribute(this.name,this.menuCursor);
					this.pageContext.setAttribute(this.depthName, this.cursors.size());
					if(this.menuCursor!=null){
						return EVAL_BODY_AGAIN;
					}
				}else if(this.cursors.size()>=depth){
					return this.doAfterBody();
				}
			}
			cursors = new Stack<Integer>();
			return EVAL_PAGE;
		} catch (ModelException e) {
			// TODO Auto-generated catch block
			throw new JspException(e.fillInStackTrace());
		}
	
	}

	public String getDepthName() {
		return depthName;
	}

	public void setDepthName(String depthName) {
		this.depthName = depthName;
	}

	private Menu getNextMenu(Stack<Integer> cursors,Menu menu) throws ModelException {
		Menu nextMenu = null;
		List<Menu> subMenus = menu.getSubMenus();
		if(subMenus==null||subMenus.isEmpty()){
			nextMenu = getParentNextMenu(cursors, menu);
		}else{
			nextMenu = subMenus.get(0);
			this.cursors.push(0);
		}
		return nextMenu;
	}
	
	private Menu getParentNextMenu(Stack<Integer> cursors, Menu menu) throws ModelException {
		Menu nextMenu = null;
		List<Menu> subMenus = menu.getSubMenus();
		
		Integer cursor = null;
		if(!cursors.isEmpty())
			cursor = cursors.pop();
		if(cursor!=null){
			menu = menu.getParentMenu();
			subMenus = menu.getSubMenus();

			cursor++;
			if(cursor<subMenus.size()){
				nextMenu = subMenus.get(cursor);
				cursors.push(cursor);
			}else{
				nextMenu = getParentNextMenu(cursors,menu);
			}
		}
		return nextMenu;
	}

	public Integer getDepth() {
		return depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}

	public boolean isShowRoot() {
		return showRoot;
	}

	public void setShowRoot(boolean showRoot) {
		this.showRoot = showRoot;
	}

}
