function addItem(itemName, itemCount, description, price, addedBy){

   var xhr = new XMLHttpRequest();
   xhr.open("POST", "http://localhost:8080/FoodMarket/example/web/addItem", true);
   xhr.setRequestHeader('Content-Type', 'application/json');
   var data = JSON.stringify({"itemName": itemName, "itemCount": itemCount, "itemDescription": description, "price": price, "addedBy": addedBy});
   xhr.send(data);
   xhr.responseType = 'json';
   xhr.onload = function (){
       var success = xhr.response;
       if(success.result == "true"){
           alert("success");
           }
       else{
           alert("fail");
           }
          } 
      }           
