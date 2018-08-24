function login(email, password){
	
	if(email==""||password==""){
		alert("fills forms please")
	}
	else{
		var xhr = new XMLHttpRequest();
	    xhr.open("POST", "http://localhost:8080/FoodMarket/example/log/login", true);
	    xhr.setRequestHeader('Content-Type', 'application/json');
	    var data = JSON.stringify({"email": email, "password": password});
	    xhr.send(data);
	    xhr.responseType = 'json';
	    xhr.onload = function (){
	    var success = xhr.response;
	       if(success.result == "true"){
	           alert("success");
	           window.location.href="Landing.html";
	       }
	       else{
	           alert("Wrong Login");
	       }
	   } 
	}
}