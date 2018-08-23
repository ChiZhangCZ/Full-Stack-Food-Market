function addInterestingStuff(username, password, password_confirm, email, firstname, lastname){
    if(password != password_confirm){
       alert("Passwords do not match");
    }
    else if(username==""||password==""||email==""||firstname==""||lastname==""){
       alert("Unable to register. Make sure you have filled all the forms.");
    }
    else{
       var xhr = new XMLHttpRequest();
       xhr.open("POST", "http://localhost:8080/FoodMarket/example/web/addFromInput", true);
       xhr.setRequestHeader('Content-Type', 'application/json');
       var data = JSON.stringify({"username": username, "password": password, "email": email, "first_name": firstname, "last_name": lastname});
       xhr.send(data);
       xhr.responseType = 'json';
       xhr.onload = function (){
           var success = xhr.response;
           if(success.result == "true"){
               alert("success");
           }
           else{
               alert("Username/Email already taken");
           }
          } 
      }           
}

function searchStuff(name){
    var requestURL = 'http://localhost:8080/FoodMarket/example/web/search/' + name;
	var request = new XMLHttpRequest();
	request.open('GET', requestURL);
	request.responseType = 'json';
	request.send();
	request.onload = function () {
	var results = request.response;
    for (var result of results){
	    var myH1 = document.createElement('h1');
		let fn = "";
		let ln = "";
		for(var key of Object.keys(result)){
			if(key == "first_name"){
				fn = result[key];
			}
			else if(key == "last_name"){
				ln = result[key];
			}
			myH1.textContent = "Name:"+" "+fn+" "+ln;
			document.getElementsByTagName('body')[0].appendChild(myH1);
			}
		}
	}
}

function deleteMember(username){
   var xhr = new XMLHttpRequest();
   xhr.open("POST", "http://localhost:8080/FoodMarket/example/web/cleanperson", true);
   xhr.setRequestHeader('Content-Type', 'application/json');
   var data = JSON.stringify({"username": username});
   xhr.send(data);	            
}