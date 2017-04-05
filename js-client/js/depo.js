var depo="http://localhost:8088/depo";

$.dto=null;

$(document).on("click", "a", function() {
    var action = $(this).text();
    var selectedId = $(this).data("id");
    if (action == "delete") {
      deleteDepo(selectedId);
    }
});

 $('#btnDepoClean').click(function(){
    $("#depoId").val("");
    $("#name").val("");
 });



 $('#btnDepoSave').click(function(){
  if($('#depoId').val()!='')
  updateDepo();
  return false;
  });

 $('#btnDepoAdd').click(function(){
    if($('#newName').val()!='')
    addDepo();
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

function deleteDepo(id){
     console.log('delete depo');
     $.ajax({
     type: 'DELETE',
     contentType: 'application/json',
     url:depo+"/delete/"+id,
     success:function(textStatus){
     alert('Depo delete successful');
     getAllDepo();
     },
     error:function (jqXHR, textStatus, errorThrown) {
           alert('delete Depo error: ' + errorThrown);
     }
     });
 }

function updateDepo(){
        console.log('updateDepo');
        $.ajax({
        type:'PUT',
        contentType:'application/json',
        url:depo+"/update",
        data:formToJSON(),
        success:function(data,textStatus,jqXHR){
         alert('Depo updated succesfully');
          $("#depoId").val("");
          $("#name").val("");
         getAllDepo();
         },
         error:function(jqXHR,textStatus,errorThrown){
         alert('updateDepo error: '+errorThrown);
         }
        });
  }


 function addDepo() {
        console.log('addDepo');
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: depo + "/add",
            dataType: 'json',
            data: formToAddJSON(),
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
   row.append($("<td>"  +depo.count + '</td>'));
   row.append($("<td>" + depo.sum + '</td>'));
   row.append($("<td>" + '<a href="#" data-id="' + depo.id + '">delete</a></td>'));
 }

function formToJSON() {
    var id = $('#depoId').val();
    return JSON.stringify({
        "id": id == "" ? null : id,
        "name": $('#name').val()
    });

 }
  function formToAddJSON() {
         //var id = $('#depoId').val();
         return JSON.stringify({
             "id":  null,
             "name": $('#newName').val()
         });
  }