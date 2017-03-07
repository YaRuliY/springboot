document.addEventListener("DOMContentLoaded", function(){
    popupObject = new Popup(document.getElementById('popup'), {
        width: 400,
        height: 300
    });
    var id = undefined;
    var url = document.URL.toString();
    if(url.indexOf("edit/") > 0){
        id = url.substring(url.indexOf('edit/')+5, url.length);
        var request = new XMLHttpRequest;
        request.onreadystatechange = function () {
            if (request.readyState == 4 && request.status == 200) {
                var data = JSON.parse(request.responseText);
                document.getElementById('id').value = data["id"];
                document.getElementById('name').value = data["name"];
                document.getElementById('surname').value = data["surname"];
                document.getElementById('patronymic').value = data["patronymic"];
                document.getElementById('telephone').value = data["telephone"];
                document.getElementById('hometel').value = data["hometel"];
                document.getElementById('address').value = data["address"];
                document.getElementById('email').value = data["email"];
            }
        };
        request.open("GET", "/get/record/"+id, false);
        request.send();
    }
});