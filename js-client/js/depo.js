var depo="http://localhost:8088/depo";
var wagon="http://localhost:8088/wagon";

$.dto=null;

getAllDepo();

function getAllDepo(){
$.ajax({
type: 'GET',
url: depo +"/getAll",
dataType:'json',
success: renderList,
error:function(jqXHR, textStatus, errorThrown){
console.log(jqXHR, textStatus, errorThrown);
alert('getAll:'+textStatus +jqXHR)
}
});
}

function renderList(data) {
    dto = data == null ? [] : (data instanceof Array ? data : [data]);
    $('#depoList tr').remove();

    $.each(dto, function (index, depo) {
        drawRow(index,depo);
    });
}

function drawRow(index,depo) {

    var row = $("<tr />")
    $("#depoList").append(row);

   row.append($("<td>" +depo.id+'</td>'));
   row.append($("<td>"   + depo.name + '</td>'));
   row.append($("<td>"  +countWagon(depo.id) + '</td>'));
    row.append($("<td>" +   3 + '</td>'));

}

function countWagon(id){

var countWagon;
$.ajax({
type: 'GET',
dataType:'json',
url: wagon +"/countOfWagon/"+id,
success:function(data){
countWagon=data}
});
return countWagon;
}


function formToJSON() {
    var id = $('#Id').val();
    return JSON.stringify({
        "Id": id == "" ? null : id,
        "Name": $('#Name').val(),

    });
}