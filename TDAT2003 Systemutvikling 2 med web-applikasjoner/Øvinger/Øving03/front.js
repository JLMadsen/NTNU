/*window.onload = function() {
    let button = document.getElementById("testBTN");
    button.addEventListener("click", function(){
        addArticle();
    });
}*/

initialize();

var counter = 0;
var con;

function initialize(){
    console.log("initialize");

    // connect
    con = connect();

    // get articles from sql
    var a = getArticles();
    
    // add newsitems
    //addArticle(a);

    // add liveitems

}

function connect(){
    console.log("connect");

    var mysql = require('mysql');

    var connection = mysql.createConnection({
    host: "mysql-ait.stud.idi.ntnu.no",
    user: "jakoblm",
    password: "p1uvcygs"
    });

    connection.connect(function(err) {
    if (err) throw err;
    console.log("Connected!");
    });

    return connection;
}

function addNArticle(headline, n){
    for(var i = 0; i<n; i++){
        var node = document.createElement("p");
       var title = document.createTextNode(headline +" "+ n);
       node.appendChild(title);
       node.className = "post";
       document.getElementById("content").appendChild(node);
    }
}

function addArticle(){
    counter++;
    console.log("addArticle");

    /**
     * contains:
     * title
     * picture
     * text
     * time and date
     * importance
     */

    // create node
    var node = document.createElement("p");

    // create title
    var title = document.createTextNode("BREAKING NEWS "+ counter);

    // append title to node
    node.appendChild(title);

    // gove node correct class for formatting
    node.className = "post";

    // add to page
    document.getElementById("content").appendChild(node);

}