var depo="http://localhost:8088/depo";
var wagon="http://localhost:8088/wagon";

$.dto=null;



 $('#btnDepoSave').click(function(){
  if($('#depoId').val()=='')
  addDepo();
  else
  updateDepo();
  return false;
  });

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
        drawRow(depo);
    });
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

  function sumOfSeats(id){
     var sumOfSeats;
     $.ajax({
     type: 'GET',
     dataType:'json',
     url: wagon +"/sumOfSeats/"+id,
     success:function(data){
     sumOfSeats=data}
     });
     return sumOfSeats;
    }

    function addDepo() {
        console.log('addDepo');
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: depo + "/add",
            dataType: 'json',
            data: formToJSON(),
            success: function (data, textStatus, jqXHR) {
                alert('Depo created successfully');
                console.log('Depo created successfully');
                $('#depoId').val(data.id);
                getAllDepo();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert('addDepo error: ' + errorThrown);
            }
        });
    }

function drawRow(depo) {
    var row = $("<tr />")
    $("#depoList").append(row);

   row.append($("<td>" +depo.id+'</td>'));
   row.append($("<td>"   + depo.name + '</td>'));
   row.append($("<td>"  +countWagon(depo.id) + '</td>'));
   row.append($("<td>" + sumOfSeats(depo.id) + '</td>'));
   row.append($("<td>" + '<a href="#" data-id="' + depo.id + '">delete</a></td>'));
}

function formToJSON() {
    var id = $('#depoId').val();

    return JSON.stringify({
        "id": id == "" ? null : id,
        "name": $('#name').val()
    });

}