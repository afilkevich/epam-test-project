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

$('#btnWagonSave').click(function(){
    if(($('#wagonId').val()!='')&&($('#typeOfWagon').val()!='')&&($('#idDepo').val()!='')&&($('#count').val()!='')&&($('#date').val()!='')){
    addWagon();
    return false;
     }
     else
     alert("please, write data in the form's");

    });

 $('#btnWagonUpdate').click(function(){
         if(($('#wagonId').val()!='')&&($('#typeOfWagon').val()!='')&&($('#idDepo').val()!='')&&($('#count').val()!='')&&($('#date').val()!='')){
         updateWagon();
        return false;
        }
        else
        alert("please, write data in the form's")
 });

 $('#btnWagonSelect').click(function(){
 if(($('#fromDate').val()!="")&($('#toDate').val()!="")){
    var from=$('#fromDate').val();
    var to=$('#toDate').val();
    selectWagon(from,to);
    return false;
}
else{
alert('please,write date in form');
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


 function selectWagon(from,to){
    $.ajax({
    type: 'GET',
    url: wagon +"/getByDate/"+from+"/"+to,
     dataType:'json',
     success: renderList ,
     error:function(jqXHR, textStatus, errorThrown){
     console.log(jqXHR, textStatus, errorThrown);
     alert('select wagon:'+textStatus +jqXHR);
      }
    });
  }



function renderList(data) {
    dto = data == null ? [] : (data instanceof Array ? data : [data]);
    $('#wagonList tr').remove();
    $('#fromDate').val("");
    $('#toDate').val('');

    $.each(dto, function (index, wagon) {
        drawRow(wagon);
    });
}

function drawRow(wagon) {
    var row = $("<tr />")
    $("#wagonList").append(row);

   row.append($("<td>" +wagon.id+'</td>'));
   row.append($("<td>" + wagon.type + '</td>'));
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

 function addWagon() {
         console.log('addWagon');
         $.ajax({
             type: 'POST',
             contentType: 'application/json',
             url: wagon + "/add",
             dataType: 'json',
             data: formToJSON(),
             success: function (data, textStatus, jqXHR) {
                 alert('Wagon created successfully');
                 console.log('Wagon created successfully');
                     $("#wagonId").val("");
                     $("#typeOfWagon").val("");
                     $("#idDepo").val("");
                     $("#count").val("");
                     $("#date").val("");
                 getAllWagon();
             },
             error: function (jqXHR, textStatus, errorThrown) {
                 alert('addWagon error: ' + errorThrown);
             }
         });
 }


 function updateWagon(){
           console.log('updateWagon');
           $.ajax({
           type:'PUT',
           contentType:'application/json',
           url:wagon+"/update",
           data:formToJSON(),
           success:function(data,textStatus,jqXHR){
            alert('Wagon updated succesfully');
                     $("#wagonId").val("");
                     $("#typeOfWagon").val("");
                     $("#idDepo").val("");
                     $("#count").val("");
                     $("#date").val("");
            getAllWagon();
            },
            error:function(jqXHR,textStatus,errorThrown){
            alert('updateWagon error: '+errorThrown);
            }
           });
   }

  function formToJSON() {
           return JSON.stringify({
              "id": $('#wagonId').val(),
              "type": $('#typeOfWagon').val(),
              "depoId" : $('#idDepo').val(),
              "countOfSeat" :$('#count').val(),
              "dateOfBuilder" : $('#date').val()
          });
   }