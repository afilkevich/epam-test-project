var wagon="http://localhost:8088/wagon";

$.dto=null;

getAllWagon();

$(document).on("click", "a", function() {
    var action = $(this).text();
    var selectedId = $(this).data("id");
    if (action == "delete") {
      deleteWagon(selectedId);
    }
});

 $('#btnWagonClean').click(function(){
    $("#wagonId").val("");
    $("#typeOfWagon").val("");
    $("#idDepo").val("");
    $("#count").val("");
    $("#date").val("");
 });


function getAllWagon(){
   $.ajax({
   type: 'GET',
   url: wagon +"/getAllWagon",
    dataType:'json',
    success: renderList,
    error:function(jqXHR, textStatus, errorThrown){
    console.log(jqXHR, textStatus, errorThrown);
    alert('getAll:'+textStatus +jqXHR);
     }
   });
 }



function renderList(data) {
    dto = data == null ? [] : (data instanceof Array ? data : [data]);
    $('#wagonList tr').remove();

    $.each(dto, function (index, wagon) {
        drawRow(wagon);
    });
}

function drawRow(wagon) {
    var row = $("<tr />")
    $("#wagonList").append(row);

   row.append($("<td>" +wagon.id+'</td>'));
   row.append($("<td>"   + wagon.type + '</td>'));
   row.append($("<td>"  +wagon.depoId + '</td>'));
   row.append($("<td>" + wagon.countOfSeat + '</td>'));
   row.append($("<td>"+ wagon.dateOfBuilder +'</td>'))
   row.append($("<td>" + '<a href="#" data-id="' + wagon.id + '">delete</a></td>'));
 }

 function deleteWagon(id){
    console.log('delete wagon');
        $.ajax({
        type: 'DELETE',
        contentType: 'application/json',
        url:wagon+"/delete/"+id,
        success:function(textStatus){
        alert('Wagon delete successful');
        getAllWagon();
        },
        error:function (jqXHR, textStatus, errorThrown) {
              alert('delete wagon error: ' + errorThrown);
        }
        });

   }