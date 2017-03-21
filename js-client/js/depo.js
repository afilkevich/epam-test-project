var host="http://localhost:8088";

$.dto=null;

getAllDepo();

function getAllDepo(){
$.ajax({
type: 'GET',
url: host +"/depo/getAll",
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
        drawRow(depo);
    });
}

function drawRow(depo) {
    var row = $("<tr />")
    $("#depoList").append(row);

    row.append($("<td>" + '<a href="#" data-id="' + depo.id + '">' + depo.name + '</a></td>'));

}

function formToJSON() {
    var id = $('#Id').val();
    return JSON.stringify({
        "Id": id == "" ? null : id,
        "Name": $('#Name').val(),

    });
}