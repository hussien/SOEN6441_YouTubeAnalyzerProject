var data =[];
var tableData;

      var wsUri = "ws://localhost:9000/ws/adf";
      var output;
		
      function init() {
         output = document.getElementById("output");
         testWebSocket();
      }
		
      function testWebSocket() {
         websocket = new WebSocket(wsUri);
			
         websocket.onopen = function(evt) {
            onOpen(evt)
         };
		
         websocket.onmessage = function(evt) {
            onMessage(evt)
         };
		
         websocket.onerror = function(evt) {
            onError(evt)
         };
      }
		
      function onOpen(evt) {
         writeToScreen("CONNECTED");
         doSend("WebSocket rocks");
      }
		
      function onMessage(evt) {
         writeToScreen('<span style = "color: blue;">RESPONSE: ' +
            evt.data+'</span>///////////'); websocket.close();
      }

      function onError(evt) {
         writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
      }
		
      function doSend(message) {
         writeToScreen("SENT: " + message); websocket.send(message);
      }
		
      function writeToScreen(message) {
         var pre = document.createElement("p"); 
        // pre.style.wordWrap = "break-word"; 
       //  pre.innerHTML = message; output.appendChild(pre);
      }
		
      window.addEventListener("load", init, false);
		

function httpGet(term)
{

//use this websocket call to bind Actor in Java code
 	var webSocket = new WebSocket('ws://localhost:9000/ws/'+term);
 	
    webSocket.onopen = function(evt) { 
    webSocket.send('00000');
    	console.log( evt);
    };
    
    webSocket.onmessage = function(evt) { 
    };
        
	url = 'http://localhost:9000/get/'+term;
	
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", url, false );
    xmlHttp.send( null );
    var response = xmlHttp.responseText;
    data = JSON.parse(xmlHttp.responseText);
    dataMapper(data);
    return data;
}

function dataMapper(data)
{
	if(data != null)
	{
		var items = data;
		for(var i=0;i<items.length;i++)
		{
			items1 = items[i];
			itemObject = items1['items'];
			termName = items1['term'];
			tableData = "<br/>"+
			"<h1 class='center'>Search Query is "+"  " + termName +" </h1>"+
			"<table id='dtBasicExample' class='table table-striped' cellspacing='0' width='100%' style='text-align:center;'>"+
			"<thead><tr>	<th class='th-sm'>VID</th><th class='th-sm'>Video Title</th><th class='th-sm'>Owner</th><th class='th-sm'>View_Count</th><th class='th-sm'>time_lapsed</th><th class='th-sm'>Similarity</th><th class='th-sm'>Sentiment</th>   </tr></thead>"+
			"<tbody>"+
			createData(itemObject);+
			"</table>";
		}
		$('.s004').append(tableData);
	}
}

function createData(itemObject)
{
	trData ="";
	for (var j = 0;j<itemObject.length;j++)
	{
		trData += "<tr>";
		trData += "<td>" + itemObject[j]['videoId']+ "</td>";
		trData += "<td>" + itemObject[j]['title']+ "</td>";
		trData += "<td>" + itemObject[j]['channelTitle']+ "</td>";
		trData += "<td>" + itemObject[j]['viewsCount']+ "</td>";
		trData += "<td>" + itemObject[j]['duration']+ "</td>";
		trData += "<td>" + itemObject[j]['similarity']+ "</td>";
		if(itemObject[j]['similarity'] == 0)
			trData += "<td> <img src='assets/images/yellow_like.png'/> </td>";
		else if(itemObject[j]['similarity'] == 1)
			trData += "<td> <img src='assets/images/green_like.png'/> </td>";
		else 
			trData += "<td> <img src='assets/images/red_like.png'/> </td>";
			
		trData += "</tr>";
	}
	return trData;
}

$(document).ready(function() {
	$('input').on('keyup', function() {
		if($(this).val().length > 3)
		{
			httpGet($(this).val());
		}
	});
});