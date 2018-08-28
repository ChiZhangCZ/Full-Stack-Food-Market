function updateUser(username, contactNo, houseNo, postcode){

   var xhr = new XMLHttpRequest();
   xhr.open("POST", "http://localhost:8080/FoodMarket/example/update/user", true);
   xhr.setRequestHeader('Content-Type', 'application/json');
   var data = JSON.stringify({"username": username, "contact_no": contactNo, "houseNameNo": houseNo, "postcode": postcode});
   xhr.send(data);
   xhr.responseType = 'json';
   xhr.onload = function (){
       var success = xhr.response;
       if(success.result == "true"){
           alert("Success");
       }
       else{
           alert("Fail");
       }
  }           
}