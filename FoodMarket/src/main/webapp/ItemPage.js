var requestURL = 'http://localhost:8080/FoodMarket/example/read/findItem/'+sessionStorage.getItem("itemID");
var request = new XMLHttpRequest();
request.open('GET', requestURL);
request.responseType = 'json';
request.send();
request.onload = function () {
    var item = request.response;
    console.log(item);
    let element = document.getElementById("Item description");
    element.innerHTML += "<label>Name:</label>"+ item.Item_Name + "<br><br>";
    element.innerHTML += "<label>Description:</label>"+ item.Item_Description + "<br><br>";
    element.innerHTML += "<label>Price:</label>"+ item.Price + "<br><br>";
    element.innerHTML += "<label>Number Available:</label>"+ item.Item_Count + "<br><br>";
    
    var memberURL = 'http://localhost:8080/FoodMarket/example/read/findMember/'+ item.Added_By;;
    var memberRequest = new XMLHttpRequest();
    memberRequest.open('GET', memberURL);
    memberRequest.responseType = 'json';
    memberRequest.send();
    memberRequest.onload = function () {
        var member = memberRequest.response;
        console.log(member);
        let element = document.getElementById("Seller");
        element.innerHTML += "<label>Name:</label>"+ member.First_Name+ " " + member.Last_Name + "<br><br>";
        element.innerHTML += "<label>House:</label>"+ member.HouseNameNo + "<br><br>";
        element.innerHTML += "<label>Postcode:</label>"+ member.Postcode + "<br><br>";
    }
}
