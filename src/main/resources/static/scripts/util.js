var popupObject;
var getRecordsURL = "/get/records";

var preparingRecords = function(url) {
    document.getElementById('table').innerHTML = '';
    var request = new XMLHttpRequest;
    request.onreadystatechange = function () {
        if (request.readyState == 4 && request.status == 200) {
            var data = JSON.parse(request.responseText);
            var table = document.getElementById("table");
            var header = table.createTHead();
            var headerRow = header.insertRow(0);
            headerRow.insertCell(0).innerHTML = "<b>Name</b>";
            headerRow.insertCell(1).innerHTML = "<b>Surname</b>";
            headerRow.insertCell(2).innerHTML = "<b>Patronymic</b>";
            headerRow.insertCell(3).innerHTML = "<b>Telephone</b>";
            headerRow.insertCell(4).innerHTML = "<b>Hometel</b>";
            headerRow.insertCell(5).innerHTML = "<b>Address</b>";
            headerRow.insertCell(6).innerHTML = "<b>E-mail</b>";

            var foot = table.createTFoot();
            for (var i = 0; i < data.length; i++) {
                var row = foot.insertRow(i);
                row.insertCell(0).innerHTML = data[i]["name"];
                row.insertCell(1).innerHTML = data[i]["surname"];
                row.insertCell(2).innerHTML = data[i]["patronymic"];
                row.insertCell(3).innerHTML = preperingTelNumber(data[i]["telephone"]);
                console.log('data[i]["hometel"]: ' + data[i]["hometel"] + data[i]["hometel"].length);
                row.insertCell(4).innerHTML = preperingTelNumber(data[i]["hometel"]);
                row.insertCell(5).innerHTML = data[i]["address"];
                row.insertCell(6).innerHTML = data[i]["email"];
                var id = data[i]["id"];
                console.log('id: '+id);
                row.insertCell(7).innerHTML =
                    "<input class=\"delete\" type=\"button\" value=\"(D)\" onclick=\"deleteRecord(" + id + ")\"/>";
                var prepurl = "document.location.href=" + "'/edit/" + id + "'";
                row.insertCell(8).innerHTML =
                    "<input class=\"delete\" type=\"button\" value=\"(E)\" onClick=\"" + prepurl + "\"/>";
            }
        }
    };
    request.open("GET", url, false);
    request.send();
};

var deleteRecord = function(id) {
    var url = '/delete/' + id;
    var request = new XMLHttpRequest;
    request.onreadystatechange = function() {
        if (request.readyState == 4 && request.status == 200){
            popupObject.setMessage(request.responseText);
            popupObject.open();
            preparingRecords(getRecordsURL);
        }
    };
    request.open("DELETE", url);
    request.send();
};

var doSearch = function(){
    var url =  "/search/" + document.getElementById("searchbox").value.toString();
    if(url != ''){
        var request = new XMLHttpRequest;
        request.onreadystatechange = function() {
            if (request.readyState == 4 && request.status == 200){
                preparingRecords(url);
            }
        };
        request.open("GET", url);
        request.send();
    }
    else preparingRecords(getRecordsURL);
};

var saveRecord = function() {
    if (document.getElementById("name").value.toString()=='' ||
        document.getElementById("surname").value.toString()==''){
        popupObject.setMessage('Fill out all fields');
        popupObject.open();
        return;
    }

    var url = '/add/record'
        + '?name=' + document.getElementById("name").value.toString()
        + '&surname=' + document.getElementById("surname").value.toString()
        + '&patronymic=' + document.getElementById("patronymic").value.toString()
        + '&telephone=' + document.getElementById("telephone").value.toString()
        + '&hometel=' + document.getElementById("hometel").value.toString()
        + '&address=' + document.getElementById("address").value.toString()
        + '&email=' + document.getElementById("email").value.toString();
    var request = new XMLHttpRequest;
    request.onreadystatechange = function() {
        if (request.readyState == 4 && request.status == 200){
            popupObject.setMessage(request.responseText);
            popupObject.open();
        }
    };
    request.open('POST', url);
    request.send();
};

var editRecord = function(id) {
    var url = '/edit/record/' + id
        + '?name=' + document.getElementById("name").value.toString()
        + '&surname=' + document.getElementById("surname").value.toString()
        + '&patronymic=' + document.getElementById("patronymic").value.toString()
        + '&telephone=' + document.getElementById("telephone").value.toString()
        + '&hometel=' + document.getElementById("hometel").value.toString()
        + '&address=' + document.getElementById("address").value.toString()
        + '&email=' + document.getElementById("email").value.toString();
    var request = new XMLHttpRequest;
    request.onreadystatechange = function() {
        if (request.readyState == 4 && request.status == 200){
            popupObject.setMessage(request.responseText);
            popupObject.open();
        }
    };
    request.open("PUT", url);
    request.send();
};

var saveUser = function() {
    if (document.getElementById("username").value.toString()=='' ||
        document.getElementById("password").value.toString()=='' ||
        document.getElementById("confirm").value.toString()=='' ||
        document.getElementById("fio").value.toString()==''){
        popupObject.setMessage('Fill out all fields');
        popupObject.open();
        return;
    }

    var url = '/user/add'
        + '?username=' + document.getElementById("username").value.toString()
        + '&password=' + document.getElementById("password").value.toString()
        + '&confirm=' + document.getElementById("confirm").value.toString()
        + '&fio=' + document.getElementById("fio").value.toString();
    var request = new XMLHttpRequest;
    request.onreadystatechange = function() {
        if (request.readyState == 4 && request.status == 200){
            popupObject.setMessage(request.responseText);
            popupObject.open();
        }
    };
    request.open('POST', url);
    request.send();
};

var preperingTelNumber = function(num){
    if(num.length > 0){
        num = num.substr(0, 3) + '(' + num.substr(3, 2) + ')' + num.substr(5, num.length);
        return '+' + num;
    }
};