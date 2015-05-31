(function($) {

  RemoveTableRow = function(handler) {
    var tr = $(handler).closest('tr');

    tr.fadeOut(400, function(){ 
      tr.remove(); 
    }); 

    return false;
  };
  
  AddTableRow = function() {
      
      var newRow = $("<tr>");
      var cols = "";
      
      cols += '<td>&nbsp;</td>';
      cols += '<td>&nbsp;</td>';
      cols += '<td>&nbsp;</td>';
      cols += '<td>&nbsp;</td>';
      
      cols += '<td class="actions">';
      cols += '<button class="btn btn-large btn-danger" onclick="RemoveTableRow(this)" type="button">Remover</button>';
      cols += '</td>';
      
      newRow.append(cols);
      
      $("#products-table").append(newRow);
    
      return false;
  };
  
})(jQuery);