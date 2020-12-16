var data =[];
var tableData;

function httpGet(term)
{
    var webSocket = new WebSocket('ws://localhost:9000/ws');
    console.log(webSocket);
    webSocket.onmessage = function(event) {
    	log(event)
    };
        
	url = 'http://localhost:9000/get/'+term;
	if(term=='')
		url = 'http://localhost:9000/update';
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
	$('input').keypress(function(event){
        var keycode = (event.keyCode ? event.keyCode : event.which);
        if(keycode == '13'){
//        	alert('ok');
        	httpGet($(this).val());  
        }
	})
        
	/*$('input').on('keyup', function() {
		if($(this).val().length > 3)
		{
			httpGet($(this).val());
		}
	});*/
});

$(document).ready(function() {
	setInterval(function() {
		//alert('ok');
		httpGet('');
	}, 60000);	
});
