<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div id="sidebar-wrapper">
				<!-- Sidebar with logo and menu -->

				<h1 id="sidebar-title">
					<a href="#">缤果自助服务</a>
				</h1>

				<!-- Logo (221px wide) -->
				<a href="#"><img
					id="logo" src="<%=request.getContextPath()%>/images/logo_bg.png" alt="Simpla Admin logo"></a>

				<!-- Sidebar Profile links -->
				<div id="profile-links">
					<s:if test="#session.user.name!=null"><s:text name="text.platform.welcome">
						<s:param value="#session.user.fullName"></s:param>
						<s:param value="#session.user.adminRole.name"></s:param>
					</s:text><br>
					[<a href="<%=request.getContextPath()%>/logout.do"><s:text name="text.platform.logout"/></a>]</s:if>
				</div>
				<ul id="main-nav">
					<!-- Accordion Menu -->
<s:set name="menu" value="menu" scope="request"/>
<s:iterator id="topMenu" value="menu.subMenus">
	<s:if test="visible">
		<s:if test="page!=null">
					<li><a href="<%=request.getContextPath()%>/protect/<s:property value="parentMenu.code"/>/<s:property value="code"/>.do"
						class="nav-top-item no-submenu <s:if test="active">current</s:if>">
							<s:property value="name"/>
					</a>
						<ul style="display: none;">
		</s:if>
		<s:else>
					<li><a href="#"
						class="nav-top-item <s:if test="active">current</s:if>">
							<s:property value="name"/>
					</a>
						<ul style="display: none;">
		</s:else>
		<s:iterator id="subMenu" value="#topMenu.subMenus">
			<s:if test="visible">
							<li><a <s:if test="active">class="current"</s:if>
								href="<%=request.getContextPath()%>/protect/<s:property value="parentMenu.code"/>/<s:property value="code"/>.do"><s:property value="name"/></a></li>

			</s:if>
		</s:iterator>
								</ul>
					</li>
	</s:if>
</s:iterator>
				</ul>
				<!-- End #main-nav -->

				<div id="messages" style="display: none">
					<!-- Messages are shown when a link with these attributes are clicked: href="#messages" rel="modal"  -->

					<h3>3 Messages</h3>

					<p>
						<strong>17th May 2009</strong> by Admin<br> Lorem ipsum dolor
							sit amet, consectetur adipiscing elit. Vivamus magna. Cras in mi
							at felis aliquet congue. <small><a
								href="#"
								class="remove-link" title="Remove message">Remove</a></small>
					</p>

					<p>
						<strong>2nd May 2009</strong> by Jane Doe<br> Ut a est eget
							ligula molestie gravida. Curabitur massa. Donec eleifend, libero
							at sagittis mollis, tellus est malesuada tellus, at luctus turpis
							elit sit amet quam. Vivamus pretium ornare est. <small><a
								href="#"
								class="remove-link" title="Remove message">Remove</a></small>
					</p>

					<p>
						<strong>25th April 2009</strong> by Admin<br> Lorem ipsum
							dolor sit amet, consectetur adipiscing elit. Vivamus magna. Cras
							in mi at felis aliquet congue. <small><a
								href="#"
								class="remove-link" title="Remove message">Remove</a></small>
					</p>

					<form action="" method="post">

						<h4>New Message</h4>

						<fieldset>
							<textarea class="textarea" name="textfield" cols="79" rows="5"></textarea>
						</fieldset>

						<fieldset>

							<select name="dropdown" class="small-input">
								<option value="option1">Send to...</option>
								<option value="option2">Everyone</option>
								<option value="option3">Admin</option>
								<option value="option4">Jane Doe</option>
							</select> <input class="button" type="submit" value="Send">
						</fieldset>

					</form>

				</div>
				<!-- End #messages -->

			</div>
			
