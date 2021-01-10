let myButton = document.querySelector("#myButton");
let login = document.querySelector("#Login");

var brukernavn = "jakob";

login.addEventListener("click", e=>{
  console.log("Login");
  var passord = document.querySelector("#passord").value;
  let url = "/login";

  fetch(url, {
    method: "POST",
    body: JSON.stringify({
      "brukernavn": brukernavn,
      "passord": passord }),
    headers: {
      "Content-Type": "application/json; charset=utf-8",
    }
  })
      .then(response => response.json())
      .then(json => localStorage.setItem("Token", json.jwt) )
      .catch(error => console.error("Error: ", error));

});

myButton.addEventListener("click", e => {
  console.log(localStorage.getItem("Token"));
  let url = "api/person/1";

  fetch(url, {
    method: "GET",
    headers: {
      "x-access-token": localStorage.getItem("Token")
    }
    })
      .then(response => refreshToken(response))
      .then(response => response.json())
      .then(json => console.log(JSON.stringify(json)))
      .catch(error => console.error("Error: ", error));
});

function refreshToken(response){
  var stat = response.status;
  console.log("Status: " + stat);
  if (stat != 401){
    fetch("/token", {
      method: "POST",
      headers: {
        "x-access-token": localStorage.getItem("Token")
      }
    })
    .then(response => response.json())
    .then(json => localStorage.setItem("Token", json.jwt))
    .catch(error => console.error("Error: ", error));
  }
  else{
    console.log("401, ugyldig token");
  }
  return response;
}


