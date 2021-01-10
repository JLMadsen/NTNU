var prefix = "em";
var face = "em-no_mouth";

window.onload = function() {
    var button = document.querySelector("#button");
    button.addEventListener("click", e =>{
        getValue();
    });
}

function getValue(){
    var text = document.getElementById("input").value;

    if(text == ""){
        alert("Empty input.");
        return;
    }

    var url = "http://bigdata.stud.iie.ntnu.no/sentiment/webresources/sentiment/log?api-key=Happy!!!";

    fetch(url, {
        method: "POST",
        headers: {
        "Content-Type": "application/json; charset=utf-8",
        'Accept': 'application/json'
        },
        body: JSON.stringify({ sentence: text })
        })
        .then(response => response.json())
        .then(json => getMood(json))
        .catch(error => console.error("Error: ", error));

    document.getElementById("input").value = "";
}

function getMood(json) {

    var number = json.value;
    console.log(json);
    
    document.getElementById("output").classList.remove("em");
    document.getElementById("output").classList.remove(face);
    document.getElementById("output").classList.add("em");

    switch(number){
        case 0:
            face = "em-face_with_symbols_on_mouth";
            document.getElementById("output").classList.add(face);

            setTimeout(function(){
                angry();
            }, 20);
            
            break;
        case 1:
            face = "em-face_with_raised_eyebrow"
            document.getElementById("output").classList.add(face);
            break;
        case 2:
            face = "em-neutral_face"
            document.getElementById("output").classList.add(face);
            break;
        case 3:
            face = "em-sunglasses"
            document.getElementById("output").classList.add(face);
            break;
        case 4:
            face = "em-star-struck"
            document.getElementById("output").classList.add(face);
            break;
        default:
                face = "em-no_mouth";
                document.getElementById("output").classList.add(face);
                break;
    } 
}

function angry() {
    alert("you are very angry");
}