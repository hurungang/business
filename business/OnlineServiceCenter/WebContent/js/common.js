function findObj(n, d) { // v4.01
	var p, i, x;
	if (!d)
		d = document;
	if ((p = n.indexOf("?")) > 0 && parent.frames.length) {
		d = parent.frames[n.substring(p + 1)].document;
		n = n.substring(0, p);
	}
	if (!(x = d[n]) && d.all)
		x = d.all[n];
	for (i = 0; !x && i < d.forms.length; i++)
		x = d.forms[i][n];
	for (i = 0; !x && d.layers && i < d.layers.length; i++)
		x = findObj(n, d.layers[i].document);
	if (!x && d.getElementById)
		x = d.getElementById(n);
	return x;
}

function showContainer(elementId) {
	var element = findObj(elementId);
	if (element != null) {
		element.style.display = "";
	}
}

function hideContainer(elementId) {
	var element = findObj(elementId);
	if (element != null) {
		element.style.display = "none";
	}
}

function setAction(form, action) {
	var actionInput = form.elements["actionType"];
	if (actionInput == null || !actionInput) {
		actionInput = document.createElement("input");
		actionInput.type = "hidden";
		actionInput.name = "actionType";
		actionInput.id = "actionType";
		actionInput.value = action;
		form.appendChild(actionInput);
	} else {
		actionInput.value = action;
	}
}

function submitFormByName(formName, action) {
	var form = document.forms[formName];
	submitForm(form, action);
}

function submitForm(form, action) {
	if (action != null) {
		setAction(form, action);
	}
	form.submit();
	return false;
}

function selectAll(control,name) {
	if(control.checked==undefined){
		control.checked = false;
	}
    var elem=document.getElementsByName(name);

	for (i = 0; i < elem.length; i++) {
		if (elem[i].type == "checkbox") {
			elem[i].checked = !control.checked;
		}
	}
	control.checked = !control.checked;
    return false;
}

function ajaxShowForm(url){
	jQuery.facebox({ ajax: url })
}


function switchTab(pageCode,tabName){
	$('#'+pageCode+'_'+tabName+'_link').click();
	//$('#'+pageCode+'_'+tabName).addClass('default-tab');
}

function calcCart(){
	var idChecks = document.getElementsByName("commodityId");
	var totalPrice = 0;
	var totalPoint = 0;
	var totalRealPrice = 0;
	for(i=0; i <idChecks.length;i++)
	{
		id=idChecks[i].value;
		var price = document.getElementById("price_"+id).value;
		var point = document.getElementById("point_"+id).value;
		var discount = document.getElementById("discount_"+id).value;
		var number = document.getElementById("number_"+id).value;
		var spanPrice = document.getElementById("priceSpan_"+id);
		var spanRealPrice = document.getElementById("realPriceSpan_"+id);
		var spanPoint =  document.getElementById("pointSpan_"+id);
		spanPrice.innerText = (price * number).toFixed(2);
		spanRealPrice.innerText = (price * discount * number).toFixed(2);
		spanPoint.innerText = (point * number).toFixed(0);
		totalPrice += price * number;
		totalPoint += point * number;
		totalRealPrice += price * discount * number;
	}
	var spanTotalPrice = document.getElementById("spanTotalPrice");
	var spanTotalRealPrice= document.getElementById("spanTotalRealPrice");
	var spanTotalPoint=  document.getElementById("spanTotalPoint");
	spanTotalPrice.innerHTML = totalPrice.toFixed(2);
	spanTotalRealPrice.innerHTML= totalRealPrice .toFixed(2);
	spanTotalPoint.innerHTML= totalPoint.toFixed(0);
}