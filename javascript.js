var completeTable;
var isIE;
var xhr;

function init()
{
	console.log("began init()");
	var completeField = document.getElementById("searchId");
	completeTable= document.getElementById("complete-table");
	var autoRow = document.getElementById("auto-row");
	console.log("completed init()");
}

function doCompletion()
{
	console.log("began doCompletion")
	var url = "AutoComplete?action=complete&searchId=" + escape(searchId.value);
	var xhr = new XMLHttpRequest();
	xhr.open('GET', url, true);
	xhr.onreadystatechange = function(){
		clearTable();
		if (xhr.readyState == 4)
		{
			if (xhr.status == 200)
			{
				parseMessages(xhr.responseXML);
			}
		}
	}
	xhr.send(null);
	console.log("finished doCompletion")
}

function initRequest()
{
	console.log("initRequest started")

	if(window.XMLHttpRequest)
	{
		if(navigator.userAgent.indexOf('MSIE') != -1)
		{
			isIE = true;
		}
		return new XMLHttpRequest();
	}
	else if (window.activeXObject)
	{
		isIE = true;
		return new ActiveXObject("Microsoft.XMLHTTP");
	}
	console.log("initRequest ended")

}

function appendProduct(productName, productId)
{
	console.log("appendProduct started")
	var row;
	var cell;
	var linkElement;

	if(isIE)
	{
		completeTable.style.display = "block";
		row = completeTable.insertRow(completeTable.rows.length);
		cell = row.insertCell(0);
	}
	else
	{
		completeTable.style.display = 'table';
		row = document.createElement('tr');
		cell = document.createElement('td');
		row.appendChild(cell);
		completeTable.appendChild(row);
	}

	cell.className = "popupCell";
	linkElement= document.createElement("a");
	linkElement.className = "popupItem";

	if (productId.includes("television"))
	{
		linkElement.setAttribute("href", "TVList?searchId=" + productId);
	}
	if (productId.includes("smartphone"))
	{
		linkElement.setAttribute("href", "SmartphoneList?searchId=" + productId);
	}
	if (productId.includes("tablet"))
	{
		linkElement.setAttribute("href", "TabletList?searchId=" + productId);
	}
	if (productId.includes("laptop"))
	{
		linkElement.setAttribute("href", "LaptopList?searchId=" + productId);
	}
	console.log(linkElement);
	linkElement.appendChild(document.createTextNode(productName));
	cell.appendChild(linkElement)
	console.log("appendProduct ended")

}

function parseMessages(responseXML)
{
	console.log("parseMessages started")

	if (responseXML == null)
	{
		return false;
		console.log("responseXML was NULL");
	}
	else
	{
		console.log("else reached : PARSEMESSAGES");
		var products = responseXML.getElementsByTagName("products")[0];
		if (products.childNodes.length > 0)
		{
			console.log("parseMessages second If reached");
			completeTable.setAttribute("bordercolor", "black");
			completeTable.setAttribute("border", '1');
			for (var loop = 0; loop < products.childNodes.length; loop++)
			{
				var product = products.childNodes[loop];
				var productName = product.getElementsByTagName("productName")[0];
				var productId = product.getElementsByTagName("id")[0];
				appendProduct(productName.childNodes[0].nodeValue, productId.childNodes[0].nodeValue);

			}
		}
	}
	console.log("appendProduct ended");

}


function clearTable() 
{
	console.log("clearTable started")

	if (completeTable.getElementsByTagName("tr").length > 0)
	{
		completeTable.style.display = "none";
		for (var loop = completeTable.childNodes.length -1; loop >=0 ; loop--)
		{
			completeTable.removeChild(completeTable.childNodes[loop]);
		}
		
	}
	console.log("clearTable ended")

}






