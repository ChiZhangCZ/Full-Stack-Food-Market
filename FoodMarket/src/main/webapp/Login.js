function login(user, password){
	
	if(user==""||password==""){
		alert("fills forms please")
	}
	else{
		var xhr = new XMLHttpRequest();
	    xhr.open("POST", "http://localhost:8080/FoodMarket/example/log/login", true);
	    xhr.setRequestHeader('Content-Type', 'application/json');
	    var data = JSON.stringify({"username": user, "password": password});
	    xhr.send(data);
	    xhr.responseType = 'json';
	    xhr.onload = function (){
	    var user = xhr.response;
	       if(user.result != "null"){
	           sessionStorage.setItem("loggedIn",user.result);
	           window.location.href="Landing.html";
	       }
	       else{
	           alert("Wrong Username/Password");
	       }
	   } 
	}
}