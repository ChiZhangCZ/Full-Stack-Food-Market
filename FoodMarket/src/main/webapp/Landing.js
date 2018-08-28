var requestURL = 'http://localhost:8080/FoodMarket/example/read/allItems/';
var request = new XMLHttpRequest();
request.open('GET', requestURL);
request.responseType = 'json';
request.send();

request.onload = function () {
var items = request.response;
for(let item of items){
	   let element = document.getElementById("grid");
	   let newDiv = document.createElement('div');
	   let button = document.createElement("input");
	   button.type = "button";
	   button.value = "more info";
	   button.onclick = function(){
		   sessionStorage.setItem("itemID",item.Item_ID);
	       window.location.href='ItemPage.html';
	       };
	   newDiv.id = item.Item_ID;
	   newDiv.className ="grid-item";
	   newDiv.innerHTML = item.Item_Name + "<br>";
	   newDiv.appendChild(button);
	   element.appendChild(newDiv);
	}
}